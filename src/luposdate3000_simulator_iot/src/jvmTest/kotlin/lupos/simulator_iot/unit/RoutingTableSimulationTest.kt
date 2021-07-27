package lupos.simulator_iot.unit

import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.models.routing.RPL
import lupos.simulator_iot.models.routing.RoutingTable
import lupos.simulator_iot.utils.FilePaths
import kotlin.test.Test
import kotlin.test.assertEquals

class RoutingTableSimulationTest {

    companion object {
        private const val prefix = "${FilePaths.testResource}/routingTableSimulationTest"
    }

    @Test
    fun multiHopDODAGRoutingTableTest() {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("$prefix/multiHopDODAGRoutingTableTest.json")
        val config = simRun.parseJsonObjects(json)

        val a = config.getDeviceByName("A")
        val aRouter = a.router as RPL
        val b = config.getDeviceByName("B")
        val bRouter = b.router as RPL
        val c = config.getDeviceByName("C")
        val cRouter = c.router as RPL
        val d = config.getDeviceByName("D")
        val dRouter = d.router as RPL
        val e = config.getDeviceByName("E")
        val eRouter = e.router as RPL
        val f = config.getDeviceByName("F")
        val fRouter = f.router as RPL

        simRun.startSimulation(config)

        // routing table from A
        assertEquals(5, aRouter.routingTable.destinationCounter)
        assertEquals(b.address, aRouter.routingTable.getNextHop(b.address))
        assertEquals(c.address, aRouter.routingTable.getNextHop(c.address))
        assertEquals(c.address, aRouter.routingTable.getNextHop(d.address))
        assertEquals(c.address, aRouter.routingTable.getNextHop(e.address))
        assertEquals(c.address, aRouter.routingTable.getNextHop(f.address))
        // routing table from B
        assertEquals(0, bRouter.routingTable.destinationCounter)
        assertEquals(a.address, bRouter.routingTable.fallbackHop)
        // routing table from C
        assertEquals(3, cRouter.routingTable.destinationCounter)
        assertEquals(a.address, cRouter.routingTable.fallbackHop)
        assertEquals(d.address, cRouter.routingTable.getNextHop(d.address))
        assertEquals(e.address, cRouter.routingTable.getNextHop(e.address))
        assertEquals(e.address, cRouter.routingTable.getNextHop(f.address))
        // routing table from D
        assertEquals(0, bRouter.routingTable.destinationCounter)
        assertEquals(c.address, dRouter.routingTable.fallbackHop)
        // routing table from E
        assertEquals(1, eRouter.routingTable.destinationCounter)
        assertEquals(c.address, eRouter.routingTable.fallbackHop)
        assertEquals(f.address, eRouter.routingTable.getNextHop(f.address))
        // routing table from F
        assertEquals(0, fRouter.routingTable.destinationCounter)
        assertEquals(e.address, fRouter.routingTable.fallbackHop)
    }

    @Test
    fun starNetworkRoutingTables() {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("$prefix/starNetworkRoutingTables.json")
        val config = simRun.parseJsonObjects(json)

        val starNet = config.randStarNetworks["garageA"]!!
        val root = starNet.root
        val rootRouter = root.router as RPL
        val child1 = starNet.children[0]
        val child1Router = child1.router as RPL

        simRun.startSimulation(config)

        assertEquals(20, rootRouter.routingTable.destinationCounter)
        assertEquals(0, child1Router.routingTable.destinationCounter)
        assertEquals(root.address, rootRouter.routingTable.fallbackHop)
        assertEquals(root.address, child1Router.routingTable.fallbackHop)
        for (child in starNet.children)
            assertEquals(child.address, rootRouter.routingTable.getNextHop(child.address))
    }

    /**
     * DB(A) -> B -> DB(C)
     */
    @Test
    fun getNextDBHops1() {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("$prefix/getNextDBHops1.json")
        val config = simRun.parseJsonObjects(json)

        val a = config.getDeviceByName("A")
        val b = config.getDeviceByName("B")
        val c = config.getDeviceByName("C")

        simRun.startSimulation(config)

        // routing table from A
        var actual = a.router.getNextDatabaseHops(intArrayOf(a.address, b.address, c.address))
        assertEquals(a.address, actual[0])
        assertEquals(a.address, actual[1])
        assertEquals(c.address, actual[2])
        // routing table from B
        actual = b.router.getNextDatabaseHops(intArrayOf(c.address, b.address, a.address, 999))
        assertEquals(c.address, actual[0])
        assertEquals(RoutingTable.notInitialized, actual[1])
        assertEquals(RoutingTable.notInitialized, actual[2])
        assertEquals(RoutingTable.notInitialized, actual[3])
        // routing table from C
        actual = c.router.getNextDatabaseHops(intArrayOf(c.address, 999, a.address))
        assertEquals(c.address, actual[0])
        assertEquals(c.address, actual[1])
        assertEquals(c.address, actual[2])
    }

    /**
     * DB(A) -> B -> DB(C)
     *            -> DB(D)
     */
    @Test
    fun getNextDBHops2() {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("$prefix/getNextDBHops2.json")
        val config = simRun.parseJsonObjects(json)

        val a = config.getDeviceByName("A")
        val b = config.getDeviceByName("B")
        val c = config.getDeviceByName("C")
        val d = config.getDeviceByName("D")

        simRun.startSimulation(config)

        // routing table from A
        var actual = a.router.getNextDatabaseHops(intArrayOf(a.address, b.address, c.address, d.address))
        assertEquals(a.address, actual[0])
        assertEquals(a.address, actual[1])
        assertEquals(c.address, actual[2])
        assertEquals(d.address, actual[3])
        // routing table from B
        actual = b.router.getNextDatabaseHops(intArrayOf(c.address, d.address, b.address))
        assertEquals(c.address, actual[0])
        assertEquals(d.address, actual[1])
        assertEquals(RoutingTable.notInitialized, actual[2])
    }

    /**
     * DB(A) -> B -> DB(C) -> DB(D) -> E -> DB(F)
     *                              -> DB(G)
     */
    @Test
    fun getNextDBHops3() {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("$prefix/getNextDBHops3.json")
        val config = simRun.parseJsonObjects(json)

        val a = config.getDeviceByName("A")
        val b = config.getDeviceByName("B")
        val c = config.getDeviceByName("C")
        val d = config.getDeviceByName("D")
        val e = config.getDeviceByName("E")
        val f = config.getDeviceByName("F")
        val g = config.getDeviceByName("G")

        simRun.startSimulation(config)

        // routing table from A
        var actual = a.router.getNextDatabaseHops(intArrayOf(a.address, b.address, c.address, d.address, e.address, f.address, g.address))
        assertEquals(a.address, actual[0])
        assertEquals(a.address, actual[1])
        assertEquals(c.address, actual[2])
        assertEquals(c.address, actual[3])
        assertEquals(c.address, actual[4])
        assertEquals(c.address, actual[5])
        assertEquals(c.address, actual[6])
        // routing table from B
        actual = b.router.getNextDatabaseHops(intArrayOf(c.address, d.address, e.address, f.address, g.address))
        assertEquals(c.address, actual[0])
        assertEquals(c.address, actual[1])
        assertEquals(c.address, actual[2])
        assertEquals(c.address, actual[3])
        assertEquals(c.address, actual[4])
        // routing table from D
        actual = d.router.getNextDatabaseHops(intArrayOf(e.address, f.address, g.address))
        assertEquals(d.address, actual[0])
        assertEquals(f.address, actual[1])
        assertEquals(g.address, actual[2])
        // routing table from E
        actual = e.router.getNextDatabaseHops(intArrayOf(e.address, f.address, a.address))
        assertEquals(RoutingTable.notInitialized, actual[0])
        assertEquals(f.address, actual[1])
        assertEquals(RoutingTable.notInitialized, actual[2])
    }
}
