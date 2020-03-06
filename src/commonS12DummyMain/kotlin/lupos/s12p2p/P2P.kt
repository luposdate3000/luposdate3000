package lupos.s12p2p

import kotlin.jvm.JvmField
import kotlinx.coroutines.delay
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.parseFromXml
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRepresenationNetwork
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPImportFromXml
import lupos.s09physicalOperators.POPBase
import lupos.s14endpoint.*
import lupos.s14endpoint.Endpoint
import lupos.SparqlTestSuite


object P2P {
    @JvmField
    val knownClients = ThreadSafeMutableList<String>()
    @JvmField
    val knownClientsLock = CoroutinesHelper.createLock()
    @JvmField
    val pendingModifications = ThreadSafeMutableMap<Long, MutableMap<String, TransferHelperNetwork>>()

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
        GlobalLogger.log(ELoggerType.DEBUG, { "execCommit $transactionID begin" })
        Endpoint.process_local_commit(transactionID)
        val pending = pendingModifications[transactionID]
        if (pending != null) {
            for ((node, data) in pending) {
                CoroutinesHelper.runBlock {
                    EndpointClientImpl.requestPostBytes("http://${(node)}${Endpoint.REQUEST_BINARY[0]}", data.finish())
                }
            }
            pendingModifications.remove(transactionID)
        }
        knownClients.forEach {
            if (it != endpointServer!!.fullname) {
                CoroutinesHelper.runBlock {
                    EndpointClientImpl.requestGetBytes("http://${(it)}${Endpoint.REQUEST_COMMIT[0]}" +//
                            "?" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_COMMIT[1], transactionID))
                }
            }
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "execCommit $transactionID end" })
    })

    fun execInsertOnNamedNode(nodeName: String, data: XMLElement) = Trace.trace({ "P2P.execInsertOnNamedNode" }, {
        CoroutinesHelper.runBlock {
            EndpointClientImpl.requestGetBytes("http://${(nodeName)}${Endpoint.REQUEST_XML_INPUT[0]}" +//
                    "?" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_XML_INPUT[1], data.toPrettyString()))
        }
    })

    fun execTripleAdd(node: String, graphName: String, transactionID: Long, s: AOPConstant, p: AOPConstant, o: AOPConstant, idx: EIndexPattern) = Trace.trace({ "P2P.execTripleAdd" }, {
        if (node == endpointServer!!.fullname)
            Endpoint.process_local_triple_add(graphName, transactionID, s, p, o, idx)
        else
            getPendingModifications(transactionID, node).addTriple(graphName, s, p, o, idx)
    })

    fun execOnNamedNode(dictionary: ResultSetDictionary, transactionID: Long, nodeName: String, pop: OPBase): OPBase = Trace.trace({ "P2P.execOnNamedNode" }, {
        var res: POPBase = POPEmptyRow(dictionary)
        CoroutinesHelper.runBlock {
            val xml = EndpointClientImpl.requestGetString("http://${(nodeName)}${Endpoint.REQUEST_OPERATOR_QUERY[0]}" +//
                    "?" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_OPERATOR_QUERY[1], pop.toXMLElement().toPrettyString()))
            res = POPImportFromXml(dictionary, XMLElement.parseFromXml(xml)!!.first())
        }
        return res
    })

    fun execGraphClearAll(transactionID: Long) = Trace.trace({ "P2P.execGraphClearAll" }, {
        Endpoint.process_local_graph_clear_all(transactionID)
        knownClients.forEach {
            if (it != endpointServer!!.fullname)
                getPendingModifications(transactionID, it).graphClearAll()
        }
    })

    fun execGraphOperation(name: String, type: EGraphOperationType) = Trace.trace({ "P2P.execGraphOperation" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "execGraphOperation $name $type P2P a" })
        Endpoint.process_local_graph_operation(name, type)
        GlobalLogger.log(ELoggerType.DEBUG, { "execGraphOperation $name $type P2P b" })
        knownClients.forEach {
            if (it != endpointServer!!.fullname) {
                CoroutinesHelper.runBlock {
                    EndpointClientImpl.requestGetBytes("http://${(it)}${Endpoint.REQUEST_GRAPH_OPERATION[0]}" +//
                            "?" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_GRAPH_OPERATION[1], name) +//
                            "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_GRAPH_OPERATION[2], type))
                }
            }
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "execGraphOperation $name $type P2P c" })
    })

    fun execTripleGet(node: String, graphName: String, resultSet: ResultSet, transactionID: Long, s: AOPBase, p: AOPBase, o: AOPBase, idx: EIndexPattern): POPBase = Trace.trace({ "P2P.execTripleGet" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "execTripleGet start $node $graphName $transactionID" })
        var res: POPBase? = null
        if (node == endpointServer!!.fullname)
            res = Endpoint.process_local_triple_get(graphName, resultSet, transactionID, s, p, o, idx)
        else {
            val sstr: String
            if (s is AOPConstant)
                sstr = s.valueToString()!!
            else if (s is AOPVariable)
                sstr = s.name
            else
                throw Exception("not reachable")

            val pstr: String
            if (p is AOPConstant)
                pstr = p.valueToString()!!
            else if (p is AOPVariable)
                pstr = p.name
            else
                throw Exception("not reachable")

            val ostr: String
            if (o is AOPConstant)
                ostr = o.valueToString()!!
            else if (o is AOPVariable)
                ostr = o.name
            else
                throw Exception("not reachable")
            val req = Endpoint.REQUEST_TRIPLE_GET[0] +//
                    "?" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[1], graphName) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[2], transactionID) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[3], sstr) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[4], pstr) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[5], ostr) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[6], s is AOPConstant) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[7], p is AOPConstant) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[8], o is AOPConstant) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[9], idx)
            CoroutinesHelper.runBlock {
                val responseBytes = EndpointClientImpl.requestGetBytes("http://${(node)}$req")
                res = ResultRepresenationNetwork.fromNetworkPackage(resultSet, responseBytes)
            }
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "execTripleGet end" })
        return res!!
    })

    fun execTripleDelete(node: String, graphName: String, transactionID: Long, data: List<AOPBase>, idx: EIndexPattern) = Trace.trace({ "P2P.execTripleDelete" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "execTripleDelete start" })
        if (node == endpointServer!!.fullname)
            Endpoint.process_local_triple_delete(graphName, transactionID, data[0], data[1], data[2], idx)
        else {
            val s: String
            if (data[0] is AOPConstant)
                s = (data[0] as AOPConstant).valueToString()!!
            else
                s = (data[0] as AOPVariable).name
            val p: String
            if (data[1] is AOPConstant)
                p = (data[1] as AOPConstant).valueToString()!!
            else
                p = (data[1] as AOPVariable).name
            val o: String
            if (data[2] is AOPConstant)
                o = (data[2] as AOPConstant).valueToString()!!
            else
                o = (data[2] as AOPVariable).name
            val req = Endpoint.REQUEST_TRIPLE_DELETE[0] +//
                    "?" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_DELETE[1], graphName) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_DELETE[2], transactionID) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_DELETE[3], s) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_DELETE[4], p) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_DELETE[5], o) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_DELETE[6], data[0] is AOPConstant) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_DELETE[7], data[1] is AOPConstant) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_DELETE[8], data[2] is AOPConstant) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_DELETE[9], idx)
            CoroutinesHelper.runBlock {
                EndpointClientImpl.requestGetBytes("http://${(node)}$req")
            }
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "execTripleDelete end" })
    })

    fun process_peers_self_test(): String = Trace.trace({ "P2P.process_peers_self_test" }, {
        SparqlTestSuite().testMain()
        return XMLElement.XMLHeader
    })

    fun process_peers_list(): String = Trace.trace({ "P2P.process_peers_list" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "process_peers_list" })
        var res: String
        val map = mutableListOf<String>()
        knownClients.forEach {
            map.add(it)
        }
        res = XMLElement.XMLHeader + "\n" + XMLElement("servers").addContent(map, "server").toPrettyString()
        return res
    })

    fun process_peers_join_internal(hostname: String?): String = Trace.trace({ "P2P.process_peers_join_internal" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "process_peers_join_internal $hostname" })
        CoroutinesHelper.runBlockWithLock(knownClientsLock) {
            if (hostname != null && hostname != "localhost")
                knownClients.add(hostname)
        }
        return XMLElement.XMLHeader
    })

    fun getKnownClientsCopy(): List<String> = Trace.trace({ "P2P.getKnownClientsCopy" }, {
        val knownClientsCopy = mutableListOf<String>()
        knownClients.forEach {
            knownClientsCopy.add(it)
        }
        return knownClientsCopy
    })

    suspend fun process_peers_join(hostname: String?): String = Trace.trace({ "P2P.process_peers_join" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "process_peers_join $hostname" })
        val knownClientsCopy = mutableListOf<String>()
        CoroutinesHelper.runBlockWithLock(knownClientsLock) {
            knownClients.forEach {
                knownClientsCopy.add(it)
            }
            if (hostname != null && hostname != "localhost")
                knownClients.add(hostname)
        }
        if (hostname != null && hostname != "localhost") {
            GlobalLogger.log(ELoggerType.DEBUG, { "process_peers_join $hostname 2" })
            knownClientsCopy.forEach {
                if (it != endpointServer!!.fullname) {
                    GlobalLogger.log(ELoggerType.DEBUG, { "process_peers_join $hostname 3 $it" })
                    GlobalLogger.log(ELoggerType.DEBUG, { "req $it ${Endpoint.REQUEST_PEERS_JOIN_INTERNAL[0]} $hostname" })
                    EndpointClientImpl.requestGetBytes("http://${(it)}${Endpoint.REQUEST_PEERS_JOIN_INTERNAL[0]}" +//
                            "?" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_PEERS_JOIN_INTERNAL[1], hostname))
                }
            }
            GlobalLogger.log(ELoggerType.DEBUG, { "process_peers_join $hostname 4" })
        }
        return XMLElement.XMLHeader + "\n" + XMLElement("servers").addContent(knownClientsCopy, "server").toPrettyString()
    })

    suspend fun start(bootstrap: String?) = Trace.trace({ "P2P.start" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "P2P.start $bootstrap" })
        CoroutinesHelper.runBlockWithLock(knownClientsLock) {
            knownClients.add(endpointServer!!.fullname)
        }
        if (bootstrap != null && bootstrap != "$endpointServer!!.fullname") {
            GlobalLogger.log(ELoggerType.DEBUG, { "P2P.start 2 $bootstrap" })
            GlobalLogger.log(ELoggerType.DEBUG, { "req $bootstrap ${Endpoint.REQUEST_PEERS_JOIN[0]} ${endpointServer!!.fullname}" })
            var responseString = EndpointClientImpl.requestGetString("http://${(bootstrap)}${Endpoint.REQUEST_PEERS_JOIN[0]}" +//
                    "?" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_PEERS_JOIN[1], endpointServer!!.fullname))
            XMLElement.parseFromXml(responseString)?.forEach { root ->
                if (root.tag == "servers") {
                    root.childs.forEach { server ->
                        if (server.tag == "server") {
                            knownClients.add(server.content)
                        }
                    }
                }
            }
        }
    })

}
