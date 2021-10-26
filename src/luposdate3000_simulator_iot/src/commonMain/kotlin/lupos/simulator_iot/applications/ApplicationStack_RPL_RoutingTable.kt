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

public class ApplicationStack_RPL_RoutingTable(
    private val ownAddress: Int,
    private val addressSpace: Int,
    private val hasDatabase: Boolean
) {

    private var nextHops = IntArray(0)
    private var nextDatabaseHops = IntArray(0)
    private var hops: MutableSet<Int> = mutableSetOf()

    internal var destinationCounter: Int = 0

    public var fallbackHop: Int = ownAddress

    private fun updateHop(destinationAddress: Int, nextHopAddress: Int, nextDatabaseHopAddress: Int): Boolean {
        var updated = false
        initializeEntries()
        if (nextHops[destinationAddress] == -1) {
            destinationCounter++
        }
        if (nextHops[destinationAddress] != nextHopAddress) {
            updated = true
        }
        nextHops[destinationAddress] = nextHopAddress
        hops.add(nextHopAddress)
        nextDatabaseHops[destinationAddress] = nextDatabaseHopAddress
        return updated
    }

    private fun initializeEntries() {
        if (nextHops.isEmpty()) {
            nextHops = IntArray(addressSpace) { -1 }
        }

        if (nextDatabaseHops.isEmpty()) {
            nextDatabaseHops = IntArray(addressSpace) { -1 }
        }
        nextHops[ownAddress] = ownAddress
        if (hasDatabase) {
            nextDatabaseHops[ownAddress] = ownAddress
        }
    }

    public fun getNextHop(destinationAddress: Int): Int {
        if (destinationAddress < nextHops.size) {
            val res = nextHops[destinationAddress]
            if (res != -1) {
                return res
            }
        }
        return fallbackHop
    }

    internal fun getNextFeatureHop(destinationAddress: Int): Int =
        if (destinationAddress < nextDatabaseHops.size) {
            nextDatabaseHops[destinationAddress]
        } else {
            -1 // tell the caller that we dont know it
        }

    internal fun getNextFeatureHops(destinationAddresses: IntArray): IntArray =
        IntArray(destinationAddresses.size) { getNextFeatureHop(destinationAddresses[it]) }

    internal fun removeDestinationsByHop(hop: Int): Boolean {
        var updated = false
        if (hop != ownAddress) {
            for ((index, value) in nextHops.withIndex())
                if (value == hop) {
                    nextHops[index] = -1
                    nextDatabaseHops[index] = -1
                    destinationCounter--
                    updated = true
                }
            hops.remove(hop)
        }
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

    public fun getDestinations(): IntArray {
        val destinations = IntArray(destinationCounter)
        var destIndex = 0
        for ((index, value) in nextHops.withIndex())
            if (value != -1 && value != ownAddress) {
                destinations[destIndex] = index
                destIndex++
            }
        return destinations
    }

    internal fun getHops(): Set<Int> = hops
}
