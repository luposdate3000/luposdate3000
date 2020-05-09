package lupos.s14endpoint
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.TripleStoreBulkImport
import lupos.s15tripleStoreDistributed.DistributedTripleStore




object Endpoint {
    suspend fun process_local_triple_import(query: Query, graphName: String, data: TripleStoreBulkImport, idx: EIndexPattern) {
        DistributedTripleStore.localStore.getNamedGraph(query, graphName).import(data, idx)
    }

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
        for (g in DistributedTripleStore.getGraphNames()) {
            DistributedTripleStore.dropGraph(query, g)
        }
    }

    fun process_local_commit(query: Query) {
        DistributedTripleStore.localStore.commit(query)
    }

    fun process_local_graph_operation(query: Query, graphName: String, type: EGraphOperationType) {
        when (type) {
            EGraphOperationType.CLEAR -> {
                DistributedTripleStore.localStore.clearGraph(query, graphName)
            }
            EGraphOperationType.CREATE -> {
                DistributedTripleStore.localStore.createGraph(query, graphName)
            }
            EGraphOperationType.DROP -> {
                DistributedTripleStore.localStore.dropGraph(query, graphName)
            }
        }
    }
}
