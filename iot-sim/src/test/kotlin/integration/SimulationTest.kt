package integration

import Configuration
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import Simulation

class SimulationTest {


    @ParameterizedTest
    @ValueSource(strings = ["sim/runSimulationWithoutEntities.json"])
    fun runSimulationWithoutEntities(fileName: String) {
        Configuration.parse(fileName)
        Simulation.initialize(Configuration.devices)
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(0, endClock)
    }

    @ParameterizedTest
    @ValueSource(strings = ["sim/selfMessagesDoNotDelay.json"])
    fun selfMessagesDoNotDelay(fileName: String) {
        Configuration.parse(fileName)
        val maxClock: Long = ParkingSensor.dataRateInSeconds.toLong() * 2
        Simulation.initialize(Configuration.devices, maxClock)
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(maxClock, endClock)
    }

    @ParameterizedTest
    @ValueSource(strings = ["sim/starNetworkIsASimpleDODAG.json"])
    fun starNetworkIsASimpleDODAG(fileName: String) {
        Configuration.parse(fileName)
        val starNet = Configuration.randStarNetworks["garageA"]!!
        val parent = starNet.parent
        val child1 = starNet.childs[0]
        val child2 = starNet.childs[1]

        Assertions.assertFalse(child1.hasParent())
        Assertions.assertFalse(child2.hasParent())

        Simulation.initialize(Configuration.devices)
        Simulation.runSimulation()

        Assertions.assertTrue(child1.hasParent())
        Assertions.assertTrue(child2.hasParent())
        Assertions.assertEquals(0, parent.rank)
        Assertions.assertEquals(1, child1.rank)
        Assertions.assertEquals(1, child2.rank)
        Assertions.assertEquals(parent.address, child1.preferredParent.address)
        Assertions.assertEquals(parent.address, child2.preferredParent.address)
        Assertions.assertEquals(parent.rank, child1.preferredParent.rank)
        Assertions.assertEquals(parent.rank, child2.preferredParent.rank)
    }


}
