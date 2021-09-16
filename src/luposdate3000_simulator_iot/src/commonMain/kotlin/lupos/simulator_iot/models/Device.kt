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
import lupos.simulator_db.IApplicationStack_Rooter
import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.applications.ApplicationStack_CatchSelfMessages
import lupos.simulator_iot.applications.ApplicationStack_MergeMessages
import lupos.simulator_iot.applications.ApplicationStack_MultipleChilds
import lupos.simulator_iot.applications.ApplicationStack_RPL
import lupos.simulator_iot.applications.ApplicationStack_Sequence
import lupos.simulator_iot.models.geo.GeoLocation
import lupos.simulator_iot.models.net.LinkManager
import lupos.simulator_iot.models.net.NetworkPackage
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
) : Entity() {
    internal var isStarNetworkChild: Boolean = false
    private lateinit var deviceStart: Instant
    internal val linkManager: LinkManager = LinkManager(supportedLinkTypes)
    public val allApplications: ApplicationStack_MultipleChilds = ApplicationStack_MultipleChilds(applications)
    public val applicationStack: IApplicationStack_Rooter =
        ApplicationStack_RPL(
            this,
            ApplicationStack_Sequence(
                address,
                ApplicationStack_MergeMessages(
                    ApplicationStack_CatchSelfMessages(
                        address,
                        allApplications,
                    )
                )
            ),
            linkManager,
            simRun.logger,
            simRun.config,
        )

    internal fun getProcessingDelay(): Long {
        if (isDeterministic) {
            return 1
        }
        val now = TimeUtils.stamp()
        val microDif = TimeUtils.differenceInNanoSec(deviceStart, now)
        val scaled = microDif * 100 / performance
        return scaled.toLong()
    }
    internal fun getNetworkDelay(destinationAddress: Int, pck: NetworkPackage): Long {
        val processingDelay = getProcessingDelay()
        return if (destinationAddress == address) {
            processingDelay
        } else {
            val transmissionDelay = linkManager.getTransmissionDelay(destinationAddress, pck.getSizeInBytes())
            transmissionDelay + processingDelay
        }
    }
    override fun onStartUp() {
        deviceStart = TimeUtils.stamp()
        applicationStack.startUp()
    }

    override fun onSteadyState() {
    }
    override fun onEvent(source: Entity, data: Any) {
        deviceStart = TimeUtils.stamp()
        val pck = data as NetworkPackage
        simRun.logger.onReceiveNetworkPackage(address, pck.payload)
        applicationStack.receive(pck)
    }

    override fun onShutDown() {
        applicationStack.shutDown()
    }

    internal fun assignToSimulation(dest: Int, hop: Int, pck: NetworkPackage, delay: Long) {
        val entity = simRun.config.getDeviceByAddress(hop)
        scheduleEvent(entity, pck, delay)
        simRun.logger.onSendNetworkPackage(address, dest, hop, pck.payload, delay)
    }

    override fun equals(other: Any?): Boolean = other is Device && address == other.address
    override fun hashCode(): Int = address

    override fun toString(): String = "Device(addr $address, name '${simRun.config.getDeviceName(deviceNameID)}')"
    internal fun registerTimer(durationInNanoSeconds: Long, entity: ITimer): Unit = setTimer(durationInNanoSeconds, entity)
    internal fun resolveHostName(name: String): Int = hostNameLookUpTable[name]!!
    public fun getAllChildApplications(): Set<IApplicationStack_Actuator> = applicationStack.getAllChildApplications()
}
