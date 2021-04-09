
import kotlinx.serialization.*
import kotlinx.serialization.json.*

object Configuration {

    var devices: MutableMap<String, Device> = HashMap()
        private set

    var jsonObjects: JsonObjects = JsonObjects()
        private set

    var entities: MutableList<Entity> = ArrayList()
        private set

    var randStarNetworks: MutableMap<String, StarNetwork> = HashMap()
        private set

    var randMeshNetworks: MutableMap<String, MeshNetwork> = HashMap()
        private set

    private const val delimiter = "_"

    private var netDeviceNumber = 0

    fun parse(fileName: String) {
        resetVariables()
        readJsonFile(fileName)
        createFixedDevices()
        createFixedConnections()
        createRandomMeshNetworks()
        createRandomStarNetworks()
    }


    private fun resetVariables() {
        devices = HashMap()
        randStarNetworks = HashMap()
        entities = ArrayList()
        jsonObjects = JsonObjects()
    }

    private fun readJsonFile(fileName: String) {
        val fileStr = readFileDirectlyAsText(fileName)
        jsonObjects = Json.decodeFromString(fileStr)
    }



    private fun readFileDirectlyAsText(fileName: String)
        = javaClass.classLoader!!.getResource(fileName)!!.readText()

    private fun addDevice(key: String, value: Device) {
        require(!devices.contains(key))
        devices[key] = value
    }

    private fun createRandomMeshNetworks() {
        for (network in jsonObjects.randomMeshNetwork) {
            netDeviceNumber = 0
            createRandomMeshNetwork(network)
        }
    }

    private fun createRandomStarNetworks() {
        for (network in jsonObjects.randomStarNetwork) {
            netDeviceNumber = 0
            createRandomStarNetwork(network)

        }
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

            while(restCoverageEast > 0) {

                val distance = RandomGenerator.getInt(1, linkType.rangeInMeters)
                val location = GeoLocation.createEasternLocation(origin.location, distance)
                val name = getNetDeviceName(network.networkPrefix, deviceType)
                val device = createDevice(deviceType, location, name)
                column = createSouthernDevices(device, linkType, network, deviceType)
                meshNetwork.mesh.add(column)

                restCoverageEast = restCoverageEast - distance - linkType.rangeInMeters
            }

        randMeshNetworks[network.networkPrefix] = meshNetwork
    }

    private fun createSouthernDevices(origin: Device, linkType: LinkType, network: RandomMeshNetwork, deviceType: DeviceType): MutableList<Device> {
        val column = ArrayList<Device>()
        var restCoverageSouth = network.signalCoverageSouth - linkType.rangeInMeters

        column.add(origin)
        while(restCoverageSouth > 0) {
            val distance = RandomGenerator.getInt(1, linkType.rangeInMeters)
            val location = GeoLocation.createSouthernLocation(origin.location, distance)
            val name = getNetDeviceName(network.networkPrefix, deviceType)
            val device = createDevice(deviceType, location, name)
            column.add(device)
            restCoverageSouth = restCoverageSouth - distance - linkType.rangeInMeters
        }

        return column
    }


    private fun createMeshOriginDevice(network: RandomMeshNetwork) : Device {
        val deviceType = getDeviceTypeByName(network.deviceType)
        val location = GeoLocation(network.originLatitude, network.originLongitude)
        val address = getNetDeviceName(network.networkPrefix, deviceType)
        return createDevice(deviceType, location, address)
    }

    private fun getNetDeviceName(networkPrefix: String, deviceType: DeviceType) : String {
        netDeviceNumber++
        return networkPrefix + delimiter + deviceType.name + delimiter + netDeviceNumber
    }


    private fun createRandomStarNetwork(network: RandomStarNetwork) {
        val root = devices[network.dataSink]!!
        val starNetwork = StarNetwork(root)
        starNetwork.networkPrefix = network.networkPrefix
        val deviceType = getDeviceTypeByName(network.deviceType)
        val linkType = getLinkTypeByName(network.linkType)
        for (i in 1..network.number) {
            val name = getNetDeviceName(network.networkPrefix, deviceType)
            val location = GeoLocation.getRandomLocationInRadius(root.location, linkType.rangeInMeters)
            val createdDevice = createDevice(deviceType, location, name)
            link(root, createdDevice)
            starNetwork.childs.add(createdDevice)
        }
        randStarNetworks[network.networkPrefix] = starNetwork
    }

    private fun link(a: Device, b: Device) {
        a.addAvailableLink(b)
        b.addAvailableLink(a)
    }

    private fun createFixedDevices() {
        for (fixedDevice in jsonObjects.fixedDevices) {
            createFixedLocatedDevice(fixedDevice)
        }
    }

    private fun createFixedConnections() {
        for (fixedLink in jsonObjects.fixedLinks) {
            val a = devices[fixedLink.endpointA]!!
            val b = devices[fixedLink.endpointB]!!
            link(a, b)
        }
    }

    private fun createFixedLocatedDevice(fixedDevices: FixedDevices) {
        val deviceType = getDeviceTypeByName(fixedDevices.deviceType)
        val location = GeoLocation(fixedDevices.latitude, fixedDevices.longitude)
        createDevice(deviceType, location, fixedDevices.name)
    }

    private fun createDevice(deviceType: DeviceType, location: GeoLocation, name: String): Device {
        val powerSupply = PowerSupply(deviceType.powerCapacity)
        val application = createAppEntity(deviceType)
        val linkTypes = createLinkTypes(deviceType)
        val device = Device(powerSupply, location, name, application, null, linkTypes)
        val parkingSensor = createParkingSensor(deviceType, device)
        device.sensor = parkingSensor
        entities.add(device)
        addDevice(device.name, device)
        return device
    }

    private fun createLinkTypes(deviceType: DeviceType): Set<LinkType> {
        val result = mutableSetOf<LinkType>()
        for (name in deviceType.supportedLinkTypes) {
            val linkType = getLinkTypeByName(name)
            result.add(linkType)
        }
        return result
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

    private fun createParkingSensor(deviceType: DeviceType, device: Device): ParkingSensor? {
        var sensor: ParkingSensor? = null
        if(deviceType.parkingSensor) {
            val name = device.name + delimiter + "Sensor"
            sensor = ParkingSensor(name, device)
            entities.add(sensor)
        }
        return sensor
    }



}
