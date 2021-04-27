
class Device(
    val powerSupply: PowerSupply,
    var location: GeoLocation,
    val address: Int,
    val database: Database?,
    var sensor: Sensor?,
    val supportedLinkTypes: IntArray
    ) : Entity()
{
    private val notInitialized = Int.MAX_VALUE
    var root = false

    var rank = notInitialized
        private set

    var routingTable = RoutingTable(address)
        private set

    var preferredParent = Parent()
        private set

    val linkManager = LinkManager(this)

    inner class Parent(var address: Int = notInitialized, var rank: Int = notInitialized)

    private fun getNetworkDelay(destinationAddress: Int): Long {
        return if (destinationAddress == address) {
            0
        } else {
            1
        }
    }

    override fun startUpEntity() {

        sensor?.observe()
        if(root) {
            rank = 0
            broadcastDIO()
        }
    }

    override fun processEvent(event: Event) {
        val pck = event.data as NetworkPackage
        when(pck.data) {
            is NetworkPackage.ObservationEnd -> sensor!!.onObservationEnd()
            is NetworkPackage.ParkingObservation -> processParkingObservation(pck)
            is NetworkPackage.DIO -> processDIO(pck)
            is NetworkPackage.DAO -> processDAO(pck)
        }
    }

    override fun shutDownEntity() {
    }

    private fun broadcastDIO() {
        for (potentialChild in linkManager.getNeighbours())
            if(potentialChild != preferredParent.address)
                sendDIO(potentialChild)
    }

    private fun sendDIO(destinationAddress: Int) {
        val dio = NetworkPackage.DIO(rank)
        sendDODAGBuildingPackage(destinationAddress, dio)
    }

    private fun sendDAO(destinationAddress: Int, isPath: Boolean) {
        val destinations = if(isPath) routingTable.getDestinations() else IntArray(0)
        val dao = NetworkPackage.DAO(isPath, destinations)
        sendDODAGBuildingPackage(destinationAddress, dao)
    }

    private fun sendDODAGBuildingPackage(dest: Int, data: Any) {
        val pck = NetworkPackage(address, dest, data)
        val delay = getNetworkDelay(dest)
        sendEvent(Configuration.devices[dest], delay, pck)
    }

    fun sendNetworkPackage(src: Int, dest: Int, data: Any) {
        val pck = NetworkPackage(src, dest, data)
        val nextHop = routingTable.getNextHop(pck.destinationAddress)
        val delay = getNetworkDelay(nextHop)
        sendEvent(Configuration.devices[nextHop], delay, pck)
    }

    fun sendSelfPackage(delay: Long, data: Any) {
        val pck = NetworkPackage(address, address, data)
        sendEvent(this, delay, pck)
    }


    private fun processParkingObservation(pck: NetworkPackage) {
        if (pck.destinationAddress == address) {
            //store
        }
        else {
            sendNetworkPackage(pck.sourceAddress, pck.destinationAddress, pck.data)
        }
    }

    private fun processDIO(pck: NetworkPackage) {
        val dio = pck.data as NetworkPackage.DIO
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
        val dao = pck.data as NetworkPackage.DAO
        val hasRoutingTableChanged: Boolean = if (dao.isPath)
            routingTable.setDestinationsByHop(pck.sourceAddress, dao.destinations)
        else
            routingTable.removeDestinationsByHop(pck.sourceAddress)

        if(hasParent() && hasRoutingTableChanged)
            sendDAO(preferredParent.address, dao.isPath)
    }

    private fun objectiveFunction(dio: NetworkPackage.DIO)
        = dio.rank + 1



    fun hasParent()
        = preferredParent.address != notInitialized




    interface Sensor {
        var dataSinkAddress: Int
        fun observe()
        fun onObservationEnd()
    }

    override fun equals(other: Any?): Boolean {
        if (other === this)
            return true

        if (other !is Device)
            return false

        return address == other.address
    }

    override fun hashCode(): Int {
        return address
    }

}