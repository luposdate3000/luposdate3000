package integration;

import com.javadocmd.simplelatlng.LatLng
import config.ConfigParser
import iot.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import simulation.Entity
import simulation.Simulation

class SimulationConfigTest {


    @ParameterizedTest
    @ValueSource(strings = ["sim/EmptyFile.json"])
    fun `run simulation without entities`(fileName: String) {
        ConfigParser.parse(fileName)
        Simulation.initialize(ConfigParser.entities)
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(0, endClock)
    }

    @ParameterizedTest
    @ValueSource(strings = ["sim/OneDeviceWithParkingSensor.json"])
    fun `message to own device do not delay`(fileName: String) {
        ConfigParser.parse(fileName)
        val sensorDataRate = ConfigParser.jsonObjects.sensorType[0].dataRateInSeconds
        val maxClock: Long = sensorDataRate.toLong() * 2
        Simulation.initialize(ConfigParser.entities, maxClock)
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(maxClock, endClock)
    }

    @ParameterizedTest
    @ValueSource(strings = ["sim/OneRandomNetwork.json"])
    fun `message to other connected device do delay`(fileName: String) {
        ConfigParser.parse(fileName)
        val sensorDataRate = ConfigParser.jsonObjects.sensorType[0].dataRateInSeconds
        val sendingDevice = ConfigParser.devices["garageA1"]!!
        val receivingDevice = sendingDevice.networkCard.connections[0].destination
        val delay = sendingDevice.networkCard.getNetworkDelay(receivingDevice)

        val maxClock: Long = sensorDataRate.toLong() + delay
        Simulation.initialize(ConfigParser.entities, maxClock)
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(maxClock, endClock)
    }

}
