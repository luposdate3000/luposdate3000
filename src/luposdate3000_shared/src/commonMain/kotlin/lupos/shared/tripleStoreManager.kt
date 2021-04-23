/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.shared

import lupos.operator.logical.IQuery

public abstract class TripleStoreManager {
    public companion object {
        public const val DEFAULT_GRAPH_NAME: String = ""
    }

    public abstract fun close()
    public abstract fun delete()
    public abstract fun getLocalhost(): LuposHostname
    public abstract fun getPartitionMode(): EPartitionMode
    public abstract fun debugAllLocalStoreContent()
    public abstract fun remoteDropGraph(query: IQuery, graphName: LuposGraphName, origin: Boolean): Unit
    public abstract fun remoteClearGraph(query: IQuery, graphName: LuposGraphName, origin: Boolean): Unit
    public abstract fun remoteModify(query: IQuery, key: String, mode: EModifyType, idx: EIndexPattern, stream: IMyInputStream)
    public abstract fun remoteModifySorted(query: IQuery, key: String, mode: EModifyType, idx: EIndexPattern, stream: IMyInputStream)
    public abstract fun getIndexFromXML(node: XMLElement): ITripleStoreIndexDescription
    public abstract fun resetDefaultTripleStoreLayout()
    public abstract fun updateDefaultTripleStoreLayout(action: (ITripleStoreDescriptionFactory) -> Unit)
    public abstract fun commit(query: IQuery)
    public abstract fun remoteCommit(query: IQuery, origin: Boolean)
    public abstract fun createGraph(query: IQuery, graphName: LuposGraphName): Unit
    public abstract fun resetGraph(query: IQuery, graphName: LuposGraphName): Unit
    public abstract fun clearGraph(query: IQuery, graphName: LuposGraphName): Unit
    public abstract fun dropGraph(query: IQuery, graphName: LuposGraphName): Unit
    public abstract fun getGraphNames(): List<LuposGraphName>
    public abstract fun getGraphNames(includeDefault: Boolean): List<LuposGraphName>
    public abstract fun getDefaultGraph(): ITripleStoreDescription
    public abstract fun getGraph(graphName: LuposGraphName): ITripleStoreDescription
    public abstract fun initialize()
    public abstract fun remoteCreateGraph(query: IQuery, graphName: LuposGraphName, origin: Boolean, meta: String?)
}

public var tripleStoreManager: TripleStoreManager = object : TripleStoreManager() {
    public override fun close(): Unit = throw Exception("not implemented")
    public override fun delete(): Unit = throw Exception("not implemented")
    public override fun getLocalhost(): LuposHostname = throw Exception("not implemented")
    public override fun getPartitionMode(): EPartitionMode = throw Exception("not implemented")
    public override fun debugAllLocalStoreContent(): Unit = throw Exception("not implemented")
    public override fun resetDefaultTripleStoreLayout(): Unit = throw Exception("not implemented")
    public override fun updateDefaultTripleStoreLayout(action: (ITripleStoreDescriptionFactory) -> Unit): Unit = throw Exception("not implemented")
    public override fun commit(query: IQuery): Unit = throw Exception("not implemented")
    public override fun remoteCommit(query: IQuery, origin: Boolean): Unit = throw Exception("not implemented")
    public override fun remoteDropGraph(query: IQuery, graphName: LuposGraphName, origin: Boolean): Unit = throw Exception("not implemented")
    public override fun remoteClearGraph(query: IQuery, graphName: LuposGraphName, origin: Boolean): Unit = throw Exception("not implemented")
    public override fun createGraph(query: IQuery, graphName: LuposGraphName): Unit = throw Exception("not implemented")
    public override fun resetGraph(query: IQuery, graphName: LuposGraphName): Unit = throw Exception("not implemented")
    public override fun clearGraph(query: IQuery, graphName: LuposGraphName): Unit = throw Exception("not implemented")
    public override fun dropGraph(query: IQuery, graphName: LuposGraphName): Unit = throw Exception("not implemented")
    public override fun getGraphNames(): List<LuposGraphName> = throw Exception("not implemented")
    public override fun getGraphNames(includeDefault: Boolean): List<LuposGraphName> = throw Exception("not implemented")
    public override fun getDefaultGraph(): ITripleStoreDescription = throw Exception("not implemented")
    public override fun getGraph(graphName: LuposGraphName): ITripleStoreDescription = throw Exception("not implemented")
    public override fun initialize(): Unit = throw Exception("not implemented")
    public override fun getIndexFromXML(node: XMLElement): ITripleStoreIndexDescription = throw Exception("not implemented")
    public override fun remoteCreateGraph(query: IQuery, graphName: LuposGraphName, origin: Boolean, meta: String?): Unit = throw Exception("not implemented")
    public override fun remoteModify(query: IQuery, key: String, mode: EModifyType, idx: EIndexPattern, stream: IMyInputStream): Unit = throw Exception("not implemented")
    public override fun remoteModifySorted(query: IQuery, key: String, mode: EModifyType, idx: EIndexPattern, stream: IMyInputStream): Unit = throw Exception("not implemented")
}
