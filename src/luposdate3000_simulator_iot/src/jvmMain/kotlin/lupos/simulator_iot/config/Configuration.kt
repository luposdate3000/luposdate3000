package lupos.simulator_iot.config

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import lupos.simulator_iot.DatabaseAdapter
import lupos.simulator_iot.Device
import lupos.simulator_iot.LinkManager
import lupos.simulator_iot.PowerSupply
import lupos.simulator_iot.RandomGenerator
import lupos.simulator_iot.geo.GeoLocation
import lupos.simulator_iot.sensor.ParkingSensor
import kotlin.math.round

public object Configuration {

    public var devices: MutableList<Device> = mutableListOf()
        private set

    private var namedAddresses: MutableMap<String, Int> = mutableMapOf()

    public var jsonObjects: JsonObjects = JsonObjects()
        private set

    public var randStarNetworks: MutableMap<String, StarNetwork> = mutableMapOf()
        private set

    public var randMeshNetworks: MutableMap<String, MeshNetwork> = mutableMapOf()
        private set

    public var dbDeviceAddresses: IntArray = intArrayOf()

    private var rootRouterAddress: Int = -1

    private var dbDeviceCounter = 0

    public fun parse(fileName: String) {
        resetVariables()
        readJsonFile(fileName)
        createSortedLinkTypes()
        createFixedDevices()
        setRootDevice()
        createFixedLinks()
        createRandomMeshNetworks()
        createRandomStarNetworks()
        createAvailableLinks()
        createDbDeviceAddresses()
    }

    private fun resetVariables() {
        devices = mutableListOf()
        randStarNetworks = mutableMapOf()
        randMeshNetworks = mutableMapOf()
        jsonObjects = JsonObjects()
        namedAddresses = mutableMapOf()
        dbDeviceCounter = 0
    }

    private fun readJsonFile(fileName: String) {
        val fileStr = readFileDirectlyAsText(fileName)
        jsonObjects = Json.decodeFromString(fileStr)
    }

    private fun readFileDirectlyAsText(fileName: String) =
        javaClass.classLoader!!.getResource(fileName)!!.readText()

    private fun createSortedLinkTypes() {
        LinkManager.sortedLinkTypes = jsonObjects.linkType.toTypedArray()
    }

    private fun createRandomMeshNetworks() {
        for (network in jsonObjects.randomMeshNetwork)
            createRandomMeshNetwork(network)
    }

    private fun createRandomStarNetworks() {
        for (network in jsonObjects.randomStarNetwork)
            createRandomStarNetwork(network)
    }

    private fun createRandomMeshNetwork(network: RandomMeshNetwork) {
        val origin = createMeshOriginDevice(network)
        val meshNetwork = MeshNetwork()
        meshNetwork.networkPrefix = network.networkPrefix
        val linkType = getLinkTypeByName(network.linkType)
        val deviceType = getDeviceTypeByName(network.deviceType)

        var column = createSouthernDevices(origin, linkType, network, deviceType)
        meshNetwork.mesh[0] = column
        var restCoverageEast = network.signalCoverageEast - linkType.rangeInMeters
        var predecessor = origin
        while (restCoverageEast > 0) {
            val distance = getRandomDistance(linkType.rangeInMeters)
            val location = GeoLocation.createEasternLocation(predecessor.location, distance)
            predecessor = createDevice(deviceType, location)
            column = createSouthernDevices(predecessor, linkType, network, deviceType)
            meshNetwork.mesh.add(column)

            restCoverageEast -= distance
        }

        randMeshNetworks[network.networkPrefix] = meshNetwork
    }

    private fun createSouthernDevices(origin: Device, linkType: LinkType, network: RandomMeshNetwork, deviceType: DeviceType): MutableList<Device> {
        val column = mutableListOf<Device>()
        var restCoverageSouth = network.signalCoverageSouth - linkType.rangeInMeters
        column.add(origin)
        var predecessor = origin
        while (restCoverageSouth > 0) {
            val distance = getRandomDistance(linkType.rangeInMeters)
            val location = GeoLocation.createSouthernLocation(predecessor.location, distance)
            predecessor = createDevice(deviceType, location)
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
        return createDevice(deviceType, location)
    }

    public fun getNamedDevice(name: String): Device {
        val index = namedAddresses.getValue(name)
        return devices[index]
    }

    public fun getRootDevice(): Device = devices[rootRouterAddress]

    private fun createRandomStarNetwork(network: RandomStarNetwork) {
        val root = getNamedDevice(network.starRoot)
        val sink = getNamedDevice(network.dataSink)
        val starNetwork = StarNetwork(root, sink)
        starNetwork.networkPrefix = network.networkPrefix
        val deviceType = getDeviceTypeByName(network.deviceType)
        val linkType = getLinkTypeByName(network.linkType)
        for (i in 1..network.number) {
            val location = GeoLocation.getRandomLocationInRadius(root.location, linkType.rangeInMeters)
            val leaf = createDevice(deviceType, location)
            leaf.sensor!!.setDataSink(sink.address)
            root.linkManager.setLinkIfPossible(leaf)
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

    private fun createFixedDevices() {
        for (fixedDevice in jsonObjects.fixedDevice)
            createFixedLocatedDevice(fixedDevice)
    }

    private fun createFixedLinks() {
        for (fixedLink in jsonObjects.fixedLink) {
            val a = getNamedDevice(fixedLink.fixedDeviceA)
            val b = getNamedDevice(fixedLink.fixedDeviceB)
            a.linkManager.setLink(b, fixedLink.dataRateInKbps)
        }
    }

    private fun createFixedLocatedDevice(fixedDevice: FixedDevice) {
        val deviceType = getDeviceTypeByName(fixedDevice.deviceType)
        val location = GeoLocation(fixedDevice.latitude, fixedDevice.longitude)
        val created = createDevice(deviceType, location)
        require(!namedAddresses.containsKey(fixedDevice.name)) { "name ${fixedDevice.name} must be unique" }
        namedAddresses[fixedDevice.name] = created.address
    }

    private fun createDevice(deviceType: DeviceType, location: GeoLocation): Device {
        val powerSupply = PowerSupply(deviceType.powerCapacity)
        val linkTypes = getLinkTypeIndices(deviceType)
        val device = Device(powerSupply, location, devices.size, null, null, linkTypes)
        val parkingSensor = getParkingSensor(deviceType, device)
        device.sensor = parkingSensor
        val database = getDatabase(deviceType, device)
        device.database = database
        devices.add(device)
        return device
    }

    private fun getLinkTypeIndices(deviceType: DeviceType): IntArray {
        val list = mutableListOf<LinkType>()
        for (name in deviceType.supportedLinkTypes)
            list.add(getLinkTypeByName(name))

        return LinkManager.getSortedLinkTypeIndices(list)
    }

    private fun getDeviceTypeByName(typeName: String): DeviceType {
        val deviceType = jsonObjects.deviceType.find { typeName == it.name }
        requireNotNull(deviceType) { "device type name $typeName does not exist" }
        return deviceType
    }

    private fun getLinkTypeByName(name: String): LinkType {
        val element = jsonObjects.linkType.find { name == it.name }
        requireNotNull(element) { "protocol $name does not exist" }
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
        if (deviceType.parkingSensor) {
            return ParkingSensor(device)
        }
        return null
    }

    private fun createAvailableLinks() {
        for (one in devices)
            for (two in devices)
                if (!one.isStarNetworkChild && !two.isStarNetworkChild) {
                    one.linkManager.setLinkIfPossible(two)
                }
    }

    private fun createDbDeviceAddresses() {
        val addresses = devices.filter { it.hasDatabase() }.map { it.address }
        dbDeviceAddresses = IntArray(addresses.size) { addresses[it] }
    }
}
