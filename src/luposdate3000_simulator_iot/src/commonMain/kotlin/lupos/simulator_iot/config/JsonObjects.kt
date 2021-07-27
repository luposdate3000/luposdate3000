@file:Suppress("UnusedImport")

package lupos.simulator_iot.config

import kotlinx.serialization.Serializable

@Serializable
public data class JsonObjects(
    var dummyDatabase: Boolean = true,
    var deterministic: Boolean = true,
    var logging: Boolean = true,
    val linkType: MutableList<LinkType> = arrayListOf(),
    val sensorType: MutableList<SensorType> = arrayListOf(),
    val deviceType: MutableList<DeviceType> = arrayListOf(),
    val fixedDevice: MutableList<FixedDevice> = arrayListOf(),
    val fixedLink: MutableList<FixedLink> = arrayListOf(),
    val rootRouter: String = "",
    val randomMeshNetwork: MutableList<RandomMeshNetwork> = arrayListOf(),
    val randomStarNetwork: MutableList<RandomStarNetwork> = arrayListOf(),
    val querySender: MutableList<QuerySender> = arrayListOf(),
)

@Serializable
public data class LinkType(
    val name: String = "",
    var rangeInMeters: Int = 0,
    val dataRateInKbps: Int = 0,
)

@Serializable
public data class SensorType(
    val name: String = "",
    val area: Int = 0,
    val dataSink: String = "",
    val rateInSec: Int = 0,
    var maxSamples: Int = -1,
)

@Serializable
public data class DeviceType(
    val name: String = "",
    var database: Boolean = false,
    val parkingSensor: String = "",
    val performance: Double = 100.0,
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
    var number: Int = 0
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

@Serializable
public data class QuerySender(
    val name: String = "",
    val sendRateInSeconds: Int = 0,
    var maxNumberOfQueries: Int = 0,
    val sendStartClockInSec: Int = 0,
    var query: String = "",
)
