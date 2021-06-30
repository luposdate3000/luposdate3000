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

import lupos.endpoint.LuposdateEndpointML
import lupos.endpoint_launcher.HttpEndpointLauncher
import lupos.shared.DateHelperRelative
import lupos.shared.Parallel
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import java.io.FileOutputStream

internal enum class OptimizerMode {
    All, OnlyWith, OnlyWithout
}

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(datasourceFiles: String, queryFiles: String, minimumTime: String, numberOfTriples: String, optimizerMode: String): Unit = Parallel.runBlocking {
    val instance = LuposdateEndpointML.initialize()
    Parallel.launch {
        HttpEndpointLauncher.start(instance)
    }
    // Cast input string to internal data types
    val bufferedReader = java.io.File(queryFiles).bufferedReader()
    val inputString = bufferedReader.use { it.readText() }

    val queryFiles2 = inputString.split(";")
    val minimumTime2 = minimumTime.toDouble()
    val numberOfTriples2 = numberOfTriples.toLong()
    var optimizerMode2: OptimizerMode
    if (optimizerMode == "OnlyWith") {
        optimizerMode2 = OptimizerMode.OnlyWith
    } else if (optimizerMode == "OnlyWithout") {
        optimizerMode2 = OptimizerMode.OnlyWithout
    } else {
        optimizerMode2 = OptimizerMode.All
    }
    // measure time for turtle data load
    val timer = DateHelperRelative.markNow()
    // Load turtle data
    LuposdateEndpointML.importTurtleFile(instance, datasourceFiles)
    val time = DateHelperRelative.elapsedSeconds(timer)
    //

    val fileName = "$datasourceFiles.dictionaryML"
    var dictionaryFile = java.io.File(fileName)

//    val buffer = ByteArrayWrapper()
//    instance.nodeGlobalDictionary?.forEachValue(buffer) { id -> println("$id -> ${DictionaryHelper.byteArrayToSparql(buffer)}") }

//    val buffer = ByteArrayWrapper()
//    var text = ""
//    dictionaryFile.printWriter().use { out ->
//        instance.nodeGlobalDictionary?.forEachValue(buffer) {
//            out.println("${DictionaryHelper.byteArrayToSparql(buffer)}")
//        }
//    }

    println("$datasourceFiles/persistence-import.sparql,$numberOfTriples2,0,1,${numberOfTriples2 * 1000.0},${1.0 / time}")
    val groupSize = IntArray(queryFiles2.size) { 1 } // int array with a 1 for every input query file, used to measure time

    // Benchmark with Optimizer
    if (optimizerMode2 != OptimizerMode.OnlyWithout) { // All or OnlyWith
        for (queryFileIdx in queryFiles2.indices) { // for every queryfile
            // Read in query file
            val queryFile = queryFiles2[queryFileIdx]
            val query = File(queryFile).readAsString()
            // measure time for query optimization + execution
            val timerFirst = DateHelperRelative.markNow()
            // Optimize + Execute query
            LuposdateEndpointML.evaluateSparqlToResultC(instance, query, true)
            // save time
            val timeFirst = DateHelperRelative.elapsedSeconds(timerFirst)
            groupSize[queryFileIdx] = 1 + (1.0 / timeFirst).toInt()
            // Benchmark
            val timer = DateHelperRelative.markNow()
            var time: Double
            var counter = 0 // counts how often the query gets executed
            while (true) { // Loop for minimumTime2 seconds
                counter += groupSize[queryFileIdx]
                for (i in 0 until groupSize[queryFileIdx]) {
                    LuposdateEndpointML.evaluateSparqlToResultB(instance, query)
                }
                time = DateHelperRelative.elapsedSeconds(timer)
                if (time > minimumTime2) {
                    break
                }
            }
            println("$queryFile,$numberOfTriples2,0,$counter,${time * 1000.0},${counter / time},WithOptimizer")
        }
    }

    val fileName2 = "/home/a/lupos/luposdate-testdata/sp2b/1024/complete.n3.bench"
    var benchFile = java.io.File(fileName2)
    benchFile.printWriter().use { it ->
        it.print("")
    }

    // Benchmark without Optimizer
    if (optimizerMode2 != OptimizerMode.OnlyWith) { // All or OnlyWithout
        for (queryFileIdx in queryFiles2.indices) { // for every query
            for (joinOrder in 0..2) { // for every permutation
                // Read in query file
                val queryFile = queryFiles2[queryFileIdx]
                val query = File(queryFile).readAsString()
                // Optimize query and convert to operatorgraph
                val node = LuposdateEndpointML.evaluateSparqlToOperatorgraphB(instance, query, true, joinOrder)
                val writer = MyPrintWriter(false)
//                for (a in node.getChildren()) {
//                    if (a is lupos.triple_store_manager.POPTripleStoreIterator) {
//                    }
//                }

                LuposdateEndpointML.evaluateOperatorgraphToResult(instance, node, writer)
                // measure time for executing the query
                val timerFirst = DateHelperRelative.markNow()
                // Execute query
                LuposdateEndpointML.evaluateOperatorgraphToResult(instance, node, writer)
                // save time
                val timeFirst = DateHelperRelative.elapsedSeconds(timerFirst)
                groupSize[queryFileIdx] = 1 + (1.0 / timeFirst).toInt()
                // Benchmark
                val timer = DateHelperRelative.markNow()
                var time: Double
                var counter = 0 // counts how often the query gets executed
                while (true) { // loop for minimumTime2 seconds
                    counter += groupSize[queryFileIdx]
                    for (i in 0 until groupSize[queryFileIdx]) {
                        LuposdateEndpointML.evaluateOperatorgraphToResult(instance, node, writer)
                    }
                    time = DateHelperRelative.elapsedSeconds(timer)
                    if (time > minimumTime2) {
                        break
                    }
                }
                FileOutputStream(benchFile, true).bufferedWriter().use { it ->
                    it.appendLine("$queryFile $joinOrder ${counter / time}")
                }

//                benchFile.printWriter().use { it ->
//                    it.println("$queryFile $joinOrder ${counter / time}")
//                }
                println("$queryFile,$numberOfTriples2,0,$counter,${time * 1000.0},${counter / time},NoOptimizer")
            }
        }
    }
}
