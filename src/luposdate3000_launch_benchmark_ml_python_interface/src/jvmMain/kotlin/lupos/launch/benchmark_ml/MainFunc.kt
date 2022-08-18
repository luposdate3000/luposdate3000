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

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import lupos.endpoint.LuposdateEndpoint
import lupos.operator.base.OPBaseCompound
import lupos.operator.base.Query
import lupos.operator.physical.multiinput.POPJoinCartesianProduct
import lupos.operator.physical.multiinput.POPJoinHashMap
import lupos.operator.physical.multiinput.POPJoinMerge
import lupos.operator.physical.multiinput.POPJoinMergeSingleColumn
import lupos.operator.physical.singleinput.POPGroup
import lupos.operator.physical.singleinput.POPProjection
import lupos.shared.EOptimizerExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.inline.MyPrintWriter
import lupos.shared.operator.IOPBase
import lupos.triple_store_manager.POPTripleStoreIterator
import py4j.GatewayServer
import java.util.Timer
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

    public fun getJoinOrderFor(query: String): String? {
        try {
            println("getJoinOrderFor $query")
            val q = Query(instance)
            q.optimizer = EOptimizerExt.MachineLearningLargePredict
            val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(instance, q, query, false)
            return q.machineLearningOptimizerOrder2.joinToString(",")
        } catch (e: Throwable) {
            e.printStackTrace()
            val msg = e.message
            if (msg != null && msg.contains("Maximum lock count exceeded")) {
                System.exit(1)
            }
            return null
        }
    }

    public fun getIntermediateResultsFor(query: String, joinOrder2: String): Long? {
        try {
            val joinOrder = joinOrder2.split(",").map { it.toInt() }
            println("getIntermediateResultsFor $joinOrder2 $query")
            val q = Query(instance)
            q.optimizer = EOptimizerExt.MachineLearningLarge
            q.machineLearningOptimizerOrder2 = joinOrder
            q.machineLearningCounter = 0
            var hadEnforcedAbort = false
            val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(instance, q, query, false)
            val writer = MyPrintWriter(false)
            var timeoutTimer: Timer? = null
            timeoutTimer = timer(daemon = true, initialDelay = (timeout * 1000).toLong(), period = 1000) {
                q._shouldAbortNow = true
                hadEnforcedAbort = true
                timeoutTimer!!.cancel()
                println("enforcing abort ...")
            }
            LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, addCounters(node), writer)
            timeoutTimer?.cancel()
            if (hadEnforcedAbort || q.machineLearningAbort) {
                println("return null")
                return null
            }
            println("return " + q.machineLearningCounter)
            return q.machineLearningCounter
        } catch (e: Throwable) {
            e.printStackTrace()
            val msg = e.message
            if (msg != null && msg.contains("Maximum lock count exceeded")) {
                System.exit(1)
            }
            println("return null")
            return null
        }
    }
}

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(datasourceFiles: String, minimumTime: String) {
    val isDynamicCluster = false
    val isDynamic = false
    val isGreedy = true
    val instance = LuposdateEndpoint.initialize()
    if (isDynamicCluster) {
        instance.enableJoinOrderOnDynamicProgramming = true
        instance.enableJoinOrderOnDynamicProgrammingNoCluster = true
    } else if (isDynamic) {
        instance.enableJoinOrderOnDynamicProgramming = true
        instance.enableJoinOrderOnDynamicProgrammingNoCluster = false
    } else if (isGreedy) {
        instance.enableJoinOrderOnDynamicProgramming = false
        instance.enableJoinOrderOnDynamicProgrammingNoCluster = false
    }
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
