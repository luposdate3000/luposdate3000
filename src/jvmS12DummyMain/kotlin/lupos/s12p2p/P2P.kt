package lupos.s12p2p

import com.soywiz.korio.net.http.createHttpClient
import com.soywiz.korio.net.http.createHttpServer
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpServer
import com.soywiz.korio.net.URL
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
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.POPBaseNullableIterator
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToXML
import lupos.s12p2p.P2P
import lupos.s12p2p.P2PLocalDummy
import lupos.s12p2p.POPServiceIRI


object P2P {
    val nodeNameRemapping = mutableMapOf<String, String>()
    val client = createHttpClient()
    val knownClients = mutableSetOf<String>()
    fun resolveNodeName(name: String): String {
        val tmp = nodeNameRemapping[name]
        if (tmp != null)
            return tmp
        return name
    }

    fun process_peers_list(): String {
/*nice to have, but not required*/
        return XMLElement.XMLHeader + "\n" + XMLElement("servers").addContent(knownClients, "server").toPrettyString()
    }

    fun process_peers_join(hostname: String?): String {
/*just a dummy ... should be removed if there is a real p2p*/
        if (hostname != null && hostname != "localhost")
            knownClients.add(hostname)
        return XMLElement.XMLHeader
    }

    suspend fun start(bootstrap: String?) {
/*start the p2p network. DONT block the thread*/
        knownClients.add(EndpointImpl.fullname)
        if (bootstrap != null && bootstrap != "$EndpointImpl.hostname:$EndpointImpl.port") {
            var response = client.request(Http.Method.GET, "http://${bootstrap}${EndpointImpl.REQUEST_PEERS_LIST[0]}")
            var responseString = response.readAllString()
            XMLElement.parseFromXml(responseString)?.forEach() { root ->
                if (root.tag == "servers") {
                    root.childs.forEach() { server ->
                        if (server.tag == "server") {
                            knownClients.add(server.content)
                            client.request(Http.Method.GET, "http://${server.content}${EndpointImpl.REQUEST_PEERS_JOIN[0]}?${EndpointImpl.REQUEST_PEERS_JOIN[1]}=${EndpointImpl.fullname}")
                        }
                    }
                }
            }
        }
    }

    fun execInsertOnNamedNode(nodeName: String, data: XMLElement) {
/*insert "data" on remote node - if it exist - otherwiese throw an exception*/
        println(data.toPrettyString())
        runBlocking {
            val response = client.request(Http.Method.GET, "http://${resolveNodeName(nodeName)}${EndpointImpl.REQUEST_XML_INPUT[0]}?EndpointImpl.REQUEST_XML_INPUT[1]=" + URL.encodeComponent(data.toPrettyString()))
        }
    }

    fun execOnNamedNode(nodeName: String, pop: OPBase): OPBase {
/*execute "pop" on remote node - if it exist - otherwiese throw an exception*/
        var res: POPBase = POPEmptyRow()
        runBlocking {
            val response = client.request(Http.Method.GET, "http://${resolveNodeName(nodeName)}${EndpointImpl.REQUEST_OPERATOR_QUERY[0]}?EndpointImpl.REQUEST_OPERATOR_QUERY[1]=" + URL.encodeComponent(pop.toXMLElement().toPrettyString()))
            val xml = response.readAllString()
            res = POPImportFromXml(XMLElement.parseFromXml(xml)!!.first())
        }
        return res
    }

    fun execTruncate() {
/*execute truncate on every known node - for TESTING only*/
        Endpoint.process_truncate()
        knownClients.forEach {
            runBlocking {
                client.request(Http.Method.GET, "http://${it}${EndpointImpl.REQUEST_TRUNCATE[0]}")
            }
        }
    }
}
