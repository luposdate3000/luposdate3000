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
    suspend fun receive(path: String, isPost: Boolean, data: String, params: Map<String, String>): String {
        when (path) {
            "/sparql/jenaquery" -> {
                if (isPost) {
                    return JenaWrapper.execQuery(data)
                } else {
                    return JenaWrapper.execQuery(params["query"]!!)
                }
/*Coverage Unreachable*/
            }
            "/sparql/jenaload" -> {
                if (isPost) {
                    JenaWrapper.loadFromFile(data)
                } else {
                    JenaWrapper.loadFromFile(params["query"]!!)
                }
                return "success\n"
            }
            "/sparql/query" -> {
                if (isPost) {
                    return HttpEndpoint.evaluate_sparql_query_string(data, true)
                } else {
                    return HttpEndpoint.evaluate_sparql_query_string(params["query"]!!, true)
                }
/*Coverage Unreachable*/
            }
            "/sparql/operator" -> {
                if (isPost) {
                    return HttpEndpoint.evaluate_sparql_query_operator_xml(data, true)
                } else {
                    return HttpEndpoint.evaluate_sparql_query_operator_xml(params["query"]!!, true)
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
                if (isPost) {
                    return HttpEndpoint.import_turtle_files(data, dict)
                } else {
                    return HttpEndpoint.import_turtle_files(params["query"]!!, dict)
                }
/*Coverage Unreachable*/
            }
            "/import/intermediate" -> {
                if (isPost) {
                    return HttpEndpoint.import_intermediate_files(data)
                } else {
                    return HttpEndpoint.import_intermediate_files(params["query"]!!)
                }
/*Coverage Unreachable*/
            }
            "/import/xml" -> {
                if (isPost) {
                    return HttpEndpoint.import_xml_data(data)
                } else {
                    return HttpEndpoint.import_xml_data(params["query"]!!)
                }
/*Coverage Unreachable*/
            }
            "/persistence/store" -> {
                DistributedTripleStore.localStore.safeToFolder()
                return "success\n"
            }
            "/persistence/load" -> {
                DistributedTripleStore.localStore.loadFromFolder()
                return "success\n"
            }
            "/debug/unittest" -> {
                SparqlTestSuite().testMain()
                return "finished\n"
            }
            "/debug/knownHosts" -> {
                return ServerCommunicationDistribution.printKnownHosts().toString()
            }
        }
        throw EnpointRecievedInvalidPath(path)
    }

    fun myWrite(stream: BufferedOutputStream, str: String) {
        val tmp = str.toByteArray(Charsets.UTF_8)
        stream.write(tmp, 0, tmp.size)
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
                        var response = receive(path, isPost, content.toString(), params)
                        println("writte data to output")
                        connectionOut.println( "HTTP/1.1 200 OK")
                        connectionOut.println( "Content-Type: text/plain")
                        connectionOut.println( );
                        connectionOut.println( response)
                        println("written data to output '$response'")
                    } catch (e: Throwable) {
                        e.printStackTrace()
                        if (connectionOut != null) {
                            connectionOut.println( "HTTP/1.1 500 Internal Server Error")
			connectionOut.println( )
                        }
                    } finally {
                        println("flushing data to output ${connectionOut != null}")
                        connectionOut?.flush()
                        connectionOut?.close()
                        println("closed output")
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
