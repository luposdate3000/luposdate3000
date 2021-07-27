package lupos.simulator_iot.unit

import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.models.routing.RPL
import lupos.simulator_iot.utils.FilePaths
import lupos.simulator_iot.utils.TimeUtils
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RoutingSimulationTest {

    companion object {
        private const val prefix = "${FilePaths.testResource}/routingSimulationTest"
    }

    @Test
    fun runSimulationWithoutEntities() {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("$prefix/runSimulationWithoutEntities.json")
        val config = simRun.parseJsonObjects(json)
        simRun.startSimulation(config)

        assertEquals(0, simRun.getCurrentSimulationClock())
    }

    @Test
    fun starNetworkIsASimpleDODAG() {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("$prefix/starNetworkIsASimpleDODAG.json")
        val config = simRun.parseJsonObjects(json)

        val starNet = config.randStarNetworks["garageA"]!!
        val parent = starNet.root
        val parentRouter = parent.router as RPL

        val child1 = starNet.children[0]
        val child1Router = child1.router as RPL
        val child2 = starNet.children[1]
        val child2Router = child2.router as RPL
        assertFalse(child1Router.hasParent())
        assertFalse(child2Router.hasParent())

        simRun.simMaxClock = TimeUtils.toNanoSec(200)
        simRun.startSimulation(config)

        assertTrue(child1Router.hasParent())
        assertTrue(child2Router.hasParent())
        assertFalse(parentRouter.hasParent())
        assertEquals(RPL.ROOT_RANK, parentRouter.rank)
        assertTrue(child1Router.rank >= RPL.ROOT_RANK)
        assertTrue(child2Router.rank >= RPL.ROOT_RANK)
        assertEquals(parent.address, child1Router.preferredParent.address)
        assertEquals(parent.address, child2Router.preferredParent.address)
        assertEquals(parentRouter.rank, child1Router.preferredParent.rank)
        assertEquals(parentRouter.rank, child2Router.preferredParent.rank)
    }

    @Test
    fun meshToDODAG() {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("$prefix/meshToDODAG.json")
        val config = simRun.parseJsonObjects(json)
        val root = config.getRootDevice()
        val rootRouter = root.router as RPL
        simRun.startSimulation(config)

        assertEquals(config.getNumberOfDevices() - 1, rootRouter.routingTable.destinationCounter)
    }

    @Test
    fun upwardRouteForwarding() {
        // Send data from the leaf F to the root A
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("$prefix/upwardRouteForwarding.json")
        val config = simRun.parseJsonObjects(json)
        simRun.simMaxClock = TimeUtils.toNanoSec(300)
        simRun.startSimulation(config)

        val a = config.getDeviceByName("A")
        assertEquals(6, a.processedSensorDataPackages)
    }

    @Test
    fun downwardRouteForwarding() {
        // Send data from the root A to the leaf F
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("$prefix/downwardRouteForwarding.json")
        val config = simRun.parseJsonObjects(json)
        simRun.simMaxClock = TimeUtils.toNanoSec(200)
        simRun.startSimulation(config)

        val f = config.getDeviceByName("F")
        assertEquals(4, f.processedSensorDataPackages)
    }

    @Test
    fun upAndDownwardRouteForwarding() {
        // Send data from the leaf F to the leaf D
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("$prefix/upAndDownwardRouteForwarding.json")
        val config = simRun.parseJsonObjects(json)
        simRun.simMaxClock = TimeUtils.toNanoSec(800)
        simRun.startSimulation(config)

        val d = config.getDeviceByName("D")
        assertEquals(3, d.processedSensorDataPackages)
    }

    @Test
    fun sendQueries() {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("$prefix/sendQueries.json")
        val config = simRun.parseJsonObjects(json)
        simRun.simMaxClock = TimeUtils.toNanoSec(800)
        simRun.startSimulation(config)

        val querySender = config.querySenders[0]
        val expectedTimeSec = querySender.maxNumberOfQueries * querySender.sendRateInSec + querySender.startClockInSec
        assertEquals(TimeUtils.toNanoSec(expectedTimeSec), simRun.getCurrentSimulationClock())
    }

    @Test
    fun sendLimitedNumberOfQueries() {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("$prefix/sendLimitedNumberOfQueries.json")
        val config = simRun.parseJsonObjects(json)
        simRun.startSimulation(config)

        val querySender = config.querySenders[0]
        assertEquals(79, querySender.queryCounter)
    }

    @Test
    fun sensorFromStarSendOverMesh() {
        // Send data from one Sensor over Mesh to fixed node
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("$prefix/sensorFromStarSendOverMesh.json")
        val config = simRun.parseJsonObjects(json)

        val fog = config.getDeviceByName("Fog")
        val starRoot = config.randStarNetworks.getValue("1")
        val child = starRoot.children[0]
        assertTrue(child.linkManager.hasLink(starRoot.root))

        simRun.startSimulation(config)
        assertEquals(4, fog.processedSensorDataPackages)
    }

    @Test
    fun sensorsFromStarSendOverFixedLinks() {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("$prefix/sensorsFromStarSendOverFixedLinks.json")
        val config = simRun.parseJsonObjects(json)

        val fog = config.getDeviceByName("DODAG ROOT")

        simRun.startSimulation(config)
        assertEquals(5, fog.processedSensorDataPackages)
    }
}
