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
package lupos.s05tripleStore

import lupos.s00misc.XMLElement
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
    public override fun initialize(): Unit = throw Exception("not implemented")
    public override fun getIndexFromXML(node: XMLElement): ITripleStoreIndexDescription = throw Exception("not implemented")
    public override fun remoteCreateGraph(query: IQuery, graphName: LuposGraphName, origin: Boolean, meta: String?): Unit = throw Exception("not implemented")
}
