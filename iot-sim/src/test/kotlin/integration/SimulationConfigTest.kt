package integration;

import config.ConfigParser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
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
        val sendingDeviceAddress = ConfigParser.jsonObjects.randomNetwork[0].name + "1"
        val sendingDevice = ConfigParser.devices[sendingDeviceAddress]!!
        val receivingDeviceAddress = ConfigParser.jsonObjects.randomNetwork[0].dataSink
        val receivingDevice = ConfigParser.devices[receivingDeviceAddress]!!
        val delay = sendingDevice.networkCard.getNetworkDelay(receivingDevice)

        val maxClock: Long = sensorDataRate.toLong() + delay
        Simulation.initialize(ConfigParser.entities, maxClock)
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(maxClock, endClock)
    }

}
