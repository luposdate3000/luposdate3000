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
import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.applications.IApplicationStack_Actuator
import lupos.simulator_iot.applications.IApplicationStack_Rooter
import lupos.simulator_iot.models.geo.GeoLocation
import lupos.simulator_iot.models.net.LinkManager
import lupos.simulator_iot.models.net.NetworkPackage
import lupos.simulator_iot.utils.TimeUtils

public class Device(
    internal val simRun: SimulationRun,
    internal var location: GeoLocation,
    public val address: Int,
    internal val performance: Double,
    public val linkManager: LinkManager,
    internal val isDeterministic: Boolean,
    public val applicationStack: IApplicationStack_Rooter,
    internal val hostNameLookUpTable: MutableMap<String, Int>,
) : Entity() {
    internal var isStarNetworkChild: Boolean = false
    private lateinit var deviceStart: Instant

    init {
        applicationStack.setDevice(this)
    }

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

    public fun closestDeviceWithFeature(name: String): Int {
        val devicesWithFeature = simRun.config.getAllDevicesForFeature(simRun.config.featureIdForName2(name)).toMutableList()
        if (devicesWithFeature.size == 0) {
            return -1
        }
        var closestDevice: Device? = null
        var closestDistance = 0.0
        for (d in devicesWithFeature) {
            if (closestDevice == null) {
                closestDevice = d
                closestDistance = d.location.getDistanceInMeters(location)
            } else {
                val dist = d.location.getDistanceInMeters(location)
                if (dist < closestDistance) {
                    closestDevice = d
                    closestDistance = dist
                }
            }
        }
        return closestDevice!!.address
    }

    internal fun assignToSimulation(dest: Int, hop: Int, pck: NetworkPackage, delay: Long) {
        val entity = simRun.config.getDeviceByAddress(hop)
        scheduleEvent(entity, pck, delay)
        simRun.logger.onSendNetworkPackage(address, dest, hop, pck.payload, delay)
    }

    override fun equals(other: Any?): Boolean = other is Device && address == other.address
    override fun hashCode(): Int = address

    override fun toString(): String = "Device(addr $address)"
    internal fun registerTimer(durationInNanoSeconds: Long, entity: ITimer): Unit = setTimer(durationInNanoSeconds, entity)
    internal fun resolveHostName(name: String): Int = hostNameLookUpTable[name]!!
    public fun getAllChildApplications(): Set<IApplicationStack_Actuator> = applicationStack.getAllChildApplications()
}
