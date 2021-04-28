
class Device(
    val powerSupply: PowerSupply,
    var location: GeoLocation,
    val address: Int,
    val database: Database?,
    var sensor: Sensor?,
    val supportedLinkTypes: IntArray
    ) : Entity()
{
    val router: Router = RPLRouter(this)
    val linkManager = LinkManager(this)

    private fun getNetworkDelay(destinationAddress: Int): Long {
        return if (destinationAddress == address) {
            0
        } else {
            1
        }
    }

    override fun startUpEntity() {
        sensor?.observe()
        router.startRouting()
    }

    override fun processEvent(event: Event) {
        val pck = event.data as NetworkPackage
        when {
            pck.data is NetworkPackage.ObservationEnd -> sensor!!.onObservationEnd()
            pck.data is NetworkPackage.ParkingObservation -> processParkingObservation(pck)
            router.isControlPackage(pck) -> router.processControlPackage(pck)
        }

    }

    override fun shutDownEntity() {
        Simulation.log()
    }


    fun sendUnRoutedPackage(destinationNeighbour: Int, data: Any) {
        val pck = NetworkPackage(address, destinationNeighbour, data)
        val delay = getNetworkDelay(destinationNeighbour)
        sendEvent(Configuration.devices[destinationNeighbour], delay, pck)
    }

    fun sendRoutedPackage(src: Int, dest: Int, data: Any) {
        val pck = NetworkPackage(src, dest, data)
        val nextHop = router.getNextHop(pck.destinationAddress)
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
            sendRoutedPackage(pck.sourceAddress, pck.destinationAddress, pck.data)
        }
    }


    interface Sensor {
        var dataSinkAddress: Int
        fun observe()
        fun onObservationEnd()
    }

    interface Router {
        fun startRouting()
        fun isControlPackage(pck: NetworkPackage): Boolean
        fun processControlPackage(pck: NetworkPackage)
        fun getNextHop(destinationAddress: Int): Int
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