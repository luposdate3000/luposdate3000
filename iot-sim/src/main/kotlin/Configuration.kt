
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

    private const val delimiter = "_"

    fun parse(fileName: String) {
        resetVariables()
        readJsonFile(fileName)
        createFixedDevices()
        createFixedConnections()
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


    private fun createRandomStarNetworks() {
        for (network in jsonObjects.randomStarNetwork) {
            createRandomStarNetwork(network)
        }
    }

    private fun createRandomStarNetwork(network: RandomStarNetwork){
        val root = devices[network.dataSink]!!
        val starNetwork = StarNetwork(root)
        starNetwork.networkPrefix = network.networkPrefix
        val deviceType = findDeviceType(network.deviceType)
        val linkType = findProtocol(network.linkType)
        for (i in 1..network.number) {
            val deviceName = network.networkPrefix + delimiter + deviceType.name + delimiter + i.toString()
            val location = GeoLocation.getRandomLocationInRadius(root.location, linkType.rangeInMeters)
            val createdDevice = createDevice(deviceType, location, deviceName)
            addDevice(createdDevice.name, createdDevice)
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
            val createdDevice = createFixedLocatedDevice(fixedDevice)
            addDevice(createdDevice.name, createdDevice)
        }
    }

    private fun createFixedConnections() {
        for (fixedLink in jsonObjects.fixedLinks) {
            val a = devices[fixedLink.endpointA]!!
            val b = devices[fixedLink.endpointB]!!
            link(a, b)
        }
    }

    private fun createFixedLocatedDevice(fixedDevices: FixedDevices): Device {
        val deviceType = findDeviceType(fixedDevices.deviceType)
        val location = GeoLocation(fixedDevices.latitude, fixedDevices.longitude)
        return createDevice(deviceType, location, fixedDevices.name)
    }

    private fun createDevice(deviceType: DeviceType, location: GeoLocation, name: String): Device {
        val powerSupply = PowerSupply(deviceType.powerCapacity)
        val application = createAppEntity(deviceType)
        val protocols = createProtocols(deviceType)
        val device = Device(powerSupply, location, name, application, null, protocols)
        val parkingSensor = createParkingSensor(deviceType, device)
        device.sensor = parkingSensor
        entities.add(device)
        return device
    }

    private fun createProtocols(deviceType: DeviceType): Set<LinkType> {
        val result = mutableSetOf<LinkType>()
        for (name in deviceType.supportedLinkTypes) {
            val linkType = findProtocol(name)
            result.add(linkType)
        }
        return result
    }



    private fun findDeviceType(typeName: String): DeviceType {
        val deviceType = jsonObjects.deviceType.find { typeName == it.name }
        requireNotNull(deviceType, { "device type name $typeName does not exist" })
        return deviceType
    }

    private fun findProtocol(name: String): LinkType {
        val element = jsonObjects.linkType.find { name == it.name }
        requireNotNull(element, { "protocol $name does not exist" })
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
            val name = device.name + "_ParkingSensor"
            sensor = ParkingSensor(name, device)
            entities.add(sensor)
        }
        return sensor
    }



}
