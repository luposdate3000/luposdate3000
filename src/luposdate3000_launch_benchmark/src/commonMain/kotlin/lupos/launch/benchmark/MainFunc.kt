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
package lupos.launch.benchmark

import lupos.endpoint.LuposdateEndpoint
import lupos.endpoint_launcher.HttpEndpointLauncher
import lupos.s00misc.DateHelperRelative
import lupos.s00misc.Parallel
import lupos.shared_inline.File
import lupos.shared_inline.MyPrintWriter

internal enum class OptimizerMode {
    All, OnlyWith, OnlyWithout
}

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(datasourceFiles: String, queryFiles: String, minimumTime: String, numberOfTriples: String, optimizerMode: String): Unit = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
    Parallel.launch {
        HttpEndpointLauncher.start()
    }
    val queryFiles2 = queryFiles.split(";")
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
    val timer = DateHelperRelative.markNow()
    LuposdateEndpoint.importIntermediateFiles(datasourceFiles)
    val time = DateHelperRelative.elapsedSeconds(timer)
    println("$datasourceFiles/persistence-import.sparql,$numberOfTriples2,0,1,${numberOfTriples2 * 1000.0},${1.0 / time}")
    val groupSize = IntArray(queryFiles2.size) { 1 }
    if (optimizerMode2 != OptimizerMode.OnlyWithout) {
        for (queryFileIdx in queryFiles2.indices) {
            val queryFile = queryFiles2[queryFileIdx]
            val query = File(queryFile).readAsString()
            val timerFirst = DateHelperRelative.markNow()
            LuposdateEndpoint.evaluateSparqlToResultC(query, true)
            val timeFirst = DateHelperRelative.elapsedSeconds(timerFirst)
            groupSize[queryFileIdx] = 1 + (1.0 / timeFirst).toInt()
            val timer = DateHelperRelative.markNow()
            var time: Double
            var counter = 0
            while (true) {
                counter += groupSize[queryFileIdx]
                for (i in 0 until groupSize[queryFileIdx]) {
                    LuposdateEndpoint.evaluateSparqlToResultB(query)
                }
                time = DateHelperRelative.elapsedSeconds(timer)
                if (time > minimumTime2) {
                    break
                }
            }
            println("$queryFile,$numberOfTriples2,0,$counter,${time * 1000.0},${counter / time},WithOptimizer")
        }
    }
    if (optimizerMode2 != OptimizerMode.OnlyWith) {
        for (queryFileIdx in queryFiles2.indices) {
            val queryFile = queryFiles2[queryFileIdx]
            val query = File(queryFile).readAsString()
            val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(query, true)
            val writer = MyPrintWriter(false)
            LuposdateEndpoint.evaluateOperatorgraphToResult(node, writer)
            val timerFirst = DateHelperRelative.markNow()
            LuposdateEndpoint.evaluateOperatorgraphToResult(node, writer)
            val timeFirst = DateHelperRelative.elapsedSeconds(timerFirst)
            groupSize[queryFileIdx] = 1 + (1.0 / timeFirst).toInt()
            val timer = DateHelperRelative.markNow()
            var time: Double
            var counter = 0
            while (true) {
                counter += groupSize[queryFileIdx]
                for (i in 0 until groupSize[queryFileIdx]) {
                    LuposdateEndpoint.evaluateOperatorgraphToResult(node, writer)
                }
                time = DateHelperRelative.elapsedSeconds(timer)
                if (time > minimumTime2) {
                    break
                }
            }
            println("$queryFile,$numberOfTriples2,0,$counter,${time * 1000.0},${counter / time},NoOptimizer")
        }
    }
}
