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
import lupos.simulator_core.ITimer
import lupos.simulator_db.IApplicationStack_Actuator
import lupos.simulator_db.IApplicationStack_BothDirections
import lupos.simulator_db.IApplicationStack_Middleware
import lupos.simulator_db.IPayload
import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.applications.ApplicationStack_CatchSelfMessages
import lupos.simulator_iot.applications.ApplicationStack_MergeMessages
import lupos.simulator_iot.applications.ApplicationStack_MultipleChilds
import lupos.simulator_iot.applications.ApplicationStack_Sequence
import lupos.simulator_iot.models.geo.GeoLocation
import lupos.simulator_iot.models.net.LinkManager
import lupos.simulator_iot.models.net.NetworkPackage
import lupos.simulator_iot.models.routing.IRoutingProtocol
import lupos.simulator_iot.models.routing.RPL
import lupos.simulator_iot.utils.TimeUtils
public class Device(
    internal val simRun: SimulationRun,
    internal var location: GeoLocation,
    internal val address: Int,
    internal val performance: Double,
    supportedLinkTypes: IntArray,
    internal val deviceNameID: Int,
    internal val isDeterministic: Boolean,
    applications: Array<IApplicationStack_Actuator>,
    internal val hostNameLookUpTable: MutableMap<String, Int>,
) : Entity(), IApplicationStack_Middleware {
    public val allApplications: ApplicationStack_MultipleChilds = ApplicationStack_MultipleChilds(applications)
    public val userApplication: IApplicationStack_BothDirections =
        ApplicationStack_Sequence(
            address,
            ApplicationStack_MergeMessages(
                ApplicationStack_CatchSelfMessages(
                    address,
                    allApplications,
                )
            )
        )
    internal val router: IRoutingProtocol = RPL(this)
    internal val linkManager: LinkManager = LinkManager(supportedLinkTypes)
    internal var isStarNetworkChild: Boolean = false
    private lateinit var deviceStart: Instant

    init {
        userApplication.setRouter(this)
    }

    override fun getAllChildApplications(): Set<IApplicationStack_Actuator> {
        var res = mutableSetOf<IApplicationStack_Actuator>(userApplication)
        res.addAll(userApplication.getAllChildApplications())
        return res
    }

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
        userApplication.startUp()
        router.startRouting()
    }

    override fun onSteadyState() {
    }
    override fun onEvent(source: Entity, data: Any) {
        val pck = data as NetworkPackage
        simRun.logger.onReceiveNetworkPackage(address, pck.payload)
        deviceStart = TimeUtils.stamp()
        if (pck.destinationAddress == address) {
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
            else -> {
                userApplication!!.receive(pck.payload)
            }
        }
    }

    override fun onShutDown() {
        for (dest in 0 until simRun.config.devices.size) {
            try {
                val hop = router.getNextHop(dest)
                if (hop != -1) {
                    simRun.logger.addConnectionTable(address, dest, hop)
                }
                val dbhop = router.getNextDatabaseHops(intArrayOf(dest))[0]
                if (dbhop != -1) {
                    simRun.logger.addConnectionTableDB(address, dest, dbhop)
                }
            } catch (e: Throwable) {
            }
        }
        userApplication.shutDown()
    }

    private fun forwardPackage(pck: NetworkPackage) {
        val hop = router.getNextHop(pck.destinationAddress)
        val delay = getNetworkDelay(hop, pck)
        assignToSimulation(pck.destinationAddress, hop, pck, delay)
    }

    internal fun sendUnRoutedPackage(hop: Int, data: IPayload) {
        val pck = NetworkPackage(address, hop, data)
        val delay = getNetworkDelay(hop, pck)
        assignToSimulation(hop, hop, pck, delay)
    }

    internal fun sendRoutedPackage(src: Int, dest: Int, data: IPayload) {
        val pck = NetworkPackage(src, dest, data)
        val hop = router.getNextHop(pck.destinationAddress)
        val delay = getNetworkDelay(hop, pck)
        assignToSimulation(dest, hop, pck, delay)
    }

    private fun assignToSimulation(dest: Int, hop: Int, pck: NetworkPackage, delay: Long) {
        val entity = simRun.config.getDeviceByAddress(hop)
        scheduleEvent(entity, pck, delay)
        simRun.logger.onSendNetworkPackage(address, dest, hop, pck.payload, delay)
    }

    override fun equals(other: Any?): Boolean = other is Device && address == other.address
    override fun hashCode(): Int = address

    override fun toString(): String {
        return "Device(addr $address, name '${simRun.config.getDeviceName(deviceNameID)}')"
    }
    override fun send(destinationAddress: Int, pck: IPayload): Unit = sendRoutedPackage(address, destinationAddress, pck)
    override fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray = router.getNextDatabaseHops(destinationAddresses)
    override fun registerTimer(durationInNanoSeconds: Long, entity: ITimer): Unit = setTimer(durationInNanoSeconds, entity)
    override fun flush() {}
    override fun resolveHostName(name: String): Int = hostNameLookUpTable[name]!!
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
