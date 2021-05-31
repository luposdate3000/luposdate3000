class Device(
    val powerSupply: PowerSupply,
    var location: GeoLocation,
    val address: Int,
    var database: DatabaseAdapter?,
    var sensor: IDeviceSensor?,
    val supportedLinkTypes: IntArray
    ) : Entity()
{
    val router: IRoutingAlgorithm = RPLRouter(this)
    val linkManager = LinkManager(this)

    var isStarNetworkChild = false

    private fun getNetworkDelay(destinationAddress: Int): Long {
        return if (destinationAddress == address) {
            0
        } else {
            1
        }
    }

    override fun onStartUp() {
        sensor?.startSampling()
        database?.startUp()
        router.startRouting()
    }

    override fun onEvent(event: Event) {
        when {

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
        scheduleEvent(Configuration.devices[destinationNeighbour], delay, pck)
    }

    fun sendRoutedPackage(src: Int, dest: Int, data: Any) {
        val pck = NetworkPackage(src, dest, data)
        val nextHop = router.getNextHop(pck.destinationAddress)
        val delay = getNetworkDelay(nextHop)
        scheduleEvent(Configuration.devices[nextHop], delay, pck)
    }

    fun waitForObservationEnd(delay: Long) {
        sendSelfEvent(delay, SensorObservationEndMarker())
    }

    fun sendSelfEvent(delay: Long, marker: Any) {
        scheduleEvent(this, delay, marker)
    }


    fun sendSensorSample(destinationAddress: Int, data: Any) {
        sendRoutedPackage(address, destinationAddress, data)
    }


    fun hasDatabase() = database != null





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