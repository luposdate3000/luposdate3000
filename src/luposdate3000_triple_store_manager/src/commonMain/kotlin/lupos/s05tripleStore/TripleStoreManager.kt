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
import lupos.s00misc.EIndexPatternHelper
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s09physicalOperators.POPBase
import kotlin.jvm.JvmField

public abstract class TripleStoreIndexDescription(
    @JvmField internal val idx: EIndexPattern,
) : ITripleStoreIndexDescription {
    @JvmField
    internal var tripleStoreDescription: TripleStoreDescription = TripleStoreDescriptionDummy
    internal abstract fun assignHosts()
    internal abstract fun releaseHosts()
    internal abstract fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>>
    internal fun getIndexWithMaximumPartitions(max_partitions: Int?, column: Int): ITripleStoreIndexDescription {
        var count = -1
        var currentindex: TripleStoreIndexDescription = this
        for (index in tripleStoreDescription.indices) {
            if (index.idx == idx &&
                (
                    index is TripleStoreIndexDescriptionPartitionedByID &&
                        (max_partitions == null || index.partitionCount < max_partitions) &&
                        index.partitionCount > count &&
                        index.partitionColumn == column
                    ) || (
                    index.getPartitionCount() == 1 &&
                        1 < count
                    )
            ) {
                count = index.getPartitionCount()
                currentindex = index
            }
        }
        if (count > -1) {
            return currentindex
        }
        throw Exception("no matching index found")
    }
}

public class TripleStoreIndexDescriptionSimple(
    idx: EIndexPattern,
) : TripleStoreIndexDescription(idx) {
    internal var hostname: LuposHostname = ""
    internal var key: LuposStoreKey = ""
    internal override fun assignHosts() {
        val tmp = (tripleStoreManager as TripleStoreManagerImpl).getNextHostAndKey()
        hostname = tmp.first
        key = tmp.second
    }

    internal override fun releaseHosts() {
        (tripleStoreManager as TripleStoreManagerImpl).releaseHostAndKey(hostname, key)
    }

    public override fun getPartitionCount(): Int {
        return 1
    }

    internal override fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>> {
        return listOf(Pair(hostname, key))
    }
}

public class TripleStoreIndexDescriptionPartitionedByID(
    idx: EIndexPattern,
    @JvmField internal val partitionCount: Int,
    @JvmField internal val partitionColumn: Int,
) : TripleStoreIndexDescription(idx) {
    internal val hostnames = Array<LuposHostname>(partitionCount) { "" }
    internal val keys = Array<LuposStoreKey>(partitionCount) { "" }
    internal override fun assignHosts() {
        for (i in 0 until partitionCount) {
            val tmp = (tripleStoreManager as TripleStoreManagerImpl).getNextHostAndKey()
            hostnames[i] = tmp.first
            keys[i] = tmp.second
        }
    }

    internal override fun releaseHosts() {
        for (i in 0 until partitionCount) {
            (tripleStoreManager as TripleStoreManagerImpl).releaseHostAndKey(hostnames[i], keys[i])
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
    idx: EIndexPattern,
    @JvmField internal val partitionCount: Int,
) : TripleStoreIndexDescription(idx) {
    internal val hostnames = Array<LuposHostname>(partitionCount) { "" }
    internal val keys = Array<LuposStoreKey>(partitionCount) { "" }
    internal override fun assignHosts() {
        for (i in 0 until partitionCount) {
            val tmp = (tripleStoreManager as TripleStoreManagerImpl).getNextHostAndKey()
            hostnames[i] = tmp.first
            keys[i] = tmp.second
        }
    }

    internal override fun releaseHosts() {
        for (i in 0 until partitionCount) {
            (tripleStoreManager as TripleStoreManagerImpl).releaseHostAndKey(hostnames[i], keys[i])
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

public class POPTripleStoreIterator(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField internal var tripleStoreIndexDescription: ITripleStoreIndexDescription,
    children: Array<IOPBase>,
) : POPBase(
    query,
    projectedVariables,
    EOperatorIDExt.POPTripleStoreIterator,
    "POPTripleStoreIterator",
    children,
    ESortPriorityExt.ANY_PROVIDED_VARIABLE
) {
    @JvmField
    public var partitionColumn: String? = null

    @JvmField
    public var hasSplitFromStore: Boolean = false
    public fun changeToIndexWithMaximumPartitions(max_partitions: Int?, column: String): Int {
        var partition_column = -1
        for (i in 0 until 3) {
            val c = children[i]
            if (c is AOPVariable && c.name == column) {
                partition_column = EIndexPatternHelper.tripleIndicees[(tripleStoreIndexDescription as TripleStoreIndexDescription).idx][i]
                break
            }
        }
        if (partition_column > -1) {
            tripleStoreIndexDescription = (tripleStoreIndexDescription as TripleStoreIndexDescription).getIndexWithMaximumPartitions(max_partitions, partition_column)
            val count = tripleStoreIndexDescription.getPartitionCount()
            partitionColumn = column
            return count
        } else {
            throw Exception("no matching index found")
        }
    }

    override fun getPartitionCount(variable: String): Int {
        var count = tripleStoreIndexDescription.getPartitionCount()

        if (count > 1) {
            SanityCheck.check { (tripleStoreIndexDescription as TripleStoreIndexDescriptionPartitionedByID).partitionCount == count }
            for (i in 0 until 3) {
                val c = children[i]
                if (c is AOPVariable && c.name == variable) {
                    val currentindex = tripleStoreIndexDescription
                    if (currentindex is TripleStoreIndexDescriptionPartitionedByID &&
                        currentindex.partitionColumn == EIndexPatternHelper.tripleIndicees[currentindex.idx][i]
                    ) {
                        return count
                    }
                    break
                }
            }
        }
        return 1
    }

    public override fun cloneOP(): IOPBase = POPTripleStoreIterator(query, projectedVariables, tripleStoreIndexDescription, children)
}

internal val TripleStoreDescriptionDummy = TripleStoreDescription(arrayOf<TripleStoreIndexDescription>())

public class TripleStoreDescription(
    @JvmField internal val indices: Array<TripleStoreIndexDescription>
) : ITripleStoreDescription {
    public override fun getIndices(idx: EIndexPattern): List<ITripleStoreIndexDescription> {
        var res = mutableListOf<ITripleStoreIndexDescription>()
        for (index in indices) {
            if (index.idx == idx) {
                res.add(index)
            }
        }
        return res
    }

    internal fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>> {
        var res = mutableListOf<Pair<LuposHostname, LuposStoreKey>>()
        for (idx in indices) {
            res.addAll(idx.getAllLocations())
        }
        return res
    }

    public override fun modify(query: IQuery, columns: Array<ColumnIterator>, type: EModifyType) {
        val bufSize = 100
        val buf = IntArray(bufSize)
        val bufSize = 100
        val bufLimit = (bufSize / 3) * 3
        loop@ while (true) {
            var i = 0
            while (i < bufLimit) {
                buf[i++] = columns[0].next()
                buf[i++] = columns[1].next()
                buf[i++] = columns[2].next()
                if (buf[i - 1] == ResultSetDictionaryExt.nullValue) {
                    i -= 3
                    break
                }
            }
            for (index in indices) {
                for (store in index.getAllLocations()) {
                    if (store.first == localhost) {
                        val tmp = tripleStoreManager.localStores[store.second]!!
                        if (type == EModifyTypeExt.Insert) {
                            tmp.insertAsBulk(buf, EIndexPatternHelper.tripleIndicees[index.idx], i)
                        } else {
                            tmp.removeAsBulk(buf, EIndexPatternHelper.tripleIndicees[index.idx], i)
                        }
                    } else {
                        throw Exception("modify send to other nodes")
                    }
                }
            }
        }
    }

    public override fun getIterator(query: IQuery, params: Array<IAOPBase>, idx: EIndexPattern): IOPBase {
        for (index in indices) {
            if (index.idx == idx) {
                val projectedVariables = mutableListOf<String>()
                for (param in params) {
                    if (param is AOPVariable) {
                        projectedVariables.add(param.name)
                    }
                }
                return POPTripleStoreIterator(query, projectedVariables, index, params)
            }
        }
        throw Exception("no valid index found")
    }

    public override fun getHistogram(params: Array<IAOPBase>, idx: EIndexPattern): Pair<Int, Int> {
        for (index in indices) {
            if (index.idx == idx) {
                var first = 0
                var second = 0
                for (store in index.getAllLocations()) {
                    if (store.first == localhost) {
                        val tmp = tripleStoreManager.localStores[store.second]!!.getHistogram(params)
                        first += tmp.first
                        second += tmp.second
                    } else {
                        throw Exception("getHistogram send to remote node")
                    }
                }
                return Pair(first, second)
            }
        }
    }
}

public open class TripleStoreIndexDescriptionFactory : ITripleStoreIndexDescriptionFactory {
    @JvmField
    internal var res: TripleStoreIndexDescription = TripleStoreIndexDescriptionSimple(EIndexPatternExt.SPO)
    public override fun simple(idx: EIndexPattern): TripleStoreIndexDescriptionFactory {
        res = TripleStoreIndexDescriptionSimple(idx)
        return this
    }

    public override fun partitionedByID(idx: EIndexPattern, partitionCount: Int, partitionColumn: Int): TripleStoreIndexDescriptionFactory {
        res = TripleStoreIndexDescriptionPartitionedByID(idx, partitionCount, partitionColumn)
        return this
    }

    public override fun partitionedByKey(idx: EIndexPattern, partitionCount: Int): TripleStoreIndexDescriptionFactory {
        res = TripleStoreIndexDescriptionPartitionedByKey(idx, partitionCount)
        return this
    }

    internal fun build(): TripleStoreIndexDescription {
        return res
    }
}

public class TripleStoreDescriptionFactory : ITripleStoreDescriptionFactory {
    @JvmField
    internal var indices = mutableListOf<TripleStoreIndexDescription>()
    public override fun addIndex(action: (ITripleStoreIndexDescriptionFactory) -> Unit): TripleStoreDescriptionFactory {
        val factory = TripleStoreIndexDescriptionFactory()
        action(factory)
        val index = factory.build()
        indices.add(index)
        return this
    }

    public override fun apply(other: ITripleStoreDescriptionFactory): ITripleStoreDescriptionFactory {
        indices.clear()
        indices.addAll((other as TripleStoreDescriptionFactory).indices)
        return this
    }

    internal fun build(): TripleStoreDescription {
        val store = TripleStoreDescription(indices.toTypedArray())
        for (index in indices) {
            index.tripleStoreDescription = store
        }
        return store
    }
}

public class TripleStoreManagerImpl(
    @JvmField internal var hostnames: Array<LuposHostname>,
    @JvmField internal var localhost: LuposHostname,
) : TripleStoreManager() {
    @JvmField
    internal val bufferManager: BufferManager = BufferManagerExt.getBuffermanager("stores")

    @JvmField
    internal val localStores = mutableMapOf<LuposStoreKey, TripleStoreIndex>()

    @JvmField
    internal val metadata = mutableMapOf<LuposGraphName, TripleStoreDescription>()

    @JvmField
    internal var keysOnHostname = Array(hostnames.size) { mutableListOf<LuposStoreKey>() }
    internal lateinit var defaultTripleStoreLayout: TripleStoreDescriptionFactory

    init {
        resetDefaultTripleStoreLayout()
    }

    public override fun resetDefaultTripleStoreLayout() {
        defaultTripleStoreLayout = TripleStoreDescriptionFactory() //
            .addIndex { it.partitionedByID(idx = EIndexPatternExt.SPO, partitionCount = 4, partitionColumn = 1) } //
            .addIndex { it.partitionedByID(idx = EIndexPatternExt.SPO, partitionCount = 4, partitionColumn = 2) } //
            .addIndex { it.partitionedByID(idx = EIndexPatternExt.SOP, partitionCount = 4, partitionColumn = 1) } //
            .addIndex { it.partitionedByID(idx = EIndexPatternExt.SOP, partitionCount = 4, partitionColumn = 2) } //
            .addIndex { it.partitionedByID(idx = EIndexPatternExt.PSO, partitionCount = 4, partitionColumn = 1) } //
            .addIndex { it.partitionedByID(idx = EIndexPatternExt.PSO, partitionCount = 4, partitionColumn = 2) } //
            .addIndex { it.partitionedByID(idx = EIndexPatternExt.POS, partitionCount = 4, partitionColumn = 1) } //
            .addIndex { it.partitionedByID(idx = EIndexPatternExt.POS, partitionCount = 4, partitionColumn = 2) } //
            .addIndex { it.partitionedByID(idx = EIndexPatternExt.OSP, partitionCount = 4, partitionColumn = 1) } //
            .addIndex { it.partitionedByID(idx = EIndexPatternExt.OSP, partitionCount = 4, partitionColumn = 2) } //
            .addIndex { it.partitionedByID(idx = EIndexPatternExt.OPS, partitionCount = 4, partitionColumn = 1) } //
            .addIndex { it.partitionedByID(idx = EIndexPatternExt.OPS, partitionCount = 4, partitionColumn = 2) }
    }

    public override fun updateDefaultTripleStoreLayout(action: (ITripleStoreDescriptionFactory) -> Unit) {
        val factory = TripleStoreDescriptionFactory()
        action(factory)
        defaultTripleStoreLayout = factory
    }

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

    public override fun createGraph(query: IQuery, graphName: LuposGraphName) {
        createGraph(query, graphName, { it.apply(defaultTripleStoreLayout) })
    }

    public override fun createGraph(query: IQuery, graphName: LuposGraphName, action: (ITripleStoreDescriptionFactory) -> Unit) {
        if (metadata[graphName] != null) {
            throw Exception("graph already exist")
        }
        val factory = TripleStoreDescriptionFactory()
        action(factory)
        val graph = factory.build()
        metadata[graphName] = graph
        for (index in graph.indices) {
            index.assignHosts()
            for (store in index.getAllLocations()) {
                if (store.first == localhost) {
                    var page: Int = 0
                    bufferManager.createPage { byteArray, pageid ->
                        page = pageid
                    }
                    localStores[store.second] = TripleStoreIndexIDTriple(page, false)
                } else {
                    throw Exception("createGraph on other nodes")
                }
            }
        }
    }

    public override fun resetGraph(query: IQuery, graphName: LuposGraphName) {
        dropGraph(query, graphName)
        createGraph(query, graphName, { it.apply(defaultTripleStoreLayout) })
    }

    public override fun resetGraph(query: IQuery, graphName: LuposGraphName, action: (ITripleStoreDescriptionFactory) -> Unit) {
        dropGraph(query, graphName)
        createGraph(query, graphName, action)
    }

    public override fun clearGraph(query: IQuery, graphName: LuposGraphName) {
        val graph = metadata[graphName]
        if (graph != null) {
            for (index in graph.indices) {
                for (store in index.getAllLocations()) {
                    if (store.first == localhost) {
                        localStores[store.second]!!.clear()
                    } else {
                        throw Exception("clearGraph on other nodes")
                    }
                }
            }
        }
    }

    public override fun dropGraph(query: IQuery, graphName: LuposGraphName) {
        val graph = metadata[graphName]
        if (graph != null) {
            for (index in graph.indices) {
                for (store in index.getAllLocations()) {
                    if (store.first == localhost) {
                        localStores[store.second]!!.clear()
                        val page = localStores[store.second]!!.store_root_page_id
                        buffermanager.deletePage(page)
                        localStores.remove(store.second)
                    } else {
                        throw Exception("deleteGraph on other nodes")
                    }
                }
            }
        }
    }

    public override fun getGraphNames(): List<LuposGraphName> {
        return getGraphNames(false)
    }

    public override fun getGraphNames(includeDefault: Boolean): List<LuposGraphName> {
        val res = mutableListOf<LuposGraphName>()
        metadata.keys.forEach {
            if (it != DEFAULT_GRAPH_NAME || includeDefault) {
                res.add(it)
            }
        }
        return res
    }

    public override fun getDefaultGraph(): TripleStoreDescription {
        return getGraph(DEFAULT_GRAPH_NAME)
    }

    public override fun getGraph(graphName: LuposGraphName): TripleStoreDescription {
        return metadata[graphName]
    }

    public override fun commit(query: IQuery) {
        val graph = metadata[graphName]
        if (graph != null) {
            for (index in graph.indices) {
                for (store in index.getAllLocations()) {
                    if (store.first == localhost) {
                        localStores[store.second]!!.commit(query)
                    } else {
                        throw Exception("commit on other nodes")
                    }
                }
            }
        }
    }
}
