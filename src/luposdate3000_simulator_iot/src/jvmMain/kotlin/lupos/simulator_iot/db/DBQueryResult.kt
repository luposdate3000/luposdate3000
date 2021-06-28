package lupos.simulator_iot.db

import lupos.simulator_iot.net.IPayload

internal class DBQueryResult(internal val result: ByteArray) : IPayload {
    override fun getSizeInBytes(): Int {
        return result.size
    }

    override fun toString(): String {
        return "DBQueryResult(size=${getSizeInBytes()} bytes)"
    }

}
