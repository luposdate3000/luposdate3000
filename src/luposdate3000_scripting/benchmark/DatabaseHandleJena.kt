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

class DatabaseHandleJena(val port: Int) : DatabaseHandle() {
    var processInstance: Process? = null
    override fun getThreads() = -1
    override fun getName(): String = "Jena"
    override fun launch(import_file_name: String, abort: () -> Unit, action: () -> Unit) {
        val p_launcher = ProcessBuilder(
            "./launcher.main.kts",
            "--run",
            "--Jena_Wrapper=On",
            "--releaseMode=Enable",
            "--inlineMode=Enable",
            "--proguardMode=On",
            "--mainClass=Endpoint",
            "--Endpoint_Launcher=Java_Sockets",
            "--partitionMode=None",
            "--dryMode=Enable",
            "--threadCount=1",
            "--processUrls=$hostname:$port",
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

    override fun runQuery(query: String): String {
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
        return response
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
