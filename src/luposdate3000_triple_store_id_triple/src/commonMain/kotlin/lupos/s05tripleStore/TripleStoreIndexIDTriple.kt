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

import lupos.ProguardTestAnnotation
import lupos.buffermanager.BufferManager
import lupos.buffermanager.BufferManagerExt
import lupos.buffermanager.BufferManagerPage
import lupos.dictionary.DictionaryExt
import lupos.s00misc.ETripleIndexTypeExt
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.Parallel
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorEmpty
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s05tripleStore.index_IDTriple.BulkImportIterator
import lupos.s05tripleStore.index_IDTriple.Count1PassThroughIterator
import lupos.s05tripleStore.index_IDTriple.DistinctIterator
import lupos.s05tripleStore.index_IDTriple.EmptyIterator
import lupos.s05tripleStore.index_IDTriple.MergeIterator
import lupos.s05tripleStore.index_IDTriple.MinusIterator
import lupos.s05tripleStore.index_IDTriple.NodeInner
import lupos.s05tripleStore.index_IDTriple.NodeLeaf
import lupos.s05tripleStore.index_IDTriple.NodeManager
import lupos.s05tripleStore.index_IDTriple.NodeShared
import lupos.s05tripleStore.index_IDTriple.TripleIterator
import kotlin.jvm.JvmField

public class TripleStoreIndexIDTriple : TripleStoreIndex {
    private val bufferManager: BufferManager
    private val rootPageID: Int
    public override fun getRootPageID(): Int = rootPageID

    public constructor(rootPageID: Int, initFromRootPage: Boolean) : this(BufferManagerExt.getBuffermanager("stores"), rootPageID, initFromRootPage)

    @ProguardTestAnnotation
    public constructor(bufferManager: BufferManager, rootPageID: Int, initFromRootPage: Boolean) {
        this.bufferManager = bufferManager
        this.rootPageID = rootPageID
        nodeManager = NodeManager(bufferManager)
        val rootPage = bufferManager.getPage(lupos.SOURCE_FILE, rootPageID)
        if (initFromRootPage) {
            root = rootPage.readInt4(4)
            countPrimary = rootPage.readInt4(8)
            distinctPrimary = rootPage.readInt4(12)
            firstLeaf = rootPage.readInt4(16)
            if (root != NodeManager.nodeNullPointer) {
                nodeManager.getNodeAny(
                    lupos.SOURCE_FILE,
                    root,
                    {
                        SanityCheck.checkUnreachable()
                    },
                    {
                        rootNode = it
                    }
                )
            }
        } else {
            rootPage.writeInt4(0, ETripleIndexTypeExt.ID_TRIPLE)
            rootPage.writeInt4(4, root)
            rootPage.writeInt4(8, countPrimary)
            rootPage.writeInt4(12, distinctPrimary)
            rootPage.writeInt4(16, firstLeaf)
            bufferManager.flushPage(lupos.SOURCE_FILE, rootPageID)
        }
        bufferManager.releasePage(lupos.SOURCE_FILE, rootPageID)
    }

    private val nodeManager: NodeManager

    private var firstLeaf_: Int = NodeManager.nodeNullPointer
    private var firstLeaf: Int
        set(value) {
            val rootPage = bufferManager.getPage(lupos.SOURCE_FILE, rootPageID)
            rootPage.writeInt4(16, value)
            firstLeaf_ = value
            bufferManager.flushPage(lupos.SOURCE_FILE, rootPageID)
            bufferManager.releasePage(lupos.SOURCE_FILE, rootPageID)
        }
        get() = firstLeaf_

    private var root_: Int = NodeManager.nodeNullPointer
    private var root: Int
        set(value) {
            val rootPage = bufferManager.getPage(lupos.SOURCE_FILE, rootPageID)
            rootPage.writeInt4(4, value)
            root_ = value
            bufferManager.flushPage(lupos.SOURCE_FILE, rootPageID)
            bufferManager.releasePage(lupos.SOURCE_FILE, rootPageID)
        }
        get() = root_

    private var countPrimary_: Int = 0
    private var countPrimary: Int
        set(value) {
            val rootPage = bufferManager.getPage(lupos.SOURCE_FILE, rootPageID)
            rootPage.writeInt4(8, value)
            countPrimary_ = value
            bufferManager.flushPage(lupos.SOURCE_FILE, rootPageID)
            bufferManager.releasePage(lupos.SOURCE_FILE, rootPageID)
        }
        get() = countPrimary_

    private var distinctPrimary_: Int = 0
    private var distinctPrimary: Int
        set(value) {
            val rootPage = bufferManager.getPage(lupos.SOURCE_FILE, rootPageID)
            rootPage.writeInt4(12, value)
            distinctPrimary_ = value
            bufferManager.flushPage(lupos.SOURCE_FILE, rootPageID)
            bufferManager.releasePage(lupos.SOURCE_FILE, rootPageID)
        }
        get() = distinctPrimary_

    private var rootNode: BufferManagerPage? = null

    private var pendingImport: MutableList<Int?> = mutableListOf()

    @JvmField
    internal var lock = MyReadWriteLock()

    private var cachedHistograms1Size: Int = 0

    private var cachedHistograms1Cursor: Int = 0

    private val cachedHistograms1: IntArray = IntArray(300)

    private var cachedHistograms2Size: Int = 0

    private var cachedHistograms2Cursor: Int = 0

    private val cachedHistograms2: IntArray = IntArray(400)

    private companion object {

        var debugLock = MyReadWriteLock()
    }

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
                res = Pair(countPrimary, distinctPrimary)
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
                        res = Pair(countPrimary, distinctPrimary)
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
                    //SanityCheck.check { projection[1] == "_" }
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
                    res = IteratorBundle(countPrimary)
                }
            }
        }
        lock.readUnlock()
        return res
    }

    private fun importHelper(a: Int, b: Int): Int {
        var nodeA: BufferManagerPage? = null
        var nodeB: BufferManagerPage? = null
        nodeManager.getNodeLeaf(lupos.SOURCE_FILE, a) {
            nodeA = it
        }
        nodeManager.getNodeLeaf(lupos.SOURCE_FILE, b) {
            nodeB = it
        }
        val res = importHelper(MergeIterator(NodeLeaf.iterator(nodeA!!, a, nodeManager), NodeLeaf.iterator(nodeB!!, b, nodeManager)))
        nodeManager.freeAllLeaves(lupos.SOURCE_FILE, a)
        nodeManager.freeAllLeaves(lupos.SOURCE_FILE, b)
        return res
    }

    private fun importHelper(iterator: TripleIterator): Int {
        var res = NodeManager.nodeNullPointer
        var node2: BufferManagerPage? = null
        nodeManager.allocateNodeLeaf(lupos.SOURCE_FILE) { n, i ->
            res = i
            node2 = n
        }
        var nodeid = res
        var node = node2!!
        NodeLeaf.initializeWith(node, nodeid, iterator)
        while (iterator.hasNext()) {
            nodeManager.allocateNodeLeaf(lupos.SOURCE_FILE) { n, i ->
                NodeShared.setNextNode(node, i)
                nodeManager.flushNode(lupos.SOURCE_FILE, nodeid)
                nodeManager.releaseNode(lupos.SOURCE_FILE, nodeid)
                nodeid = i
                node = n
            }
            NodeLeaf.initializeWith(node, nodeid, iterator)
        }
        nodeManager.flushNode(lupos.SOURCE_FILE, nodeid)
        nodeManager.releaseNode(lupos.SOURCE_FILE, nodeid)
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
            var node: BufferManagerPage? = null
            var flag = false
            nodeManager.getNodeAny(
                lupos.SOURCE_FILE,
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
            SanityCheck.check { root == NodeManager.nodeNullPointer }
            SanityCheck.check { firstLeaf == NodeManager.nodeNullPointer }
            rootNode = null
            root = NodeManager.nodeNullPointer
            firstLeaf = NodeManager.nodeNullPointer
            if (flag) {
                rebuildData(NodeLeaf.iterator(node!!, firstLeaf2, nodeManager))
            } else {
                rebuildData(NodeInner.iterator(node!!, nodeManager))
            }
            nodeManager.freeAllLeaves(lupos.SOURCE_FILE, firstLeaf2)
            pendingImport.clear()
        }
    }

    private fun rebuildData(_iterator: TripleIterator) {
// assuming to have write-lock
        var iterator = Count1PassThroughIterator(DistinctIterator(_iterator))
        if (iterator.hasNext()) {
            var currentLayer = mutableListOf<Int>()
            var node2: BufferManagerPage? = null
            nodeManager.allocateNodeLeaf(lupos.SOURCE_FILE) { n, i ->
                firstLeaf = i
                node2 = n
                currentLayer.add(i)
            }
            var node = node2!!
            var nodeid = firstLeaf
            NodeLeaf.initializeWith(node, nodeid, iterator)
            while (iterator.hasNext()) {
                nodeManager.allocateNodeLeaf(lupos.SOURCE_FILE) { n, i ->
                    NodeShared.setNextNode(node, i)
                    nodeManager.flushNode(lupos.SOURCE_FILE, nodeid)
                    nodeManager.releaseNode(lupos.SOURCE_FILE, nodeid)
                    nodeid = i
                    node = n
                    currentLayer.add(i)
                }
                NodeLeaf.initializeWith(node, nodeid, iterator)
            }
            SanityCheck.check { currentLayer.size > 0 }
            val rebuildDataPart1 = {
// work around the crossinline here, because the method would be too large
                while (currentLayer.size > 1) {
                    val tmp = mutableListOf<Int>()
                    var prev2: BufferManagerPage? = null
                    nodeManager.allocateNodeInner(lupos.SOURCE_FILE) { n, i ->
                        nodeManager.flushNode(lupos.SOURCE_FILE, nodeid)
                        nodeManager.releaseNode(lupos.SOURCE_FILE, nodeid)
                        nodeid = i
                        tmp.add(i)
                        NodeInner.initializeWith(n, i, currentLayer, nodeManager)
                        prev2 = n
                    }
                    var prev = prev2!!
                    while (currentLayer.size > 0) {
                        nodeManager.allocateNodeInner(lupos.SOURCE_FILE) { n, i ->
                            nodeManager.flushNode(lupos.SOURCE_FILE, nodeid)
                            nodeManager.releaseNode(lupos.SOURCE_FILE, nodeid)
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
            rebuildDataPart1()
            nodeManager.flushNode(lupos.SOURCE_FILE, nodeid)
            nodeManager.releaseNode(lupos.SOURCE_FILE, nodeid)
            var rootNodeIsLeaf = false
            SanityCheck.check { rootNode == null }
            nodeManager.getNodeAny(
                lupos.SOURCE_FILE,
                currentLayer[0],
                {
                    rootNodeIsLeaf = true
                },
                {
                    rootNode = it
                    root = currentLayer[0]
                }
            )
            if (rootNodeIsLeaf) {
                nodeManager.flushNode(lupos.SOURCE_FILE, nodeid)
                nodeManager.releaseNode(lupos.SOURCE_FILE, nodeid)
                nodeManager.allocateNodeInner(lupos.SOURCE_FILE) { n, i ->
                    NodeInner.initializeWith(n, i, mutableListOf(currentLayer[0]), nodeManager)
                    rootNode = n
                    root = i
                    nodeManager.flushNode(lupos.SOURCE_FILE, root)
                }
            }
        } else {
// this index is cleared completely
            SanityCheck.check { rootNode == null }
            rootNode = null
            root = NodeManager.nodeNullPointer
            firstLeaf = NodeManager.nodeNullPointer
        }
        countPrimary = iterator.count
        distinctPrimary = iterator.distinct
        clearCachedHistogram()
    }

    override fun insertAsBulk(data: IntArray, order: IntArray, dataSize: Int) {
        SanityCheck {
            for (i in 0 until dataSize / 3) {
                // println("insertAsBulk (${data[i * 3 + order[0]].toString(16).padStart(8, '0')},${data[i * 3 + order[1]].toString(16).padStart(8, '0')},${data[i * 3 + order[2]].toString(16).padStart(8, '0')})")
            }
        }
        flushContinueWithWriteLock()
        val d = arrayOf(data, IntArray(dataSize))
        TripleStoreBulkImportExt.sortUsingBuffers(0, 0, 1, d, dataSize / 3, order)
        val iteratorImport = BulkImportIterator(d[0], dataSize, order)
        var iteratorStore2: TripleIterator? = null
        if (firstLeaf == NodeManager.nodeNullPointer) {
            iteratorStore2 = EmptyIterator()
        } else {
            nodeManager.getNodeLeaf(lupos.SOURCE_FILE, firstLeaf) {
                iteratorStore2 = NodeLeaf.iterator(it, firstLeaf, nodeManager)
            }
        }
        val iteratorStore = iteratorStore2!!
        val iterator = MergeIterator(iteratorStore, iteratorImport)
        val oldroot = root
        rootNode = null
        root = NodeManager.nodeNullPointer
        firstLeaf = NodeManager.nodeNullPointer
        rebuildData(iterator)
        if (oldroot != NodeManager.nodeNullPointer) {
            nodeManager.freeNodeAndAllRelated(lupos.SOURCE_FILE, oldroot)
        }
        lock.writeUnlock()
    }

    override fun removeAsBulk(data: IntArray, order: IntArray, dataSize: Int) {
        SanityCheck {
            for (i in 0 until dataSize / 3) {
                // println("removeAsBulk (0x${data[i * 3 + order[0]].toString(16).padStart(8, '0')},0x${data[i * 3 + order[1]].toString(16).padStart(8, '0')},0x${data[i * 3 + order[2]].toString(16).padStart(8, '0')})")
            }
        }
        flushContinueWithWriteLock()
        val d = arrayOf(data, IntArray(dataSize))
        TripleStoreBulkImportExt.sortUsingBuffers(0, 0, 1, d, dataSize / 3, order)
        val iteratorImport = BulkImportIterator(d[0], dataSize, order)
        var iteratorStore2: TripleIterator? = null
        if (firstLeaf == NodeManager.nodeNullPointer) {
            iteratorStore2 = EmptyIterator()
        } else {
            nodeManager.getNodeLeaf(lupos.SOURCE_FILE, firstLeaf) {
                iteratorStore2 = NodeLeaf.iterator(it, firstLeaf, nodeManager)
            }
        }
        val iteratorStore = iteratorStore2!!
        val iterator = MinusIterator(iteratorStore, iteratorImport)
        val oldroot = root
        rootNode = null
        root = NodeManager.nodeNullPointer
        firstLeaf = NodeManager.nodeNullPointer
        rebuildData(iterator)
        if (oldroot != NodeManager.nodeNullPointer) {
            nodeManager.freeNodeAndAllRelated(lupos.SOURCE_FILE, oldroot)
        }
        lock.writeUnlock()
    }

    override fun clear() {
        flushContinueWithWriteLock()
        if (root != NodeManager.nodeNullPointer) {
            nodeManager.freeNodeAndAllRelated(lupos.SOURCE_FILE, root)
            root = NodeManager.nodeNullPointer
        }
        firstLeaf = NodeManager.nodeNullPointer
        rootNode = null
        clearCachedHistogram()
        lock.writeUnlock()
    }

    override fun delete() {
        clear()
        bufferManager.getPage(lupos.SOURCE_FILE, rootPageID)
        bufferManager.deletePage(lupos.SOURCE_FILE, rootPageID)
    }

    override fun close() {
        flush()
        if (root != NodeManager.nodeNullPointer) {
            nodeManager.releaseNode(lupos.SOURCE_FILE, root)
        }
    }
}
