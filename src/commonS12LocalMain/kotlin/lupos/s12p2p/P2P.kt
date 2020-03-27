package lupos.s12p2p

import kotlin.jvm.JvmField
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ThreadSafeMutableList
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.POPBase
import lupos.s14endpoint.Endpoint
import lupos.SparqlTestSuite

object P2P {
    @JvmField
    val knownClients = ThreadSafeMutableList<String>()

    fun execCommit(query: Query) = Trace.trace({ "P2P.execCommit" }, {
        Endpoint.process_local_commit(query)
    })

    fun execInsertOnNamedNode(nodeName: String, data: XMLElement) = Trace.trace({ "P2P.execInsertOnNamedNode" }, {
    })

    fun execTripleAdd(query: Query, node: String, graphName: String, params: Array<ValueDefinition>, idx: EIndexPattern) = Trace.trace({ "P2P.execTripleAdd" }, {
        Endpoint.process_local_triple_add(query, graphName, params, idx)
    })

    fun execOnNamedNode(query: Query, nodeName: String, pop: OPBase): OPBase = Trace.trace({ "P2P.execOnNamedNode" }, {
        return POPEmptyRow(query)
    })

    fun execGraphClearAll(query: Query) = Trace.trace({ "P2P.execGraphClearAll" }, {
        Endpoint.process_local_graph_clear_all(query)
    })

    fun execGraphOperation(query: Query, name: String, type: EGraphOperationType) = Trace.trace({ "P2P.execGraphOperation" }, {
        Endpoint.process_local_graph_operation(query, name, type)
    })

    fun execTripleGet(query: Query, resultSet: ResultSet node: String, graphName: String, params: Array<AOPBase>, idx: EIndexPattern): POPBase = Trace.trace({ "P2P.execTripleGet" }, {
        return Endpoint.process_local_triple_get(query, resultSet, graphName, params, idx)
    })

    fun execTripleDelete(query: Query, node: String, graphName: String, data: Array<AOPBase>, idx: EIndexPattern) = Trace.trace({ "P2P.execTripleDelete" }, {
        Endpoint.process_local_triple_delete(query, graphName, data, idx)
    })

    fun process_peers_self_test(): String = Trace.trace({ "P2P.process_peers_self_test" }, {
        SparqlTestSuite().testMain()
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
