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
import lupos.shared.BufferManagerPage
import lupos.shared.BufferManagerPageWrapper
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.ETripleIndexTypeExt
import lupos.shared.IBufferManager
import lupos.shared.IQuery
import lupos.shared.Luposdate3000Instance
import lupos.shared.SanityCheck
import lupos.shared.TripleStoreBulkImportExt
import lupos.shared.TripleStoreIndex
import lupos.shared.inline.MyThreadReadWriteLock
import lupos.shared.inline.ParallelThread
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.ColumnIteratorEmpty
import lupos.shared.operator.iterator.IteratorBundle
import lupos.triple_store_id_triple.index_IDTriple.BulkImportIterator
import lupos.triple_store_id_triple.index_IDTriple.Count1PassThroughIterator
import lupos.triple_store_id_triple.index_IDTriple.DistinctIterator
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
    internal val bufferManager: IBufferManager

    @JvmField
    public var debugSortOrder: IntArray = intArrayOf()

    @JvmField
    internal val rootPageID: Int

    @JvmField
    internal val nodeManager: NodeManager

    @JvmField
    internal var firstLeaf_: Int = NodeManager.nodeNullPointer

    @JvmField
    internal var root_: Int = NodeManager.nodeNullPointer

    @JvmField
    internal var countPrimary_: Int = 0

    @JvmField
    internal var distinctPrimary_: Int = 0

    @JvmField
    internal var rootNode: BufferManagerPageWrapper? = null

    @JvmField
    internal var pendingImport: MutableList<Int?> = mutableListOf()

    @JvmField
    internal var pendingRemove: MutableList<Int?> = mutableListOf()

    @JvmField
    internal var lock = MyThreadReadWriteLock()

    @JvmField
    internal var cachedHistograms1Size: Int = 0

    @JvmField
    internal var cachedHistograms1Cursor: Int = 0

    @JvmField
    internal val cachedHistograms1Values: DictionaryValueTypeArray = DictionaryValueTypeArray(100)

    @JvmField
    internal val cachedHistograms1Response: IntArray = IntArray(200)

    @JvmField
    internal var cachedHistograms2Size: Int = 0

    @JvmField
    internal var cachedHistograms2Cursor: Int = 0

    @JvmField
    internal val cachedHistograms2Values: DictionaryValueTypeArray = DictionaryValueTypeArray(200)

    @JvmField
    internal val cachedHistograms2Response: IntArray = IntArray(200)

    override fun getRootPageID(): Int = rootPageID

    public constructor(rootPageID: Int, initFromRootPage: Boolean, instance: Luposdate3000Instance) : this(instance.bufferManager!!, rootPageID, initFromRootPage)

    @ProguardTestAnnotation
    public constructor(bufferManager: IBufferManager, rootPageID: Int, initFromRootPage: Boolean) {
        this.bufferManager = bufferManager
        this.rootPageID = rootPageID
        nodeManager = NodeManager(bufferManager)
        val rootPage = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:117"/*SOURCE_FILE_END*/, rootPageID)
        if (initFromRootPage) {
            root_ = BufferManagerPage.readInt4(rootPage, 4)
            countPrimary_ = BufferManagerPage.readInt4(rootPage, 8)
            distinctPrimary_ = BufferManagerPage.readInt4(rootPage, 12)
            firstLeaf_ = BufferManagerPage.readInt4(rootPage, 16)
            if (root_ != NodeManager.nodeNullPointer) {
                nodeManager.getNodeAny(
                    /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:125"/*SOURCE_FILE_END*/,
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
            bufferManager.flushPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:141"/*SOURCE_FILE_END*/, rootPageID)
        }
        bufferManager.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:143"/*SOURCE_FILE_END*/, rootPageID)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun setFirstLeaf(value: Int) {
        val rootPage = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:148"/*SOURCE_FILE_END*/, rootPageID)
        BufferManagerPage.writeInt4(rootPage, 16, value)
        firstLeaf_ = value
        bufferManager.flushPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:151"/*SOURCE_FILE_END*/, rootPageID)
        bufferManager.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:152"/*SOURCE_FILE_END*/, rootPageID)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun setRoot(value: Int) {
        val rootPage = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:157"/*SOURCE_FILE_END*/, rootPageID)
        BufferManagerPage.writeInt4(rootPage, 4, value)
        root_ = value
        bufferManager.flushPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:160"/*SOURCE_FILE_END*/, rootPageID)
        bufferManager.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:161"/*SOURCE_FILE_END*/, rootPageID)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun setCountPrimary(value: Int) {
        val rootPage = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:166"/*SOURCE_FILE_END*/, rootPageID)
        BufferManagerPage.writeInt4(rootPage, 8, value)
        countPrimary_ = value
        bufferManager.flushPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:169"/*SOURCE_FILE_END*/, rootPageID)
        bufferManager.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:170"/*SOURCE_FILE_END*/, rootPageID)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun setDistinctPrimary(value: Int) {
        val rootPage = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:175"/*SOURCE_FILE_END*/, rootPageID)
        BufferManagerPage.writeInt4(rootPage, 12, value)
        distinctPrimary_ = value
        bufferManager.flushPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:178"/*SOURCE_FILE_END*/, rootPageID)
        bufferManager.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:179"/*SOURCE_FILE_END*/, rootPageID)
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun clearCachedHistogram() {
        cachedHistograms1Size = 0
        cachedHistograms2Size = 0
        cachedHistograms1Cursor = 0
        cachedHistograms2Cursor = 0
    }

    private fun checkForCachedHistogram(filter: DictionaryValueTypeArray): Pair<Int, Int>? {
        var res: Pair<Int, Int>? = null
        when (filter.size) {
            0 -> {
                res = Pair(countPrimary_, distinctPrimary_)
            }
            1 -> {
                for (i in 0 until cachedHistograms1Size) {
                    if (cachedHistograms1Values[i] == filter[0]) {
                        res = Pair(cachedHistograms1Response[i * 2 + 0], cachedHistograms1Response[i * 2 + 1])
                        break
                    }
                }
            }
            2 -> {
                for (i in 0 until cachedHistograms2Size) {
                    if (cachedHistograms2Values[i * 2] == filter[0] && cachedHistograms2Values[i * 2 + 1] == filter[1]) {
                        res = Pair(cachedHistograms2Response[i * 2], cachedHistograms2Response[i * 2 + 1])
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

    private fun updateCachedHistogram(filter: DictionaryValueTypeArray, data: Pair<Int, Int>) {
        when (filter.size) {
            1 -> {
                if (cachedHistograms1Size < 100) {
                    val i = cachedHistograms1Size
                    cachedHistograms1Values[i] = filter[0]
                    cachedHistograms1Response[i * 2 + 0] = data.first
                    cachedHistograms1Response[i * 2 + 1] = data.second
                    cachedHistograms1Size++
                    cachedHistograms1Cursor = cachedHistograms1Size
                } else {
                    if (cachedHistograms1Cursor >= 100) {
                        cachedHistograms1Cursor = 0
                    }
                    val i = cachedHistograms1Cursor
                    cachedHistograms1Values[i] = filter[0]
                    cachedHistograms1Response[i * 2 + 0] = data.first
                    cachedHistograms1Response[i * 2 + 1] = data.second
                    cachedHistograms1Cursor++
                }
            }
            2 -> {
                if (cachedHistograms2Size < 100) {
                    val i = cachedHistograms2Size
                    cachedHistograms2Values[i * 2] = filter[0]
                    cachedHistograms2Values[i * 2 + 1] = filter[1]
                    cachedHistograms2Response[i * 2 + 0] = data.first
                    cachedHistograms2Response[i * 2 + 1] = data.second
                    cachedHistograms2Size++
                    cachedHistograms2Cursor = cachedHistograms2Size
                } else {
                    if (cachedHistograms2Cursor >= 100) {
                        cachedHistograms2Cursor = 0
                    }
                    val i = cachedHistograms2Cursor
                    cachedHistograms2Values[i * 2] = filter[0]
                    cachedHistograms2Values[i * 2 + 1] = filter[1]
                    cachedHistograms2Response[i * 2 + 0] = data.first
                    cachedHistograms2Response[i * 2 + 1] = data.second
                    cachedHistograms2Cursor++
                }
            }
        }
    }

    override fun getHistogram(query: IQuery, filter: DictionaryValueTypeArray): Pair<Int, Int> {
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
                        val iterator = NodeInner.iterator1(node, filter, lock, 1, nodeManager, query.getInstance().timeout)
                        var count = 0
                        var distinct = 0
                        var lastValue = iterator.next()
                        if (lastValue != DictionaryValueHelper.nullValue) {
                            distinct++
                            count++
                            var value = iterator.next()
                            while (value != DictionaryValueHelper.nullValue) {
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
                        val iterator = NodeInner.iterator2(node, filter, lock, nodeManager, query.getInstance().timeout)
                        var count = 0
                        while (iterator.next() != DictionaryValueHelper.nullValue) {
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

    override fun getIterator(query: IQuery, filter: DictionaryValueTypeArray, projection: List<String>): IteratorBundle {
println("TripleStoreIndexIDTriple.kt .. getIterator")
        var res: IteratorBundle
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
                val it = NodeInner.iterator3(node, filter, lock, nodeManager, query.getInstance().timeout)
                while (it.next() != DictionaryValueHelper.nullValue) {
                    count++
                }
                res = IteratorBundle(count)
            } else if (filter.size == 2) {
                if (projection[0] == "_") {
                    var count = 0
                    val it = NodeInner.iterator2(node, filter, lock, nodeManager, query.getInstance().timeout)
                    while (it.next() != DictionaryValueHelper.nullValue) {
                        count++
                    }
                    res = IteratorBundle(count)
                } else {
                    columns[projection[0]] = NodeInner.iterator2(node, filter, lock, nodeManager, query.getInstance().timeout)
                }
            } else if (filter.size == 1) {
                if (projection[0] != "_") {
                    columns[projection[0]] = NodeInner.iterator1(node, filter, lock, 1, nodeManager, query.getInstance().timeout)
                    if (projection[1] != "_") {
                        columns[projection[1]] = NodeInner.iterator1(node, filter, lock, 2, nodeManager, query.getInstance().timeout)
                    }
                } else if (projection[1] != "_") {
                    columns[projection[1]] = NodeInner.iterator1(node, filter, lock, 2, nodeManager, query.getInstance().timeout)
                } else {
                    var count = 0
                    val it = NodeInner.iterator1(node, filter, lock, 1, nodeManager, query.getInstance().timeout)
                    while (it.next() != DictionaryValueHelper.nullValue) {
                        count++
                    }
                    res = IteratorBundle(count)
                }
            } else {
                if (projection[0] != "_") {
                    columns[projection[0]] = NodeInner.iterator(node, lock, 0, nodeManager, query.getInstance().timeout)
                    if (projection[1] != "_") {
                        columns[projection[1]] = NodeInner.iterator(node, lock, 1, nodeManager, query.getInstance().timeout)
                        if (projection[2] != "_") {
                            columns[projection[2]] = NodeInner.iterator(node, lock, 2, nodeManager, query.getInstance().timeout)
                        }
                    } else if (projection[2] != "_") {
                        columns[projection[2]] = NodeInner.iterator(node, lock, 2, nodeManager, query.getInstance().timeout)
                    }
                } else if (projection[1] != "_") {
                    columns[projection[1]] = NodeInner.iterator(node, lock, 1, nodeManager, query.getInstance().timeout)
                    if (projection[2] != "_") {
                        columns[projection[2]] = NodeInner.iterator(node, lock, 2, nodeManager, query.getInstance().timeout)
                    }
                } else if (projection[2] != "_") {
                    columns[projection[2]] = NodeInner.iterator(node, lock, 2, nodeManager, query.getInstance().timeout)
                } else {
                    res = IteratorBundle(countPrimary_)
                }
            }
        }
        lock.readUnlock()
        return res
    }

    private fun importHelper(a: Int, b: Int, combinator: (TripleIterator, TripleIterator) -> TripleIterator): Int {
        var nodeA: BufferManagerPageWrapper? = null
        var nodeB: BufferManagerPageWrapper? = null
        nodeManager.getNodeLeaf(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:399"/*SOURCE_FILE_END*/, a) {
            nodeA = it
        }
        nodeManager.getNodeLeaf(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:402"/*SOURCE_FILE_END*/, b) {
            nodeB = it
        }
        val res = importHelper(combinator(NodeLeaf.iterator(nodeA!!, a, nodeManager), NodeLeaf.iterator(nodeB!!, b, nodeManager)))
        nodeManager.freeAllLeaves(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:406"/*SOURCE_FILE_END*/, a)
        nodeManager.freeAllLeaves(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:407"/*SOURCE_FILE_END*/, b)
        return res
    }

    private fun importHelper(iterator: TripleIterator): Int {
        var res = NodeManager.nodeNullPointer
        var node2: BufferManagerPageWrapper? = null
        nodeManager.allocateNodeLeaf(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:414"/*SOURCE_FILE_END*/) { n, i ->
            res = i
            node2 = n
        }
        var nodeid = res
        var node = node2!!
        NodeLeaf.initializeWith(node, iterator)
        while (iterator.hasNext()) {
            nodeManager.allocateNodeLeaf(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:422"/*SOURCE_FILE_END*/) { n, i ->
                NodeShared.setNextNode(node, i)
                nodeManager.flushNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:424"/*SOURCE_FILE_END*/, nodeid)
                nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:425"/*SOURCE_FILE_END*/, nodeid)
                nodeid = i
                node = n
            }
            NodeLeaf.initializeWith(node, iterator)
        }
        nodeManager.flushNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:431"/*SOURCE_FILE_END*/, nodeid)
        nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:432"/*SOURCE_FILE_END*/, nodeid)
        return res
    }

    override fun flush() {
println("TripleStoreIndexIDTriple.kt .. flush ${pendingImport.size}")
        if (pendingImport.size > 0) {
            lock.writeLock()
            flushAssumeLocks()
            lock.writeUnlock()
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun flushContinueWithWriteLock() {
        lock.writeLock()
        flushAssumeLocks()
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun flushContinueWithReadLock() {
        var hasLock = false
        while (pendingImport.size > 0) {
            if (lock.tryWriteLock()) {
                flushAssumeLocks()
                lock.downgradeToReadLock()
                hasLock = true
                break
            } else {
                SanityCheck.suspended {
                    ParallelThread.delay(100)
                }
            }
        }
        if (!hasLock) {
            lock.readLock()
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    private fun collapseList(l: MutableList<Int?>): Int {
        var j = 1
        while (j < l.size) {
            if (l[j] == null) {
                l[j] = l[j - 1]
            } else if (l[j - 1] != null) {
                val a = l[j]!!
                val b = l[j - 1]!!
                l[j] = importHelper(a, b) { x, y -> MergeIterator(x, y) }
            }
            j++
        }
        val res = l[l.size - 1]!!
        l.clear()
        return res
    }

    private fun flushAssumeLocks() {
        if (pendingImport.size > 0) {
            // check again, that there is something to be done ... this may be changed, because there could be someone _else beforehand, holding exactly this lock ... .
            val insertID = collapseList(pendingImport)
            val firstLeaf2: Int
            firstLeaf2 = if (pendingRemove.size > 0) {
                val removeID = collapseList(pendingRemove)
                importHelper(insertID, removeID) { x, y -> MinusIterator(x, y) }
            } else {
                insertID
            }
            var node: BufferManagerPageWrapper? = null
            var flag = false
            nodeManager.getNodeAny(
                /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:503"/*SOURCE_FILE_END*/,
                firstLeaf2,
                {
                    flag = true
                    node = it
                },
                {
                    node = it
                }
            )
            rootNode = null
            setRoot(NodeManager.nodeNullPointer)
            setFirstLeaf(NodeManager.nodeNullPointer)
            if (flag) {
                rebuildData(NodeLeaf.iterator(node!!, firstLeaf2, nodeManager))
            } else {
                rebuildData(NodeInner.iterator(node!!, nodeManager))
            }
            nodeManager.freeAllLeaves(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:521"/*SOURCE_FILE_END*/, firstLeaf2)
        }
    }

    private fun rebuildData(_iterator: TripleIterator) {
println("TripleStoreIndexIDTriple.kt .. rebuildData")
// assuming to have write-lock
        val iterator = Count1PassThroughIterator(DistinctIterator(_iterator))
        if (iterator.hasNext()) {
            var currentLayer = mutableListOf<Int>()
            var node2: BufferManagerPageWrapper? = null
            nodeManager.allocateNodeLeaf(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:532"/*SOURCE_FILE_END*/) { n, i ->
                setFirstLeaf(i)
                node2 = n
                currentLayer.add(i)
            }
            var node = node2!!
            var nodeid = firstLeaf_
            NodeLeaf.initializeWith(node, iterator)
            while (iterator.hasNext()) {
                nodeManager.allocateNodeLeaf(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:541"/*SOURCE_FILE_END*/) { n, i ->
                    NodeShared.setNextNode(node, i)
                    nodeManager.flushNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:543"/*SOURCE_FILE_END*/, nodeid)
                    nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:544"/*SOURCE_FILE_END*/, nodeid)
                    nodeid = i
                    node = n
                    currentLayer.add(i)
                }
                NodeLeaf.initializeWith(node, iterator)
            }
// work around the  here, because the method would be too large
            while (currentLayer.size > 1) {
                val tmp = mutableListOf<Int>()
                var prev2: BufferManagerPageWrapper? = null
                val codeSection1 = {
                    nodeManager.allocateNodeInner(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:556"/*SOURCE_FILE_END*/) { n, i ->
                        nodeManager.flushNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:557"/*SOURCE_FILE_END*/, nodeid)
                        nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:558"/*SOURCE_FILE_END*/, nodeid)
                        nodeid = i
                        tmp.add(i)
                        NodeInner.initializeWith(n, currentLayer, nodeManager)
                        prev2 = n
                    }
                }
                codeSection1()
                var prev = prev2!!
                val codeSection3 = {
                    while (currentLayer.size > 0) {
                        nodeManager.allocateNodeInner(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:569"/*SOURCE_FILE_END*/) { n, i ->
                            nodeManager.flushNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:570"/*SOURCE_FILE_END*/, nodeid)
                            nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:571"/*SOURCE_FILE_END*/, nodeid)
                            nodeid = i
                            tmp.add(i)
                            NodeInner.initializeWith(n, currentLayer, nodeManager)
                            NodeShared.setNextNode(prev, i)
                            prev = n
                        }
                    }
                }
                codeSection3()
                currentLayer = tmp
            }
            nodeManager.flushNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:583"/*SOURCE_FILE_END*/, nodeid)
            nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:584"/*SOURCE_FILE_END*/, nodeid)
            var rootNodeIsLeaf = false
            val codeSection2 = {
                nodeManager.getNodeAny(
                    /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:588"/*SOURCE_FILE_END*/,
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
                    nodeManager.flushNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:599"/*SOURCE_FILE_END*/, nodeid)
                    nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:600"/*SOURCE_FILE_END*/, nodeid)
                    nodeManager.allocateNodeInner(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:601"/*SOURCE_FILE_END*/) { n, i ->
                        NodeInner.initializeWith(n, mutableListOf(currentLayer[0]), nodeManager)
                        rootNode = n
                        setRoot(i)
                        nodeManager.flushNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:605"/*SOURCE_FILE_END*/, root_)
                    }
                }
            }
            codeSection2()
        } else {
// this index is cleared completely
            rootNode = null
            setRoot(NodeManager.nodeNullPointer)
            setFirstLeaf(NodeManager.nodeNullPointer)
        }
        setCountPrimary(iterator.count)
        setDistinctPrimary(iterator.distinct)
        clearCachedHistogram()
    }

    override fun insertAsBulk(data: DictionaryValueTypeArray, order: IntArray, dataSize: Int, isSorted: Boolean) {
println("TripleStoreIndexIDTriple.kt insertAsBulk $isSorted $dataSize")
        if (isSorted) {
            insertAsBulkSorted(data, order, dataSize)
        } else {
            val d = arrayOf(data, DictionaryValueTypeArray(dataSize))
            TripleStoreBulkImportExt.sortUsingBuffers(0, 0, 1, d, dataSize / 3, order)
            insertAsBulkSorted(d[0], order, dataSize)
        }
    }

    override fun removeAsBulk(data: DictionaryValueTypeArray, order: IntArray, dataSize: Int, isSorted: Boolean) {
println("TripleStoreIndexIDTriple.kt removeAsBulk $isSorted $dataSize")
        if (isSorted) {
            removeAsBulkSorted(data, order, dataSize)
        } else {
            val d = arrayOf(data, DictionaryValueTypeArray(dataSize))
            TripleStoreBulkImportExt.sortUsingBuffers(0, 0, 1, d, dataSize / 3, order)
            removeAsBulkSorted(d[0], order, dataSize)
        }
    }

    private fun insertAsBulkSorted(data: DictionaryValueTypeArray, order: IntArray, dataSize: Int) {
println("TripleStoreIndexIDTriple.kt insertAsBulkSorted $dataSize")
        if (dataSize > 0) {
            lock.writeLock()
            if (firstLeaf_ != NodeManager.nodeNullPointer) {
                pendingImport.add(firstLeaf_)
            }
            pendingImport.add(importHelper(BulkImportIterator(data, dataSize, order)))
            if (root_ != NodeManager.nodeNullPointer) {
                nodeManager.freeAllInner(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:652"/*SOURCE_FILE_END*/, root_)
            }
            rootNode = null
            setRoot(NodeManager.nodeNullPointer)
            setFirstLeaf(NodeManager.nodeNullPointer)
            lock.writeUnlock()
        }
    }

    private fun removeAsBulkSorted(data: DictionaryValueTypeArray, order: IntArray, dataSize: Int) {
println("TripleStoreIndexIDTriple.kt removeAsBulkSorted $dataSize")
        if (dataSize > 0) {
            lock.writeLock()
            if (firstLeaf_ != NodeManager.nodeNullPointer) {
                pendingImport.add(firstLeaf_)
            }
            if (pendingImport.size > 0) {
                pendingRemove.add(importHelper(BulkImportIterator(data, dataSize, order)))
            }
            if (root_ != NodeManager.nodeNullPointer) {
                nodeManager.freeAllInner(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:672"/*SOURCE_FILE_END*/, root_)
            }
            rootNode = null
            setRoot(NodeManager.nodeNullPointer)
            setFirstLeaf(NodeManager.nodeNullPointer)
            lock.writeUnlock()
        }
    }

    override fun clear() {
        flushContinueWithWriteLock()
        if (root_ != NodeManager.nodeNullPointer) {
            nodeManager.freeNodeAndAllRelated(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:684"/*SOURCE_FILE_END*/, root_)
            setRoot(NodeManager.nodeNullPointer)
        }
        setFirstLeaf(NodeManager.nodeNullPointer)
        rootNode = null
        clearCachedHistogram()
        lock.writeUnlock()
    }

    override fun delete() {
        clear()
        bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:695"/*SOURCE_FILE_END*/, rootPageID)
        bufferManager.deletePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:696"/*SOURCE_FILE_END*/, rootPageID)
    }

    override fun close() {
        flush()
        if (root_ != NodeManager.nodeNullPointer) {
            nodeManager.releaseNode(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/TripleStoreIndexIDTriple.kt:702"/*SOURCE_FILE_END*/, root_)
        }
    }
}
