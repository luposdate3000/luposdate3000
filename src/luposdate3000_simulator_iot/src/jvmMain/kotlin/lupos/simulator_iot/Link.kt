package lupos.simulator_iot

public data class Link(
    var distanceInMeters: Int,
    val linkTypeIndex: Int,
    val dataRateInKbps: Int,
) {
    override fun toString(): String {
        return "(${distanceInMeters}m, ${dataRateInKbps}kbps)"
    }
}
