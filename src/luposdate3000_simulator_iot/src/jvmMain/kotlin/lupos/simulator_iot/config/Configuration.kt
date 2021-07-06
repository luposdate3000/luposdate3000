package lupos.simulator_iot.config

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import lupos.shared.inline.File
import lupos.simulator_core.Entity
import lupos.simulator_iot.Device
import lupos.simulator_iot.RandomGenerator
import lupos.simulator_iot.db.DatabaseAdapter
import lupos.simulator_iot.geo.GeoLocation
import lupos.simulator_iot.net.MeshNetwork
import lupos.simulator_iot.net.StarNetwork
import lupos.simulator_iot.sensor.ParkingSensor
import kotlin.math.round

internal object Configuration {

    internal var devices: MutableList<Device> = mutableListOf()
        private set

    private var namedAddresses: MutableMap<String, Int> = mutableMapOf()

    internal var jsonObjects: JsonObjects = JsonObjects()
        private set

    internal var randStarNetworks: MutableMap<String, StarNetwork> = mutableMapOf()
        private set

    internal var randMeshNetworks: MutableMap<String, MeshNetwork> = mutableMapOf()
        private set

    internal var querySenders: MutableList<lupos.simulator_iot.QuerySender> = mutableListOf()
        private set

    internal var dbDeviceAddresses: IntArray = intArrayOf()

    private var rootRouterAddress: Int = -1

    private var dbDeviceCounter = 0

    private var deviceNames: MutableList<String> = mutableListOf()

    internal var linker = DeviceLinker()

    internal fun parse(jsonObjects: JsonObjects) {
        resetVariables()
        initVariables(jsonObjects)
        createFixedDevices()
        setRootDevice()
        createQuerySenders()
        createFixedLinks()
        createRandomMeshNetworks()
        createRandomStarNetworks()
        linker.createAvailableLinks(devices)
        createDbDeviceAddresses()
    }

    internal fun parse(fileName: String) {
        parse(readJsonFile(fileName))
    }

    private fun initVariables(jsonObjects: JsonObjects) {
        this.jsonObjects = jsonObjects
        linker.sortedLinkTypes = jsonObjects.linkType.toTypedArray()
    }

    private fun resetVariables() {
        jsonObjects = JsonObjects()
        devices = mutableListOf()
        randStarNetworks = mutableMapOf()
        randMeshNetworks = mutableMapOf()
        namedAddresses = mutableMapOf()
        querySenders = mutableListOf()
        dbDeviceCounter = 0
        deviceNames = mutableListOf()
        linker = DeviceLinker()
    }

    internal fun readJsonFile(fileName: String): JsonObjects {
        val fileStr = File(fileName).readAsString()
        return Json.decodeFromString(fileStr)
    }

    internal fun getEntities(): MutableList<Entity> {
        val entities: MutableList<Entity> = mutableListOf()
        entities.addAll(devices)
        entities.addAll(querySenders)
        return entities
    }

    private fun createRandomMeshNetworks() {
        for (network in jsonObjects.randomMeshNetwork)
            createRandomMeshNetwork(network)
    }

    private fun createRandomStarNetworks() {
        for (network in jsonObjects.randomStarNetwork)
            createRandomStarNetwork(network)
    }

    private fun createQuerySenders() {
        for (sender in jsonObjects.querySender)
            createQuerySender(sender)
    }

    private fun createRandomMeshNetwork(network: RandomMeshNetwork) {
        val origin = createMeshOriginDevice(network)
        val meshNetwork = MeshNetwork()
        meshNetwork.networkPrefix = network.networkPrefix
        val nameID = addDeviceName("${network.networkPrefix}_member")
        val linkType = getLinkTypeByName(network.linkType)
        val deviceType = getDeviceTypeByName(network.deviceType)

        var column = createSouthernDevices(origin, linkType, network, deviceType, nameID)
        meshNetwork.mesh[0] = column
        var restCoverageEast = network.signalCoverageEast - linkType.rangeInMeters
        var predecessor = origin
        while (restCoverageEast > 0) {
            val distance = getRandomDistance(linkType.rangeInMeters)
            val location = GeoLocation.createEasternLocation(predecessor.location, distance)
            predecessor = createDevice(deviceType, location, nameID)
            column = createSouthernDevices(predecessor, linkType, network, deviceType, nameID)
            meshNetwork.mesh.add(column)

            restCoverageEast -= distance
        }

        randMeshNetworks[network.networkPrefix] = meshNetwork
    }

    private fun createSouthernDevices(origin: Device, linkType: LinkType, network: RandomMeshNetwork, deviceType: DeviceType, nameID: Int): MutableList<Device> {
        val column = mutableListOf<Device>()
        var restCoverageSouth = network.signalCoverageSouth - linkType.rangeInMeters
        column.add(origin)
        var predecessor = origin
        while (restCoverageSouth > 0) {
            val distance = getRandomDistance(linkType.rangeInMeters)
            val location = GeoLocation.createSouthernLocation(predecessor.location, distance)
            predecessor = createDevice(deviceType, location, nameID)
            column.add(predecessor)
            restCoverageSouth -= distance
        }

        return column
    }

    private fun getRandomDistance(maxDistance: Int): Int {
        val density = 0.7
        val percentage = round(maxDistance * density).toInt()
        return RandomGenerator.getInt(percentage, maxDistance)
    }

    private fun createMeshOriginDevice(network: RandomMeshNetwork): Device {
        val deviceType = getDeviceTypeByName(network.deviceType)
        val location = GeoLocation(network.originLatitude, network.originLongitude)
        val nameID = addDeviceName("${network.networkPrefix}_origin")
        return createDevice(deviceType, location, nameID)
    }

    internal fun getNamedDevice(name: String): Device {
        val index = namedAddresses.getValue(name)
        return devices[index]
    }

    internal fun getDeviceName(nameIndex: Int) =
        deviceNames[nameIndex]

    internal fun getRootDevice(): Device = devices[rootRouterAddress]

    private fun createRandomStarNetwork(network: RandomStarNetwork) {
        val root = getNamedDevice(network.starRoot)
        val starNetwork = StarNetwork(root)
        starNetwork.networkPrefix = network.networkPrefix
        val childNameID = addDeviceName("${starNetwork.networkPrefix}_child")
        val deviceType = getDeviceTypeByName(network.deviceType)
        val linkType = getLinkTypeByName(network.linkType)
        for (i in 1..network.number) {
            val location = GeoLocation.getRandomLocationInRadius(root.location, linkType.rangeInMeters)
            val leaf = createDevice(deviceType, location, childNameID)
            linker.linkIfPossible(root, leaf)
            leaf.isStarNetworkChild = true
            starNetwork.children.add(leaf)
        }
        randStarNetworks[network.networkPrefix] = starNetwork
    }

    private fun setRootDevice() {
        val name = jsonObjects.rootRouter
        if (name.isNotEmpty()) {
            val device = getNamedDevice(name)
            device.router.isRoot = true
            rootRouterAddress = device.address
        }
    }

    private fun addDeviceName(name: String): Int {
        deviceNames.add(name)
        return deviceNames.size - 1
    }

    private fun createFixedDevices() {
        for (fixedDevice in jsonObjects.fixedDevice)
            createFixedLocatedDevice(fixedDevice)
    }

    private fun createFixedLinks() {
        for (fixedLink in jsonObjects.fixedLink) {
            val a = getNamedDevice(fixedLink.fixedDeviceA)
            val b = getNamedDevice(fixedLink.fixedDeviceB)
            linker.link(a, b, fixedLink.dataRateInKbps)
        }
    }

    private fun createFixedLocatedDevice(fixedDevice: FixedDevice) {
        val deviceType = getDeviceTypeByName(fixedDevice.deviceType)
        val location = GeoLocation(fixedDevice.latitude, fixedDevice.longitude)
        val nameID = addDeviceName(fixedDevice.name)
        val created = createDevice(deviceType, location, nameID)
        require(!namedAddresses.containsKey(fixedDevice.name)) { "name ${fixedDevice.name} must be unique" }
        namedAddresses[fixedDevice.name] = created.address
    }

    private fun createQuerySender(querySenderJson: QuerySender) {
        val receiverDevice = getNamedDevice(jsonObjects.rootRouter)
        val querySender = lupos.simulator_iot.QuerySender(
            name = querySenderJson.name,
            sendRateInSec = querySenderJson.sendRateInSeconds,
            maxNumberOfQueries = querySenderJson.maxNumberOfQueries,
            startClock = querySenderJson.sendStartClockInSec,
            receiver = receiverDevice,
            query = querySenderJson.query
        )
        querySenders.add(querySender)
    }

    private fun createDevice(deviceType: DeviceType, location: GeoLocation, nameIndex: Int): Device {
        val linkTypes = linker.getSortedLinkTypeIndices(deviceType.supportedLinkTypes)
        require(deviceType.performance != 0.0) { "The performance level of a device can not be 0.0 %" }
        val device = Device(location, devices.size, null, null, deviceType.performance, linkTypes, nameIndex)
        val parkingSensor = getParkingSensor(deviceType, device)
        device.sensor = parkingSensor
        val database = getDatabase(deviceType, device)
        device.database = database
        devices.add(device)
        return device
    }

    private fun getDeviceTypeByName(typeName: String): DeviceType {
        val deviceType = jsonObjects.deviceType.find { typeName == it.name }
        requireNotNull(deviceType) { "device type name $typeName does not exist" }
        return deviceType
    }

    private fun getLinkTypeByName(name: String): LinkType {
        val element = jsonObjects.linkType.find { name == it.name }
        requireNotNull(element) { "link type $name does not exist" }
        return element
    }

    private fun getSensorTypeByName(name: String): SensorType {
        val element = jsonObjects.sensorType.find { name == it.name }
        requireNotNull(element) { "sensor type $name does not exist" }
        return element
    }

    private fun getDatabase(deviceType: DeviceType, device: Device): DatabaseAdapter? {
        if (deviceType.database) {
            dbDeviceCounter++
            return DatabaseAdapter(device, jsonObjects.dummyDatabase)
        }
        return null
    }

    private fun getParkingSensor(deviceType: DeviceType, device: Device): ParkingSensor? {
        if (deviceType.parkingSensor.isNotEmpty()) {
            val sensorType = getSensorTypeByName(deviceType.parkingSensor)
            return ParkingSensor(device, sensorType.rateInSec, sensorType.maxSamples, sensorType.dataSink)
        }
        return null
    }

    private fun createDbDeviceAddresses() {
        val addresses = devices.filter { it.hasDatabase() }.map { it.address }
        dbDeviceAddresses = IntArray(addresses.size) { addresses[it] }
    }
}
