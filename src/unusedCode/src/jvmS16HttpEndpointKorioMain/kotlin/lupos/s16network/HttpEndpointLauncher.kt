package lupos.s16network

import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpServer
import com.soywiz.korio.net.http.createHttpServer
import lupos.SparqlTestSuite
import lupos.s00misc.EnpointRecievedInvalidPath
import lupos.s00misc.File
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.Parallel
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s15tripleStoreDistributed.distributedTripleStore
import kotlin.jvm.JvmField

@UseExperimental(ExperimentalStdlibApi::class)
object HttpEndpointLauncher {
    @JvmField
    var server: HttpServer? = null
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
                    return LuposdateEndpoint.evaluate_sparql_to_result_c(data, true)
                } else {
                    return LuposdateEndpoint.evaluate_sparql_to_result_c(params["query"]!!, true)
                }
/*Coverage Unreachable*/
            }
            "/sparql/operator" -> {
                if (isPost) {
                    return LuposdateEndpoint.evaluate_operatorgraphXML_to_result_b(data, true)
                } else {
                    return LuposdateEndpoint.evaluate_operatorgraphXML_to_result_b(params["query"]!!, true)
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
                    return LuposdateEndpoint.import_turtle_files(data, dict)
                } else {
                    return LuposdateEndpoint.import_turtle_files(params["query"]!!, dict)
                }
/*Coverage Unreachable*/
            }
            "/import/intermediate" -> {
                if (isPost) {
                    return LuposdateEndpoint.import_intermediate_files(data)
                } else {
                    return LuposdateEndpoint.import_intermediate_files(params["query"]!!)
                }
/*Coverage Unreachable*/
            }
            "/import/xml" -> {
                if (isPost) {
                    return LuposdateEndpoint.import_xml_data(data)
                } else {
                    return LuposdateEndpoint.import_xml_data(params["query"]!!)
                }
/*Coverage Unreachable*/
            }
            "/persistence/store" -> {
                distributedTripleStore.localStore.safeToFolder()
                return "success\n"
            }
            "/persistence/load" -> {
                distributedTripleStore.localStore.loadFromFolder()
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

    suspend fun myRequestHandler(request: HttpServer.Request) {
        val params = request.getParams
        request.replaceHeader("Connection", "close")
        request.replaceHeader("Content-Type", "text/html")
        var responseBytes: ByteArray
        var data = StringBuilder()
        request.handler { it ->
            data.append(it.decodeToString())
        }
        request.endHandler {
            Parallel.runBlocking {
                try {
                    try {
                        val singleParams = mutableMapOf<String, String>()
                        params.forEach { k, v ->
                            singleParams[k] = v.first()
                        }
                        responseBytes = receive(request.path, request.method == Http.Method.POST, data.toString(), singleParams).encodeToByteArray()
                    } catch (e: Throwable) {
                        responseBytes = e.toString().encodeToByteArray()
                        request.setStatus(500)
                        SanityCheck.println({ "TODO exception 6" })
                        e.printStackTrace()
                    }
                    request.end(responseBytes)
                } catch (e: Throwable) {
// DO NOT send anything here, as that may be the root cause of the exception
                    e.printStackTrace()
                }
            }
        }
    }

    suspend fun start(hostname: String = "localhost", port: Int = 80) {
        server = createHttpServer().listen(port, hostname, ::myRequestHandler)
    }
}
