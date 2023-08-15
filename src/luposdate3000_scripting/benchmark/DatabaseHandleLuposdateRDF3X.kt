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

import lupos.shared.inline.Platform
import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.net.HttpURLConnection
import java.net.URL

class DatabaseHandleLuposdateRDF3X(val workDir: String, val port: Int) : DatabaseHandle() {
    var processInstance: Process? = null
    override fun getThreads() = -1
    override fun getName(): String = "LuposdateRDF3X"
    override fun launch(import_file_name: String, abort: () -> Unit, action: () -> Unit) {
        File(workDir).deleteRecursively()
        File(workDir).mkdirs()
        val javaFileName = "/usr/lib/jvm/java-16-openjdk-amd64/bin/java"
        val javaFile = File(javaFileName)
        val cmd = mutableListOf<String>()
        if (javaFile.exists()) {
            cmd.add(javaFileName)
            cmd.add("-XX:+UnlockExperimentalVMOptions")
            cmd.add("-XX:+UseShenandoahGC")
            cmd.add("-XX:ShenandoahUncommitDelay=1000")
            cmd.add("-XX:ShenandoahGuaranteedGCInterval=10000")
        } else {
            cmd.add("java")
        }
        cmd.add("-Xmx${Platform.getAvailableRam()}g")
        cmd.add("-server")
        cmd.add("-cp")
        cmd.add(luposdateParallelJar)
        cmd.add("lupos.engine.indexconstruction.FastRDF3XIndexConstruction")
        cmd.add(import_file_name)
        cmd.add("N3")
        cmd.add("UTF-8")
        cmd.add("NONE")
        cmd.add("$workDir/luposdateindex")
        cmd.add("500000")
        cmd.add("4")
        cmd.add("2")
        val p_launcher = ProcessBuilder(cmd)
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
            "$workDir/luposdateindex",
            "port$port",
            "RDF3X_PARALLEL",
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

    override fun runQuery(query: String): String {
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
        return response
    }
}
