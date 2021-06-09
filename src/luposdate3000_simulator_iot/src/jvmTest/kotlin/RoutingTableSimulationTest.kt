package lupos.simulator_iot

import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.routing.RPLRouter
import lupos.simulator_iot.routing.RoutingTable
import kotlin.test.*

class RoutingTableSimulationTest {

    companion object {
        private const val prefix = "RoutingTableSimulationTest"
    }

    @Test
    fun multiHopDODAGRoutingTableTest() {
        Configuration.parse("$prefix/multiHopDODAGRoutingTableTest.json")
        val a = Configuration.getNamedDevice("A")
        val aRouter = a.router as RPLRouter
        val b = Configuration.getNamedDevice("B")
        val bRouter = b.router as RPLRouter
        val c = Configuration.getNamedDevice("C")
        val cRouter = c.router as RPLRouter
        val d = Configuration.getNamedDevice("D")
        val dRouter = d.router as RPLRouter
        val e = Configuration.getNamedDevice("E")
        val eRouter = e.router as RPLRouter
        val f = Configuration.getNamedDevice("F")
        val fRouter = f.router as RPLRouter

        val sim = Simulation(Configuration.devices, callback = Logger())
        sim.startSimulation()

        // routing table from A
        assertEquals(5, aRouter.routingTable.destinationCounter)
        assertEquals(b.address, aRouter.routingTable.getNextHop(b.address))
        assertEquals(c.address, aRouter.routingTable.getNextHop(c.address))
        assertEquals(c.address, aRouter.routingTable.getNextHop(d.address))
        assertEquals(c.address, aRouter.routingTable.getNextHop(e.address))
        assertEquals(c.address, aRouter.routingTable.getNextHop(f.address))
        // routing table from B
        assertEquals(0, bRouter.routingTable.destinationCounter)
        assertEquals(a.address, bRouter.routingTable.defaultAddress)
        // routing table from C
        assertEquals(3, cRouter.routingTable.destinationCounter)
        assertEquals(a.address, cRouter.routingTable.defaultAddress)
        assertEquals(d.address, cRouter.routingTable.getNextHop(d.address))
        assertEquals(e.address, cRouter.routingTable.getNextHop(e.address))
        assertEquals(e.address, cRouter.routingTable.getNextHop(f.address))
        // routing table from D
        assertEquals(0, bRouter.routingTable.destinationCounter)
        assertEquals(c.address, dRouter.routingTable.defaultAddress)
        // routing table from E
        assertEquals(1, eRouter.routingTable.destinationCounter)
        assertEquals(c.address, eRouter.routingTable.defaultAddress)
        assertEquals(f.address, eRouter.routingTable.getNextHop(f.address))
        // routing table from F
        assertEquals(0, fRouter.routingTable.destinationCounter)
        assertEquals(e.address, fRouter.routingTable.defaultAddress)
    }

    @Test
    fun starNetworkRoutingTables() {
        Configuration.parse("$prefix/starNetworkRoutingTables.json")
        val starNet = Configuration.randStarNetworks["garageA"]!!
        val root = starNet.dataSink
        val rootRouter = root.router as RPLRouter
        val child1 = starNet.children[0]
        val child1Router = child1.router as RPLRouter

        val sim = Simulation(Configuration.devices, maxClock = 100 ,callback = Logger())
        sim.startSimulation()

        assertEquals(20, rootRouter.routingTable.destinationCounter)
        assertEquals(0, child1Router.routingTable.destinationCounter)
        assertEquals(root.address, rootRouter.routingTable.defaultAddress)
        assertEquals(root.address, child1Router.routingTable.defaultAddress)
        for (child in starNet.children)
            assertEquals(child.address, rootRouter.routingTable.getNextHop(child.address))
    }

    /**
     * DB(A) -> B -> DB(C)
     */
    @Test
    fun getNextDBHops1() {
        Configuration.parse("$prefix/getNextDBHops1.json")
        val a = Configuration.getNamedDevice("A")
        val aRouter = a.router as RPLRouter
        val b = Configuration.getNamedDevice("B")
        val bRouter = b.router as RPLRouter
        val c = Configuration.getNamedDevice("C")

        val sim = Simulation(Configuration.devices, callback = Logger())
        sim.startSimulation()

        // routing table from A
        assertEquals(RoutingTable.notInitialized, aRouter.getNextDatabaseHop(a.address))
        assertEquals(RoutingTable.notInitialized, aRouter.getNextDatabaseHop(b.address))
        assertEquals(c.address, aRouter.getNextDatabaseHop(c.address))
        // routing table from B
        assertEquals(c.address, bRouter.getNextDatabaseHop(c.address))
    }

    /**
     * DB(A) -> B -> DB(C)
     *            -> DB(D)
     */
    @Test
    fun getNextDBHops2() {
        Configuration.parse("$prefix/getNextDBHops2.json")
        val a = Configuration.getNamedDevice("A")
        val aRouter = a.router as RPLRouter
        val b = Configuration.getNamedDevice("B")
        val bRouter = b.router as RPLRouter
        val c = Configuration.getNamedDevice("C")
        val d = Configuration.getNamedDevice("D")

        val sim = Simulation(Configuration.devices, callback = Logger())
        sim.startSimulation()

        // routing table from A
        assertEquals(RoutingTable.notInitialized, aRouter.getNextDatabaseHop(a.address))
        assertEquals(RoutingTable.notInitialized, aRouter.getNextDatabaseHop(b.address))
        assertEquals(c.address, aRouter.getNextDatabaseHop(c.address))
        assertEquals(d.address, aRouter.getNextDatabaseHop(d.address))
        // routing table from B
        assertEquals(c.address, bRouter.getNextDatabaseHop(c.address))
        assertEquals(d.address, bRouter.getNextDatabaseHop(d.address))
    }

    /**
     * DB(A) -> B -> DB(C) -> DB(D) -> E -> DB(F)
     *                              -> DB(G)
     */
    @Test
    fun getNextDBHops3() {
        Configuration.parse("$prefix/getNextDBHops3.json")
        val a = Configuration.getNamedDevice("A")
        val aRouter = a.router as RPLRouter
        val b = Configuration.getNamedDevice("B")
        val bRouter = b.router as RPLRouter
        val c = Configuration.getNamedDevice("C")
        val d = Configuration.getNamedDevice("D")
        val dRouter = d.router as RPLRouter
        val e = Configuration.getNamedDevice("E")
        val f = Configuration.getNamedDevice("F")
        val g = Configuration.getNamedDevice("G")

        val sim = Simulation(Configuration.devices, callback = Logger())
        sim.startSimulation()

        // routing table from A
        assertEquals(RoutingTable.notInitialized, aRouter.getNextDatabaseHop(a.address))
        assertEquals(RoutingTable.notInitialized, aRouter.getNextDatabaseHop(b.address))
        assertEquals(c.address, aRouter.getNextDatabaseHop(c.address))
        assertEquals(c.address, aRouter.getNextDatabaseHop(d.address))
        assertEquals(c.address, aRouter.getNextDatabaseHop(e.address))
        assertEquals(c.address, aRouter.getNextDatabaseHop(f.address))
        assertEquals(c.address, aRouter.getNextDatabaseHop(g.address))
        // routing table from B
        assertEquals(c.address, bRouter.getNextDatabaseHop(c.address))
        assertEquals(c.address, bRouter.getNextDatabaseHop(d.address))
        assertEquals(c.address, bRouter.getNextDatabaseHop(e.address))
        assertEquals(c.address, bRouter.getNextDatabaseHop(f.address))
        assertEquals(c.address, bRouter.getNextDatabaseHop(g.address))
        // routing table from D
        assertEquals(RoutingTable.notInitialized, dRouter.getNextDatabaseHop(e.address))
        assertEquals(f.address, dRouter.getNextDatabaseHop(f.address))
        assertEquals(g.address, dRouter.getNextDatabaseHop(g.address))
    }
}
