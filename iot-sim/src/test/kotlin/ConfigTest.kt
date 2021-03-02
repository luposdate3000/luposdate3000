import AppEntity
import Config
import LocalizationSensor
import ParkingSensor
import com.javadocmd.simplelatlng.LatLng
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ConfigTest {


    @ParameterizedTest
    @ValueSource(strings = ["config/configEmptyFile.json"])
    fun `parse empty config file`(fileName: String) {
        Config.parse(fileName)
        Assertions.assertTrue(Config.devices.isEmpty())
        Assertions.assertTrue(Config.entities.isEmpty())
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneSimpleDevice.json"])
    fun `one simple device`(fileName: String) {
        Config.parse(fileName)
        val devices = Config.devices
        val deviceName = Config.jsonObjects.fixedDevices[0].name
        val lat = Config.jsonObjects.fixedDevices[0].latitude
        val lon = Config.jsonObjects.fixedDevices[0].longitude
        val location = LatLng(lat, lon)

        Assertions.assertEquals(Config.jsonObjects.fixedDevices.size, devices.size)
        Assertions.assertEquals(deviceName, devices[deviceName]!!.name)
        Assertions.assertEquals(location, devices[deviceName]!!.location)
        Assertions.assertNull(devices[deviceName]!!.application)
        Assertions.assertTrue(devices[deviceName]!!.sensors.isEmpty())
        Assertions.assertTrue(devices[deviceName]!!.powerSupply.isInfinite)
        Assertions.assertEquals(1, Config.entities.size)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneComplexDevice.json"])
    fun `one application device with sensors`(fileName: String) {
        Config.parse(fileName)
        val devices = Config.devices
        val deviceName = Config.jsonObjects.fixedDevices[0].name
        val numSensors = 2
        Assertions.assertTrue(devices[deviceName]!!.application is AppEntity)
        Assertions.assertEquals(numSensors, devices[deviceName]!!.sensors.size)
        Assertions.assertEquals(70.0, devices[deviceName]!!.powerSupply.actualCapacity)
        Assertions.assertFalse(devices[deviceName]!!.powerSupply.isInfinite)
        Assertions.assertEquals(2 + numSensors, Config.entities.size)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneComplexDevice.json"])
    fun `sensors know their device`(fileName: String) {
        Config.parse(fileName)
        val devices = Config.devices
        val deviceName = "Tower1"
        val device = devices[deviceName]!!
        val parkSensor = device.sensors[0] as ParkingSensor
        val locSensor = device.sensors[1] as LocalizationSensor
        Assertions.assertEquals(device, parkSensor.device)
        Assertions.assertEquals(device, locSensor.device)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneComplexDevice.json"])
    fun `sensors get correct values`(fileName: String) {
        Config.parse(fileName)
        val devices = Config.devices
        val deviceName = "Tower1"
        val device = devices[deviceName]!!
        val parkSensor = device.sensors[0] as ParkingSensor
        val locSensor = device.sensors[1] as LocalizationSensor
        Assertions.assertEquals(8, parkSensor.dataRateInSeconds)
        Assertions.assertEquals(device, parkSensor.dataSink)
        Assertions.assertEquals(60, locSensor.dataRateInSeconds)
        Assertions.assertEquals(device, locSensor.dataSink)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneFixedConnection.json"])
    fun `two devices have a connection`(fileName: String) {
        Config.parse(fileName)
        val devices = Config.devices
        val device2Address = "Fog1"
        val device1Address = "Tower1"
        val device1 = devices["Tower1"]!!
        val device2 = devices[device2Address]!!
        val con1 = device1.networkCard.getDirectConnection(device2Address)
        val con2 = device2.networkCard.getDirectConnection(device1Address)
        Assertions.assertTrue(device1.networkCard.hasDirectConnection(device2Address))
        Assertions.assertEquals(-1, con1.dataRateInKbps)
        Assertions.assertEquals("WIRE", con1.protocolName)

        Assertions.assertEquals(con1, con2)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneRandomNetwork.json"])
    fun `one random network`(fileName: String) {
        Config.parse(fileName)
        val devices = Config.devices
        val rootDeviceAddress = "Fog1"
        val rootDevice = devices[rootDeviceAddress]!!
        val nic = rootDevice.networkCard
        val number = 30
        Assertions.assertEquals(number + 1, devices.size)
        for(n in 1 .. number) {
            val otherAddress: String = Config.jsonObjects.randomNetwork[0].name + n
            Assertions.assertTrue(nic.hasDirectConnection(otherAddress))
            val otherNic = devices[otherAddress]!!.networkCard
            Assertions.assertTrue(otherNic.hasDirectConnection(rootDeviceAddress))
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configMultipleDevices.json"])
    fun `multiple fixed and random network`(fileName: String) {
        Config.parse(fileName)
        val devices = Config.devices
        val numGarageA = 501
        val numGarageB = 10002
        val numFixed = 2
        val numSensors = numGarageA + numGarageB
        val expectedEntities = numSensors + numGarageA + numGarageB + numFixed
        Assertions.assertEquals(numFixed + numGarageA + numGarageB, devices.size)
        Assertions.assertEquals(expectedEntities, Config.entities.size)

    }


}
