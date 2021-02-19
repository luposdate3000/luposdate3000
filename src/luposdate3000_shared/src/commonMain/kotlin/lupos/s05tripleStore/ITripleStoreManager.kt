package lupos.s05tripleStore

import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.Partition
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator

public typealias LuposHostname = String
public typealias LuposStoreKey = String
public typealias LuposGraphName = String

public interface ITripleStoreIndexDescription {
    public fun getPartitionCount(): Int
}

public interface ITripleStoreDescription {
    public fun getIndices(idx: EIndexPattern): List<ITripleStoreIndexDescription>
    public fun modify(query: IQuery, columns: Array<ColumnIterator>, type: EModifyType)
    public fun getIterator(params: Array<IAOPBase>, idx: EIndexPattern, partition: Partition): IOPBase
    public fun getHistogram(params: Array<IAOPBase>, idx: EIndexPattern): Pair<Int, Int>
}

public interface ITripleStoreDescriptionFactory {
    public fun addIndex(action: (ITripleStoreIndexDescriptionFactory) -> Unit): ITripleStoreDescriptionFactory
    public fun apply(other: ITripleStoreDescriptionFactory): ITripleStoreDescriptionFactory
}

public interface ITripleStoreIndexDescriptionFactory {
    public fun simple(idx: EIndexPattern): ITripleStoreIndexDescriptionFactory
    public fun partitionedByID(idx: EIndexPattern, partitionCount: Int, partitionColumn: Int): ITripleStoreIndexDescriptionFactory
    public fun partitionedByKey(idx: EIndexPattern, partitionCount: Int): ITripleStoreIndexDescriptionFactory
}

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
