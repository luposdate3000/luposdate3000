package lupos.s05tripleStore

import lupos.s04logicalOperators.IQuery

public var tripleStoreManager: TripleStoreManager = object : TripleStoreManager() {
    public override fun resetDefaultTripleStoreLayout(): Unit = throw Exception("not implemented")
    public override fun updateDefaultTripleStoreLayout(action: (ITripleStoreDescriptionFactory) -> Unit): Unit = throw Exception("not implemented")
    public override fun commit(query: IQuery): Unit = throw Exception("not implemented")
    public override fun createGraph(query: IQuery, graphName: LuposGraphName): Unit = throw Exception("not implemented")
    public override fun createGraph(query: IQuery, graphName: LuposGraphName, action: (ITripleStoreDescriptionFactory) -> Unit): Unit = throw Exception("not implemented")
    public override fun resetGraph(query: IQuery, graphName: LuposGraphName): Unit = throw Exception("not implemented")
    public override fun resetGraph(query: IQuery, graphName: LuposGraphName, action: (ITripleStoreDescriptionFactory) -> Unit): Unit = throw Exception("not implemented")
    public override fun clearGraph(query: IQuery, graphName: LuposGraphName): Unit = throw Exception("not implemented")
    public override fun dropGraph(query: IQuery, graphName: LuposGraphName): Unit = throw Exception("not implemented")
    public override fun getGraphNames(): List<LuposGraphName> = throw Exception("not implemented")
    public override fun getGraphNames(includeDefault: Boolean): List<LuposGraphName> = throw Exception("not implemented")
    public override fun getDefaultGraph(): ITripleStoreDescription = throw Exception("not implemented")
    public override fun getGraph(graphName: LuposGraphName): ITripleStoreDescription = throw Exception("not implemented")
}
