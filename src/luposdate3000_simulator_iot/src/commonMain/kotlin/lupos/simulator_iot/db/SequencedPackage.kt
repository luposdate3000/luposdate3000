package lupos.simulator_iot.db

internal abstract class SequencedPackage(
    val sourceAddress: Int,
    val destinationAddress: Int
) {
    internal var sequenceNumber = -1
}
