package lupos.simulator_iot.db

import lupos.simulator_iot.net.IPayload

internal class DBQueryResultPackage(
    sourceAddress: Int,
    destinationAddress: Int,
    internal val result: ByteArray
) : IPayload, SequencedPackage(sourceAddress, destinationAddress) {

    override fun getSizeInBytes(): Int {
        return result.size
    }

    override fun toString(): String {
        return "DBQueryResultPck"
    }
}
