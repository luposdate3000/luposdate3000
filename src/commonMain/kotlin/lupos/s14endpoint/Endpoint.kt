package lupos.s14endpoint

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.GlobalLogger
import lupos.s00misc.parseFromXml
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
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s06buildOperatorGraph.OperatorGraphVisitor
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.noinput.*
import lupos.s09physicalOperators.POPBase
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToXML
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s15tripleStoreDistributed.DistributedTripleStore

object Endpoint {
    @JvmField
    val REQUEST_BINARY = arrayOf("/binary")
    @JvmField
    val REQUEST_TRIPLE_GET = arrayOf("/triple/get", "graph", "id", "s", "p", "o", "sv", "pv", "ov", "idx")
    @JvmField
    val REQUEST_TRIPLE_DELETE = arrayOf("/triple/delete", "graph", "id", "s", "p", "o", "idx")
    @JvmField
    val REQUEST_COMMIT = arrayOf("/commit", "id")
    @JvmField
    val REQUEST_SPARQL_QUERY = arrayOf("/sparql/query", "query")
    @JvmField
    val REQUEST_GRAPH_OPERATION = arrayOf("/graph/operation", "name", "id", "type")
    @JvmField
    val REQUEST_TURTLE_INPUT = arrayOf("/import/turtle", "data")
    @JvmField
    val REQUEST_XML_INPUT = arrayOf("/import/xml", "data")
    @JvmField
    val REQUEST_PEERS_LIST = arrayOf("/peers/list")
    @JvmField
    val REQUEST_PEERS_JOIN = arrayOf("/peers/join", "hostname")
    @JvmField
    val REQUEST_PEERS_JOIN_INTERNAL = arrayOf("/peers/join_internal", "hostname")
    @JvmField
    val REQUEST_PEERS_SELF_TEST = arrayOf("/peers/self_test")
    @JvmField
    val REQUEST_STACKTRACE = arrayOf("/stacktrace")
    @JvmField
    val REQUEST_OPERATOR_QUERY = arrayOf("operator/query", "query")

    fun process_local_triple_add(query: Query, graphName: String, params: Array<ValueDefinition>, idx: EIndexPattern): XMLElement = Trace.trace({ "process_local_triple_add" }, {
        val g = DistributedTripleStore.localStore.getNamedGraph(query, graphName)
        g.addData(query, params, idx)
        return XMLElement("success")
    })

    fun process_local_triple_delete(query: Query, graphName: String, params: Array<ValueDefinition>, idx: EIndexPattern): XMLElement = Trace.trace({ "process_local_triple_delete" }, {
        val g = DistributedTripleStore.localStore.getNamedGraph(query, graphName)
        g.deleteData(query, params, idx)
        return XMLElement("success")
    })

    fun process_local_triple_get(query: Query, resultSet: ResultSet, graphName: String, params: Array<AOPBase>, idx: EIndexPattern): POPBase = Trace.trace({ "process_local_triple_get" }, {
        val g = DistributedTripleStore.localStore.getNamedGraph(query, graphName)
        return g.getIterator(query, resultSet, params, idx)
    })

    fun process_local_graph_clear_all(query: Query): XMLElement = Trace.trace({ "process_local_graph_clear_all" }, {
        DistributedTripleStore.localStore.getDefaultGraph(query).clear()
        for (g in DistributedTripleStore.getGraphNames())
            DistributedTripleStore.dropGraph(query, g)
        return XMLElement("success")
    })

    fun process_local_commit(query: Query): XMLElement = Trace.trace({ "process_local_commit" }, {
        DistributedTripleStore.localStore.commit(query)
        return XMLElement("success")
    })

    fun process_local_graph_operation(query: Query, name: String, type: EGraphOperationType): XMLElement = Trace.trace({ "process_local_graph_operation" }, {
        when (type) {
            EGraphOperationType.CLEAR -> DistributedTripleStore.localStore.clearGraph(query, name)
            EGraphOperationType.CREATE -> DistributedTripleStore.localStore.createGraph(query, name)
            EGraphOperationType.DROP -> DistributedTripleStore.localStore.dropGraph(query, name)
            else -> SanityCheck.checkUnreachable()
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "process_local_graph_operation bb" })
        return XMLElement("success")
    })
}
