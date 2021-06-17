package lupos.simulator_iot.sensor

public data class ParkingSample(
    val sampleID: Int,
    val sensorID: Int,
    val sampleTime: String,
    val isOccupied: Boolean,
    val parkingSpotID: Int,
    val area: String
)
