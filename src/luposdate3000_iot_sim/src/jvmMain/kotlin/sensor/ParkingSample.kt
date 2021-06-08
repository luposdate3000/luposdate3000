package lupos.iot_sim.sensor

public data class ParkingSample(
    val sampleID: Int,
    val sensorID: Int,
    val sampleTime: Long,
    val isOccupied: Boolean,
    val parkingSpotID: Int,
    val area: String)

