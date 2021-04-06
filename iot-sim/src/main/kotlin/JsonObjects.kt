import kotlinx.serialization.Serializable

@Serializable
data class JsonObjects(
    val protocolType: List<ProtocolType> = arrayListOf(),
    val deviceType: List<DeviceType> = arrayListOf(),
    val fixedDevices: List<FixedDevices> = arrayListOf(),
    val fixedConnection: List<FixedConnection> = arrayListOf(),
    val randomNetwork: List<RandomNetwork> = arrayListOf(),
)

@Serializable
data class ProtocolType(
    val name: String = "",
    val rangeInMeters: Int = 0,
    val dataRateInKbps: Int = 0,
)

@Serializable
data class DeviceType(
    val name: String = "",
    val application: Boolean = false,
    val parkingSensor: Boolean = false,
    var powerCapacity: Double = -1.0,
    val supportedProtocolTypes: List<String> = arrayListOf(),
)


@Serializable
data class FixedDevices(
    val name: String = "",
    val deviceType: String = "",
    val latitude: Double,
    val longitude: Double,
)

@Serializable
data class FixedConnection(
    val protocolType: String = "",
    val endpointA: String = "",
    val endpointB: String = "",
)

@Serializable
data class RandomNetwork(
    val networkPrefix: String = "",
    val dataSink: String = "",
    val type: String = "",
    val number: Int = 0,
    val protocolType: String = "",
    val sensorsPerDevice: SensorsPerDevice = SensorsPerDevice()
)

@Serializable
data class SensorsPerDevice(
    val number: Int = 0,
    val type: String = "",
)
