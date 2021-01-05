package lupos.s15tripleStoreDistributed
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IQuery
public var distributedTripleStore: IDistributedTripleStore = object : IDistributedTripleStore {
    override fun reloadPartitioningScheme() {
        SanityCheck.checkUnreachable()
    }
    override fun getGraphNames(includeDefault: Boolean): List<String> {
        SanityCheck.checkUnreachable()
    }
    override /*suspend*/ fun createGraph(query: IQuery, name: String): IDistributedGraph {
        SanityCheck.checkUnreachable()
    }
    override /*suspend*/ fun dropGraph(query: IQuery, name: String) {
        SanityCheck.checkUnreachable()
    }
    override /*suspend*/ fun clearGraph(query: IQuery, name: String) {
        SanityCheck.checkUnreachable()
    }
    override /*suspend*/ fun getNamedGraph(query: IQuery, name: String): IDistributedGraph {
        SanityCheck.checkUnreachable()
    }
    override fun getDefaultGraph(query: IQuery): IDistributedGraph {
        SanityCheck.checkUnreachable()
    }
    override /*suspend*/ fun commit(query: IQuery) {
        SanityCheck.checkUnreachable()
    }
    override fun getLocalStore(): IPersistentStoreLocal {
        SanityCheck.checkUnreachable()
    }
}
