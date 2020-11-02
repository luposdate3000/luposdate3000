package lupos.s15tripleStoreDistributed

import lupos.s04logicalOperators.IQuery
import lupos.s05tripleStore.ITripleStoreLocalBase

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
