package lupos.s15tripleStoreDistributed

import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.Partition
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s05tripleStore.ITripleStoreBulkImport
import lupos.s05tripleStore.ITripleStoreLocalBase
import lupos.s09physicalOperators.IPOPBase

var distributedTripleStore: IDistributedTripleStore = object : IDistributedTripleStore {
    override fun getGraphNames(includeDefault: Boolean): List<String> {
        throw Exception("unreachable")
    }

    suspend override fun createGraph(query: IQuery, name: String): IDistributedGraph {
        throw Exception("unreachable")
    }

    suspend override fun dropGraph(query: IQuery, name: String) {
        throw Exception("unreachable")
    }

    suspend override fun clearGraph(query: IQuery, name: String) {
        throw Exception("unreachable")
    }

    suspend override fun getNamedGraph(query: IQuery, name: String): IDistributedGraph {
        throw Exception("unreachable")
    }

    override fun getDefaultGraph(query: IQuery): IDistributedGraph {
        throw Exception("unreachable")
    }

    suspend override fun commit(query: IQuery) {
        throw Exception("unreachable")
    }

    override fun getLocalStore(): IPersistentStoreLocal {
        throw Exception("unreachable")
    }
}
