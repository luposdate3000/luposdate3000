package lupos.s15tripleStoreDistributed

import lupos.s04logicalOperators.IQuery

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
