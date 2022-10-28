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
@file:Import("../../../src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EOperatingSystem.kt")
@file:Import("../../../src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EOperatingSystemExt.kt")
@file:Import("../../../src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/ETripleComponentType.kt")
@file:Import("../../../src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/ETripleComponentTypeExt.kt")
@file:Import("../../../src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/Platform.kt")
@file:Import("../../../src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/shared/inline/Platform.kt")
@file:CompilerOptions("-Xmulti-platform")

import lupos.shared.inline.Platform
import java.io.File
import java.io.PrintWriter
import java.lang.ProcessBuilder.Redirect
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

// ///////////////////////////////////////////////////////////////////////////////////////////////////////
// config options -> /////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////////////////////////////////////////////////////////////////////////////////////
val tmpFolder = "tmp_fig7_data" // point to a folder with enough storage space available
val minimumTime = 10.0 // the minimum time (in seconds) for a single measurement
val resultFolder = "fig7_result_data" // the folder where the results of the measurements are stored
val port = 2324 // the port to be used by luposdate3000
val blazeGraphJar = "documentation/paper/Flexible_data_partitioning_schemes_for_parallel_merge_joins_in_semantic_web_queries/blazegraph.jar"
val luposdateJar = "documentation/paper/Flexible_data_partitioning_schemes_for_parallel_merge_joins_in_semantic_web_queries/luposdate.jar"
val luposdateParallelJar = "documentation/paper/Flexible_data_partitioning_schemes_for_parallel_merge_joins_in_semantic_web_queries/luposdate-parallel.jar"
val virtuosoBasePath = "/opt/virtuoso-dist/" /*this folder contains the folders "bin", and "var/lib/virtuosodb" */
//
// disable individual steps, if the program crashes in the middle due to "out of memory" followed by the out-of-memory-killer choosing this script instead of the database.
//
val enableCompile = false
val enableMeasuerments = true
val enableGrapic = true
// ///////////////////////////////////////////////////////////////////////////////////////////////////////
// config options <- /////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////////////////////////////////////////////////////////////////////////////////////
val databaseHandleLuposdate3000_1 = DatabaseHandleLuposdate3000(1)
val databaseHandleLuposdate3000_2 = DatabaseHandleLuposdate3000(2)
val databaseHandleLuposdate3000_4 = DatabaseHandleLuposdate3000(4)
val databaseHandleLuposdate3000_8 = DatabaseHandleLuposdate3000(8)
val databaseHandleLuposdate3000_16 = DatabaseHandleLuposdate3000(16)
val databaseHandleJena = DatabaseHandleJena()
val databaseHandleBlazegraph = DatabaseHandleBlazegraph()
val databaseHandleLuposdateMemory = DatabaseHandleLuposdateMemory()
val databaseHandleLuposdateRDF3X = DatabaseHandleLuposdateRDF3X()
val databaseHandleVirtuoso = DatabaseHandleVirtuoso()
val allDatabases = listOf(
    databaseHandleLuposdate3000_1,
    databaseHandleLuposdate3000_2,
    databaseHandleLuposdate3000_4,
    databaseHandleLuposdate3000_8,
    databaseHandleLuposdate3000_16,
    databaseHandleJena,
    databaseHandleBlazegraph,
    databaseHandleLuposdateMemory,
    databaseHandleLuposdateRDF3X,
    databaseHandleVirtuoso,
)
val resultRowsArray = arrayOf(128, 32768)
var allDatabasePrintWriters = arrayOf<Array<PrintWriter>>()
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
    allDatabasePrintWriters = Array(allDatabases.size) { it -> Array(2) { t -> File(File(resultFolder), "${allDatabases[it].getName()}_${resultRowsArray[t]}.log".replace("(", "_").replace(")", "")).printWriter() } }
    for (trash in listOf(0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096)) {
        execute(resultRowsArray[0], trash)
    }
    for (trash in listOf(0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512)) {
        execute(resultRowsArray[1], trash)
    }
    for (printers in allDatabasePrintWriters) {
        for (printer in printers) {
            printer.close()
        }
    }
}
// visualize data
if (enableGrapic) {
    for (i in 0 until resultRowsArray.size) {
        val f = "$resultFolder/graph_${resultRowsArray[i]}"
        File(f + ".plot").printWriter().use { out ->
            out.println("set terminal png size 1920,1080")
            out.println("set output '$f.png'")
            out.println("set datafile separator ','")
            out.println("set notitle")
            out.println("set logscale y")
            out.println("set logscale x 2")
            out.println("set key outside right")
            out.println("set xlabel 'selectivity'")
            out.println("set ylabel 'ms per result row'")
            out.println("set format x \"\$\\\\frac{1}{1+2^{%L}}\$\"")
            out.print("plot ")
            for (d in 0 until allDatabases.size) {
                out.print("'" + File(File(resultFolder), "${allDatabases[d].getName()}_${resultRowsArray[i]}.log".replace("(", "_").replace(")", "")).getAbsolutePath() + "' using 1:2 with lines title '${allDatabases[d].getName()}', ")
            }
        }
        ProcessBuilder("gnuplot", "$f.plot")
            .directory(File("."))
            .redirectOutput(Redirect.INHERIT)
            .redirectError(Redirect.INHERIT)
            .start()
            .waitFor()
    }
}
// remove temorary folders
File(tmpFolder).deleteRecursively()
// helper functions
fun execute(result_rows: Int, trash: Int) {
    val triples = generateData(result_rows, 10, 1, trash, tmpFolder)
    for (databaseIdx in 0 until allDatabases.size) {
        val database = allDatabases[databaseIdx]
        try {
            var abortSignal = false
            database.launch(
                "$tmpFolder/data0.n3",
                {
                    abortSignal = true
                },
                {
                    for ((queryname, query) in queries) {
                        try {
                            database.runQuery(query)
                            var counter = 0
                            var starttime = System.nanoTime()
                            val targettime = starttime + (minimumTime * 1_000_000_000.0).toLong()
                            var currenttime = starttime
                            while (!abortSignal && currenttime < targettime) {
                                database.runQuery(query)
                                counter++
                                currenttime = System.nanoTime()
                            }
                            if (!abortSignal) {
                                val timeInNanoseconds = currenttime - starttime
                                val timeInMilliSeconds = (timeInNanoseconds / 1_000_000.0)
                                val timeInMilliSecondsPerRepetition = timeInMilliSeconds / counter
                                val timeInMilliSecondsPerResultRow = timeInMilliSeconds / (result_rows * counter)
                                allDatabasePrintWriters[databaseIdx][resultRowsArray.indexOf(result_rows)].println("$trash,$timeInMilliSecondsPerResultRow,$queryname,${database.getThreads()},$triples,$counter,$timeInMilliSeconds,$timeInMilliSecondsPerRepetition")
                                allDatabasePrintWriters[databaseIdx][resultRowsArray.indexOf(result_rows)].flush()
                            }
                        } catch (e: Throwable) {
                            abortSignal = true
                            e.printStackTrace()
                        }
                        if (abortSignal) {
                            println("errored query ${database.getName()},$queryname,$trash,${database.getThreads()},$triples,$result_rows")
                        }
                    }
                }
            )
        } catch (e: Throwable) {
            println("errored import ${database.getName()},???,$trash,${database.getThreads()},$triples,$result_rows")
            e.printStackTrace()
        }
    }
}

fun generateData(targetNumberOfResults_: Int, numberOfPredicates: Int, blockCount: Int, trashCount: Int, folderName: String): Int {
    var targetNumberOfResults = targetNumberOfResults_
    val preventMultiplesOfList = intArrayOf(2, 3, 5, 7, 11, 13, 17, 19)
    val object_counter = 100
    // successful match-blocks and trash-blocks are interleaved in the data (if the data is interpreted in the same ordering as generated)
    File(folderName).deleteRecursively()
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
    abstract fun launch(import_file_name: String, abort: () -> Unit, action: () -> Unit): Unit
    abstract fun runQuery(query: String)
    fun encode(s: String): String {
        return URLEncoder.encode(s, "utf-8").replace("+", "%20").replace("*", "%2A")
    }
}

class DatabaseHandleLuposdate3000(val partitionCount: Int) : DatabaseHandle() {
    var processInstance: Process? = null
    override fun getThreads() = partitionCount
    override fun getName(): String = "Luposdate3000($partitionCount)"
    override fun launch(import_file_name: String, abort: () -> Unit, action: () -> Unit) {
        val p_launcher = if (partitionCount > 1) {
            ProcessBuilder(
                "./launcher.main.kts",
                "--run",
                "--releaseMode=Enable",
                "--mainClass=Launch_Endpoint",
                "--Network_Wrapper=Java_Sockets",
                "--partitionMode=Threads",
                "--dryMode=Enable",
                "--threadCount=$partitionCount",
                "--processUrlsQuery=$hostname:$port",
                "--processUrlsStore=$hostname:$port",
            )
        } else {
            ProcessBuilder(
                "./launcher.main.kts",
                "--run",
                "--releaseMode=Enable",
                "--mainClass=Launch_Endpoint",
                "--Network_Wrapper=Java_Sockets",
                "--partitionMode=None",
                "--dryMode=Enable",
                "--threadCount=$partitionCount",
                "--processUrlsQuery=$hostname:$port",
                "--processUrlsStore=$hostname:$port",
            )
        }
            .directory(File("."))
            .redirectError(Redirect.INHERIT)
        val p_launcher_instance = p_launcher.start()
        var cmd = listOf<String>()
        var env2 = mutableMapOf<String, String>()
        p_launcher_instance!!.getInputStream().bufferedReader().use {
            var line = it.readLine()
            while (line != null) {
                if (line.startsWith("exec :: ")) {
                    cmd = line.substring("exec :: ".length).split(" ")
                } else if (line.startsWith("export ")) {
                    val tmp = line.substring("export ".length).split("=")
                    env2[tmp[0]] = tmp[1]
                }
                line = it.readLine()
            }
        }
        val p = ProcessBuilder(cmd).directory(File("."))
        val env = p.environment()
        env.putAll(env2) 
        processInstance = p.start()
        val inputstream = processInstance!!.getInputStream()
        val inputreader = inputstream.bufferedReader()
        var inputline = inputreader.readLine()
        var inputThread = Thread {
            while (inputline != null) {
                inputline = inputreader.readLine()
            }
        }
        val errorstream = processInstance!!.getErrorStream()
        val errorreader = errorstream.bufferedReader()
        var errorThread = Thread {
            var errorline = errorreader.readLine()
            while (errorline != null) {
                println(errorline)
                if (errorline.contains("Exception")) {
                    abort()
                }
                errorline = errorreader.readLine()
            }
        }
        while (inputline != null) {
            if (inputline.contains("waiting for connections now")) {
                inputThread.start()
                errorThread.start()
                importData(import_file_name)
                action()
                break
            }
            inputline = inputreader.readLine()
        }
        processInstance!!.destroy()
        inputreader.close()
        inputstream.close()
        inputThread.stop()
        errorThread.stop()
    }

    override fun runQuery(query: String) {
        val encodedData = "query=${encode(query)}".encodeToByteArray()
        val u = URL("http://$hostname:$port/sparql/query")
        val conn = u.openConnection() as HttpURLConnection
        conn.setDoOutput(true)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        conn.setRequestProperty("Content-Length", "${encodedData.size}")
        conn.connect()
        val os = conn.getOutputStream()
        os.write(encodedData)
        val response = conn.inputStream.bufferedReader().readText()
        val code = conn.getResponseCode()
        if (code != 200) {
            throw Exception("query failed with response code $code")
        }
    }

    fun importData(file: String) {
        val encodedData = "file=${encode(file)}".encodeToByteArray()
        val u = URL("http://$hostname:$port/import/turtle")
        val conn = u.openConnection() as HttpURLConnection
        conn.setDoOutput(true)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        conn.setRequestProperty("Content-Length", "${encodedData.size}")
        conn.connect()
        val os = conn.getOutputStream()
        os.write(encodedData)
        val response = conn.inputStream.bufferedReader().readText()
        val code = conn.getResponseCode()
        if (code != 200) {
            throw Exception("import failed with response code $code")
        }
    }
}

class DatabaseHandleJena() : DatabaseHandle() {
    var processInstance: Process? = null
    override fun getThreads() = -1
    override fun getName(): String = "Jena"
    override fun launch(import_file_name: String, abort: () -> Unit, action: () -> Unit) {
        val p_launcher = ProcessBuilder(
            "./launcher.main.kts",
            "--run",
            "--jenaWrapper=On",
            "--releaseMode=Enable",
            "--mainClass=Launch_Endpoint",
            "--Network_Wrapper=Java_Sockets",
            "--partitionMode=None",
            "--dryMode=Enable",
            "--threadCount=1",
            "--processUrlsQuery=$hostname:$port",
            "--processUrlsStore=$hostname:$port",
        )
            .directory(File("."))
            .redirectError(Redirect.INHERIT)
        val p_launcher_instance = p_launcher.start()
        var cmd = listOf<String>()
        var env2 = mutableMapOf<String, String>()
        p_launcher_instance!!.getInputStream().bufferedReader().use {
            var line = it.readLine()
            while (line != null) {
                if (line.startsWith("exec :: ")) {
                    cmd = line.substring("exec :: ".length).split(" ")
                } else if (line.startsWith("export ")) {
                    val tmp = line.substring("export ".length).split("=")
                    env2[tmp[0]] = tmp[1]
                }
                line = it.readLine()
            }
        }
        val p = ProcessBuilder(cmd).directory(File("."))
        val env = p.environment()
        env.putAll(env2)
        processInstance = p.start()
        val inputstream = processInstance!!.getInputStream()
        val inputreader = inputstream.bufferedReader()
        var inputline = inputreader.readLine()
        var inputThread = Thread {
            println(inputline)
            while (inputline != null) {
                inputline = inputreader.readLine()
            }
        }
        val errorstream = processInstance!!.getErrorStream()
        val errorreader = errorstream.bufferedReader()
        var errorThread = Thread {
            var errorline = errorreader.readLine()
            while (errorline != null) {
                println(errorline)
                if (errorline.contains("Exception")) {
                    abort()
                }
                errorline = errorreader.readLine()
            }
        }
        while (inputline != null) {
            println(inputline)
            if (inputline.contains("waiting for connections now")) {
                inputThread.start()
                errorThread.start()
                importData(import_file_name)
                action()
                break
            }
            inputline = inputreader.readLine()
        }
        processInstance!!.destroy()
        inputreader.close()
        inputstream.close()
        inputThread.stop()
        errorThread.stop()
    }

    override fun runQuery(query: String) {
        val encodedData = "query=${encode(query)}".encodeToByteArray()
        val u = URL("http://$hostname:$port/sparql/jenaquery")
        val conn = u.openConnection() as HttpURLConnection
        conn.setDoOutput(true)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        conn.setRequestProperty("Content-Length", "${encodedData.size}")
        conn.connect()
        val os = conn.getOutputStream()
        os.write(encodedData)
        val response = conn.inputStream.bufferedReader().readText()
        val code = conn.getResponseCode()
        if (code != 200) {
            throw Exception("query failed with response code $code")
        }
    }

    fun importData(file: String) {
        val encodedData = "file=${encode(file)}".encodeToByteArray()
        val u = URL("http://$hostname:$port/sparql/jenaload")
        val conn = u.openConnection() as HttpURLConnection
        conn.setDoOutput(true)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        conn.setRequestProperty("Content-Length", "${encodedData.size}")
        conn.connect()
        val os = conn.getOutputStream()
        os.write(encodedData)
        val response = conn.inputStream.bufferedReader().readText()
        val code = conn.getResponseCode()
        if (code != 200) {
            throw Exception("import failed with response code $code")
        }
    }
}

class DatabaseHandleBlazegraph() : DatabaseHandle() {
    var processInstance: Process? = null
    override fun getThreads() = -1
    override fun getName(): String = "Blazegraph"
    override fun launch(import_file_name: String, abort: () -> Unit, action: () -> Unit) {
        val p = ProcessBuilder(
            "java",
            "-server",
            "-jar",
            blazeGraphJar
        ).directory(File("."))
        processInstance = p.start()
        val inputstream = processInstance!!.getInputStream()
        val inputreader = inputstream.bufferedReader()
        var inputline = inputreader.readLine()
        var inputThread = Thread {
            while (inputline != null) {
                inputline = inputreader.readLine()
            }
        }
        val errorstream = processInstance!!.getErrorStream()
        val errorreader = errorstream.bufferedReader()
        var errorThread = Thread {
            var errorline = errorreader.readLine()
            while (errorline != null) {
                if (errorline.contains("Exception")) {
                    abort()
                }
                println(errorline)
                errorline = errorreader.readLine()
            }
        }
        while (inputline != null) {
            if (inputline.startsWith("Go to ") && inputline.endsWith("/blazegraph/ to get started.")) {
                inputThread.start()
                errorThread.start()
                importData(import_file_name)
                action()
                break
            }
            inputline = inputreader.readLine()
        }
        processInstance!!.destroy()
        inputreader.close()
        inputstream.close()
        inputThread.stop()
        errorThread.stop()
    }

    override fun runQuery(query: String) {
        val encodedData = "query=${encode(query)}".encodeToByteArray()
        val u = URL("http://$hostname:9999/blazegraph/sparql")
        val conn = u.openConnection() as HttpURLConnection
        conn.setDoOutput(true)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        conn.setRequestProperty("Content-Length", "${encodedData.size}")
        conn.connect()
        val os = conn.getOutputStream()
        os.write(encodedData)
        val response = conn.inputStream.bufferedReader().readText()
        val code = conn.getResponseCode()
        if (code != 200) {
            throw Exception("query failed with response code $code")
        }
    }

    fun importData(file: String) {
        val encodedData = "update=LOAD <file://${File(file).getAbsolutePath()}>;".encodeToByteArray()
        val u = URL("http://$hostname:9999/blazegraph/namespace/kb/sparql")
        val conn = u.openConnection() as HttpURLConnection
        conn.setDoOutput(true)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        conn.setRequestProperty("Content-Length", "${encodedData.size}")
        conn.connect()
        val os = conn.getOutputStream()
        os.write(encodedData)
        val response = conn.inputStream.bufferedReader().readText()
        val code = conn.getResponseCode()
        if (code != 200) {
            throw Exception("import failed with response code $code")
        }
    }
}

class DatabaseHandleLuposdateMemory() : DatabaseHandle() {
    var processInstance: Process? = null
    override fun getThreads() = -1
    override fun getName(): String = "LuposdateMemory"
    override fun launch(import_file_name: String, abort: () -> Unit, action: () -> Unit) {
        val p = ProcessBuilder(
            "java",
            "-cp",
            luposdateJar,
            "lupos.endpoint.server.Endpoint",
            import_file_name,
            "port$port",
            "MEMORY"
        ).directory(File("."))
        processInstance = p.start()
        val inputstream = processInstance!!.getInputStream()
        val inputreader = inputstream.bufferedReader()
        var inputline = inputreader.readLine()
        var inputThread = Thread {
            while (inputline != null) {
                inputline = inputreader.readLine()
            }
        }
        val errorstream = processInstance!!.getErrorStream()
        val errorreader = errorstream.bufferedReader()
        var errorThread = Thread {
            var errorline = errorreader.readLine()
            while (errorline != null) {
                if (errorline.contains("Exception")) {
                    abort()
                }
                println(errorline)
                errorline = errorreader.readLine()
            }
        }
        while (inputline != null) {
            if (inputline.contains("Endpoint ready to receive requests")) {
                inputThread.start()
                errorThread.start()
                action()
                break
            }
            inputline = inputreader.readLine()
        }
        processInstance!!.destroy()
        inputreader.close()
        inputstream.close()
        inputThread.stop()
        errorThread.stop()
    }

    override fun runQuery(query: String) {
        val encodedData = "query=${encode(query)}".encodeToByteArray()
        val u = URL("http://$hostname:$port/sparql")
        val conn = u.openConnection() as HttpURLConnection
        conn.setDoOutput(true)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        conn.setRequestProperty("Content-Length", "${encodedData.size}")
        conn.connect()
        val os = conn.getOutputStream()
        os.write(encodedData)
        val response = conn.inputStream.bufferedReader().readText()
        val code = conn.getResponseCode()
        if (code != 200) {
            throw Exception("query failed with response code $code")
        }
    }
}

class DatabaseHandleLuposdateRDF3X() : DatabaseHandle() {
    var processInstance: Process? = null
    override fun getThreads() = -1
    override fun getName(): String = "LuposdateRDF3X"
    override fun launch(import_file_name: String, abort: () -> Unit, action: () -> Unit) {
        val p_launcher = ProcessBuilder(
            "java",
            "-cp",
            luposdateParallelJar,
            "lupos.engine.indexconstruction.FastRDF3XIndexConstruction",
            import_file_name,
            "N3",
            "UTF-8",
            "NONE",
            "$tmpFolder/luposdateindex",
            "500000",
            "4",
            "2"
        )
            .redirectOutput(Redirect.INHERIT)
            .redirectError(Redirect.INHERIT)
            .start()
        p_launcher.waitFor()
        if (p_launcher.exitValue() != 0) {
            throw Exception("import failed with error code:: " + p_launcher.exitValue())
        }
        val p = ProcessBuilder(
            "java",
            "-cp",
            luposdateParallelJar,
            "lupos.endpoint.server.Endpoint",
            "$tmpFolder/luposdateindex",
            "port$port",
            "RDF3X_PARALLEL"
        ).directory(File("."))
        processInstance = p.start()
        val inputstream = processInstance!!.getInputStream()
        val inputreader = inputstream.bufferedReader()
        var inputline = inputreader.readLine()
        var inputThread = Thread {
            while (inputline != null) {
                inputline = inputreader.readLine()
            }
        }
        val errorstream = processInstance!!.getErrorStream()
        val errorreader = errorstream.bufferedReader()
        var errorThread = Thread {
            var errorline = errorreader.readLine()
            while (errorline != null) {
                if (errorline.contains("Exception")) {
                    abort()
                }
                println(errorline)
                errorline = errorreader.readLine()
            }
        }
        while (inputline != null) {
            if (inputline.contains("Endpoint ready to receive requests")) {
                inputThread.start()
                errorThread.start()
                action()
                break
            }
            inputline = inputreader.readLine()
        }
        processInstance!!.destroy()
        inputreader.close()
        inputstream.close()
        inputThread.stop()
        errorThread.stop()
    }

    override fun runQuery(query: String) {
        val encodedData = "query=${encode(query)}".encodeToByteArray()
        val u = URL("http://$hostname:$port/sparql")
        val conn = u.openConnection() as HttpURLConnection
        conn.setDoOutput(true)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        conn.setRequestProperty("Content-Length", "${encodedData.size}")
        conn.connect()
        val os = conn.getOutputStream()
        os.write(encodedData)
        val response = conn.inputStream.bufferedReader().readText()
        val code = conn.getResponseCode()
        if (code != 200) {
            throw Exception("query failed with response code $code")
        }
    }
}

class DatabaseHandleVirtuoso() : DatabaseHandle() {
    var processInstance: Process? = null
    override fun getThreads() = -1
    override fun getName(): String = "Virtuoso"
    override fun launch(import_file_name: String, abort: () -> Unit, action: () -> Unit) {
        File("$tmpFolder/virtuoso.ini").printWriter().use { out ->
            File("${virtuosoBasePath}var/lib/virtuoso/db/virtuoso.ini").forEachLine { line ->
                out.println(line.replace("${virtuosoBasePath}var/lib/virtuoso/db/", "$tmpFolder/").replace("$tmpFolder/virtuoso.log", "/dev/stdout"))
            }
        }
        File("$tmpFolder/init").printWriter().use { out ->
            out.println("GRANT SPARQL_LOAD_SERVICE_DATA to \"SPARQL\";")
            out.println("GRANT SPARQL_UPDATE to \"SPARQL\";")
        }
        val p = ProcessBuilder(
            "${virtuosoBasePath}bin/virtuoso-t",
            "-f",
            "-c",
            "$tmpFolder/virtuoso.ini",
        )
            .directory(File("."))
        processInstance = p.start()
        val inputstream = processInstance!!.getInputStream()
        val inputreader = inputstream.bufferedReader()
        var inputline = inputreader.readLine()
        var inputThread = Thread {
            println(inputline)
            while (inputline != null) {
                inputline = inputreader.readLine()
            }
        }
        while (inputline != null) {
            println(inputline)
            if (inputline.contains("Server online at ")) {
                inputThread.start()
                ProcessBuilder(
                    "${virtuosoBasePath}bin/isql",
                    "1111",
                    "dba",
                    "dba",
                    "$tmpFolder/init"
                )
                    .directory(File("."))
                    .redirectOutput(Redirect.INHERIT)
                    .redirectError(Redirect.INHERIT)
                    .start()
                    .waitFor()
                importData(import_file_name)
                action()
                break
            }
            inputline = inputreader.readLine()
        }
        processInstance!!.destroy()
        inputreader.close()
        inputstream.close()
        inputThread.stop()
    }

    override fun runQuery(query: String) {
        val encodedData = "default-graph-uri=${encode("http://benchmark")}&query=${encode(query)}&format=xml&timeout=0&debug=off&run=${encode(" Run Query")}".encodeToByteArray()
        val u = URL("http://$hostname:8890/sparql/")
        val conn = u.openConnection() as HttpURLConnection
        conn.setDoOutput(true)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        conn.setRequestProperty("Content-Length", "${encodedData.size}")
        conn.connect()
        val os = conn.getOutputStream()
        os.write(encodedData)
        val response = conn.inputStream.bufferedReader().readText()
        val code = conn.getResponseCode()
        if (code != 200) {
            throw Exception("query failed with response code $code")
        }
    }

    fun importData(file: String) {
        val encodedData = "default-graph-uri=${encode("http://benchmark")}&query=${encode("LOAD <file://${File(file).getAbsolutePath()}> into GRAPH <http://benchmark>")}&format=xml&timeout=0&debug=off&run=${encode(" Run Query")}".encodeToByteArray()
        val u = URL("http://$hostname:8890/sparql/")
        val conn = u.openConnection() as HttpURLConnection
        conn.setDoOutput(true)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        conn.setRequestProperty("Content-Length", "${encodedData.size}")
        conn.connect()
        val os = conn.getOutputStream()
        os.write(encodedData)
        val response = conn.inputStream.bufferedReader().readText()
        println(response)
        val code = conn.getResponseCode()
        if (code != 200) {
            throw Exception("import failed with response code $code")
        }
    }
}
