import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

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
        table.setDestinationsByHop(hop, intArrayOf())
        Assertions.assertEquals(hop, table.getNextHop(hop))
    }

    @Test
    fun setOnceAndGetHops() {
        val default = 0
        val table = RoutingTable(default, 20)
        val hop = 4
        val dest = intArrayOf(1,2,3,4,5,6,7)
        table.setDestinationsByHop(hop, dest)
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
        table.setDestinationsByHop(hop, dest1)
        val dest2 = intArrayOf(1,2,3,4,5,6,7)
        table.setDestinationsByHop(hop, dest2)
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
        table.setDestinationsByHop(hop, dest)
        table.removeDestinationsByHop(hop)
        Assertions.assertEquals(default, table.getNextHop(1))
        Assertions.assertEquals(default, table.getNextHop(7))
    }

    @Test
    fun thereIsOnlyOneHop() {
        val table = RoutingTable(0, 100)
        val hop = 4
        table.setDestinationsByHop(hop, intArrayOf(1,2))
        table.setDestinationsByHop(hop, intArrayOf(99))
        Assertions.assertEquals(1, table.getHops().size)
        Assertions.assertTrue(table.getHops().contains(hop))
    }

    @Test
    fun thereAreTwoHops() {
        val table = RoutingTable(0, 100)
        val hop1 = 4
        table.setDestinationsByHop(hop1, intArrayOf(1,2))
        table.setDestinationsByHop(hop1, intArrayOf(99))
        val hop2 = 3
        table.setDestinationsByHop(hop2, intArrayOf(3))
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
        table.setDestinationsByHop(hop, dest)
        val actual = table.getDestinations()
        Assertions.assertEquals(numberOfDestinations, actual.size)
        Assertions.assertEquals(1, actual[0])
        Assertions.assertEquals(4, actual[1])
        Assertions.assertEquals(19, actual[2])
    }

    @Test
    fun theFirstInsertUpdatesAlways() {
        val table = RoutingTable(0, 20)
        val isUpdated = table.setDestinationsByHop(3, intArrayOf())
        Assertions.assertTrue(isUpdated)
    }

    @Test
    fun updatesByMultipleInserts() {
        val table = RoutingTable(0, 20)
        val hop = 7
        table.setDestinationsByHop(hop, intArrayOf(1,2))
        val isUpdated = table.setDestinationsByHop(hop, intArrayOf(1,2,3))
        Assertions.assertTrue(isUpdated)
    }

    @Test
    fun noUpdatesByRedundantInserts() {
        val table = RoutingTable(0, 20)
        val hop = 9
        table.setDestinationsByHop(hop, intArrayOf(1,2))
        val isUpdated = table.setDestinationsByHop(hop, intArrayOf(1,2))
        Assertions.assertFalse(isUpdated)
    }

    @Test
    fun updateIfIsRemoved() {
        val table = RoutingTable(0, 20)
        table.setDestinationsByHop(3, intArrayOf())
        val isUpdated = table.removeDestinationsByHop(3)
        Assertions.assertTrue(isUpdated)
    }

    @Test
    fun noUpdateIfNothingIsRemoved() {
        val table = RoutingTable(0, 20)
        table.setDestinationsByHop(3, intArrayOf())
        val isUpdated = table.removeDestinationsByHop(9)
        Assertions.assertFalse(isUpdated)
    }
}