import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import routing.RoutingTable

class RoutingTableTest {

    @Test
    fun nextHopOnEmptyTableIsDefault() {
        val default = 0
        val size = 0
        val table = RoutingTable(default, size)
        Assertions.assertEquals(default, table.getNextHop(0))
        Assertions.assertEquals(default, table.getNextHop(1))
    }

    @Test
    fun theHopIsADestination() {
        val table = RoutingTable(0, 20)
        val hop = 3
        table.setDestinationsByDatabaseHop(hop, intArrayOf())
        Assertions.assertEquals(hop, table.getNextHop(hop))
    }

    @Test
    fun setOnceAndGetHops() {
        val default = 0
        val table = RoutingTable(default, 20)
        val hop = 4
        val dest = intArrayOf(1,2,3,4,5,6,7)
        table.setDestinationsByDatabaseHop(hop, dest)
        Assertions.assertEquals(default, table.getNextHop(0))
        Assertions.assertEquals(default, table.getNextHop(8))
        Assertions.assertEquals(hop, table.getNextHop(1))
        Assertions.assertEquals(hop, table.getNextHop(7))
    }

    @Test
    fun setTwiceAndGetHops() {
        val default = 0
        val table = RoutingTable(default, 20)
        val hop = 4
        val dest1 = intArrayOf(6,7,8)
        table.setDestinationsByDatabaseHop(hop, dest1)
        val dest2 = intArrayOf(1,2,3,4,5,6,7)
        table.setDestinationsByDatabaseHop(hop, dest2)
        Assertions.assertEquals(default, table.getNextHop(0))
        Assertions.assertEquals(default, table.getNextHop(9))
        Assertions.assertEquals(hop, table.getNextHop(1))
        Assertions.assertEquals(hop, table.getNextHop(8))
    }

    @Test
    fun setAndRemoveDestinations() {
        val default = 0
        val table = RoutingTable(default, 20)
        val hop = 4
        val dest = intArrayOf(1,2,3,4,5,6,7)
        table.setDestinationsByDatabaseHop(hop, dest)
        table.removeDestinationsByHop(hop)
        Assertions.assertEquals(default, table.getNextHop(1))
        Assertions.assertEquals(default, table.getNextHop(7))
    }

    @Test
    fun thereIsOnlyOneHop() {
        val table = RoutingTable(0, 100)
        val hop = 4
        table.setDestinationsByDatabaseHop(hop, intArrayOf(1,2))
        table.setDestinationsByDatabaseHop(hop, intArrayOf(99))
        Assertions.assertEquals(1, table.getHops().size)
        Assertions.assertTrue(table.getHops().contains(hop))
    }

    @Test
    fun thereAreTwoHops() {
        val table = RoutingTable(0, 100)
        val hop1 = 4
        table.setDestinationsByDatabaseHop(hop1, intArrayOf(1,2))
        table.setDestinationsByDatabaseHop(hop1, intArrayOf(99))
        val hop2 = 3
        table.setDestinationsByDatabaseHop(hop2, intArrayOf(3))
        Assertions.assertEquals(2, table.getHops().size)
        Assertions.assertTrue(table.getHops().contains(hop1))
        Assertions.assertTrue(table.getHops().contains(hop2))
    }

    @Test
    fun getDestinations() {
        val table = RoutingTable(0, 20)
        val hop = 4
        val dest = intArrayOf(1,19)
        val numberOfDestinations = 1 + dest.size
        table.setDestinationsByDatabaseHop(hop, dest)
        val actual = table.getDestinations()
        Assertions.assertEquals(numberOfDestinations, actual.size)
        Assertions.assertEquals(1, actual[0])
        Assertions.assertEquals(4, actual[1])
        Assertions.assertEquals(19, actual[2])
    }

    @Test
    fun theFirstInsertUpdatesAlways() {
        val table = RoutingTable(0, 20)
        val isUpdated = table.setDestinationsByDatabaseHop(3, intArrayOf())
        Assertions.assertTrue(isUpdated)
    }

    @Test
    fun updatesByMultipleInserts() {
        val table = RoutingTable(0, 20)
        val hop = 7
        table.setDestinationsByDatabaseHop(hop, intArrayOf(1,2))
        val isUpdated = table.setDestinationsByDatabaseHop(hop, intArrayOf(1,2,3))
        Assertions.assertTrue(isUpdated)
    }

    @Test
    fun noUpdatesByRedundantInserts() {
        val table = RoutingTable(0, 20)
        val hop = 9
        table.setDestinationsByDatabaseHop(hop, intArrayOf(1,2))
        val isUpdated = table.setDestinationsByDatabaseHop(hop, intArrayOf(1,2))
        Assertions.assertFalse(isUpdated)
    }

    @Test
    fun updateIfIsRemoved() {
        val table = RoutingTable(0, 20)
        table.setDestinationsByDatabaseHop(3, intArrayOf())
        val isUpdated = table.removeDestinationsByHop(3)
        Assertions.assertTrue(isUpdated)
    }

    @Test
    fun noUpdateIfNothingIsRemoved() {
        val table = RoutingTable(0, 20)
        table.setDestinationsByDatabaseHop(3, intArrayOf())
        val isUpdated = table.removeDestinationsByHop(9)
        Assertions.assertFalse(isUpdated)
    }

    @Test
    fun getDatabaseHop() {
        val table = RoutingTable(0, 20)
        val hop = 3
        val dest = 8
        table.setDestinationsByDatabaseHop(hop, intArrayOf(1,2,dest))
        Assertions.assertEquals(hop, table.getNextDatabaseHop(dest))
    }

    @Test
    fun getDefaultDatabaseHop() {
        val table = RoutingTable(0, 20)
        val hop = 8
        table.setDestinationsByHop(hop, intArrayOf(), intArrayOf())
        Assertions.assertEquals(RoutingTable.notInitialized, table.getNextDatabaseHop(hop))
    }

    @Test
    fun getExistingDatabaseHop() {
        val table = RoutingTable(0, 10)
        val hop = 8
        val dest = 4
        val dbHop = 9
        table.setDestinationsByHop(hop, intArrayOf(1,2,3, dest), intArrayOf(2, 6, 7, dbHop))
        Assertions.assertEquals(dbHop, table.getNextDatabaseHop(dest))
    }

    @Test
    fun removeDestinationsRemovesAlsoDBHops() {
        val table = RoutingTable(0, 10)
        val hop = 0
        val dest = 4
        table.setDestinationsByHop(hop, intArrayOf(1,2,3,dest), intArrayOf(2,6,7,9))
        table.removeDestinationsByHop(hop)
        Assertions.assertEquals(RoutingTable.notInitialized, table.getNextDatabaseHop(dest))
    }

    @Test
    fun testMultipleUpdates() {
        val table = RoutingTable(0, 20)
        table.setDestinationsByDatabaseHop(0, intArrayOf(1,2))
        table.setDestinationsByHop(5, intArrayOf(6,7), intArrayOf(6,7))
        table.setDestinationsByHop(5, intArrayOf(11,12), intArrayOf(9, 10))
        Assertions.assertEquals(0, table.getNextDatabaseHop(0))
        Assertions.assertEquals(6, table.getNextDatabaseHop(6))
        Assertions.assertEquals(9, table.getNextDatabaseHop(11))
    }

    @Test
    fun getAllDBHopsByDestinations() {
        val table = RoutingTable(0, 20)
        table.setDestinationsByDatabaseHop(0, intArrayOf(1,2))
        table.setDestinationsByHop(5, intArrayOf(6,7), intArrayOf(6,7))
        table.setDestinationsByHop(5, intArrayOf(11,12), intArrayOf(9, 10))
        val result = table.getNextDatabaseHops(intArrayOf(1, 6, 11))
        Assertions.assertEquals(3, result.size)
        Assertions.assertEquals(0, result[0])
        Assertions.assertEquals(6, result[1])
        Assertions.assertEquals(9, result[2])
    }


}