package lupos.simulator_iot

import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.geo.GeoLocation
import lupos.simulator_iot.sensor.ParkingSensor
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
        private const val prefix = "ConfigurationTest"
    }

    @Test
    fun parseEmptyConfigFile() {
        Configuration.parse("$prefix/parseEmptyConfigFile.json")
        assertTrue(Configuration.devices.isEmpty())
    }

    @Test
    fun oneFixedDevice() {
        Configuration.parse("$prefix/oneFixedDevice.json")
        val deviceName = Configuration.jsonObjects.fixedDevice[0].name
        val lat = Configuration.jsonObjects.fixedDevice[0].latitude
        val lon = Configuration.jsonObjects.fixedDevice[0].longitude
        val location = GeoLocation(lat, lon)
        val device = Configuration.getNamedDevice(deviceName)

        assertEquals(Configuration.jsonObjects.fixedDevice.size, Configuration.devices.size)
        assertEquals(0, device.address)
        assertEquals(location, device.location)
        assertNull(device.database)
        assertNull(device.sensor)
        assertTrue(device.powerSupply.isInfinite)
    }

    @Test
    fun oneDatabaseDeviceWithSensor() {
        Configuration.parse("$prefix/oneDatabaseDeviceWithSensor.json")
        val deviceName = Configuration.jsonObjects.fixedDevice[0].name
        val device = Configuration.getNamedDevice(deviceName)
        assertTrue(device.database is DatabaseAdapter)
        assertNotNull(device.sensor)
        assertEquals(70.0, device.powerSupply.actualCapacity)
        assertFalse(device.powerSupply.isInfinite)
    }

    @Test
    fun sensorsKnowTheirDevice() {
        Configuration.parse("$prefix/sensorsKnowTheirDevice.json")
        val device = Configuration.getNamedDevice("Tower1")
        val parkingSensor = device.sensor!! as ParkingSensor
        assertSame(device, parkingSensor.device)
    }

    @Test
    fun sensorsDataSinkIsItsOwnDevice() {
        Configuration.parse("$prefix/sensorsDataSinkIsItsOwnDevice.json")
        val device = Configuration.getNamedDevice("Tower1")
        val parkingSensor = device.sensor!! as ParkingSensor
        assertEquals(device.address, parkingSensor.dataSinkAddress)
    }

    @Test
    fun starRootIsDataSinkOfItsSensors() {
        Configuration.parse("$prefix/starRootIsDataSinkOfItsSensors.json")
        val root = Configuration.getNamedDevice("Tower1")
        val starNet = Configuration.randStarNetworks["garageA"]!!
        val parkingSensor = starNet.children[0].sensor as ParkingSensor
        assertEquals(root.address, parkingSensor.dataSinkAddress)
    }

    @Test
    fun twoDevicesHaveOneConnection() {
        Configuration.parse("$prefix/twoDevicesHaveOneConnection.json")
        val deviceAName = Configuration.jsonObjects.fixedLink[0].fixedDeviceA
        val deviceBName = Configuration.jsonObjects.fixedLink[0].fixedDeviceB
        val deviceA = Configuration.getNamedDevice(deviceAName)
        val deviceB = Configuration.getNamedDevice(deviceBName)

        assertNotNull(deviceA.linkManager.getLink(deviceB))
        assertNotNull(deviceB.linkManager.getLink(deviceA))
    }

    @Test
    fun twoLinkedDevicesShareTheirLinkObject() {
        Configuration.parse("$prefix/twoLinkedDevicesShareTheirLinkObject.json")
        val deviceAName = Configuration.jsonObjects.fixedLink[0].fixedDeviceA
        val deviceBName = Configuration.jsonObjects.fixedLink[0].fixedDeviceB
        val deviceA = Configuration.getNamedDevice(deviceAName)
        val deviceB = Configuration.getNamedDevice(deviceBName)
        val linkA = deviceA.linkManager.getLink(deviceB)
        val linkB = deviceB.linkManager.getLink(deviceA)

        assertSame(linkA, linkB)
        linkA!!.distanceInMeters = -1
        assertEquals(linkB!!.distanceInMeters, -1)
    }

    @Test
    fun countNumberOfDevicesInRandomNetwork() {
        Configuration.parse("$prefix/countNumberOfDevicesInRandomNetwork.json")
        val devices = Configuration.devices
        val network = Configuration.jsonObjects.randomStarNetwork[0]
        val starNet = Configuration.randStarNetworks[network.networkPrefix]!!
        assertEquals(1 + network.number, devices.size)
        assertEquals(network.number, starNet.children.size)
    }

    @Test
    fun checkLinksInStarNetwork() {
        Configuration.parse("$prefix/checkLinksInStarNetwork.json")
        val networkPrefix = Configuration.jsonObjects.randomStarNetwork[0].networkPrefix
        val starNet = Configuration.randStarNetworks[networkPrefix]!!
        for (child in starNet.children) {
            assertTrue(child.linkManager.hasLink(starNet.dataSink))
            assertTrue(starNet.dataSink.linkManager.hasLink(child))
        }
    }

    @Test
    fun inStarNetworkChildrenDoNotKnowEachOther() {
        Configuration.parse("$prefix/inStarNetworkChildrenDoNotKnowEachOther.json")
        val starNet = Configuration.randStarNetworks["garageA"]!!
        for (childA in starNet.children)
            for (childB in starNet.children)
                assertFalse(childA.linkManager.hasLink(childB))
    }

    @Test
    fun severalStarNetworksDifferInTheNetworkPrefix() {
        Configuration.parse("$prefix/severalStarNetworksDifferInTheNetworkPrefix.json")
        val starNetA = Configuration.randStarNetworks["garageA"]!!
        val starNetB = Configuration.randStarNetworks["garageB"]!!
        val starNetC = Configuration.randStarNetworks["garageC"]!!

        assertEquals(10, starNetA.children.size)
        assertEquals(11, starNetB.children.size)
        assertEquals(12, starNetC.children.size)
    }

    @Test
    fun checkLinkData() {
        Configuration.parse("$prefix/checkLinkData.json")
        val device1Address = Configuration.jsonObjects.fixedDevice[0].name
        val device2Address = Configuration.jsonObjects.fixedDevice[1].name
        val device1 = Configuration.getNamedDevice(device1Address)
        val device2 = Configuration.getNamedDevice(device2Address)
        val link1 = device1.linkManager.getLink(device2)
        val link2 = device2.linkManager.getLink(device1)

        assertTrue(device1.linkManager.hasLink(device2))
        assertTrue(device2.linkManager.hasLink(device1))
        assertEquals(1, device1.linkManager.getNumberOfLinks())
        assertEquals(1, device2.linkManager.getNumberOfLinks())
        assertEquals(link1!!.distanceInMeters, link2!!.distanceInMeters)
    }

    @Test
    fun onlyOneMeshDevice() {
        Configuration.parse("$prefix/onlyOneMeshDevice.json")
        val networkPrefix = Configuration.jsonObjects.randomMeshNetwork[0].networkPrefix
        val meshNet = Configuration.randMeshNetworks[networkPrefix]!!
        assertEquals(1, meshNet.numOfDevices())
        assertEquals(1, Configuration.devices.size)
    }

    @Test
    fun onlySouthernMeshDevices() {
        Configuration.parse("$prefix/onlySouthernMeshDevices.json")
        val networkPrefix = Configuration.jsonObjects.randomMeshNetwork[0].networkPrefix
        val meshNet = Configuration.randMeshNetworks[networkPrefix]!!
        assertEquals(1, meshNet.mesh.size)
        assertTrue(Configuration.devices.size > 1)
        assertTrue(meshNet.mesh[0].size > 1)
        assertEquals(meshNet.numOfDevices(), Configuration.devices.size)
    }

    @Test
    fun onlyEasternMeshDevices() {
        Configuration.parse("$prefix/onlyEasternMeshDevices.json")
        val networkPrefix = Configuration.jsonObjects.randomMeshNetwork[0].networkPrefix
        val meshNet = Configuration.randMeshNetworks[networkPrefix]!!
        assertTrue(Configuration.devices.size > 1)
        assertTrue(meshNet.mesh.size > 1)
        assertEquals(meshNet.numOfDevices(), Configuration.devices.size)
        for (col in meshNet.mesh) {
            assertEquals(1, col.size)
        }
    }

    @Test
    fun moreSouthernThanEasternMeshDevices() {
        Configuration.parse("$prefix/moreSouthernThanEasternMeshDevices.json")
        val networkPrefix = Configuration.jsonObjects.randomMeshNetwork[0].networkPrefix
        val meshNet = Configuration.randMeshNetworks[networkPrefix]!!
        assertTrue(Configuration.devices.size > 1)
        assertTrue(meshNet.mesh.size > 1)
        assertEquals(meshNet.numOfDevices(), Configuration.devices.size)
        val rowSize = meshNet.mesh.size
        for (col in meshNet.mesh) {
            assertTrue(col.size > rowSize)
        }
    }

    @Test
    fun edgeMeshDevicesCannotReachEachOther() {
        Configuration.parse("$prefix/edgeMeshDevicesCannotReachEachOther.json")
        val networkPrefix = Configuration.jsonObjects.randomMeshNetwork[0].networkPrefix
        val mesh = Configuration.randMeshNetworks[networkPrefix]!!.mesh
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
        Configuration.parse("$prefix/distanceToNeighboringDevicesIsSmaller.json")
        val networkPrefix = Configuration.jsonObjects.randomMeshNetwork[0].networkPrefix
        val maxLinkRange = Configuration.jsonObjects.linkType[0].rangeInMeters
        val mesh = Configuration.randMeshNetworks[networkPrefix]!!.mesh
        val device = mesh[0][0]
        val neighbour = mesh[1][0]
        val neighbourNeighbour = mesh[2][0]

        val distanceToNeighbour = device.linkManager.getDistanceInMeters(neighbour)
        val distanceToNeighbourNeighbour = device.linkManager.getDistanceInMeters(neighbourNeighbour)

        assertTrue(distanceToNeighbour <= maxLinkRange)
        assertTrue(distanceToNeighbour < distanceToNeighbourNeighbour)
    }

    @Test
    fun fixedAndMeshedDevicesAreLinkable() {
        Configuration.parse("$prefix/fixedAndMeshedDevicesAreLinkable.json")
        val fixedDeviceName = Configuration.jsonObjects.fixedDevice[0].name
        val fixedDevice = Configuration.getNamedDevice(fixedDeviceName)

        val networkPrefix = Configuration.jsonObjects.randomMeshNetwork[0].networkPrefix
        val mesh = Configuration.randMeshNetworks[networkPrefix]!!.mesh
        val meshOrigin = mesh[0][0]

        assertTrue(fixedDevice.linkManager.hasLink(meshOrigin))
        assertEquals(meshOrigin.linkManager.getNumberOfLinks(), fixedDevice.linkManager.getNumberOfLinks())
    }

    @Test
    fun fixedAndMeshedDevicesAreNotLinkable() {
        Configuration.parse("$prefix/fixedAndMeshedDevicesAreNotLinkable.json")
        val fixedDeviceName = Configuration.jsonObjects.fixedDevice[0].name
        val fixedDevice = Configuration.getNamedDevice(fixedDeviceName)

        val networkPrefix = Configuration.jsonObjects.randomMeshNetwork[0].networkPrefix
        val mesh = Configuration.randMeshNetworks[networkPrefix]!!.mesh
        val meshOrigin = mesh[0][0]

        assertFalse(fixedDevice.linkManager.hasLink(meshOrigin))
        assertNotEquals(meshOrigin.linkManager.getNumberOfLinks(), fixedDevice.linkManager.getNumberOfLinks())
    }

    @Test
    fun configOneQuerySender() {
        Configuration.parse("$prefix/configOneQuerySender.json")
        assertEquals(1, Configuration.querySenders.size)
        val querySender = Configuration.querySenders[0]
        assertEquals("Driver1", querySender.name)
        assertEquals( 30, querySender.sendRateInSec)
        assertEquals(Configuration.getNamedDevice("Tower1"), querySender.receiver)
        assertEquals("Select dummy From dum", querySender.query)
    }
}
