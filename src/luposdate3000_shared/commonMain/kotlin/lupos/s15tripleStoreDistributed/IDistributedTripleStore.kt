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

interface IDistributedTripleStore {
    fun getGraphNames(includeDefault: Boolean = false): List<String>
    suspend fun createGraph(query: IQuery, name: String): IDistributedGraph
    suspend fun dropGraph(query: IQuery, name: String)
    suspend fun clearGraph(query: IQuery, name: String)
    suspend fun getNamedGraph(query: IQuery, name: String): IDistributedGraph
    fun getDefaultGraph(query: IQuery): IDistributedGraph
    suspend fun commit(query: IQuery)
    fun getLocalStore(): IPersistentStoreLocal
}
