package lupos.simulator_iot

import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.routing.RPLRouter
import lupos.simulator_iot.sensor.ParkingSensor
import kotlin.test.*

class RoutingSimulationTest {

    companion object {
        private const val prefix = "RoutingSimulationTest"
    }

    @Test
    fun runSimulationWithoutEntities() {
        Configuration.parse("$prefix/runSimulationWithoutEntities.json")
        val sim: Simulation = Simulation(Configuration.devices, callback = Logger())

        sim.startSimulation()
        assertEquals(0, sim.getCurrentClock())
    }

    @Test
    fun selfMessagesDoNotDelay() {
        Configuration.parse("$prefix/selfMessagesDoNotDelay.json")
        val maxClock: Long = ParkingSensor.dataRateInSeconds.toLong() * 2

        val sim = Simulation(Configuration.devices, maxClock = maxClock, callback = Logger())

        sim.startSimulation()
        assertEquals(maxClock, sim.getCurrentClock())
    }

    @Test
    fun starNetworkIsASimpleDODAG() {
        Configuration.parse("$prefix/starNetworkIsASimpleDODAG.json")
        val starNet = Configuration.randStarNetworks["garageA"]!!
        val parent = starNet.dataSink
        val parentRouter = parent.router as RPLRouter

        val child1 = starNet.children[0]
        val child1Router = child1.router as RPLRouter
        val child2 = starNet.children[1]
        val child2Router = child2.router as RPLRouter
        assertFalse(child1Router.hasParent())
        assertFalse(child2Router.hasParent())

        val sim = Simulation(Configuration.devices, maxClock = 200, callback = Logger())
        sim.startSimulation()

        assertTrue(child1Router.hasParent())
        assertTrue(child2Router.hasParent())
        assertFalse(parentRouter.hasParent())
        assertEquals(RPLRouter.ROOT_RANK, parentRouter.rank)
        assertTrue(child1Router.rank >= RPLRouter.ROOT_RANK)
        assertTrue(child2Router.rank >= RPLRouter.ROOT_RANK)
        assertEquals(parent.address, child1Router.preferredParent.address)
        assertEquals(parent.address, child2Router.preferredParent.address)
        assertEquals(parentRouter.rank, child1Router.preferredParent.rank)
        assertEquals(parentRouter.rank, child2Router.preferredParent.rank)
    }

    @Test
    fun meshToDODAG() {
        Configuration.parse("$prefix/meshToDODAG.json")
        val root = Configuration.getRootDevice()
        val rootRouter = root.router as RPLRouter
        val sim = Simulation(Configuration.devices, callback = Logger())
        sim.startSimulation()

        assertEquals(Configuration.devices.size - 1, rootRouter.routingTable.destinationCounter)
    }

    @Test
    fun upwardRouteForwarding() {
        // Send data from the leaf F to the root A
        Configuration.parse("$prefix/upwardRouteForwarding.json")
        val a = Configuration.getNamedDevice("A")

        val f = Configuration.getNamedDevice("F")

        f.sensor!!.setDataSink(a.address)

        val maxClock: Long = 100
        val numberOfSamples = maxClock / ParkingSensor.dataRateInSeconds

        val sim = Simulation(Configuration.devices, maxClock = maxClock, callback = Logger())
        sim.startSimulation()

        assertEquals(numberOfSamples, a.processedSensorDataPackages)
    }

    @Test
    fun downwardRouteForwarding() {
        // Send data from the root A to the leaf F
        Configuration.parse("$prefix/downwardRouteForwarding.json")
        val a = Configuration.getNamedDevice("A")
        val f = Configuration.getNamedDevice("F")

        a.sensor!!.setDataSink(f.address)

        val maxClock: Long = 100
        val numberOfSamples = maxClock / ParkingSensor.dataRateInSeconds

        val sim = Simulation(Configuration.devices, maxClock = maxClock, callback = Logger())
        sim.startSimulation()

        assertEquals(numberOfSamples, f.processedSensorDataPackages)
    }

    @Test
    fun upAndDownwardRouteForwarding() {
        // Send data from the leaf F to the leaf D
        Configuration.parse("$prefix/upAndDownwardRouteForwarding.json")
        val d = Configuration.getNamedDevice("D")
        val f = Configuration.getNamedDevice("F")

        f.sensor!!.setDataSink(d.address)

        val maxClock: Long = 100
        val numberOfSamples = maxClock / ParkingSensor.dataRateInSeconds

        val sim = Simulation(Configuration.devices, maxClock = maxClock, callback = Logger())
        sim.startSimulation()

        assertEquals(numberOfSamples, d.processedSensorDataPackages)
    }

    @Test
    fun sensorFromStarSendDataOverMesh() {
        // TODO zuerst star root
//        //Send data from the leaf F to the leaf D
//        Configuration.parse("$prefix/sensorFromStarSendDataOverMesh.json")
//        val d = Configuration.getNamedDevice("D")
//        val f = Configuration.getNamedDevice("F")
//
//        f.sensor!!.setDataSink(d.address)
//
//        val maxClock: Long = 100
//        val numberOfSamples = maxClock / ParkingSensor.dataRateInSeconds
//
//        val sim = Simulation(Configuration.devices)
//        sim.setLifeCycleCallback(Logger(sim))
//        sim.setMaximalTime(maxClock)
//        sim.start()
//
//        assertEquals(numberOfSamples, d.processedSensorDataPackages)
    }
}
