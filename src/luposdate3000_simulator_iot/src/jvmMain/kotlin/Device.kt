package lupos.simulator_iot

import lupos.simulator_core.Entity
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.geo.GeoLocation
import lupos.simulator_iot.routing.IRoutingAlgorithm
import lupos.simulator_iot.routing.RPLRouter
import lupos.simulator_iot.sensor.ISensor
import lupos.simulator_iot.sensor.ParkingSample

public class Device(
    public val powerSupply: PowerSupply,
    internal var location: GeoLocation,
    public val address: Int,
    public var database: DatabaseAdapter?,
    public var sensor: ISensor?,
    public val supportedLinkTypes: IntArray
) : Entity() {
    public val router: IRoutingAlgorithm = RPLRouter(this)
    public val linkManager: LinkManager = LinkManager(this)
    public var isStarNetworkChild: Boolean = false

    public var processedSensorDataPackages: Long = 0
        private set

    private fun getNetworkDelay(destinationAddress: Int): Int {
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

    override fun onSteadyState() {
    }

    override fun onEvent(source: Entity, data: Any) {
        val pck = data as NetworkPackage
        packageCounter++
        if (pck.destinationAddress == address)
            processPackage(pck)
        else
            forwardPackage(pck)
    }

    private fun processPackage(pck: NetworkPackage) {
        if (router.isControlPackage(pck)) {
            router.processControlPackage(pck)
        } else if (pck.payload is ParkingSample) {
            processedSensorDataPackages++
            database?.saveParkingSample(pck.payload)
        } else if (pck.payload is IDatabasePackage) {
            database?.receive(pck.payload)
        }
    }

    override fun onShutDown() {
        sensor?.stopSampling()
        database?.shutDown()
    }

    private fun forwardPackage(pck: NetworkPackage) {
        val nextHop = router.getNextHop(pck.destinationAddress)
        val delay = getNetworkDelay(nextHop)
        scheduleEvent(Configuration.devices[nextHop], pck, delay)
    }

    public fun sendUnRoutedPackage(destinationNeighbour: Int, data: Any) {
        val pck = NetworkPackage(address, destinationNeighbour, data)
        val delay = getNetworkDelay(destinationNeighbour)
        scheduleEvent(Configuration.devices[destinationNeighbour], pck, delay)
    }

    public fun sendRoutedPackage(src: Int, dest: Int, data: Any) {
        val pck = NetworkPackage(src, dest, data)
        forwardPackage(pck)
    }

    public fun sendSensorSample(destinationAddress: Int, data: Any) {
        sendRoutedPackage(address, destinationAddress, data)
    }

    public fun hasDatabase(): Boolean = database != null

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

    public companion object {
        public var packageCounter: Int = 0
            private set

        public var observationPackageCounter: Int = 0
            private set

        public fun resetCounter() {
            packageCounter = 0
            observationPackageCounter = 0
        }
    }
}
