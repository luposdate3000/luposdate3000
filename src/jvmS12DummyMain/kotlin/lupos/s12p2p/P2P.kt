package lupos.s12p2p

import com.soywiz.korio.net.http.*
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
import lupos.*
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
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.noinput.*
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToXML
import lupos.s12p2p.*
import lupos.s12p2p.P2PLocalDummy
import lupos.s12p2p.POPServiceIRI
import lupos.s14endpoint.*


object P2P {
    val nodeNameRemapping = mutableMapOf<String, String>()
    val client = createHttpClient()
    val knownClients = mutableSetOf<String>()

    suspend fun retryRequest(method: Http.Method, url: String): HttpClient.Response {
	require(!url.startsWith("http://${EndpointImpl.fullname}"))
        while (true) {
            try {
                return client.request(method, url)
            } catch (e: Throwable) {
                delay(10)
            }
        }
    }

    fun resolveNodeName(name: String): String {
        val tmp = nodeNameRemapping[name]
        if (tmp != null)
            return tmp
        return name
    }

    fun process_peers_self_test(): String {
        testMain()
        return XMLElement.XMLHeader
    }

    fun process_peers_list(): String {
/*nice to have, but not required*/
        println("process_peers_list")
        var res = ""
        synchronized(knownClients) {
            res = XMLElement.XMLHeader + "\n" + XMLElement("servers").addContent(knownClients, "server").toPrettyString()
        }
        return res
    }

    fun process_peers_join_internal(hostname: String?): String {
/*just a dummy ... should be removed if there is a real p2p*/
        println("process_peers_join_internal $hostname")
        synchronized(knownClients) {
            if (hostname != null && hostname != "localhost")
                knownClients.add(hostname)
        }
        return XMLElement.XMLHeader
    }

    suspend fun process_peers_join(hostname: String?): String {
/*just a dummy ... should be removed if there is a real p2p*/
        println("process_peers_join $hostname")
        val knownClientsCopy = mutableListOf<String>()
        synchronized(knownClients) {
            knownClients.forEach {
                knownClientsCopy.add(it)
            }
            if (hostname != null && hostname != "localhost")
                knownClients.add(hostname)
        }
        if (hostname != null && hostname != "localhost") {
            println("process_peers_join $hostname 2")
            knownClientsCopy.forEach {
                if (it != EndpointImpl.fullname) {
                    println("process_peers_join $hostname 3 $it")
                    println("req ${it} ${EndpointImpl.REQUEST_PEERS_JOIN_INTERNAL[0]} ${hostname}")
                    retryRequest(Http.Method.GET, "http://${resolveNodeName(it)}${EndpointImpl.REQUEST_PEERS_JOIN_INTERNAL[0]}?${EndpointImpl.REQUEST_PEERS_JOIN_INTERNAL[1]}=${hostname}")
                }
            }
            println("process_peers_join $hostname 4")
        }
        return XMLElement.XMLHeader + "\n" + XMLElement("servers").addContent(knownClientsCopy, "server").toPrettyString()
    }

    suspend fun start(bootstrap: String?) {
/*start the p2p network. DONT block the thread*/
        println("P2P.start $bootstrap")
        synchronized(knownClients) {
            knownClients.add(EndpointImpl.fullname)
        }
        if (bootstrap != null && bootstrap != "$EndpointImpl.fullname") {
            println("P2P.start 2 $bootstrap")
            println("req ${bootstrap} ${EndpointImpl.REQUEST_PEERS_JOIN[0]} ${EndpointImpl.fullname}")
            var response = retryRequest(Http.Method.GET, "http://${resolveNodeName(bootstrap)}${EndpointImpl.REQUEST_PEERS_JOIN[0]}?${EndpointImpl.REQUEST_PEERS_JOIN[1]}=${EndpointImpl.fullname}")
            var responseString = response.readAllString()
            XMLElement.parseFromXml(responseString)?.forEach() { root ->
                if (root.tag == "servers") {
                    root.childs.forEach() { server ->
                        if (server.tag == "server") {
                            knownClients.add(server.content)
                        }
                    }
                }
            }
        }
    }

    fun execInsertOnNamedNode(nodeName: String, data: XMLElement) {
/*insert "data" on remote node - if it exist - otherwiese throw an exception*/
        runBlocking {
            val response = retryRequest(Http.Method.GET, "http://${resolveNodeName(nodeName)}${EndpointImpl.REQUEST_XML_INPUT[0]}?EndpointImpl.REQUEST_XML_INPUT[1]=" + URL.encodeComponent(data.toPrettyString()))
        }
    }

    fun execOnNamedNode(dictionary: ResultSetDictionary, transactionID: Long, nodeName: String, pop: OPBase): OPBase {
/*execute "pop" on remote node - if it exist - otherwiese throw an exception*/
        var res: POPBase = POPEmptyRow(dictionary)
        runBlocking {
            val response = retryRequest(Http.Method.GET, "http://${resolveNodeName(nodeName)}${EndpointImpl.REQUEST_OPERATOR_QUERY[0]}?EndpointImpl.REQUEST_OPERATOR_QUERY[1]=" + URL.encodeComponent(pop.toXMLElement().toPrettyString()))
            val xml = response.readAllString()
            res = POPImportFromXml(dictionary, XMLElement.parseFromXml(xml)!!.first())
        }
        return res
    }

    fun execTruncate() {
/*execute truncate on every known node - for TESTING only*/
        Endpoint.process_truncate()
        synchronized(knownClients) {
            knownClients.forEach {
		if(it!="$EndpointImpl.full"){
                runBlocking {
                    retryRequest(Http.Method.GET, "http://${resolveNodeName(it)}${EndpointImpl.REQUEST_TRUNCATE[0]}")
                }
            }
}
        }
        nodeNameRemapping.clear()
    }
}
