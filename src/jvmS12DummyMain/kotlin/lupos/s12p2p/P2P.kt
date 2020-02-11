package lupos.s12p2p

import com.soywiz.korio.net.http.createHttpClient
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpClient
import com.soywiz.korio.net.URL
import kotlin.concurrent.thread
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import lupos.s00misc.parseFromXml
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPImportFromXml
import lupos.s09physicalOperators.POPBase
import lupos.s14endpoint.Endpoint
import lupos.s14endpoint.EndpointImpl
import lupos.testMain


object P2P {
    val nodeNameRemapping = mutableMapOf<String, String>()
    val client = createHttpClient()
    val knownClients = mutableListOf<String>()

    suspend fun retryRequest(method: Http.Method, url: String): HttpClient.Response {
        require(!url.startsWith("http://${EndpointImpl.fullname}"))
        println("retryRequest::$url")
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

    fun getKnownClientsCopy(): List<String> {
        val knownClientsCopy = mutableListOf<String>()
        synchronized(knownClients) {
            knownClients.forEach {
                knownClientsCopy.add(it)
            }
        }
        return knownClientsCopy
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

    fun execGraphClearAll() {
/*execute clear on every known node - for TESTING only*/
        Endpoint.process_local_graph_clear_all()
        synchronized(knownClients) {
            knownClients.forEach {
                if (it != EndpointImpl.fullname) {
                    runBlocking {
                        retryRequest(Http.Method.GET, "http://${resolveNodeName(it)}${EndpointImpl.REQUEST_GRAPH_CLEAR_ALL[0]}")
                    }
                }
            }
        }
        nodeNameRemapping.clear()
    }

    fun execGraphOperation(name: String, type: GraphOperationType) {
/*execute clear on every known node - for TESTING only*/
        println("execGraphOperation $name $type P2P a")
        Endpoint.process_local_graph_operation(name, type)
        println("execGraphOperation $name $type P2P b")
        synchronized(knownClients) {
            knownClients.forEach {
                if (it != EndpointImpl.fullname) {
                    runBlocking {
                        retryRequest(Http.Method.GET, "http://${resolveNodeName(it)}${EndpointImpl.REQUEST_GRAPH_OPERATION[0]}?${EndpointImpl.REQUEST_GRAPH_OPERATION[1]}=$name&${EndpointImpl.REQUEST_GRAPH_OPERATION[2]}=$type")
                    }
                }
            }
        }
        println("execGraphOperation $name $type P2P c")
    }

    fun execCommit(transactionID: Long) {
/*execute clear on every known node - for TESTING only*/
println("execCommit $transactionID begin")
        Endpoint.process_local_commit(transactionID)
        synchronized(knownClients) {
            knownClients.forEach {
                if (it != EndpointImpl.fullname) {
                    runBlocking {
                        retryRequest(Http.Method.GET, "http://${resolveNodeName(it)}${EndpointImpl.REQUEST_COMMIT[0]}?${EndpointImpl.REQUEST_COMMIT[1]}=$transactionID")
                    }
                }
            }
        }
println("execCommit $transactionID end")
    }

    fun execTripleAdd(node: String, graphName: String, transactionID: Long, data: List<String>) {
        println("execTripleAdd start")
        if (node == EndpointImpl.fullname)
            Endpoint.process_local_triple_add(graphName, transactionID, data[0], data[1], data[2])
        else {
            val req = "${EndpointImpl.REQUEST_TRIPLE_ADD[0]}" +//
                    "?${EndpointImpl.REQUEST_TRIPLE_ADD[1]}=$graphName" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_ADD[2]}=$transactionID" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_ADD[3]}=${data[0]}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_ADD[4]}=${data[1]}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_ADD[5]}=${data[2]}"
            runBlocking {
                retryRequest(Http.Method.GET, "http://${resolveNodeName(node)}$req")
            }
        }
        println("execTripleAdd end")
    }

    fun execTripleGet(node: String, graphName: String, transactionID: Long): XMLElement {
        println("execTripleGet start $node $graphName $transactionID")
        var res: XMLElement? = null
        if (node == EndpointImpl.fullname)
            res = Endpoint.process_local_triple_get(graphName, transactionID)
        else {
            val req = "${EndpointImpl.REQUEST_TRIPLE_GET[0]}" +//
                    "?${EndpointImpl.REQUEST_TRIPLE_GET[1]}=$graphName" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_GET[2]}=$transactionID"
            runBlocking {
                val response = retryRequest(Http.Method.GET, "http://${resolveNodeName(node)}$req")
                var responseString = response.readAllString()
                res = XMLElement.parseFromXml(responseString)!!.first()!!
            }
        }
        println("execTripleGet end")
        return res!!
    }

    fun execTripleDelete(node: String, graphName: String, transactionID: Long, data: List<Pair<String, Boolean>>) {
        println("execTripleDelete start")
        if (node == EndpointImpl.fullname)
            Endpoint.process_local_triple_delete(graphName, transactionID, data[0].first, data[1].first, data[2].first, data[0].second, data[1].second, data[2].second)
        else {
            val req = "${EndpointImpl.REQUEST_TRIPLE_DELETE[0]}" +//
                    "?${EndpointImpl.REQUEST_TRIPLE_DELETE[1]}=$graphName" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_DELETE[2]}=$transactionID" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_DELETE[3]}=${data[0].first}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_DELETE[4]}=${data[1].first}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_DELETE[5]}=${data[2].first}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_DELETE[6]}=${data[0].second}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_DELETE[7]}=${data[1].second}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_DELETE[8]}=${data[2].second}"
            runBlocking {
                retryRequest(Http.Method.GET, "http://${resolveNodeName(node)}$req")
            }
        }
        println("execTripleDelete end")
    }
}
