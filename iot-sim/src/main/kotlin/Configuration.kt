
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlin.math.round

object Configuration {

    var devices: MutableList<Device> = ArrayList()
        private set

    private var namedAddresses: MutableMap<String, Int> = HashMap()

    var jsonObjects: JsonObjects = JsonObjects()
        private set

    var entities: MutableList<Entity> = ArrayList()
        private set

    var randStarNetworks: MutableMap<String, StarNetwork> = HashMap()
        private set

    var randMeshNetworks: MutableMap<String, MeshNetwork> = HashMap()
        private set



    fun parse(fileName: String) {
        resetVariables()
        readJsonFile(fileName)
        createSortedLinkTypes()
        createFixedDevices()
        createFixedLinks()
        createRandomMeshNetworks()
        createRandomStarNetworks()
        createAvailableLinks()
    }

    private fun resetVariables() {
        devices = ArrayList()
        randStarNetworks = HashMap()
        randMeshNetworks = HashMap()
        entities = ArrayList()
        jsonObjects = JsonObjects()
        namedAddresses = HashMap()
    }


    private fun readJsonFile(fileName: String) {
        val fileStr = readFileDirectlyAsText(fileName)
        jsonObjects = Json.decodeFromString(fileStr)
    }

    private fun readFileDirectlyAsText(fileName: String)
        = javaClass.classLoader!!.getResource(fileName)!!.readText()


    private fun createSortedLinkTypes() {
        Device.sortedLinkTypes = jsonObjects.linkType as MutableList<LinkType>
    }

    private fun createRandomMeshNetworks() {
        for (network in jsonObjects.randomMeshNetwork) {
            createRandomMeshNetwork(network)
        }
    }

    private fun createRandomStarNetworks() {
        for (network in jsonObjects.randomStarNetwork) {
            createRandomStarNetwork(network)

        }
    }

    private fun createRandomMeshNetwork(network: RandomMeshNetwork) {
        val origin = createMeshOriginDevice(network)
        origin.dodagRoot = true
        val meshNetwork = MeshNetwork()
        meshNetwork.networkPrefix = network.networkPrefix
        val linkType = getLinkTypeByName(network.linkType)
        val deviceType = getDeviceTypeByName(network.deviceType)

        var column = createSouthernDevices(origin, linkType, network, deviceType)
        meshNetwork.mesh[0] = column
        var restCoverageEast = network.signalCoverageEast - linkType.rangeInMeters
        var predecessor = origin
        while(restCoverageEast > 0) {
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
        val column = ArrayList<Device>()
        var restCoverageSouth = network.signalCoverageSouth - linkType.rangeInMeters
        column.add(origin)
        var predecessor = origin
        while(restCoverageSouth > 0) {
            val distance = getRandomDistance(linkType.rangeInMeters)
            val location = GeoLocation.createSouthernLocation(predecessor.location, distance)
            predecessor = createDevice(deviceType, location)
            column.add(predecessor)
            restCoverageSouth -= distance
        }

        return column
    }

    private fun getRandomDistance( maxDistance: Int):Int {
        val density = 0.7
        val percentage = round(maxDistance * density).toInt()
        return RandomGenerator.getInt(percentage, maxDistance)
    }

    private fun createMeshOriginDevice(network: RandomMeshNetwork) : Device {
        val deviceType = getDeviceTypeByName(network.deviceType)
        val location = GeoLocation(network.originLatitude, network.originLongitude)
        return createDevice(deviceType, location)
    }

    fun getNamedDevice(name: String): Device {
        val index = namedAddresses.getValue(name)
        return devices[index]
    }


    private fun createRandomStarNetwork(network: RandomStarNetwork) {
        val root = getNamedDevice(network.dataSink)
        root.dodagRoot = true
        val starNetwork = StarNetwork(root)
        starNetwork.networkPrefix = network.networkPrefix
        val deviceType = getDeviceTypeByName(network.deviceType)
        val linkType = getLinkTypeByName(network.linkType)
        for (i in 1..network.number) {
            val location = GeoLocation.getRandomLocationInRadius(root.location, linkType.rangeInMeters)
            val createdDevice = createDevice(deviceType, location)
            createdDevice.sensor?.dataSinkAddress = root.address
            link(root, createdDevice)
            starNetwork.childs.add(createdDevice)
        }
        randStarNetworks[network.networkPrefix] = starNetwork
    }


    private fun link(a: Device, b: Device) {
        a.addAvailableLink(b)
    }

    private fun createFixedDevices() {
        for (fixedDevice in jsonObjects.fixedDevice) {
            createFixedLocatedDevice(fixedDevice)
        }
    }

    private fun createFixedLinks() {
        for (fixedLink in jsonObjects.fixedLink) {
            val a = getNamedDevice(fixedLink.fixedDeviceA)
            val b = getNamedDevice(fixedLink.fixedDeviceB)
            link(a, b)
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
        val application = createAppEntity(deviceType)
        val linkTypes = getLinkTypeIndices(deviceType)
        val device = Device(powerSupply, location, devices.size, application, null, linkTypes)
        val parkingSensor = getParkingSensor(deviceType, device)
        device.sensor = parkingSensor
        entities.add(device)
        devices.add(device)
        return device
    }

    private fun getLinkTypeIndices(deviceType: DeviceType): IntArray {
        val list = ArrayList<LinkType>(deviceType.supportedLinkTypes.size)
        for(name in deviceType.supportedLinkTypes) {
            list.add(getLinkTypeByName(name))
        }
        return Device.getSortedLinkTypeIndices(list)
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



    private fun createAppEntity(deviceType: DeviceType) : Entity? {
        var app: Entity? = null
        if (deviceType.application) {
            app = DatabaseApp()
            entities.add(app)
        }

        return app
    }

    private fun getParkingSensor(deviceType: DeviceType, device: Device): ParkingSensor? {
        var sensor: ParkingSensor? = null
        if(deviceType.parkingSensor) {
            sensor = ParkingSensor(device, device.address)
        }
        return sensor
    }

    private fun createAvailableLinks() {
        for (one in devices)
            for(two in devices)
                one.addAvailableLink(two)


    }
}
