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

import lupos.simulator_iot.models.Device
import lupos.simulator_iot.models.net.NetworkPackage
import lupos.simulator_iot.utils.TimeUtils

internal class RPL(internal val device: Device) : IRoutingProtocol {

    internal lateinit var routingTable: RoutingTable

    private val notInitializedAddress = -1

    override var isRoot: Boolean = false

    internal var rank: Int = INFINITE_RANK
        private set

    internal var preferredParent: Parent = Parent()
        private set

    private var isDelayDAOTimerRunning = false

    internal inner class Parent(internal var address: Int = notInitializedAddress, internal var rank: Int = INFINITE_RANK)

    private fun broadcastDIO() {
        for (potentialChild in device.linkManager.getNeighbours())
            if (potentialChild != preferredParent.address) {
                sendDIO(potentialChild)
            }
    }

    private fun sendDIO(destinationAddress: Int) {
        val dio = DIO(rank)
        device.sendUnRoutedPackage(destinationAddress, dio)
    }
    private fun hasDatabase(): Boolean {
        val res = device.simRun.config.dbDeviceAddressesStore.contains(device.address) || device.simRun.config.dbDeviceAddressesQuery.contains(device.address)
        return res
    }

    private fun sendDAO(destinationAddress: Int) {
        val destinations = routingTable.getDestinations()
        val nextDatabaseHops = routingTable.getNextDatabaseHops(destinations)
        val dao = DAO(true, destinations, hasDatabase(), nextDatabaseHops)
        device.sendUnRoutedPackage(destinationAddress, dao)
    }

    private fun sendDAONoPath(destinationAddress: Int) {
        val dao = DAO(false, IntArray(0), false, IntArray(0))
        device.sendUnRoutedPackage(destinationAddress, dao)
    }

    private fun processDIO(pck: NetworkPackage) {
        val dio = pck.payload as DIO
        if (objectiveFunction(pck) >= rank) {
            return
        }
        rank = objectiveFunction(pck)
        updateParent(Parent(pck.sourceAddress, dio.rank))
        broadcastDIO()
    }

    private fun updateParent(newParent: Parent) {
        if (hasParent()) {
            if (newParent.address == preferredParent.address) {
                return
            }
        }
        if (hasParent()) {
            sendDAONoPath(preferredParent.address)
        }
        preferredParent = newParent
        routingTable.fallbackHop = preferredParent.address
        sendDAO(preferredParent.address)
    }

    private fun processDAO(pck: NetworkPackage) {
        val dao = pck.payload as DAO
        val hasRoutingTableChanged = updateRoutingTable(pck.sourceAddress, dao)
        if (hasParent() && hasRoutingTableChanged) {
            if (!isDelayDAOTimerRunning) {
                startDelayDAOTimer()
            }
        }
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

    override fun startRouting() {
        val numberOfDevices = device.simRun.config.getNumberOfDevices()
        routingTable = RoutingTable(device.address, numberOfDevices, hasDatabase())
        if (isRoot) {
            rank = ROOT_RANK
            broadcastDIO()
        }
    }

    override fun isControlPackage(pck: NetworkPackage): Boolean =
        pck.payload is DAO || pck.payload is DIO

    private fun forwardDAO() {
        sendDAO(preferredParent.address)
    }

    private fun startDelayDAOTimer() {
        val daoDelay = TimeUtils.toNanoSec(DEFAULT_DAO_DELAY)
        device.setTimer(
            daoDelay,
            {
                isDelayDAOTimerRunning = false
                forwardDAO()
            }
        )
        isDelayDAOTimerRunning = true
    }

    override fun processControlPackage(pck: NetworkPackage) {
        when (pck.payload) {
            is DIO -> processDIO(pck)
            is DAO -> processDAO(pck)
            else -> throw Exception("Wrong Package")
        }
    }

    override fun getNextHop(destinationAddress: Int): Int =
        routingTable.getNextHop(destinationAddress)

    override fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray =
        routingTable.getNextDatabaseHops(destinationAddresses)

    override fun toString(): String {
        val strBuilder = StringBuilder()
        strBuilder
            .append("> $device").append(", ")
            .append("rank $rank").append(", ")
            .append(getParentString())
            .appendLine().append("  ")
            .append("children [${getChildrenString()}]")

        return strBuilder.toString()
    }

    private fun getParentString() =
        if (hasParent()) "parent ${device.simRun.config.getDeviceByAddress(preferredParent.address)}" else "root"

    private fun getChildrenString(): StringBuilder {
        val strBuilder = StringBuilder()
        val separator = ", "
        for (children in routingTable.getHops()) {
            val link = device.linkManager.links[children]!!
            val device = device.simRun.config.getDeviceByAddress(children)
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
}
