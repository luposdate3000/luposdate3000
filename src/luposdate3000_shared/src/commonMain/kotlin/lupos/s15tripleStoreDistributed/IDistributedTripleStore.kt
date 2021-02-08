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
package lupos.s15tripleStoreDistributed

import lupos.s04logicalOperators.IQuery

public interface IDistributedTripleStore {
    public fun reloadPartitioningScheme()
    public fun getGraphNames(): List<String>
    public fun getGraphNames(includeDefault: Boolean): List<String>
    /*suspend*/ public fun createGraph(query: IQuery, name: String): IDistributedGraph
    /*suspend*/ public fun dropGraph(query: IQuery, name: String)
    /*suspend*/ public fun clearGraph(query: IQuery, name: String)
    /*suspend*/ public fun getNamedGraph(query: IQuery, name: String): IDistributedGraph
    public fun getDefaultGraph(query: IQuery): IDistributedGraph
    /*suspend*/ public fun commit(query: IQuery)
    public fun getLocalStore(): IPersistentStoreLocal
}
