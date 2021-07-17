package lupos.simulator_iot.queryproc.pck

internal abstract class SequencedPackage(
    val sourceAddress: Int,
    val destinationAddress: Int
) {
    internal var packageNumberInSequence = -1
    internal var sequenceID = -1
}
