package lupos.s16network

import com.soywiz.korio.net.http.createHttpServer
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpServer
import kotlin.jvm.JvmField
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
import lupos.s00misc.ELoggerType
import lupos.s00misc.File
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s15tripleStoreDistributed.DistributedTripleStore

@UseExperimental(ExperimentalStdlibApi::class)
object HttpEndpointLauncher {
    @JvmField
    var server: HttpServer? = null

    suspend fun receive(path: String, isPost: Boolean, data: String, params: Map<String, String>): String {
        when (path) {
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
            "/import/xml" -> {
                if (isPost) {
                    return HttpEndpoint.import_xml_data(data)
                } else {
                    return HttpEndpoint.import_xml_data(params["query"]!!)
                }
/*Coverage Unreachable*/
            }
            "/persistence/store" -> {
                DistributedTripleStore.localStore.safeToFolder(data)
            }
            "/persistence/load" -> {
                DistributedTripleStore.localStore.loadFromFolder(data)
            }
        }
        throw Exception("$path unknown http-request")
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
            CoroutinesHelper.runBlock {
                try {
                    val singleParams = mutableMapOf<String, String>()
                    params.forEach { k, v ->
                        singleParams[k] = v.first()
                    }
                    responseBytes = receive(request.path, request.method == Http.Method.POST, data.toString(), singleParams).encodeToByteArray()
                } catch (e: Throwable) {
                    responseBytes = e.toString().encodeToByteArray()
                    request.setStatus(500)
                }
                request.end(responseBytes)
            }
        }
    }

    suspend fun start(hostname: String = "localhost", port: Int = 80) {
        server = createHttpServer().listen(port, hostname, ::myRequestHandler)
    }
}
