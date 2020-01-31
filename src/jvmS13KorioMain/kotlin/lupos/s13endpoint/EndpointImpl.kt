package lupos.s13endpoint

import com.soywiz.korio.net.http.createHttpClient
import com.soywiz.korio.net.http.createHttpServer
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpServer
import kotlin.concurrent.thread
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import lupos.s00misc.kotlinStacktrace
import lupos.s00misc.parseFromXml
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s02buildSyntaxTree.rdf.ID_Triple
import lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithDictionary
import lupos.s02buildSyntaxTree.turtle.TurtleScanner
import lupos.s03buildOperatorGraph.OperatorGraphVisitor
import lupos.s05logicalOptimisation.LogicalOptimizer
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Variable
import lupos.s07physicalOperators.POPBase
import lupos.s07physicalOperators.POPBaseNullableIterator
import lupos.s09physicalOptimisation.PhysicalOptimizer
import lupos.s10outputResult.QueryResultToXML
import lupos.s11p2p.*
import lupos.s13endpoint.Endpoint


@UseExperimental(kotlin.ExperimentalStdlibApi::class)
object EndpointImpl {
    var hostname = "localhost"
    var port = 8080
    var fullname = hostname + ":" + port
    val REQUEST_TRACE_PRINT = arrayOf("/trace/print")
    val REQUEST_SPARQL_QUERY = arrayOf("/sparql/query", "query")
    val REQUEST_TRUNCATE = arrayOf("/import/truncate")
    val REQUEST_TURTLE_INPUT = arrayOf("/import/turtle", "data")
    val REQUEST_XML_INPUT = arrayOf("/import/xml", "data")
    val REQUEST_PEERS_LIST = arrayOf("/peers/list")
    val REQUEST_PEERS_JOIN = arrayOf("/peers/join", "hostname")
    val REQUEST_OPERATOR_QUERY = arrayOf("operator/query", "query")
    var server: HttpServer? = null
    val client = createHttpClient()

    suspend fun process_print_traces(): String {
        return Trace.toString()
    }

    suspend fun myRequestHandler(request: HttpServer.Request) {
        println("listen::Request")
        val params = request.getParams
        println(params)
        println(request.path)
        println(request.method)
        request.replaceHeader("Connection", "close")
        request.replaceHeader("Content-Type", "text/html")
        var response = ""
        var data = ""
        var endFlag = true
        request.handler { it ->
            data += it.decodeToString()
            println(data)
        }
        request.endHandler {
            endFlag = false
        }
        while (endFlag)
            delay(1)
        try {
            when (request.path) {
                REQUEST_TRACE_PRINT[0] -> response = process_print_traces()
                REQUEST_PEERS_LIST[0] -> response = P2P.process_peers_list()
                REQUEST_PEERS_JOIN[0] -> response = P2P.process_peers_join(params[REQUEST_PEERS_JOIN[1]]?.first())
                REQUEST_TRUNCATE[0] -> response = Endpoint.process_truncate().toPrettyString()
                REQUEST_OPERATOR_QUERY[0] -> {
                    if (request.method == Http.Method.POST)
                        response = Endpoint.process_operatorgraph_query(data).toPrettyString()
                    else
                        response = Endpoint.process_operatorgraph_query(params[REQUEST_OPERATOR_QUERY[1]]!!.first()).toPrettyString()
                }
                REQUEST_SPARQL_QUERY[0] -> {
                    if (request.method == Http.Method.POST)
                        response = Endpoint.process_sparql_query(data).toPrettyString()
                    else
                        response = Endpoint.process_sparql_query(params[REQUEST_SPARQL_QUERY[1]]!!.first()).toPrettyString()
                }
                REQUEST_XML_INPUT[0] -> {
                    if (request.method == Http.Method.POST)
                        response = Endpoint.process_xml_input(data).toPrettyString()
                    else
                        response = Endpoint.process_xml_input(params[REQUEST_XML_INPUT[1]]!!.first()).toPrettyString()
                }
                REQUEST_TURTLE_INPUT[0] -> {
                    if (request.method == Http.Method.POST)
                        response = Endpoint.process_turtle_input(data).toPrettyString()
                    else
                        response = Endpoint.process_turtle_input(params[REQUEST_TURTLE_INPUT[1]]!!.first()).toPrettyString()
                }
                else -> throw Exception("unknown request path: \"" + request.path + "\"")
            }
        } catch (e: Throwable) {
            e.kotlinStacktrace()
            response = e.toString()
            request.setStatus(404)
        }
        request.end(response)
        println("response::" + response)
    }

    suspend fun start(bootstrap: String?) {
        fullname = hostname + ":" + port
        P2P.start(bootstrap)
        server = createHttpServer().listen(port, hostname, ::myRequestHandler)
    }
}

fun main(args: Array<String>) = runBlocking {
    var i = 0
    var serverPort = 0
    var serverName = ""
    var bootStrapServer: String? = null
    for (a in args) {
        println("args[$i]=$a")
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
