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
package lupos.triple_store_manager

import lupos.buffer_manager.BufferManagerExt
import lupos.operator.base.Query
import lupos.shared.EIndexPattern
import lupos.shared.EIndexPatternExt
import lupos.shared.EIndexPatternHelper
import lupos.shared.EModifyType
import lupos.shared.EModifyTypeExt
import lupos.shared.EPartitionMode
import lupos.shared.EPartitionModeExt
import lupos.shared.IBufferManager
import lupos.shared.IMyInputStream
import lupos.shared.IQuery
import lupos.shared.ITripleStoreDescriptionFactory
import lupos.shared.ITripleStoreIndexDescription
import lupos.shared.LuposGraphName
import lupos.shared.LuposHostname
import lupos.shared.LuposStoreKey
import lupos.shared.Luposdate3000Instance
import lupos.shared.SanityCheck
import lupos.shared.TripleStoreIndex
import lupos.shared.TripleStoreManager
import lupos.shared.XMLElement
import lupos.shared_inline.BufferManagerPage
import lupos.shared_inline.ByteArrayHelper
import lupos.shared_inline.File
import lupos.triple_store_id_triple.TripleStoreIndexIDTriple
import kotlin.jvm.JvmField
import kotlin.math.min

public class TripleStoreManagerImpl : TripleStoreManager {
    @JvmField
    internal var hostnames: Array<LuposHostname>

    @JvmField
    internal var localhost: LuposHostname

    @JvmField
    internal var instance: Luposdate3000Instance

    @JvmField
    internal val partitionMode: EPartitionMode

    @JvmField
    internal var bufferManager: IBufferManager

    @JvmField
    internal val localStores_ = mutableMapOf<LuposStoreKey, TripleStoreIndex>()

    @JvmField
    internal val metadata_ = mutableMapOf<LuposGraphName, TripleStoreDescription>()
    private lateinit var defaultTripleStoreLayout: TripleStoreDescriptionFactory

    @JvmField
    internal var rootPageID: Int = -1

    @JvmField
    internal val globalManagerRootFileName = "triple_store_manager.page"

    @JvmField
    internal val keysOnHostname_: Array<MutableSet<LuposStoreKey>>

    @Suppress("NOTHING_TO_INLINE")
    internal fun localStoresGet() = localStores_

    @Suppress("NOTHING_TO_INLINE")
    internal fun metadataGet() = metadata_

    @Suppress("NOTHING_TO_INLINE")
    private fun toByteArray(): ByteArray {
        var size = 8
        for (k in localStores_.keys) {
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

    @Suppress("NOTHING_TO_INLINE")
    private fun initFromByteArray(buffer: ByteArray) {
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
            val store = TripleStoreIndexIDTriple(pageid, true, instance)
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
            val description = TripleStoreDescriptionFactory(instance)
            for (j in 0 until l5) {
                val l6 = ByteArrayHelper.readInt4(buffer, off)
                off += 4
                val buf2 = ByteArray(l6)
                buffer.copyInto(buf2, 0, off, off + l6)
                off += l6
                description.addIndex { it.initFromByteArray(buf2) }
            }
            metadata_[name] = description.build(instance)
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun keysOnHostnameAdd(hostidx: Int, key: LuposStoreKey) {
        keysOnHostname_[hostidx].add(key)
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun localStoresAdd(key: LuposStoreKey, tripleStore: TripleStoreIndex) {
        SanityCheck.check { localStores_[key] == null }
        localStores_[key] = tripleStore
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun localStoresRemove(key: LuposStoreKey) {
        val tripleStore = localStores_[key]!!
        tripleStore.delete()
        localStores_.remove(key)
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun metadataAdd(name: LuposGraphName, tripleStore: TripleStoreDescription) {
        SanityCheck.check { metadata_[name] == null }
        metadata_[name] = tripleStore
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun metadataRemove(name: LuposGraphName) {
        SanityCheck.check({ metadata_[name] != null || name == DEFAULT_GRAPH_NAME }, { "$name :: ${metadata_.keys}" })
        metadata_.remove(name)
    }

    public constructor(hostnames: Array<LuposHostname>, localhost: LuposHostname, instance: Luposdate3000Instance) : super() {
        this.instance = instance
        this.bufferManager = instance.bufferManager!!
        this.hostnames = hostnames
        this.localhost = localhost
        keysOnHostname_ = Array(hostnames.size) { mutableSetOf<LuposStoreKey>() }
        partitionMode = instance.LUPOS_PARTITION_MODE
    }

    public override fun initialize() {
        val file = File(instance.BUFFER_HOME + globalManagerRootFileName)
        var pageid = -1
        if (BufferManagerExt.allowInitFromDisk && instance.allowInitFromDisk && file.exists()) {
            file.withInputStream {
                pageid = it.readInt()
            }
            initialize(bufferManager, pageid, true)
        } else {
            pageid = bufferManager.allocPage("/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:225")
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
        var page = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:237", pageid)
        var nextid = BufferManagerPage.readInt4(page, 0)
        val size = BufferManagerPage.readInt4(page, 4)
        val buffer = ByteArray(size)
        var off = 0
        val len = min(size - off, BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 8)
        page.copyInto(buffer, off, 8, 8 + len)
        off += len
        bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:245", pageid)
        while (off < size) {
            pageid = nextid
            page = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:248", pageid)
            nextid = BufferManagerPage.readInt4(page, 0)
            val len2 = min(size - off, BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 4)
            page.copyInto(buffer, off, 4, 4 + len2)
            off += len2
            bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:253", pageid)
        }
        initFromByteArray(buffer)
    }

    private fun deleteAllPagesExceptRootID() {
        var pageid = rootPageID
        var page = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:260", pageid)
        var nextid = BufferManagerPage.readInt4(page, 0)
        val size = BufferManagerPage.readInt4(page, 4)
        var off = 0
        val len = min(size - off, BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 8)
        off += len
        bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:266", pageid)
        while (off < size) {
            pageid = nextid
            page = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:269", pageid)
            nextid = BufferManagerPage.readInt4(page, 0)
            val len2 = min(size - off, BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 4)
            off += len2
            bufferManager.deletePage("/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:273", pageid)
        }
    }

    private fun writeToPageID() {
        val buffer = toByteArray()
        var pageid = rootPageID
        var page = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:280", pageid)
        val size = buffer.size
        BufferManagerPage.writeInt4(page, 4, size)
        var off = 0
        val len = min(size - off, BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 8)
        BufferManagerPage.copyFrom(page, buffer, 8, off, off + len)
        off += len
        while (off < size) {
            val pageid2 = bufferManager.allocPage("/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:288")
            BufferManagerPage.writeInt4(page, 0, pageid2)
            bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:290", pageid)
            pageid = pageid2
            page = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:292", pageid2)
            val len2 = min(size - off, BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 4)
            BufferManagerPage.copyFrom(page, buffer, 4, off, off + len2)
            off += len2
        }
        bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:297", pageid)
    }

    public fun initialize(bufferManager: IBufferManager, rootPageID: Int, initFromRootPage: Boolean) {
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
        bufferManager.getPage("/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:324", rootPageID)
        bufferManager.deletePage("/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:325", rootPageID)
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
                val query = Query(instance)
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
            defaultTripleStoreLayout = TripleStoreDescriptionFactory(instance) //
                .addIndex { it.simple(EIndexPatternExt.SPO) } //
                .addIndex { it.simple(EIndexPatternExt.SOP) } //
                .addIndex { it.simple(EIndexPatternExt.PSO) } //
                .addIndex { it.simple(EIndexPatternExt.POS) } //
                .addIndex { it.simple(EIndexPatternExt.OSP) } //
                .addIndex { it.simple(EIndexPatternExt.OPS) } //
        } else {
            when (0) {
                0 -> { // use partitions as default
                    defaultTripleStoreLayout = TripleStoreDescriptionFactory(instance) //
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
                    defaultTripleStoreLayout = TripleStoreDescriptionFactory(instance) //
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
        val factory = TripleStoreDescriptionFactory(instance)
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

    public override fun remoteModifySorted(query: IQuery, key: String, mode: EModifyType, idx: EIndexPattern, stream: IMyInputStream) {
        val store = localStores_[key]!!
        var count = stream.readInt()
        val buf = IntArray(count)
        for (i in 0 until count) {
            buf[i] = stream.readInt()
        }
        if (mode == EModifyTypeExt.INSERT) {
            store.insertAsBulkSorted(buf, EIndexPatternHelper.tripleIndicees[idx], count)
        } else {
            store.removeAsBulkSorted(buf, EIndexPatternHelper.tripleIndicees[idx], count)
        }
    }

    public override fun remoteCreateGraph(query: IQuery, graphName: LuposGraphName, origin: Boolean, meta: String?) {
        if (origin) {
            createGraph(query, graphName)
        } else {
            val graph = TripleStoreDescription(meta!!, instance)
            metadataAdd(graphName, graph)
            createGraphShared(graph)
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun createGraphShared(graph: TripleStoreDescription) {
        for (index in graph.indices) {
            for (store in index.getAllLocations()) {
                if (store.first == localhost) {
                    val page = bufferManager.allocPage("/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreManagerImpl.kt:471")
                    localStoresAdd(store.second, TripleStoreIndexIDTriple(page, false, instance))
                }
            }
        }
    }

    public fun createGraph(query: IQuery, graphName: LuposGraphName, action: (ITripleStoreDescriptionFactory) -> Unit) {
        if (metadata_[graphName] != null) {
            throw Exception("graph already exist")
        }
        val factory = TripleStoreDescriptionFactory(instance)
        action(factory)
        val graph = factory.build(instance)
        metadataAdd(graphName, graph)
        for (index in graph.indices) {
            index.assignHosts()
        }
        createGraphShared(graph)
        val metadataStr = graph.toMetaString()
        for (hostname in hostnames) {
            if (hostname != localhost) {
                query.getInstance().communicationHandler!!.sendData(
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
                                query.getInstance().communicationHandler!!.sendData(
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
                            query.getInstance().communicationHandler!!.sendData(
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
            val query = Query(instance)
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
                    query.getInstance().communicationHandler!!.sendData(
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
