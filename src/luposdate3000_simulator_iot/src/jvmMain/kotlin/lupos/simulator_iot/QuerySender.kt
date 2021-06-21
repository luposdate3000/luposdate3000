package lupos.simulator_iot

import lupos.simulator_core.Entity
import lupos.simulator_iot.net.IPayload
import lupos.simulator_iot.net.NetworkPackage

internal class QuerySender(
    val name: String,
    val sendRateInSec: Int,
    val maxNumberOfQueries: Int,
    val startClock: Int,
    val receiver: Device,
    val query: String,
): Entity() {

    internal var queryCounter = 0
        private set

    override fun onEvent(source: Entity, data: Any) {
        throw Exception("Wrong way. A QuerySender is only a sender.")
    }

    override fun onStartUp() {
        setTimer(Time.toMillis(startClock), StartUpTimer())
    }

    override fun onSteadyState() {

    }

    override fun onShutDown() {
    }

    private inner class StartUpTimer: ITimer {
        override fun onExpire() {
            scheduleQuery()
        }
    }

    private fun scheduleQuery() {
        if(queryCounter < maxNumberOfQueries) {
            queryCounter++
            triggerQueryProcessing()
            setTimer(Time.toMillis(sendRateInSec), SendTimer())
        }
    }


    private inner class SendTimer: ITimer {
        override fun onExpire() {
            scheduleQuery()
        }
    }



    private fun triggerQueryProcessing() {
        val queryPck = QueryPackage(query)
        val pck = NetworkPackage(-1, receiver.address, queryPck)
        scheduleEvent(receiver, pck, 0)
    }





    public class QueryPackage (public val query: String) : IPayload {
        override fun getSizeInBytes(): Int {
            return query.toByteArray().size
        }

    }

}
