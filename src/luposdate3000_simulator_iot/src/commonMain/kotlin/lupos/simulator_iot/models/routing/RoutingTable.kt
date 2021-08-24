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

internal class RoutingTable(
    private val ownAddress: Int,
    private val addressSpace: Int,
    private val hasDatabase: Boolean
) {

    private var nextHops = IntArray(0)
    private var nextDatabaseHops = IntArray(0)
    private var hops: MutableSet<Int> = mutableSetOf()

    internal var fallbackHop = ownAddress

    private fun updateHop(destinationAddress: Int, nextHopAddress: Int, nextDatabaseHopAddress: Int): Boolean {
        var updated = false
        if (nextHops.isEmpty()) {
            nextHops = IntArray(addressSpace) { -1 }
        }
        if (nextDatabaseHops.isEmpty()) {
            nextDatabaseHops = IntArray(addressSpace) { -1 }
        }
        if (nextHops[destinationAddress] != nextHopAddress) {
            updated = true
        }
        nextHops[destinationAddress] = nextHopAddress
        hops.add(nextHopAddress)

        nextDatabaseHops[destinationAddress] = nextDatabaseHopAddress

        return updated
    }

    internal fun getNextHop(destinationAddress: Int): Int {
        if (destinationAddress <nextHops.size) {
            var res = nextHops[destinationAddress]
            if (res != -1) {
                return res
            }
        }
        return fallbackHop
    }
    internal fun getNextDatabaseHop(destinationAddress: Int): Int {
        if (destinationAddress <nextDatabaseHops.size) {
            var res = nextDatabaseHops[destinationAddress]
            if (res != -1) {
                return res
            }
        }
        // for routing we'd call 'fallbackHop' here .. so we dont know the next hop so we return this to the sender, such that the sender can handle it itself
        return -1
    }

    internal fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray {
        return IntArray(destinationAddresses.size) { getNextDatabaseHop(destinationAddresses[it]) }
    }

    internal fun removeDestinationsByHop(hop: Int): Boolean {
        var updated = false
        for ((index, value) in nextHops.withIndex())
            if (value == hop) {
                nextHops[index] = -1
                nextDatabaseHops[index] = -1
                updated = true
            }
        hops.remove(hop)
        return updated
    }

    internal fun setDestinationsByHop(hop: Int, destinations: IntArray, existingDatabaseHops: IntArray): Boolean {
        var updated = updateHop(hop, hop, -1)
        for ((index, dest) in destinations.withIndex()) {
            val flag = updateHop(dest, hop, existingDatabaseHops[index])
            updated = updated || flag
        }
        return updated
    }

    internal fun setDestinationsByDatabaseHop(hop: Int, destinations: IntArray): Boolean {
        var updated = updateHop(hop, hop, hop)
        for (dest in destinations) {
            val flag = updateHop(dest, hop, hop)
            updated = updated || flag
        }
        return updated
    }

    internal fun getDestinations(): IntArray {
        return nextHops.filter { it != -1 }.toIntArray()
    }

    internal fun getHops(): Set<Int> = hops
}
