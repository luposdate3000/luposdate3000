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
package lupos.simulator_db.luposdate3000
import simora.applications.scenario.parking.Package_QueryResponse
import simora.applications.scenario.parking.Package_Query
import simora.IPayload
import simora.ITimer
import simora.applications.IApplicationStack_Actuator
import lupos.endpoint.LuposdateEndpoint
import simora.applications.IApplicationStack_Middleware
import simora.applications.scenario.parking.IPackage_Database
import lupos.shared.inline.File

public class Application_MachineLearning(
    internal val queriesFileName: String,
    internal val joinOrders: Int,
    internal val receiver: Int,
) : IApplicationStack_Actuator, ITimer {

    private lateinit var parent: IApplicationStack_Middleware
private val queries=File(queriesFileName).readAsString().split(";").toTypedArray()
private var queryIndex=0
private var joinOrder=0
private var awaitingQueries = mutableSetOf<Int>()

    override fun setRouter(router: IApplicationStack_Middleware) {
        parent = router
    }

    override fun startUp() {
    }

    override fun shutDown() {
    }

    override fun receive(pck: IPayload): IPayload? {
return if (pck is Package_QueryResponse) {
            if (awaitingQueries.contains(pck.queryID)) {
                awaitingQueries.remove(pck.queryID)
                null
            } else {
                pck
            }
        } else {
            pck
        }
    }

    override fun onTimerExpired(clock: Long) {
    }

    override fun emptyEventQueue(): String? {
if(joinOrder==joinOrders){
joinOrder=0
queryIndex++
}
if(queryIndex<queries.size){
val p=Package_Query(receiver, File(queries[queryIndex]).readAsString().encodeToByteArray(),mapOf("machineLearningOptimizerOrder" to joinOrder))
awaitingQueries.add(p.queryID)
parent.send(receiver, p)
        parent.flush()
return "${queries[queryIndex]}#${joinOrder++}"
}else{
return null
}
}
}
