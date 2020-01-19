package lupos.p2p

import com.soywiz.korio.net.http.createHttpClient
import com.soywiz.korio.net.http.createHttpServer
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpServer
import lupos.s1buildSyntaxTree.ParseError
import lupos.s1buildSyntaxTree.rdf.Dictionary
import lupos.s1buildSyntaxTree.rdf.ID_Triple
import lupos.s1buildSyntaxTree.rdf.IRI
import lupos.s1buildSyntaxTree.rdf.SimpleLiteral
import lupos.s1buildSyntaxTree.sparql1_1.parseSPARQL
import lupos.s1buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s1buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s1buildSyntaxTree.LexerCharIterator
import lupos.s1buildSyntaxTree.*
import lupos.s1buildSyntaxTree.turtle.*
import lupos.s2buildOperatorGraph.OperatorGraphVisitor
import lupos.s3logicalOptimisation.LogicalOptimizer
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase
import lupos.s5physicalOperators.POPBaseNullableIterator
import lupos.s6tripleStore.TripleStore
import lupos.s7physicalOptimisation.PhysicalOptimizer
import lupos.s8outputResult.printResult
import lupos.misc.kotlinStacktrace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import kotlin.text.*

class XMLElement(val tag: String) {
    // https://regex101.com
    companion object {
        val XMLHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        fun parseFromXml(xml: String): List<XMLElement>? {
            val x = xml.replace("\n", "").replace("\r", "")
            val res = mutableListOf<XMLElement>()
            var lastindex = 0
            """((<([a-zA-Z]+)([^>]*?)>(.*?)<\/\3>)|(<([a-zA-Z]+)([^>]*?)>)|(<\?.*?\?>)|(<!--.*?-->))?""".toRegex().findAll(x).forEach() { child ->
                var value = child.value
                if (value.length > 0 && !value.startsWith("<?") && !value.startsWith("<!--") && child.range.start >= lastindex) {
                    var nodeName = ""
                    if (child.groups[3] != null)
                        nodeName = child.groups[3]!!.value
                    if (child.groups[7] != null)
                        nodeName = child.groups[7]!!.value
                    val childNode = XMLElement(nodeName)
                    res.add(childNode)
                    var nodeAttributes = ""
                    if (child.groups[4] != null)
                        nodeAttributes = child.groups[4]!!.value
                    if (child.groups[8] != null)
                        nodeAttributes = child.groups[8]!!.value
                    """([^\s]*?)="(([^\\"]*(\\"|\\)*)*)"""".toRegex().findAll(nodeAttributes).forEach() { attrMatch ->
                        if (attrMatch.groups[1] != null && attrMatch.groups[2] != null)
                            childNode.addAttribute(attrMatch.groups[1]!!.value, attrMatch.groups[2]!!.value)
                    }
                    var content = ""
                    if (child.groups[5] != null)
                        content = child.groups[5]!!.value
                    if (!child.value.endsWith("</${nodeName}>") && !child.value.endsWith("/>")) {
                        val search = "</${nodeName}>"
                        val idx2 = x.indexOf(search, child.range.last)
                        content = x.substring(child.range.last, idx2 + search.length)
                        lastindex = idx2
                    }
                    if (content != "") {
                        val tmp = parseFromXml(content)
                        if (tmp == null)
                            childNode.addContent(content)
                        else
                            childNode.addContent(tmp)
                    }
                }
            }
            if (res.isEmpty() && !xml.isEmpty())
                return null
            return res
        }
    }

    val attributes = mutableMapOf<String, String>()
    var content: String = ""
    val childs = mutableListOf<XMLElement>()
    fun addAttribute(name: String, value: String): XMLElement {
        attributes[name] = value
        return this
    }

    fun addContent(content: String): XMLElement {
        if (!childs.isEmpty())
            throw Exception("either content or subchilds must be empty")
        this.content += content
        return this
    }

    fun addContent(childs: Collection<XMLElement>): XMLElement {
        if (!content.isEmpty())
            throw Exception("either content or subchilds must be empty")
        this.childs.addAll(childs)
        return this
    }

    fun addContent(child: XMLElement): XMLElement {
        if (!content.isEmpty())
            throw Exception("either content or subchilds must be empty")
        childs.add(child)
        return this
    }

    fun addContent(childs: Collection<String>, childTag: String): XMLElement {
        for (c in childs) {
            addContent(XMLElement(childTag).addContent(c).toString())
        }
        return this
    }

    override fun toString(): String {
        var res = "<${tag}"
        for ((k, v) in attributes)
            res += " ${k}=\"${v}\""
        if (content.isEmpty() && childs.isEmpty()) {
            res += "/>"
        } else {
            res += ">"
            for (c in childs)
                res += c.toString()
            res += "${content}</${tag}>"
        }
        return res
    }

    fun toPrettyString(indention: String = ""): String {
        var res = "${indention}<${tag}"
        for ((k, v) in attributes)
            res += " ${k}=\"${v}\""
        if (content.isEmpty() && childs.isEmpty()) {
            res += "/>\n"
        } else {
            if (content.isEmpty()) {
                res += ">\n"
                for (c in childs)
                    res += c.toPrettyString(indention + "\t")
                res += "${indention}</${tag}>\n"
            } else {
                res += ">${content}</${tag}>\n"
            }
        }
        return res
    }

}

class TripleInsertIterator : POPBaseNullableIterator {
    var result: ResultRow?

    private val resultSet = ResultSet()

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
val        triple= ID_Triple(triple_s, triple_p, triple_o)
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
        return XMLElement("servers").addContent(knownClients, "server").toPrettyString()
    }

    suspend fun process_peers_join(hostname: String?): String {
        if (hostname != null && hostname != "localhost")
            knownClients.add(hostname)
        return ""
    }

    suspend fun process_turtle_input(data: String): String {
val lcit=LexerCharIterator(data)
val tit=TurtleScanner(lcit)
val ltit = LookAheadTokenIterator(tit, 3)
    TurtleParserWithDictionary(::consume_triple, ltit).turtleDoc()
	return XMLElement("done").toPrettyString()
	}

    suspend fun process_sparql_query(query: String): String {
            var res = ""
        try {
            val lcit = LexerCharIterator(query)
            val tit = TokenIteratorSPARQLParser(lcit)
            val ltit = LookAheadTokenIterator(tit, 3)
            val parser = SPARQLParser(ltit)
            val ast_node = parser.expr()
            val lop_node = ast_node.visit(OperatorGraphVisitor())
            val lop_node2 = LogicalOptimizer().optimize(lop_node)
            val pop_optimizer = PhysicalOptimizer()
            val pop_node = pop_optimizer.optimize(lop_node2) as POPBase
            val resultSet = pop_node.getResultSet()
            val variableNames = resultSet.getVariableNames().toTypedArray()
            val variables = arrayOfNulls<Variable>(variableNames.size)
            var i = 0
            for (variableName in variableNames) {
                if (i == 0)
                    res += variableName
                else
                    res += ", " + variableName
                variables[i] = resultSet.createVariable(variableName)
                i++
            }
            res += "\n\n"
            while (pop_node.hasNext()) {
                val resultRow = pop_node.next()
                i = 0
                for (variable in variables) {
                    if (i == 0)
                        res += resultSet.getValue(resultRow[variable!!])
                    else
                        res += ", " + resultSet.getValue(resultRow[variable!!])
                    i++
                }
                res += "\n"
            }
        } catch (e: Throwable) {
            res = e.toString()
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
        var response = XMLElement.XMLHeader
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
                REQUEST_PEERS_LIST[0] -> response = response + process_peers_list()
                REQUEST_PEERS_JOIN[0] -> response = response + process_peers_join(params[REQUEST_PEERS_JOIN[1]]?.first())
                REQUEST_SPARQL_QUERY[0] -> response = response + process_sparql_query(data)
                REQUEST_TURTLE_INPUT[0] -> response = response + process_turtle_input(data)
                else -> request.setStatus(404)
            }
        } catch (e: Throwable) {
            e.kotlinStacktrace()
            response = e.toString()
            request.setStatus(404)
        }
        request.end(response)
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
