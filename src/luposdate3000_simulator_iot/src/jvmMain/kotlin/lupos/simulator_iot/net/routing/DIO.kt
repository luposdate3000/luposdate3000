package lupos.simulator_iot.net.routing

import lupos.simulator_iot.net.IPayload

public class DIO(public val rank: Int) : IPayload {
    override fun getSizeInBytes(): Int {
        return 2
    }

}
