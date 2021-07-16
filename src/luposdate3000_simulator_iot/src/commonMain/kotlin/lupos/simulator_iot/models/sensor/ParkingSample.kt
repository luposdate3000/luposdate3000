package lupos.simulator_iot.models.sensor

import lupos.simulator_iot.models.net.IPayload

internal class ParkingSample(
    internal val sampleID: Int,
    internal val sensorID: Int,
    internal val sampleTime: String,
    internal val isOccupied: Boolean,
    internal val parkingSpotID: Int,
    internal val area: String
) : IPayload {
    override fun getSizeInBytes(): Int {
        return 2 + 2 + sampleTime.encodeToByteArray().size + 2 + area.encodeToByteArray().size
    }

    override fun toString(): String {
        return "ParkingSample(sample $sampleID, sensorID $sensorID)"
    }
}
