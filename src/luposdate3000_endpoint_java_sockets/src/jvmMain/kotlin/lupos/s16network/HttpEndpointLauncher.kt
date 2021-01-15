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
import lupos.s03resultRepresentation.nodeGlobalDictionary
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
    public actual /*suspend*/ fun start(hostname: String, port: Int) {
        try {
            val server = ServerSocket()
            server.bind(InetSocketAddress(hostname, port))
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
                                val allparams = path.substring(idx + 1)
                                for (p in allparams.split('&')) {
                                    val t = p.split('=')
                                    params[t[0]] = URLDecoder.decode(t[1])
                                }
                                path = path.substring(0, idx)
                            }
                            val content = StringBuilder()
                            while (connectionIn.ready()) {
                                content.append(connectionIn.read().toChar())
                            }
                            when (path) {
                                "/sparql/jenaquery" -> {
                                    printHeaderSuccess(connectionOut)
                                    if (isPost) {
                                        connectionOut.print(JenaWrapper.execQuery(content.toString()))
                                    } else {
                                        connectionOut.print(JenaWrapper.execQuery(params["query"]!!))
                                    }
                                    /*Coverage Unreachable*/
                                }
                                "/sparql/jenaload" -> {
                                    if (isPost) {
                                        JenaWrapper.loadFromFile(content.toString())
                                    } else {
                                        JenaWrapper.loadFromFile(params["query"]!!)
                                    }
                                    printHeaderSuccess(connectionOut)
                                    connectionOut.print("success")
                                }
                                "/sparql/query" -> {
                                    if (isPost) {
                                        LuposdateEndpoint.evaluateSparqlToResultD(content.toString(), connectionOut, false)
                                    } else {
                                        LuposdateEndpoint.evaluateSparqlToResultD(params["query"]!!, connectionOut, false)
                                    }
                                    /*Coverage Unreachable*/
                                }
                                "/sparql/operator" -> {
                                    printHeaderSuccess(connectionOut)
                                    if (isPost) {
                                        connectionOut.print(LuposdateEndpoint.evaluateOperatorgraphxmlToResultB(content.toString(), true))
                                    } else {
                                        connectionOut.print(LuposdateEndpoint.evaluateOperatorgraphxmlToResultB(params["query"]!!, true))
                                    }
                                    /*Coverage Unreachable*/
                                }
                                "/import/turtle" -> {
                                    val dict = mutableMapOf<String, Int>()
                                    val dictfile = params["bnodeList"]
                                    if (dictfile != null) {
                                        File(dictfile).forEachLine {
                                            dict[it] = nodeGlobalDictionary.createNewBNode()
                                        }
                                    }
                                    printHeaderSuccess(connectionOut)
                                    if (isPost) {
                                        connectionOut.print(LuposdateEndpoint.importTurtleFiles(content.toString(), dict))
                                    } else {
                                        connectionOut.print(LuposdateEndpoint.importTurtleFiles(params["query"]!!, dict))
                                    }
                                    /*Coverage Unreachable*/
                                }
                                "/import/intermediate" -> {
                                    printHeaderSuccess(connectionOut)
                                    if (isPost) {
                                        connectionOut.print(LuposdateEndpoint.importIntermediateFiles(content.toString()))
                                    } else {
                                        connectionOut.print(LuposdateEndpoint.importIntermediateFiles(params["query"]!!))
                                    }
                                    /*Coverage Unreachable*/
                                }
                                "/import/xml" -> {
                                    printHeaderSuccess(connectionOut)
                                    if (isPost) {
                                        connectionOut.print(LuposdateEndpoint.importXmlData(content.toString()))
                                    } else {
                                        connectionOut.print(LuposdateEndpoint.importXmlData(params["query"]!!))
                                    }
                                    /*Coverage Unreachable*/
                                }
                                else -> {
                                    throw EnpointRecievedInvalidPath(path)
                                }
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
