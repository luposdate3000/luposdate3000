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
import java.net.HttpURLConnection
import java.net.URL

class DatabaseHandleLuposdateMemory(val port: Int) : DatabaseHandle() {
    var processInstance: Process? = null
    override fun getThreads() = -1
    override fun getName(): String = "LuposdateMemory"
    override fun launch(import_file_name: String, abort: () -> Unit, action: () -> Unit) {
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
        cmd.add("-server")
        cmd.add("-Xmx${Platform.getAvailableRam()}g")
        cmd.add("-cp")
        cmd.add(luposdateJar)
        cmd.add("lupos.endpoint.server.Endpoint")
        cmd.add(import_file_name)
        cmd.add("port$port")
        cmd.add("MEMORY")
        println(cmd)
        val p = ProcessBuilder(
            cmd,
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
            println(inputline)
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
        println("runQuery $query")
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
