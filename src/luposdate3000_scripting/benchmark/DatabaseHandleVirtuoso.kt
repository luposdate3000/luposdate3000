/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of>
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
