package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.EBenchmark
import lupos.s00misc.File
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorEmpty
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.Query
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

class TripleStoreIndex_IDTriple : TripleStoreIndex() {
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
    var lock = ReadWriteLock()

    companion object {
        @JvmField
        var debuguuiditerator = 0
    }

    suspend override fun safeToFile(filename: String) {
/*
        flushContinueWithReadLock()
        SanityCheck.suspended {
            if (root != NodeManager.nodeNullPointer) {
                var found = false
                SanityCheck.println({ "Outside.refcount($root) ${NodeManager.bufferManager.allPagesRefcounters[root]} x25" })
                NodeManager.getNodeAny(root, {
                    SanityCheck.checkUnreachable()
                }, {
                    found = true
                    SanityCheck.check { rootNode == it }
                })
SanityCheck.println({ "Outside.refcount($root) ${NodeManager.bufferManager.allPagesRefcounters[root]} x80" })
		NodeManager.releaseNode(root)
                SanityCheck.check { found }
            } else {
                SanityCheck.check { rootNode == null }
            }
        }
        File(filename).dataOutputStream { out ->
            out.writeInt(firstLeaf)
            out.writeInt(root)
            out.writeInt(countPrimary)
            out.writeInt(distinctPrimary)
        }
        SanityCheck.suspended {
            SanityCheck.println { firstLeaf }
            SanityCheck.println { root }
            SanityCheck.println { countPrimary }
            SanityCheck.println { distinctPrimary }
            if (rootNode != null) {
                val iterator = NodeInner.iterator(rootNode!!)
                while (iterator.hasNext()) {
                    SanityCheck.println { iterator.next().map { it } }
                }
            }
        }
        lock.readUnlock()
*/
    }

    suspend override fun loadFromFile(filename: String) {
/*
        clear()
	lock.writeLock()
SanityCheck.check{rootNode==null}
        File(filename).dataInputStreamSuspended { fis ->
            firstLeaf = fis.readInt()
            root = fis.readInt()
            countPrimary = fis.readInt()
            distinctPrimary = fis.readInt()
            if (root == NodeManager.nodeNullPointer) {
                rootNode = null
            } else {
                SanityCheck.println({ "Outside.refcount($root) ${NodeManager.bufferManager.allPagesRefcounters[root]} x15" })
                NodeManager.getNodeInner(root, {
			"xxx refcount of rootnode is too high here"
                    rootNode = it
                })
                SanityCheck.check { rootNode != null }
            }
        }
        SanityCheck.suspended {
            SanityCheck.println { firstLeaf }
            SanityCheck.println { root }
            SanityCheck.println { countPrimary }
            SanityCheck.println { distinctPrimary }
            if (rootNode != null) {
                val iterator = NodeInner.iterator(rootNode!!)
                while (iterator.hasNext()) {
                    SanityCheck.println { iterator.next().map { it } }
                }
            }
        }
        lock.writeUnlock()
*/
    }

    var cachedHistograms1Size = 0
    var cachedHistograms1Cursor = 0
    val cachedHistograms1 = IntArray(300)
    var cachedHistograms2Size = 0
    var cachedHistograms2Cursor = 0
    val cachedHistograms2 = IntArray(400)
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

    suspend override fun getHistogram(query: Query, params: TripleStoreFeatureParams): Pair<Int, Int> {
        val filter = (params as TripleStoreFeatureParamsDefault).getFilter(query)
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
                        var iterator = NodeInner.iterator1(node, filter, lock, 1)
                        var count = 0
                        var distinct = 0
                        var lastValue = iterator.next()
                        if (lastValue != ResultSetDictionary.nullValue) {
                            distinct++
                            count++
                            var value = iterator.next()
                            while (value != ResultSetDictionary.nullValue) {
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
                        while (iterator.next() != ResultSetDictionary.nullValue) {
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
            updateCachedHistogram(filter, res!!)
        }
        return res!!
    }

    suspend override fun getIterator(query: Query, params: TripleStoreFeatureParams): IteratorBundle {
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
                while (it.next() != ResultSetDictionary.nullValue) {
                    count++
                }
                res = IteratorBundle(count)
            } else if (filter.size == 2) {
                if (projection[0] == "_") {
                    var count = 0
                    var it = NodeInner.iterator2(node, filter, lock)
                    while (it.next() != ResultSetDictionary.nullValue) {
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
                    while (it.next() != ResultSetDictionary.nullValue) {
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
        lock.readUnlock()
        return res
    }

    suspend fun importHelper(a: TripleIterator, b: TripleIterator): Int {
        return importHelper(MergeIterator(a, b))
    }

    suspend fun importHelper(a: Int, b: Int): Int {
        var nodeA: ByteArray? = null
        var nodeB: ByteArray? = null
        SanityCheck.println({ "Outside.refcount($a) ${NodeManager.bufferManager.allPagesRefcounters[a]} x10" })
        NodeManager.getNodeLeaf(a, {
            nodeA = it
        })
        SanityCheck.println({ "Outside.refcount($b) ${NodeManager.bufferManager.allPagesRefcounters[b]} x11" })
        NodeManager.getNodeLeaf(b, {
            nodeB = it
        })
        val res = importHelper(MergeIterator(NodeLeaf.iterator(nodeA!!, a), NodeLeaf.iterator(nodeB!!, b)))
        SanityCheck.println({ "Outside.refcount($a) ${NodeManager.bufferManager.allPagesRefcounters[a]} x46" })
        NodeManager.freeAllLeaves(a)
        SanityCheck.println({ "Outside.refcount($b) ${NodeManager.bufferManager.allPagesRefcounters[b]} x47" })
        NodeManager.freeAllLeaves(b)
        return res
    }

    suspend fun importHelper(iterator: TripleIterator): Int {
        var res = NodeManager.nodeNullPointer
        var node2: ByteArray? = null
        SanityCheck.println({ "Outside.refcount(??) - x50" })
        NodeManager.allocateNodeLeaf { n, i ->
            res = i
            node2 = n
        }
        var nodeid = res
        var node = node2!!
        NodeLeaf.initializeWith(node, iterator)
        while (iterator.hasNext()) {
            SanityCheck.println({ "Outside.refcount(??) - x51" })
            NodeManager.allocateNodeLeaf { n, i ->
                SanityCheck.println({ "Outside.refcount($nodeid) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x61" })
                NodeManager.releaseNode(nodeid)
                nodeid = i
                NodeShared.setNextNode(node, i)
                node = n
            }
            NodeLeaf.initializeWith(node, iterator)
        }
        SanityCheck.println({ "Outside.refcount($nodeid) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x62" })
        NodeManager.releaseNode(nodeid)
        return res
    }

    suspend override fun flush() {
        if (pendingImport.size > 0) {
            lock.writeLock()
            flushAssumeLocks()
            lock.writeUnlock()
        }
    }

    inline suspend fun flushContinueWithWriteLock() {
        lock.writeLock()
        flushAssumeLocks()
    }

    inline suspend fun flushContinueWithReadLock() {
        lock.readLock()
        if (pendingImport.size > 0) {
            lock.readUnlock()
            lock.writeLock()
            flushAssumeLocks()
            lock.downgradeToReadLock()
        }
    }

    suspend fun flushAssumeLocks() {
        if (pendingImport.size > 0) {
            //check again, that there is something to be done ... this may be changed, because there could be someone _else beforehand, holding exactly this lock ... .
            BenchmarkUtils.start(EBenchmark.IMPORT_REBUILD_MAP)
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
            SanityCheck.println({ "Outside.refcount($firstLeaf2) ${NodeManager.bufferManager.allPagesRefcounters[firstLeaf2]} x26" })
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
            SanityCheck.println({ "Outside.refcount($firstLeaf2) ${NodeManager.bufferManager.allPagesRefcounters[firstLeaf2]} x48" })
            NodeManager.freeAllLeaves(firstLeaf2)
            pendingImport.clear()
            BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_REBUILD_MAP)
        }
    }

    suspend override fun import(dataImport: IntArray, count: Int, order: IntArray) {
        lock.writeLock()
        BenchmarkUtils.start(EBenchmark.IMPORT_MERGE_DATA)
        if (count > 0) {
            val iteratorImport = BulkImportIterator(dataImport, count, order)
            val iterator = iteratorImport
            var newFirstLeaf = importHelper(iterator)
            if (firstLeaf != NodeManager.nodeNullPointer) {
                pendingImport.add(firstLeaf)
                SanityCheck.println({ "Outside.refcount($root) ${NodeManager.bufferManager.allPagesRefcounters[root]} x49" })
                NodeManager.freeAllInnerNodes(root)
                firstLeaf = NodeManager.nodeNullPointer
                SanityCheck.check { root != NodeManager.nodeNullPointer }
                SanityCheck.println({ "Outside.refcount($root) ${NodeManager.bufferManager.allPagesRefcounters[root]} x60" })
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
        BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_MERGE_DATA)
        lock.writeUnlock()
    }

    suspend fun rebuildData(_iterator: TripleIterator) {
//assuming to have write-lock
        val iterator = Count1PassThroughIterator(DistinctIterator(_iterator))
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
            NodeLeaf.initializeWith(node, iterator)
            while (iterator.hasNext()) {
                SanityCheck.println({ "Outside.refcount(??) - x53" })
                NodeManager.allocateNodeLeaf { n, i ->
                    NodeShared.setNextNode(node, i)
                    SanityCheck.println({ "Outside.refcount(${nodeid}) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x64" })
                    NodeManager.releaseNode(nodeid)
                    nodeid = i
                    node = n
                    currentLayer.add(i)
                }
                NodeLeaf.initializeWith(node, iterator)
            }
            SanityCheck.check { currentLayer.size > 0 }
            while (currentLayer.size > 1) {
                var tmp = mutableListOf<Int>()
                var prev2: ByteArray? = null
                SanityCheck.println({ "Outside.refcount(??) - x54" })
                NodeManager.allocateNodeInner { n, i ->
                    SanityCheck.println({ "Outside.refcount(${nodeid}) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x65" })
                    NodeManager.releaseNode(nodeid)
                    nodeid = i
                    tmp.add(i)
                    NodeInner.initializeWith(n, currentLayer)
                    prev2 = n
                }
                var prev = prev2!!
                while (currentLayer.size > 0) {
                    SanityCheck.println({ "Outside.refcount(??) - x55" })
                    NodeManager.allocateNodeInner { n, i ->
                        SanityCheck.println({ "Outside.refcount(${nodeid}) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x66" })
                        NodeManager.releaseNode(nodeid)
                        nodeid = i
                        tmp.add(i)
                        NodeInner.initializeWith(n, currentLayer)
                        NodeShared.setNextNode(prev, i)
                        prev = n
                    }
                }
                currentLayer = tmp
            }
            SanityCheck.println({ "Outside.refcount(${nodeid}) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x71" })
            NodeManager.releaseNode(nodeid)
            var rootNodeIsLeaf = false
            SanityCheck.check { rootNode == null }
            SanityCheck.println({ "Outside.refcount(${currentLayer[0]}) ${NodeManager.bufferManager.allPagesRefcounters[currentLayer[0]]} x27" })
            NodeManager.getNodeAny(currentLayer[0], {
                rootNodeIsLeaf = true
            }, {
                rootNode = it
                root = currentLayer[0]
            })
            if (rootNodeIsLeaf) {
                SanityCheck.println({ "Outside.refcount(${nodeid}) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x67" })
                NodeManager.releaseNode(nodeid)
                SanityCheck.println({ "Outside.refcount(??) - x56" })
                NodeManager.allocateNodeInner { n, i ->
                    NodeInner.initializeWith(n, mutableListOf(currentLayer[0]))
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
        countPrimary = iterator.count
        distinctPrimary = iterator.distinct
        clearCachedHistogram()
    }

    suspend override fun insertAsBulk(data: IntArray, order: IntArray) {
        flushContinueWithWriteLock()
        var d = arrayOf(data, IntArray(data.size))
        TripleStoreBulkImport.sortUsingBuffers(0, 0, 1, d, data.size / 3, order)
        val iteratorImport = BulkImportIterator(d[0], data.size, order)
        var iteratorStore2: TripleIterator? = null
        if (firstLeaf == NodeManager.nodeNullPointer) {
            iteratorStore2 = EmptyIterator()
        } else {
            SanityCheck.println({ "Outside.refcount($firstLeaf) ${NodeManager.bufferManager.allPagesRefcounters[firstLeaf]} x12" })
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
            SanityCheck.println({ "Outside.refcount($oldroot) ${NodeManager.bufferManager.allPagesRefcounters[oldroot]} x43" })
            NodeManager.freeNodeAndAllRelated(oldroot)
        }
        lock.writeUnlock()
    }

    suspend override fun removeAsBulk(data: IntArray, order: IntArray) {
        flushContinueWithWriteLock()
        var d = arrayOf(data, IntArray(data.size))
        TripleStoreBulkImport.sortUsingBuffers(0, 0, 1, d, data.size / 3, order)
        val iteratorImport = BulkImportIterator(d[0], data.size, order)
        var iteratorStore2: TripleIterator? = null
        if (firstLeaf == NodeManager.nodeNullPointer) {
            iteratorStore2 = EmptyIterator()
        } else {
            SanityCheck.println({ "Outside.refcount($firstLeaf) ${NodeManager.bufferManager.allPagesRefcounters[firstLeaf]} x13" })
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
            SanityCheck.println({ "Outside.refcount($oldroot) ${NodeManager.bufferManager.allPagesRefcounters[oldroot]} x44" })
            NodeManager.freeNodeAndAllRelated(oldroot)
        }
        lock.writeUnlock()
    }

    override fun insert(a: Value, b: Value, c: Value) {
        SanityCheck.checkUnreachable()
    }

    override fun remove(a: Value, b: Value, c: Value) {
        SanityCheck.checkUnreachable()
    }

    suspend override fun clear() {
        flushContinueWithWriteLock()
        if (root != NodeManager.nodeNullPointer) {
            SanityCheck.println({ "Outside.refcount($root) ${NodeManager.bufferManager.allPagesRefcounters[root]} x45" })
            NodeManager.freeNodeAndAllRelated(root)
            root = NodeManager.nodeNullPointer
        }
        firstLeaf = NodeManager.nodeNullPointer
        rootNode = null
        clearCachedHistogram()
        lock.writeUnlock()
    }

    suspend override fun printContents() {
        lock.readLock()
        if (firstLeaf != NodeManager.nodeNullPointer) {
            SanityCheck.println({ "Outside.refcount($firstLeaf) ${NodeManager.bufferManager.allPagesRefcounters[firstLeaf]} x14" })
            NodeManager.getNodeLeaf(firstLeaf, { node ->
                var it = NodeLeaf.iterator(node, firstLeaf)
                while (it.hasNext()) {
                    var d = it.next()
                    SanityCheck.println({ "debug ${d.map { it }}" })
                }
            })
        }
        lock.readUnlock()
    }
}
