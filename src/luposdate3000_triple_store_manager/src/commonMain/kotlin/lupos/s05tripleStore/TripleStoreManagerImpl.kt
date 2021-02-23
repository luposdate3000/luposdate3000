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
import lupos.s00misc.EModifyTypeExt
import lupos.s00misc.File
import lupos.s00misc.IMyInputStream
import lupos.s00misc.XMLElement
import lupos.s00misc.communicationHandler
import lupos.s01io.BufferManager
import lupos.s01io.BufferManagerExt
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.Query
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
    internal var keysOnHostname = Array(hostnames.size) { mutableListOf<LuposStoreKey>() } // TODO initialize based on "metadata" on each restart
    internal lateinit var defaultTripleStoreLayout: TripleStoreDescriptionFactory

    public override fun debugAllLocalStoreContent() {
        File("${localhost.replace(":", "_")}.metadata").printWriter { out ->
            for ((k, v) in metadata) {
                out.println("graphname : '$k'")
                val meta = v.toMetaString().split("|")
                for (s in meta) {
                    out.println("    $s")
                }
            }
            out.flush()
        }
        for ((k, v) in localStores) {
            File("${localhost.replace(":", "_")}_$k.store").printWriter { out ->
                val query = Query()
                val iter = v.getIterator(query, IntArray(0), listOf("s", "p", "o"))
                val rowiter = iter.rows
                var off = rowiter.next()
                while (off > -1) {
                    var s = ""
                    for (i in 0 until 3) {
//                        s += query.getDictionary().getValue(rowiter.buf[off + i]).valueToString()
                        s += "0x${rowiter.buf[off + i].toString(16)}"
                        if (i < 2) {
                            s += " "
                        }
                    }
                    out.println(s)
                    off = rowiter.next()
                }
                out.flush()
            }
        }
    }

    public override fun initialize() {
        resetDefaultTripleStoreLayout()
    }

    public override fun resetDefaultTripleStoreLayout() {
        when (0) {
            0 -> { // use partitions as default
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
                    .addIndex { it.partitionedByID(idx = EIndexPatternExt.OPS, partitionCount = 4, partitionColumn = 2) } //
            }
            1 -> { // use hashing as default
                defaultTripleStoreLayout = TripleStoreDescriptionFactory() //
                    .addIndex { it.partitionedByKey(idx = EIndexPatternExt.SPO, partitionCount = 4) } //
                    .addIndex { it.partitionedByKey(idx = EIndexPatternExt.S_PO, partitionCount = 4) } //
                    .addIndex { it.partitionedByKey(idx = EIndexPatternExt.P_SO, partitionCount = 4) } //
                    .addIndex { it.partitionedByKey(idx = EIndexPatternExt.O_SP, partitionCount = 4) } //
                    .addIndex { it.partitionedByKey(idx = EIndexPatternExt.SP_O, partitionCount = 4) } //
                    .addIndex { it.partitionedByKey(idx = EIndexPatternExt.SO_P, partitionCount = 4) } //
                    .addIndex { it.partitionedByKey(idx = EIndexPatternExt.PO_S, partitionCount = 4) } //
            }
        }
    }

    public override fun updateDefaultTripleStoreLayout(action: (ITripleStoreDescriptionFactory) -> Unit) {
        val factory = TripleStoreDescriptionFactory()
        action(factory)
        defaultTripleStoreLayout = factory
    }

    internal fun getNextHostAndKey(): Pair<LuposHostname, LuposStoreKey> {
        var hostidx = 0
        for (i in 1 until hostnames.size) {
            if (keysOnHostname[i].size < keysOnHostname[hostidx].size) {
                hostidx = i
            }
        }
        var key = 0
        while (keysOnHostname[hostidx].contains("$key")) {
            key++
        }
        println(keysOnHostname.map { it })
        keysOnHostname[hostidx].add("$key")
        return Pair(hostnames[hostidx], "$key")
    }

    public override fun createGraph(query: IQuery, graphName: LuposGraphName) {
        createGraph(query, graphName, { it.apply(defaultTripleStoreLayout) })
    }

    public override fun remoteModify(query: IQuery, key: String, mode: EModifyType, idx: EIndexPattern, stream: IMyInputStream) {
        val store = localStores[key]!!
        var count = stream.readInt()
        val buf = IntArray(count)
        for (i in 0 until count) {
            buf[i] = stream.readInt()
        }
        if (mode == EModifyTypeExt.INSERT) {
            store.insertAsBulk(buf, EIndexPatternHelper.tripleIndicees[idx], count)
        } else {
            store.removeAsBulk(buf, EIndexPatternHelper.tripleIndicees[idx], count)
        }
    }

    public override fun remoteCreateGraph(query: IQuery, graphName: LuposGraphName, origin: Boolean, meta: String?) {
        println("remoteCreateGraph $localhost '$graphName'")
        if (origin) {
            createGraph(query, graphName)
        } else {
            val graph = TripleStoreDescription(meta!!)
            metadata[graphName] = graph
            createGraphShared(graph)
        }
    }

    internal inline fun createGraphShared(graph: TripleStoreDescription) {
        println("createGraphShared $localhost")
        for (index in graph.indices) {
            println("createGraphShared -- $localhost :: ${EIndexPatternExt.names[index.idx_set[0]]}")
            for (store in index.getAllLocations()) {
                println("createGraphShared -- $localhost :: ${store.first} ${store.second}")
                if (store.first == localhost) {
                    var page: Int = 0
                    bufferManager.createPage { byteArray, pageid ->
                        page = pageid
                    }
                    println("create index $localhost ${store.second}")
                    localStores[store.second] = TripleStoreIndexIDTriple(page, false)
                }
            }
        }
    }

    public override fun createGraph(query: IQuery, graphName: LuposGraphName, action: (ITripleStoreDescriptionFactory) -> Unit) {
        println("createGraph $localhost '$graphName'")
        if (metadata[graphName] != null) {
            throw Exception("graph already exist")
        }
        val factory = TripleStoreDescriptionFactory()
        action(factory)
        val graph = factory.build()
        metadata[graphName] = graph
        for (index in graph.indices) {
            index.assignHosts()
        }
        createGraphShared(graph)
        val metadataStr = graph.toMetaString()
        for (hostname in hostnames) {
            if (hostname != localhost) {
                communicationHandler.sendData(
                    hostname, "/distributed/graph/create",
                    mapOf(
                        "name" to graphName,
                        "origin" to "false",
                        "metadata" to metadataStr,
                    )
                )
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
        remoteClearGraph(query, graphName, true)
    }

    public override fun remoteClearGraph(query: IQuery, graphName: LuposGraphName, origin: Boolean) {
        if (graphName == DEFAULT_GRAPH_NAME && metadata[graphName] == null) {
            createGraph(query, graphName)
        } else {
            val graph = metadata[graphName]
            if (graph != null) {
                for (index in graph.indices) {
                    for (store in index.getAllLocations()) {
                        if (store.first == localhost) {
                            localStores[store.second]!!.clear()
                        } else {
                            if (origin) {
                                communicationHandler.sendData(
                                    store.first, "/distributed/graph/clear",
                                    mapOf(
                                        "origin" to "false",
                                        "name" to graphName
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    public override fun dropGraph(query: IQuery, graphName: LuposGraphName) {
        remoteDropGraph(query, graphName, true)
    }

    public override fun remoteDropGraph(query: IQuery, graphName: LuposGraphName, origin: Boolean) {
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
                        if (origin) {
                            communicationHandler.sendData(
                                store.first, "/distributed/graph/drop",
                                mapOf(
                                    "origin" to "false",
                                    "name" to graphName
                                )
                            )
                        }
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
        if (includeDefault) {
            res.add(DEFAULT_GRAPH_NAME)
        }
        metadata.keys.forEach {
            if (it != DEFAULT_GRAPH_NAME) {
                res.add(it)
            }
        }
        return res
    }

    public override fun getDefaultGraph(): TripleStoreDescription {
        return getGraph(DEFAULT_GRAPH_NAME)
    }

    public override fun getIndexFromXML(node: XMLElement): ITripleStoreIndexDescription {
        println("node=${node.toPrettyString()}")
        val node2 = node["TripleStoreIndexDescription"]!!
        val graph = metadata[node2.attributes["graphName"]]!!
        val idx = EIndexPatternExt.names.indexOf(node2.attributes["pattern"]!!)
        for (index in graph.indices) {
            if (index.hasPattern(idx)) {
                when (index) {
                    is TripleStoreIndexDescriptionPartitionedByID -> {
                        if (node2.attributes["type"] == "TripleStoreIndexDescriptionPartitionedByID") {
                            if (index.partitionCount == node2.attributes["partitionCount"]!!.toInt()) {
                                if (index.partitionColumn == node2.attributes["partitionColumn"]!!.toInt()) {
                                    return index
                                }
                            }
                        }
                    }
                    is TripleStoreIndexDescriptionPartitionedByKey -> {
                        if (node2.attributes["type"] == "TripleStoreIndexDescriptionPartitionedByKey") {
                            if (index.partitionCount == node2.attributes["partitionCount"]!!.toInt()) {
                                return index
                            }
                        }
                    }
                    is TripleStoreIndexDescriptionSimple -> {
                        if (node2.attributes["type"] == "TripleStoreIndexDescriptionSimple") {
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
        if (graphName == DEFAULT_GRAPH_NAME && metadata[graphName] == null) {
            val query = Query()
            createGraph(query, graphName)
        }
        return metadata[graphName]!!
    }

    public override fun remoteCommit(query: IQuery, origin: Boolean) {
        for (graph in metadata.values) {
            for (index in graph.indices) {
                for (store in index.getAllLocations()) {
                    if (store.first == localhost) {
                        localStores[store.second]!!.flush()
                    }
                }
            }
        }
        if (origin) {
            for (hostname in hostnames) {
                if (hostname != localhost) {
                    communicationHandler.sendData(
                        hostname, "/distributed/graph/commit",
                        mapOf(
                            "origin" to "false",
                        )
                    )
                }
            }
        }
    }

    public override fun commit(query: IQuery) {
        remoteCommit(query, true)
    }
}
