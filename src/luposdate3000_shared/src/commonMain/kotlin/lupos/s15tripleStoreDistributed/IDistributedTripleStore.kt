package lupos.s15tripleStoreDistributed
import lupos.s04logicalOperators.IQuery
public interface IDistributedTripleStore {
    public fun reloadPartitioningScheme()
    public fun getGraphNames(includeDefault: Boolean = false): List<String>
    /*suspend*/ public fun createGraph(query: IQuery, name: String): IDistributedGraph
    /*suspend*/ public fun dropGraph(query: IQuery, name: String)
    /*suspend*/ public fun clearGraph(query: IQuery, name: String)
    /*suspend*/ public fun getNamedGraph(query: IQuery, name: String): IDistributedGraph
    public fun getDefaultGraph(query: IQuery): IDistributedGraph
    /*suspend*/ public fun commit(query: IQuery)
    public fun getLocalStore(): IPersistentStoreLocal
}
