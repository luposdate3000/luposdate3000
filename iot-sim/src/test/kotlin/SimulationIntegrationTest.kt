import config.Configuration
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import routing.RPLRouter
import sensor.ParkingSensor

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
        f.sensor!!.setDataSink(a.address)

        val maxClock = 100
        val numberOfSamples = maxClock / ParkingSensor.dataRateInSeconds

        Simulation.start(Configuration.devices, Logger(), maxClock.toLong())
        Assertions.assertEquals(numberOfSamples, a.processedSensorDataPackages)
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
        a.sensor!!.setDataSink(f.address)

        val maxClock = 100
        val numberOfSamples = maxClock / ParkingSensor.dataRateInSeconds

        Simulation.start(Configuration.devices, Logger(), maxClock.toLong())
        Assertions.assertEquals(numberOfSamples, f.processedSensorDataPackages)
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
        f.sensor!!.setDataSink(d.address)

        val maxClock = 100
        val numberOfSamples = maxClock / ParkingSensor.dataRateInSeconds

        Simulation.start(Configuration.devices, Logger(), maxClock.toLong())
        Assertions.assertEquals(numberOfSamples, d.processedSensorDataPackages)
    }

//    @ParameterizedTest
//    @ValueSource(strings = ["sim/join.json"])
//    fun join(fileName: String) {
//        //Send data from the leaf F to the leaf D
//        config.Configuration.parse(fileName)
//        val a = config.Configuration.getNamedDevice("A")
//        val c = config.Configuration.getNamedDevice("C")
//        val d = config.Configuration.getNamedDevice("D")
//        val f = config.Configuration.getNamedDevice("F")
//        val aRouter = a.router as routing.RPLRouter
//        aRouter.root = true
//        f.sensor!!.dataSinkAddress = d.address
//        c.database!!.receiveDatabaseQuery("JOIN 3 5")
//
//        Simulation.start(config.Configuration.devices, Logger(), 100)
//    }


}
