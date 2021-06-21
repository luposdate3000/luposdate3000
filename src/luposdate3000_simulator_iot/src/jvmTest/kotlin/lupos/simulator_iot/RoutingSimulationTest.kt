package lupos.simulator_iot

import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.net.routing.RPLRouter
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RoutingSimulationTest {

    companion object {
        private const val prefix = "RoutingSimulationTest"
    }

    @Test
    fun runSimulationWithoutEntities() {
        Configuration.parse("$prefix/runSimulationWithoutEntities.json")
        val sim: Simulation = Simulation(Configuration.devices, callback = IoTSimLifeCycle)

        sim.startSimulation()
        assertEquals(0, sim.getCurrentClock())
    }



    @Test
    fun starNetworkIsASimpleDODAG() {
        Configuration.parse("$prefix/starNetworkIsASimpleDODAG.json")
        val starNet = Configuration.randStarNetworks["garageA"]!!
        val parent = starNet.root
        val parentRouter = parent.router as RPLRouter

        val child1 = starNet.children[0]
        val child1Router = child1.router as RPLRouter
        val child2 = starNet.children[1]
        val child2Router = child2.router as RPLRouter
        assertFalse(child1Router.hasParent())
        assertFalse(child2Router.hasParent())

        val sim = Simulation(Configuration.devices, maxClock = 200, callback = IoTSimLifeCycle)
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
        val sim = Simulation(Configuration.devices, callback = IoTSimLifeCycle)
        sim.startSimulation()

        assertEquals(Configuration.devices.size - 1, rootRouter.routingTable.destinationCounter)
    }

    @Test
    fun upwardRouteForwarding() {
        // Send data from the leaf F to the root A
        Configuration.parse("$prefix/upwardRouteForwarding.json")
        val a = Configuration.getNamedDevice("A")

        val maxClock: Long = 300 * 1000
        val sim = Simulation(Configuration.devices, maxClock = maxClock, callback = IoTSimLifeCycle)
        sim.startSimulation()

        assertEquals(6, a.processedSensorDataPackages)
    }

    @Test
    fun downwardRouteForwarding() {
        // Send data from the root A to the leaf F
        Configuration.parse("$prefix/downwardRouteForwarding.json")
        val f = Configuration.getNamedDevice("F")


        val maxClock: Long = 200 * 1000
        val sim = Simulation(Configuration.devices, maxClock = maxClock, callback = IoTSimLifeCycle)
        sim.startSimulation()

        assertEquals(4, f.processedSensorDataPackages)
    }

    @Test
    fun upAndDownwardRouteForwarding() {
        // Send data from the leaf F to the leaf D
        Configuration.parse("$prefix/upAndDownwardRouteForwarding.json")
        val d = Configuration.getNamedDevice("D")

        val maxClock: Long = 800 * 1000
        val sim = Simulation(Configuration.devices, maxClock = maxClock, callback = IoTSimLifeCycle)
        sim.startSimulation()

        assertEquals(3, d.processedSensorDataPackages)
    }

    @Test
    fun sendQueries() {
        Configuration.parse("$prefix/sendQueries.json")
        val querySender = Configuration.querySenders[0]
        val expectedTimeSec = querySender.maxNumberOfQueries * querySender.sendRateInSec
        val expectedTimeMillis = expectedTimeSec * 1000
        val sim = Simulation(Configuration.getEntities(), steadyClock = 0, callback = IoTSimLifeCycle)
        sim.startSimulation()
        assertEquals(expectedTimeMillis.toLong(), sim.getCurrentClock())
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
//        sim.setLifeCycleCallback(IoTSimLifeCycle(sim))
//        sim.setMaximalTime(maxClock)
//        sim.start()
//
//        assertEquals(numberOfSamples, d.processedSensorDataPackages)
    }
}
