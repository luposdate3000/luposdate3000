package lupos.simulator_iot.queryproc

internal class SequenceEndpoint(val address: Int, val sequenceID: Int) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SequenceEndpoint

        if (address != other.address) return false
        if (sequenceID != other.sequenceID) return false

        return true
    }

    override fun hashCode(): Int {
        var result = address
        result = 31 * result + sequenceID
        return result
    }
}
