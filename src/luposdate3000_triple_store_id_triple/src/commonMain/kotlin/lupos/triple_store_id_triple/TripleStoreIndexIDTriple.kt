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
package lupos.triple_store_id_triple

import lupos.ProguardTestAnnotation
import lupos.buffer_manager.BufferManager
import lupos.buffer_manager.BufferManagerExt
import lupos.shared.ETripleIndexTypeExt
import lupos.shared.IQuery
import lupos.shared.MyReadWriteLock
import lupos.shared.Parallel
import lupos.shared.SanityCheck
import lupos.shared.TripleStoreBulkImportExt
import lupos.shared.TripleStoreIndex
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.ColumnIteratorEmpty
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared_inline.BufferManagerPage
import lupos.triple_store_id_triple.index_IDTriple.BulkImportIterator
import lupos.triple_store_id_triple.index_IDTriple.Count1PassThroughIterator
import lupos.triple_store_id_triple.index_IDTriple.DistinctIterator
import lupos.triple_store_id_triple.index_IDTriple.EmptyIterator
import lupos.triple_store_id_triple.index_IDTriple.MergeIterator
import lupos.triple_store_id_triple.index_IDTriple.MinusIterator
import lupos.triple_store_id_triple.index_IDTriple.NodeInner
import lupos.triple_store_id_triple.index_IDTriple.NodeLeaf
import lupos.triple_store_id_triple.index_IDTriple.NodeManager
import lupos.triple_store_id_triple.index_IDTriple.NodeShared
import lupos.triple_store_id_triple.index_IDTriple.TripleIterator
import kotlin.jvm.JvmField

public class TripleStoreIndexIDTriple : TripleStoreIndex {
    @JvmField
    internal val bufferManager: BufferManager

    @JvmField
    internal val rootPageID: Int

    public override fun getRootPageID(): Int = rootPageID

    public constructor(rootPageID: Int, initFromRootPage: Boolean) : this(BufferManagerExt.getBuffermanager(), rootPageID, initFromRootPage)

    @ProguardTestAnnotation
    public constructor(bufferManager: BufferManager, rootPageID: Int, initFromRootPage: Boolean) {
        this.bufferManager = bufferManager
        this.rootPageID = rootPageID
        nodeManager = NodeManager(bufferManager)
        val rootPage = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:65", rootPageID)
        if (initFromRootPage) {
            root_ = BufferManagerPage.readInt4(rootPage, 4)
            countPrimary_ = BufferManagerPage.readInt4(rootPage, 8)
            distinctPrimary_ = BufferManagerPage.readInt4(rootPage, 12)
            firstLeaf_ = BufferManagerPage.readInt4(rootPage, 16)
            if (root_ != NodeManager.nodeNullPointer) {
                nodeManager.getNodeAny(
                    "/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:73",
                    root_,
                    {
                        SanityCheck.checkUnreachable()
                    },
                    {
                        rootNode = it
                    }
                )
            }
        } else {
            BufferManagerPage.writeInt4(rootPage, 0, ETripleIndexTypeExt.ID_TRIPLE)
            BufferManagerPage.writeInt4(rootPage, 4, root_)
            BufferManagerPage.writeInt4(rootPage, 8, countPrimary_)
            BufferManagerPage.writeInt4(rootPage, 12, distinctPrimary_)
            BufferManagerPage.writeInt4(rootPage, 16, firstLeaf_)
            bufferManager.flushPage("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:89", rootPageID)
        }
        bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:91", rootPageID)
    }

    @JvmField
    internal val nodeManager: NodeManager

    @JvmField
    internal var firstLeaf_: Int = NodeManager.nodeNullPointer

    @Suppress("NOTHING_TO_INLNE")
    internal inline fun setFirstLeaf(value: Int) {
        val rootPage = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:103", rootPageID)
        BufferManagerPage.writeInt4(rootPage, 16, value)
        firstLeaf_ = value
        bufferManager.flushPage("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:106", rootPageID)
        bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:107", rootPageID)
    }

    @JvmField
    internal var root_: Int = NodeManager.nodeNullPointer

    @Suppress("NOTHING_TO_INLNE")
    internal inline fun setRoot(value: Int) {
        val rootPage = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:115", rootPageID)
        BufferManagerPage.writeInt4(rootPage, 4, value)
        root_ = value
        bufferManager.flushPage("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:118", rootPageID)
        bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:119", rootPageID)
    }

    @JvmField
    internal var countPrimary_: Int = 0

    @Suppress("NOTHING_TO_INLNE")
    internal inline fun setCountPrimary(value: Int) {
        val rootPage = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:127", rootPageID)
        BufferManagerPage.writeInt4(rootPage, 8, value)
        countPrimary_ = value
        bufferManager.flushPage("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:130", rootPageID)
        bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:131", rootPageID)
    }

    @JvmField
    internal var distinctPrimary_: Int = 0

    @Suppress("NOTHING_TO_INLNE")
    internal inline fun setDistinctPrimary(value: Int) {
        val rootPage = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:139", rootPageID)
        BufferManagerPage.writeInt4(rootPage, 12, value)
        distinctPrimary_ = value
        bufferManager.flushPage("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:142", rootPageID)
        bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:143", rootPageID)
    }

    @JvmField
    internal var rootNode: ByteArray? = null

    @JvmField
    internal var pendingImport: MutableList<Int?> = mutableListOf()

    @JvmField
    internal var lock = MyReadWriteLock()

    @JvmField
    internal var cachedHistograms1Size: Int = 0

    @JvmField
    internal var cachedHistograms1Cursor: Int = 0

    @JvmField
    internal val cachedHistograms1: IntArray = IntArray(300)

    @JvmField
    internal var cachedHistograms2Size: Int = 0

    @JvmField
    internal var cachedHistograms2Cursor: Int = 0

    @JvmField
    internal val cachedHistograms2: IntArray = IntArray(400)

    @Suppress("NOTHING_TO_INLINE")
    private inline fun clearCachedHistogram() {
        cachedHistograms1Size = 0
        cachedHistograms2Size = 0
        cachedHistograms1Cursor = 0
        cachedHistograms2Cursor = 0
    }

    private fun checkForCachedHistogram(filter: IntArray): Pair<Int, Int>? {
        var res: Pair<Int, Int>? = null
        when (filter.size) {
            0 -> {
                res = Pair(countPrimary_, distinctPrimary_)
            }
            1 -> {
                for (i in 0 until cachedHistograms1Size) {
                    if (cachedHistograms1[i * 3] == filter[0]) {
                        res = Pair(cachedHistograms1[i * 3 + 1], cachedHistograms1[i * 3 + 2])
                        break
                    }
                }
            }
            2 -> {
                for (i in 0 until cachedHistograms2Size) {
                    if (cachedHistograms2[i * 4] == filter[0] && cachedHistograms2[i * 4 + 1] == filter[1]) {
                        res = Pair(cachedHistograms2[i * 4 + 2], cachedHistograms2[i * 4 + 3])
                        break
                    }
                }
            }
            3 -> {
                res = Pair(1, 1)
            }
        }
        return res
    }

    private fun updateCachedHistogram(filter: IntArray, data: Pair<Int, Int>) {
        when (filter.size) {
            1 -> {
                if (cachedHistograms1Size < 100) {
                    val i = cachedHistograms1Size
                    cachedHistograms1[i * 3] = filter[0]
                    cachedHistograms1[i * 3 + 1] = data.first
                    cachedHistograms1[i * 3 + 2] = data.second
                    cachedHistograms1Size++
                    cachedHistograms1Cursor = cachedHistograms1Size
                } else {
                    if (cachedHistograms1Cursor >= 100) {
                        cachedHistograms1Cursor = 0
                    }
                    val i = cachedHistograms1Cursor
                    cachedHistograms1[i * 3] = filter[0]
                    cachedHistograms1[i * 3 + 1] = data.first
                    cachedHistograms1[i * 3 + 2] = data.second
                    cachedHistograms1Cursor++
                }
            }
            2 -> {
                if (cachedHistograms2Size < 100) {
                    val i = cachedHistograms2Size
                    cachedHistograms2[i * 4] = filter[0]
                    cachedHistograms2[i * 4 + 1] = filter[1]
                    cachedHistograms2[i * 4 + 2] = data.first
                    cachedHistograms2[i * 4 + 3] = data.second
                    cachedHistograms2Size++
                    cachedHistograms2Cursor = cachedHistograms2Size
                } else {
                    if (cachedHistograms2Cursor >= 100) {
                        cachedHistograms2Cursor = 0
                    }
                    val i = cachedHistograms2Cursor
                    cachedHistograms2[i * 4] = filter[0]
                    cachedHistograms2[i * 4 + 1] = filter[1]
                    cachedHistograms2[i * 4 + 2] = data.first
                    cachedHistograms2[i * 4 + 3] = data.second
                    cachedHistograms2Cursor++
                }
            }
        }
    }

    override fun getHistogram(query: IQuery, filter: IntArray): Pair<Int, Int> {
        var res: Pair<Int, Int>? = checkForCachedHistogram(filter)
        if (res == null) {
            lock.readLock()
            val node = rootNode
            if (node != null) {
                when (filter.size) {
                    0 -> {
                        res = Pair(countPrimary_, distinctPrimary_)
                    }
                    1 -> {
                        val iterator = NodeInner.iterator1(node, filter, lock, 1, nodeManager)
                        var count = 0
                        var distinct = 0
                        var lastValue = iterator.next()
                        if (lastValue != DictionaryExt.nullValue) {
                            distinct++
                            count++
                            var value = iterator.next()
                            while (value != DictionaryExt.nullValue) {
                                count++
                                if (value != lastValue) {
                                    distinct++
                                }
                                lastValue = value
                                value = iterator.next()
                            }
                        }
                        res = Pair(count, distinct)
                    }
                    2 -> {
                        val iterator = NodeInner.iterator2(node, filter, lock, nodeManager)
                        var count = 0
                        while (iterator.next() != DictionaryExt.nullValue) {
                            count++
                        }
                        res = Pair(count, count)
                    }
                    3 -> {
                        res = Pair(1, 1)
                    }
                    else -> {
                        SanityCheck.checkUnreachable()
                    }
                }
            } else {
                res = Pair(0, 0)
            }
            lock.readUnlock()
            updateCachedHistogram(filter, res)
        }
        return res
    }

    override fun getIterator(query: IQuery, filter: IntArray, projection: List<String>): IteratorBundle {
        var res: IteratorBundle
        SanityCheck.check { filter.size in 0..3 }
        SanityCheck.check { projection.size + filter.size == 3 }
        val columns = mutableMapOf<String, ColumnIterator>()
        for (s in projection) {
            if (s != "_") {
                columns[s] = ColumnIteratorEmpty()
            }
        }
        res = if (columns.isNotEmpty()) {
            IteratorBundle(columns)
        } else {
            IteratorBundle(0)
        }
        flushContinueWithReadLock()
        val node = rootNode
        if (node != null) {
            if (filter.size == 3) {
                var count = 0
                val it = NodeInner.iterator3(node, filter, lock, nodeManager)
                while (it.next() != DictionaryExt.nullValue) {
                    count++
                }
                res = IteratorBundle(count)
            } else if (filter.size == 2) {
                if (projection[0] == "_") {
                    var count = 0
                    val it = NodeInner.iterator2(node, filter, lock, nodeManager)
                    while (it.next() != DictionaryExt.nullValue) {
                        count++
                    }
                    res = IteratorBundle(count)
                } else {
                    columns[projection[0]] = NodeInner.iterator2(node, filter, lock, nodeManager)
                }
            } else if (filter.size == 1) {
                if (projection[0] != "_") {
                    columns[projection[0]] = NodeInner.iterator1(node, filter, lock, 1, nodeManager)
                    if (projection[1] != "_") {
                        columns[projection[1]] = NodeInner.iterator1(node, filter, lock, 2, nodeManager)
                    }
                } else {
                    SanityCheck.check { projection[1] == "_" }
                    var count = 0
                    val it = NodeInner.iterator1(node, filter, lock, 1, nodeManager)
                    while (it.next() != DictionaryExt.nullValue) {
                        count++
                    }
                    res = IteratorBundle(count)
                }
            } else {
                SanityCheck.check { filter.isEmpty() }
                if (projection[0] != "_") {
                    columns[projection[0]] = NodeInner.iterator(node, lock, 0, nodeManager)
                    if (projection[1] != "_") {
                        columns[projection[1]] = NodeInner.iterator(node, lock, 1, nodeManager)
                        if (projection[2] != "_") {
                            columns[projection[2]] = NodeInner.iterator(node, lock, 2, nodeManager)
                        }
                    } else {
                        SanityCheck.check { projection[2] == "_" }
                    }
                } else {
                    SanityCheck.check { projection[1] == "_" }
                    SanityCheck.check { projection[2] == "_" }
                    res = IteratorBundle(countPrimary_)
                }
            }
        }
        lock.readUnlock()
        return res
    }

    private fun importHelper(a: Int, b: Int): Int {
        var nodeA: ByteArray? = null
        var nodeB: ByteArray? = null
        nodeManager.getNodeLeaf("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:386", a) {
            nodeA = it
        }
        nodeManager.getNodeLeaf("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:389", b) {
            nodeB = it
        }
        val res = importHelper(MergeIterator(NodeLeaf.iterator(nodeA!!, a, nodeManager), NodeLeaf.iterator(nodeB!!, b, nodeManager)))
        nodeManager.freeAllLeaves("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:393", a)
        nodeManager.freeAllLeaves("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:394", b)
        return res
    }

    private fun importHelper(iterator: TripleIterator): Int {
        var res = NodeManager.nodeNullPointer
        var node2: ByteArray? = null
        nodeManager.allocateNodeLeaf("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:401") { n, i ->
            res = i
            node2 = n
        }
        var nodeid = res
        var node = node2!!
        NodeLeaf.initializeWith(node, nodeid, iterator)
        while (iterator.hasNext()) {
            nodeManager.allocateNodeLeaf("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:409") { n, i ->
                NodeShared.setNextNode(node, i)
                nodeManager.flushNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:411", nodeid)
                nodeManager.releaseNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:412", nodeid)
                nodeid = i
                node = n
            }
            NodeLeaf.initializeWith(node, nodeid, iterator)
        }
        nodeManager.flushNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:418", nodeid)
        nodeManager.releaseNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:419", nodeid)
        return res
    }

    override fun flush() {
        if (pendingImport.size > 0) {
            lock.writeLock()
            flushAssumeLocks()
            lock.writeUnlock()
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun flushContinueWithWriteLock() {
        lock.writeLock()
        flushAssumeLocks()
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun flushContinueWithReadLock() {
        var hasLock = false
        while (pendingImport.size > 0) {
            if (lock.tryWriteLock()) {
                flushAssumeLocks()
                lock.downgradeToReadLock()
                hasLock = true
                break
            } else {
                SanityCheck.suspended {
                    Parallel.delay(100)
                }
            }
        }
        if (!hasLock) {
            lock.readLock()
        }
    }

    private fun flushAssumeLocks() {
        if (pendingImport.size > 0) {
            // check again, that there is something to be done ... this may be changed, because there could be someone _else beforehand, holding exactly this lock ... .
            var j = 1
            while (j < pendingImport.size) {
                if (pendingImport[j] == null) {
                    pendingImport[j] = pendingImport[j - 1]
                } else if (pendingImport[j - 1] != null) {
                    val a = pendingImport[j]!!
                    val b = pendingImport[j - 1]!!
                    pendingImport[j] = importHelper(a, b)
                }
                j++
            }
            SanityCheck.check { pendingImport.size > 0 }
            val firstLeaf2 = pendingImport[pendingImport.size - 1]!!
            var node: ByteArray? = null
            var flag = false
            nodeManager.getNodeAny(
                "/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:476",
                firstLeaf2,
                {
                    flag = true
                    node = it
                },
                {
                    node = it
                }
            )
            SanityCheck.check { rootNode == null }
            SanityCheck.check { root_ == NodeManager.nodeNullPointer }
            SanityCheck.check { firstLeaf_ == NodeManager.nodeNullPointer }
            rootNode = null
            setRoot(NodeManager.nodeNullPointer)
            setFirstLeaf(NodeManager.nodeNullPointer)
            if (flag) {
                rebuildData(NodeLeaf.iterator(node!!, firstLeaf2, nodeManager))
            } else {
                rebuildData(NodeInner.iterator(node!!, nodeManager))
            }
            nodeManager.freeAllLeaves("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:497", firstLeaf2)
            pendingImport.clear()
        }
    }

    private fun rebuildData(_iterator: TripleIterator) {
// assuming to have write-lock
        var iterator = Count1PassThroughIterator(DistinctIterator(_iterator))
        if (iterator.hasNext()) {
            var currentLayer = mutableListOf<Int>()
            var node2: ByteArray? = null
            nodeManager.allocateNodeLeaf("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:508") { n, i ->
                setFirstLeaf(i)
                node2 = n
                currentLayer.add(i)
            }
            var node = node2!!
            var nodeid = firstLeaf_
            NodeLeaf.initializeWith(node, nodeid, iterator)
            while (iterator.hasNext()) {
                nodeManager.allocateNodeLeaf("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:517") { n, i ->
                    NodeShared.setNextNode(node, i)
                    nodeManager.flushNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:519", nodeid)
                    nodeManager.releaseNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:520", nodeid)
                    nodeid = i
                    node = n
                    currentLayer.add(i)
                }
                NodeLeaf.initializeWith(node, nodeid, iterator)
            }
            SanityCheck.check { currentLayer.size > 0 }
            val codeSection1 = {
// work around the crossinline here, because the method would be too large
                while (currentLayer.size > 1) {
                    val tmp = mutableListOf<Int>()
                    var prev2: ByteArray? = null
                    nodeManager.allocateNodeInner("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:533") { n, i ->
                        nodeManager.flushNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:534", nodeid)
                        nodeManager.releaseNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:535", nodeid)
                        nodeid = i
                        tmp.add(i)
                        NodeInner.initializeWith(n, i, currentLayer, nodeManager)
                        prev2 = n
                    }
                    var prev = prev2!!
                    while (currentLayer.size > 0) {
                        nodeManager.allocateNodeInner("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:543") { n, i ->
                            nodeManager.flushNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:544", nodeid)
                            nodeManager.releaseNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:545", nodeid)
                            nodeid = i
                            tmp.add(i)
                            NodeInner.initializeWith(n, i, currentLayer, nodeManager)
                            NodeShared.setNextNode(prev, i)
                            prev = n
                        }
                    }
                    currentLayer = tmp
                }
            }
            codeSection1()
            nodeManager.flushNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:557", nodeid)
            nodeManager.releaseNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:558", nodeid)
            var rootNodeIsLeaf = false
            SanityCheck.check { rootNode == null }
            val codeSection2 = {
                nodeManager.getNodeAny(
                    "/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:563",
                    currentLayer[0],
                    {
                        rootNodeIsLeaf = true
                    },
                    {
                        rootNode = it
                        setRoot(currentLayer[0])
                    }
                )
                if (rootNodeIsLeaf) {
                    nodeManager.flushNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:574", nodeid)
                    nodeManager.releaseNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:575", nodeid)
                    nodeManager.allocateNodeInner("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:576") { n, i ->
                        NodeInner.initializeWith(n, i, mutableListOf(currentLayer[0]), nodeManager)
                        rootNode = n
                        setRoot(i)
                        nodeManager.flushNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:580", root_)
                    }
                }
            }
            codeSection2()
        } else {
// this index is cleared completely
            SanityCheck.check { rootNode == null }
            rootNode = null
            setRoot(NodeManager.nodeNullPointer)
            setFirstLeaf(NodeManager.nodeNullPointer)
        }
        setCountPrimary(iterator.count)
        setDistinctPrimary(iterator.distinct)
        clearCachedHistogram()
    }

    override fun insertAsBulk(data: IntArray, order: IntArray, dataSize: Int) {
        flushContinueWithWriteLock()
        val d = arrayOf(data, IntArray(dataSize))
        TripleStoreBulkImportExt.sortUsingBuffers(0, 0, 1, d, dataSize / 3, order)
        val iteratorImport = BulkImportIterator(d[0], dataSize, order)
        var iteratorStore2: TripleIterator? = null
        if (firstLeaf_ == NodeManager.nodeNullPointer) {
            iteratorStore2 = EmptyIterator()
        } else {
            nodeManager.getNodeLeaf("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:611", firstLeaf_) {
                iteratorStore2 = NodeLeaf.iterator(it, firstLeaf_, nodeManager)
            }
        }
        val iteratorStore = iteratorStore2!!
        val iterator = MergeIterator(iteratorStore, iteratorImport)
        val oldroot = root_
        rootNode = null
        setRoot(NodeManager.nodeNullPointer)
        setFirstLeaf(NodeManager.nodeNullPointer)
        rebuildData(iterator)
        if (oldroot != NodeManager.nodeNullPointer) {
            nodeManager.freeNodeAndAllRelated("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:623", oldroot)
        }
        lock.writeUnlock()
    }

    override fun removeAsBulk(data: IntArray, order: IntArray, dataSize: Int) {
        flushContinueWithWriteLock()
        val d = arrayOf(data, IntArray(dataSize))
        TripleStoreBulkImportExt.sortUsingBuffers(0, 0, 1, d, dataSize / 3, order)
        val iteratorImport = BulkImportIterator(d[0], dataSize, order)
        var iteratorStore2: TripleIterator? = null
        if (firstLeaf_ == NodeManager.nodeNullPointer) {
            iteratorStore2 = EmptyIterator()
        } else {
            nodeManager.getNodeLeaf("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:642", firstLeaf_) {
                iteratorStore2 = NodeLeaf.iterator(it, firstLeaf_, nodeManager)
            }
        }
        val iteratorStore = iteratorStore2!!
        val iterator = MinusIterator(iteratorStore, iteratorImport)
        val oldroot = root_
        rootNode = null
        setRoot(NodeManager.nodeNullPointer)
        setFirstLeaf(NodeManager.nodeNullPointer)
        rebuildData(iterator)
        if (oldroot != NodeManager.nodeNullPointer) {
            nodeManager.freeNodeAndAllRelated("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:654", oldroot)
        }
        lock.writeUnlock()
    }

    override fun insertAsBulkSorted(data: IntArray, order: IntArray, dataSize: Int) {
        flushContinueWithWriteLock()
        val iteratorImport = BulkImportIterator(data, dataSize, order)
        var iteratorStore2: TripleIterator? = null
        if (firstLeaf_ == NodeManager.nodeNullPointer) {
            iteratorStore2 = EmptyIterator()
        } else {
            nodeManager.getNodeLeaf("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:666", firstLeaf_) {
                iteratorStore2 = NodeLeaf.iterator(it, firstLeaf_, nodeManager)
            }
        }
        val iteratorStore = iteratorStore2!!
        val iterator = MergeIterator(iteratorStore, iteratorImport)
        val oldroot = root_
        rootNode = null
        setRoot(NodeManager.nodeNullPointer)
        setFirstLeaf(NodeManager.nodeNullPointer)
        rebuildData(iterator)
        if (oldroot != NodeManager.nodeNullPointer) {
            nodeManager.freeNodeAndAllRelated("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:678", oldroot)
        }
        lock.writeUnlock()
    }

    override fun removeAsBulkSorted(data: IntArray, order: IntArray, dataSize: Int) {
        flushContinueWithWriteLock()
        val iteratorImport = BulkImportIterator(data, dataSize, order)
        var iteratorStore2: TripleIterator? = null
        if (firstLeaf_ == NodeManager.nodeNullPointer) {
            iteratorStore2 = EmptyIterator()
        } else {
            nodeManager.getNodeLeaf("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:690", firstLeaf_) {
                iteratorStore2 = NodeLeaf.iterator(it, firstLeaf_, nodeManager)
            }
        }
        val iteratorStore = iteratorStore2!!
        val iterator = MinusIterator(iteratorStore, iteratorImport)
        val oldroot = root_
        rootNode = null
        setRoot(NodeManager.nodeNullPointer)
        setFirstLeaf(NodeManager.nodeNullPointer)
        rebuildData(iterator)
        if (oldroot != NodeManager.nodeNullPointer) {
            nodeManager.freeNodeAndAllRelated("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:702", oldroot)
        }
        lock.writeUnlock()
    }

    override fun clear() {
        flushContinueWithWriteLock()
        if (root_ != NodeManager.nodeNullPointer) {
            nodeManager.freeNodeAndAllRelated("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:710", root_)
            setRoot(NodeManager.nodeNullPointer)
        }
        setFirstLeaf(NodeManager.nodeNullPointer)
        rootNode = null
        clearCachedHistogram()
        lock.writeUnlock()
    }

    override fun delete() {
        clear()
        bufferManager.getPage("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:721", rootPageID)
        bufferManager.deletePage("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:722", rootPageID)
    }

    override fun close() {
        flush()
        if (root_ != NodeManager.nodeNullPointer) {
            nodeManager.releaseNode("/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:729", root_)
        }
    }
}
