import java.lang.StringBuilder

class RPLRouter(val device: Device): Device.Router {

    val routingTable = RoutingTable(device.address)
    private val notInitialized = Int.MAX_VALUE
    var root = false

    var rank = notInitialized
        private set

    var preferredParent = Parent()
        private set

    class DelayDAOTimerExpiredMarker
    private var isDelayDAOTimerRunning = false

    var dioSentCounter = 0
        private set

    var dioReceivedCounter = 0
        private set

    var daoSentCounter = 0
        private set

    var daoReceivedCounter = 0
        private set

    inner class Parent(var address: Int = notInitialized, var rank: Int = notInitialized)


    private fun broadcastDIO() {
        for (potentialChild in device.linkManager.getNeighbours())
            if(potentialChild != preferredParent.address)
                sendDIO(potentialChild)
    }

    private fun sendDIO(destinationAddress: Int) {
        val dio = DIO(rank)
        device.sendUnRoutedPackage(destinationAddress, dio)
        dioSentCounter++
    }

    private fun sendDAO(destinationAddress: Int, isPath: Boolean) {
        val destinations = if(isPath) routingTable.getDestinations() else IntArray(0)
        val dao = DAO(isPath, destinations)
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
            sendDAO(preferredParent.address, false)

        preferredParent = newParent
        sendDAO(preferredParent.address, true)
        routingTable.defaultAddress = preferredParent.address
    }


    private fun processDAO(pck: NetworkPackage) {
        val dao = pck.payload as DAO
        daoReceivedCounter++
        daoCounter++
        val hasRoutingTableChanged: Boolean = if (dao.isPath)
            routingTable.setDestinationsByHop(pck.sourceAddress, dao.destinations)
        else
            routingTable.removeDestinationsByHop(pck.sourceAddress)

        if(hasParent() && hasRoutingTableChanged)
            if (!isDelayDAOTimerRunning)
                startDelayDAOTimer()
    }

    private fun objectiveFunction(pck: NetworkPackage): Int {
        val link = device.linkManager.getLink(pck.sourceAddress)!!
        val otherRank = (pck.payload as DIO).rank
        return otherRank + link.distanceInMeters
    }



    fun hasParent()
            = preferredParent.address != notInitialized


    override fun startRouting() {
        if(root) {
            rank = ROOT_RANK
            broadcastDIO()
        }
    }

    class DIO(val rank: Int)
    class DAO(val isPath: Boolean, val destinations: IntArray)

    override fun isControlPackage(pck: NetworkPackage)
        = pck.payload is DAO || pck.payload is DIO

    override fun isSelfEvent(marker: Any)
        = marker is DelayDAOTimerExpiredMarker

    override fun processSelfEvent(marker: Any) {
        if(marker is DelayDAOTimerExpiredMarker) {
            isDelayDAOTimerRunning = false
            forwardDAO()
        }
    }

    private fun forwardDAO() {
        sendDAO(preferredParent.address, true)
        forwardedDaoCounter++
    }

    private fun startDelayDAOTimer() {
        device.sendSelfEvent(daoDelay.toLong(), DelayDAOTimerExpiredMarker())
        isDelayDAOTimerRunning = true
    }


    override fun processControlPackage(pck: NetworkPackage) {
        when(pck.payload) {
            is DIO -> processDIO(pck)
            is DAO -> processDAO(pck)
        }
    }

    override fun getNextHop(destinationAddress: Int)
        = routingTable.getNextHop(destinationAddress)

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

    private fun getParentString()
        = if (hasParent()) "parent ${preferredParent.address}" else "root"


    private fun getChildrenString(): StringBuilder {
        val strBuilder = StringBuilder()
        val separator = ", "
        for (children in routingTable.getHops()) {
            val link = device.linkManager.getLink(children)!!
            strBuilder.append("$link to $children").append(separator)
        }
        if(strBuilder.length >= separator.length)
            strBuilder.deleteRange(strBuilder.length - separator.length, strBuilder.length)
        return strBuilder
    }

    companion object {

        //RPL Constants and Variables (see RFC 6550)
        const val DEFAULT_DAO_DELAY = 1 //seconds


        val daoDelay = 2//DEFAULT_DAO_DELAY * 3

        const val ROOT_RANK = 0

        var daoCounter = 0
            private set

        var dioCounter = 0
            private set

        var forwardedDaoCounter = 0
            private set

        var forwardedDioCounter = 0
            private set

        fun resetCounter() {
            daoCounter = 0
            dioCounter = 0
            forwardedDaoCounter = 0
            forwardedDioCounter = 0
        }
    }
}