package lupos.simulator_iot.db

import lupos.simulator_core.Entity
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_db.QueryPackage
import lupos.simulator_iot.Device
import lupos.simulator_iot.Time

internal class QuerySender(
    val name: String,
    val sendRateInSec: Int,
    val maxNumberOfQueries: Int,
    val startClock: Int,
    val receiver: Device,
    val query: String,
) : Entity() {

    internal var queryCounter = 0
        private set

    internal var queryPck: IDatabasePackage = QueryPackage(receiver.address, query.encodeToByteArray())

    override fun onEvent(source: Entity, data: Any) {
        throw Exception("Wrong way. A QuerySender is only a sender.")
    }

    override fun onStartUp() {
        require(receiver.hasDatabase()) {"The query receiver device must have a database"}
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
            receiver.database!!.processIDatabasePackage(queryPck)
            setTimer(Time.toMillis(sendRateInSec), SendTimer())
        }
    }

    private inner class SendTimer : ITimer {
        override fun onExpire() {
            scheduleQuery()
        }
    }

}
