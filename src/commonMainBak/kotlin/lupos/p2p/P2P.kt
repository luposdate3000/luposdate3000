package lupos.p2p

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

import lupos.misc.kotlinStacktrace
import lupos.s8outputResult.QueryResultToXML

import com.soywiz.korio.net.http.createHttpClient
import com.soywiz.korio.net.http.createHttpServer
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpServer
import lupos.s2buildOperatorGraph.OperatorGraphVisitor
import lupos.s3logicalOptimisation.LogicalOptimizer
import lupos.s5physicalOperators.POPBase
import lupos.s5physicalOperators.POPBaseNullableIterator
import lupos.s6tripleStore.TripleStore
import lupos.s7physicalOptimisation.PhysicalOptimizer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class TripleInsertIterator : POPBaseNullableIterator {
    var result: ResultRow?

    private val resultSet = ResultSet()
    override fun toXMLElement(): XMLElement {
        return XMLElement("TripleInsertIterator")
    }

    fun cleanString(s: String): String {
        var res = s
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
            if (match == null)
                return res
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
    }

    constructor(triple: ID_Triple) {
        result = resultSet.createResultRow()
        result!![resultSet.createVariable("s")] = resultSet.createValue(cleanString(Dictionary[triple.s]!!.toN3String()))
        result!![resultSet.createVariable("p")] = resultSet.createValue(cleanString(Dictionary[triple.p]!!.toN3String()))
        result!![resultSet.createVariable("o")] = resultSet.createValue(cleanString(Dictionary[triple.o]!!.toN3String()))
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }

    override fun nnext(): ResultRow? {
        var res = result
        result = null
        return res
    }

}

fun consume_triple(triple_s: Long, triple_p: Long, triple_o: Long) {
    val triple = ID_Triple(triple_s, triple_p, triple_o)
    PhysicalOptimizer._store.addData(TripleInsertIterator(triple))
}

@UseExperimental(kotlin.ExperimentalStdlibApi::class)
object P2P {
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

    suspend fun process_peers_list(): String {
        return XMLElement.XMLHeader + "\n" + XMLElement("servers").addContent(knownClients, "server").toPrettyString()
    }

    suspend fun process_peers_join(hostname: String?): String {
        if (hostname != null && hostname != "localhost")
            knownClients.add(hostname)
        return XMLElement.XMLHeader
    }

    suspend fun process_turtle_input(data: String): String {
        val lcit = LexerCharIterator(data)
        val tit = TurtleScanner(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        TurtleParserWithDictionary(::consume_triple, ltit).turtleDoc()
        return XMLElement("done").toPrettyString()
    }

    suspend fun process_sparql_query(query: String?): String {
        var res = ""
        if (query != null) {
            res = XMLElement.XMLHeader + "\n"
            try {
                println("----------String Query")
                println(query)
                println("----------Abstract Syntax Tree")
                val lcit = LexerCharIterator(query)
                val tit = TokenIteratorSPARQLParser(lcit)
                val ltit = LookAheadTokenIterator(tit, 3)
                val parser = SPARQLParser(ltit)
                val ast_node = parser.expr()
                println(ast_node)
                println("----------Logical Operator Graph")
                val lop_node = ast_node.visit(OperatorGraphVisitor())
                println(lop_node)
                println("----------Logical Operator Graph optimized")
                val lop_node2 = LogicalOptimizer().optimize(lop_node)
                println(lop_node2)
                println("----------Physical Operator Graph")
                val pop_optimizer = PhysicalOptimizer()
                val pop_node = pop_optimizer.optimize(lop_node2) as POPBase
                println(pop_node)
                res += QueryResultToXML.toXML(pop_node).first().toPrettyString()
                println("----------Query Result")
            } catch (e: Throwable) {
                e.kotlinStacktrace()
                res = e.toString()
            }
        }
        return res
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
                REQUEST_PEERS_LIST[0] -> response = process_peers_list()
                REQUEST_PEERS_JOIN[0] -> response = process_peers_join(params[REQUEST_PEERS_JOIN[1]]?.first())
                REQUEST_SPARQL_QUERY[0] -> {
                    if (request.method == Http.Method.POST)
                        response = process_sparql_query(data)
                    else
                        response = process_sparql_query(params["query"]?.first())
                }
                REQUEST_TURTLE_INPUT[0] -> response = process_turtle_input(data)
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

