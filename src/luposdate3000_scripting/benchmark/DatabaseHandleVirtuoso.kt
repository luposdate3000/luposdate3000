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
package lupos.benchmark

import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.net.HttpURLConnection
import java.net.URL

class DatabaseHandleVirtuoso(val workDir: String) : DatabaseHandle() {
    var processInstance: Process? = null
    override fun getThreads() = -1
    override fun getName(): String = "Virtuoso"
    override fun launch(import_file_name: String, abort: () -> Unit, action: () -> Unit) {
        File(workDir).deleteRecursively()
        File(workDir).mkdirs()
        println("workDir:: " + workDir)
        File("$workDir/virtuoso.ini").printWriter().use { out ->
            File("${virtuosoBasePath}var/lib/virtuoso/db/virtuoso.ini").forEachLine { line ->
                when {
                    line.startsWith("DirsAllowed") -> {
                        out.println("DirsAllowed = /")
                    }
                    line.startsWith("NumberOfBuffers") -> {
                        out.println("NumberOfBuffers = 2720000")
                    }
                    line.startsWith("MaxDirtyBuffers") -> {
                        out.println("MaxDirtyBuffers = 2000000")
                    }
                    line.startsWith("ResultSetMaxRows") -> {
                        out.println("ResultSetMaxRows = 100000")
                    }
                    else -> {
                        out.println(line.replace("${virtuosoBasePath}var/lib/virtuoso/db/", "$workDir/").replace("$workDir/virtuoso.log", "/dev/stdout"))
                    }
                }
            }
        }
        File(import_file_name).copyTo(File("$workDir/data/${import_file_name.substring(import_file_name.lastIndexOf("/"))}"))
        File("$workDir/init").printWriter().use { out ->
            out.println("GRANT SPARQL_LOAD_SERVICE_DATA to \"SPARQL\";")
            out.println("GRANT SPARQL_UPDATE to \"SPARQL\";")
            val name = import_file_name.substring(import_file_name.lastIndexOf("."))
            out.println("ld_dir ('$workDir/data', '*$name', 'http://benchmark');")
            out.println("select * from DB.DBA.load_list;")
            out.println("rdf_loader_run();")
            out.println("checkpoint;")
        }
        val p = ProcessBuilder(
            "${virtuosoBasePath}bin/virtuoso-t",
            "-f",
            "-c",
            "$workDir/virtuoso.ini",
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
                    "$workDir/init",
                )
                    .directory(File("."))
                    .redirectOutput(Redirect.INHERIT)
                    .redirectError(Redirect.INHERIT)
                    .start()
                    .waitFor()
//                importData(import_file_name)
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

    override fun runQuery(query: String): String {
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
        return response
    }

    fun importData(file: String) {
        val encodedData = "default-graph-uri=${encode("http://benchmark")}&query=${encode("LOAD <file://${File(file).absolutePath}> into GRAPH <http://benchmark>")}&format=xml&timeout=0&debug=off&run=${encode(" Run Query")}".encodeToByteArray()
        val u = URL("http://$hostname:8890/sparql/")
        val conn = u.openConnection() as HttpURLConnection
        conn.setDoOutput(true)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        conn.setRequestProperty("Content-Length", "${encodedData.size}")
        conn.connect()
        val os = conn.getOutputStream()
        os.write(encodedData)
        println(encodedData.decodeToString())
        try {
            val response = conn.inputStream.bufferedReader().readText()
            println(response)
            val code = conn.getResponseCode()
            if (code != 200) {
                throw Exception("import failed with response code $code")
            }
        } catch (e: Throwable) {
            val response = conn.errorStream.bufferedReader().readText()
            println(response)
            throw e
        }
    }
}
