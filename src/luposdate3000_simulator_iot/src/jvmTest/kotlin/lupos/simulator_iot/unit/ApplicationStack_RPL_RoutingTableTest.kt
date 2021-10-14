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

package lupos.simulator_iot.unit

import lupos.simulator_iot.applications.ApplicationStack_RPL_RoutingTable
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ApplicationStack_RPL_RoutingTableTest {

    @Test
    fun nextHopOnEmptyTableIsDefault() {
        val default = 0
        val size = 0
        val table = ApplicationStack_RPL_RoutingTable(default, size, false)
        assertEquals(default, table.getNextHop(0))
        assertEquals(default, table.getNextHop(1))
    }

    @Test
    fun theHopIsADestination() {
        val table = ApplicationStack_RPL_RoutingTable(0, 20, false)
        val hop = 3
        table.setDestinationsByDatabaseHop(hop, intArrayOf())
        assertEquals(hop, table.getNextHop(hop))
    }

    @Test
    fun setOnceAndGetHops() {
        val default = 0
        val table = ApplicationStack_RPL_RoutingTable(default, 20, false)
        val hop = 4
        val dest = intArrayOf(1, 2, 3, 4, 5, 6, 7)
        table.setDestinationsByDatabaseHop(hop, dest)
        assertEquals(default, table.getNextHop(0))
        assertEquals(default, table.getNextHop(8))
        assertEquals(hop, table.getNextHop(1))
        assertEquals(hop, table.getNextHop(7))
    }

    @Test
    fun setTwiceAndGetHops() {
        val default = 0
        val table = ApplicationStack_RPL_RoutingTable(default, 20, false)
        val hop = 4
        val dest1 = intArrayOf(6, 7, 8)
        table.setDestinationsByDatabaseHop(hop, dest1)
        val dest2 = intArrayOf(1, 2, 3, 4, 5, 6, 7)
        table.setDestinationsByDatabaseHop(hop, dest2)
        assertEquals(default, table.getNextHop(0))
        assertEquals(default, table.getNextHop(9))
        assertEquals(hop, table.getNextHop(1))
        assertEquals(hop, table.getNextHop(8))
    }

    @Test
    fun setAndRemoveDestinations() {
        val default = 0
        val table = ApplicationStack_RPL_RoutingTable(default, 20, false)
        val hop = 4
        val dest = intArrayOf(1, 2, 3, 4, 5, 6, 7)
        table.setDestinationsByDatabaseHop(hop, dest)
        table.removeDestinationsByHop(hop)
        assertEquals(default, table.getNextHop(1))
        assertEquals(default, table.getNextHop(7))
    }

    @Test
    fun thereIsOnlyOneHop() {
        val table = ApplicationStack_RPL_RoutingTable(0, 100, false)
        val hop = 4
        table.setDestinationsByDatabaseHop(hop, intArrayOf(1, 2))
        table.setDestinationsByDatabaseHop(hop, intArrayOf(99))
        assertEquals(1, table.getHops().size)
        assertTrue(table.getHops().contains(hop))
    }

    @Test
    fun thereAreTwoHops() {
        val table = ApplicationStack_RPL_RoutingTable(0, 100, false)
        val hop1 = 4
        table.setDestinationsByDatabaseHop(hop1, intArrayOf(1, 2))
        table.setDestinationsByDatabaseHop(hop1, intArrayOf(99))
        val hop2 = 3
        table.setDestinationsByDatabaseHop(hop2, intArrayOf(3))
        assertEquals(2, table.getHops().size)
        assertTrue(table.getHops().contains(hop1))
        assertTrue(table.getHops().contains(hop2))
    }

    @Test
    fun getDestinations() {
        val table = ApplicationStack_RPL_RoutingTable(0, 20, false)
        val hop = 4
        val dest = intArrayOf(1, 19)
        val numberOfDestinations = 1 + dest.size
        table.setDestinationsByDatabaseHop(hop, dest)
        val actual = table.getDestinations()
        assertEquals(numberOfDestinations, actual.size)
        assertEquals(1, actual[0])
        assertEquals(4, actual[1])
        assertEquals(19, actual[2])
    }

    @Test
    fun theFirstInsertUpdatesAlways() {
        val table = ApplicationStack_RPL_RoutingTable(0, 20, false)
        val isUpdated = table.setDestinationsByDatabaseHop(3, intArrayOf())
        assertTrue(isUpdated)
    }

    @Test
    fun updatesByMultipleInserts() {
        val table = ApplicationStack_RPL_RoutingTable(0, 20, false)
        val hop = 7
        table.setDestinationsByDatabaseHop(hop, intArrayOf(1, 2))
        val isUpdated = table.setDestinationsByDatabaseHop(hop, intArrayOf(1, 2, 3))
        assertTrue(isUpdated)
    }

    @Test
    fun noUpdatesByRedundantInserts() {
        val table = ApplicationStack_RPL_RoutingTable(0, 20, false)
        val hop = 9
        table.setDestinationsByDatabaseHop(hop, intArrayOf(1, 2))
        val isUpdated = table.setDestinationsByDatabaseHop(hop, intArrayOf(1, 2))
        assertFalse(isUpdated)
    }

    @Test
    fun updateIfIsRemoved() {
        val table = ApplicationStack_RPL_RoutingTable(0, 20, false)
        table.setDestinationsByDatabaseHop(3, intArrayOf())
        val isUpdated = table.removeDestinationsByHop(3)
        assertTrue(isUpdated)
    }

    @Test
    fun noUpdateIfNothingIsRemoved() {
        val table = ApplicationStack_RPL_RoutingTable(0, 20, false)
        table.setDestinationsByDatabaseHop(3, intArrayOf())
        val isUpdated = table.removeDestinationsByHop(9)
        assertFalse(isUpdated)
    }

    @Test
    fun getHopCount() {
        val numberOfHops = 20
        val table = ApplicationStack_RPL_RoutingTable(0, numberOfHops + 1, false)
        for (i in 1..numberOfHops) {
            table.setDestinationsByHop(i, intArrayOf(), intArrayOf())
        }
        assertEquals(numberOfHops, table.getHops().size)
        table.setDestinationsByHop(1, intArrayOf(), intArrayOf())
        assertEquals(numberOfHops, table.getHops().size)
    }
}
