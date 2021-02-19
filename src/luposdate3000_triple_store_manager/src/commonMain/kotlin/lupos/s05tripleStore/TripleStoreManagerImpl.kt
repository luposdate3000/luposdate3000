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

import lupos.s00misc.EIndexPatternExt
import lupos.s01io.BufferManager
import lupos.s01io.BufferManagerExt
import lupos.s04logicalOperators.IQuery
import kotlin.jvm.JvmField

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

    public override fun initialize() {
        resetDefaultTripleStoreLayout()
        val graph = defaultTripleStoreLayout.build()
        metadata[TripleStoreManager.DEFAULT_GRAPH_NAME] = graph
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
                        bufferManager.deletePage(page)
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

    public override fun getIndexFromXML(node: XMLElement): TripleStoreIndexDescription {
        val graph = metadata[node.attributes["graphName"]]!!
        val idx = EIndexPatternExt.names.indexOf(node.attributes["pattern"]!!)
        for (index in graph.indices) {
            if (index.hasPattern(idx)) {
                when (index) {
                    is TripleStoreIndexDescriptionPartitionedByID -> {
                        if (node.attributes["type"] == "TripleStoreIndexDescriptionPartitionedByID") {
                            if (index.partitionCount == node.attributes["partitionCount"]!!.toInt()) {
                                if (index.partitionColumn == node.attributes["partitionColumn"]!!.toInt()) {
                                    return index
                                }
                            }
                        }
                    }
                    is TripleStoreIndexDescriptionPartitionedByKey -> {
                        if (node.attributes["type"] == "TripleStoreIndexDescriptionPartitionedByKey") {
                            if (index.partitionCount == node.attributes["partitionCount"]!!.toInt()) {
                                return index
                            }
                        }
                    }
                    is TripleStoreIndexDescriptionSimple -> {
                        if (node.attributes["type"] == "TripleStoreIndexDescriptionSimple") {
                            return index
                        }
                    }
                    else -> throw Exception("unexpected type")
                }
            }
        }
        throw Exception("desired index not found")
    }

    public override fun getGraph(graphName: LuposGraphName): TripleStoreDescription {
        return metadata[graphName]!!
    }

    public override fun commit(query: IQuery) {
        for (graph in metadata.values) {
            for (index in graph.indices) {
                for (store in index.getAllLocations()) {
                    if (store.first == localhost) {
                        localStores[store.second]!!.flush()
                    } else {
                        throw Exception("commit on other nodes")
                    }
                }
            }
        }
    }
}
