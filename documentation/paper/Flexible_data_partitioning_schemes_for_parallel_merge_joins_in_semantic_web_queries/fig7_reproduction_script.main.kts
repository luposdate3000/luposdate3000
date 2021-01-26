#!/usr/bin/env kotlin
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
@file:Import("../../../../src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ETripleComponentType.kt")
@file:Import("../../../../src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ETripleComponentTypeExt.kt")
@file:CompilerOptions("-Xmulti-platform")
import java.io.File
import java.io.PrintWriter
import java.lang.ProcessBuilder.Redirect
// ///////////////////////////////////////////////////////////////////////////////////////////////////////
// config options -> /////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////////////////////////////////////////////////////////////////////////////////////
val tmpFolder = "tmp_fig5_data" // point to a folder with enough storage space available
val minimumTime = 10.0 // the minimum time (in seconds) for a single measurement
val resultFolder = "fig5_result_data" // the folder where the results of the measurements are stored
//
// disable individual steps, if the program crashes in the middle due to "out of memory" followed by the out-of-memory-killer choosing this script instead of the database.
//
val enableCompile = false
val enableMeasuerments = true
val enableExtraction = true
val enableGrapic = true
// ///////////////////////////////////////////////////////////////////////////////////////////////////////
// config options <- /////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////////////////////////////////////////////////////////////////////////////////////
val tmpLogFile = File(File(resultFolder), "database.log")
File(tmpFolder).mkdirs()
File(resultFolder).mkdirs()
// compile
if (enableCompile) {
    val p = ProcessBuilder(
        "./launcher.main.kts",
        "--compileAll",
        "--releaseMode=Enable",
        "--inlineMode=Enable",
        "--compileArgument_Luposdate3000_Endpoint:QueryResultToStream=lupos.s11outputResult.QueryResultToEmptyStream",
    )
        .directory(File("."))
        .redirectOutput(Redirect.INHERIT)
        .redirectError(Redirect.INHERIT)
        .start()
    p.waitFor()
    if (p.exitValue() != 0) {
        throw Exception("exit-code:: " + p.exitValue())
    }
}
// perform the measurements
if (enableMeasuerments) {
    for (trash in listOf(0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096)) {
        execute(128, trash)
    }
    for (trash in listOf(0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512)) {
        execute(32768, trash)
    }
}
// extract relevant data
if (enableExtraction) {
}
// visualize data
if (enableGrapic) {
}
// remove temorary folders
File(tmpFolder).deleteRecursively()
// helper functions
fun execute(result_rows: Int, trash: Int) {
    val triples=generateData(result_rows, 10, 1, trash, tmpFolder)
    for (threads in listOf(1, 2, 4, 8, 16)) {
        for (database in listOf(databaseLuposdate3000)) {
            database.launch()
            for (query in listOf("q10.sparql")) {
                var counter = 0
                var starttime = System.nanoTime()
                val targettime = starttime + (minimumTime * 1_000_000_000.0).toLong()
                var currenttime = System.nanoTime()
                while (true) {
                    database.runQuery(query)
                    counter++
                    currenttime = System.nanoTime()
                    if (currenttime> targettime) {
                        break
                    }
                }
val timeInNanoseconds=currenttime-starttime
val timeInMilliSeconds=(timeInNanoseconds/1_000_000.0)
val timeInMilliSecondsPerRepetition=timeInMilliSeconds/counter
/*File xxx (database.getName()).append ...*/ println("$query,$trash,1,$threads,10,$triples,$result_rows,$counter,$timeInMilliSeconds,$timeInMilliSecondsPerRepetition")
            }
            database.terminate()
        }
    }
}
fun generateData(targetNumberOfResults: Int, numberOfPredicates: Int, blockCount: Int, trashCount: Int, folderName: String): Int {
// var targetNumberOfResults = 1L
// var numberOfPredicates = 2
// var blockCount = 1 //if there is a match, than x elements match in a row
// var trashCount = 0 //if there is no match, than x elements dont match in a row
// var folderName = "/tmp/luposdate"
    val preventMultiplesOfList = intArrayOf(2, 3, 5, 7, 11, 13, 17, 19)
    val object_counter = 100
    // successful match-blocks and trash-blocks are interleaved in the data (if the data is interpreted in the same ordering as generated)
    File(folderName).mkdirs()
    var outN3: PrintWriter = File(folderName + "/data0.n3").printWriter()
    // predefining unused values, to prevent mixing unwanted "new" values, which would break the target patterns
    val s_offset = 2 + object_counter + numberOfPredicates
    val p_offset = 2 + object_counter
    val o_offset = 2
    var total_count = 0
    outN3.println("@prefix b: <http://benchmark.com/> .")
    for (i in 0 until object_counter) {
        outN3.println("_:a1 b:unused b:o${i.toString(16)} .")
        total_count++
    }
    for (i in 0 until numberOfPredicates) {
        outN3.println("_:a1 b:unused b:p$i .")
        total_count++
    }
    var counter = 0
    loop@ while (targetNumberOfResults > 0) {
        for (p in 0 until numberOfPredicates) {
            for (j in 0 until blockCount) {
                outN3.println("_:s${counter.toString(16)} b:p$p b:o${((j + counter) % object_counter).toString(16)} .")
                total_count++
            }
        }
        targetNumberOfResults--
        counter++
        if (trashCount > 0) {
            var trashcounter = 1
            for (p in 0 until numberOfPredicates) {
                for (j in 0 until trashCount) {
                    outN3.println("_:s${counter.toString(16)} b:p$p b:o${((j + counter) % object_counter).toString(16)} .")
                    total_count++
                    counter++
                    trashcounter++
                }
            }
            var flag = true
            trashloop@ while (flag) {
                flag = false
                for (i in preventMultiplesOfList) {
                    if (trashcounter % i == 0) {
                        flag = true
                        outN3.println("_:s${counter.toString(16)} b:unused b:o${(counter % object_counter).toString(16)} .")
                        total_count++
                        counter++
                        trashcounter++
                    }
                }
            }
        }
    }
    outN3.close()
    return total_count
}
abstract class DatabaseHandle {
    abstract fun getName():String
    abstract fun launch(): Process
    abstract fun runQuery(filename: String)
    abstract fun terminate(p: Process)
}
val databaseLuposdate3000 = object : DatabaseHandle {
override fun getName():String="Luposdate3000"
    override fun launch(): Process {
        val p = ProcessBuilder(
            "./launcher.main.kts",
            "--run",
            "--releaseMode=Enable",
            "--inlineMode=Enable",
            "--proguardMode=On",
            "--mainClass=Endpoint",
        )
            .directory(File("."))
            .redirectOutput(Redirect.INHERIT)
            .redirectError(Redirect.INHERIT)
        return p.start()
    }
    override fun runQuery(filename: String) {
    }
    override fun terminate(p: Process) {
    }
}
