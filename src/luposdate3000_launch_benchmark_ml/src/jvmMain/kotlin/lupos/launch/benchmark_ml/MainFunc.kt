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
package lupos.launch.benchmark_ml
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import lupos.endpoint.LuposdateEndpoint
import lupos.operator.base.OPBaseCompound
import lupos.operator.base.Query
import lupos.operator.physical.multiinput.POPJoinCartesianProduct
import lupos.operator.physical.multiinput.POPJoinHashMap
import lupos.operator.physical.multiinput.POPJoinMerge
import lupos.operator.physical.multiinput.POPJoinMergeSingleColumn
import lupos.operator.physical.singleinput.POPGroup
import lupos.operator.physical.singleinput.POPProjection
import lupos.optimizer.logical.LogicalOptimizerBuildCustomJoinOrderML
import lupos.shared.DateHelperRelative
import lupos.shared.TooManyIntermediateResultsException
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import lupos.shared.inline.Platform
import lupos.shared.operator.IOPBase
import lupos.triple_store_manager.POPTripleStoreIterator
import kotlin.concurrent.timer
private suspend fun <A, B> Array<A>.pmap(f: suspend (A) -> B): List<B> = coroutineScope {
    map { async { f(it) } }.awaitAll()
}

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(datasourceFiles: String, queryFiles: String, minimumTime: String) {
    val tripleCount = Platform.getEnv("tripleCount", "4")!!.toInt()
    val instance = LuposdateEndpoint.initialize()
//    Parallel.launch {
//        HttpEndpointLauncher.start(instance)
//    }
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
    val inputString = File(queryFiles).readAsString()
    val queryFiles3 = inputString.split(";").toMutableSet()
    var benchFileHasHeader = false
    try {
        File("$datasourceFiles.bench.csv").withInputStream { benchIn ->
            while (true) {
                val line = benchIn.readLine()
                if (line == null) {
                    break
                }
                benchFileHasHeader = true
                queryFiles3.remove(line.split(",")[0])
            }
        }
    } catch (e: Throwable) {
        benchFileHasHeader = false
    }
    val queryFiles2 = queryFiles3.toTypedArray()
    queryFiles2.shuffle()

    LuposdateEndpoint.importTripleFile(instance, datasourceFiles)

    // avoid unnecessary overhead during measurement
    val joinOrders = List(LogicalOptimizerBuildCustomJoinOrderML.joinOrderCountForTripleCount(tripleCount)) { it }
    val columnNames = listOf(
        "queryFile",
        "luposdateWouldChoose",
        "luposdateAbsoluteRanking(time)",
        "luposdateRelativeRanking(time)",
        "luposdateAbsoluteRanking(results)",
        "luposdateRelativeRanking(results)",
    ) + joinOrders.map { "timeFor($it)" } + joinOrders.map { "joinResultsFor($it)" }

    fun addCounters(node: IOPBase): IOPBase {
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

    val benchOut = File("$datasourceFiles.bench.csv").openOutputStream(true)
    if (!benchFileHasHeader) {
        benchOut.println(columnNames.joinToString())
    }
    runBlocking {
        val mutex = Mutex()
        withContext(Dispatchers.Default) {
            queryFiles2.pmap { queryFile ->
                println("going to benchmark $queryFile")
                val writer = MyPrintWriter(false)
                val query = File(queryFile).readAsString()
                val benchmarkValues = mutableMapOf("queryFile" to queryFile)
                var luposChoice = -1
                var measured_time = DoubleArray(joinOrders.size)
                var measured_results = DoubleArray(joinOrders.size)
                for (joinOrder in joinOrders) {
                    var hadEnforcedAbort = false
                    try {
                        // Read in query file
                        println("going to benchmark $queryFile for joinOrder $joinOrder")
                        // Optimize query and convert to operatorgraph
                        val q = Query(instance)
                        q.optimizer = EOptimizer.MachineLearningSmall
                        q.machineLearningOptimizerOrder = joinOrder
                        q.machineLearningOptimizerTripleCount = tripleCount
                        q.machineLearningOptimizerOrderWouldBeChoosen = false
                        q.machineLearningCounter = 0
                        val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(instance, q, query, false)
                        if (q.machineLearningOptimizerOrderWouldBeChoosen) {
                            if (luposChoice != -1) {
                                TODO("loposdate optimizer should be deterministic")
                            }
                            luposChoice = joinOrder
                        }
                        // dry run, to prevent caching issues

                        val timeoutTimer = timer(daemon = true, initialDelay = (timeout * 1000).toLong(), period = 1000) {
                            (node.getQuery() as Query)._shouldAbortNow = true
                            hadEnforcedAbort = true
                            println("enforcing abort ...")
                        }
                        LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, addCounters(node), writer)
                        measured_results[joinOrder] = q.machineLearningCounter.toDouble()
                        benchmarkValues["joinResultsFor($joinOrder)"] = q.machineLearningCounter.toString()

                        // measure time for executing the query
                        val timer = DateHelperRelative.markNow()

                        // Execute query
                        LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, node, writer)

                        // save time, and predict how often we could execute this within one second. This is only required to benchmark super fast queries. otherwise groupsize of 1 is ok
                        var time = DateHelperRelative.elapsedSeconds(timer)
                        var counter = 1 // counts how often the query gets executed
                        var groupSize = (1 + 0.25 * (minimumTime2 - time) / (time / counter.toDouble())).toInt()

                        // Benchmark
                        do {
                            counter += groupSize
                            for (i in 0 until groupSize) {
                                LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, node, writer)
                            }
                            time = DateHelperRelative.elapsedSeconds(timer)
                            groupSize = (1 + 0.25 * (minimumTime2 - time) / (time / counter.toDouble())).toInt()
                        } while (time < minimumTime2 && !hadEnforcedAbort)
                        timeoutTimer.cancel()
                        val res = time / counter
                        measured_time[joinOrder] = res
                        benchmarkValues["timeFor($joinOrder)"] = res.toString()
                    } catch (e: TooManyIntermediateResultsException) {
                        println("abort due to TooManyIntermediateResultsException...")
                        hadEnforcedAbort = true
                    }
                    if (hadEnforcedAbort) {
                        measured_results[joinOrder] = 9999999999.toDouble()
                        benchmarkValues["joinResultsFor($joinOrder)"] = "9999999999"
                        measured_time[joinOrder] = timeout * 10
                        benchmarkValues["timeFor($joinOrder)"] = (timeout * 10).toString()
                    }
                }
                benchmarkValues["luposdateWouldChoose"] = luposChoice.toString()
                if (luposChoice == -1) {
                    benchmarkValues["luposdateAbsoluteRanking(time)"] = ""
                    benchmarkValues["luposdateRelativeRanking(time)"] = ""
                    TODO("luposdate did not choose any join order??")
                } else {
                    fun relAndAbsRanking(label: String, data: DoubleArray) {
                        var luposAbsolute = 0
                        var timeMin = data[0]
                        var timeMax = data[0]
                        for (joinOrder in joinOrders) {
                            if (data[joinOrder] < data[luposChoice]) {
                                luposAbsolute++
                            }
                            if (data[joinOrder] < timeMin) {
                                timeMin = data[joinOrder]
                            }
                            if (data[joinOrder] > timeMax) {
                                timeMax = data[joinOrder]
                            }
                        }
                        val luposRelative = (data[luposChoice] - timeMin) / (timeMax - timeMin)
                        benchmarkValues["luposdateAbsoluteRanking($label)"] = "$luposAbsolute"
                        benchmarkValues["luposdateRelativeRanking($label)"] = "$luposRelative"
                    }
                    relAndAbsRanking("time", measured_time)
                    relAndAbsRanking("results", measured_results)
                }
                mutex.withLock {
                    benchOut.println(columnNames.map { benchmarkValues[it]!! }.joinToString())
                    benchOut.flush()
                }
                true
            }
        }
    }
    benchOut.close()
}
