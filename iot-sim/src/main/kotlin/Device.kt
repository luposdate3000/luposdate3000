
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
    class SensorObservationEndMarker
    var isStarNetworkChild = false

    private fun getNetworkDelay(destinationAddress: Int): Long {
        return if (destinationAddress == address) {
            0
        } else {
            1
        }
    }

    override fun onStartUp() {
        sensor?.observe()
        router.startRouting()
    }

    override fun onEvent(event: Event) {
        if(event.data is SensorObservationEndMarker) {
            sensor!!.onObservationEnd()
            return
        }

        if(event.data != null && router.isSelfEvent(event.data!!)) {
            router.processSelfEvent(event.data!!)
            return
        }

        val pck = event.data as NetworkPackage
        packageCounter++
        when {
            pck.payload is ParkingSensor.ParkingObservation -> processParkingObservation(pck)
            router.isControlPackage(pck) -> router.processControlPackage(pck)
        }

    }

    override fun onShutDown() {

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

    fun waitForObservationEnd(delay: Long) {
        sendSelfEvent(delay, SensorObservationEndMarker())
    }

    fun sendSelfEvent(delay: Long, marker: Any) {
        sendEvent(this, delay, marker)
    }


    private fun processParkingObservation(pck: NetworkPackage) {
        observationPackageCounter++
        if (pck.destinationAddress == address) {
            //store
        }
        else {
            sendRoutedPackage(pck.sourceAddress, pck.destinationAddress, pck.payload)
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
        fun isSelfEvent(marker: Any): Boolean
        fun processSelfEvent(marker: Any)
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

    companion object {
        var packageCounter = 0
            private set

        var observationPackageCounter = 0
            private set

        fun resetCounter() {
            packageCounter = 0
            observationPackageCounter = 0
        }
    }

}