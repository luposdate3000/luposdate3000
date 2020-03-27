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
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.noinput.*
import lupos.s09physicalOperators.noinput.POPEmptyRow
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

    fun getPendingModifications(query: Query, nodeName: String): TransferHelperNetwork {
        val transaction = pendingModifications[query.transactionID]
        if (transaction == null) {
            val tmp = mutableMapOf<String, TransferHelperNetwork>()
            pendingModifications[query.transactionID] = tmp
            val res = TransferHelperNetwork(query)
            tmp[nodeName] = res
            return res
        }
        val tmp = transaction[nodeName]
        if (tmp != null)
            return tmp
        val res = TransferHelperNetwork(query)
        transaction[nodeName] = res
        return res
    }

    fun execCommit(query: Query) = Trace.trace({ "P2P.execCommit" }, {
        Endpoint.process_local_commit(query)
        val pending = pendingModifications[query.transactionID]
        if (pending != null) {
            for ((node, data) in pending) {
                CoroutinesHelper.runBlock {
                    EndpointClientImpl.requestPostBytes("http://${(node)}${Endpoint.REQUEST_BINARY[0]}", data.finish())
                }
            }
            pendingModifications.remove(query.transactionID)
        }
        knownClients.forEach {
            if (it != endpointServer!!.fullname) {
                CoroutinesHelper.runBlock {
                    EndpointClientImpl.requestGetBytes("http://${(it)}${Endpoint.REQUEST_COMMIT[0]}" +//
                            "?" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_COMMIT[1], query.transactionID))
                }
            }
        }
    })

    fun execInsertOnNamedNode(nodeName: String, data: XMLElement) = Trace.trace({ "P2P.execInsertOnNamedNode" }, {
        CoroutinesHelper.runBlock {
            EndpointClientImpl.requestGetBytes("http://${(nodeName)}${Endpoint.REQUEST_XML_INPUT[0]}" +//
                    "?" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_XML_INPUT[1], data.toPrettyString()))
        }
    })

    fun execTripleAdd(query: Query, node: String, graphName: String, params: Array<ValueDefinition>, idx: EIndexPattern) = Trace.trace({ "P2P.execTripleAdd" }, {
        if (node == endpointServer!!.fullname)
            Endpoint.process_local_triple_add(query, graphName, params, idx)
        else
            getPendingModifications(query, node).addTriple(graphName, params, idx)
    })

    fun execOnNamedNode(query: Query, nodeName: String, pop: OPBase): OPBase = Trace.trace({ "P2P.execOnNamedNode" }, {
        var res: POPBase = POPEmptyRow(query)
        CoroutinesHelper.runBlock {
            val xml = EndpointClientImpl.requestGetString("http://${(nodeName)}${Endpoint.REQUEST_OPERATOR_QUERY[0]}" +//
                    "?" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_OPERATOR_QUERY[1], pop.toXMLElement().toPrettyString()))
            res = POPValuesImportXML(query, XMLElement.parseFromXml(xml)!!.first())
        }
        return res
    })

    fun execGraphClearAll(query: Query) = Trace.trace({ "P2P.execGraphClearAll" }, {
        Endpoint.process_local_graph_clear_all(query)
        knownClients.forEach {
            if (it != endpointServer!!.fullname)
                getPendingModifications(query, it).graphClearAll()
        }
    })

    fun execGraphOperation(query: Query, name: String, type: EGraphOperationType) = Trace.trace({ "P2P.execGraphOperation" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "execGraphOperation $name $type P2P a" })
        Endpoint.process_local_graph_operation(query, name, type)
        GlobalLogger.log(ELoggerType.DEBUG, { "execGraphOperation $name $type P2P b" })
        knownClients.forEach {
            if (it != endpointServer!!.fullname) {
                CoroutinesHelper.runBlock {
                    EndpointClientImpl.requestGetBytes("http://${(it)}${Endpoint.REQUEST_GRAPH_OPERATION[0]}" +//
                            "?" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_GRAPH_OPERATION[1], name) +//
                            "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_GRAPH_OPERATION[2], query.transactionID) +//
                            "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_GRAPH_OPERATION[3], type))
                }
            }
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "execGraphOperation $name $type P2P c" })
    })

    fun execTripleGet(query: Query, resultSet: ResultSet, node: String, graphName: String, params: Array<AOPBase>, idx: EIndexPattern): POPBase = Trace.trace({ "P2P.execTripleGet" }, {
        var res: POPBase? = null
        if (node == endpointServer!!.fullname)
            res = Endpoint.process_local_triple_get(query, resultSet, graphName, params, idx)
        else {
            val sarr = Array(3) {
                if (params[it] is AOPConstant)
                    (params[it] as AOPConstant).value.valueToString()!!
                else if (params[it] is AOPVariable)
                    (params[it] as AOPVariable).name
                else
                    throw Exception("not reachable")
            }
            val req = Endpoint.REQUEST_TRIPLE_GET[0] +//
                    "?" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[1], graphName) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[2], query.transactionID) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[3], sarr[0]) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[4], sarr[1]) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[5], sarr[2]) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[6], params[0] is AOPConstant) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[7], params[1] is AOPConstant) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[8], params[2] is AOPConstant) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_GET[9], idx)
            CoroutinesHelper.runBlock {
                val responseBytes = EndpointClientImpl.requestGetBytes("http://${(node)}$req")
                res = ResultRepresenationNetwork.fromNetworkPackage(query, resultSet, responseBytes)
            }
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "execTripleGet end" })
        return res!!
    })

    fun execTripleDelete(query: Query, node: String, graphName: String, data: Array<ValueDefinition>, idx: EIndexPattern) = Trace.trace({ "P2P.execTripleDelete" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "execTripleDelete start" })
        if (node == endpointServer!!.fullname)
            Endpoint.process_local_triple_delete(query, graphName, data, idx)
        else {
            val req = Endpoint.REQUEST_TRIPLE_DELETE[0] +//
                    "?" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_DELETE[1], graphName) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_DELETE[2], query.transactionID) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_DELETE[3], data[0].valueToString()!!) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_DELETE[4], data[1].valueToString()!!) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_DELETE[5], data[2].valueToString()!!) +//
                    "&" + EndpointClientImpl.encodeParam(Endpoint.REQUEST_TRIPLE_DELETE[6], idx)
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
