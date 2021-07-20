package lupos.simulator_iot.unit

import lupos.simulator_iot.models.routing.RoutingTable
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RoutingTableTest {

    @Test
    fun nextHopOnEmptyTableIsDefault() {
        val default = 0
        val size = 0
        val table = RoutingTable(default, size, false)
        assertEquals(default, table.getNextHop(0))
        assertEquals(default, table.getNextHop(1))
    }

    @Test
    fun theHopIsADestination() {
        val table = RoutingTable(0, 20, false)
        val hop = 3
        table.setDestinationsByDatabaseHop(hop, intArrayOf())
        assertEquals(hop, table.getNextHop(hop))
    }

    @Test
    fun setOnceAndGetHops() {
        val default = 0
        val table = RoutingTable(default, 20, false)
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
        val table = RoutingTable(default, 20, false)
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
        val table = RoutingTable(default, 20, false)
        val hop = 4
        val dest = intArrayOf(1, 2, 3, 4, 5, 6, 7)
        table.setDestinationsByDatabaseHop(hop, dest)
        table.removeDestinationsByHop(hop)
        assertEquals(default, table.getNextHop(1))
        assertEquals(default, table.getNextHop(7))
    }

    @Test
    fun thereIsOnlyOneHop() {
        val table = RoutingTable(0, 100, false)
        val hop = 4
        table.setDestinationsByDatabaseHop(hop, intArrayOf(1, 2))
        table.setDestinationsByDatabaseHop(hop, intArrayOf(99))
        assertEquals(1, table.getHops().size)
        assertTrue(table.getHops().contains(hop))
    }

    @Test
    fun thereAreTwoHops() {
        val table = RoutingTable(0, 100, false)
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
        val table = RoutingTable(0, 20, false)
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
        val table = RoutingTable(0, 20, false)
        val isUpdated = table.setDestinationsByDatabaseHop(3, intArrayOf())
        assertTrue(isUpdated)
    }

    @Test
    fun updatesByMultipleInserts() {
        val table = RoutingTable(0, 20, false)
        val hop = 7
        table.setDestinationsByDatabaseHop(hop, intArrayOf(1, 2))
        val isUpdated = table.setDestinationsByDatabaseHop(hop, intArrayOf(1, 2, 3))
        assertTrue(isUpdated)
    }

    @Test
    fun noUpdatesByRedundantInserts() {
        val table = RoutingTable(0, 20, false)
        val hop = 9
        table.setDestinationsByDatabaseHop(hop, intArrayOf(1, 2))
        val isUpdated = table.setDestinationsByDatabaseHop(hop, intArrayOf(1, 2))
        assertFalse(isUpdated)
    }

    @Test
    fun updateIfIsRemoved() {
        val table = RoutingTable(0, 20, false)
        table.setDestinationsByDatabaseHop(3, intArrayOf())
        val isUpdated = table.removeDestinationsByHop(3)
        assertTrue(isUpdated)
    }

    @Test
    fun noUpdateIfNothingIsRemoved() {
        val table = RoutingTable(0, 20, false)
        table.setDestinationsByDatabaseHop(3, intArrayOf())
        val isUpdated = table.removeDestinationsByHop(9)
        assertFalse(isUpdated)
    }

    @Test
    fun getDatabaseHopWithoutOwnDatabase() {
        val table = RoutingTable(0, 20, false)
        val hop = 3
        table.setDestinationsByDatabaseHop(hop, intArrayOf(1, 2, 8))
        assertEquals(hop, table.getNextDatabaseHop(1))
        assertEquals(hop, table.getNextDatabaseHop(2))
        assertEquals(hop, table.getNextDatabaseHop(8))
    }

    @Test
    fun getDatabaseHopWithOwnDatabase() {
        val table = RoutingTable(0, 20, true)
        val hop = 3
        table.setDestinationsByDatabaseHop(hop, intArrayOf(1, 2, 8))
        assertEquals(hop, table.getNextDatabaseHop(1))
        assertEquals(hop, table.getNextDatabaseHop(2))
        assertEquals(hop, table.getNextDatabaseHop(8))
    }

    @Test
    fun getDefaultDatabaseHopWithoutOwnDatabase() {
        val table = RoutingTable(0, 20, false)
        val hop = 8
        table.setDestinationsByHop(hop, intArrayOf(), intArrayOf())
        assertEquals(RoutingTable.notInitialized, table.getNextDatabaseHop(hop))
    }

    @Test
    fun getDefaultDatabaseHopWithOwnDatabase() {
        val table = RoutingTable(0, 20, true)
        val hop = 8
        table.setDestinationsByHop(hop, intArrayOf(), intArrayOf())
        assertEquals(0, table.getNextDatabaseHop(hop))
    }

    @Test
    fun getExistingDBHopWithoutOwnDB() {
        val table = RoutingTable(0, 10, false)
        val hop = 8
        val dest = 4
        val dbHop = 9
        table.setDestinationsByHop(hop, intArrayOf(1, 2, 3, dest), intArrayOf(2, 6, 7, dbHop))
        assertEquals(dbHop, table.getNextDatabaseHop(dest))
    }

    @Test
    fun getExistingDBHopWithOwnDB() {
        val table = RoutingTable(0, 10, true)
        val hop = 8
        val dest = 4
        val dbHop = 9
        table.setDestinationsByHop(hop, intArrayOf(1, 2, 3, dest), intArrayOf(2, 6, 7, dbHop))
        assertEquals(dbHop, table.getNextDatabaseHop(dest))
    }

    @Test
    fun removeDestinationsRemovesAlsoDBHopsWithoutDB() {
        val table = RoutingTable(0, 10, false)
        val hop = 0
        val dest = 4
        table.setDestinationsByHop(hop, intArrayOf(1, 2, 3, dest), intArrayOf(2, 6, 7, 9))
        table.removeDestinationsByHop(hop)
        assertEquals(RoutingTable.notInitialized, table.getNextDatabaseHop(dest))
    }

    @Test
    fun removeDestinationsRemovesAlsoDBHopsWithDB() {
        val table = RoutingTable(0, 10, true)
        val hop = 0
        val dest = 4
        table.setDestinationsByHop(hop, intArrayOf(1, 2, 3, dest), intArrayOf(2, 6, 7, 9))
        table.removeDestinationsByHop(hop)
        assertEquals(0, table.getNextDatabaseHop(dest))
    }

    @Test
    fun testMultipleUpdates() {
        val table = RoutingTable(0, 20, false)
        table.setDestinationsByDatabaseHop(0, intArrayOf(1, 2))
        table.setDestinationsByHop(5, intArrayOf(6, 7), intArrayOf(6, 7))
        table.setDestinationsByHop(5, intArrayOf(11, 12), intArrayOf(9, 10))
        assertEquals(0, table.getNextDatabaseHop(0))
        assertEquals(6, table.getNextDatabaseHop(6))
        assertEquals(9, table.getNextDatabaseHop(11))
    }

    @Test
    fun getAllDBHopsByDestinations() {
        val table = RoutingTable(0, 20, false)
        table.setDestinationsByDatabaseHop(0, intArrayOf(1, 2))
        table.setDestinationsByHop(5, intArrayOf(6, 7), intArrayOf(6, 7))
        table.setDestinationsByHop(5, intArrayOf(11, 12), intArrayOf(9, 10))
        val result = table.getNextDatabaseHops(intArrayOf(1, 6, 11))
        assertEquals(3, result.size)
        assertEquals(0, result[0])
        assertEquals(6, result[1])
        assertEquals(9, result[2])
    }

    @Test
    fun getHopCount() {
        val numberOfHops = 20
        val table = RoutingTable(0, numberOfHops + 1, false)
        for (i in 1..numberOfHops) {
            table.setDestinationsByHop(i, intArrayOf(), intArrayOf())
        }
        assertEquals(numberOfHops, table.getHops().size)
        table.setDestinationsByHop(1, intArrayOf(), intArrayOf())
        assertEquals(numberOfHops, table.getHops().size)
    }
}
