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
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*

import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s06buildOperatorGraph.OperatorGraphVisitor
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.noinput.*
import lupos.s09physicalOperators.POPBase
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToXML
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s15tripleStoreDistributed.DistributedTripleStore


object Endpoint {
    /*
    the distribution algorithm selected this node, therefore all of these functions need to be executed on THIS node, without any further distribution
    */
    suspend fun process_local_triple_modify(query: Query, graphName: String, params: Array<ColumnIterator>, idx: EIndexPattern, type: EModifyType) {
        DistributedTripleStore.localStore.getNamedGraph(query, graphName).modify(query, params, idx, type)
    }

    fun process_local_triple_get(query: Query, graphName: String, params: Array<AOPBase>, idx: EIndexPattern): ColumnIteratorRow {
        return DistributedTripleStore.localStore.getNamedGraph(query, graphName).getIterator(query, params, idx)
    }

    fun process_local_graph_clear_all(query: Query) {
        DistributedTripleStore.localStore.getDefaultGraph(query).clear()
        for (g in DistributedTripleStore.getGraphNames())
            DistributedTripleStore.dropGraph(query, g)
    }

    fun process_local_commit(query: Query) {
        DistributedTripleStore.localStore.commit(query)
    }

    fun process_local_graph_operation(query: Query, graphName: String, type: EGraphOperationType) {
        when (type) {
            EGraphOperationType.CLEAR -> DistributedTripleStore.localStore.clearGraph(query, graphName)
            EGraphOperationType.CREATE -> DistributedTripleStore.localStore.createGraph(query, graphName)
            EGraphOperationType.DROP -> DistributedTripleStore.localStore.dropGraph(query, graphName)
        }
    }
}
