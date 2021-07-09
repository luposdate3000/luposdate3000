package lupos.simulator_iot

import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.log.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class DatabaseSimulationTest {

    companion object {
        private const val prefix = "${FilePaths.testResource}/DatabaseSimulationTest"
    }

    /**
     * DB(A) -> B -> DB(C) -> DB(D) -> E -> DB(F)
     *                              -> DB(G)
     *
     * Send Data from root A to the leaf G and save it there.
     */
    @Test
    fun saveParkingSamplesInDummyTripleStore() {
        Configuration.parse("$prefix/saveParkingSamplesInDummyTripleStore.json")
        val g = Configuration.getNamedDevice("G")

        val maxClock: Long = 100000000
        val sim = Simulation(Configuration.devices, maxClock = maxClock, callback = Logger)
        sim.startSimulation()

        assertEquals(8, g.processedSensorDataPackages)
    }
}
