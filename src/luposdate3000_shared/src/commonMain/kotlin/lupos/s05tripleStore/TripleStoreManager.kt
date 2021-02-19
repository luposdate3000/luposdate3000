package lupos.s05tripleStore

import lupos.s04logicalOperators.IQuery

public abstract class TripleStoreManager {
    public companion object {
        public const val DEFAULT_GRAPH_NAME: String = ""
    }

    public abstract fun resetDefaultTripleStoreLayout()
    public abstract fun updateDefaultTripleStoreLayout(action: (ITripleStoreDescriptionFactory) -> Unit)
    public abstract fun commit(query: IQuery)
    public abstract fun createGraph(query: IQuery, graphName: LuposGraphName): Unit
    public abstract fun createGraph(query: IQuery, graphName: LuposGraphName, action: (ITripleStoreDescriptionFactory) -> Unit): Unit
    public abstract fun resetGraph(query: IQuery, graphName: LuposGraphName): Unit
    public abstract fun resetGraph(query: IQuery, graphName: LuposGraphName, action: (ITripleStoreDescriptionFactory) -> Unit): Unit
    public abstract fun clearGraph(query: IQuery, graphName: LuposGraphName): Unit
    public abstract fun dropGraph(query: IQuery, graphName: LuposGraphName): Unit
    public abstract fun getGraphNames(): List<LuposGraphName>
    public abstract fun getGraphNames(includeDefault: Boolean): List<LuposGraphName>
    public abstract fun getDefaultGraph(): ITripleStoreDescription
    public abstract fun getGraph(graphName: LuposGraphName): ITripleStoreDescription
}
