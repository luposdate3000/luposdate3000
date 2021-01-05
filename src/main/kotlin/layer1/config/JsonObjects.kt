package layer1.config

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Area(
    val xLeft: Int = 0,
    val xRight: Int = 0,
    val yBottom: Int = 0,
    val yTop: Int = 0,
)

@Serializable
data class Application(
    val processingPower: String = "",
)

@Serializable
data class Sensor(
    val dataSize: String = "",
    val storageCapacity: String = "",
)

@Serializable
data class Power(
    val capacity: Double = 0.0,
    val drainageRate: Double = 0.0,
)

@Serializable
data class Point(
    val x: Int = -1,
    val y: Int = -1,
)

@Serializable
data class Mobility(
    val velocity: Int = 0,
    val destinationPoint: Point = Point(),
)

@Serializable
data class Device(
    var signalRange: Double = 0.0,
    val application: Application = Application(),
    val sensor: Sensor = Sensor(),
    val actuator: Boolean = false,
    val power: Power = Power(),
    val mobility: Mobility = Mobility(),
    @Transient var point: Point = Point(-1, -1),
)

@Serializable
data class DeviceCategory(
    val name: String = "",
    var numberOfDevices: Int = 1,
    val area: Area = Area(),
    val device: Device = Device(),
    @Transient var devices: ArrayList<Device> = arrayListOf(),
)


@Serializable
data class DeviceCategories(
    val deviceCategories: List<DeviceCategory> = arrayListOf(),
)
