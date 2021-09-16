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

package lupos.simulator_iot.models.routing
import lupos.simulator_core.ITimer
import lupos.simulator_db.IApplicationStack_Actuator
import lupos.simulator_db.IApplicationStack_Middleware
import lupos.simulator_db.IApplicationStack_Rooter
import lupos.simulator_db.ILogger
import lupos.simulator_db.IPayload
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.models.Device
import lupos.simulator_iot.models.net.LinkManager
import lupos.simulator_iot.models.net.NetworkPackage
import lupos.simulator_iot.utils.TimeUtils
internal class RPL(
    private val parent: Device,
    private val child: IApplicationStack_Actuator,
    private val linkManager: LinkManager,
    private val logger: ILogger,
    private val config: Configuration,
) : IApplicationStack_Rooter {
    init {
        child.setRouter(this)
    }
    internal lateinit var routingTable: RoutingTable
    private val notInitializedAddress = -1
    internal var isRoot: Boolean = false
    internal var rank: Int = INFINITE_RANK
    internal var preferredParent: Parent = Parent()
    private var isDelayDAOTimerRunning = false

    internal inner class Parent(internal var address: Int = notInitializedAddress, internal var rank: Int = INFINITE_RANK)
    override fun setRoot() {
        isRoot = true
    }

    internal fun sendUnRoutedPackage(hop: Int, data: IPayload) {
        val pck = NetworkPackage(parent.address, hop, data)
        val delay = parent.getNetworkDelay(hop, pck)
        parent.assignToSimulation(hop, hop, pck, delay)
    }

    private fun broadcastDIO() {
        for (potentialChild in linkManager.getNeighbours())
            if (potentialChild != preferredParent.address) {
                sendDIO(potentialChild)
            }
    }

    private fun sendDIO(destinationAddress: Int) {
        val dio = DIO(rank)
        sendUnRoutedPackage(destinationAddress, dio)
    }
    private fun hasDatabase(): Boolean {
        val res = parent.simRun.config.dbDeviceAddressesStore.contains(parent.address) || parent.simRun.config.dbDeviceAddressesQuery.contains(parent.address)
        return res
    }

    private fun sendDAO(destinationAddress: Int) {
        val destinations = routingTable.getDestinations()
        val nextDatabaseHops = routingTable.getNextDatabaseHops(destinations)
        val dao = DAO(true, destinations, hasDatabase(), nextDatabaseHops)
        sendUnRoutedPackage(destinationAddress, dao)
    }

    private fun updateParent(newParent: Parent) {
        if (hasParent()) {
            if (newParent.address == preferredParent.address) {
                return
            }
        }
        if (hasParent()) {
            val dao = DAO(false, IntArray(0), false, IntArray(0))
            sendUnRoutedPackage(preferredParent.address, dao)
        }
        preferredParent = newParent
        routingTable.fallbackHop = preferredParent.address
        sendDAO(preferredParent.address)
    }

    private fun updateRoutingTable(hopAddress: Int, dao: DAO): Boolean {
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
        val otherRank = (pck.payload as DIO).rank
        return otherRank + MinHopRankIncrease
    }

    internal fun hasParent(): Boolean =
        preferredParent.address != notInitializedAddress

    override fun startUp() {
        val numberOfDevices = parent.simRun.config.getNumberOfDevices()
        routingTable = RoutingTable(parent.address, numberOfDevices, hasDatabase())
        if (isRoot) {
            rank = ROOT_RANK
            broadcastDIO()
        }
        child.startUp()
    }
    override fun receive(pck: IPayload): IPayload? {
        pck as NetworkPackage
        val payload = pck.payload
        if (pck.destinationAddress == parent.address) {
            when (payload) {
                is DIO -> {
                    if (objectiveFunction(pck) < rank) {
                        rank = objectiveFunction(pck)
                        updateParent(Parent(pck.sourceAddress, payload.rank))
                        broadcastDIO()
                    }
                }
                is DAO -> {
                    val hasRoutingTableChanged = updateRoutingTable(pck.sourceAddress, payload)
                    if (hasParent() && hasRoutingTableChanged) {
                        if (!isDelayDAOTimerRunning) {
                            val daoDelay = TimeUtils.toNanoSec(DEFAULT_DAO_DELAY)
                            parent.setTimer(
                                daoDelay,
                                object : ITimer {
                                    override fun onTimerExpired(clock: Long) {
                                        isDelayDAOTimerRunning = false
                                        sendDAO(preferredParent.address)
                                    }
                                }
                            )
                            isDelayDAOTimerRunning = true
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
    override fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray = routingTable.getNextDatabaseHops(destinationAddresses)

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
        if (hasParent()) "parent ${parent.simRun.config.getDeviceByAddress(preferredParent.address)}" else "root"

    private fun getChildrenString(): StringBuilder {
        val strBuilder = StringBuilder()
        val separator = ", "
        for (children in routingTable.getHops()) {
            val link = linkManager.links[children]!!
            val device = parent.simRun.config.getDeviceByAddress(children)
            strBuilder.append("$link to $device").append(separator).append("\n  ")
        }
        if (strBuilder.length >= separator.length) {
            strBuilder.deleteRange(strBuilder.length - separator.length, strBuilder.length)
        }
        return strBuilder
    }

    internal companion object {

        // RPL Constants (see section 17. of RFC 6550)
        // ------------------------

        // This is the default value for the DelayDAO Timer. Default is 1 second.
        internal const val DEFAULT_DAO_DELAY: Int = 1

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
                val dbhop = getNextDatabaseHops(intArrayOf(dest))[0]
                if (dbhop != -1) {
                    logger.addConnectionTableDB(parent.address, dest, dbhop)
                }
            } catch (e: Throwable) {
            }
        }
        child.shutDown()
    }
}
