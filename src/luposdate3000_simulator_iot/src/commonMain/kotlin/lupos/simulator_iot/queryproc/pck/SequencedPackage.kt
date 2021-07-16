package lupos.simulator_iot.queryproc.pck

internal abstract class SequencedPackage(
    val sourceAddress: Int,
    val destinationAddress: Int
) {
    internal var sequenceNumber = -1
}
