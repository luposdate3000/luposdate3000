package lupos.simulator_iot.queryproc.pck

import lupos.simulator_iot.models.net.IPayload

internal class DBSequenceEndPackage(
    sourceAddress: Int,
    destinationAddress: Int,
) : IPayload, SequencedPackage(sourceAddress, destinationAddress) {

    override fun getSizeInBytes(): Int {
        // ignore this package size
        return 0
    }

    override fun toString(): String {
        return "DBSeqEndPck(seqNum $sequenceNumber, pckNum $packageNumber,)"
    }
}
