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
    @ValueSource(strings = ["config/oneFixedDevice.json"])
    fun oneFixedDevice(fileName: String) {
        Configuration.parse(fileName)
        val deviceName = Configuration.jsonObjects.fixedDevices[0].name
        val lat = Configuration.jsonObjects.fixedDevices[0].latitude
        val lon = Configuration.jsonObjects.fixedDevices[0].longitude
        val location = GeoLocation(lat, lon)
        val device = Configuration.getNamedDevice(deviceName)

        Assertions.assertEquals(Configuration.jsonObjects.fixedDevices.size, Configuration.devices.size)
        Assertions.assertEquals(0, device.address)
        Assertions.assertEquals(location, device.location)
        Assertions.assertNull(device.application)
        Assertions.assertNull(device.sensor)
        Assertions.assertTrue(device.powerSupply.isInfinite)
        Assertions.assertEquals(1, Configuration.entities.size)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneComplexDevice.json"])
    fun `one application device with sensors`(fileName: String) {
        Configuration.parse(fileName)
        val devices = Configuration.devices
        val deviceName = Configuration.jsonObjects.fixedDevices[0].name
        val device = Configuration.getNamedDevice(deviceName)
        val numSensors = 1
        Assertions.assertTrue(device.application is DatabaseApp)
        Assertions.assertNotNull(device.sensor)
        Assertions.assertEquals(70.0, device.powerSupply.actualCapacity)
        Assertions.assertFalse(device.powerSupply.isInfinite)
        Assertions.assertEquals(2 + numSensors, Configuration.entities.size)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneComplexDevice.json"])
    fun `sensors know their device`(fileName: String) {
        Configuration.parse(fileName)
        val deviceName = "Tower1"
        val device = Configuration.getNamedDevice(deviceName)
        Assertions.assertEquals(device, device.sensor!!.device)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneComplexDevice.json"])
    fun `sensors get correct values`(fileName: String) {
        Configuration.parse(fileName)
        val deviceName = "Tower1"
        val device = Configuration.getNamedDevice(deviceName)
        Assertions.assertEquals(device, device.sensor!!.dataSink)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneFixedConnection.json"])
    fun `two devices have a connection`(fileName: String) {
        Configuration.parse(fileName)
        val deviceAName = Configuration.jsonObjects.fixedLinks[0].endpointA
        val deviceBName = Configuration.jsonObjects.fixedLinks[0].endpointB
        val deviceA = Configuration.getNamedDevice(deviceAName)
        val deviceB = Configuration.getNamedDevice(deviceBName)

        Assertions.assertNotNull(deviceA.getAvailableLink(deviceB))
        Assertions.assertNotNull(deviceB.getAvailableLink(deviceA))
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneRandomNetwork.json"])
    fun `count number of devices in random network`(fileName: String) {
        Configuration.parse(fileName)
        val devices = Configuration.devices
        val network = Configuration.jsonObjects.randomStarNetwork[0]
        val starNet = Configuration.randStarNetworks[network.networkPrefix]!!
        Assertions.assertEquals(1 + network.number, devices.size)
        Assertions.assertEquals(network.number, starNet.childs.size)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneRandomNetwork.json"])
    fun `in star network every child is linked to root and vice versa`(fileName: String) {
        Configuration.parse(fileName)
        val networkPrefix = Configuration.jsonObjects.randomStarNetwork[0].networkPrefix
        val starNet = Configuration.randStarNetworks[networkPrefix]!!
        for(child in starNet.childs) {
            Assertions.assertTrue(child.hasAvailAbleLink(starNet.parent))
            Assertions.assertTrue(starNet.parent.hasAvailAbleLink(child))
        }
    }


    @ParameterizedTest
    @ValueSource(strings = ["config/configOneFixedConnection.json"])
    fun `check link between two fixed devices`(fileName: String) {
        Configuration.parse(fileName)
        val device1Address = Configuration.jsonObjects.fixedDevices[0].name
        val device2Address = Configuration.jsonObjects.fixedDevices[1].name
        val device1 = Configuration.getNamedDevice(device1Address)
        val device2 = Configuration.getNamedDevice(device2Address)
        val link1 = device1.getAvailableLink(device2)
        val link2 = device2.getAvailableLink(device1)

        Assertions.assertTrue(device1.hasAvailAbleLink(device2))
        Assertions.assertTrue(device2.hasAvailAbleLink(device1))
        Assertions.assertEquals(1, device1.numOfAvailAbleLinks())
        Assertions.assertEquals(1, device2.numOfAvailAbleLinks())
        Assertions.assertEquals(link1!!.distanceInMeters, link2!!.distanceInMeters)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/onlyOneMeshDevice.json"])
    fun onlyOneMeshDevice(fileName: String) {
        Configuration.parse(fileName)
        val networkPrefix = Configuration.jsonObjects.randomMeshNetwork[0].networkPrefix
        val meshNet = Configuration.randMeshNetworks[networkPrefix]!!
        Assertions.assertEquals(1, meshNet.numOfDevices())
        Assertions.assertEquals(1, Configuration.devices.size)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/onlySouthernMeshDevices.json"])
    fun onlySouthernMeshDevices(fileName: String) {
        Configuration.parse(fileName)
        val networkPrefix = Configuration.jsonObjects.randomMeshNetwork[0].networkPrefix
        val meshNet = Configuration.randMeshNetworks[networkPrefix]!!
        Assertions.assertEquals(1, meshNet.mesh.size)
        Assertions.assertTrue(Configuration.devices.size > 1)
        Assertions.assertTrue(meshNet.mesh[0].size > 1)
        Assertions.assertEquals(meshNet.numOfDevices(), Configuration.devices.size)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/onlyEasternMeshDevices.json"])
    fun onlyEasternMeshDevices(fileName: String) {
        Configuration.parse(fileName)
        val networkPrefix = Configuration.jsonObjects.randomMeshNetwork[0].networkPrefix
        val meshNet = Configuration.randMeshNetworks[networkPrefix]!!
        Assertions.assertTrue(Configuration.devices.size > 1)
        Assertions.assertTrue(meshNet.mesh.size > 1)
        Assertions.assertEquals(meshNet.numOfDevices(), Configuration.devices.size)
        for(col in meshNet.mesh) {
            Assertions.assertEquals(1, col.size)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/moreSouthernThanEasternMeshDevices.json"])
    fun moreSouthernThanEasternMeshDevices(fileName: String) {
        Configuration.parse(fileName)
        val networkPrefix = Configuration.jsonObjects.randomMeshNetwork[0].networkPrefix
        val meshNet = Configuration.randMeshNetworks[networkPrefix]!!
        Assertions.assertTrue(Configuration.devices.size > 1)
        Assertions.assertTrue(meshNet.mesh.size > 1)
        Assertions.assertEquals(meshNet.numOfDevices(), Configuration.devices.size)
        val rowSize = meshNet.mesh.size
        for(col in meshNet.mesh) {
            Assertions.assertTrue(col.size > rowSize)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/edgeMeshDevicesCannotReachEachOther.json"])
    fun edgeMeshDevicesCannotReachEachOther(fileName: String) {
        Configuration.parse(fileName)
        val networkPrefix = Configuration.jsonObjects.randomMeshNetwork[0].networkPrefix
        val mesh = Configuration.randMeshNetworks[networkPrefix]!!.mesh
        val northWest = mesh[0][0]
        val southWest = mesh[0][mesh[0].size-1]
        val northEast = mesh[mesh.size-1][0]
        val southEast = mesh[mesh.size-1][mesh[mesh.size-1].size-1]

        Assertions.assertFalse(northWest.hasAvailAbleLink(northEast))
        Assertions.assertFalse(northWest.hasAvailAbleLink(southEast))
        Assertions.assertFalse(northWest.hasAvailAbleLink(southWest))
        Assertions.assertFalse(northEast.hasAvailAbleLink(southWest))
        Assertions.assertFalse(northEast.hasAvailAbleLink(southEast))
        Assertions.assertFalse(southEast.hasAvailAbleLink(southWest))
    }

}
