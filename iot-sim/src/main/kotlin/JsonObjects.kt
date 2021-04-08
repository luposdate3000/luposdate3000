import kotlinx.serialization.Serializable

@Serializable
data class JsonObjects(
    val linkType: List<LinkType> = arrayListOf(),
    val deviceType: List<DeviceType> = arrayListOf(),
    val fixedDevices: List<FixedDevices> = arrayListOf(),
    val fixedLinks: List<FixedLinks> = arrayListOf(),
    val randomStarNetwork: List<RandomNetwork> = arrayListOf(),
)

@Serializable
data class LinkType(
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
    val supportedLinkTypes: List<String> = arrayListOf(),
)


@Serializable
data class FixedDevices(
    val name: String = "",
    val deviceType: String = "",
    val latitude: Double,
    val longitude: Double,
)

@Serializable
data class FixedLinks(
    val endpointA: String = "",
    val endpointB: String = "",
)

@Serializable
data class RandomNetwork(
    val networkPrefix: String = "",
    val dataSink: String = "",
    val type: String = "",
    val number: Int = 0,
    val linkType: String = "",
    val sensorsPerDevice: SensorsPerDevice = SensorsPerDevice()
)

@Serializable
data class SensorsPerDevice(
    val number: Int = 0,
    val type: String = "",
)
