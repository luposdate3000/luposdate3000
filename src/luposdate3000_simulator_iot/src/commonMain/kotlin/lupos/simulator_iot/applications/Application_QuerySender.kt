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
package lupos.simulator_iot.applications

import lupos.shared.SanityCheck
import lupos.shared.inline.File
import lupos.simulator_core.ITimer
import lupos.simulator_iot.IPackage_Database
import lupos.simulator_iot.IPayload
import lupos.simulator_iot.Package_Query
import lupos.simulator_iot.Package_QueryResponse

public class Application_QuerySender(
    internal val startClockInSec: Int,
    internal val sendRateInSec: Int,
    internal val maxNumber: Int,
    internal val queryPck: IPackage_Database,
    internal val receiver: Int,
    private val outputdirectory: String,
) : IApplicationStack_Actuator, ITimer {
    public constructor(
        startClockInSec: Int,
        sendRateInSec: Int,
        maxNumber: Int,
        query: String,
        receiver: Int,
        outputdirectory: String,
    ) : this(startClockInSec, sendRateInSec, maxNumber, Package_Query(receiver, query.encodeToByteArray()), receiver, outputdirectory)

    private lateinit var parent: IApplicationStack_Middleware
    private var eventCounter = 0
    private var awaitingQueries = mutableListOf<Int>()

    override fun setRouter(router: IApplicationStack_Middleware) {
        parent = router
    }

    override fun startUp() {
        parent.registerTimer(startClockInSec.toLong() * 1000000000L, this)
    }

    override fun shutDown() {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_iot/src/commonMain/kotlin/lupos/simulator_iot/applications/Application_QuerySender.kt:52"/*SOURCE_FILE_END*/ },
            { awaitingQueries.size == 0 }
        )
    }

    override fun receive(pck: IPayload): IPayload? {
        if (pck is Package_QueryResponse) {
            if (awaitingQueries.contains(pck.queryID)) {
                awaitingQueries.remove(pck.queryID)
                File(outputdirectory + "result_${pck.queryID}").withOutputStream { out ->
                    out.write(pck.result)
                }
                return null
            } else {
                return pck
            }
        } else {
            return pck
        }
    }

    override fun onTimerExpired(clock: Long) {
        if (eventCounter < maxNumber || maxNumber == -1) {
            eventCounter++
            val p = queryPck
            if (p is Package_Query) {
                awaitingQueries.add(p.queryID)
            }
            parent.send(receiver, p)
            parent.flush()
            parent.registerTimer(sendRateInSec.toLong() * 1000000000L, this)
        }
    }
}
