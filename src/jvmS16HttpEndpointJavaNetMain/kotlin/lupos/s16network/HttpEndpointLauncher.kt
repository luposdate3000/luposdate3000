package lupos.s16network

import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.io.IOException
import java.io.OutputStream
import java.io.PrintWriter
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.net.URLDecoder
import java.util.Date
import java.util.StringTokenizer
import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.Coverage
import lupos.s00misc.EnpointRecievedInvalidPath
import lupos.s00misc.File
import lupos.s00misc.DateHelper
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s15tripleStoreDistributed.DistributedTripleStore
import lupos.SparqlTestSuite

class MyPrintWriterExtension(out: OutputStream) : PrintWriter(out, false) {
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
object HttpEndpointLauncher {
    fun printHeaderSuccess(stream: PrintWriter) {
        stream.println("HTTP/1.1 200 OK")
        stream.println("Content-Type: text/plain")
        stream.println();
    }

    suspend fun start(hostname: String = "localhost", port: Int = 80) {
        try {
            val server = ServerSocket()
            server.bind(InetSocketAddress(hostname, port))
            while (true) {
                val connection = server.accept()
                Thread {
                    runBlocking {
                        var timertotal = DateHelper.markNow()
                        var timer = timertotal
                        var connectionIn: BufferedReader? = null
                        var connectionOut: PrintWriter? = null
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
                            println("timer #409 ${DateHelper.elapsedSeconds(timer)}")
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
                                        HttpEndpoint.evaluate_sparql_query_string(content.toString(), connectionOut, false)
                                    } else {
                                        HttpEndpoint.evaluate_sparql_query_string(params["query"]!!, connectionOut, false)
                                    }
                                    /*Coverage Unreachable*/
                                }
                                "/sparql/operator" -> {
                                    printHeaderSuccess(connectionOut)
                                    if (isPost) {
                                        connectionOut.print(HttpEndpoint.evaluate_sparql_query_operator_xml(content.toString(), true))
                                    } else {
                                        connectionOut.print(HttpEndpoint.evaluate_sparql_query_operator_xml(params["query"]!!, true))
                                    }
                                    /*Coverage Unreachable*/
                                }
                                "/import/turtle" -> {
                                    val dict = MyMapStringIntPatriciaTrie()
                                    var dictfile = params["bnodeList"]
                                    if (dictfile != null) {
                                        File(dictfile).forEachLine {
                                            dict[it] = nodeGlobalDictionary.createNewBNode()
                                        }
                                    }
                                    printHeaderSuccess(connectionOut)
                                    if (isPost) {
                                        connectionOut.print(HttpEndpoint.import_turtle_files(content.toString(), dict))
                                    } else {
                                        connectionOut.print(HttpEndpoint.import_turtle_files(params["query"]!!, dict))
                                    }
                                    /*Coverage Unreachable*/
                                }
                                "/import/intermediate" -> {
                                    printHeaderSuccess(connectionOut)
                                    if (isPost) {
                                        connectionOut.print(HttpEndpoint.import_intermediate_files(content.toString()))
                                    } else {
                                        connectionOut.print(HttpEndpoint.import_intermediate_files(params["query"]!!))
                                    }
                                    /*Coverage Unreachable*/
                                }
                                "/import/xml" -> {
                                    printHeaderSuccess(connectionOut)
                                    if (isPost) {
                                        connectionOut.print(HttpEndpoint.import_xml_data(content.toString()))
                                    } else {
                                        connectionOut.print(HttpEndpoint.import_xml_data(params["query"]!!))
                                    }
                                    /*Coverage Unreachable*/
                                }
                                "/persistence/store" -> {
                                    DistributedTripleStore.localStore.safeToFolder()
                                    printHeaderSuccess(connectionOut)
                                    connectionOut.print("success")
                                }
                                "/persistence/load" -> {
                                    DistributedTripleStore.localStore.loadFromFolder()
                                    printHeaderSuccess(connectionOut)
                                    connectionOut.print("success")
                                }
                                "/debug/unittest" -> {
                                    SparqlTestSuite().testMain()
                                    printHeaderSuccess(connectionOut)
                                    connectionOut.print("success")
                                }
                                "/debug/knownHosts" -> {
                                    printHeaderSuccess(connectionOut)
                                    connectionOut.print(ServerCommunicationDistribution.printKnownHosts().toString())
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
                        println("timer #400 ${DateHelper.elapsedSeconds(timertotal)}")
                    }
                }.start()
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}
