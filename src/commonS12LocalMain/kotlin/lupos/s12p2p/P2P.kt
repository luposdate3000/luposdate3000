package lupos.s12p2p

import lupos.s00misc.*
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s03resultRepresentation.ResultSetDictionary
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

    fun execTripleAdd(node: String, graphName: String, transactionID: Long, s: String, p: String, o: String, idx: EIndexPattern) = Trace.trace({ "P2P.execTripleAdd" }, {
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

    fun execTripleGet(node: String, graphName: String, resultSet: ResultSet, transactionID: Long, s: String, p: String, o: String, sv: Boolean, pv: Boolean, ov: Boolean, idx: EIndexPattern): POPBase = Trace.trace({ "P2P.execTripleGet" }, {
        return Endpoint.process_local_triple_get(graphName, resultSet, transactionID, s, p, o, sv, pv, ov, idx)
    })

    fun execTripleDelete(node: String, graphName: String, transactionID: Long, data: List<Pair<String, Boolean>>, idx: EIndexPattern) = Trace.trace({ "P2P.execTripleDelete" }, {
        Endpoint.process_local_triple_delete(graphName, transactionID, data[0].first, data[1].first, data[2].first, data[0].second, data[1].second, data[2].second, idx)
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
        return mutableListOf<String>()
    })

    suspend fun process_peers_join(hostname: String?): String = Trace.trace({ "P2P.process_peers_join" }, {
        return XMLElement.XMLHeader
    })

    suspend fun start(bootstrap: String?) = Trace.trace({ "P2P.start" }, {
    })

}
