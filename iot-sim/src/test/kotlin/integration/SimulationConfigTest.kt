package integration

import Config
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import Simulation

class SimulationConfigTest {


    @ParameterizedTest
    @ValueSource(strings = ["sim/EmptyFile.json"])
    fun `run simulation without entities`(fileName: String) {
        Config.parse(fileName)
        Simulation.initialize(Config.entities)
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(0, endClock)
    }

    @ParameterizedTest
    @ValueSource(strings = ["sim/OneDeviceWithParkingSensor.json"])
    fun `message to own device do not delay`(fileName: String) {
        Config.parse(fileName)
        val sensorDataRate = Config.jsonObjects.sensorType[0].dataRateInSeconds
        val maxClock: Long = sensorDataRate.toLong() * 2
        Simulation.initialize(Config.entities, maxClock)
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(maxClock, endClock)
    }

    @ParameterizedTest
    @ValueSource(strings = ["sim/OneRandomNetwork.json"])
    fun `message to other connected device do delay`(fileName: String) {
        Config.parse(fileName)
        val sensorDataRate = Config.jsonObjects.sensorType[0].dataRateInSeconds
        val sendingDeviceAddress = Config.jsonObjects.randomNetwork[0].name + "1"
        val sendingDevice = Config.devices[sendingDeviceAddress]!!
        val receivingDeviceAddress = Config.jsonObjects.randomNetwork[0].dataSink
        val receivingDevice = Config.devices[receivingDeviceAddress]!!
        val delay = sendingDevice.networkCard.getNetworkDelay(receivingDevice)

        val maxClock: Long = sensorDataRate.toLong() + delay
        Simulation.initialize(Config.entities, maxClock)
        val endClock = Simulation.runSimulation()
        Assertions.assertEquals(maxClock, endClock)
    }

}
