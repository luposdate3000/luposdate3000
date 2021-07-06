package lupos.simulator_iot.net.routing

import lupos.simulator_iot.net.IPayload

internal class DIO(internal val rank: Int) : IPayload {
    override fun getSizeInBytes(): Int {
        return 2
    }

    override fun toString(): String {
        return "DIO(rank $rank)"
    }
}
