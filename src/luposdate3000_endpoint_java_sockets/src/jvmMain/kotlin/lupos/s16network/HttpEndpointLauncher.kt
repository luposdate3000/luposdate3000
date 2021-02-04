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
package lupos.s16network
import lupos.s00misc.EnpointRecievedInvalidPath
import lupos.s00misc.File
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.Parallel
import lupos.s00misc.Partition
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s11outputResult.EQueryResultToStreamExt
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.URLDecoder
internal class MyPrintWriterExtension(out: OutputStream) : MyPrintWriter(out) {
    private var counter = 0
    override fun print(x: String) {
        val len = x.length
        counter += len
        if (counter > 8192) {
            flush()
            counter = len
        }
        super.print(x)
    }
}
@OptIn(ExperimentalStdlibApi::class)
public actual object HttpEndpointLauncher {
    private fun printHeaderSuccess(stream: MyPrintWriter) {
        stream.println("HTTP/1.1 200 OK")
        stream.println("Content-Type: text/plain")
        stream.println()
    }
    internal fun extractParamsFromString(str: String, params: MutableMap<String, String>) {
        for (p in str.split('&')) {
            val t = p.split('=')
            if (t.size> 1) {
                params[t[0]] = URLDecoder.decode(t[1])
            }
        }
    }
    internal fun inputElement(name: String, value: String): String = "<input type=\"text\" name=\"$name\" value=\"$value\"/>"
    internal class PathMappingHelper(val addPostParams: Boolean/*parse the post-body as additional parameters for the query*/, val params: Map<Pair<String/*name*/, String/*default-value*/>, (String, String)->String/*html-string of element*/>, val action: () -> Unit/*action to perform, when this is the called url*/)
    public actual /*suspend*/ fun start() {
        val hosturl = Partition.myProcessUrls[Partition.myProcessId].split(":")
        val hostname = hosturl[0]
        val port = if (hosturl.size> 1) {
            hosturl[1].toInt()
        } else {
            80
        }
        try {
            val server = ServerSocket()
            server.bind(InetSocketAddress("0.0.0.0", port)) // maybe use "::" for ipv6
            println("launched server socket on '0.0.0.0':'$port' - waiting for connections now")
            while (true) {
                val connection = server.accept()
                Thread {
                    Parallel.runBlocking {
//                        var timertotal = DateHelperRelative.markNow()
//                        var timer = timertotal
                        var connectionIn: BufferedReader? = null
                        var connectionOut: MyPrintWriter? = null
                        try {
                            connectionIn = BufferedReader(InputStreamReader(connection.getInputStream()))
                            connectionOut = MyPrintWriterExtension(connection.getOutputStream())
                            var line = connectionIn.readLine()
                            var path = ""
                            var isPost = false
                            val params = mutableMapOf<String, String>()
                            while (line != null && line.isNotEmpty()) {
                                if (line.startsWith("POST")) {
                                    isPost = true
                                    path = line.substring(5)
                                }
                                if (line.startsWith("GET")) {
                                    path = line.substring(4)
                                }
                                line = connectionIn.readLine()
                            }
                            var idx = path.indexOf(' ')
                            if (idx > 0) {
                                path = path.substring(0, idx)
                            }
                            idx = path.indexOf('?')
                            if (idx > 0) {
                                extractParamsFromString(path.substring(idx + 1), params)
                                path = path.substring(0, idx)
                            }
                            val content = StringBuilder()
                            while (connectionIn.ready()) {
                                content.append(connectionIn.read().toChar())
                            }
                            val paths = mutableMapOf<String, PathMappingHelper>()
                            paths[ "/sparql/jenaquery" ] = PathMappingHelper(true, mapOf(Pair("query", "SELECT * WHERE {?s <p> ?o . ?s ?p <o>}") to ::inputElement)) {
                                printHeaderSuccess(connectionOut)
                                connectionOut.print(JenaWrapper.execQuery(params["query"]!!))
                                /*Coverage Unreachable*/
                            }
                            paths[ "/sparql/jenaload" ] = PathMappingHelper(true, mapOf(Pair("file", "/mnt/luposdate-testdata/sp2b/1024/complete.n3") to ::inputElement)) {
                                JenaWrapper.loadFromFile(params["file"]!!)
                                printHeaderSuccess(connectionOut)
                                connectionOut.print("success")
                            }
                            paths[ "/sparql/query" ] = PathMappingHelper(
                                true,
                                mapOf(
                                    Pair("query", "SELECT * WHERE {?s <p> ?o . ?s ?p <o>}") to ::inputElement,
                                    Pair("evaluator", "") to { it, value ->
                                        var res: String = "<select name=\"$it\">"
                                        for (evaluator in EQueryResultToStreamExt.names) {
                                            res += "<option>$evaluator</option>"
                                        }
                                        res + "</select>"
                                    }
                                )
                            ) {
                                val e = params["evaluator"]
                                val evaluator = if (e == null) {
                                    EQueryResultToStreamExt.DEFAULT_STREAM
                                } else {
                                    val e2 = EQueryResultToStreamExt.names.indexOf(e)
                                    if (e2 >= 0) {
                                        e2
                                    } else {
                                        EQueryResultToStreamExt.DEFAULT_STREAM
                                    }
                                }
                                val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(params["query"]!!, false)
                                LuposdateEndpoint.evaluateOperatorgraphToResultA(node, connectionOut, evaluator)
                                /*Coverage Unreachable*/
                            }
                            paths["/sparql/operator" ] = PathMappingHelper(true, mapOf(Pair("query", "") to ::inputElement)) {
                                printHeaderSuccess(connectionOut)
                                connectionOut.print(LuposdateEndpoint.evaluateOperatorgraphxmlToResultB(params["query"]!!, true))
                                /*Coverage Unreachable*/
                            }
                            paths["/import/turtle" ] = PathMappingHelper(true, mapOf(Pair("file", "/mnt/luposdate-testdata/sp2b/1024/complete.n3") to ::inputElement)) {
                                val dict = mutableMapOf<String, Int>()
                                val dictfile = params["bnodeList"]
                                if (dictfile != null) {
                                    File(dictfile).forEachLine {
                                        dict[it] = nodeGlobalDictionary.createNewBNode()
                                    }
                                }
                                printHeaderSuccess(connectionOut)
                                connectionOut.print(LuposdateEndpoint.importTurtleFiles(params["file"]!!, dict))
                                /*Coverage Unreachable*/
                            }
                            paths["/import/estimatedPartitions" ] = PathMappingHelper(true, mapOf(Pair("file", "/mnt/luposdate-testdata/sp2b/1024/complete.n3.partitions") to ::inputElement)) {
                                LuposdateEndpoint.setEstimatedPartitionsFromFile(params["file"]!!)
                                printHeaderSuccess(connectionOut)
                                connectionOut.println("Partitining-Scheme :: ")
                                connectionOut.println("${Partition.estimatedPartitions0}")
                                connectionOut.println("${Partition.estimatedPartitions1}")
                                connectionOut.println("${Partition.estimatedPartitions2}")
                                connectionOut.println("${Partition.estimatedPartitionsValid}")
                            }
                            paths["/import/intermediate" ] = PathMappingHelper(true, mapOf(Pair("file", "/mnt/luposdate-testdata/sp2b/1024/complete.n3") to ::inputElement)) {
                                printHeaderSuccess(connectionOut)
                                connectionOut.print(LuposdateEndpoint.importIntermediateFiles(params["file"]!!))
                                /*Coverage Unreachable*/
                            }
                            paths["/import/xml" ] = PathMappingHelper(true, mapOf(Pair("xml", "") to ::inputElement)) {
                                printHeaderSuccess(connectionOut)
                                connectionOut.print(LuposdateEndpoint.importXmlData(params["xml"]!!))
                                /*Coverage Unreachable*/
                            }
                            paths["/index.html" ] = PathMappingHelper(true, mapOf()) {
                                connectionOut.println("HTTP/1.1 200 OK")
                                connectionOut.println("Content-Type: text/html; charset=UTF-8")
                                connectionOut.println()
                                connectionOut.println("<!DOCTYPE html>")
                                connectionOut.println("<html lang=\"en\">")
                                connectionOut.println("   <head>")
                                connectionOut.println("   <title>Luposdate3000</title>")
                                connectionOut.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>")
                                connectionOut.println("<script>")
                                connectionOut.println("   $(document).ready(function() {")
                                for ((k, v) in paths) {
                                    if (k.length> 1) {
                                        val formId = k.replace("/", "_")
                                        connectionOut.println("       $('#$formId').on(\"submit\", function(event) {")
                                        connectionOut.println("           var formData = {")
                                        for ((p, q) in v.params) {
                                            connectionOut.println("               '${p.first}': $('#$formId [name=${p.first}]').val(),")
                                        }
                                        connectionOut.println("           };")
                                        connectionOut.println("           $.ajax({")
                                        connectionOut.println("                   type: 'POST',")
                                        connectionOut.println("                   url: '${k.substring(1)}',")
                                        connectionOut.println("                   data: formData")
                                        connectionOut.println("               })")
                                        connectionOut.println("               .done(function(data) {")
                                        connectionOut.println("                   $('#responseDiv').text(data);")
                                        connectionOut.println("               });")
                                        connectionOut.println("           event.preventDefault();")
                                        connectionOut.println("       });")
                                    }
                                }
                                connectionOut.println("   });")
                                connectionOut.println("</script>")
                                connectionOut.println("   </head>")
                                connectionOut.println("   <body>")
                                for ((k, v) in paths) {
                                    if (k.length> 1) {
                                        val formId = k.replace("/", "_")
                                        connectionOut.println("<form id=\"$formId\" >")
                                        for ((p, q) in v.params) {
                                            connectionOut.println("${q(p.first,p.second)}")
                                        }
                                        connectionOut.println("<input type=\"submit\" value=\"$k\" />")
                                        connectionOut.println("</form>")
                                    }
                                }
                                connectionOut.println("   <div id=\"responseDiv\"></div>")
                                connectionOut.println("   </body>")
                                connectionOut.println("</html>")
                            }
                            paths[""] = paths["/index.html"]!!
                            paths["/"] = paths["/index.html"]!!
                            val actionHelper = paths[path]
                            if (actionHelper == null) {
                                throw EnpointRecievedInvalidPath(path)
                            } else {
                                if (actionHelper.addPostParams && isPost) {
                                    extractParamsFromString(content.toString(), params)
                                }
                                actionHelper.action()
                            }
                        } catch (e: Throwable) {
                            e.printStackTrace()
                            if (connectionOut != null) {
                                connectionOut.println("HTTP/1.1 500 Internal Server Error")
                                connectionOut.println()
                            }
                        } finally {
                            connectionOut?.flush()
                            connectionOut?.close()
                            connectionIn?.close()
                            connection?.close()
                        }
                    }
                }.start()
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}
