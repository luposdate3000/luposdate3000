package lupos.s16network

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.PrintWriter
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.net.URLDecoder
import java.util.Date
import lupos.s00misc.EnpointRecievedInvalidPath
import lupos.s00misc.File
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.Parallel
import lupos.s03resultRepresentation.nodeGlobalDictionary

internal class MyPrintWriterExtension(out: OutputStream) : MyPrintWriter(out) {
    var counter = 0
    override fun print(s: String) {
        val len = s.length
        counter += len
        if (counter > 8192) {
            flush()
            counter = len
        }
        super.print(s)
    }
}

@UseExperimental(ExperimentalStdlibApi::class)
actual object HttpEndpointLauncher {
    internal fun printHeaderSuccess(stream: MyPrintWriter) {
        stream.println("HTTP/1.1 200 OK")
        stream.println("Content-Type: text/plain")
        stream.println();
    }

    actual suspend fun start(hostname: String, port: Int) {
println("call start on the launcher")
        try {
            val server = ServerSocket()
println("created serversocket")
            server.bind(InetSocketAddress(hostname, port))
println("bound server socket $hostname $port")
            while (true) {
                val connection = server.accept()
println("get connection")
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
                            while (line != null && line.length > 0) {
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
                                var allparams = path.substring(idx + 1)
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
                                        LuposdateEndpoint.evaluate_sparql_to_result_d(content.toString(), connectionOut, false)
                                    } else {
                                        LuposdateEndpoint.evaluate_sparql_to_result_d(params["query"]!!, connectionOut, false)
                                    }
                                    /*Coverage Unreachable*/
                                }
                                "/sparql/operator" -> {
                                    printHeaderSuccess(connectionOut)
                                    if (isPost) {
                                        connectionOut.print(LuposdateEndpoint.evaluate_operatorgraphXML_to_result_b(content.toString(), true))
                                    } else {
                                        connectionOut.print(LuposdateEndpoint.evaluate_operatorgraphXML_to_result_b(params["query"]!!, true))
                                    }
                                    /*Coverage Unreachable*/
                                }
                                "/import/turtle" -> {
                                    val dict = mutableMapOf<String, Int>()
                                    var dictfile = params["bnodeList"]
                                    if (dictfile != null) {
                                        File(dictfile).forEachLine {
                                            dict[it] = nodeGlobalDictionary.createNewBNode()
                                        }
                                    }
                                    printHeaderSuccess(connectionOut)
                                    if (isPost) {
                                        connectionOut.print(LuposdateEndpoint.import_turtle_files(content.toString(), dict))
                                    } else {
                                        connectionOut.print(LuposdateEndpoint.import_turtle_files(params["query"]!!, dict))
                                    }
                                    /*Coverage Unreachable*/
                                }
                                "/import/intermediate" -> {
                                    printHeaderSuccess(connectionOut)
                                    if (isPost) {
                                        connectionOut.print(LuposdateEndpoint.import_intermediate_files(content.toString()))
                                    } else {
                                        connectionOut.print(LuposdateEndpoint.import_intermediate_files(params["query"]!!))
                                    }
                                    /*Coverage Unreachable*/
                                }
                                "/import/xml" -> {
                                    printHeaderSuccess(connectionOut)
                                    if (isPost) {
                                        connectionOut.print(LuposdateEndpoint.import_xml_data(content.toString()))
                                    } else {
                                        connectionOut.print(LuposdateEndpoint.import_xml_data(params["query"]!!))
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
