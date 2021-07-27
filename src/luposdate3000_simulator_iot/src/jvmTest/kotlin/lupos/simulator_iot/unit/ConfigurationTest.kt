package lupos.simulator_iot.unit

import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.config.RandomStarNetwork
import lupos.simulator_iot.models.geo.GeoLocation
import lupos.simulator_iot.models.sensor.ParkingSensor
import lupos.simulator_iot.queryproc.DatabaseAdapter
import lupos.simulator_iot.utils.FilePaths
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

class ConfigurationTest {

    companion object {
        private const val prefix = "${FilePaths.testResource}/configurationTest"
    }

    @Test
    fun parseEmptyConfigFile() {
        val config = SimulationRun().config
        config.parse("$prefix/parseEmptyConfigFile.json")
        assertEquals(0, config.getNumberOfDevices())
    }

    @Test
    fun oneFixedDevice() {
        val config = SimulationRun().config
        config.parse("$prefix/oneFixedDevice.json")
        val deviceName = config.jsonObjects.fixedDevice[0].name
        val lat = config.jsonObjects.fixedDevice[0].latitude
        val lon = config.jsonObjects.fixedDevice[0].longitude
        val location = GeoLocation(lat, lon)
        val device = config.getDeviceByName(deviceName)

        assertEquals(config.jsonObjects.fixedDevice.size, config.getNumberOfDevices())
        assertEquals(0, device.address)
        assertEquals(location, device.location)
        assertNull(device.database)
        assertNull(device.sensor)
    }

    @Test
    fun oneDatabaseDeviceWithSensor() {
        val config = SimulationRun().config
        config.parse("$prefix/oneDatabaseDeviceWithSensor.json")
        val deviceName = config.jsonObjects.fixedDevice[0].name
        val device = config.getDeviceByName(deviceName)
        assertTrue(device.database is DatabaseAdapter)
        assertNotNull(device.sensor)
    }

    @Test
    fun sensorsKnowTheirDevice() {
        val config = SimulationRun().config
        config.parse("$prefix/sensorsKnowTheirDevice.json")
        val device = config.getDeviceByName("Tower1")
        val parkingSensor = device.sensor!! as ParkingSensor
        assertSame(device, parkingSensor.device)
    }

    @Test
    fun starRootIsDataSinkOfItsSensors() {
        val config = SimulationRun().config
        config.parse("$prefix/starRootIsDataSinkOfItsSensors.json")
        val root = config.getDeviceByName("Tower1")
        val starNet = config.randStarNetworks["garageA"]!!
        val parkingSensor = starNet.children[0].sensor as ParkingSensor
        assertEquals(root.address, parkingSensor.getSinkAddress())
    }

    @Test
    fun twoDevicesHaveOneConnection() {
        val config = SimulationRun().config
        config.parse("$prefix/twoDevicesHaveOneConnection.json")
        val deviceAName = config.jsonObjects.fixedLink[0].fixedDeviceA
        val deviceBName = config.jsonObjects.fixedLink[0].fixedDeviceB
        val deviceA = config.getDeviceByName(deviceAName)
        val deviceB = config.getDeviceByName(deviceBName)

        assertNotNull(deviceA.linkManager.getLink(deviceB))
        assertNotNull(deviceB.linkManager.getLink(deviceA))
    }

    @Test
    fun twoLinkedDevicesShareTheirLinkObject() {
        val config = SimulationRun().config
        config.parse("$prefix/twoLinkedDevicesShareTheirLinkObject.json")
        val deviceAName = config.jsonObjects.fixedLink[0].fixedDeviceA
        val deviceBName = config.jsonObjects.fixedLink[0].fixedDeviceB
        val deviceA = config.getDeviceByName(deviceAName)
        val deviceB = config.getDeviceByName(deviceBName)
        val linkA = deviceA.linkManager.getLink(deviceB)
        val linkB = deviceB.linkManager.getLink(deviceA)

        assertSame(linkA, linkB)
        linkA!!.distanceInMeters = -1
        assertEquals(linkB!!.distanceInMeters, -1)
    }

    @Test
    fun countNumberOfDevicesInRandomNetwork() {
        val config = SimulationRun().config
        config.parse("$prefix/countNumberOfDevicesInRandomNetwork.json")
        val network = config.jsonObjects.randomStarNetwork[0]
        val starNet = config.randStarNetworks[network.networkPrefix]!!
        assertEquals(1 + network.number, config.getNumberOfDevices())
        assertEquals(network.number, starNet.children.size)
    }

    @Test
    fun checkLinksInStarNetwork() {
        val config = SimulationRun().config
        config.parse("$prefix/checkLinksInStarNetwork.json")
        val networkPrefix = config.jsonObjects.randomStarNetwork[0].networkPrefix
        val starNet = config.randStarNetworks[networkPrefix]!!
        for (child in starNet.children) {
            assertTrue(child.linkManager.hasLink(starNet.root))
            assertTrue(starNet.root.linkManager.hasLink(child))
        }
    }

    @Test
    fun inStarNetworkChildrenDoNotKnowEachOther() {
        val config = SimulationRun().config
        config.parse("$prefix/inStarNetworkChildrenDoNotKnowEachOther.json")
        val starNet = config.randStarNetworks["garageA"]!!
        for (childA in starNet.children)
            for (childB in starNet.children)
                assertFalse(childA.linkManager.hasLink(childB))
    }

    @Test
    fun severalStarNetworksDifferInTheNetworkPrefix() {
        val config = SimulationRun().config
        config.parse("$prefix/severalStarNetworksDifferInTheNetworkPrefix.json")
        val starNetA = config.randStarNetworks["garageA"]!!
        val starNetB = config.randStarNetworks["garageB"]!!
        val starNetC = config.randStarNetworks["garageC"]!!

        assertEquals(10, starNetA.children.size)
        assertEquals(11, starNetB.children.size)
        assertEquals(12, starNetC.children.size)
    }

    @Test
    fun checkLinkData() {
        val config = SimulationRun().config
        config.parse("$prefix/checkLinkData.json")
        val device1Address = config.jsonObjects.fixedDevice[0].name
        val device2Address = config.jsonObjects.fixedDevice[1].name
        val device1 = config.getDeviceByName(device1Address)
        val device2 = config.getDeviceByName(device2Address)
        val link1 = device1.linkManager.getLink(device2)
        val link2 = device2.linkManager.getLink(device1)

        assertTrue(device1.linkManager.hasLink(device2))
        assertTrue(device2.linkManager.hasLink(device1))
        assertEquals(1, device1.linkManager.links.size)
        assertEquals(1, device2.linkManager.links.size)
        assertEquals(link1!!.distanceInMeters, link2!!.distanceInMeters)
    }

    @Test
    fun onlyOneMeshDevice() {
        val config = SimulationRun().config
        config.parse("$prefix/onlyOneMeshDevice.json")
        val networkPrefix = config.jsonObjects.randomMeshNetwork[0].networkPrefix
        val meshNet = config.randMeshNetworks[networkPrefix]!!
        assertEquals(1, meshNet.numOfDevices())
        assertEquals(1, config.getNumberOfDevices())
    }

    @Test
    fun onlySouthernMeshDevices() {
        val config = SimulationRun().config
        config.parse("$prefix/onlySouthernMeshDevices.json")
        val networkPrefix = config.jsonObjects.randomMeshNetwork[0].networkPrefix
        val meshNet = config.randMeshNetworks[networkPrefix]!!
        assertEquals(1, meshNet.mesh.size)
        assertTrue(config.getNumberOfDevices() > 1)
        assertTrue(meshNet.mesh[0].size > 1)
        assertEquals(meshNet.numOfDevices(), config.getNumberOfDevices())
    }

    @Test
    fun onlyEasternMeshDevices() {
        val config = SimulationRun().config
        config.parse("$prefix/onlyEasternMeshDevices.json")
        val networkPrefix = config.jsonObjects.randomMeshNetwork[0].networkPrefix
        val meshNet = config.randMeshNetworks[networkPrefix]!!
        assertTrue(config.getNumberOfDevices() > 1)
        assertTrue(meshNet.mesh.size > 1)
        assertEquals(meshNet.numOfDevices(), config.getNumberOfDevices())
        for (col in meshNet.mesh) {
            assertEquals(1, col.size)
        }
    }

    @Test
    fun moreSouthernThanEasternMeshDevices() {
        val config = SimulationRun().config
        config.parse("$prefix/moreSouthernThanEasternMeshDevices.json")
        val networkPrefix = config.jsonObjects.randomMeshNetwork[0].networkPrefix
        val meshNet = config.randMeshNetworks[networkPrefix]!!
        assertTrue(config.getNumberOfDevices() > 1)
        assertTrue(meshNet.mesh.size > 1)
        assertEquals(meshNet.numOfDevices(), config.getNumberOfDevices())
        val rowSize = meshNet.mesh.size
        for (col in meshNet.mesh) {
            assertTrue(col.size > rowSize)
        }
    }

    @Test
    fun edgeMeshDevicesCannotReachEachOther() {
        val config = SimulationRun().config
        config.parse("$prefix/edgeMeshDevicesCannotReachEachOther.json")
        val networkPrefix = config.jsonObjects.randomMeshNetwork[0].networkPrefix
        val mesh = config.randMeshNetworks[networkPrefix]!!.mesh
        val northWest = mesh[0][0]
        val southWest = mesh[0][mesh[0].size - 1]
        val northEast = mesh[mesh.size - 1][0]
        val southEast = mesh[mesh.size - 1][mesh[mesh.size - 1].size - 1]

        assertFalse(northWest.linkManager.hasLink(northEast))
        assertFalse(northWest.linkManager.hasLink(southEast))
        assertFalse(northWest.linkManager.hasLink(southWest))
        assertFalse(northEast.linkManager.hasLink(southWest))
        assertFalse(northEast.linkManager.hasLink(southEast))
        assertFalse(southEast.linkManager.hasLink(southWest))
    }

    @Test
    fun distanceToNeighboringDevicesIsSmaller() {
        val config = SimulationRun().config
        config.parse("$prefix/distanceToNeighboringDevicesIsSmaller.json")
        val networkPrefix = config.jsonObjects.randomMeshNetwork[0].networkPrefix
        val maxLinkRange = config.jsonObjects.linkType[0].rangeInMeters
        val mesh = config.randMeshNetworks[networkPrefix]!!.mesh
        val device = mesh[0][0]
        val neighbour = mesh[1][0]
        val neighbourNeighbour = mesh[2][0]

        val distanceToNeighbour = config.linker.getDistanceInMeters(device, neighbour)
        val distanceToNeighbourNeighbour = config.linker.getDistanceInMeters(device, neighbourNeighbour)

        assertTrue(distanceToNeighbour <= maxLinkRange)
        assertTrue(distanceToNeighbour < distanceToNeighbourNeighbour)
    }

    @Test
    fun fixedAndMeshedDevicesAreLinkable() {
        val config = SimulationRun().config
        config.parse("$prefix/fixedAndMeshedDevicesAreLinkable.json")
        val fixedDeviceName = config.jsonObjects.fixedDevice[0].name
        val fixedDevice = config.getDeviceByName(fixedDeviceName)

        val networkPrefix = config.jsonObjects.randomMeshNetwork[0].networkPrefix
        val mesh = config.randMeshNetworks[networkPrefix]!!.mesh
        val meshOrigin = mesh[0][0]

        assertTrue(fixedDevice.linkManager.hasLink(meshOrigin))
        assertEquals(meshOrigin.linkManager.links.size, fixedDevice.linkManager.links.size)
    }

    @Test
    fun fixedAndMeshedDevicesAreNotLinkable() {
        val config = SimulationRun().config
        config.parse("$prefix/fixedAndMeshedDevicesAreNotLinkable.json")
        val fixedDeviceName = config.jsonObjects.fixedDevice[0].name
        val fixedDevice = config.getDeviceByName(fixedDeviceName)

        val networkPrefix = config.jsonObjects.randomMeshNetwork[0].networkPrefix
        val mesh = config.randMeshNetworks[networkPrefix]!!.mesh
        val meshOrigin = mesh[0][0]

        assertFalse(fixedDevice.linkManager.hasLink(meshOrigin))
        assertNotEquals(meshOrigin.linkManager.links.size, fixedDevice.linkManager.links.size)
    }

    @Test
    fun configOneQuerySender() {
        val config = SimulationRun().config
        config.parse("$prefix/configOneQuerySender.json")
        assertEquals(1, config.querySenders.size)
        val querySender = config.querySenders[0]
        assertEquals("Driver1", querySender.name)
        assertEquals(30, querySender.sendRateInSec)
        assertEquals(config.getDeviceByName("Tower1"), querySender.receiver)
        assertEquals("Select dummy From dum", querySender.query)
    }

    @Test
    fun manipulateJsonObjects() {
        val config = SimulationRun().config
        val jsonObjects = config.readJsonFile("$prefix/manipulateJsonObjects.json")
        jsonObjects.randomStarNetwork.add(
            RandomStarNetwork(
                networkPrefix = "star2",
                starRoot = "Tower1",
                linkType = "WPAN",
                deviceType = "StandAloneParkingSensor",
                number = 3
            )
        )
        config.parse(jsonObjects)
        assertEquals(2, config.randStarNetworks.size)
    }
}
