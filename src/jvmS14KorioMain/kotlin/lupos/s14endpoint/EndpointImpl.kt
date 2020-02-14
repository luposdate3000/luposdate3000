package lupos.s14endpoint

import com.soywiz.korio.net.http.createHttpServer
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpServer
import kotlin.concurrent.thread
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s04logicalOperators.noinput.*
import lupos.s12p2p.*
import lupos.s14endpoint.Endpoint


@UseExperimental(kotlin.ExperimentalStdlibApi::class)
object EndpointImpl {
    var hostname = "localhost"
    var port = 80
    var fullname = hostname + ":" + port
    val REQUEST_BINARY = arrayOf("/binary")
    val REQUEST_TRIPLE_ADD = arrayOf("/triple/add", "graph", "id", "s", "p", "o", "idx")
    val REQUEST_TRIPLE_GET = arrayOf("/triple/get", "graph", "id", "s", "p", "o", "sv", "pv", "ov", "idx")
    val REQUEST_TRIPLE_DELETE = arrayOf("/triple/delete", "graph", "id", "s", "p", "o", "sv", "pv", "ov", "idx")
    val REQUEST_COMMIT = arrayOf("/commit", "id")
    val REQUEST_TRACE_PRINT = arrayOf("/trace/print")
    val REQUEST_SPARQL_QUERY = arrayOf("/sparql/query", "query")
    val REQUEST_GRAPH_CLEAR_ALL = arrayOf("/graph/clear")
    val REQUEST_GRAPH_OPERATION = arrayOf("/graph/operation", "name", "type")
    val REQUEST_TURTLE_INPUT = arrayOf("/import/turtle", "data")
    val REQUEST_XML_INPUT = arrayOf("/import/xml", "data")
    val REQUEST_PEERS_LIST = arrayOf("/peers/list")
    val REQUEST_PEERS_JOIN = arrayOf("/peers/join", "hostname")
    val REQUEST_PEERS_JOIN_INTERNAL = arrayOf("/peers/join_internal", "hostname")
    val REQUEST_PEERS_SELF_TEST = arrayOf("/peers/self_test")
    val REQUEST_OPERATOR_QUERY = arrayOf("operator/query", "query")
    var server: HttpServer? = null

    suspend fun process_print_traces(): String {
        return Trace.toString()
    }

    suspend fun myRequestHandler(request: HttpServer.Request) {
        println("request start")
        GlobalLogger.log(ELoggerType.DEBUG, { "listen::Request" })
        val params = request.getParams
        GlobalLogger.log(ELoggerType.DEBUG, { params })
        GlobalLogger.log(ELoggerType.DEBUG, { request.path })
        GlobalLogger.log(ELoggerType.DEBUG, { request.method })
        request.replaceHeader("Connection", "close")
        var endFlag = true
        if (request.path == REQUEST_BINARY[0]) {
            var data: ByteArray? = null
            request.handler { it ->
                if (data == null)
                    data = it
                else
                    data = data!! + it
            }
            request.endHandler {
                endFlag = false
            }
            while (endFlag)
                delay(10)
            val res = TransferHelperNetwork.processBinary(data!!)
            request.replaceHeader("Content-Type", "application/x-binary")
            request.end(res)
            return
        }
        request.replaceHeader("Content-Type", "text/html")
        var responseStr = ""
        var responseBytes: ByteArray? = null
        var data = ""


        request.handler { it ->
            data += it.decodeToString()
            GlobalLogger.log(ELoggerType.DEBUG, { data })
        }
        request.endHandler {
            endFlag = false
        }
        while (endFlag)
            delay(10)
        try {
            when (request.path) {

                REQUEST_TRIPLE_ADD[0] -> responseStr = Endpoint.process_local_triple_add(params[REQUEST_TRIPLE_ADD[1]]!!.first(), params[REQUEST_TRIPLE_ADD[2]]!!.first().toLong(), params[REQUEST_TRIPLE_ADD[3]]!!.first(), params[REQUEST_TRIPLE_ADD[4]]!!.first(), params[REQUEST_TRIPLE_ADD[5]]!!.first(), EIndexPattern.valueOf(params[REQUEST_TRIPLE_ADD[6]]!!.first())).toPrettyString()
                REQUEST_TRIPLE_GET[0] -> responseBytes = ResultRepresenationNetwork.toNetworkPackage(Endpoint.process_local_triple_get(params[REQUEST_TRIPLE_GET[1]]!!.first(), ResultSetDictionary(), params[REQUEST_TRIPLE_GET[2]]!!.first().toLong(), params[REQUEST_TRIPLE_GET[3]]!!.first(), params[REQUEST_TRIPLE_GET[4]]!!.first(), params[REQUEST_TRIPLE_GET[5]]!!.first(), params[REQUEST_TRIPLE_GET[6]]!!.first().toBoolean(), params[REQUEST_TRIPLE_GET[7]]!!.first().toBoolean(), params[REQUEST_TRIPLE_GET[8]]!!.first().toBoolean(), EIndexPattern.valueOf(params[REQUEST_TRIPLE_GET[9]]!!.first())))
                REQUEST_TRIPLE_DELETE[0] -> responseStr = Endpoint.process_local_triple_delete(params[REQUEST_TRIPLE_DELETE[1]]!!.first(), params[REQUEST_TRIPLE_DELETE[2]]!!.first().toLong(), params[REQUEST_TRIPLE_DELETE[3]]!!.first(), params[REQUEST_TRIPLE_DELETE[4]]!!.first(), params[REQUEST_TRIPLE_DELETE[5]]!!.first(), params[REQUEST_TRIPLE_DELETE[6]]!!.first().toBoolean(), params[REQUEST_TRIPLE_DELETE[7]]!!.first().toBoolean(), params[REQUEST_TRIPLE_DELETE[8]]!!.first().toBoolean(), EIndexPattern.valueOf(params[REQUEST_TRIPLE_DELETE[9]]!!.first())).toPrettyString()
                REQUEST_TRACE_PRINT[0] -> responseStr = process_print_traces()
                REQUEST_PEERS_LIST[0] -> responseStr = P2P.process_peers_list()
                REQUEST_PEERS_SELF_TEST[0] -> responseStr = P2P.process_peers_self_test()
                REQUEST_COMMIT[0] -> responseStr = Endpoint.process_local_commit(params[REQUEST_COMMIT[1]]!!.first().toLong()).toPrettyString()
                REQUEST_PEERS_JOIN[0] -> responseStr = P2P.process_peers_join(params[REQUEST_PEERS_JOIN[1]]?.first())
                REQUEST_PEERS_JOIN_INTERNAL[0] -> responseStr = P2P.process_peers_join_internal(params[REQUEST_PEERS_JOIN_INTERNAL[1]]?.first())
                REQUEST_GRAPH_CLEAR_ALL[0] -> responseStr = Endpoint.process_local_graph_clear_all().toPrettyString()
                REQUEST_GRAPH_OPERATION[0] -> responseStr = Endpoint.process_local_graph_operation(params[REQUEST_GRAPH_OPERATION[1]]!!.first(), EGraphOperationType.valueOf(params[REQUEST_GRAPH_OPERATION[2]]!!.first())).toPrettyString()
                REQUEST_OPERATOR_QUERY[0] -> {
                    if (request.method == Http.Method.POST)
                        responseStr = Endpoint.process_operatorgraph_query(data).toPrettyString()
                    else
                        responseStr = Endpoint.process_operatorgraph_query(params[REQUEST_OPERATOR_QUERY[1]]!!.first()).toPrettyString()
                }
                REQUEST_SPARQL_QUERY[0] -> {
                    if (request.method == Http.Method.POST)
                        responseStr = Endpoint.process_sparql_query(data).toPrettyString()
                    else
                        responseStr = Endpoint.process_sparql_query(params[REQUEST_SPARQL_QUERY[1]]!!.first()).toPrettyString()
                }
                REQUEST_XML_INPUT[0] -> {
                    if (request.method == Http.Method.POST)
                        responseStr = Endpoint.process_xml_input(data).toPrettyString()
                    else
                        responseStr = Endpoint.process_xml_input(params[REQUEST_XML_INPUT[1]]!!.first()).toPrettyString()
                }
                REQUEST_TURTLE_INPUT[0] -> {
                    if (request.method == Http.Method.POST)
                        responseStr = Endpoint.process_turtle_input(data).toPrettyString()
                    else
                        responseStr = Endpoint.process_turtle_input(params[REQUEST_TURTLE_INPUT[1]]!!.first()).toPrettyString()
                }
                else -> throw Exception("unknown request path: \"" + request.path + "\"")
            }
        } catch (e: Throwable) {
            GlobalLogger.stacktrace(ELoggerType.DEBUG, e)
            responseStr = e.toString()
            e.printStackTrace()
            request.setStatus(404)
        }
        if (responseBytes != null)
            request.end(responseBytes)
        else
            request.end(responseStr)
        GlobalLogger.log(ELoggerType.DEBUG, { "responseStr::" + responseStr })
        println("request end")
    }

    suspend fun start(bootstrap: String?) {
        fullname = hostname + ":" + port
        GlobalLogger.log(ELoggerType.DEBUG, { "before P2P.start" })
        P2P.start(bootstrap)
        GlobalLogger.log(ELoggerType.DEBUG, { "listen:: $hostname $port" })
        server = createHttpServer().listen(port, hostname, ::myRequestHandler)
    }
}

fun main(args: Array<String>) = runBlocking {
    var i = 0
    var bootStrapServer: String? = null
    for (a in args) {
        GlobalLogger.log(ELoggerType.DEBUG, { "args[$i]=$a" })
        when (i) {
            0 -> EndpointImpl.hostname = a
            1 -> bootStrapServer = a
        }
        i++
    }
    thread(start = true) {
        launch(Dispatchers.Default) {
            EndpointImpl.start(bootStrapServer)
        }
    }
    while (true) {
        delay(1000)
    }
}
/*
fun main(args: Array<String>) = runBlocking {
    var i = 0
    var bootStrapServer: String? = null
    for (a in args) {
        GlobalLogger.log(ELoggerType.DEBUG,{"args[$i]=$a"})
        when (i) {
            0 -> EndpointImpl.port = a.toInt()
            1 -> EndpointImpl.hostname = a
            2 -> bootStrapServer = a
        }
        i++
    }
    thread(start = true) {
        launch(Dispatchers.Default) {
            EndpointImpl.start(bootStrapServer)
        }
    }
    while (true) {
        delay(1000)
    }
}
*/
