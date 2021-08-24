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

    internal var destinationCounter: Int = 0
        private set

    internal companion object {
        internal const val notInitialized: Int = -1
    }

    internal var fallbackHop = ownAddress

    private fun updateHop(destinationAddress: Int, nextHopAddress: Int, nextDatabaseHopAddress: Int): Boolean {
        var updated = false
        initializeEntries()

        if (nextHops[destinationAddress] == notInitialized) {
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
            nextHops = IntArray(addressSpace) { notInitialized }
        }

        if (nextDatabaseHops.isEmpty()) {
            nextDatabaseHops = IntArray(addressSpace) { notInitialized }
        }
    }

    internal fun getNextHop(destinationAddress: Int): Int =
        if (!hasDestination(destinationAddress)) {
            fallbackHop
        } else {
            nextHops[destinationAddress]
        }
    internal fun getNextDatabaseHop(destinationAddress: Int): Int {
        val res: Int
        println("RoutingTable.getNextDatabaseHop $destinationAddress ... ${nextDatabaseHops.map{it}} .. ${nextHops.map{it}}")
        if (!hasDestination(destinationAddress)) {
            res = getOwnAddressIfItHasDatabase()
            println("RoutingTable.getNextDatabaseHop a $destinationAddress -> $res")
        } else {
            val hop = nextDatabaseHops[destinationAddress]
            if (hop != notInitialized) {
                res = hop
                println("RoutingTable.getNextDatabaseHop b $destinationAddress -> $res")
            } else {
                res = getOwnAddressIfItHasDatabase()
                println("RoutingTable.getNextDatabaseHop c $destinationAddress -> $res")
            }
        }
        return res
    }

    private fun getOwnAddressIfItHasDatabase(): Int =
        if (hasDatabase) ownAddress else notInitialized

    internal fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray {
        val dbHops = IntArray(destinationAddresses.size) { -1 }
        for ((index, dest) in destinationAddresses.withIndex())
            dbHops[index] = getNextDatabaseHop(dest)
        println("RoutingTable.getNextDatabaseHops ${destinationAddresses.map{it}} .. ${dbHops.map{it}}")
        return dbHops
    }

    private fun hasDestination(destinationAddress: Int) =
        destinationAddress <= nextHops.size - 1 && nextHops[destinationAddress] != notInitialized

    internal fun removeDestinationsByHop(hop: Int): Boolean {
        var updated = false
        for ((index, value) in nextHops.withIndex())
            if (value == hop) {
                nextHops[index] = notInitialized
                nextDatabaseHops[index] = notInitialized
                destinationCounter--
                updated = true
            }
        hops.remove(hop)
        return updated
    }

    internal fun setDestinationsByHop(hop: Int, destinations: IntArray, existingDatabaseHops: IntArray): Boolean {
        var updated: Boolean
        updated = updateHop(hop, hop, notInitialized)
        for ((index, dest) in destinations.withIndex()) {
            val flag = updateHop(dest, hop, existingDatabaseHops[index])
            updated = updated || flag
            println("RoutingTable.setDestinationsByHop $ownAddress ... ${nextDatabaseHops.map{it}} .. ${nextHops.map{it}}")
        }
        return updated
    }

    internal fun setDestinationsByDatabaseHop(hop: Int, destinations: IntArray): Boolean {
        var updated: Boolean
        updated = updateHop(hop, hop, hop)
        for (dest in destinations) {
            val flag = updateHop(dest, hop, hop)
            updated = updated || flag
            println("RoutingTable.setDestinationsByDatabaseHop $ownAddress ... ${nextDatabaseHops.map{it}} .. ${nextHops.map{it}}")
        }
        return updated
    }

    internal fun getDestinations(): IntArray {
        val destinations = IntArray(destinationCounter)
        var destIndex = 0
        for ((index, value) in nextHops.withIndex())
            if (value != notInitialized) {
                destinations[destIndex] = index
                destIndex++
            }
        return destinations
    }

    internal fun getHops(): Set<Int> = hops
}
