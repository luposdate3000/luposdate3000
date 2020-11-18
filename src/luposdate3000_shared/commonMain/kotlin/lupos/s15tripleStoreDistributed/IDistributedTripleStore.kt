package lupos.s15tripleStoreDistributed

import lupos.s04logicalOperators.IQuery

interface IDistributedTripleStore {
fun reloadPartitioningScheme()
    fun getGraphNames(includeDefault: Boolean = false): List<String>
    /*suspend*/ fun createGraph(query: IQuery, name: String): IDistributedGraph
    /*suspend*/ fun dropGraph(query: IQuery, name: String)
    /*suspend*/ fun clearGraph(query: IQuery, name: String)
    /*suspend*/ fun getNamedGraph(query: IQuery, name: String): IDistributedGraph
    fun getDefaultGraph(query: IQuery): IDistributedGraph
    /*suspend*/ fun commit(query: IQuery)
    fun getLocalStore(): IPersistentStoreLocal
}
