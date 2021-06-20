package lupos.simulator_iot.sensor

import lupos.simulator_iot.net.IPayload

public class ParkingSample(
    public val sampleID: Int,
    public val sensorID: Int,
    public val sampleTime: String,
    public val isOccupied: Boolean,
    public val parkingSpotID: Int,
    public val area: String
) : IPayload {
    override fun getSizeInBytes(): Int {
        return 2 + 2 + sampleTime.toByteArray().size + 2 + area.toByteArray().size
    }

}
