package lupos.simulator_iot.db

import lupos.simulator_core.Entity
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_db.PostProcessSend
import lupos.simulator_db.QueryPackage
import lupos.simulator_iot.Device
import lupos.simulator_iot.Time
import lupos.simulator_iot.net.NetworkPackage
public class QuerySender(
    internal val name: String,
    internal val sendRateInSec: Int,
    internal val maxNumberOfQueries: Int,
    internal val startClock: Int,
    internal val receiver: Device,
    internal val query: String,
) : Entity() {

    internal var queryCounter = 0
        private set

    public var queryPck: IDatabasePackage = QueryPackage(receiver.address, query.encodeToByteArray())

    override fun onEvent(source: Entity, data: Any) {
        throw Exception("Wrong way. A QuerySender is only a sender.")
    }

    override fun onStartUp() {
        require(receiver.hasDatabase()) { "The query receiver device must have a database" }
        setTimer(Time.toMillis(startClock), StartUpTimer())
    }

    override fun onSteadyState() {
    }

    override fun onShutDown() {
    }

    private inner class StartUpTimer : ITimer {
        override fun onExpire() {
            scheduleQuery()
        }
    }

    private fun scheduleQuery() {
        if (queryCounter < maxNumberOfQueries) {
            queryCounter++
            triggerQueryProcessing()
            setTimer(Time.toMillis(sendRateInSec), SendTimer())
        }
    }

    private inner class SendTimer : ITimer {
        override fun onExpire() {
            scheduleQuery()
        }
    }

    private fun triggerQueryProcessing() {
        val pck = DBQuerySenderPackage(queryPck)
        val netPck = NetworkPackage(receiver.address, receiver.address, pck)
        PostProcessSend.process(receiver.address, receiver.address, receiver.simRun.sim.clock, receiver.simRun.sim.visualisationNetwork, queryPck)
        scheduleEvent(receiver, netPck, 0)
    }
}
