import config.Configuration
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import routing.RPLRouter
import sensor.ParkingSensor

class RoutingSimulationTest {

    companion object {
        private const val prefix = "RoutingSimulationTest"
    }

    @ParameterizedTest
    @ValueSource(strings = ["$prefix/runSimulationWithoutEntities.json"])
    fun runSimulationWithoutEntities(fileName: String) {
        Configuration.parse(fileName)
        val sim = Simulation(Configuration.devices)
        sim.setLifeCycleCallback(Logger(sim))
        sim.start()
        Assertions.assertEquals(0, sim.currentClock)
    }

    @ParameterizedTest
    @ValueSource(strings = ["$prefix/selfMessagesDoNotDelay.json"])
    fun selfMessagesDoNotDelay(fileName: String) {
        Configuration.parse(fileName)
        val maxClock: Long = ParkingSensor.dataRateInSeconds.toLong() * 2

        val sim = Simulation(Configuration.devices)
        sim.setLifeCycleCallback(Logger(sim))
        sim.setMaximalTime(maxClock)
        sim.start()

        Assertions.assertEquals(maxClock, sim.currentClock)
    }

    @ParameterizedTest
    @ValueSource(strings = ["$prefix/starNetworkIsASimpleDODAG.json"])
    fun starNetworkIsASimpleDODAG(fileName: String) {
        Configuration.parse(fileName)
        val starNet = Configuration.randStarNetworks["garageA"]!!
        val parent = starNet.parent
        val parentRouter = parent.router as RPLRouter

        val child1 = starNet.children[0]
        val child1Router = child1.router as RPLRouter
        val child2 = starNet.children[1]
        val child2Router = child2.router as RPLRouter
        Assertions.assertFalse(child1Router.hasParent())
        Assertions.assertFalse(child2Router.hasParent())

        val sim = Simulation(Configuration.devices)
        sim.setLifeCycleCallback(Logger(sim))
        sim.start()

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
    @ValueSource(strings = ["$prefix/meshToDODAG.json"])
    fun meshToDODAG(fileName: String) {
        Configuration.parse(fileName)
        val root = Configuration.getRootDevice()
        val rootRouter = root.router as RPLRouter
        val sim = Simulation(Configuration.devices)
        sim.setLifeCycleCallback(Logger(sim))
        sim.start()

        Assertions.assertEquals(Configuration.devices.size - 1, rootRouter.routingTable.destinationCounter)
    }

    @ParameterizedTest
    @ValueSource(strings = ["$prefix/upwardRouteForwarding.json"])
    fun upwardRouteForwarding(fileName: String) {
        //Send data from the leaf F to the root A
        Configuration.parse(fileName)
        val a = Configuration.getNamedDevice("A")

        val f = Configuration.getNamedDevice("F")

        f.sensor!!.setDataSink(a.address)

        val maxClock: Long = 100
        val numberOfSamples = maxClock / ParkingSensor.dataRateInSeconds

        val sim = Simulation(Configuration.devices)
        sim.setLifeCycleCallback(Logger(sim))
        sim.setMaximalTime(maxClock)
        sim.start()

        Assertions.assertEquals(numberOfSamples, a.processedSensorDataPackages)
    }

    @ParameterizedTest
    @ValueSource(strings = ["$prefix/downwardRouteForwarding.json"])
    fun downwardRouteForwarding(fileName: String) {
        //Send data from the root A to the leaf F
        Configuration.parse(fileName)
        val a = Configuration.getNamedDevice("A")
        val f = Configuration.getNamedDevice("F")

        a.sensor!!.setDataSink(f.address)

        val maxClock: Long = 100
        val numberOfSamples = maxClock / ParkingSensor.dataRateInSeconds

        val sim = Simulation(Configuration.devices)
        sim.setLifeCycleCallback(Logger(sim))
        sim.setMaximalTime(maxClock)
        sim.start()

        Assertions.assertEquals(numberOfSamples, f.processedSensorDataPackages)
    }

    @ParameterizedTest
    @ValueSource(strings = ["$prefix/upAndDownwardRouteForwarding.json"])
    fun upAndDownwardRouteForwarding(fileName: String) {
        //Send data from the leaf F to the leaf D
        Configuration.parse(fileName)
        val d = Configuration.getNamedDevice("D")
        val f = Configuration.getNamedDevice("F")

        f.sensor!!.setDataSink(d.address)

        val maxClock: Long = 100
        val numberOfSamples = maxClock / ParkingSensor.dataRateInSeconds

        val sim = Simulation(Configuration.devices)
        sim.setLifeCycleCallback(Logger(sim))
        sim.setMaximalTime(maxClock)
        sim.start()

        Assertions.assertEquals(numberOfSamples, d.processedSensorDataPackages)
    }

}
