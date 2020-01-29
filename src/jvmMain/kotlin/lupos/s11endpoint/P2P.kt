package lupos.s11endpoint
import lupos.s00misc.parseFromXml
import lupos.s00misc.Trace

import kotlin.concurrent.thread
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import lupos.s00misc.kotlinStacktrace
import lupos.s10outputResult.QueryResultToXML
import lupos.s09physicalOptimisation.PhysicalOptimizer

import lupos.s07physicalOperators.POPBaseNullableIterator
import lupos.s07physicalOperators.POPBase
import lupos.s05logicalOptimisation.LogicalOptimizer
import lupos.s03buildOperatorGraph.OperatorGraphVisitor

import lupos.s02buildSyntaxTree.turtle.TurtleScanner
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithDictionary
import lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s02buildSyntaxTree.rdf.ID_Triple
import lupos.s00misc.XMLElement
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.Variable
import lupos.s06resultRepresentation.ResultSet

import com.soywiz.korio.net.http.createHttpClient
import com.soywiz.korio.net.http.createHttpServer
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpServer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay


@UseExperimental(kotlin.ExperimentalStdlibApi::class)
object P2P {
    val REQUEST_TRACE_PRINT = arrayOf("/trace/print")
    val REQUEST_SPARQL_QUERY = arrayOf("/sparql/query")
    val REQUEST_TURTLE_INPUT = arrayOf("/turtle/input")
    val REQUEST_PEERS_LIST = arrayOf("/peers/list")
    val REQUEST_PEERS_JOIN = arrayOf("/peers/join", "hostname")
    var hostname = "localhost"
    var port = 8080
    val knownClients = mutableSetOf<String>()
    var server: HttpServer? = null
    val client = createHttpClient()
    var fullname = hostname + ":" + port

    suspend fun process_print_traces(): String {
        return Trace.toString()
    }

    suspend fun process_peers_list(): String {
        return XMLElement.XMLHeader + "\n" + XMLElement("servers").addContent(knownClients, "server").toPrettyString()
    }

    suspend fun process_peers_join(hostname: String?): String {
        if (hostname != null && hostname != "localhost")
            knownClients.add(hostname)
        return XMLElement.XMLHeader
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
                REQUEST_PEERS_LIST[0] -> response = process_peers_list()
                REQUEST_PEERS_JOIN[0] -> response = process_peers_join(params[REQUEST_PEERS_JOIN[1]]?.first())
                REQUEST_SPARQL_QUERY[0] -> {
                    if (request.method == Http.Method.POST)
                        response = Endpoint.process_sparql_query(data).toPrettyString()
                    else
                        response = Endpoint.process_sparql_query(params["query"]!!.first()).toPrettyString()
                }
                REQUEST_TURTLE_INPUT[0] -> response = Endpoint.process_turtle_input(data).toPrettyString()
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
        knownClients.add(fullname)
        if (bootstrap != null && bootstrap != "$hostname:$port") {
            var response = client.request(Http.Method.GET, "http://${bootstrap}${REQUEST_PEERS_LIST[0]}")
            var responseString = response.readAllString()
            XMLElement.parseFromXml(responseString)?.forEach() { root ->
                if (root.tag == "servers") {
                    root.childs.forEach() { server ->
                        if (server.tag == "server") {
                            knownClients.add(server.content)
                            client.request(Http.Method.GET, "http://${server.content}${REQUEST_PEERS_JOIN[0]}?${REQUEST_PEERS_JOIN[1]}=${fullname}")
                        }
                    }
                }
            }
        }
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
            0 -> P2P.port = a.toInt()
            1 -> P2P.hostname = a
            2 -> bootStrapServer = a
        }
        i++
    }
    thread(start = true) {
        launch(Dispatchers.Default) {
            P2P.start(bootStrapServer)
        }
    }
    while (true) {
        delay(1000)
    }
}
