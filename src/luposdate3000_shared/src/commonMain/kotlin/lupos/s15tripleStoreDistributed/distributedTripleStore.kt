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

@file:lupos.ProguardKeepAnnotation
package lupos.s15tripleStoreDistributed
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IQuery
import kotlin.jvm.JvmField
@JvmField
public var distributedTripleStore: IDistributedTripleStore = DummyDistributedTripleStore()
public class DummyDistributedTripleStore public constructor() : IDistributedTripleStore {
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
    override fun getGraphNames(): List<String> {
        return getGraphNames(false)
    }
}
