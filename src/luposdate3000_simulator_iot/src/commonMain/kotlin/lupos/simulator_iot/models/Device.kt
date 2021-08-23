/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package lupos.simulator_iot.models
import kotlinx.datetime.Instant
import lupos.simulator_core.Entity
import lupos.simulator_db.IPayload
import lupos.simulator_db.IUserApplicationLayer
import lupos.simulator_db.QueryPackage
import lupos.simulator_db.luposdate3000.PostProcessSend
import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.models.geo.GeoLocation
import lupos.simulator_iot.models.net.LinkManager
import lupos.simulator_iot.models.net.NetworkPackage
import lupos.simulator_iot.models.routing.IRoutingProtocol
import lupos.simulator_iot.models.routing.RPL
import lupos.simulator_iot.models.sensor.ISensor
import lupos.simulator_iot.models.sensor.ParkingSample
import lupos.simulator_iot.queryproc.SemanticData
import lupos.simulator_iot.utils.TimeUtils

public class Device(
    internal val simRun: SimulationRun,
    internal var location: GeoLocation,
    internal val address: Int,
    internal var sensor: ISensor?,
    internal val performance: Double,
    supportedLinkTypes: IntArray,
    internal val deviceNameID: Int,
    internal val isDeterministic: Boolean,
) : Entity() {
    internal var hasDatabaseStore: Boolean = false
    internal var hasDatabaseQuery: Boolean = false
    public var userApplication: IUserApplicationLayer? = null
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
            return 1
        }
        val now = TimeUtils.stamp()
        val microDif = TimeUtils.differenceInNanoSec(deviceStart, now)
        val scaled = microDif * 100 / performance
        return scaled.toLong()
    }

    override fun onStartUp() {
        deviceStart = TimeUtils.stamp()
        sensor?.startSampling()
        userApplication?.startUp()
        router.startRouting()
    }

    override fun onSteadyState() {
    }

    override fun onEvent(source: Entity, data: Any) {
        val pck = data as NetworkPackage
        deviceStart = TimeUtils.stamp()
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
                val sample = pck.payload as ParkingSample
                val query = SemanticData.getInsertQueryString(sample)
                val bytes = query.encodeToByteArray()
                val pck = QueryPackage(address, bytes)
                PostProcessSend.process(address, address, simRun.sim.clock, simRun.visualisationNetwork, pck)
                userApplication!!.receive(pck)
            }
            else -> {
                userApplication!!.receive(pck.payload)
            }
        }
    }

    override fun onShutDown() {
        for (dest in 0 until simRun.config.devices.size) {
            try {
                val hop = router.getNextHop(dest)
                simRun.visualisationNetwork.addConnectionTable(address, dest, hop)
            } catch (e: Throwable) {
            }
        }
        sensor?.stopSampling()
        userApplication?.shutDown()
    }

    private fun forwardPackage(pck: NetworkPackage) {
        val nextHop = router.getNextHop(pck.destinationAddress)
        val delay = getNetworkDelay(nextHop, pck)
        measureForwarding(pck)
        assignToSimulation(nextHop, pck, delay)
    }

    internal fun sendUnRoutedPackage(destinationNeighbour: Int, data: IPayload) {
        val pck = NetworkPackage(address, destinationNeighbour, data)
        val delay = getNetworkDelay(destinationNeighbour, pck)
        logSendPackage(pck, delay)
        measureSending(pck)
        assignToSimulation(destinationNeighbour, pck, delay)
    }

    internal fun sendRoutedPackage(src: Int, dest: Int, data: IPayload) {
        val pck = NetworkPackage(src, dest, data)
        val nextHop = router.getNextHop(pck.destinationAddress)
        val delay = getNetworkDelay(nextHop, pck)
        logSendPackage(pck, delay)
        measureSending(pck)
        assignToSimulation(nextHop, pck, delay)
    }

    private fun assignToSimulation(destinationAddress: Int, pck: NetworkPackage, delay: Long) {
        val entity = simRun.config.getDeviceByAddress(destinationAddress)
        scheduleEvent(entity, pck, delay)
    }

    private fun measureSending(pck: NetworkPackage) {
        // ignore rpl for the evaluation
        if (router.isControlPackage(pck)) {
            return
        }

        if (pck.destinationAddress != address) {
            // ignore self packages
            simRun.incNumberOfSentPackages()
            simRun.incNetworkTraffic(pck.pckSize)
        }
    }

    private fun measureForwarding(pck: NetworkPackage) {
        // ignore rpl for the evaluation
        if (router.isControlPackage(pck)) {
            return
        }
        simRun.incNumberOfForwardedPackages()
        simRun.incNetworkTraffic(pck.pckSize)
    }

    internal fun sendSensorSample(destinationAddress: Int, data: IPayload) {
        if (destinationAddress != address) {
            // ignore self packages
            simRun.incNumberOfSentSamplePackages()
        }
        sendRoutedPackage(address, destinationAddress, data)
    }

    private fun logReceivePackage(pck: NetworkPackage) {
        simRun.logger?.log("> $this receives $pck at clock ${simRun.getCurrentSimulationClock()}")
    }

    private fun logSendPackage(pck: NetworkPackage, delay: Long) {
        simRun.logger?.log("> $this sends $pck at clock ${simRun.getCurrentSimulationClock()} with delay $delay")
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
