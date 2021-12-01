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
internal fun mainFunc(datasourceFiles: String, queryFiles: String, minimumTime: String, numberOfTriples: String, optimizerMode: String): Unit = Parallel.runBlocking {
    val instance = LuposdateEndpoint.initialize()
    Parallel.launch {
        HttpEndpointLauncher.start(instance)
    }
    // Cast input string to internal data types
    val inputString = File(queryFiles).readAsString()
    val queryFiles2 = inputString.split(";")

    val minimumTime2 = minimumTime.toDouble()

    // measure time for turtle data load
    val timer = DateHelperRelative.markNow()

    // Load turtle data
    LuposdateEndpoint.importTripleFile(instance, datasourceFiles)

    // avoid unnecessary overhead during measurement
    val groupSize = IntArray(queryFiles2.size) { 1 }
    val writer = MyPrintWriter(false)

    if (optimizerMode == "Test") {
        File("${queryFiles2[0]}.luposTestDataFile").withOutputStream { testResult ->
            for (queryFileIdx in queryFiles2.indices) { // for every query
                val queryFile = queryFiles2[queryFileIdx]
                File("$queryFile.test").withOutputStream { testOut ->

                    // Read in query file
                    val query = File(queryFile).readAsString()

                    // Optimize query and convert to operatorgraph
                    val node = LuposdateEndpoint.evaluateSparqlToLogicalOperatorgraphB(instance, query)
                    var lopjoin0 = node.getChildren() // lopjoin

                    // ERROR : this is NOT typesafe, and may error. Especially for more than 2 Joins. Please Check for the Class-Types here !!
                    var pred0 = lopjoin0[0].getChildren()[0].getChildren()[1]
                    var pred1 = lopjoin0[0].getChildren()[1].getChildren()[0].getChildren()[1]
                    var pred2 = lopjoin0[0].getChildren()[1].getChildren()[1].getChildren()[1]

                    testOut.print(query)
                    testOut.print(pred0.toString())
                    testOut.print(pred1.toString())
                    testOut.print(pred2.toString())
                    testResult.print("$queryFile.test;")
                }
            }
        }
    } else {
        File("$datasourceFiles.bench").withOutputStream { benchOut ->
            for (queryFileIdx in queryFiles2.indices) { // for every query
                for (joinOrder in 0..2) { // for every permutation

                    // Read in query file
                    val queryFile = queryFiles2[queryFileIdx]
                    val query = File(queryFile).readAsString()

                    // Optimize query and convert to operatorgraph
                    instance.useMachineLearningOptimizer = true
                    instance.machineLearningOptimizerOrder = joinOrder
                    val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(instance, query, true)

                    // dry run, to prevent caching issues
                    LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, node, writer)

                    // measure time for executing the query
                    val timerFirst = DateHelperRelative.markNow()

                    // Execute query
                    LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, node, writer)

                    // save time, and predict how often we could execute this
                    val timeFirst = DateHelperRelative.elapsedSeconds(timerFirst)
                    groupSize[queryFileIdx] = 1 + (1.0 / timeFirst).toInt()

                    // Benchmark
                    val timer2 = DateHelperRelative.markNow()
                    var time2: Double
                    var counter = 0 // counts how often the query gets executed
                    do {
                        counter += groupSize[queryFileIdx]
                        for (i in 0 until groupSize[queryFileIdx]) {
                            LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, node, writer)
                        }
                        time2 = DateHelperRelative.elapsedSeconds(timer2)
                    } while (time2 < minimumTime2)
                    benchOut.println("$queryFile $joinOrder ${counter / time2}")
                }
            }
        }
    }
}
