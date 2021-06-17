package lupos.simulator_iot.config

import kotlinx.serialization.Serializable

@Serializable
public data class JsonObjects(
    val linkType: List<LinkType> = arrayListOf(),
    val deviceType: List<DeviceType> = arrayListOf(),
    val fixedDevice: List<FixedDevice> = arrayListOf(),
    val fixedLink: List<FixedLink> = arrayListOf(),
    val rootRouter: String = "",
    val randomMeshNetwork: List<RandomMeshNetwork> = arrayListOf(),
    val randomStarNetwork: List<RandomStarNetwork> = arrayListOf(),
)

@Serializable
public data class LinkType(
    val name: String = "",
    val rangeInMeters: Int = 0,
    val dataRateInKbps: Int = 0,
)

@Serializable
public data class DeviceType(
    val name: String = "",
    val database: Boolean = false,
    val parkingSensor: Boolean = false,
    var powerCapacity: Double = -1.0,
    val supportedLinkTypes: List<String> = arrayListOf(),
)

@Serializable
public data class FixedDevice(
    val name: String = "",
    val deviceType: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)

@Serializable
public data class FixedLink(
    val fixedDeviceA: String = "",
    val fixedDeviceB: String = "",
    val dataRateInKbps: Int = 0,
)

@Serializable
public data class RandomStarNetwork(
    val networkPrefix: String = "",
    val starRoot: String = "",
    val dataSink: String = "",
    val linkType: String = "",
    val deviceType: String = "",
    val number: Int = 0
)

@Serializable
public data class RandomMeshNetwork(
    val networkPrefix: String = "",
    val originLatitude: Double = 0.0,
    val originLongitude: Double = 0.0,
    val deviceType: String = "",
    val linkType: String = "",
    val signalCoverageEast: Int = 0,
    val signalCoverageSouth: Int = 0
)
