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

import lupos.endpoint.LuposdateEndpoint
import lupos.operator.physical.multiinput.POPJoinMerge
import lupos.endpoint_launcher.HttpEndpointLauncher
import lupos.operator.base.OPBaseCompound
import lupos.shared.operator.IOPBase
import lupos.shared.DateHelperRelative
import lupos.shared.Parallel
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(datasourceFiles: String, queryFiles: String, minimumTime: String): Unit {
    val instance = LuposdateEndpoint.initialize()
    Parallel.launch {
        HttpEndpointLauncher.start(instance)
    }

    val inputString = File(queryFiles).readAsString()
    val queryFiles2 = inputString.split(";")

    val minimumTime2 = minimumTime.toDouble()

    LuposdateEndpoint.importTripleFile(instance, datasourceFiles)

    // avoid unnecessary overhead during measurement
    val writer = MyPrintWriter(false)

    val joinOrders = List(15) { it }
    val columnNames = listOf(
        "queryFile",
        "luposdateWouldChoose",
        "luposdateAbsoluteRanking(time)",
        "luposdateRelativeRanking(time)",
        "luposdateAbsoluteRanking(results)",
        "luposdateRelativeRanking(results)",
    ) + joinOrders.map { "timeFor($it)" }    +joinOrders.map { "joinResultsFor($it)" }

    File("$datasourceFiles.bench.csv").withOutputStream { benchOut ->
        benchOut.println(columnNames.joinToString())
        for (queryFileIdx in queryFiles2.indices) { // for every query
            val queryFile = queryFiles2[queryFileIdx]
            val query = File(queryFile).readAsString()
            val benchmarkValues = mutableMapOf("queryFile" to queryFile)
            var luposChoice = -1
            var measured_time = DoubleArray(joinOrders.size)
            var measured_results = DoubleArray(joinOrders.size)
            for (joinOrder in joinOrders) {
                // Read in query file

                // Optimize query and convert to operatorgraph
                instance.useMachineLearningOptimizer = true
                instance.machineLearningOptimizerOrder = joinOrder
                instance.machineLearningOptimizerOrderWouldBeChoosen = false
                instance.machineLearningCounter = 0
                val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(instance, query, false)
                if (instance.machineLearningOptimizerOrderWouldBeChoosen) {
                    if (luposChoice != -1) {
                        TODO("loposdate optimizer should be deterministic")
                    }
                    luposChoice = joinOrder
                }
                // dry run, to prevent caching issues
                fun addCounters(node: IOPBase): IOPBase {
                    return when (node){
is OPBaseCompound->{
val a = addCounters(node.children[0])
OPBaseCompound(node.getQuery(),arrayOf(a),node.columnProjectionOrder)
}
 is POPJoinMerge-> {
                        val a = addCounters(node.children[0])
                        val b = addCounters(node.children[1])
                        POPCounter(node.getQuery(), node.getProvidedVariableNames(), POPJoinMerge(node.getQuery(), node.getProvidedVariableNames(), a, b, false))
                    }
 else-> {
TODO(node)
                        node
                    }
}
                }
                LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, addCounters(node), writer)
                measured_results[joinOrder] = instance.machineLearningCounter.toDouble()
                benchmarkValues["joinResultsFor($joinOrder)"] = instance.machineLearningCounter.toString()

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
                } while (time < minimumTime2)
                val res = time / counter
                measured_time[joinOrder] = res
                benchmarkValues["timeFor($joinOrder)"] = res.toString()
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
            benchOut.println(columnNames.map { benchmarkValues[it]!! }.joinToString())
            benchOut.flush()
        }
    }
}
