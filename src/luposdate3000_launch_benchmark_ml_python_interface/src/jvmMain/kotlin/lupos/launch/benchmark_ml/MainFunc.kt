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
package lupos.launch.benchmark_ml_python_interface

import lupos.shared.Luposdate3000Instance
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import lupos.endpoint.LuposdateEndpoint
import lupos.operator.base.OPBaseCompound
import lupos.operator.base.Query
import lupos.shared.EOptimizer
import lupos.shared.EOptimizerExt
import lupos.operator.physical.multiinput.POPJoinCartesianProduct
import lupos.operator.physical.multiinput.POPJoinHashMap
import lupos.operator.physical.multiinput.POPJoinMerge
import lupos.operator.physical.multiinput.POPJoinMergeSingleColumn
import lupos.operator.physical.singleinput.POPGroup
import lupos.operator.physical.singleinput.POPProjection
import lupos.shared.inline.MyPrintWriter
import lupos.shared.inline.Platform
import lupos.shared.operator.IOPBase
import lupos.triple_store_manager.POPTripleStoreIterator
import py4j.GatewayServer
import kotlin.concurrent.timer

private suspend fun <A, B> Array<A>.pmap(f: suspend (A) -> B): List<B> = coroutineScope {
    map { async { f(it) } }.awaitAll()
}

internal fun addCounters(node: IOPBase): IOPBase {
    return when (node) {
        is OPBaseCompound -> {
            val a = addCounters(node.children[0])
            OPBaseCompound(node.getQuery(), arrayOf(a), node.columnProjectionOrder)
        }
        is POPJoinCartesianProduct -> {
            val a = addCounters(node.children[0])
            val b = addCounters(node.children[1])
            POPCounter(node.getQuery(), node.getProvidedVariableNames(), POPJoinCartesianProduct(node.getQuery(), node.getProvidedVariableNames(), a, b, false))
        }
        is POPJoinHashMap -> {
            val a = addCounters(node.children[0])
            val b = addCounters(node.children[1])
            POPCounter(node.getQuery(), node.getProvidedVariableNames(), POPJoinHashMap(node.getQuery(), node.getProvidedVariableNames(), a, b, false))
        }
        is POPJoinMergeSingleColumn -> {
            val a = addCounters(node.children[0])
            val b = addCounters(node.children[1])
            POPCounter(node.getQuery(), node.getProvidedVariableNames(), POPJoinMergeSingleColumn(node.getQuery(), node.getProvidedVariableNames(), a, b, false))
        }
        is POPJoinMerge -> {
            val a = addCounters(node.children[0])
            val b = addCounters(node.children[1])
            POPCounter(node.getQuery(), node.getProvidedVariableNames(), POPJoinMerge(node.getQuery(), node.getProvidedVariableNames(), a, b, false))
        }
        is POPProjection -> {
            POPProjection(node.getQuery(), node.getProvidedVariableNames(), addCounters(node.children[0]))
        }
        is POPGroup -> {
            POPGroup(node.getQuery(), node.getProvidedVariableNames(), node.by, node.bindings, addCounters(node.children[0]))
        }
        is POPTripleStoreIterator -> {
            node
        }
        else -> {
            TODO(node.getClassname())
        }
    }
}

public class PythonBridgeApplication(private val instance: Luposdate3000Instance, private val timeout: Double) {
    init {
        println("ready for python to connect")
    }

    public fun getIntermediateResultsFor(query: String, joinOrder: String): Long {
        return getIntermediateResultsFor(query, joinOrder.split(",").map { it.toInt() })
    }

    private fun getIntermediateResultsFor(query: String, joinOrder: List<Int>): Long {
        println("the query")
        println(query)
        println("the joinOrder $joinOrder")
        val q = Query(instance)
        q.optimizer = EOptimizerExt.MachineLearningLarge
        q.machineLearningOptimizerOrder2 = joinOrder
        q.machineLearningCounter = 0
        var hadEnforcedAbort = false
        val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(instance, q, query, false)
        val writer = MyPrintWriter(false)
        val timeoutTimer = timer(daemon = true, initialDelay = (timeout * 1000).toLong(), period = 1000) {
            q._shouldAbortNow = true
            hadEnforcedAbort = true
            println("enforcing abort ...")
        }
        LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, addCounters(node), writer)
        timeoutTimer.cancel()
        if (hadEnforcedAbort) {
            return -1
        }
        return q.machineLearningCounter
    }
}

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(datasourceFiles: String, minimumTime: String) {
    val instance = LuposdateEndpoint.initialize()
    var noTimeMeasurement = minimumTime.toDouble() <= 0
    val minimumTime2 = if (noTimeMeasurement) {
        if (minimumTime.toDouble() < 0.1 && minimumTime.toDouble() > -0.1) {
            1.0
        } else {
            -minimumTime.toDouble()
        }
    } else {
        minimumTime.toDouble()
    }
    val timeout = minimumTime2 * 10.0
    LuposdateEndpoint.importTripleFile(instance, datasourceFiles)

// https://www.py4j.org/index.html

    GatewayServer(PythonBridgeApplication(instance, timeout)).start()
}
