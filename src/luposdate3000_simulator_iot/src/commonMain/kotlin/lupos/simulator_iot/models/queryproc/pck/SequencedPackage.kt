package lupos.simulator_iot.models.queryproc.pck

internal abstract class SequencedPackage(
    val sourceAddress: Int,
    val destinationAddress: Int
) {
    internal var sequenceNumber = -1
}
