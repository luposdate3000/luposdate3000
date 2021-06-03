import config.Configuration
import geo.GeoLocation
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import sensor.ParkingSensor

class ConfigurationTest {


    @ParameterizedTest
    @ValueSource(strings = ["config/parseEmptyConfigFile.json"])
    fun parseEmptyConfigFile(fileName: String) {
        Configuration.parse(fileName)
        Assertions.assertTrue(Configuration.devices.isEmpty())
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/oneFixedDevice.json"])
    fun oneFixedDevice(fileName: String) {
        Configuration.parse(fileName)
        val deviceName = Configuration.jsonObjects.fixedDevice[0].name
        val lat = Configuration.jsonObjects.fixedDevice[0].latitude
        val lon = Configuration.jsonObjects.fixedDevice[0].longitude
        val location = GeoLocation(lat, lon)
        val device = Configuration.getNamedDevice(deviceName)

        Assertions.assertEquals(Configuration.jsonObjects.fixedDevice.size, Configuration.devices.size)
        Assertions.assertEquals(0, device.address)
        Assertions.assertEquals(location, device.location)
        Assertions.assertNull(device.database)
        Assertions.assertNull(device.sensor)
        Assertions.assertTrue(device.powerSupply.isInfinite)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneComplexDevice.json"])
    fun `one database device with sensors`(fileName: String) {
        Configuration.parse(fileName)
        val deviceName = Configuration.jsonObjects.fixedDevice[0].name
        val device = Configuration.getNamedDevice(deviceName)
        Assertions.assertTrue(device.database is DatabaseAdapter)
        Assertions.assertNotNull(device.sensor)
        Assertions.assertEquals(70.0, device.powerSupply.actualCapacity)
        Assertions.assertFalse(device.powerSupply.isInfinite)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/sensorsKnowTheirDevice.json"])
    fun sensorsKnowTheirDevice(fileName: String) {
        Configuration.parse(fileName)
        val device = Configuration.getNamedDevice("Tower1")
        val parkingSensor = device.sensor!! as ParkingSensor
        Assertions.assertTrue(device === parkingSensor.device)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/sensorsDataSinkIsItsOwnDevice.json"])
    fun sensorsDataSinkIsItsOwnDevice(fileName: String) {
        Configuration.parse(fileName)
        val device = Configuration.getNamedDevice("Tower1")
        val parkingSensor = device.sensor!! as ParkingSensor
        Assertions.assertEquals(device.address, parkingSensor.dataSinkAddress)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/starRootIsDataSinkOfItsSensors.json"])
    fun starRootIsDataSinkOfItsSensors(fileName: String) {
        Configuration.parse(fileName)
        val root = Configuration.getNamedDevice("Tower1")
        val starNet = Configuration.randStarNetworks["garageA"]!!
        val parkingSensor = starNet.children[0].sensor as ParkingSensor
        Assertions.assertEquals(root.address, parkingSensor.dataSinkAddress)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneFixedConnection.json"])
    fun `two devices have a connection`(fileName: String) {
        Configuration.parse(fileName)
        val deviceAName = Configuration.jsonObjects.fixedLink[0].fixedDeviceA
        val deviceBName = Configuration.jsonObjects.fixedLink[0].fixedDeviceB
        val deviceA = Configuration.getNamedDevice(deviceAName)
        val deviceB = Configuration.getNamedDevice(deviceBName)

        Assertions.assertNotNull(deviceA.linkManager.getLink(deviceB))
        Assertions.assertNotNull(deviceB.linkManager.getLink(deviceA))
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/twoLinkedDevicesShareTheirLinkObject.json"])
    fun twoLinkedDevicesShareTheirLinkObject(fileName: String) {
        Configuration.parse(fileName)
        val deviceAName = Configuration.jsonObjects.fixedLink[0].fixedDeviceA
        val deviceBName = Configuration.jsonObjects.fixedLink[0].fixedDeviceB
        val deviceA = Configuration.getNamedDevice(deviceAName)
        val deviceB = Configuration.getNamedDevice(deviceBName)
        val linkA = deviceA.linkManager.getLink(deviceB)
        val linkB = deviceB.linkManager.getLink(deviceA)

        Assertions.assertTrue(linkA === linkB)
        linkA!!.distanceInMeters = -1
        Assertions.assertTrue(linkB!!.distanceInMeters == -1)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneRandomNetwork.json"])
    fun `count number of devices in random network`(fileName: String) {
        Configuration.parse(fileName)
        val devices = Configuration.devices
        val network = Configuration.jsonObjects.randomStarNetwork[0]
        val starNet = Configuration.randStarNetworks[network.networkPrefix]!!
        Assertions.assertEquals(1 + network.number, devices.size)
        Assertions.assertEquals(network.number, starNet.children.size)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/configOneRandomNetwork.json"])
    fun `in star network every child is linked to root and vice versa`(fileName: String) {
        Configuration.parse(fileName)
        val networkPrefix = Configuration.jsonObjects.randomStarNetwork[0].networkPrefix
        val starNet = Configuration.randStarNetworks[networkPrefix]!!
        for(child in starNet.children) {
            Assertions.assertTrue(child.linkManager.hasLink(starNet.parent))
            Assertions.assertTrue(starNet.parent.linkManager.hasLink(child))
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/inStarNetworkChildrenDoNotKnowEachOther.json"])
    fun inStarNetworkChildrenDoNotKnowEachOther(fileName: String) {
        Configuration.parse(fileName)
        val starNet = Configuration.randStarNetworks["garageA"]!!
        for(childA in starNet.children)
            for (childB in starNet.children)
                Assertions.assertFalse(childA.linkManager.hasLink(childB))
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/severalStarNetworksDifferInTheNetworkPrefix.json"])
    fun severalStarNetworksDifferInTheNetworkPrefix(fileName: String) {
        Configuration.parse(fileName)
        val starNetA = Configuration.randStarNetworks["garageA"]!!
        val starNetB = Configuration.randStarNetworks["garageB"]!!
        val starNetC = Configuration.randStarNetworks["garageC"]!!

        Assertions.assertEquals(10, starNetA.children.size)
        Assertions.assertEquals(11, starNetB.children.size)
        Assertions.assertEquals(12, starNetC.children.size)
    }


    @ParameterizedTest
    @ValueSource(strings = ["config/configOneFixedConnection.json"])
    fun `check link between two fixed devices`(fileName: String) {
        Configuration.parse(fileName)
        val device1Address = Configuration.jsonObjects.fixedDevice[0].name
        val device2Address = Configuration.jsonObjects.fixedDevice[1].name
        val device1 = Configuration.getNamedDevice(device1Address)
        val device2 = Configuration.getNamedDevice(device2Address)
        val link1 = device1.linkManager.getLink(device2)
        val link2 = device2.linkManager.getLink(device1)

        Assertions.assertTrue(device1.linkManager.hasLink(device2))
        Assertions.assertTrue(device2.linkManager.hasLink(device1))
        Assertions.assertEquals(1, device1.linkManager.getNumberOfLinks())
        Assertions.assertEquals(1, device2.linkManager.getNumberOfLinks())
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

        Assertions.assertFalse(northWest.linkManager.hasLink(northEast))
        Assertions.assertFalse(northWest.linkManager.hasLink(southEast))
        Assertions.assertFalse(northWest.linkManager.hasLink(southWest))
        Assertions.assertFalse(northEast.linkManager.hasLink(southWest))
        Assertions.assertFalse(northEast.linkManager.hasLink(southEast))
        Assertions.assertFalse(southEast.linkManager.hasLink(southWest))
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/distanceToNeighboringDevicesIsSmaller.json"])
    fun distanceToNeighboringDevicesIsSmaller(fileName: String) {
        Configuration.parse(fileName)
        val networkPrefix = Configuration.jsonObjects.randomMeshNetwork[0].networkPrefix
        val maxLinkRange = Configuration.jsonObjects.linkType[0].rangeInMeters
        val mesh = Configuration.randMeshNetworks[networkPrefix]!!.mesh
        val device = mesh[0][0]
        val neighbour = mesh[1][0]
        val neighbourNeighbour = mesh[2][0]

        val distanceToNeighbour = device.linkManager.getDistanceInMeters(neighbour)
        val distanceToNeighbourNeighbour = device.linkManager.getDistanceInMeters(neighbourNeighbour)

        Assertions.assertTrue(distanceToNeighbour <= maxLinkRange)
        Assertions.assertTrue(distanceToNeighbour < distanceToNeighbourNeighbour)
    }

    @ParameterizedTest
    @ValueSource(strings = ["config/fixedAndMeshedDevicesAreLinkable.json"])
    fun fixedAndMeshedDevicesAreLinkable(fileName: String) {
        Configuration.parse(fileName)
        val fixedDeviceName = Configuration.jsonObjects.fixedDevice[0].name
        val fixedDevice = Configuration.getNamedDevice(fixedDeviceName)

        val networkPrefix = Configuration.jsonObjects.randomMeshNetwork[0].networkPrefix
        val mesh = Configuration.randMeshNetworks[networkPrefix]!!.mesh
        val meshOrigin = mesh[0][0]

        Assertions.assertTrue(fixedDevice.linkManager.hasLink(meshOrigin))
        Assertions.assertEquals(meshOrigin.linkManager.getNumberOfLinks() ,fixedDevice.linkManager.getNumberOfLinks())
    }


    @ParameterizedTest
    @ValueSource(strings = ["config/fixedAndMeshedDevicesAreNotLinkable.json"])
    fun fixedAndMeshedDevicesAreNotLinkable(fileName: String) {
        Configuration.parse(fileName)
        val fixedDeviceName = Configuration.jsonObjects.fixedDevice[0].name
        val fixedDevice = Configuration.getNamedDevice(fixedDeviceName)

        val networkPrefix = Configuration.jsonObjects.randomMeshNetwork[0].networkPrefix
        val mesh = Configuration.randMeshNetworks[networkPrefix]!!.mesh
        val meshOrigin = mesh[0][0]

        Assertions.assertFalse(fixedDevice.linkManager.hasLink(meshOrigin))
        Assertions.assertNotEquals(meshOrigin.linkManager.getNumberOfLinks() ,fixedDevice.linkManager.getNumberOfLinks())
    }


}
