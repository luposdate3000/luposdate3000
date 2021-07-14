package lupos.simulator_iot.db.pck

import lupos.simulator_iot.net.IPayload

internal class DBSequenceEndPackage(
    sourceAddress: Int,
    destinationAddress: Int,
    val numberOfPackages: Int
) : IPayload, SequencedPackage(sourceAddress, destinationAddress) {

    override fun getSizeInBytes(): Int {
        return 0
    }

    override fun toString(): String {
        return "DBSequenceEndPck(numberOfPacks $numberOfPackages)"
    }
}
