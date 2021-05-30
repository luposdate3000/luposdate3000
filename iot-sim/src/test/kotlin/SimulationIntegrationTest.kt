import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SimulationIntegrationTest {


    @ParameterizedTest
    @ValueSource(strings = ["sim/runSimulationWithoutEntities.json"])
    fun runSimulationWithoutEntities(fileName: String) {
        Configuration.parse(fileName)
        val endClock = Simulation.start(Configuration.devices, Logger())
        Assertions.assertEquals(0, endClock)
    }

    @ParameterizedTest
    @ValueSource(strings = ["sim/selfMessagesDoNotDelay.json"])
    fun selfMessagesDoNotDelay(fileName: String) {
        Configuration.parse(fileName)
        val maxClock: Long = ParkingSensor.dataRateInSeconds.toLong() * 2
        val endClock = Simulation.start(Configuration.devices, Logger(), maxClock)
        Assertions.assertEquals(maxClock, endClock)
    }

    @ParameterizedTest
    @ValueSource(strings = ["sim/starNetworkIsASimpleDODAG.json"])
    fun starNetworkIsASimpleDODAG(fileName: String) {
        Configuration.parse(fileName)
        val starNet = Configuration.randStarNetworks["garageA"]!!
        val parent = starNet.parent
        val parentRouter = parent.router as RPLRouter
        parentRouter.root = true
        val child1 = starNet.children[0]
        val child1Router = child1.router as RPLRouter
        val child2 = starNet.children[1]
        val child2Router = child2.router as RPLRouter
        Assertions.assertFalse(child1Router.hasParent())
        Assertions.assertFalse(child2Router.hasParent())
        Simulation.start(Configuration.devices, Logger())

        Assertions.assertTrue(child1Router.hasParent())
        Assertions.assertTrue(child2Router.hasParent())
        Assertions.assertFalse(parentRouter.hasParent())
        Assertions.assertEquals(RPLRouter.ROOT_RANK, parentRouter.rank)
        Assertions.assertTrue(child1Router.rank >= RPLRouter.ROOT_RANK)
        Assertions.assertTrue(child2Router.rank >= RPLRouter.ROOT_RANK)
        Assertions.assertEquals(parent.address, child1Router.preferredParent.address)
        Assertions.assertEquals(parent.address, child2Router.preferredParent.address)
        Assertions.assertEquals(parentRouter.rank, child1Router.preferredParent.rank)
        Assertions.assertEquals(parentRouter.rank, child2Router.preferredParent.rank)
    }


    @ParameterizedTest
    @ValueSource(strings = ["sim/starNetworkRoutingTables.json"])
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

    @ParameterizedTest
    @ValueSource(strings = ["sim/multiHopDODAGRoutingTableTest.json"])
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
    @ValueSource(strings = ["sim/meshToDODAG.json"])
    fun meshToDODAG(fileName: String) {
        Configuration.parse(fileName)
        val root = Configuration.randMeshNetworks["meshA"]!!.mesh[0][0]
        val rootRouter = root.router as RPLRouter
        rootRouter.root = true
        Simulation.start(Configuration.devices, Logger())

        Assertions.assertEquals(Configuration.devices.size - 1, rootRouter.routingTable.destinationCounter)
    }

    @ParameterizedTest
    @ValueSource(strings = ["sim/upwardRouteForwarding.json"])
    fun upwardRouteForwarding(fileName: String) {
        //Send data from the leaf F to the root A
        Configuration.parse(fileName)
        val a = Configuration.getNamedDevice("A")
        val aRouter = a.router as RPLRouter
        val f = Configuration.getNamedDevice("F")

        aRouter.root = true
        f.sensor!!.dataSinkAddress = a.address

        Simulation.start(Configuration.devices, Logger(), 100)
    }

    @ParameterizedTest
    @ValueSource(strings = ["sim/downwardRouteForwarding.json"])
    fun downwardRouteForwarding(fileName: String) {
        //Send data from the root A to the leaf F
        Configuration.parse(fileName)
        val a = Configuration.getNamedDevice("A")
        val f = Configuration.getNamedDevice("F")
        val aRouter = a.router as RPLRouter

        aRouter.root = true
        a.sensor!!.dataSinkAddress = f.address

        Simulation.start(Configuration.devices, Logger(), 100)
    }

    @ParameterizedTest
    @ValueSource(strings = ["sim/upAndDownwardRouteForwarding.json"])
    fun upAndDownwardRouteForwarding(fileName: String) {
        //Send data from the leaf F to the leaf D
        Configuration.parse(fileName)
        val a = Configuration.getNamedDevice("A")
        val d = Configuration.getNamedDevice("D")
        val f = Configuration.getNamedDevice("F")
        val aRouter = a.router as RPLRouter

        aRouter.root = true
        f.sensor!!.dataSinkAddress = d.address

        Simulation.start(Configuration.devices, Logger(), 100)
    }

//    @ParameterizedTest
//    @ValueSource(strings = ["sim/join.json"])
//    fun join(fileName: String) {
//        //Send data from the leaf F to the leaf D
//        Configuration.parse(fileName)
//        val a = Configuration.getNamedDevice("A")
//        val c = Configuration.getNamedDevice("C")
//        val d = Configuration.getNamedDevice("D")
//        val f = Configuration.getNamedDevice("F")
//        val aRouter = a.router as RPLRouter
//        aRouter.root = true
//        f.sensor!!.dataSinkAddress = d.address
//        c.database!!.receiveDatabaseQuery("JOIN 3 5")
//
//        Simulation.start(Configuration.devices, Logger(), 100)
//    }


}
