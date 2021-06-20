package lupos.simulator_iot

import lupos.simulator_core.Entity
import lupos.simulator_iot.net.IPayload
import lupos.simulator_iot.net.NetworkPackage

internal class QuerySender(
    val name: String,
    val sendRateInSec: Int,
    val maxNumberOfQueries: Int,
    val receiver: Device,
    val query: String,
): Entity() {

    private var queryCounter = 0

    override fun onEvent(source: Entity, data: Any) {
        throw Exception("Wrong way")
    }

    override fun onStartUp() {
        startTimer()
    }

    override fun onSteadyState() {

    }

    override fun onShutDown() {
    }

    private inner class SendTimer: Entity.ITimer {
        override fun onExpire() {
            triggerQueryProcessing()
            if(queryCounter < maxNumberOfQueries)
                startTimer()
        }
    }

    private fun startTimer() {
        val millis = sendRateInSec * 1000
        setTimer(millis.toLong(), SendTimer())
    }

    private fun triggerQueryProcessing() {
        queryCounter++
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
