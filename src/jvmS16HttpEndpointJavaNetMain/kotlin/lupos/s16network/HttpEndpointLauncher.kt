package lupos.s16network

import java.net.InetSocketAddress
import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.Coverage
import lupos.s00misc.EnpointRecievedInvalidPath
import lupos.s00misc.File
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s15tripleStoreDistributed.DistributedTripleStore
import lupos.SparqlTestSuite
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import java.util.Date
import java.util.StringTokenizer
import java.net.URLDecoder

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
                        var connectionIn: BufferedReader? = null
                        var connectionOut: PrintWriter? = null
                        try {
                            connectionIn = BufferedReader(InputStreamReader(connection.getInputStream()))
                            connectionOut = PrintWriter(connection.getOutputStream())
                            var line = connectionIn.readLine()
                            println("header ::")
                            var path = ""
                            var isPost = false
                            val params = mutableMapOf<String, String>()
                            while (line != null && line.length > 0) {
                                println("received :: " + line)
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
                            println("content $isPost $path $params")
                            val content = StringBuilder()
                            while (connectionIn.ready()) {
                                content.append(connectionIn.read().toChar())
                            }
                            println(content.toString())
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
                                        HttpEndpoint.evaluate_sparql_query_string(content.toString(), connectionOut, true)
                                    } else {
                                        HttpEndpoint.evaluate_sparql_query_string(params["query"]!!, connectionOut, true)
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
                    }
                }.start()
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}
