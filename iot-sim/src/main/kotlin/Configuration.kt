import com.javadocmd.simplelatlng.LatLng
import com.javadocmd.simplelatlng.LatLngTool
import com.javadocmd.simplelatlng.util.LengthUnit
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.lang.IllegalArgumentException

object Configuration {

    var devices: MutableMap<String, Device> = HashMap()
        private set

    var jsonObjects: JsonObjects = JsonObjects()
        private set

    var entities: MutableList<Entity> = ArrayList()
        private set

    var graph = Graph<String, ConnectionParameter>(arrayListOf())
        private set

    private var connections: MutableList<Connection> = ArrayList()

    private class Connection(
        val src: String,
        val dest: String,
        val params: ConnectionParameter)

    fun parse(fileName: String) {
        resetVariables()
        readJsonFile(fileName)
        createFixedDevices()
        createFixedConnections()
        createRandomNetworks()
        createGraph()
    }


    private fun resetVariables() {
        devices = HashMap()
        entities = ArrayList()
        jsonObjects = JsonObjects()
        connections = ArrayList()
        graph = Graph(arrayListOf())
    }


    private fun readJsonFile(fileName: String) {
        val fileStr = readFileDirectlyAsText(fileName)
        jsonObjects = Json.decodeFromString(fileStr)
    }

    private fun readFileDirectlyAsText(fileName: String)
        = javaClass.classLoader!!.getResource(fileName)!!.readText()



    private fun put(key: String, device: Device) {
        require(!devices.contains(key))
        devices[key] = device
    }



    private fun createRandomNetworks() {
        for (network in jsonObjects.randomNetwork) {
            createRandomNetwork(network)
        }
    }

    private fun createRandomNetwork(network: RandomNetwork){
        val dataSink = devices[network.dataSink]!!
        val deviceType = findDeviceType(network.type)
        val protocol = findProtocol(network.networkProtocol)
        for (i in 1..network.number) {
            val deviceName = network.name + i.toString()
            val location = RandomGenerator.createLocationInCircularWindow(dataSink.location, protocol.rangeInMeters)
            val createdDevice = createDevice(deviceType, location, deviceName)
            setSinkOfSensors(createdDevice.sensors, dataSink)
            put(createdDevice.name, createdDevice)
            createConnection(createdDevice, dataSink, protocol)
        }
    }

    private fun setSinkOfSensors(sensors: List<Sensor>, dataSink: Device) {
        for (sensor in sensors) {
            sensor.dataSink = dataSink
        }
    }


    private fun createFixedDevices() {
        for (fixedDevice in jsonObjects.fixedDevices) {
            val createdDevice = createFixedLocatedDevice(fixedDevice)
            put(createdDevice.name, createdDevice)
        }
    }

    private fun createFixedConnections() {
        for (fixedCon in jsonObjects.fixedConnection) {
            val protocol = findProtocol(fixedCon.networkProtocol)
            val a = devices[fixedCon.endpointA]!!
            val b = devices[fixedCon.endpointB]!!
            createConnection(a, b, protocol)
        }

    }

    private fun createConnection(src: Device, dest: Device, p: NetworkProtocol) {
        val distance = LatLngTool.distance(src.location, dest.location, LengthUnit.METER)
        val param = ConnectionParameter(
            p.dataRateInKbps, p.rangeInMeters.toDouble(),
            p.name, distance
        )
        val con = Connection(src.name, dest.name, param)
        connections.add(con)
    }



    private fun createFixedLocatedDevice(fixedDevices: FixedDevices): Device {
        val deviceType = findDeviceType(fixedDevices.deviceType)
        val location = LatLng(fixedDevices.latitude, fixedDevices.longitude)
        return createDevice(deviceType, location, fixedDevices.name)
    }

    private fun createDevice(deviceType: DeviceType, location: LatLng, name: String): Device {
        val powerSupply = PowerSupply(deviceType.powerCapacity)
        val application = createAppEntity(deviceType)
        val device = Device(powerSupply, location, name, application)
        val sensors = createSensorEntities(deviceType.sensors, device)
        device.sensors.addAll(sensors)
        entities.add(device.networkCard)
        return device
    }


    private fun findDeviceType(typeName: String): DeviceType {
        val deviceType = jsonObjects.deviceType.find { typeName == it.name }
        requireNotNull(deviceType, { "device type name $typeName does not exist" })
        return deviceType
    }

    private fun findSensorType(typeName: String): SensorType {
        val sensorType = jsonObjects.sensorType.find { typeName == it.name }
        requireNotNull(sensorType, { "sensor type name $typeName does not exist" })
        return sensorType
    }

    private fun findProtocol(name: String): NetworkProtocol {
        val element = jsonObjects.networkProtocol.find { name == it.name }
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

    private fun createSensorEntities(sensorRefs: List<String>, device: Device) : List<Sensor> {
        val sensors = ArrayList<Sensor>(sensorRefs.size)
        for (sensorRef in sensorRefs) {
            val sensor = createSensorEntity(sensorRef, device)
            sensors.add(sensor)
            entities.add(sensor)
        }
        return sensors
    }

    private fun createSensorEntity(sensorRef: String, device: Device) : Sensor {
        val sensorType = findSensorType(sensorRef)
        val name = sensorType.name
        val rate = sensorType.dataRateInSeconds
        return when (sensorType.name) {
            "Parking" -> {
                ParkingSensor(name, rate, device, device)
            }
            "Localization" -> {
                LocalizationSensor(name, rate, device, device)
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    private fun createGraph() {
        val keyList = ArrayList(devices.keys)
        graph = Graph(keyList)
        for (con in connections) {
            graph.addUndirectedEdge(con.src, con.dest, con.params)
        }
    }





}
