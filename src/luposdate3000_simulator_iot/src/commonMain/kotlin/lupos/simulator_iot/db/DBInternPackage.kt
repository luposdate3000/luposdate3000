package lupos.simulator_iot.db

import lupos.simulator_db.IDatabasePackage
import lupos.simulator_iot.net.IPayload

internal class DBInternPackage(
    sourceAddress: Int,
    destinationAddress: Int,
    internal val content: IDatabasePackage) : IPayload, SequencedPackage(sourceAddress, destinationAddress) {
    override fun getSizeInBytes(): Int {
        return content.getPackageSizeInBytes()
    }

    override fun toString(): String {
        return "DBInternPck(content ${content.getContentLogString()})"
    }
}
