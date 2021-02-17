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

import lupos.s00misc.EIndexPattern
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.EModifyType
import lupos.s00misc.Partition
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator

public typealias LuposHostname = String
public typealias LuposStoreKey = String
public typealias LuposGraphName = String

public abstract class TripleStoreIndexDescription {
    internal abstract fun assignHosts()
    internal abstract fun releaseHosts()
    internal abstract fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>>
    public abstract fun getPartitionCount(): Int
}

public class TripleStoreIndexDescriptionSimple(
    internal val idx: EIndexPattern,
) : TripleStoreIndexDescription() {
    internal var hostname: LuposHostname = ""
    internal var key: LuposStoreKey = ""
    internal override fun assignHosts() {
        val tmp = TripleStoreManager.getNextHostAndKey()
        hostname = tmp.first
        key = tmp.second
    }

    internal override fun releaseHosts() {
        TripleStoreManager.releaseHostAndKey(hostname, key)
    }

    public override fun getPartitionCount(): Int {
        return 1
    }

    internal override fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>> {
        return listOf(Pair(hostname, key))
    }
}

public class TripleStoreIndexDescriptionPartitionedByID(
    internal val idx: EIndexPattern,
    internal val partitionCount: Int,
    internal val partitionColumn: Int,
) : TripleStoreIndexDescription() {
    internal val hostnames = Array<LuposHostname>(partitionCount) { "" }
    internal val keys = Array<LuposStoreKey>(partitionCount) { "" }
    internal override fun assignHosts() {
        for (i in 0 until partitionCount) {
            val tmp = TripleStoreManager.getNextHostAndKey()
            hostnames[i] = tmp.first
            keys[i] = tmp.second
        }
    }

    internal override fun releaseHosts() {
        for (i in 0 until partitionCount) {
            TripleStoreManager.releaseHostAndKey(hostnames[i], keys[i])
        }
    }

    public override fun getPartitionCount(): Int {
        return partitionCount
    }

    internal override fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>> {
        var res = mutableListOf<Pair<LuposHostname, LuposStoreKey>>()
        for (i in 0 until partitionCount) {
            res.add(Pair(hostnames[i], keys[i]))
        }
        return res
    }
}

public class TripleStoreIndexDescriptionPartitionedByKey(
    internal val idx: EIndexPattern,
    internal val partitionCount: Int,
) : TripleStoreIndexDescription() {
    internal val hostnames = Array<LuposHostname>(partitionCount) { "" }
    internal val keys = Array<LuposStoreKey>(partitionCount) { "" }
    internal override fun assignHosts() {
        for (i in 0 until partitionCount) {
            val tmp = TripleStoreManager.getNextHostAndKey()
            hostnames[i] = tmp.first
            keys[i] = tmp.second
        }
    }

    internal override fun releaseHosts() {
        for (i in 0 until partitionCount) {
            TripleStoreManager.releaseHostAndKey(hostnames[i], keys[i])
        }
    }

    public override fun getPartitionCount(): Int {
        return 1
    }

    internal override fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>> {
        var res = mutableListOf<Pair<LuposHostname, LuposStoreKey>>()
        for (i in 0 until partitionCount) {
            res.add(Pair(hostnames[i], keys[i]))
        }
        return res
    }
}

public class TripleStoreDescription(
    internal val indices: Array<TripleStoreIndexDescription>
) {
    public fun getIndices(): Array<TripleStoreIndexDescription> {
        return indices
    }

    internal fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>> {
        var res = mutableListOf<Pair<LuposHostname, LuposStoreKey>>()
        for (idx in indices) {
            res.addAll(idx.getAllLocations())
        }
        return res
    }

    public fun modify(query: IQuery, columns: Array<ColumnIterator>, type: EModifyType) {
        throw Exception("TODO perform the modification on every index")
        throw Exception("TODO move this into the exact index too, to be consistent with the read-case")
    }

    public fun getIterator(params: Array<IAOPBase>, idx: EIndexPattern, partition: Partition): IOPBase {
        throw Exception("TODO move this into the exact index to use - and remove partition parameter")
        throw Exception("TODO get iterator from correct remote node")
    }

    public fun getHistogram(params: Array<IAOPBase>, idx: EIndexPattern): Pair<Int, Int> {
        throw Exception("TODO get histogram from correct remote node")
    }
}

public open class TripleStoreIndexDescriptionFactory {
    internal var res: TripleStoreIndexDescription = TripleStoreIndexDescriptionSimple(EIndexPatternExt.SPO)
    public fun simple(idx: EIndexPattern): TripleStoreIndexDescriptionFactory {
        res = TripleStoreIndexDescriptionSimple(idx)
        return this
    }

    public fun partitionedByID(idx: EIndexPattern, partitionCount: Int, partitionColumn: Int): TripleStoreIndexDescriptionFactory {
        res = TripleStoreIndexDescriptionPartitionedByID(idx, partitionCount, partitionColumn)
        return this
    }

    public fun partitionedByKey(idx: EIndexPattern, partitionCount: Int): TripleStoreIndexDescriptionFactory {
        res = TripleStoreIndexDescriptionPartitionedByKey(idx, partitionCount)
        return this
    }

    internal fun build(): TripleStoreIndexDescription {
        return res
    }
}

public class TripleStoreDescriptionFactory {
    internal var indices = mutableListOf<TripleStoreIndexDescription>()
    public fun addIndex(action: (TripleStoreIndexDescriptionFactory) -> Unit): TripleStoreDescriptionFactory {
        val factory = TripleStoreIndexDescriptionFactory()
        action(factory)
        indices.add(factory.build())
        return this
    }

    internal fun build(): TripleStoreDescription {
        return TripleStoreDescription(indices.toTypedArray())
    }
}

public object TripleStoreManager {
    public const val DEFAULT_GRAPH_NAME: String = ""
    internal val localStores = mutableMapOf<LuposStoreKey, TripleStoreIndex>()
    internal val metadata = mutableMapOf<LuposGraphName, TripleStoreDescription>()
    internal var hostnames = Array<LuposHostname>(1) { "localhost" }
    internal var keysOnHostname = Array(hostnames.size) { mutableListOf<LuposStoreKey>() }
    internal var localhost: LuposHostname = ""

    internal fun releaseHostAndKey(host: LuposHostname, key: LuposStoreKey) {
        keysOnHostname[hostnames.indexOf(host)].remove(key)
    }

    internal fun getNextHostAndKey(): Pair<LuposHostname, LuposStoreKey> {
        var hostidx = 0
        for (i in 1 until hostnames.size) {
            if (keysOnHostname[i].size < keysOnHostname[hostidx].size) {
                hostidx = i
            }
        }
        var key = 0
        while (keysOnHostname[hostidx].contains(key)) {
            key++
        }
        return Pair(hostnames[hostidx], "$key")
    }

    public fun initialize(hosts: List<LuposHostname>, localhost: LuposHostname) {
        this.localhost = localhost
        hostnames = hosts.toTypedArray()
        keysOnHostname = Array(hostnames.size) { mutableListOf<LuposStoreKey>() }
    }

    public fun createGraph(query: IQuery, graphName: LuposGraphName) {
        createGraph(query, graphName, {})
    }

    public fun createGraph(query: IQuery, graphName: LuposGraphName, action: (TripleStoreDescriptionFactory) -> Unit) {
        if (metadata[graphName] != null) {
            throw Exception("graph already exist")
        }
        val factory = TripleStoreDescriptionFactory()
        action(factory)
        val idx = factory.build()
        metadata[graphName] = idx
        throw Exception("TODO publish metadata to other nodes")
        throw Exception("initialize local store")
    }

    public fun resetGraph(query: IQuery, graphName: LuposGraphName) {
        resetGraph(query, graphName, {})
    }

    public fun resetGraph(query: IQuery, graphName: LuposGraphName, action: (TripleStoreDescriptionFactory) -> Unit) {
        val factory = TripleStoreDescriptionFactory()
        action(factory)
        val idx = factory.build()
        throw Exception("not implemented")
    }

    public fun clearGraph(query: IQuery, graphName: LuposGraphName) {
        throw Exception("not implemented")
    }

    public fun dropGraph(query: IQuery, graphName: LuposGraphName) {
        throw Exception("not implemented")
    }

    public fun getGraphNames(): List<LuposGraphName> {
        return getGraphNames(false)
    }

    public fun getGraphNames(includeDefault: Boolean): List<LuposGraphName> {
        val res = mutableListOf<LuposGraphName>()
        metadata.keys.forEach {
            if (it != DEFAULT_GRAPH_NAME || includeDefault) {
                res.add(it)
            }
        }
        return res
    }

    public fun getDefaultGraph(): TripleStoreDescription {
        return getGraph(DEFAULT_GRAPH_NAME)
    }

    public fun getGraph(graphName: LuposGraphName): TripleStoreDescription {
        throw Exception("not implemented")
    }
}
