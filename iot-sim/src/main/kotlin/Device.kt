class Device(
    val powerSupply: PowerSupply,
    var location: GeoLocation,
    val address: Int,
    var database: DatabaseAdapter?,
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
        sensor?.startTakingSample()
        database?.startUp()
        router.startRouting()
    }

    override fun onEvent(event: Event) {
        when {
            event.data is SensorObservationEndMarker -> sensor!!.onSampleTaken()
            event.data != null && router.isSelfEvent(event.data!!) -> router.processSelfEvent(event.data!!)
            event.data is NetworkPackage -> processNetworkPackage(event.data as NetworkPackage)
        }
    }

    private fun processNetworkPackage(pck: NetworkPackage) {
        packageCounter++
        when {
            //hasDatabase() && database!!.isDatabasePackage(pck.payload) -> processDBPackage(pck)
            router.isControlPackage(pck) -> router.processControlPackage(pck)
        }
    }

    override fun onShutDown() {
        database?.shutDown()
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


    fun sendSensorSample(destinationAddress: Int, data: Any) {
        sendRoutedPackage(address, destinationAddress, data)
    }


    fun hasDatabase() = database != null


    interface Sensor {
        var dataSinkAddress: Int
        fun startTakingSample()
        fun onSampleTaken()
    }

    interface Router {
        fun startRouting()
        fun isControlPackage(pck: NetworkPackage): Boolean
        fun isSelfEvent(marker: Any): Boolean
        fun processSelfEvent(marker: Any)
        fun processControlPackage(pck: NetworkPackage)
        fun getNextHop(destinationAddress: Int): Int
        fun getNextDatabaseHop(destinationAddress: Int): Int
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