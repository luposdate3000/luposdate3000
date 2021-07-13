package lupos.simulator_iot.net.routing

import lupos.simulator_core.Entity
import lupos.simulator_iot.Device
import lupos.simulator_iot.net.NetworkPackage

internal class RPL(internal val device: Device) : IRoutingProtocol {

    internal lateinit var routingTable: RoutingTable

    private val notInitializedRank = Int.MAX_VALUE

    private val notInitializedAddress = -1

    override var isRoot: Boolean = false

    internal var rank: Int = notInitializedRank
        private set

    internal var preferredParent: Parent = Parent()
        private set

    private var isDelayDAOTimerRunning = false

    internal inner class Parent(internal var address: Int = notInitializedAddress, internal var rank: Int = notInitializedRank)

    private fun broadcastDIO() {
        for (potentialChild in device.linkManager.getNeighbours())
            if (potentialChild != preferredParent.address) {
                sendDIO(potentialChild)
            }
    }

    private fun sendDIO(destinationAddress: Int) {
        val dio = DIO(rank)
        device.sendUnRoutedPackage(destinationAddress, dio)
        device.simRun.incNumberOfSentDIOPackages()
    }

    private fun sendDAO(destinationAddress: Int) {
        val destinations = routingTable.getDestinations()
        val nextDatabaseHops = routingTable.getNextDatabaseHops(destinations)
        val dao = DAO(true, destinations, device.hasDatabase(), nextDatabaseHops)
        device.sendUnRoutedPackage(destinationAddress, dao)
        device.simRun.incNumberOfSentDAOPackages()
    }

    private fun sendDAONoPath(destinationAddress: Int) {
        val dao = DAO(false, IntArray(0), false, IntArray(0))
        device.sendUnRoutedPackage(destinationAddress, dao)
        device.simRun.incNumberOfSentDAOPackages()
    }

    private fun processDIO(pck: NetworkPackage) {
        val dio = pck.payload as DIO
        if (objectiveFunction(pck) >= rank) {
            return
        }

        rank = objectiveFunction(pck)
        updateParent(Parent(pck.sourceAddress, dio.rank))
        broadcastDIO()
    }

    private fun updateParent(newParent: Parent) {
        if (hasParent()) {
            if (newParent.address == preferredParent.address) {
                return
            }
        }
        if (hasParent()) {
            sendDAONoPath(preferredParent.address)
        }
        preferredParent = newParent
        routingTable.fallbackHop = preferredParent.address
        sendDAO(preferredParent.address)
    }

    private fun processDAO(pck: NetworkPackage) {
        val dao = pck.payload as DAO
        val hasRoutingTableChanged = updateRoutingTable(pck.sourceAddress, dao)
        if (hasParent() && hasRoutingTableChanged) {
            if (!isDelayDAOTimerRunning) {
                startDelayDAOTimer()
            }
        }
    }

    private fun updateRoutingTable(hopAddress: Int, dao: DAO): Boolean {
        return if (dao.isPath) {
            if (dao.hopHasDatabase) {
                routingTable.setDestinationsByDatabaseHop(hopAddress, dao.destinations)
            } else {
                routingTable.setDestinationsByHop(hopAddress, dao.destinations, dao.existingDatabaseHops)
            }
        } else {
            routingTable.removeDestinationsByHop(hopAddress)
        }
    }

    private fun objectiveFunction(pck: NetworkPackage): Int {
        val link = device.linkManager.links[pck.sourceAddress]!!
        val otherRank = (pck.payload as DIO).rank
        return otherRank + link.distanceInMeters
    }

    internal fun hasParent(): Boolean =
        preferredParent.address != notInitializedAddress

    override fun startRouting() {
        val numberOfDevices = device.simRun.config.getNumberOfDevices()
        routingTable = RoutingTable(device.address, numberOfDevices, device.hasDatabase())
        if (isRoot) {
            rank = ROOT_RANK
            broadcastDIO()
        }
    }

    override fun isControlPackage(pck: NetworkPackage): Boolean =
        pck.payload is DAO || pck.payload is DIO

    private fun forwardDAO() {
        sendDAO(preferredParent.address)
    }

    internal inner class DelayDAOTimerExpired : Entity.ITimer {
        override fun onExpire() {
            isDelayDAOTimerRunning = false
            forwardDAO()
        }
    }

    private fun startDelayDAOTimer() {
        device.setTimer(daoDelay, DelayDAOTimerExpired())
        isDelayDAOTimerRunning = true
    }

    override fun processControlPackage(pck: NetworkPackage) {
        when (pck.payload) {
            is DIO -> processDIO(pck)
            is DAO -> processDAO(pck)
            else -> throw Exception("Wrong Package")
        }
    }

    override fun getNextHop(destinationAddress: Int): Int =
        routingTable.getNextHop(destinationAddress)

    override fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray =
        routingTable.getNextDatabaseHops(destinationAddresses)

    override fun toString(): String {
        val strBuilder = StringBuilder()
        strBuilder
            .append("> Device ${device.address}").append(", ")
            .append("name '${device.simRun.config.getDeviceName(device.deviceNameID)}'").append(", ")
            .append("rank $rank").append(", ")
            .append(getParentString())
            .appendLine().append("  ")
            .append("children [${getChildrenString()}]")

        return strBuilder.toString()
    }

    private fun getParentString() =
        if (hasParent()) "parent ${preferredParent.address}" else "root"

    private fun getChildrenString(): StringBuilder {
        val strBuilder = StringBuilder()
        val separator = ", "
        for (children in routingTable.getHops()) {
            val link = device.linkManager.links[children]!!
            strBuilder.append("$link to $children").append(separator)
        }
        if (strBuilder.length >= separator.length) {
            strBuilder.deleteRange(strBuilder.length - separator.length, strBuilder.length)
        }
        return strBuilder
    }

    internal companion object {

        // RPL Constants and Variables (see RFC 6550)
        internal const val DEFAULT_DAO_DELAY: Int = 1 // seconds

        internal val daoDelay: Long = 2 // DEFAULT_DAO_DELAY * 3

        internal const val ROOT_RANK: Int = 0

    }
}
