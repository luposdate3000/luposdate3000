package lupos.s16network

import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EModifyType
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.TripleStoreBulkImport
import lupos.s05tripleStore.TripleStoreFeatureParams
import lupos.s15tripleStoreDistributed.DistributedTripleStore

object ServerCommunicationSend {
    suspend fun bulkImport(query: Query, graphName: String, action: suspend (TripleStoreBulkImportDistributed) -> Unit) {
        val bulk = TripleStoreBulkImportDistributed(query, graphName)
        action(bulk)
        bulk.finishImport()
    }

    fun distributedLogMessage(msg: String) {
        println(msg)
    }

    suspend fun commit(query: Query) {
        DistributedTripleStore.localStore.commit(query)
    }

    suspend fun tripleModify(query: Query, graphName: String, data: Array<ColumnIterator>, type: EModifyType) {
        DistributedTripleStore.localStore.getNamedGraph(query, graphName).modify(query, data, type)
    }

    suspend fun graphClearAll(query: Query) {
        DistributedTripleStore.localStore.getDefaultGraph(query).clear()
        for (g in DistributedTripleStore.getGraphNames()) {
            DistributedTripleStore.dropGraph(query, g)
        }
    }

    suspend fun graphOperation(query: Query, graphName: String, type: EGraphOperationType) {
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
            else -> {
                SanityCheck.checkUnreachable()
            }
        }
    }

    suspend fun tripleGet(query: Query, graphName: String, params: TripleStoreFeatureParams): IteratorBundle {
        return DistributedTripleStore.localStore.getNamedGraph(query, graphName).getIterator(query, params)
    }

    suspend fun histogramGet(query: Query, graphName: String, params: TripleStoreFeatureParams): Pair<Int, Int> {
        return DistributedTripleStore.localStore.getNamedGraph(query, graphName).getHistogram(query, params)
    }

    fun start(hostname: String = "localhost", port: Int = NETWORK_DEFAULT_PORT, bootstrap: String? = null) {
    }
}
