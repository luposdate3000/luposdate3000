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
import lupos.shared.DateHelperRelative
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import lupos.shared.inline.ParallelThread

internal enum class OptimizerMode {
    All, OnlyWith, OnlyWithout
}

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(datasourceFiles: String, queryFiles: String, minimumTime: String, numberOfTriples: String, optimizerMode: String): Unit = ParallelThread.runBlocking {
    val instance = LuposdateEndpoint.initialize()
    ParallelThread.launch {
        HttpEndpointLauncher.start(instance)
    }
    val queryFiles2 = queryFiles.split(";")
    val minimumTime2 = minimumTime.toDouble()
    val numberOfTriples2 = numberOfTriples.toLong()
    val optimizerMode2: OptimizerMode = if (optimizerMode == "OnlyWith") {
        OptimizerMode.OnlyWith
    } else if (optimizerMode == "OnlyWithout") {
        OptimizerMode.OnlyWithout
    } else {
        OptimizerMode.All
    }
    val timer = DateHelperRelative.markNow()
    LuposdateEndpoint.importTripleFile(instance, datasourceFiles)
    val time = DateHelperRelative.elapsedSeconds(timer)
    println("$datasourceFiles/persistence-import.sparql,$numberOfTriples2,0,1,${numberOfTriples2 * 1000.0},${1.0 / time}")
    val groupSize = IntArray(queryFiles2.size) { 1 }
    if (optimizerMode2 != OptimizerMode.OnlyWithout) {
        for (queryFileIdx in queryFiles2.indices) {
            val queryFile = queryFiles2[queryFileIdx]
            val query = File(queryFile).readAsString()
            val timerFirst = DateHelperRelative.markNow()
            LuposdateEndpoint.evaluateSparqlToResultC(instance, query, true)
            val timeFirst = DateHelperRelative.elapsedSeconds(timerFirst)
            groupSize[queryFileIdx] = 1 + (1.0 / timeFirst).toInt()
            val timer2 = DateHelperRelative.markNow()
            var time2: Double
            var counter = 0
            while (true) {
                counter += groupSize[queryFileIdx]
                for (i in 0 until groupSize[queryFileIdx]) {
                    LuposdateEndpoint.evaluateSparqlToResultB(instance, query)
                }
                time2 = DateHelperRelative.elapsedSeconds(timer2)
                if (time2 > minimumTime2) {
                    break
                }
            }
            println("$queryFile,$numberOfTriples2,0,$counter,${time2 * 1000.0},${counter / time2},WithOptimizer")
        }
    }
    if (optimizerMode2 != OptimizerMode.OnlyWith) {
        for (queryFileIdx in queryFiles2.indices) {
            val queryFile = queryFiles2[queryFileIdx]
            val query = File(queryFile).readAsString()
            val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(instance, query, true)
            val writer = MyPrintWriter(false)
            LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, node, writer)
            val timerFirst = DateHelperRelative.markNow()
            LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, node, writer)
            val timeFirst = DateHelperRelative.elapsedSeconds(timerFirst)
            groupSize[queryFileIdx] = 1 + (1.0 / timeFirst).toInt()
            val timer2 = DateHelperRelative.markNow()
            var time2: Double
            var counter = 0
            while (true) {
                counter += groupSize[queryFileIdx]
                for (i in 0 until groupSize[queryFileIdx]) {
                    LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, node, writer)
                }
                time2 = DateHelperRelative.elapsedSeconds(timer2)
                if (time2 > minimumTime2) {
                    break
                }
            }
            println("$queryFile,$numberOfTriples2,0,$counter,${time2 * 1000.0},${counter / time2},NoOptimizer")
        }
    }
}
