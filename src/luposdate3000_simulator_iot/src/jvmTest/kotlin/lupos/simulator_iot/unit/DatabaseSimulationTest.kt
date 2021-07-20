package lupos.simulator_iot.unit

import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.utils.FilePaths
import kotlin.test.Test
import kotlin.test.assertEquals

class DatabaseSimulationTest {

    companion object {
        private const val prefix = "${FilePaths.testResource}/databaseSimulationTest"
    }

    /**
     * DB(A) -> B -> DB(C) -> DB(D) -> E -> DB(F)
     *                              -> DB(G)
     *
     * Send Data from root A to the leaf G and save it there.
     */
    @Test
    fun saveParkingSamplesInDummyTripleStore() {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("$prefix/saveParkingSamplesInDummyTripleStore.json")
        val config = simRun.parseJsonObjects(json)
        val g = config.getDeviceByName("G")

        simRun.simMaxClock = 100000000
        simRun.startSimulation(config)

        assertEquals(0, g.processedSensorDataPackages)
    }
}
