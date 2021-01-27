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
@file:Import("../../../src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("../../../src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystemExt.kt")
@file:Import("../../../src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ETripleComponentType.kt")
@file:Import("../../../src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ETripleComponentTypeExt.kt")
@file:Import("../../../src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/PlatformAlias.kt")
@file:Import("../../../src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("../../../src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:CompilerOptions("-Xmulti-platform")
import lupos.s00misc.Platform
import java.io.File
import java.io.PrintWriter
import java.lang.ProcessBuilder.Redirect
import java.net.HttpURLConnection
import java.net.URL
// ///////////////////////////////////////////////////////////////////////////////////////////////////////
// config options -> /////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////////////////////////////////////////////////////////////////////////////////////
val tmpFolder = "tmp_fig7_data" // point to a folder with enough storage space available
val minimumTime = 10.0 // the minimum time (in seconds) for a single measurement
val resultFolder = "fig7_result_data" // the folder where the results of the measurements are stored
val port = 2324 // the port to be used by luposdate3000
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
val databaseHandleLuposdate3000_1 = DatabaseHandleLuposdate3000(1)
val databaseHandleLuposdate3000_2 = DatabaseHandleLuposdate3000(2)
val databaseHandleLuposdate3000_4 = DatabaseHandleLuposdate3000(4)
val databaseHandleLuposdate3000_8 = DatabaseHandleLuposdate3000(8)
val databaseHandleLuposdate3000_16 = DatabaseHandleLuposdate3000(16)
val allDatabases = listOf(
    databaseHandleLuposdate3000_1,
    databaseHandleLuposdate3000_2,
    databaseHandleLuposdate3000_4,
    databaseHandleLuposdate3000_8,
    databaseHandleLuposdate3000_16,
)
var allDatabasePrintWriters = arrayOf<PrintWriter>()
val queries = mapOf("q10" to "PREFIX b: <http://benchmark.com/> SELECT * WHERE { ?s b:p0 ?o0 . ?s b:p1 ?o1 . ?s b:p2 ?o2 . ?s b:p3 ?o3 . ?s b:p4 ?o4 . ?s b:p5 ?o5 . ?s b:p6 ?o6 . ?s b:p7 ?o7 . ?s b:p8 ?o8 . ?s b:p9 ?o9 . }")
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
    allDatabasePrintWriters = Array(allDatabases.size) { File(File(resultFolder), "${allDatabases[it].getName()}.log".replace("(", "_").replace(")", "")).printWriter() }
    for (trash in listOf(0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096)) {
        execute(128, trash)
    }
    for (trash in listOf(0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512)) {
        execute(32768, trash)
    }
    for (printer in allDatabasePrintWriters) {
        printer.close()
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
    val triples = generateData(result_rows, 10, 1, trash, tmpFolder)
    for (databaseIdx in 0 until allDatabases.size) {
        val database = allDatabases[databaseIdx]
        database.launch {
            for ((queryname, query) in queries) {
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
                val timeInNanoseconds = currenttime - starttime
                val timeInMilliSeconds = (timeInNanoseconds / 1_000_000.0)
                val timeInMilliSecondsPerRepetition = timeInMilliSeconds / counter
                allDatabasePrintWriters[databaseIdx].println("$queryname,$trash,${database.getThreads()},$triples,$result_rows,$counter,$timeInMilliSeconds,$timeInMilliSecondsPerRepetition")
                allDatabasePrintWriters[databaseIdx].flush()
            }
        }
    }
}
fun generateData(targetNumberOfResults_: Int, numberOfPredicates: Int, blockCount: Int, trashCount: Int, folderName: String): Int {
// var targetNumberOfResults = 1L
// var numberOfPredicates = 2
// var blockCount = 1 //if there is a match, than x elements match in a row
// var trashCount = 0 //if there is no match, than x elements dont match in a row
// var folderName = "/tmp/luposdate"
    var targetNumberOfResults = targetNumberOfResults_
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
abstract class DatabaseHandle() {
    val hostname = Platform.getHostName()
    abstract fun getThreads(): Int
    abstract fun getName(): String
    abstract fun launch(action: () -> Unit): Unit
    abstract fun runQuery(query: String)
    abstract fun importData(file: String)
}
class DatabaseHandleLuposdate3000(val partitionCount: Int) : DatabaseHandle() {
    var processInstance: Process? = null
    override fun getThreads() = partitionCount
    override fun getName(): String = "Luposdate3000($partitionCount)"
    override fun launch(action: () -> Unit) {
        val p_launcher = if (partitionCount > 1) {
            ProcessBuilder(
                "./launcher.main.kts",
                "--run",
                "--releaseMode=Enable",
                "--inlineMode=Enable",
                "--proguardMode=On",
                "--mainClass=Endpoint",
                "--endpointMode=Java_Sockets",
                "--partitionMode=On",
                "--dryMode=Enable",
                "--runArgument_Luposdate3000_Launch_Endpoint:hostname=$hostname",
                "--runArgument_Luposdate3000_Launch_Endpoint:port=$port",
                "--runArgument_Luposdate3000_Launch_Endpoint:partitionCount=$partitionCount",
            )
        } else {
            ProcessBuilder(
                "./launcher.main.kts",
                "--run",
                "--releaseMode=Enable",
                "--inlineMode=Enable",
                "--proguardMode=On",
                "--mainClass=Endpoint",
                "--endpointMode=Java_Sockets",
                "--partitionMode=Off",
                "--dryMode=Enable",
                "--runArgument_Luposdate3000_Launch_Endpoint:hostname=$hostname",
                "--runArgument_Luposdate3000_Launch_Endpoint:port=$port",
                "--runArgument_Luposdate3000_Launch_Endpoint:partitionCount=$partitionCount",
            )
        }
            .directory(File("."))
            .redirectError(Redirect.INHERIT)
        val p_launcher_instance = p_launcher.start()
        var cmd = listOf<String>()
        p_launcher_instance!!.getInputStream().bufferedReader().use {
            var line = it.readLine()
            while (line != null) {
                if (line.startsWith("exec :: ")) {
                    cmd = line.substring("exec :: ".length).split(" ")
                }
                line = it.readLine()
            }
        }
        val p = ProcessBuilder(cmd).directory(File("."))
            .redirectError(Redirect.INHERIT)
        processInstance = p.start()
        val inputstream = processInstance!!.getInputStream()
        val reader = inputstream.bufferedReader()
        var line = reader.readLine()
        while (line != null) {
            if (line.contains("waiting for connections now")) {
                action()
                break
            }
            line = reader.readLine()
        }
        processInstance!!.destroy()
        reader.close()
        inputstream.close()
    }
    override fun runQuery(query: String) {
        val encodedData = query.encodeToByteArray()
        val u = URL("http://$hostname:$port/sparql/query")
        val conn = u.openConnection() as HttpURLConnection
        conn.setDoOutput(true)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        conn.setRequestProperty("Content-Length", "${encodedData.size}")
        conn.connect()
        val os = conn.getOutputStream()
        os.write(encodedData)
        conn.inputStream.bufferedReader().readText()
        if (conn.getResponseCode() != 200) {
            throw Exception("query failed")
        }
    }
    override fun importData(file: String) {
        val encodedData = file.encodeToByteArray()
        val u = URL("http://$hostname:$port/import/turtle")
        val conn = u.openConnection() as HttpURLConnection
        conn.setDoOutput(true)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        conn.setRequestProperty("Content-Length", "${encodedData.size}")
        conn.connect()
        val os = conn.getOutputStream()
        os.write(encodedData)
        conn.inputStream.bufferedReader().readText()
        if (conn.getResponseCode() != 200) {
            throw Exception("query failed")
        }
    }
}
