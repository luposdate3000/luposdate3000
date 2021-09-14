/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package lupos.simulator_iot.queryproc

import lupos.shared.SanityCheck
import lupos.simulator_core.Entity
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_db.QueryPackage
import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.models.Device
import lupos.simulator_iot.models.net.NetworkPackage
import lupos.simulator_iot.utils.TimeUtils

public class QuerySender(
    internal val simRun: SimulationRun,
    internal val name: String,
    internal val sendRateInSec: Int,
    internal val maxNumberOfQueries: Int,
    internal val startClockInSec: Int,
    internal val receiver: Device,
    internal val query: String,
) : Entity() {

    internal var queryCounter = 0
        private set

    public var queryPck: IDatabasePackage = QueryPackage(receiver.address, query.encodeToByteArray())

    override fun onEvent(source: Entity, data: Any) {
        throw Exception("Wrong way. A QuerySender is only a sender.")
    }
    private fun hasDatabase(): Boolean {
        return receiver.simRun.config.dbDeviceAddressesStore.contains(receiver.address) || receiver.simRun.config.dbDeviceAddressesQuery.contains(receiver.address)
    }

    override fun onStartUp() {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_iot/src/commonMain/kotlin/lupos/simulator_iot/queryproc/QuerySender.kt:52"/*SOURCE_FILE_END*/ },
            { hasDatabase() },
            { "The query receiver device must have a database" }
        )
        setTimer(TimeUtils.toNanoSec(startClockInSec), ::scheduleQuery)
    }

    override fun onSteadyState() {
    }

    override fun onShutDown() {
    }

    private fun scheduleQuery() {
        if (queryCounter < maxNumberOfQueries) {
            queryCounter++
            triggerQueryProcessing()
            setTimer(TimeUtils.toNanoSec(sendRateInSec), ::scheduleQuery)
        }
    }

    private fun triggerQueryProcessing() {
        val pck = queryPck
        val netPck = NetworkPackage(receiver.address, receiver.address, pck)
        scheduleEvent(receiver, netPck, 0)
    }
}
