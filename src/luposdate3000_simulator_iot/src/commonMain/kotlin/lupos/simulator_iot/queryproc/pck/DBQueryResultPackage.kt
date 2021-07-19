package lupos.simulator_iot.queryproc.pck

import lupos.simulator_iot.models.net.IPayload

internal class DBQueryResultPackage(
    sourceAddress: Int,
    destinationAddress: Int,
    internal val result: ByteArray
) : IPayload, SequencedPackage(sourceAddress, destinationAddress) {

    override fun getSizeInBytes(): Int {
        return result.size
    }

    override fun toString(): String {
        return "DBQueryResultPck(seqID $sequenceNumber, numInSeq $packageNumber)"
    }
}
