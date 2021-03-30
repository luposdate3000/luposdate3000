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

import lupos.buffermanager.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES
import lupos.buffermanager.BufferManager
import lupos.buffermanager.BufferManagerExt
import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.EIndexPatternHelper
import lupos.s00misc.EModifyType
import lupos.s00misc.EModifyTypeExt
import lupos.s00misc.EPartitionMode
import lupos.s00misc.EPartitionModeExt
import lupos.s00misc.File
import lupos.s00misc.IMyInputStream
import lupos.s00misc.Platform
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s00misc.communicationHandler
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.Query
import kotlin.jvm.JvmField
import kotlin.math.min

public class TripleStoreManagerImpl : TripleStoreManager {
    private var hostnames: Array<LuposHostname>

    @JvmField
    internal var localhost: LuposHostname
    private val partitionMode: EPartitionMode
    private var bufferManager: BufferManager = BufferManagerExt.getBuffermanager("stores")
    private val localStores_ = mutableMapOf<LuposStoreKey, TripleStoreIndex>()
    private val metadata_ = mutableMapOf<LuposGraphName, TripleStoreDescription>()
    private lateinit var defaultTripleStoreLayout: TripleStoreDescriptionFactory
    private var rootPageID: Int = -1
    private val globalManagerRootFileName = "triple_store_manager.page"
    private val keysOnHostname_: Array<MutableSet<LuposStoreKey>>
    internal inline fun localStoresGet() = localStores_
    internal inline fun metadataGet() = metadata_

    private inline fun toByteArray(): ByteArray {
        var size = 8
        for ((k, v) in localStores_) {
            val buf = k.encodeToByteArray()
            size += 8 + buf.size
        }
        for ((name, description) in metadata_) {
            val buf = name.encodeToByteArray()
            size += 8 + buf.size
            for (index in description.indices) {
                val buf2 = index.toByteArray()
                size += 4 + buf2.size
            }
        }
        val buffer = ByteArray(size)
        var off = 0
        ByteArrayHelper.writeInt4(buffer, off, localStores_.size)
        off += 4
        for ((key, index) in localStores_) {
            val buf = key.encodeToByteArray()
            ByteArrayHelper.writeInt4(buffer, off, index.getRootPageID())
            off += 4
            ByteArrayHelper.writeInt4(buffer, off, buf.size)
            off += 4
            buf.copyInto(buffer, off)
            off += buf.size
        }
        ByteArrayHelper.writeInt4(buffer, off, metadata_.size)
        off += 4
        for ((name, description) in metadata_) {
            val buf = name.encodeToByteArray()
            ByteArrayHelper.writeInt4(buffer, off, buf.size)
            off += 4
            buf.copyInto(buffer, off)
            off += buf.size
            ByteArrayHelper.writeInt4(buffer, off, description.indices.size)
            off += 4
            for (index in description.indices) {
                val buf2 = index.toByteArray()
                ByteArrayHelper.writeInt4(buffer, off, buf2.size)
                off += 4
                buf2.copyInto(buffer, off)
                off += buf2.size
            }
        }
        return buffer
    }

    private inline fun initFromByteArray(buffer: ByteArray) {
        var off = 0
        val l1 = ByteArrayHelper.readInt4(buffer, off)
        off += 4
        for (i in 0 until l1) {
            val pageid = ByteArrayHelper.readInt4(buffer, off)
            off += 4
            val l2 = ByteArrayHelper.readInt4(buffer, off)
            off += 4
            val buf = ByteArray(l2)
            buffer.copyInto(buf, 0, off, off + l2)
            off += l2
            val store = TripleStoreIndexIDTriple(pageid, true)
            val key = buf.decodeToString()
            localStores_[key] = store
        }
        val l3 = ByteArrayHelper.readInt4(buffer, off)
        off += 4
        for (i in 0 until l3) {
            val l4 = ByteArrayHelper.readInt4(buffer, off)
            off += 4
            val buf = ByteArray(l4)
            buffer.copyInto(buf, 0, off, off + l4)
            off += l4
            val name = buf.decodeToString()
            val l5 = ByteArrayHelper.readInt4(buffer, off)
            off += 4
            val description = TripleStoreDescriptionFactory()
            for (j in 0 until l5) {
                val l6 = ByteArrayHelper.readInt4(buffer, off)
                off += 4
                val buf2 = ByteArray(l6)
                buffer.copyInto(buf2, 0, off, off + l6)
                off += l6
                description.addIndex { it.initFromByteArray(buf2) }
            }
            metadata_[name] = description.build()
        }
    }

    private inline fun keysOnHostnameAdd(hostidx: Int, key: LuposStoreKey) {
        keysOnHostname_[hostidx].add(key)
    }

    private inline fun localStoresAdd(key: LuposStoreKey, pageid: Int, tripleStore: TripleStoreIndex) {
        SanityCheck.check { localStores_[key] == null }
        localStores_[key] = tripleStore
    }

    private inline fun localStoresRemove(key: LuposStoreKey) {
        val tripleStore = localStores_[key]!!
        tripleStore.delete()
        localStores_.remove(key)
    }

    private inline fun metadataAdd(name: LuposGraphName, tripleStore: TripleStoreDescription) {
        SanityCheck.check { metadata_[name] == null }
        metadata_[name] = tripleStore
    }

    private inline fun metadataRemove(name: LuposGraphName) {
        SanityCheck.check { metadata_[name] != null }
        metadata_.remove(name)
    }

    public constructor(hostnames: Array<LuposHostname>, localhost: LuposHostname,) : super() {
        this.hostnames = hostnames
        this.localhost = localhost
        keysOnHostname_ = Array(hostnames.size) { mutableSetOf<LuposStoreKey>() }
        val t = Platform.getEnv("LUPOS_PARTITION_MODE", EPartitionModeExt.names[EPartitionModeExt.None])!!
        val tmp = EPartitionModeExt.names.indexOf(t)
        if (tmp < 0) {
            throw Exception("invalid parameter '$t' for 'LUPOS_PARTITION_MODE'. Choose one of ${EPartitionModeExt.names.map { it }}")
        }
        partitionMode = tmp
    }

    public override fun initialize() {
        val file = File(BufferManagerExt.bufferPrefix + globalManagerRootFileName)
        var pageid = -1
        if (file.exists()) {
            file.withInputStream {
                pageid = it.readInt()
            }
            initialize(bufferManager, pageid, true)
        } else {
            bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid2 ->
                pageid = pageid2
            }
            bufferManager.releasePage(lupos.SOURCE_FILE, pageid)
            if (BufferManagerExt.allowInitFromDisk) {
                file.withOutputStream {
                    it.writeInt(pageid)
                }
            }
            initialize(bufferManager, pageid, false)
        }
    }

    private fun initFromPageID() {
        var pageid = rootPageID
        var page = bufferManager.getPage(lupos.SOURCE_FILE, pageid)
        var nextid = page.readInt4(0)
        val size = page.readInt4(4)
        val buffer = ByteArray(size)
        var off = 0
        val len = min(size - off, BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 8)
        page.copyInto(buffer, off, 8, 8 + len)
        off += len
        bufferManager.releasePage(lupos.SOURCE_FILE, pageid)
        while (off < size) {
            pageid = nextid
            page = bufferManager.getPage(lupos.SOURCE_FILE, pageid)
            nextid = page.readInt4(0)
            val len2 = min(size - off, BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 4)
            page.copyInto(buffer, off, 4, 4 + len2)
            off += len2
            bufferManager.releasePage(lupos.SOURCE_FILE, pageid)
        }
        initFromByteArray(buffer)
    }

    private fun deleteAllPagesExceptRootID() {
        var pageid = rootPageID
        var page = bufferManager.getPage(lupos.SOURCE_FILE, pageid)
        var nextid = page.readInt4(0)
        val size = page.readInt4(4)
        var off = 0
        val len = min(size - off, BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 8)
        off += len
        bufferManager.releasePage(lupos.SOURCE_FILE, pageid)
        while (off < size) {
            pageid = nextid
            page = bufferManager.getPage(lupos.SOURCE_FILE, pageid)
            nextid = page.readInt4(0)
            val len2 = min(size - off, BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 4)
            off += len2
            bufferManager.deletePage(lupos.SOURCE_FILE, pageid)
        }
    }

    private fun writeToPageID() {
        val buffer = toByteArray()
        var pageid = rootPageID
        var page = bufferManager.getPage(lupos.SOURCE_FILE, pageid)
        val size = buffer.size
        page.writeInt4(4, size)
        var off = 0
        val len = min(size - off, BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 8)
        page.copyFrom(buffer, 8, off, off + len)
        off += len
        while (off < size) {
            bufferManager.createPage(lupos.SOURCE_FILE) { page2, pageid2 ->
                page.writeInt4(0, pageid2)
                bufferManager.releasePage(lupos.SOURCE_FILE, pageid)
                pageid = pageid2
                page = page2
                val len = min(size - off, BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 4)
                page.copyFrom(buffer, 4, off, off + len)
                off += len
            }
        }
        bufferManager.releasePage(lupos.SOURCE_FILE, pageid)
    }

    public fun initialize(bufferManager: BufferManager, rootPageID: Int, initFromRootPage: Boolean) {
        this.bufferManager = bufferManager
        this.rootPageID = rootPageID
        resetDefaultTripleStoreLayout()
        if (initFromRootPage) {
            initFromPageID()
        } else {
            writeToPageID()
        }
    }

    override fun close() {
        for (v in localStores_.values) {
            v.close()
        }
        deleteAllPagesExceptRootID()
        writeToPageID()
    }

    override fun delete() {
        for (v in localStores_.values) {
            v.delete()
        }
        deleteAllPagesExceptRootID()
        bufferManager.getPage(lupos.SOURCE_FILE, rootPageID)
        bufferManager.deletePage(lupos.SOURCE_FILE, rootPageID)
    }

    public override fun getLocalhost(): LuposHostname = localhost
    public override fun getPartitionMode(): EPartitionMode = partitionMode
    public override fun debugAllLocalStoreContent() {
        File("${localhost.replace(":", "_")}.metadata").withOutputStream { out ->
            for ((k, v) in metadata_) {
                out.println("graphname : '$k'")
                val meta = v.toMetaString().split("|")
                for (s in meta) {
                    out.println("    $s")
                }
            }
            out.flush()
        }
        for ((k, v) in localStores_) {
            File("${localhost.replace(":", "_")}_$k.store").withOutputStream { out ->
                val query = Query()
                val iter = v.getIterator(query, IntArray(0), listOf("s", "p", "o"))
                val rowiter = iter.rows
                var off = rowiter.next()
                while (off > -1) {
                    var s = ""
                    for (i in 0 until 3) {
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

    public override fun resetDefaultTripleStoreLayout() {
        if (partitionMode == EPartitionModeExt.None) {
            defaultTripleStoreLayout = TripleStoreDescriptionFactory() //
                .addIndex { it.simple(EIndexPatternExt.SPO) } //
                .addIndex { it.simple(EIndexPatternExt.SOP) } //
                .addIndex { it.simple(EIndexPatternExt.PSO) } //
                .addIndex { it.simple(EIndexPatternExt.POS) } //
                .addIndex { it.simple(EIndexPatternExt.OSP) } //
                .addIndex { it.simple(EIndexPatternExt.OPS) } //
        } else {
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
    }

    public override fun updateDefaultTripleStoreLayout(action: (ITripleStoreDescriptionFactory) -> Unit) {
        val factory = TripleStoreDescriptionFactory()
        action(factory)
        defaultTripleStoreLayout = factory
    }

    internal fun getNextHostAndKey(): Pair<LuposHostname, LuposStoreKey> {
        var hostidx = 0
        for (i in 1 until hostnames.size) {
            if (keysOnHostname_[i].size < keysOnHostname_[hostidx].size) {
                hostidx = i
            }
        }
        var key = 0
        while (keysOnHostname_[hostidx].contains("$key")) {
            key++
        }
        keysOnHostnameAdd(hostidx, "$key")
        return Pair(hostnames[hostidx], "$key")
    }

    public override fun createGraph(query: IQuery, graphName: LuposGraphName) {
        createGraph(query, graphName, { it.apply(defaultTripleStoreLayout) })
    }

    public override fun remoteModify(query: IQuery, key: String, mode: EModifyType, idx: EIndexPattern, stream: IMyInputStream) {
        val store = localStores_[key]!!
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
        if (origin) {
            createGraph(query, graphName)
        } else {
            val graph = TripleStoreDescription(meta!!)
            metadataAdd(graphName, graph)
            createGraphShared(graph)
        }
    }

    private inline fun createGraphShared(graph: TripleStoreDescription) {
        for (index in graph.indices) {
            for (store in index.getAllLocations()) {
                if (store.first == localhost) {
                    var page: Int = 0
                    bufferManager.createPage(lupos.SOURCE_FILE) { byteArray, pageid ->
                        page = pageid
                    }
                    bufferManager.releasePage(lupos.SOURCE_FILE, page)
                    println("allocated store-root page :: $page")
                    localStoresAdd(store.second, page, TripleStoreIndexIDTriple(page, false))
                }
            }
        }
    }

    public fun createGraph(query: IQuery, graphName: LuposGraphName, action: (ITripleStoreDescriptionFactory) -> Unit) {
        if (metadata_[graphName] != null) {
            throw Exception("graph already exist")
        }
        val factory = TripleStoreDescriptionFactory()
        action(factory)
        val graph = factory.build()
        metadataAdd(graphName, graph)
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

    public override fun clearGraph(query: IQuery, graphName: LuposGraphName) {
        remoteClearGraph(query, graphName, true)
    }

    public override fun remoteClearGraph(query: IQuery, graphName: LuposGraphName, origin: Boolean) {
        if (graphName == DEFAULT_GRAPH_NAME && metadata_[graphName] == null) {
            createGraph(query, graphName)
        } else {
            val graph = metadata_[graphName]
            if (graph != null) {
                for (index in graph.indices) {
                    for (store in index.getAllLocations()) {
                        if (store.first == localhost) {
                            localStores_[store.second]!!.clear()
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
        val graph = metadata_[graphName]
        if (graph != null) {
            for (index in graph.indices) {
                for (store in index.getAllLocations()) {
                    if (store.first == localhost) {
                        localStoresRemove(store.second)
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
        metadataRemove(graphName)
    }

    public override fun getGraphNames(): List<LuposGraphName> {
        return getGraphNames(false)
    }

    public override fun getGraphNames(includeDefault: Boolean): List<LuposGraphName> {
        val res = mutableListOf<LuposGraphName>()
        if (includeDefault) {
            res.add(DEFAULT_GRAPH_NAME)
        }
        metadata_.keys.forEach {
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
        val node2 = node["TripleStoreIndexDescription"]!!
        val graph = metadata_[node2.attributes["graphName"]]!!
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
        if (graphName == DEFAULT_GRAPH_NAME && metadata_[graphName] == null) {
            val query = Query()
            createGraph(query, graphName)
        }
        return metadata_[graphName]!!
    }

    public override fun remoteCommit(query: IQuery, origin: Boolean) {
        for (graph in metadata_.values) {
            for (index in graph.indices) {
                for (store in index.getAllLocations()) {
                    if (store.first == localhost) {
                        localStores_[store.second]!!.flush()
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
