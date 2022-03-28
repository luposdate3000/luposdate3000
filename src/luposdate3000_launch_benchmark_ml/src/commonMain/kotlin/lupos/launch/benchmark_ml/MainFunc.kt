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
import lupos.endpoint_launcher.HttpEndpointLauncher
import lupos.shared.DateHelperRelative
import lupos.shared.Parallel
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(datasourceFiles: String, queryFiles: String, minimumTime: String): Unit = Parallel.runBlocking {
    val instance = LuposdateEndpoint.initialize()
    Parallel.launch {
        HttpEndpointLauncher.start(instance)
    }

    val inputString = File(queryFiles).readAsString()
    val queryFiles2 = inputString.split(";")

    val minimumTime2 = minimumTime.toDouble()

    LuposdateEndpoint.importTripleFile(instance, datasourceFiles)

    // avoid unnecessary overhead during measurement
    val groupSize = IntArray(queryFiles2.size) { 1 }
    val writer = MyPrintWriter(false)

    val joinOrders = List(15) { it }
    val columnNames = listOf(
        "queryFile",
        "luposdateWouldChoose",
        "luposdateAbsoluteRanking",
        "luposdateRelativeRanking",
    ) + joinOrders.map { "timeFor($it)" }

    File("$datasourceFiles.bench").withOutputStream { benchOut ->
        benchOut.println(columnNames.joinToString())
        for (queryFileIdx in queryFiles2.indices) { // for every query
            val queryFile = queryFiles2[queryFileIdx]
            val query = File(queryFile).readAsString()
            val benchmarkValues = mutableMapOf("queryFile" to queryFile)
            var luposChoice = -1
            var timings = DoubleArray(joinOrders.size)
            for (joinOrder in joinOrders) {
                // Read in query file

                // Optimize query and convert to operatorgraph
                instance.useMachineLearningOptimizer = true
                instance.machineLearningOptimizerOrder = joinOrder
                instance.machineLearningOptimizerOrderWouldBeChoosen = false
                val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(instance, query, true)
                if (instance.machineLearningOptimizerOrderWouldBeChoosen) {
if(luposChoice!=-1){
TODO("loposdate optimizer should be deterministic")
}
                    luposChoice = joinOrder
                }
                // dry run, to prevent caching issues
                LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, node, writer)

                // measure time for executing the query
                val timerFirst = DateHelperRelative.markNow()

                // Execute query
                LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, node, writer)

                // save time, and predict how often we could execute this within one second. This is only required to benchmark super fast queries. otherwise groupsize of 1 is ok
                val timeFirst = DateHelperRelative.elapsedSeconds(timerFirst)
                groupSize[queryFileIdx] = 1 + (1.0 / timeFirst).toInt()

                // Benchmark
                val timer = DateHelperRelative.markNow()
                var time: Double
                var counter = 0 // counts how often the query gets executed
                do {
                    counter += groupSize[queryFileIdx]
                    for (i in 0 until groupSize[queryFileIdx]) {
                        LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, node, writer)
                    }
                    time = DateHelperRelative.elapsedSeconds(timer)
                } while (time < minimumTime2)
                val res = counter / time
                timings[joinOrder] = res
            }
            benchmarkValues["luposdateWouldChoose"] = luposChoice.toString()
            if (luposChoice == -1) {
                benchmarkValues["luposdateAbsoluteRanking"] = ""
                benchmarkValues["luposdateRelativeRanking"] = ""
TODO("luposdate did not choose any join order??")
            } else {
                var luposAbsolute = 0
                var timeMin = timings[0]
                var timeMax = timings[0]
                for (joinOrder in joinOrders) {
                    if (timings[joinOrder] < timings[luposChoice]) {
                        luposAbsolute++
                    }
                    if (timings[joinOrder] < timeMin) {
                        timeMin = timings[joinOrder]
                    }
                    if (timings[joinOrder] > timeMax) {
                        timeMax = timings[joinOrder]
                    }
                }
                val luposRelative = (timings[luposChoice] - timeMin) / (timeMax - timeMin)
                benchmarkValues["luposdateAbsoluteRanking"] = "$luposAbsolute"
                benchmarkValues["luposdateRelativeRanking"] = "$luposRelative"
            }
            benchOut.println(columnNames.map { benchmarkValues[it]!! }.joinToString())
        }
    }
}
