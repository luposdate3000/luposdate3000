package lupos.simulator_iot

import kotlinx.datetime.Instant
import lupos.simulator_core.Entity
import lupos.simulator_iot.db.DatabaseAdapter
import lupos.simulator_iot.geo.GeoLocation
import lupos.simulator_iot.net.IPayload
import lupos.simulator_iot.net.LinkManager
import lupos.simulator_iot.net.NetworkPackage
import lupos.simulator_iot.net.routing.IRoutingProtocol
import lupos.simulator_iot.net.routing.RPL
import lupos.simulator_iot.sensor.ISensor
import lupos.simulator_iot.sensor.ParkingSample

public class Device(
    internal val simRun: SimulationRun,
    internal var location: GeoLocation,
    internal val address: Int,
    public var database: DatabaseAdapter?,
    internal var sensor: ISensor?,
    internal val performance: Double,
    supportedLinkTypes: IntArray,
    internal val deviceNameID: Int,
    internal val isDeterministic: Boolean,
) : Entity() {
    internal val router: IRoutingProtocol = RPL(this)
    internal val linkManager: LinkManager = LinkManager(this, supportedLinkTypes)
    internal var isStarNetworkChild: Boolean = false

    internal var processedSensorDataPackages: Long = 0
        private set

    private lateinit var deviceStart: Instant

    private fun getNetworkDelay(destinationAddress: Int, pck: NetworkPackage): Long {
        return if (destinationAddress == address) {
            getProcessingDelay()
        } else {
            val processingDelay = getProcessingDelay()
            val transmissionDelay = linkManager.getTransmissionDelay(destinationAddress, pck.pckSize)
            transmissionDelay + processingDelay
        }
    }

    private fun getProcessingDelay(): Long {
        if (isDeterministic) {
            return 0
        }

        val now = Time.stamp()
        val microDif = Time.differenceInMicroSec(deviceStart, now)
        val scaled = microDif * 100 / performance
        val millis = scaled / 1000
        return millis.toLong()
    }

    override fun onStartUp() {
        deviceStart = Time.stamp()
        sensor?.startSampling()
        database?.startUp()
        router.startRouting()
    }

    override fun onSteadyState() {
    }

    override fun onEvent(source: Entity, data: Any) {
        val pck = data as NetworkPackage
        deviceStart = Time.stamp()
        packageCounter++
        networkLoad += pck.pckSize
        if (pck.destinationAddress == address) {
            logReceivePackage(pck)
            processPackage(pck)
        } else {
            forwardPackage(pck)
        }
    }

    private fun processPackage(pck: NetworkPackage) {
        when {
            router.isControlPackage(pck) -> {
                router.processControlPackage(pck)
            }
            pck.payload is ParkingSample -> {
                processedSensorDataPackages++
                requireNotNull(
                    database
                ) { "The device $address has no database configured to store the ParkingSample." }
                database!!.saveParkingSample(pck.payload)
            }
            database?.isDatabasePackage(pck.payload) == true -> {
                database?.processPackage(pck.payload)
            }
            else -> throw Exception("Undefined NetworkPackage or wrong device address.")
        }
    }

    override fun onShutDown() {
        for (dest in 0 until simRun.config.devices.size) {
            try {
                val hop = router.getNextHop(dest)
                simRun.sim.visualisationNetwork.addConnectionTable(address, dest, hop)
            } catch (e: Throwable) {
            }
        }
        sensor?.stopSampling()
        database?.shutDown()
    }

    private fun forwardPackage(pck: NetworkPackage) {
        val nextHop = router.getNextHop(pck.destinationAddress)
        val delay = getNetworkDelay(nextHop, pck)
        scheduleEvent(simRun.config.devices[nextHop], pck, delay)
    }

    internal fun sendUnRoutedPackage(destinationNeighbour: Int, data: IPayload) {
        val pck = NetworkPackage(address, destinationNeighbour, data)
        val delay = getNetworkDelay(destinationNeighbour, pck)
        logSendPackage(pck, delay)
        scheduleEvent(simRun.getDeviceByAddress(destinationNeighbour), pck, delay)
    }

    internal fun sendRoutedPackage(src: Int, dest: Int, data: IPayload) {
        val pck = NetworkPackage(src, dest, data)
        val nextHop = router.getNextHop(pck.destinationAddress)
        val delay = getNetworkDelay(nextHop, pck)
        logSendPackage(pck, delay)
        scheduleEvent(simRun.getDeviceByAddress(nextHop), pck, delay)
    }

    internal fun sendSensorSample(destinationAddress: Int, data: IPayload) {
        sendRoutedPackage(address, destinationAddress, data)
    }

    public fun hasDatabase(): Boolean = database != null

    private fun logReceivePackage(pck: NetworkPackage) {
        simRun.logger.log("> $this receives $pck at clock ${simRun.getCurrentSimulationClock()}")
    }

    private fun logSendPackage(pck: NetworkPackage, delay: Long) {
        simRun.logger.log("> $this sends $pck at clock ${simRun.getCurrentSimulationClock()} with delay $delay")
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }

        if (other !is Device) {
            return false
        }

        return address == other.address
    }

    override fun hashCode(): Int {
        return address
    }

    override fun toString(): String {
        return "Device(addr $address, name '${simRun.config.getDeviceName(deviceNameID)}')"
    }

    internal companion object {
        internal var packageCounter: Int = 0
            private set

        internal var networkLoad: Long = 0
            private set

        internal fun getNetworkLoadKBytes() =
            networkLoad / 1000

        internal var observationPackageCounter: Int = 0
            private set

        internal fun resetCounter() {
            packageCounter = 0
            observationPackageCounter = 0
        }
    }
}
