package config;

import com.javadocmd.simplelatlng.LatLng
import iot.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import simulation.Entity

class ConfigParserTest {


    @ParameterizedTest
    @ValueSource(strings = ["configEmptyFile.json"])
    fun `parse empty config file`(fileName: String) {
        val devices: Map<String, Device> = ConfigParser.parse(fileName)
        Assertions.assertTrue(devices.isEmpty())
    }

    @ParameterizedTest
    @ValueSource(strings = ["configOneSimpleDevice.json"])
    fun `one simple device`(fileName: String) {
        val devices: Map<String, Device> = ConfigParser.parse(fileName)
        val deviceName = "Tower1"
        val location = LatLng(3.0, 3.0)

        Assertions.assertEquals(1, devices.size)
        Assertions.assertEquals(deviceName, devices[deviceName]!!.name)
        Assertions.assertEquals(location, devices[deviceName]!!.location)
        Assertions.assertTrue(devices[deviceName]!!.connections.isEmpty())
        Assertions.assertTrue(devices[deviceName]!!.application is NoAppEntity)
        Assertions.assertTrue(devices[deviceName]!!.sensors.isEmpty())
        Assertions.assertTrue(devices[deviceName]!!.powerSupply.isInfinite)
    }

    @ParameterizedTest
    @ValueSource(strings = ["configOneComplexDevice.json"])
    fun `one application device with sensors`(fileName: String) {
        val devices: Map<String, Device> = ConfigParser.parse(fileName)
        val deviceName = "Tower1"
        Assertions.assertTrue(devices[deviceName]!!.application is AppEntity)
        Assertions.assertEquals(2, devices[deviceName]!!.sensors.size)
        Assertions.assertEquals(70.0, devices[deviceName]!!.powerSupply.actualCapacity)
        Assertions.assertFalse(devices[deviceName]!!.powerSupply.isInfinite)
    }

    @ParameterizedTest
    @ValueSource(strings = ["configOneComplexDevice.json"])
    fun `sensors know their device`(fileName: String) {
        val devices: Map<String, Device> = ConfigParser.parse(fileName)
        val deviceName = "Tower1"
        val device = devices[deviceName]!!
        val parkSensor = device.sensors[0] as ParkingSensorEntity
        val locSensor = device.sensors[1] as LocalizationSensorEntity
        Assertions.assertEquals(device, parkSensor.device)
        Assertions.assertEquals(device, locSensor.device)
    }

    @ParameterizedTest
    @ValueSource(strings = ["configOneComplexDevice.json"])
    fun `sensors get correct values`(fileName: String) {
        val devices: Map<String, Device> = ConfigParser.parse(fileName)
        val deviceName = "Tower1"
        val device = devices[deviceName]!!
        val parkSensor = device.sensors[0] as ParkingSensorEntity
        val locSensor = device.sensors[1] as LocalizationSensorEntity
        Assertions.assertEquals(8, parkSensor.dataRateInSeconds)
        Assertions.assertEquals(device, parkSensor.dataSink)
        Assertions.assertEquals(60, locSensor.dataRateInSeconds)
        Assertions.assertEquals(device, locSensor.dataSink)
    }

    @ParameterizedTest
    @ValueSource(strings = ["configOneFixedConnection.json"])
    fun `two devices have a connection`(fileName: String) {
        val devices: Map<String, Device> = ConfigParser.parse(fileName)
        val device1 = devices["Tower1"]!!
        val device2 = devices["Fog1"]!!
        Assertions.assertEquals(1, device1.connections.size)
        Assertions.assertEquals(device2, device1.connections[0].destination)
        Assertions.assertEquals(-1, device1.connections[0].dataRateInKbps)
        Assertions.assertEquals("WIRE", device1.connections[0].protocolName)
        Assertions.assertEquals(1, device2.connections.size)
        Assertions.assertEquals(device1, device2.connections[0].destination)
        Assertions.assertEquals(-1, device2.connections[0].dataRateInKbps)
        Assertions.assertEquals("WIRE", device2.connections[0].protocolName)
    }

    @ParameterizedTest
    @ValueSource(strings = ["configOneRandomNetwork.json"])
    fun `one random network`(fileName: String) {
        val devices: Map<String, Device> = ConfigParser.parse(fileName)
        val rootDevice = devices["Fog1"]!!
        val number = 30
        Assertions.assertEquals(number + 1, devices.size)
        Assertions.assertEquals(number, rootDevice.connections.size)
        for(n in 0 until number) {
            val other = rootDevice.connections[n].destination
            Assertions.assertNotNull(devices[other.name])
            Assertions.assertEquals(1, other.connections.size)
            Assertions.assertEquals(rootDevice, other.connections[0].destination)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["configMultipleDevices.json"])
    fun `multiple fixed and random network`(fileName: String) {
        val devices: Map<String, Device> = ConfigParser.parse(fileName)
        val numGarageA = 501
        val numGarageB = 10002
        val numFixed = 2
        Assertions.assertEquals(numFixed + numGarageA + numGarageB, devices.size)

    }


}
