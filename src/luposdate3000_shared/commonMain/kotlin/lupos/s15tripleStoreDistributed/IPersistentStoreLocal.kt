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

interface IPersistentStoreLocal {
    fun getGraphNames(includeDefault: Boolean = false): List<String>
    fun createGraph(query: IQuery, name: String): ITripleStoreLocalBase
    suspend fun dropGraph(query: IQuery, name: String)
    suspend fun clearGraph(query: IQuery, name: String)
    suspend fun getNamedGraph(query: IQuery, name: String, create: Boolean = false): ITripleStoreLocalBase
    suspend fun getDefaultGraph(query: IQuery): ITripleStoreLocalBase
    suspend fun commit(query: IQuery)
    suspend fun safeToFolder()
    suspend fun loadFromFolder()
}
