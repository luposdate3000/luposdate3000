package lupos.s12p2p

import com.soywiz.korio.net.http.createHttpClient
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpClient
import com.soywiz.korio.net.URL
import com.soywiz.korio.stream.*
import kotlin.concurrent.thread
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import lupos.s00misc.*
import lupos.s00misc.parseFromXml
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s03resultRepresentation.*
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
    val pendingModifications = mutableMapOf<Long, MutableMap<String, TransferHelperNetwork>>()

    fun getPendingModifications(transactionID: Long, nodeName: String): TransferHelperNetwork {
        val transaction = pendingModifications[transactionID]
        if (transaction == null) {
            val tmp = mutableMapOf<String, TransferHelperNetwork>()
            pendingModifications[transactionID] = tmp
            val res = TransferHelperNetwork(transactionID)
            tmp[nodeName] = res
            return res
        }
        val tmp = transaction[nodeName]
        if (tmp != null)
            return tmp
        val res = TransferHelperNetwork(transactionID)
        transaction[nodeName] = res
        return res
    }

    fun execCommit(transactionID: Long) = Trace.trace({ "P2P.execCommit" }, {
        /*execute clear on every known node - for TESTING only*/
        GlobalLogger.log(ELoggerType.DEBUG, { "execCommit $transactionID begin" })
        Endpoint.process_local_commit(transactionID)

        val pending = pendingModifications[transactionID]
        if (pending != null) {
            for ((node, data) in pending) {
                runBlocking {
                    retryRequestPost("http://${resolveNodeName(node)}${EndpointImpl.REQUEST_BINARY[0]}", data.finish())
                }
            }
            pendingModifications.remove(transactionID)
        }


        synchronized(knownClients) {
            knownClients.forEach {
                if (it != EndpointImpl.fullname) {
                    runBlocking {
                        retryRequestGet("http://${resolveNodeName(it)}${EndpointImpl.REQUEST_COMMIT[0]}" +//
                                "?${EndpointImpl.REQUEST_COMMIT[1]}=${URL.encodeComponent("" + transactionID)}")
                    }
                }
            }
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "execCommit $transactionID end" })
    })

    fun execInsertOnNamedNode(nodeName: String, data: XMLElement) = Trace.trace({ "P2P.execInsertOnNamedNode" }, {
        /*insert "data" on remote node - if it exist - otherwiese throw an exception*/
        runBlocking {
            retryRequestGet("http://${resolveNodeName(nodeName)}${EndpointImpl.REQUEST_XML_INPUT[0]}" +//
                    "?EndpointImpl.REQUEST_XML_INPUT[1]=${URL.encodeComponent(data.toPrettyString())}")
        }
    })

    fun execTripleAdd(node: String, graphName: String, transactionID: Long, s: String, p: String, o: String, idx: EIndexPattern) = Trace.trace({ "P2P.execTripleAdd" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "execTripleAdd start" })
        if (node == EndpointImpl.fullname)
            Endpoint.process_local_triple_add(graphName, transactionID, s, p, o, idx)
        else
            getPendingModifications(transactionID, node).addTriple(graphName, s, p, o, idx)
        GlobalLogger.log(ELoggerType.DEBUG, { "execTripleAdd end" })
    })

    fun execOnNamedNode(dictionary: ResultSetDictionary, transactionID: Long, nodeName: String, pop: OPBase): OPBase = Trace.trace({ "P2P.execOnNamedNode" }, {
        /*execute "pop" on remote node - if it exist - otherwiese throw an exception*/
        var res: POPBase = POPEmptyRow(dictionary)
        runBlocking {
            val response = retryRequestGet("http://${resolveNodeName(nodeName)}${EndpointImpl.REQUEST_OPERATOR_QUERY[0]}" +//
                    "?EndpointImpl.REQUEST_OPERATOR_QUERY[1]=${URL.encodeComponent(pop.toXMLElement().toPrettyString())}")
            val xml = response.readAllString()
            res = POPImportFromXml(dictionary, XMLElement.parseFromXml(xml)!!.first())
        }
        return res
    }) as OPBase

    fun execGraphClearAll() = Trace.trace({ "P2P.execGraphClearAll" }, {
        /*execute clear on every known node - for TESTING only*/
        Endpoint.process_local_graph_clear_all()
        synchronized(knownClients) {
            knownClients.forEach {
                if (it != EndpointImpl.fullname) {
                    runBlocking {
                        retryRequestGet("http://${resolveNodeName(it)}${EndpointImpl.REQUEST_GRAPH_CLEAR_ALL[0]}")
                    }
                }
            }
        }
        nodeNameRemapping.clear()
    })

    fun execGraphOperation(name: String, type: EGraphOperationType) = Trace.trace({ "P2P.execGraphOperation" }, {
        /*execute clear on every known node - for TESTING only*/
        GlobalLogger.log(ELoggerType.DEBUG, { "execGraphOperation $name $type P2P a" })
        Endpoint.process_local_graph_operation(name, type)
        GlobalLogger.log(ELoggerType.DEBUG, { "execGraphOperation $name $type P2P b" })
        synchronized(knownClients) {
            knownClients.forEach {
                if (it != EndpointImpl.fullname) {
                    runBlocking {
                        retryRequestGet("http://${resolveNodeName(it)}${EndpointImpl.REQUEST_GRAPH_OPERATION[0]}" +//
                                "?${EndpointImpl.REQUEST_GRAPH_OPERATION[1]}=${URL.encodeComponent(name)}" +//
                                "&${EndpointImpl.REQUEST_GRAPH_OPERATION[2]}=${URL.encodeComponent("" + type)}")
                    }
                }
            }
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "execGraphOperation $name $type P2P c" })
    })

    fun execTripleGet(node: String, graphName: String, dictionary: ResultSetDictionary, transactionID: Long, s: String, p: String, o: String, sv: Boolean, pv: Boolean, ov: Boolean, idx: EIndexPattern): POPBase = Trace.trace({ "P2P.execTripleGet" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "execTripleGet start $node $graphName $transactionID" })
        var res: POPBase? = null
        if (node == EndpointImpl.fullname)
            res = Endpoint.process_local_triple_get(graphName, dictionary, transactionID, s, p, o, sv, pv, ov, idx)
        else {
            val req = "${EndpointImpl.REQUEST_TRIPLE_GET[0]}" +//
                    "?${EndpointImpl.REQUEST_TRIPLE_GET[1]}=${URL.encodeComponent(graphName)}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_GET[2]}=${URL.encodeComponent("" + transactionID)}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_GET[3]}=${URL.encodeComponent(s)}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_GET[4]}=${URL.encodeComponent(p)}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_GET[5]}=${URL.encodeComponent(o)}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_GET[6]}=${URL.encodeComponent("" + sv)}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_GET[7]}=${URL.encodeComponent("" + pv)}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_GET[8]}=${URL.encodeComponent("" + ov)}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_GET[9]}=${URL.encodeComponent("" + idx)}"
            runBlocking {
                val response = retryRequestGet("http://${resolveNodeName(node)}$req")
                var responseBytes = response.readAllBytes()
                res = ResultRepresenationNetwork.fromNetworkPackage(dictionary, responseBytes)
            }
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "execTripleGet end" })
        return res!!
    }) as POPBase

    fun execTripleDelete(node: String, graphName: String, transactionID: Long, data: List<Pair<String, Boolean>>, idx: EIndexPattern) = Trace.trace({ "P2P.execTripleDelete" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "execTripleDelete start" })
        if (node == EndpointImpl.fullname)
            Endpoint.process_local_triple_delete(graphName, transactionID, data[0].first, data[1].first, data[2].first, data[0].second, data[1].second, data[2].second, idx)
        else {
            val req = "${EndpointImpl.REQUEST_TRIPLE_DELETE[0]}" +//
                    "?${EndpointImpl.REQUEST_TRIPLE_DELETE[1]}=${URL.encodeComponent(graphName)}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_DELETE[2]}=${URL.encodeComponent("" + transactionID)}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_DELETE[3]}=${URL.encodeComponent(data[0].first)}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_DELETE[4]}=${URL.encodeComponent(data[1].first)}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_DELETE[5]}=${URL.encodeComponent(data[2].first)}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_DELETE[6]}=${URL.encodeComponent("" + data[0].second)}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_DELETE[7]}=${URL.encodeComponent("" + data[1].second)}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_DELETE[8]}=${URL.encodeComponent("" + data[2].second)}" +//
                    "&${EndpointImpl.REQUEST_TRIPLE_DELETE[9]}=${URL.encodeComponent("" + idx)}"
            runBlocking {
                retryRequestGet("http://${resolveNodeName(node)}$req")
            }
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "execTripleDelete end" })
    })

    suspend fun retryRequestGet(url: String): HttpClient.Response = Trace.trace({ "P2P.retryRequest" }, {
        require(!url.startsWith("http://${EndpointImpl.fullname}"))
        var i = 0
        while (true) {
            i++
            try {
                val res = client.request(Http.Method.GET, url)
                return res
            } catch (e: Throwable) {
                if (i > 100)
                    throw e
                delay(10)
            }
        }
    }) as HttpClient.Response

    suspend fun retryRequestPost(url: String, data: AsyncStream): HttpClient.Response = Trace.trace({ "P2P.retryRequest" }, {
        require(!url.startsWith("http://${EndpointImpl.fullname}"))
        var i = 0
        while (true) {
            i++
            try {
                val res = client.request(Http.Method.POST, url, Http.Headers(), data)
                return res
            } catch (e: Throwable) {
                if (i > 100)
                    throw e
                delay(10)
            }
        }
    }) as HttpClient.Response

    fun resolveNodeName(name: String): String = Trace.trace({ "P2P.resolveNodeName" }, {
        val tmp = nodeNameRemapping[name]
        if (tmp != null)
            return tmp
        return name
    }) as String

    fun process_peers_self_test(): String = Trace.trace({ "P2P.process_peers_self_test" }, {
        testMain()
        return XMLElement.XMLHeader
    }) as String

    fun process_peers_list(): String = Trace.trace({ "P2P.process_peers_list" }, {
        /*nice to have, but not required*/
        GlobalLogger.log(ELoggerType.DEBUG, { "process_peers_list" })
        var res = ""
        synchronized(knownClients) {
            res = XMLElement.XMLHeader + "\n" + XMLElement("servers").addContent(knownClients, "server").toPrettyString()
        }
        return res
    }) as String

    fun process_peers_join_internal(hostname: String?): String = Trace.trace({ "P2P.process_peers_join_internal" }, {
        /*just a dummy ... should be removed if there is a real p2p*/
        GlobalLogger.log(ELoggerType.DEBUG, { "process_peers_join_internal $hostname" })
        synchronized(knownClients) {
            if (hostname != null && hostname != "localhost")
                knownClients.add(hostname)
        }
        return XMLElement.XMLHeader
    }) as String

    fun getKnownClientsCopy(): List<String> = Trace.trace({ "P2P.getKnownClientsCopy" }, {
        val knownClientsCopy = mutableListOf<String>()
        synchronized(knownClients) {
            knownClients.forEach {
                knownClientsCopy.add(it)
            }
        }
        return knownClientsCopy
    }) as List<String>

    suspend fun process_peers_join(hostname: String?): String = Trace.trace({ "P2P.process_peers_join" }, {
        /*just a dummy ... should be removed if there is a real p2p*/
        GlobalLogger.log(ELoggerType.DEBUG, { "process_peers_join $hostname" })
        val knownClientsCopy = mutableListOf<String>()
        synchronized(knownClients) {
            knownClients.forEach {
                knownClientsCopy.add(it)
            }
            if (hostname != null && hostname != "localhost")
                knownClients.add(hostname)
        }
        if (hostname != null && hostname != "localhost") {
            GlobalLogger.log(ELoggerType.DEBUG, { "process_peers_join $hostname 2" })
            knownClientsCopy.forEach {
                if (it != EndpointImpl.fullname) {
                    GlobalLogger.log(ELoggerType.DEBUG, { "process_peers_join $hostname 3 $it" })
                    GlobalLogger.log(ELoggerType.DEBUG, { "req ${it} ${EndpointImpl.REQUEST_PEERS_JOIN_INTERNAL[0]} ${hostname}" })
                    retryRequestGet("http://${resolveNodeName(it)}${EndpointImpl.REQUEST_PEERS_JOIN_INTERNAL[0]}" +//
                            "?${EndpointImpl.REQUEST_PEERS_JOIN_INTERNAL[1]}=${URL.encodeComponent(hostname)}")
                }
            }
            GlobalLogger.log(ELoggerType.DEBUG, { "process_peers_join $hostname 4" })
        }
        return XMLElement.XMLHeader + "\n" + XMLElement("servers").addContent(knownClientsCopy, "server").toPrettyString()
    }) as String

    suspend fun start(bootstrap: String?) = Trace.trace({ "P2P.start" }, {
        /*start the p2p network. DONT block the thread*/
        GlobalLogger.log(ELoggerType.DEBUG, { "P2P.start $bootstrap" })
        synchronized(knownClients) {
            knownClients.add(EndpointImpl.fullname)
        }
        if (bootstrap != null && bootstrap != "$EndpointImpl.fullname") {
            GlobalLogger.log(ELoggerType.DEBUG, { "P2P.start 2 $bootstrap" })
            GlobalLogger.log(ELoggerType.DEBUG, { "req ${bootstrap} ${EndpointImpl.REQUEST_PEERS_JOIN[0]} ${EndpointImpl.fullname}" })
            var response = retryRequestGet("http://${resolveNodeName(bootstrap)}${EndpointImpl.REQUEST_PEERS_JOIN[0]}" +//
                    "?${EndpointImpl.REQUEST_PEERS_JOIN[1]}=${URL.encodeComponent(EndpointImpl.fullname)}")
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
    })

}
