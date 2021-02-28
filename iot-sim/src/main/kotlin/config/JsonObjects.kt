package config

import kotlinx.serialization.Serializable

@Serializable
data class Config(
        val networkProtocol: List<NetworkProtocol> = arrayListOf(),
        val sensorType: List<SensorType> = arrayListOf(),
        val deviceType: List<DeviceType> = arrayListOf(),
        val fixedDevices: List<FixedDevices> = arrayListOf(),
        val fixedConnection: List<FixedConnection> = arrayListOf(),
        val randomNetwork: List<RandomNetwork> = arrayListOf(),
)

@Serializable
data class NetworkProtocol(
    val name: String = "",
    val rangeInMeters: Int = 0,
    val dataRateInKbps: Int = 0,
)

@Serializable
data class SensorType(
    val name: String = "",
    val dataRateInSeconds: Int = 0,
)

@Serializable
data class DeviceType(
    val name: String = "",
    val application: Boolean = false,
    val sensors: List<String> = arrayListOf(),
    var powerCapacity: Double = -1.0,
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
    val networkProtocol: String = "",
    val endpointA: String = "",
    val endpointB: String = "",
)

@Serializable
data class RandomNetwork(
    val name: String = "",
    val dataSink: String = "",
    val type: String = "",
    val number: Int = 0,
    val networkProtocol: String = "",
)
