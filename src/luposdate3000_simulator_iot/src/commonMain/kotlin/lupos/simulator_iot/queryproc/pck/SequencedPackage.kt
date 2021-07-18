package lupos.simulator_iot.queryproc.pck

internal abstract class SequencedPackage(
    val sourceAddress: Int,
    val destinationAddress: Int
) {
    internal var packageNumber = -1
    internal var sequenceNumber = -1
}
