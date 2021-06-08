package lupos.iot_sim

import lupos.des_core.Simulation
import lupos.iot_sim.config.Configuration
import org.junit.jupiter.api.Assertions

import lupos.iot_sim.sensor.ParkingSensor
import kotlin.test.*


class DatabaseSimulationTest {

    companion object {
        private const val prefix = "DatabaseSimulationTest"
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
        val a = Configuration.getNamedDevice("A")
        val g = Configuration.getNamedDevice("G")
        a.sensor!!.setDataSink(g.address)

        val maxClock: Long = 100
        val numberOfSamples = maxClock / ParkingSensor.dataRateInSeconds

        val sim = Simulation(Configuration.devices)
        sim.setMaximalTime(maxClock)
        sim.setLifeCycleCallback(Logger(sim))
        sim.start()

        Assertions.assertEquals(numberOfSamples, g.processedSensorDataPackages)
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
//        val aRouter = a.router as RPLRouter
//        aRouter.root = true
//        val g = Configuration.getNamedDevice("G")
//        a.sensor!!.setDataSink(g.address)
//
//        val maxClock = 100
//        val numberOfSamples = maxClock / ParkingSensor.dataRateInSeconds
//
//        Simulation.start(Configuration.devices, Logger(), maxClock.toLong())
//        Assertions.assertEquals(numberOfSamples, g.processedSensorDataPackages)
//    }



}
