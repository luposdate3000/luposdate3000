package lupos.s12p2p

import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ThreadSafeMutableList
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.POPBase
import lupos.s14endpoint.Endpoint
import lupos.testMain


object P2P {
    val knownClients = ThreadSafeMutableList<String>()
    fun execCommit(transactionID: Long) = Trace.trace({ "P2P.execCommit" }, {
        Endpoint.process_local_commit(transactionID)
    })

    fun execInsertOnNamedNode(nodeName: String, data: XMLElement) = Trace.trace({ "P2P.execInsertOnNamedNode" }, {
    })

    fun execTripleAdd(node: String, graphName: String, transactionID: Long, s: AOPConstant, p: AOPConstant, o: AOPConstant, idx: EIndexPattern) = Trace.trace({ "P2P.execTripleAdd" }, {
        Endpoint.process_local_triple_add(graphName, transactionID, s, p, o, idx)
    })

    fun execOnNamedNode(dictionary: ResultSetDictionary, transactionID: Long, nodeName: String, pop: OPBase): OPBase = Trace.trace({ "P2P.execOnNamedNode" }, {
        return POPEmptyRow(dictionary)
    })

    fun execGraphClearAll() = Trace.trace({ "P2P.execGraphClearAll" }, {
        Endpoint.process_local_graph_clear_all()
    })

    fun execGraphOperation(name: String, type: EGraphOperationType) = Trace.trace({ "P2P.execGraphOperation" }, {
        Endpoint.process_local_graph_operation(name, type)
    })

    fun execTripleGet(node: String, graphName: String, resultSet: ResultSet, transactionID: Long, s: AOPBase, p: AOPBase, o: AOPBase, idx: EIndexPattern): POPBase = Trace.trace({ "P2P.execTripleGet" }, {
        return Endpoint.process_local_triple_get(graphName, resultSet, transactionID, s, p, o, idx)
    })

    fun execTripleDelete(node: String, graphName: String, transactionID: Long, data: List<AOPBase>, idx: EIndexPattern) = Trace.trace({ "P2P.execTripleDelete" }, {
        Endpoint.process_local_triple_delete(graphName, transactionID, data[0], data[1], data[2], idx)
    })

    fun process_peers_self_test(): String = Trace.trace({ "P2P.process_peers_self_test" }, {
        testMain()
        return XMLElement.XMLHeader
    })

    fun process_peers_list(): String = Trace.trace({ "P2P.process_peers_list" }, {
        return XMLElement.XMLHeader
    })

    fun process_peers_join_internal(hostname: String?): String = Trace.trace({ "P2P.process_peers_join_internal" }, {
        return XMLElement.XMLHeader
    })

    fun getKnownClientsCopy(): List<String> = Trace.trace({ "P2P.getKnownClientsCopy" }, {
        val res = mutableListOf<String>()
        knownClients.forEach {
            res.add(it)
        }
        return res
    })

    suspend fun process_peers_join(hostname: String?): String = Trace.trace({ "P2P.process_peers_join" }, {
        return XMLElement.XMLHeader
    })

    suspend fun start(bootstrap: String?) = Trace.trace({ "P2P.start" }, {
    })

}
