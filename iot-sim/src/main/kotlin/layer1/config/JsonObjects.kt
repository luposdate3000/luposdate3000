package layer1.config

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val networkProtocol: List<NetworkProtocol> = arrayListOf(),
    val sensorType: List<SensorType> = arrayListOf(),
    val deviceType: List<DeviceType> = arrayListOf(),
    val fixedLocation: List<FixedLocation> = arrayListOf(),
    val fixedConnection: List<FixedConnection> = arrayListOf(),
    val randomNetwork: List<RandomNetwork> = arrayListOf(),
)

@Serializable
data class NetworkProtocol(
    val name: String = "",
    val rangeInMeters: Int = 0,
    val dataRateInKbps: Int = 0,
    val wireless: Boolean = false
)

@Serializable
data class SensorType(
    val name: String = "",
    val networkProtocol: List<ProtocolRef> = arrayListOf(),
    val dataRateInSeconds: Int = 0,
    val power: Power = Power()
)

@Serializable
data class ProtocolRef(
    val name: String = "",
)

@Serializable
data class Power(
    val capacity: Double = 0.0,
    val drainageRate: Double = 0.0,
)

@Serializable
data class DeviceType(
    val name: String = "",
    val wirelessAP: String = "",
    val networkProtocol: List<ProtocolRef> = arrayListOf(),
    val application: Boolean = false,
    val mobile: Boolean = false,
    val sensors: List<SensorRef> = arrayListOf(),
    val power: Power = Power()
)

@Serializable
data class SensorRef(
    val name: String = "",
)

@Serializable
data class FixedLocation(
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
    val originWirelessAP: String = "",
    val generatedDeviceType: String = "",
    val number: Int = 0,
    val sensors: List<SensorGroup> = arrayListOf(),
)


@Serializable
data class SensorGroup(
    val sensorType: String = "",
    val number: Int = 0,
)