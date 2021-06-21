package lupos.simulator_iot

import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration
import kotlin.test.Test
import kotlin.test.assertEquals

class DatabaseSimulationTest {

    companion object {
        private const val prefix = "$testResource\\DatabaseSimulationTest"
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

        val maxClock: Long = 10000000
        val sim = Simulation(Configuration.devices, maxClock = maxClock, callback = IoTSimLifeCycle)
        sim.startSimulation()

        assertEquals(8, g.processedSensorDataPackages)
    }

//    /**
//     * DB(A) -> B -> DB(C) -> DB(D) -> E -> DB(F)
//     *                              -> DB(G)
//     *
//     * Send Data from root A to the leaf G and save it there.
//     * Then A receives a query "Select * From G".
//     * Afterwards, A should have stored the same data as G
//     */
//    onTransient im Lifecycle?
//    @ParameterizedTest
//    @ValueSource(strings = ["$prefix/saveParkingSamplesInDummyTripleStore.json"])
//    fun queryTest1(fileName: String) {
//        Configuration.parse(fileName)
//        val a = Configuration.getNamedDevice("A")
//        val aRouter = a.router as RPL
//        aRouter.root = true
//        val g = Configuration.getNamedDevice("G")
//        a.sensor!!.setDataSink(g.address)
//
//        val maxClock = 100
//        val numberOfSamples = maxClock / ParkingSensor.dataRateInSeconds
//
//        Simulation.start(Configuration.devices, IoTSimLifeCycle(), maxClock.toLong())
//        assertEquals(numberOfSamples, g.processedSensorDataPackages)
//    }
}
