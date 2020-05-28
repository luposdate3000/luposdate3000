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
@UseExperimental(ExperimentalStdlibApi::class)
object HttpEndpointLauncher {
    @JvmField
    var server: HttpServer? = null
    suspend fun receive(path: String, isPost: Boolean, data: String, params: Map<String, String>): String {
Coverage.funStart(16487)
        when (path) {
            "/sparql/query" -> {
Coverage.whenCaseStart(16489)
                if (isPost) {
Coverage.ifStart(16490)
                    return HttpEndpoint.evaluate_sparql_query_string(data, true)
                } else {
Coverage.statementStart(16491)
                    return HttpEndpoint.evaluate_sparql_query_string(params["query"]!!, true)
                }
Coverage.statementStart(16492)
            }
            "/sparql/operator" -> {
Coverage.whenCaseStart(16493)
                if (isPost) {
Coverage.ifStart(16494)
                    return HttpEndpoint.evaluate_sparql_query_operator_xml(data, true)
                } else {
Coverage.statementStart(16495)
                    return HttpEndpoint.evaluate_sparql_query_operator_xml(params["query"]!!, true)
                }
Coverage.statementStart(16496)
            }
            "/import/turtle" -> {
Coverage.whenCaseStart(16497)
                val dict = MyMapStringIntPatriciaTrie()
Coverage.statementStart(16498)
                var dictfile = params["bnodeList"]
Coverage.statementStart(16499)
                if (dictfile != null) {
Coverage.ifStart(16500)
                    File(dictfile).forEachLine {
Coverage.statementStart(16501)
                        dict[it] = nodeGlobalDictionary.createNewBNode()
Coverage.statementStart(16502)
                    }
Coverage.statementStart(16503)
                }
Coverage.statementStart(16504)
                if (isPost) {
Coverage.ifStart(16505)
                    return HttpEndpoint.import_turtle_files(data, dict)
                } else {
Coverage.statementStart(16506)
                    return HttpEndpoint.import_turtle_files(params["query"]!!, dict)
                }
Coverage.statementStart(16507)
            }
            "/import/xml" -> {
Coverage.whenCaseStart(16508)
                if (isPost) {
Coverage.ifStart(16509)
                    return HttpEndpoint.import_xml_data(data)
                } else {
Coverage.statementStart(16510)
                    return HttpEndpoint.import_xml_data(params["query"]!!)
                }
Coverage.statementStart(16511)
            }
            "/persistence/store" -> {
Coverage.whenCaseStart(16512)
                return HttpEndpoint.persistence_store(data)
            }
            "/persistence/load" -> {
Coverage.whenCaseStart(16513)
                return HttpEndpoint.persistence_load(data)
            }
        }
Coverage.statementStart(16514)
        throw Exception("$path unknown http-request")
    }
    suspend fun myRequestHandler(request: HttpServer.Request) {
Coverage.funStart(16515)
        val params = request.getParams
Coverage.statementStart(16516)
        request.replaceHeader("Connection", "close")
Coverage.statementStart(16517)
        request.replaceHeader("Content-Type", "text/html")
Coverage.statementStart(16518)
        var responseBytes: ByteArray
Coverage.statementStart(16519)
        var data = StringBuilder()
Coverage.statementStart(16520)
        request.handler { it ->
Coverage.statementStart(16521)
            data.append(it.decodeToString())
Coverage.statementStart(16522)
        }
Coverage.statementStart(16523)
        request.endHandler {
Coverage.statementStart(16524)
            CoroutinesHelper.runBlock {
Coverage.statementStart(16525)
                try {
Coverage.statementStart(16526)
                    val singleParams = mutableMapOf<String, String>()
Coverage.statementStart(16527)
                    params.forEach { k, v ->
Coverage.statementStart(16528)
                        singleParams[k] = v.first()
Coverage.statementStart(16529)
                    }
Coverage.statementStart(16530)
                    responseBytes = receive(request.path, request.method == Http.Method.POST, data.toString(), singleParams).encodeToByteArray()
Coverage.statementStart(16531)
                } catch (e: Throwable) {
Coverage.statementStart(16532)
                    responseBytes = e.toString().encodeToByteArray()
Coverage.statementStart(16533)
                    request.setStatus(500)
Coverage.statementStart(16534)
                }
Coverage.statementStart(16535)
                request.end(responseBytes)
Coverage.statementStart(16536)
            }
Coverage.statementStart(16537)
        }
Coverage.statementStart(16538)
    }
    suspend fun start(hostname: String = "localhost", port: Int = 80) {
Coverage.funStart(16539)
        server = createHttpServer().listen(port, hostname, ::myRequestHandler)
Coverage.statementStart(16540)
    }
}
