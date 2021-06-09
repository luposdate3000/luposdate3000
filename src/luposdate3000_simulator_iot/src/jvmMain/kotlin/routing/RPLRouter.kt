package lupos.simulator_iot.routing

import lupos.simulator_core.Entity
import lupos.simulator_iot.Device
import lupos.simulator_iot.NetworkPackage
import lupos.simulator_iot.config.Configuration

public class RPLRouter(public val device: Device) : IRoutingAlgorithm {

    public lateinit var routingTable: RoutingTable

    private val notInitializedRank = Int.MAX_VALUE

    private val notInitializedAddress = -1

    override var isRoot: Boolean = false

    public var rank: Int = notInitializedRank
        private set

    public var preferredParent: Parent = Parent()
        private set

    private var isDelayDAOTimerRunning = false

    public var dioSentCounter: Int = 0
        private set

    public var dioReceivedCounter: Int = 0
        private set

    public var daoSentCounter: Int = 0
        private set

    public var daoReceivedCounter: Int = 0
        private set

    public inner class Parent(public var address: Int = notInitializedAddress, public var rank: Int = notInitializedRank)

    private fun broadcastDIO() {
        for (potentialChild in device.linkManager.getNeighbours())
            if (potentialChild != preferredParent.address)
                sendDIO(potentialChild)
    }

    private fun sendDIO(destinationAddress: Int) {
        val dio = DIO(rank)
        device.sendUnRoutedPackage(destinationAddress, dio)
        dioSentCounter++
    }

    private fun sendDAO(destinationAddress: Int) {
        val destinations = routingTable.getDestinations()
        val nextDatabaseHops = routingTable.getNextDatabaseHops(destinations)

        val dao = DAO(true, destinations, device.hasDatabase(), nextDatabaseHops)
        device.sendUnRoutedPackage(destinationAddress, dao)
        daoSentCounter++
    }

    private fun sendDAONoPath(destinationAddress: Int) {
        val dao = DAO(false, IntArray(0), false, IntArray(0))
        device.sendUnRoutedPackage(destinationAddress, dao)
        daoSentCounter++
    }

    private fun processDIO(pck: NetworkPackage) {
        val dio = pck.payload as DIO
        dioReceivedCounter++
        dioCounter++
        if (objectiveFunction(pck) >= rank)
            return

        forwardedDioCounter++
        rank = objectiveFunction(pck)
        updateParent(Parent(pck.sourceAddress, dio.rank))
        broadcastDIO()
    }

    private fun updateParent(newParent: Parent) {
        if (hasParent())
            if (newParent.address == preferredParent.address)
                return

        if (hasParent())
            sendDAONoPath(preferredParent.address)

        preferredParent = newParent
        sendDAO(preferredParent.address)
        routingTable.defaultAddress = preferredParent.address
    }

    private fun processDAO(pck: NetworkPackage) {
        val dao = pck.payload as DAO
        daoReceivedCounter++
        daoCounter++

        val hasRoutingTableChanged = updateRoutingTable(pck.sourceAddress, dao)

        if (hasParent() && hasRoutingTableChanged)
            if (!isDelayDAOTimerRunning)
                startDelayDAOTimer()
    }

    private fun updateRoutingTable(hopAddress: Int, dao: DAO): Boolean {
        return if (dao.isPath) {
            if (dao.hopHasDatabase)
                routingTable.setDestinationsByDatabaseHop(hopAddress, dao.destinations)
            else
                routingTable.setDestinationsByHop(hopAddress, dao.destinations, dao.existingDatabaseHops)
        } else
            routingTable.removeDestinationsByHop(hopAddress)
    }

    private fun objectiveFunction(pck: NetworkPackage): Int {
        val link = device.linkManager.getLink(pck.sourceAddress)!!
        val otherRank = (pck.payload as DIO).rank
        return otherRank + link.distanceInMeters
    }

    public fun hasParent(): Boolean =
        preferredParent.address != notInitializedAddress

    override fun startRouting() {
        routingTable = RoutingTable(device.address, Configuration.devices.size)
        if (isRoot) {
            rank = ROOT_RANK
            broadcastDIO()
        }
    }

    public class DIO(public val rank: Int)

    public class DAO(
        public val isPath: Boolean,
        public val destinations: IntArray,
        public val hopHasDatabase: Boolean,
        public val existingDatabaseHops: IntArray
    )

    override fun isControlPackage(pck: NetworkPackage): Boolean =
        pck.payload is DAO || pck.payload is DIO

    private fun forwardDAO() {
        sendDAO(preferredParent.address)
        forwardedDaoCounter++
    }

    public inner class DelayDAOTimerExpired : Entity.ITimerExpired {
        override fun onExpire() {
            isDelayDAOTimerRunning = false
            forwardDAO()
        }
    }

    private fun startDelayDAOTimer() {
        device.setTimer(daoDelay.toLong(), DelayDAOTimerExpired())
        isDelayDAOTimerRunning = true
    }

    override fun processControlPackage(pck: NetworkPackage) {
        when (pck.payload) {
            is DIO -> processDIO(pck)
            is DAO -> processDAO(pck)
        }
    }

    override fun getNextHop(destinationAddress: Int): Int =
        routingTable.getNextHop(destinationAddress)

    override fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray =
        routingTable.getNextDatabaseHops(destinationAddresses)

    override fun getNextDatabaseHop(destinationAddress: Int): Int =
        routingTable.getNextDatabaseHop(destinationAddress)

    override fun toString(): String {
        val strBuilder = StringBuilder()
        strBuilder
            .append("> ")
            .append("Device ${device.address}").append(", ")
            .append("rank $rank").append(", ")
            .append(getParentString())
            .appendLine().append("  ")
            .append("children [${getChildrenString()}]")
            .appendLine().append("  ")
            .append("DIO (received: $dioReceivedCounter, sent: $dioSentCounter)")
            .appendLine().append("  ")
            .append("DAO (received: $daoReceivedCounter, sent: $daoSentCounter)")

        return strBuilder.toString()
    }

    private fun getParentString() =
        if (hasParent()) "parent ${preferredParent.address}" else "root"

    private fun getChildrenString(): StringBuilder {
        val strBuilder = StringBuilder()
        val separator = ", "
        for (children in routingTable.getHops()) {
            val link = device.linkManager.getLink(children)!!
            strBuilder.append("$link to $children").append(separator)
        }
        if (strBuilder.length >= separator.length)
            strBuilder.deleteRange(strBuilder.length - separator.length, strBuilder.length)
        return strBuilder
    }

    public companion object {

        // RPL Constants and Variables (see RFC 6550)
        public const val DEFAULT_DAO_DELAY: Int = 1 // seconds

        public val daoDelay: Int = 2 // DEFAULT_DAO_DELAY * 3

        public const val ROOT_RANK: Int = 0

        public var daoCounter: Int = 0
            private set

        public var dioCounter: Int = 0
            private set

        public var forwardedDaoCounter: Int = 0
            private set

        public var forwardedDioCounter: Int = 0
            private set

        public fun resetCounter() {
            daoCounter = 0
            dioCounter = 0
            forwardedDaoCounter = 0
            forwardedDioCounter = 0
        }
    }
}
