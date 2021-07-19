package lupos.simulator_iot.models.net

internal class Link(
    var distanceInMeters: Int,
    val linkTypeIndex: Int,
    val dataRateInKbps: Int,
) {

    override fun toString(): String {
        return "(${distanceInMeters}m, ${dataRateInKbps}kbps)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        other as Link

        if (distanceInMeters != other.distanceInMeters) return false
        if (linkTypeIndex != other.linkTypeIndex) return false
        if (dataRateInKbps != other.dataRateInKbps) return false

        return true
    }

    override fun hashCode(): Int {
        var result = distanceInMeters
        result = 31 * result + linkTypeIndex
        result = 31 * result + dataRateInKbps
        return result
    }
}
