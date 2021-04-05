import com.javadocmd.simplelatlng.LatLng
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ConfigurationTest {


    @ParameterizedTest
    @ValueSource(strings = ["config/configEmptyFile.json"])
    fun `parse empty config file`(fileName: String) {
        Configuration.parse(fileName)
        Assertions.assertTrue(Configuration.devices.isEmpty())
        Assertions.assertTrue(Configuration.entities.isEmpty())
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneSimpleDevice.json"])
    fun `one simple device`(fileName: String) {
        Configuration.parse(fileName)
        val devices = Configuration.devices
        val deviceName = Configuration.jsonObjects.fixedDevices[0].name
        val lat = Configuration.jsonObjects.fixedDevices[0].latitude
        val lon = Configuration.jsonObjects.fixedDevices[0].longitude
        val location = LatLng(lat, lon)

        Assertions.assertEquals(Configuration.jsonObjects.fixedDevices.size, devices.size)
        Assertions.assertEquals(deviceName, devices[deviceName]!!.name)
        Assertions.assertEquals(location, devices[deviceName]!!.location)
        Assertions.assertNull(devices[deviceName]!!.application)
        Assertions.assertTrue(devices[deviceName]!!.sensors.isEmpty())
        Assertions.assertTrue(devices[deviceName]!!.powerSupply.isInfinite)
        Assertions.assertEquals(1, Configuration.entities.size)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneComplexDevice.json"])
    fun `one application device with sensors`(fileName: String) {
        Configuration.parse(fileName)
        val devices = Configuration.devices
        val deviceName = Configuration.jsonObjects.fixedDevices[0].name
        val numSensors = 1
        Assertions.assertTrue(devices[deviceName]!!.application is DatabaseApp)
        Assertions.assertEquals(numSensors, devices[deviceName]!!.sensors.size)
        Assertions.assertEquals(70.0, devices[deviceName]!!.powerSupply.actualCapacity)
        Assertions.assertFalse(devices[deviceName]!!.powerSupply.isInfinite)
        Assertions.assertEquals(2 + numSensors, Configuration.entities.size)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneComplexDevice.json"])
    fun `sensors know their device`(fileName: String) {
        Configuration.parse(fileName)
        val devices = Configuration.devices
        val deviceName = "Tower1"
        val device = devices[deviceName]!!
        val parkSensor = device.sensors[0] as ParkingSensor
        Assertions.assertEquals(device, parkSensor.device)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneComplexDevice.json"])
    fun `sensors get correct values`(fileName: String) {
        Configuration.parse(fileName)
        val devices = Configuration.devices
        val deviceName = "Tower1"
        val device = devices[deviceName]!!
        val parkSensor = device.sensors[0] as ParkingSensor
        Assertions.assertEquals(8, parkSensor.dataRateInSeconds)
        Assertions.assertEquals(device, parkSensor.dataSink)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneFixedConnection.json"])
    fun `two devices have a connection`(fileName: String) {
        Configuration.parse(fileName)
        val device2Address = "Fog1"
        val device1Address = "Tower1"
        val param1 = Configuration.graph.getEdge(device1Address, device2Address)
        val param2 = Configuration.graph.getEdge(device2Address, device1Address)
        Assertions.assertNotNull(param1)
        Assertions.assertNotNull(param2)
        Assertions.assertEquals(-1, param1!!.dataRateInKbps)
        Assertions.assertEquals("WIRE", param2!!.protocolName)
        Assertions.assertEquals(param1, param2)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneRandomNetwork.json"])
    fun `count number of devices in random network`(fileName: String) {
        Configuration.parse(fileName)
        val devices = Configuration.devices
        val randomNetwork = Configuration.jsonObjects.randomNetwork[0]
        val numberOfFogDevice = 1
        val numberOfEdgeDevice = randomNetwork.number
        val numberOfSensorsPerDevice = randomNetwork.sensorsPerDevice.number
        val numberOfSensorDevice = numberOfEdgeDevice * numberOfSensorsPerDevice
        val totalNumber = numberOfFogDevice + numberOfEdgeDevice + numberOfSensorDevice
        Assertions.assertEquals(totalNumber, devices.size)

    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneRandomNetwork.json"])
    fun `count number of links in random network`(fileName: String) {
        Configuration.parse(fileName)
        val randomNetwork = Configuration.jsonObjects.randomNetwork[0]
        val numberOfEdgeDevice = randomNetwork.number
        val numberOfSensorsPerDevice = randomNetwork.sensorsPerDevice.number
        val totalNumberOfLinks = numberOfEdgeDevice * numberOfSensorsPerDevice

        Assertions.assertEquals(totalNumberOfLinks, Configuration.graph.getEdgeCount())
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configMultipleDevices.json"])
    fun `multiple fixed and random network`(fileName: String) {
        Configuration.parse(fileName)
        val devices = Configuration.devices
        val networkA = Configuration.jsonObjects.randomNetwork[0]
        val networkB = Configuration.jsonObjects.randomNetwork[1]

        val numGarageA = networkA.number
        val numGarageASensors = networkA.sensorsPerDevice.number * numGarageA
        val expectedEntitiesGarageA = numGarageA + numGarageASensors + 1

        val numGarageB = networkB.number
        val numGarageBSensors = networkB.sensorsPerDevice.number * numGarageB
        val expectedEntitiesGarageB = numGarageB + numGarageBSensors + 1

        val total = expectedEntitiesGarageA + expectedEntitiesGarageB
        Assertions.assertEquals(total, devices.size)

    }


}
