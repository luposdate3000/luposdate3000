import config.Configuration
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import routing.RPLRouter
import routing.RoutingTable

class RoutingTableSimulationTest {

    companion object {
        private const val prefix = "RoutingTableSimulationTest"
    }

    @ParameterizedTest
    @ValueSource(strings = ["$prefix/multiHopDODAGRoutingTableTest.json"])
    fun multiHopDODAGRoutingTableTest(fileName: String) {
        Configuration.parse(fileName)
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

        aRouter.root = true
        Simulation.start(Configuration.devices, Logger())
        //routing table from A
        Assertions.assertEquals(5, aRouter.routingTable.destinationCounter)
        Assertions.assertEquals(b.address, aRouter.routingTable.getNextHop(b.address))
        Assertions.assertEquals(c.address, aRouter.routingTable.getNextHop(c.address))
        Assertions.assertEquals(c.address, aRouter.routingTable.getNextHop(d.address))
        Assertions.assertEquals(c.address, aRouter.routingTable.getNextHop(e.address))
        Assertions.assertEquals(c.address, aRouter.routingTable.getNextHop(f.address))
        //routing table from B
        Assertions.assertEquals(0, bRouter.routingTable.destinationCounter)
        Assertions.assertEquals(a.address, bRouter.routingTable.defaultAddress)
        //routing table from C
        Assertions.assertEquals(3, cRouter.routingTable.destinationCounter)
        Assertions.assertEquals(a.address, cRouter.routingTable.defaultAddress)
        Assertions.assertEquals(d.address, cRouter.routingTable.getNextHop(d.address))
        Assertions.assertEquals(e.address, cRouter.routingTable.getNextHop(e.address))
        Assertions.assertEquals(e.address, cRouter.routingTable.getNextHop(f.address))
        //routing table from D
        Assertions.assertEquals(0, bRouter.routingTable.destinationCounter)
        Assertions.assertEquals(c.address, dRouter.routingTable.defaultAddress)
        //routing table from E
        Assertions.assertEquals(1, eRouter.routingTable.destinationCounter)
        Assertions.assertEquals(c.address, eRouter.routingTable.defaultAddress)
        Assertions.assertEquals(f.address, eRouter.routingTable.getNextHop(f.address))
        //routing table from F
        Assertions.assertEquals(0, fRouter.routingTable.destinationCounter)
        Assertions.assertEquals(e.address, fRouter.routingTable.defaultAddress)
    }

    @ParameterizedTest
    @ValueSource(strings = ["$prefix/starNetworkRoutingTables.json"])
    fun starNetworkRoutingTables(fileName: String) {
        Configuration.parse(fileName)
        val starNet = Configuration.randStarNetworks["garageA"]!!
        val root = starNet.parent
        val rootRouter = root.router as RPLRouter
        rootRouter.root = true
        val child1 = starNet.children[0]
        val child1Router = child1.router as RPLRouter

        Simulation.start(Configuration.devices, Logger())
        Assertions.assertEquals(20, rootRouter.routingTable.destinationCounter)
        Assertions.assertEquals(0, child1Router.routingTable.destinationCounter)
        Assertions.assertEquals(root.address, rootRouter.routingTable.defaultAddress)
        Assertions.assertEquals(root.address, child1Router.routingTable.defaultAddress)
        for(child in starNet.children)
            Assertions.assertEquals(child.address, rootRouter.routingTable.getNextHop(child.address))
    }


    /**
     * DB(A) -> B -> DB(C)
     */
    @ParameterizedTest
    @ValueSource(strings = ["$prefix/getNextDBHops1.json"])
    fun getNextDBHops1(fileName: String) {
        Configuration.parse(fileName)
        val a = Configuration.getNamedDevice("A")
        val aRouter = a.router as RPLRouter
        val b = Configuration.getNamedDevice("B")
        val bRouter = b.router as RPLRouter
        val c = Configuration.getNamedDevice("C")

        aRouter.root = true
        Simulation.start(Configuration.devices, Logger())
        //routing table from A
        Assertions.assertEquals(RoutingTable.notInitialized, aRouter.getNextDatabaseHop(a.address))
        Assertions.assertEquals(RoutingTable.notInitialized, aRouter.getNextDatabaseHop(b.address))
        Assertions.assertEquals(c.address, aRouter.getNextDatabaseHop(c.address))
        //routing table from B
        Assertions.assertEquals(c.address, bRouter.getNextDatabaseHop(c.address))
    }


    /**
     * DB(A) -> B -> DB(C)
     *            -> DB(D)
     */
    @ParameterizedTest
    @ValueSource(strings = ["$prefix/getNextDBHops2.json"])
    fun getNextDBHops2(fileName: String) {
        Configuration.parse(fileName)
        val a = Configuration.getNamedDevice("A")
        val aRouter = a.router as RPLRouter
        val b = Configuration.getNamedDevice("B")
        val bRouter = b.router as RPLRouter
        val c = Configuration.getNamedDevice("C")
        val d = Configuration.getNamedDevice("D")

        aRouter.root = true
        Simulation.start(Configuration.devices, Logger())
        //routing table from A
        Assertions.assertEquals(RoutingTable.notInitialized, aRouter.getNextDatabaseHop(a.address))
        Assertions.assertEquals(RoutingTable.notInitialized, aRouter.getNextDatabaseHop(b.address))
        Assertions.assertEquals(c.address, aRouter.getNextDatabaseHop(c.address))
        Assertions.assertEquals(d.address, aRouter.getNextDatabaseHop(d.address))
        //routing table from B
        Assertions.assertEquals(c.address, bRouter.getNextDatabaseHop(c.address))
        Assertions.assertEquals(d.address, bRouter.getNextDatabaseHop(d.address))
    }

    /**
     * DB(A) -> B -> DB(C) -> DB(D) -> E -> DB(F)
     *                              -> DB(G)
     */
    @ParameterizedTest
    @ValueSource(strings = ["$prefix/getNextDBHops3.json"])
    fun getNextDBHops3(fileName: String) {
        Configuration.parse(fileName)
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


        aRouter.root = true
        Simulation.start(Configuration.devices, Logger())
        //routing table from A
        Assertions.assertEquals(RoutingTable.notInitialized, aRouter.getNextDatabaseHop(a.address))
        Assertions.assertEquals(RoutingTable.notInitialized, aRouter.getNextDatabaseHop(b.address))
        Assertions.assertEquals(c.address, aRouter.getNextDatabaseHop(c.address))
        Assertions.assertEquals(c.address, aRouter.getNextDatabaseHop(d.address))
        Assertions.assertEquals(c.address, aRouter.getNextDatabaseHop(e.address))
        Assertions.assertEquals(c.address, aRouter.getNextDatabaseHop(f.address))
        Assertions.assertEquals(c.address, aRouter.getNextDatabaseHop(g.address))
        //routing table from B
        Assertions.assertEquals(c.address, bRouter.getNextDatabaseHop(c.address))
        Assertions.assertEquals(c.address, bRouter.getNextDatabaseHop(d.address))
        Assertions.assertEquals(c.address, bRouter.getNextDatabaseHop(e.address))
        Assertions.assertEquals(c.address, bRouter.getNextDatabaseHop(f.address))
        Assertions.assertEquals(c.address, bRouter.getNextDatabaseHop(g.address))
        //routing table from D
        Assertions.assertEquals(RoutingTable.notInitialized, dRouter.getNextDatabaseHop(e.address))
        Assertions.assertEquals(f.address, dRouter.getNextDatabaseHop(f.address))
        Assertions.assertEquals(g.address, dRouter.getNextDatabaseHop(g.address))
    }



}