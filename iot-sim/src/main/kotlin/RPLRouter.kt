import java.lang.StringBuilder

class RPLRouter(val device: Device): Device.Router {

    val routingTable = RoutingTable(device.address)
    private val notInitialized = Int.MAX_VALUE
    var root = false

    var rank = notInitialized
        private set

    var preferredParent = Parent()
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
    }

    private fun sendDAO(destinationAddress: Int, isPath: Boolean) {
        val destinations = if(isPath) routingTable.getDestinations() else IntArray(0)
        val dao = DAO(isPath, destinations)
        device.sendUnRoutedPackage(destinationAddress, dao)
    }


    private fun processDIO(pck: NetworkPackage) {
        val dio = pck.data as DIO
        dioCounter++
        if (objectiveFunction(dio) >= rank)
            return

        rank = objectiveFunction(dio)
        updateParent(Parent(pck.sourceAddress, dio.rank))
        broadcastDIO()
    }

    private fun updateParent(newParent: Parent) {

        if (hasParent())
            sendDAO(preferredParent.address, false)

        preferredParent = newParent
        sendDAO(preferredParent.address, true)
        routingTable.defaultAddress = preferredParent.address
    }


    private fun processDAO(pck: NetworkPackage) {
        val dao = pck.data as DAO
        daoCounter++
        val hasRoutingTableChanged: Boolean = if (dao.isPath)
            routingTable.setDestinationsByHop(pck.sourceAddress, dao.destinations)
        else
            routingTable.removeDestinationsByHop(pck.sourceAddress)

        if(hasParent() && hasRoutingTableChanged)
            sendDAO(preferredParent.address, dao.isPath)
    }

    private fun objectiveFunction(dio: DIO)
            = dio.rank + 1



    fun hasParent()
            = preferredParent.address != notInitialized


    override fun startRouting() {
        if(root) {
            rank = 0
            broadcastDIO()
        }
    }

    class DIO(val rank: Int)
    class DAO(val isPath: Boolean, val destinations: IntArray)

    override fun isControlPackage(pck: NetworkPackage)
        = pck.data is DAO || pck.data is DIO


    override fun processControlPackage(pck: NetworkPackage) {
        when(pck.data) {
            is DIO -> processDIO(pck)
            is DAO -> processDAO(pck)
        }
    }

    override fun getNextHop(destinationAddress: Int)
        = routingTable.getNextHop(destinationAddress)

    override fun toString(): String {
        val strBuilder = StringBuilder()
        strBuilder
            .append("Device ${device.address}").append(", ")
            .append("rank $rank").append(", ")
            .append(getParentString()).append(", ")
            .append("children ")
            .append("{${getChildrenString()}}")

        return strBuilder.toString()
    }

    private fun getParentString()
        = if (hasParent()) "parent ${preferredParent.address}" else "root"


    private fun getChildrenString(): StringBuilder {
        val strBuilder = StringBuilder()
        val separator = ", "
        for (neighbour in device.linkManager.getNeighbours())
            if(neighbour != preferredParent.address) {
                val link = device.linkManager.getLink(neighbour)!!
                strBuilder.append("$link to $neighbour").append(separator)
            }
        if(strBuilder.length >= separator.length)
            strBuilder.deleteRange(strBuilder.length - separator.length, strBuilder.length)
        return strBuilder
    }

    companion object {
        var daoCounter = 0
            private set

        var dioCounter = 0
            private set

        fun resetCounter() {
            daoCounter = 0
            dioCounter = 0
        }
    }
}