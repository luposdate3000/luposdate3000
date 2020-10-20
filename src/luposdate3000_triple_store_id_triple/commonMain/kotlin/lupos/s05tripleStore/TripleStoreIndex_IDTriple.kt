package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.Parallel
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorEmpty
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.IQuery
import lupos.s05tripleStore.index_IDTriple.BulkImportIterator
import lupos.s05tripleStore.index_IDTriple.Count1PassThroughIterator
import lupos.s05tripleStore.index_IDTriple.DebugPassThroughIterator
import lupos.s05tripleStore.index_IDTriple.DistinctIterator
import lupos.s05tripleStore.index_IDTriple.EmptyIterator
import lupos.s05tripleStore.index_IDTriple.MergeIterator
import lupos.s05tripleStore.index_IDTriple.MinusIterator
import lupos.s05tripleStore.index_IDTriple.NodeInner
import lupos.s05tripleStore.index_IDTriple.NodeLeaf
import lupos.s05tripleStore.index_IDTriple.NodeLeafColumnIterator0
import lupos.s05tripleStore.index_IDTriple.NodeLeafColumnIterator1
import lupos.s05tripleStore.index_IDTriple.NodeLeafColumnIterator2
import lupos.s05tripleStore.index_IDTriple.NodeLeafColumnIteratorPrefix1_1
import lupos.s05tripleStore.index_IDTriple.NodeLeafColumnIteratorPrefix1_2
import lupos.s05tripleStore.index_IDTriple.NodeLeafColumnIteratorPrefix2_2
import lupos.s05tripleStore.index_IDTriple.NodeManager
import lupos.s05tripleStore.index_IDTriple.NodeShared
import lupos.s05tripleStore.index_IDTriple.TripleIterator


class TripleStoreIndex_IDTriple (): TripleStoreIndex() {
    @JvmField
    var firstLeaf = NodeManager.nodeNullPointer

    @JvmField
    var root = NodeManager.nodeNullPointer

    @JvmField
    var rootNode: ByteArray? = null

    @JvmField
    var pendingImport = mutableListOf<Int?>()

    @JvmField
    var countPrimary = 0

    @JvmField
    var distinctPrimary = 0

    @JvmField
internal    var lock = MyReadWriteLock()
@JvmField    var cachedHistograms1Size = 0
   @JvmField var cachedHistograms1Cursor = 0
  @JvmField  val cachedHistograms1 = IntArray(300)
  @JvmField  var cachedHistograms2Size = 0
@JvmField    var cachedHistograms2Cursor = 0
@JvmField    val cachedHistograms2 = IntArray(400)

internal    companion object {
        @JvmField
        var debuguuiditerator = 0
@JvmField
var debugLock = MyReadWriteLock()
    }

    suspend override fun safeToFile(filename: String) {
    }

    suspend override fun loadFromFile(filename: String) {
    }

    inline fun clearCachedHistogram() {
        cachedHistograms1Size = 0
        cachedHistograms2Size = 0
        cachedHistograms1Cursor = 0
        cachedHistograms2Cursor = 0
    }

    fun checkForCachedHistogram(filter: IntArray): Pair<Int, Int>? {
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

    fun updateCachedHistogram(filter: IntArray, data: Pair<Int, Int>) {
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

    suspend override fun getHistogram(query: IQuery, params: TripleStoreFeatureParams): Pair<Int, Int> {
        val filter = (params as TripleStoreFeatureParamsDefault).getFilter(query)
        var res: Pair<Int, Int>? = checkForCachedHistogram(filter)
        if (res == null) {
            SanityCheck.println { "readLock(${lock.getUUID()}) x129" }
            lock.readLock()
            val node = rootNode
            if (node != null) {
                when (filter.size) {
                    0 -> {
                        res = Pair(countPrimary, distinctPrimary)
                    }
                    1 -> {
                        var iterator = NodeInner.iterator1(node, filter, lock, 1)
                        var count = 0
                        var distinct = 0
                        var lastValue = iterator.next()
                        if (lastValue != ResultSetDictionaryExt.nullValue) {
                            distinct++
                            count++
                            var value = iterator.next()
                            while (value != ResultSetDictionaryExt.nullValue) {
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
                        var iterator = NodeInner.iterator2(node, filter, lock)
                        var count = 0
                        while (iterator.next() != ResultSetDictionaryExt.nullValue) {
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
            SanityCheck.println { "readUnlock(${lock.getUUID()}) x130" }
            lock.readUnlock()
            updateCachedHistogram(filter, res)
        }
        return res
    }

    suspend override fun getIterator(query: IQuery, params: TripleStoreFeatureParams): IteratorBundle {
        var fp = (params as TripleStoreFeatureParamsDefault).getFilterAndProjection(query)
        val filter = fp.first
        val projection = fp.second
        var res: IteratorBundle
        SanityCheck.check { filter.size >= 0 && filter.size <= 3 }
        SanityCheck.check { projection.size + filter.size == 3 }
        val columns = mutableMapOf<String, ColumnIterator>()
        for (s in projection) {
            if (s != "_") {
                columns[s] = ColumnIteratorEmpty()
            }
        }
        if (columns.size > 0) {
            res = IteratorBundle(columns)
        } else {
            res = IteratorBundle(0)
        }
        flushContinueWithReadLock()
        val node = rootNode
        if (node != null) {
            if (filter.size == 3) {
                var count = 0
                var it = NodeInner.iterator3(node, filter, lock)
                while (it.next() != ResultSetDictionaryExt.nullValue) {
                    count++
                }
                res = IteratorBundle(count)
            } else if (filter.size == 2) {
                if (projection[0] == "_") {
                    var count = 0
                    var it = NodeInner.iterator2(node, filter, lock)
                    while (it.next() != ResultSetDictionaryExt.nullValue) {
                        count++
                    }
                    res = IteratorBundle(count)
                } else {
                    columns[projection[0]] = NodeInner.iterator2(node, filter, lock)
                }
            } else if (filter.size == 1) {
                if (projection[0] != "_") {
                    columns[projection[0]] = NodeInner.iterator1(node, filter, lock, 1)
                    if (projection[1] != "_") {
                        columns[projection[1]] = NodeInner.iterator1(node, filter, lock, 2)
                    }
                } else {
                    SanityCheck.check { projection[1] == "_" }
                    var count = 0
                    var it = NodeInner.iterator1(node, filter, lock, 1)
                    while (it.next() != ResultSetDictionaryExt.nullValue) {
                        count++
                    }
                    res = IteratorBundle(count)
                }
            } else {
                SanityCheck.check { filter.size == 0 }
                if (projection[0] != "_") {
                    columns[projection[0]] = NodeInner.iterator(node, lock, 0)
                    if (projection[1] != "_") {
                        columns[projection[1]] = NodeInner.iterator(node, lock, 1)
                        if (projection[2] != "_") {
                            columns[projection[2]] = NodeInner.iterator(node, lock, 2)
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
        SanityCheck.println { "readUnlock(${lock.getUUID()}) x131" }
        lock.readUnlock()
        return res
    }

internal    suspend fun importHelper(a: TripleIterator, b: TripleIterator): Int {
        return importHelper(MergeIterator(a, b))
    }

internal    suspend fun importHelper(a: Int, b: Int): Int {
        var nodeA: ByteArray? = null
        var nodeB: ByteArray? = null
        SanityCheck.println({ "Outside.refcount($a)  x132" })
        NodeManager.getNodeLeaf(a, {
            nodeA = it
        })
        SanityCheck.println({ "Outside.refcount($b)  x125" })
        NodeManager.getNodeLeaf(b, {
            nodeB = it
        })
        val res = importHelper(MergeIterator(NodeLeaf.iterator(nodeA!!, a), NodeLeaf.iterator(nodeB!!, b)))
        SanityCheck.println({ "Outside.refcount($a)  x133" })
        NodeManager.freeAllLeaves(a)
        SanityCheck.println({ "Outside.refcount($b)  x134" })
        NodeManager.freeAllLeaves(b)
        return res
    }

internal    suspend fun importHelper(iterator: TripleIterator): Int {
        var res = NodeManager.nodeNullPointer
        var node2: ByteArray? = null
        SanityCheck.println({ "Outside.refcount(??) - x135" })
        NodeManager.allocateNodeLeaf { n, i ->
            res = i
            node2 = n
        }
        var nodeid = res
        var node = node2!!
        NodeLeaf.initializeWith(node, nodeid, iterator)
        while (iterator.hasNext()) {
            SanityCheck.println({ "Outside.refcount(??) - x51" })
            NodeManager.allocateNodeLeaf { n, i ->
                SanityCheck.println({ "Outside.refcount($nodeid)  x136" })
                NodeManager.releaseNode(nodeid)
                nodeid = i
                NodeShared.setNextNode(node, i)
                node = n
            }
            NodeLeaf.initializeWith(node, nodeid, iterator)
        }
        SanityCheck.println({ "Outside.refcount($nodeid)  x137" })
        NodeManager.releaseNode(nodeid)
        return res
    }

    suspend override fun flush() {
        if (pendingImport.size > 0) {
            SanityCheck.println { "writeLock(${lock.getUUID()}) x138" }
            lock.writeLock()
            flushAssumeLocks()
            SanityCheck.println { "writeUnlock(${lock.getUUID()}) x139" }
            lock.writeUnlock()
        }
    }

internal    inline suspend fun flushContinueWithWriteLock() {
        SanityCheck.println { "writeLock(${lock.getUUID()}) x140" }
        lock.writeLock()
        flushAssumeLocks()
    }

internal    inline suspend fun flushContinueWithReadLock() {
        var hasLock = false
        while (pendingImport.size > 0) {
            SanityCheck.println { "tryWriteLock(${lock.getUUID()}) x204" }
            if (lock.tryWriteLock()) {
                SanityCheck.println { "tryWriteLock(${lock.getUUID()}) x204 success" }
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
            SanityCheck.println { "readLock(${lock.getUUID()}) x57" }
            lock.readLock()
        }
    }

internal    suspend fun flushAssumeLocks() {
        if (pendingImport.size > 0) {
            //check again, that there is something to be done ... this may be changed, because there could be someone _else beforehand, holding exactly this lock ... .
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
            SanityCheck.println({ "Outside.refcount($firstLeaf2)  x141" })
            NodeManager.getNodeAny(firstLeaf2, {
                flag = true
                node = it
            }, {
                node = it
            })
            SanityCheck.check { rootNode == null }
            SanityCheck.check { root == NodeManager.nodeNullPointer }
            SanityCheck.check { firstLeaf == NodeManager.nodeNullPointer }
            rootNode = null
            root = NodeManager.nodeNullPointer
            firstLeaf = NodeManager.nodeNullPointer
            if (flag) {
                rebuildData(NodeLeaf.iterator(node!!, firstLeaf2))
            } else {
                rebuildData(NodeInner.iterator(node!!))
            }
            SanityCheck.println({ "Outside.refcount($firstLeaf2)  x48" })
            NodeManager.freeAllLeaves(firstLeaf2)
            pendingImport.clear()
        }
    }

    suspend override fun import(dataImport: IntArray, count: Int, order: IntArray) {
        SanityCheck.println { "writeLock(${lock.getUUID()}) x142" }
        lock.writeLock()
        if (count > 0) {
            val iteratorImport = BulkImportIterator(dataImport, count, order)
            val iterator = iteratorImport
            var newFirstLeaf = importHelper(iterator)
            if (firstLeaf != NodeManager.nodeNullPointer) {
                pendingImport.add(firstLeaf)
                SanityCheck.println({ "Outside.refcount($root)  x49" })
                NodeManager.freeAllInnerNodes(root)
                firstLeaf = NodeManager.nodeNullPointer
                SanityCheck.check { root != NodeManager.nodeNullPointer }
                SanityCheck.println({ "Outside.refcount($root)  x60" })
                NodeManager.releaseNode(root)
                root = NodeManager.nodeNullPointer
                rootNode = null
            }
            if (pendingImport.size == 0) {
                pendingImport.add(newFirstLeaf)
            } else if (pendingImport[0] == null) {
                pendingImport[0] = newFirstLeaf
            } else {
                pendingImport[0] = importHelper(pendingImport[0]!!, newFirstLeaf)
                if (pendingImport[pendingImport.size - 1] != null) {
                    pendingImport.add(null)
                }
                var j = 1
                while (j < pendingImport.size) {
                    if (pendingImport[j] == null) {
                        pendingImport[j] = pendingImport[j - 1]
                        pendingImport[j - 1] = null
                        break
                    } else {
                        val a = pendingImport[j]!!
                        val b = pendingImport[j - 1]!!
                        pendingImport[j] = importHelper(a, b)
                        pendingImport[j - 1] = null
                    }
                    j++
                }
            }
        }
        SanityCheck.println { "writeUnlock(${lock.getUUID()}) x61" }
        lock.writeUnlock()
    }

internal    suspend fun rebuildData(_iterator: TripleIterator) {
//assuming to have write-lock
        var iterator: TripleIterator = Count1PassThroughIterator(DistinctIterator(_iterator))
        SanityCheck {
            iterator = DebugPassThroughIterator(iterator)
        }
        if (iterator.hasNext()) {
            var currentLayer = mutableListOf<Int>()
            var node2: ByteArray? = null
            SanityCheck.println({ "Outside.refcount(??) - x52" })
            NodeManager.allocateNodeLeaf { n, i ->
                firstLeaf = i
                node2 = n
                currentLayer.add(i)
            }
            var node = node2!!
            var nodeid = firstLeaf
            NodeLeaf.initializeWith(node, nodeid, iterator)
            while (iterator.hasNext()) {
                SanityCheck.println({ "Outside.refcount(??) - x53" })
                NodeManager.allocateNodeLeaf { n, i ->
                    NodeShared.setNextNode(node, i)
                    SanityCheck.println({ "Outside.refcount(${nodeid})  x143" })
                    NodeManager.releaseNode(nodeid)
                    nodeid = i
                    node = n
                    currentLayer.add(i)
                }
                NodeLeaf.initializeWith(node, nodeid, iterator)
            }
            SanityCheck.check { currentLayer.size > 0 }
            while (currentLayer.size > 1) {
                var tmp = mutableListOf<Int>()
                var prev2: ByteArray? = null
                SanityCheck.println({ "Outside.refcount(??) - x54" })
                NodeManager.allocateNodeInner { n, i ->
                    SanityCheck.println({ "Outside.refcount(${nodeid})  x144" })
                    NodeManager.releaseNode(nodeid)
                    nodeid = i
                    tmp.add(i)
                    NodeInner.initializeWith(n, i, currentLayer)
                    prev2 = n
                }
                var prev = prev2!!
                while (currentLayer.size > 0) {
                    SanityCheck.println({ "Outside.refcount(??) - x55" })
                    NodeManager.allocateNodeInner { n, i ->
                        SanityCheck.println({ "Outside.refcount(${nodeid})  x145" })
                        NodeManager.releaseNode(nodeid)
                        nodeid = i
                        tmp.add(i)
                        NodeInner.initializeWith(n, i, currentLayer)
                        NodeShared.setNextNode(prev, i)
                        prev = n
                    }
                }
                currentLayer = tmp
            }
            SanityCheck.println({ "Outside.refcount(${nodeid})  x146" })
            NodeManager.releaseNode(nodeid)
            var rootNodeIsLeaf = false
            SanityCheck.check { rootNode == null }
            SanityCheck.println({ "Outside.refcount(${currentLayer[0]}) x10" })
            NodeManager.getNodeAny(currentLayer[0], {
                rootNodeIsLeaf = true
            }, {
                rootNode = it
                root = currentLayer[0]
            })
            if (rootNodeIsLeaf) {
                SanityCheck.println({ "Outside.refcount(${nodeid})  x148" })
                NodeManager.releaseNode(nodeid)
                SanityCheck.println({ "Outside.refcount(??) - x56" })
                NodeManager.allocateNodeInner { n, i ->
                    NodeInner.initializeWith(n, i, mutableListOf(currentLayer[0]))
                    rootNode = n
                    root = i
                }
            }
        } else {
//this index is cleared completely
            SanityCheck.check { rootNode == null }
            rootNode = null
            root = NodeManager.nodeNullPointer
            firstLeaf = NodeManager.nodeNullPointer
        }
        SanityCheck.suspended {
            if (firstLeaf != NodeManager.nodeNullPointer) {
                debugLock.writeLock()
                debugLock.writeUnlock()
                val queueS = (iterator as DebugPassThroughIterator).queueS
                val queueP = (iterator as DebugPassThroughIterator).queueP
                val queueO = (iterator as DebugPassThroughIterator).queueO
                var myleaf = ByteArray(0)
//
                NodeManager.getNodeLeaf(firstLeaf) { it ->
                    myleaf = it
                }
                var iterator0 = NodeLeafColumnIterator0(myleaf, firstLeaf, debugLock)
                for (s in queueS) {
                    val tmpa = iterator0.next()
                    SanityCheck.check { tmpa == s }
                }
                val tmpa = iterator0.next()
                SanityCheck.check { tmpa == ResultSetDictionaryExt.nullValue }
                SanityCheck.check { iterator0.label == 0 }
//
                NodeManager.getNodeLeaf(firstLeaf) { it ->
                    myleaf = it
                }
                var iterator1 = NodeLeafColumnIterator1(myleaf, firstLeaf, debugLock)
                for (s in queueP) {
                    val tmpb = iterator1.next()
                    SanityCheck.check { tmpb == s }
                }
                val tmpb = iterator1.next()
                SanityCheck.check { tmpb == ResultSetDictionaryExt.nullValue }
                SanityCheck.check { iterator1.label == 0 }
//
                NodeManager.getNodeLeaf(firstLeaf) { it ->
                    myleaf = it
                }
                var iterator2 = NodeLeafColumnIterator2(myleaf, firstLeaf, debugLock)
                for (s in queueO) {
                    val tmpc = iterator2.next()
                    SanityCheck.check { tmpc == s }
                }
                val tmpc = iterator2.next()
                SanityCheck.check { tmpc == ResultSetDictionaryExt.nullValue }
                SanityCheck.check { iterator2.label == 0 }
//
                if (queueS.size > 0) {
                    var iterator_s = queueS.iterator()
                    var iterator_p = queueP.iterator()
                    var iterator_o = queueO.iterator()
                    NodeManager.getNodeLeaf(firstLeaf) { it ->
                        myleaf = it
                    }
                    var iterator_1_1 = NodeLeafColumnIteratorPrefix1_1(myleaf, firstLeaf, intArrayOf(queueS[0]), debugLock)
                    NodeManager.getNodeLeaf(firstLeaf) { it ->
                        myleaf = it
                    }
                    var iterator_1_2 = NodeLeafColumnIteratorPrefix1_2(myleaf, firstLeaf, intArrayOf(queueS[0]), debugLock)
                    var last_s = iterator_s.next()
                    val tmpd = iterator_1_1.next()
                    SanityCheck.check { tmpd == iterator_p.next() }
                    val tmpe = iterator_1_2.next()
                    SanityCheck.check({ tmpe == iterator_o.next() }, { "$queueS $queueP $queueO $tmpe" })
                    var count = 1
                    var counters = mutableListOf<Int>()
                    while (iterator_s.hasNext()) {
                        var current_s = iterator_s.next()
                        var current_p = iterator_p.next()
                        var current_o = iterator_o.next()
                        val tmpf = iterator_1_1.next()
                        val tmpg = iterator_1_2.next()
                        count++
                        if (last_s == current_s) {
                            SanityCheck.check { tmpf == current_p }
                            SanityCheck.check { tmpg == current_o }
                        } else {
                            SanityCheck.check { tmpf == ResultSetDictionaryExt.nullValue }
                            SanityCheck.check { tmpg == ResultSetDictionaryExt.nullValue }
                            SanityCheck.check { iterator_1_1.label == 0 }
                            SanityCheck.check { iterator_1_2.label == 0 }
                            NodeManager.getNodeLeaf(firstLeaf) { it ->
                                myleaf = it
                            }
                            iterator_1_1 = NodeLeafColumnIteratorPrefix1_1(myleaf, firstLeaf, intArrayOf(current_s), debugLock)
                            NodeManager.getNodeLeaf(firstLeaf) { it ->
                                myleaf = it
                            }
                            iterator_1_2 = NodeLeafColumnIteratorPrefix1_2(myleaf, firstLeaf, intArrayOf(current_s), debugLock)
                            val tmph = iterator_1_1.next()
                            val tmpi = iterator_1_2.next()
                            SanityCheck.check { tmph == current_p }
                            SanityCheck.check { tmpi == current_o }
                            counters.add(count)
                            count = 1
                        }
                        last_s = current_s
                    }
                    counters.add(count)
                    val tmpj = iterator_1_1.next()
                    val tmpk = iterator_1_2.next()
                    SanityCheck.check({ tmpj == ResultSetDictionaryExt.nullValue }, { "$queueS $queueP $queueO $tmpj $counters" })
                    SanityCheck.check { tmpk == ResultSetDictionaryExt.nullValue }
                    SanityCheck.check { iterator_1_1.label == 0 }
                    SanityCheck.check { iterator_1_2.label == 0 }
                }
                if (queueS.size > 0) {
                    var iterator_s = queueS.iterator()
                    var iterator_p = queueP.iterator()
                    var iterator_o = queueO.iterator()
                    NodeManager.getNodeLeaf(firstLeaf) { it ->
                        myleaf = it
                    }
                    var iterator_1_1 = NodeLeafColumnIteratorPrefix1_1(myleaf, firstLeaf, intArrayOf(queueS[0]), debugLock)
                    NodeManager.getNodeLeaf(firstLeaf) { it ->
                        myleaf = it
                    }
                    var iterator_1_2 = NodeLeafColumnIteratorPrefix1_2(myleaf, firstLeaf, intArrayOf(queueS[0]), debugLock)
                    var last_s = iterator_s.next()
                    val tmpd = iterator_1_1.skipSIP(0)
                    SanityCheck.check { tmpd == iterator_p.next() }
                    val tmpe = iterator_1_2.skipSIP(0)
                    SanityCheck.check({ tmpe == iterator_o.next() }, { "$queueS $queueP $queueO $tmpe" })
                    var count = 1
                    var counters = mutableListOf<Int>()
                    while (iterator_s.hasNext()) {
                        var current_s = iterator_s.next()
                        var current_p = iterator_p.next()
                        var current_o = iterator_o.next()
                        count++
                        if (last_s == current_s) {
                            val tmpf = iterator_1_1.skipSIP(0)
                            val tmpg = iterator_1_2.skipSIP(0)
                            SanityCheck.check { tmpf == current_p }
                            SanityCheck.check { tmpg == current_o }
                        } else {
                            iterator_1_1.close()
                            NodeManager.getNodeLeaf(firstLeaf) { it ->
                                myleaf = it
                            }
                            iterator_1_1 = NodeLeafColumnIteratorPrefix1_1(myleaf, firstLeaf, intArrayOf(current_s), debugLock)
                            iterator_1_2.close()
                            NodeManager.getNodeLeaf(firstLeaf) { it ->
                                myleaf = it
                            }
                            iterator_1_2 = NodeLeafColumnIteratorPrefix1_2(myleaf, firstLeaf, intArrayOf(current_s), debugLock)
                            val tmph = iterator_1_1.skipSIP(0)
                            val tmpi = iterator_1_2.skipSIP(0)
                            SanityCheck.check { tmph == current_p }
                            SanityCheck.check { tmpi == current_o }
                            counters.add(count)
                            count = 1
                        }
                        last_s = current_s
                    }
                    counters.add(count)
                    iterator_1_1.close()
                    iterator_1_2.close()
                }
                if (queueS.size > 0) {
                    var iterator_s = queueS.iterator()
                    var iterator_p = queueP.iterator()
                    var iterator_o = queueO.iterator()
                    NodeManager.getNodeLeaf(firstLeaf) { it ->
                        myleaf = it
                    }
                    var iterator_1_1 = NodeLeafColumnIteratorPrefix1_1(myleaf, firstLeaf, intArrayOf(queueS[0]), debugLock)
                    NodeManager.getNodeLeaf(firstLeaf) { it ->
                        myleaf = it
                    }
                    var iterator_1_2 = NodeLeafColumnIteratorPrefix1_2(myleaf, firstLeaf, intArrayOf(queueS[0]), debugLock)
                    var skipping = 1
                    var last_s = iterator_s.next()
                    iterator_p.next()
                    iterator_o.next()
                    var idx = 0
                    var lastreset_idx = 0
                    while (iterator_s.hasNext()) {
                        idx++
                        var current_s = iterator_s.next()
                        var current_p = iterator_p.next()
                        var current_o = iterator_o.next()
                        if (skipping == 0) {
                            skipping = 1
                        } else {
                            if (last_s == current_s) {
                                val tmpf = iterator_1_1.skipSIP(1)
                                val tmpg = iterator_1_2.skipSIP(1)
                                SanityCheck.check({ tmpg == current_o }, { "1_2 $queueS $queueP $queueO $tmpg $idx $lastreset_idx $current_o" })
                                SanityCheck.check({ tmpf == current_p }, { "1_1 $queueS $queueP $queueO $tmpf $idx $lastreset_idx $current_p" }) //error is here xxxx
                            }
                            skipping = 0
                        }
                        if (last_s != current_s) {
                            lastreset_idx = idx
                            iterator_1_1.close()
                            NodeManager.getNodeLeaf(firstLeaf) { it ->
                                myleaf = it
                            }
                            iterator_1_1 = NodeLeafColumnIteratorPrefix1_1(myleaf, firstLeaf, intArrayOf(current_s), debugLock)
                            iterator_1_2.close()
                            NodeManager.getNodeLeaf(firstLeaf) { it ->
                                myleaf = it
                            }
                            iterator_1_2 = NodeLeafColumnIteratorPrefix1_2(myleaf, firstLeaf, intArrayOf(current_s), debugLock)
                            skipping = 1
                        }
                        last_s = current_s
                    }
                    iterator_1_1.close()
                    iterator_1_2.close()
                }
                if (queueS.size > 0) {
                    var iterator_s = queueS.iterator()
                    var iterator_p = queueP.iterator()
                    var iterator_o = queueO.iterator()
                    NodeManager.getNodeLeaf(firstLeaf) { it ->
                        myleaf = it
                    }
                    var iterator_2_2 = NodeLeafColumnIteratorPrefix2_2(myleaf, firstLeaf, intArrayOf(queueS[0], queueP[0]), debugLock)
                    var last_s = iterator_s.next()
                    var last_p = iterator_p.next()
                    val tmpo = iterator_2_2.next()
                    SanityCheck.check({ tmpo == iterator_o.next() }, { "$queueS $queueP $queueO $tmpo" })
                    while (iterator_s.hasNext()) {
                        var current_s = iterator_s.next()
                        var current_p = iterator_p.next()
                        var current_o = iterator_o.next()
                        val tmpl = iterator_2_2.next()
                        if (last_s == current_s && last_p == current_p) {
                            SanityCheck.check { tmpl == current_o }
                        } else {
                            SanityCheck.check { tmpl == ResultSetDictionaryExt.nullValue }
                            SanityCheck.check { iterator_2_2.label == 0 }
                            NodeManager.getNodeLeaf(firstLeaf) { it ->
                                myleaf = it
                            }
                            iterator_2_2 = NodeLeafColumnIteratorPrefix2_2(myleaf, firstLeaf, intArrayOf(current_s, current_p), debugLock)
                            val tmpm = iterator_2_2.next()
                            SanityCheck.check { tmpm == current_o }
                        }
                        last_s = current_s
                        last_p = current_p
                    }
                    val tmpn = iterator_2_2.next()
                    SanityCheck.check { tmpn == ResultSetDictionaryExt.nullValue }
                    SanityCheck.check { iterator_2_2.label == 0 }
                }
                debugLock.writeLock()
                debugLock.writeUnlock()
            }
//
            iterator = (iterator as DebugPassThroughIterator).a
        }
        countPrimary = (iterator as Count1PassThroughIterator).count
        distinctPrimary = (iterator as Count1PassThroughIterator).distinct
        clearCachedHistogram()
    }

    suspend override fun insertAsBulk(data: IntArray, order: IntArray) {
        flushContinueWithWriteLock()
        var d = arrayOf(data, IntArray(data.size))
        TripleStoreBulkImportExt.sortUsingBuffers(0, 0, 1, d, data.size / 3, order)
        val iteratorImport = BulkImportIterator(d[0], data.size, order)
        var iteratorStore2: TripleIterator? = null
        if (firstLeaf == NodeManager.nodeNullPointer) {
            iteratorStore2 = EmptyIterator()
        } else {
            SanityCheck.println({ "Outside.refcount($firstLeaf)  x12" })
            NodeManager.getNodeLeaf(firstLeaf, {
                iteratorStore2 = NodeLeaf.iterator(it, firstLeaf)
            })
        }
        val iteratorStore = iteratorStore2!!
        val iterator = MergeIterator(iteratorStore, iteratorImport)
        val oldroot = root
        rootNode = null
        root = NodeManager.nodeNullPointer
        firstLeaf = NodeManager.nodeNullPointer
        rebuildData(iterator)
        if (oldroot != NodeManager.nodeNullPointer) {
            SanityCheck.println({ "Outside.refcount($oldroot)  x149" })
            NodeManager.freeNodeAndAllRelated(oldroot)
        }
        SanityCheck.println { "writeUnlock(${lock.getUUID()}) x62" }
        lock.writeUnlock()
    }

    suspend override fun removeAsBulk(data: IntArray, order: IntArray) {
        flushContinueWithWriteLock()
        var d = arrayOf(data, IntArray(data.size))
        TripleStoreBulkImportExt.sortUsingBuffers(0, 0, 1, d, data.size / 3, order)
        val iteratorImport = BulkImportIterator(d[0], data.size, order)
        var iteratorStore2: TripleIterator? = null
        if (firstLeaf == NodeManager.nodeNullPointer) {
            iteratorStore2 = EmptyIterator()
        } else {
            SanityCheck.println({ "Outside.refcount($firstLeaf)  x13" })
            NodeManager.getNodeLeaf(firstLeaf, {
                iteratorStore2 = NodeLeaf.iterator(it, firstLeaf)
            })
        }
        val iteratorStore = iteratorStore2!!
        val iterator = MinusIterator(iteratorStore, iteratorImport)
        val oldroot = root
        rootNode = null
        root = NodeManager.nodeNullPointer
        firstLeaf = NodeManager.nodeNullPointer
        rebuildData(iterator)
        if (oldroot != NodeManager.nodeNullPointer) {
            SanityCheck.println({ "Outside.refcount($oldroot)  x150" })
            NodeManager.freeNodeAndAllRelated(oldroot)
        }
        SanityCheck.println { "writeUnlock(${lock.getUUID()}) x63" }
        lock.writeUnlock()
    }

    override fun insert(a: Int, b: Int, c: Int) {
        SanityCheck.checkUnreachable()
    }

    override fun remove(a: Int, b: Int, c: Int) {
        SanityCheck.checkUnreachable()
    }

    suspend override fun clear() {
        flushContinueWithWriteLock()
        if (root != NodeManager.nodeNullPointer) {
            SanityCheck.println({ "Outside.refcount($root)  x151" })
            NodeManager.freeNodeAndAllRelated(root)
            root = NodeManager.nodeNullPointer
        }
        firstLeaf = NodeManager.nodeNullPointer
        rootNode = null
        clearCachedHistogram()
        SanityCheck.println { "writeUnlock(${lock.getUUID()}) x64" }
        lock.writeUnlock()
    }

    suspend override fun printContents() {
        SanityCheck.println { "readLock(${lock.getUUID()}) x65" }
        lock.readLock()
        if (firstLeaf != NodeManager.nodeNullPointer) {
            SanityCheck.println({ "Outside.refcount($firstLeaf)  x122" })
            NodeManager.getNodeLeaf(firstLeaf, { node ->
                var it = NodeLeaf.iterator(node, firstLeaf)
                while (it.hasNext()) {
                    var d = it.next()
                    SanityCheck.println({ "debug ${d.map { it }}" })
                }
            })
        }
        SanityCheck.println { "readUnlock(${lock.getUUID()}) x66" }
        lock.readUnlock()
    }
}
