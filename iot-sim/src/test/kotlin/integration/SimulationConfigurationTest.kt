package integration

import Configuration
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import Simulation

class SimulationConfigurationTest {


    @ParameterizedTest
    @ValueSource(strings = ["sim/EmptyFile.json"])
    fun `run simulation without entities`(fileName: String) {
        Configuration.parse(fileName)
        Simulation.initialize(Configuration.entities)
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(0, endClock)
    }

    @ParameterizedTest
    @ValueSource(strings = ["sim/OneDeviceWithParkingSensor.json"])
    fun `message to own device do not delay`(fileName: String) {
        Configuration.parse(fileName)
        val maxClock: Long = ParkingSensor.dataRateInSeconds.toLong() * 2
        Simulation.initialize(Configuration.entities, maxClock)
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(maxClock, endClock)
    }


/*    @ParameterizedTest
    @ValueSource(strings = ["sim/OneRandomNetwork.json"])
    fun `wsn gateway start flooding`(fileName: String) {
        Configuration.parse(fileName)
        val randomStarNetwork = Configuration.jsonObjects.randomStarNetwork[0]
        val gateway = Configuration.devices[randomStarNetwork.dataSink]!!
        Simulation.initialize(Configuration.entities)
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(0, gateway.rank)

        //Am besten einen Graph zeichnen
        //Einmal alle Links und nach der Simulation nur den DODAG
        //die Anzahl der Links ist kleiner, jeder hat nur 1 Eltern
        //Kinder haben einen höheren Rang
        //teste zuerst nur mit einem Knoten
        //dann mit zwei, bevorzuge kürzerer Wege (weil LLN)
    }*/

}
