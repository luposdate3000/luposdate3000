import kotlinx.serialization.Serializable

@Serializable
data class JsonObjects(
    val linkType: List<LinkType> = arrayListOf(),
    val deviceType: List<DeviceType> = arrayListOf(),
    val fixedDevice: List<FixedDevice> = arrayListOf(),
    val fixedLink: List<FixedLink> = arrayListOf(),
    val randomMeshNetwork: List<RandomMeshNetwork> = arrayListOf(),
    val randomStarNetwork: List<RandomStarNetwork> = arrayListOf(),
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
    val database: Boolean = false,
    val parkingSensor: Boolean = false,
    var powerCapacity: Double = -1.0,
    val supportedLinkTypes: List<String> = arrayListOf(),
)


@Serializable
data class FixedDevice(
    val name: String = "",
    val deviceType: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)

@Serializable
data class FixedLink(
    val fixedDeviceA: String = "",
    val fixedDeviceB: String = "",
)

@Serializable
data class RandomStarNetwork(
    val networkPrefix: String = "",
    val dataSink: String = "",
    val linkType: String = "",
    val deviceType: String = "",
    val number: Int = 0
)

@Serializable
data class RandomMeshNetwork(
    val networkPrefix: String = "",
    val originLatitude: Double = 0.0,
    val originLongitude: Double = 0.0,
    val deviceType: String = "",
    val linkType: String = "",
    val signalCoverageEast: Int = 0,
    val signalCoverageSouth: Int = 0
)
