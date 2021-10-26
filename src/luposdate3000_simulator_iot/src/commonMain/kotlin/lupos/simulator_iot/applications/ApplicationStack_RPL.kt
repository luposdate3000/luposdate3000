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

package lupos.simulator_iot.applications

import lupos.simulator_core.ITimer
import lupos.simulator_iot.ILogger
import lupos.simulator_iot.IPayload
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.models.Device
import lupos.simulator_iot.models.net.NetworkPackage
import lupos.simulator_iot.utils.TimeUtils

public class ApplicationStack_RPL(
    private val child: IApplicationStack_Actuator,
    private val logger: ILogger,
    private val config: Configuration,
) : IApplicationStack_Rooter {
    init {
        child.setRouter(this)
    }

    private lateinit var parent: Device
    public lateinit var routingTable: ApplicationStack_RPL_RoutingTable
    private val notInitializedAddress = -1
    internal var isRoot: Boolean = false
    internal var rank: Int = INFINITE_RANK
    internal var preferredParent: Parent = Parent()
    private var isDelayPackage_ApplicationStack_RPL_DAOTimerRunning = false

    internal inner class Parent(internal var address: Int = notInitializedAddress, internal var rank: Int = INFINITE_RANK)

    override fun setDevice(device: Device) {
        parent = device
    }

    override fun setRoot() {
        isRoot = true
    }

    internal fun sendUnRoutedPackage(hop: Int, data: IPayload) {
        val pck = NetworkPackage(parent.address, hop, data)
        val delay = parent.getNetworkDelay(hop, pck)
        parent.assignToSimulation(hop, hop, pck, delay)
    }

    private fun broadcastPackage_ApplicationStack_RPL_DIO() {
        for (potentialChild in parent.linkManager.getNeighbours())
            if (potentialChild != preferredParent.address) {
                sendPackage_ApplicationStack_RPL_DIO(potentialChild)
            }
    }

    private fun sendPackage_ApplicationStack_RPL_DIO(destinationAddress: Int) {
        val dio = Package_ApplicationStack_RPL_DIO(rank)
        sendUnRoutedPackage(destinationAddress, dio)
    }

    private fun hasDatabase(): Boolean {
        for (f in 0 until config.features.size) {
            if (config.features[f].getName().contains("Database") && config.hasFeature(parent, f)) {
                return true
            }
        }
        return false
    }

    private fun sendPackage_ApplicationStack_RPL_DAO(destinationAddress: Int) {
        val destinations = routingTable.getDestinations()
        val nextDatabaseHops = routingTable.getNextFeatureHops(destinations)
        val dao = Package_ApplicationStack_RPL_DAO(true, destinations, hasDatabase(), nextDatabaseHops)
        sendUnRoutedPackage(destinationAddress, dao)
    }

    private fun updateParent(newParent: Parent) {
        if (hasParent()) {
            if (newParent.address == preferredParent.address) {
                return
            }
        }
        if (hasParent()) {
            val dao = Package_ApplicationStack_RPL_DAO(false, IntArray(0), false, IntArray(0))
            sendUnRoutedPackage(preferredParent.address, dao)
        }
        preferredParent = newParent
        routingTable.fallbackHop = preferredParent.address
        sendPackage_ApplicationStack_RPL_DAO(preferredParent.address)
    }

    private fun updateApplicationStack_RPL_RoutingTable(hopAddress: Int, dao: Package_ApplicationStack_RPL_DAO): Boolean {
        return if (dao.isPath) {
            if (dao.hopHasDatabase) {
                routingTable.setDestinationsByDatabaseHop(hopAddress, dao.destinations)
            } else {
                routingTable.setDestinationsByHop(hopAddress, dao.destinations, dao.existingDatabaseHops)
            }
        } else {
            routingTable.removeDestinationsByHop(hopAddress)
        }
    }

    private fun objectiveFunction(pck: NetworkPackage): Int {
        val otherRank = (pck.payload as Package_ApplicationStack_RPL_DIO).rank
        return otherRank + MinHopRankIncrease
    }

    internal fun hasParent(): Boolean =
        preferredParent.address != notInitializedAddress

    override fun startUp() {
        val numberOfDevices = config.getNumberOfDevices()
        routingTable = ApplicationStack_RPL_RoutingTable(parent.address, numberOfDevices, hasDatabase())
        if (isRoot) {
            rank = ROOT_RANK
            broadcastPackage_ApplicationStack_RPL_DIO()
        }
        child.startUp()
    }

    override fun receive(pck: IPayload): IPayload? {
        pck as NetworkPackage
        val payload = pck.payload
        if (pck.destinationAddress == parent.address) {
            when (payload) {
                is Package_ApplicationStack_RPL_DIO -> {
                    if (objectiveFunction(pck) < rank) {
                        rank = objectiveFunction(pck)
                        updateParent(Parent(pck.sourceAddress, payload.rank))
                        broadcastPackage_ApplicationStack_RPL_DIO()
                    }
                }
                is Package_ApplicationStack_RPL_DAO -> {
                    val hasApplicationStack_RPL_RoutingTableChanged = updateApplicationStack_RPL_RoutingTable(pck.sourceAddress, payload)
                    if (hasParent() && hasApplicationStack_RPL_RoutingTableChanged) {
                        if (!isDelayPackage_ApplicationStack_RPL_DAOTimerRunning) {
                            val daoDelay = TimeUtils.toNanoSec(DEFAULT_Package_ApplicationStack_RPL_DAO_DELAY)
                            parent.setTimer(
                                daoDelay,
                                object : ITimer {
                                    override fun onTimerExpired(clock: Long) {
                                        isDelayPackage_ApplicationStack_RPL_DAOTimerRunning = false
                                        sendPackage_ApplicationStack_RPL_DAO(preferredParent.address)
                                    }
                                }
                            )
                            isDelayPackage_ApplicationStack_RPL_DAOTimerRunning = true
                        }
                    }
                }
                else -> {
                    child.receive(payload)
                }
            }
        } else {
            val hop = getNextHop(pck.destinationAddress)
            val delay = parent.getNetworkDelay(hop, pck)
            parent.assignToSimulation(pck.destinationAddress, hop, pck, delay)
        }
        return null
    }

    private fun getNextHop(destinationAddress: Int): Int = routingTable.getNextHop(destinationAddress)
    override fun getNextFeatureHops(destinationAddresses: IntArray, flag: Int): IntArray {
        return routingTable.getNextFeatureHops(destinationAddresses)
    }

    override fun toString(): String {
        val strBuilder = StringBuilder()
        strBuilder
            .append("> $parent").append(", ")
            .append("rank $rank").append(", ")
            .append(getParentString())
            .appendLine().append("  ")
            .append("children [${getChildrenString()}]")

        return strBuilder.toString()
    }

    private fun getParentString() =
        if (hasParent()) "parent ${config.getDeviceByAddress(preferredParent.address)}" else "root"

    private fun getChildrenString(): StringBuilder {
        val strBuilder = StringBuilder()
        val separator = ", "
        for (children in routingTable.getHops()) {
            val link = parent.linkManager.links[children]!!
            val device = config.getDeviceByAddress(children)
            strBuilder.append("$link to $device").append(separator).append("\n  ")
        }
        if (strBuilder.length >= separator.length) {
            strBuilder.deleteRange(strBuilder.length - separator.length, strBuilder.length)
        }
        return strBuilder
    }

    internal companion object {

        // ApplicationStack_RPL Constants (see section 17. of RFC 6550)
        // ------------------------

        // This is the default value for the DelayPackage_ApplicationStack_RPL_DAO Timer. Default is 1 second.
        internal const val DEFAULT_Package_ApplicationStack_RPL_DAO_DELAY: Int = 1

        // The minimum increase in Rank between a node and any of its DODAG parents.
        internal const val MinHopRankIncrease: Int = 1

        // This is the Rank for a DODAG root. ROOT_RANK has a value of MinHopRankIncrease
        internal const val ROOT_RANK: Int = MinHopRankIncrease

        // This is the constant maximum for the Rank.
        internal const val INFINITE_RANK: Int = Int.MAX_VALUE
    }

    override fun getAllChildApplications(): Set<IApplicationStack_Actuator> {
        var res = mutableSetOf<IApplicationStack_Actuator>(child)
        if (child is IApplicationStack_Middleware) {
            res.addAll(child.getAllChildApplications())
        }
        return res
    }

    override fun flush() {}
    override fun registerTimer(durationInNanoSeconds: Long, entity: ITimer): Unit = parent.registerTimer(durationInNanoSeconds, entity)
    override fun closestDeviceWithFeature(name: String): Int = parent.closestDeviceWithFeature(name)
    override fun resolveHostName(name: String): Int = parent.resolveHostName(name)
    override fun send(destinationAddress: Int, pck: IPayload) {
        val pck2 = NetworkPackage(parent.address, destinationAddress, pck)
        val hop = getNextHop(destinationAddress)
        val delay = parent.getNetworkDelay(hop, pck2)
        parent.assignToSimulation(destinationAddress, hop, pck2, delay)
    }

    override fun shutDown() {
        for (dest in 0 until config.devices.size) {
            try {
                val hop = getNextHop(dest)
                if (hop != -1) {
                    logger.addConnectionTable(parent.address, dest, hop)
                }
            } catch (e: Throwable) {
            }
        }
        child.shutDown()
    }

    override fun addChildApplication(child: IApplicationStack_Actuator): Unit = (this.child as IApplicationStack_Middleware).addChildApplication(child)
}
