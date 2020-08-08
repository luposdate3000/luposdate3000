package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.EBenchmark
import lupos.s00misc.File
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck
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
        flushContinueWithReadLock()
        SanityCheck.suspended {
            if (root != NodeManager.nodeNullPointer) {
                var found = false
                NodeManager.getNodeAny(root, {
                    SanityCheck.println { "root is inner node" }
                    SanityCheck.checkUnreachable()
                }, {
                    found = true
                    SanityCheck.check { rootNode == it }
                })
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
        SanityCheck.println { "readunlock 1" }
    }

    suspend override fun loadFromFile(filename: String) {
        SanityCheck.println({ "writelock 2" })
        lock.withWriteLock {
            pendingImport.clear()
            File(filename).dataInputStreamSuspended { fis ->
                firstLeaf = fis.readInt()
                root = fis.readInt()
                countPrimary = fis.readInt()
                distinctPrimary = fis.readInt()
                if (root == NodeManager.nodeNullPointer) {
                    rootNode = null
                } else {
                    NodeManager.getNodeInner(root, {
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
        }
        SanityCheck.println({ "writeunlock 2" })
    }

    var cachedHistograms1Size = 0
    var cachedHistograms1Cursor = 0
    val cachedHistograms1 = IntArray(300)
    var cachedHistograms2Size = 0
    var cachedHistograms2Cursor = 0
    val cachedHistograms2 = IntArray(400)
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
            SanityCheck.println({ "readlock 6" })
            lock.withReadLock {
                val node = rootNode
                if (node != null) {
                    when (filter.size) {
                        0 -> {
                            res = Pair(countPrimary, distinctPrimary)
                        }
                        1 -> {
                            var iterator = NodeInner.iterator1(node, filter)
                            var count = 0
                            var distinct = 0
                            if (iterator.hasNext()) {
                                var lastValue = iterator.next(1)
                                distinct++
                                count++
                                while (iterator.hasNext()) {
                                    var value = iterator.next(1)
                                    count++
                                    if (value != lastValue) {
                                        distinct++
                                    }
                                    lastValue = value
                                }
                            }
                            res = Pair(count, distinct)
                        }
                        2 -> {
                            var iterator = NodeInner.iterator2(node, filter)
                            var count = 0
                            while (iterator.hasNext()) {
                                iterator.next()
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
            }
            SanityCheck.println({ "readunlock 6" })
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
                if (NodeInner.iterator3(node, filter).hasNext()) {
                    res = IteratorBundle(1)
                }
            } else if (filter.size == 2) {
                if (projection[0] == "_") {
                    var count = 0
                    var it = NodeInner.iterator2(node, filter)
                    while (it.hasNext()) {
                        it.next()
                        count++
                    }
                    res = IteratorBundle(count)
                } else {
                    columns[projection[0]] = NodeInner.iterator2(node, filter, lock, 2)
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
                    var it = NodeInner.iterator1(node, filter)
                    while (it.hasNext()) {
                        it.next()
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
                    var count = 0
                    var it = NodeInner.iterator(node)
                    while (it.hasNext()) {
                        it.next()
                        count++
                    }
                    res = IteratorBundle(count)
                }
            }
        }
        lock.readUnlock()
        SanityCheck.println({ "readunlock 7" })
        return res
    }

    suspend fun importHelper(a: TripleIterator, b: TripleIterator): Int {
        return importHelper(MergeIterator(a, b))
    }

    suspend fun importHelper(a: Int, b: Int): Int {
        var nodeA: ByteArray? = null
        var nodeB: ByteArray? = null
        NodeManager.getNodeLeaf(a, {
            nodeA = it
        })
        NodeManager.getNodeLeaf(b, {
            nodeB = it
        })
        val res = importHelper(MergeIterator(NodeLeaf.iterator(nodeA!!), NodeLeaf.iterator(nodeB!!)))
        NodeManager.freeAllLeaves(a)
        NodeManager.freeAllLeaves(b)
        return res
    }

    suspend fun importHelper(iterator: TripleIterator): Int {
        var res = NodeManager.nodeNullPointer
        var node2: ByteArray? = null
        NodeManager.allocateNodeLeaf { n, i ->
            res = i
            node2 = n
        }
        var node = node2!!
        NodeLeaf.initializeWith(node, iterator)
        while (iterator.hasNext()) {
            NodeManager.allocateNodeLeaf { n, i ->
                NodeShared.setNextNode(node, i)
                node = n
            }
            NodeLeaf.initializeWith(node, iterator)
        }
        return res
    }

    suspend override fun flush() {
        if (pendingImport.size > 0) {
            SanityCheck.println({ "writelock 8" })
            lock.withWriteLock {
                flushAssumeLocks()
            }
            SanityCheck.println({ "writeunlock 8" })
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
            val newFirstLeaf = pendingImport[pendingImport.size - 1]!!
            var node: ByteArray? = null
            var flag = false
            NodeManager.getNodeAny(newFirstLeaf, {
                flag = true
                node = it
            }, {
                node = it
            })
            if (flag) {
                rebuildData(DistinctIterator(NodeLeaf.iterator(node!!)))
            } else {
                rebuildData(DistinctIterator(NodeInner.iterator(node!!)))
            }
            NodeManager.freeAllLeaves(newFirstLeaf)
            pendingImport.clear()
            BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_REBUILD_MAP)
        }
    }

    suspend override fun import(dataImport: IntArray, count: Int, order: IntArray) {
        SanityCheck.println({ "writelock 9" })
        lock.withWriteLock {
            BenchmarkUtils.start(EBenchmark.IMPORT_MERGE_DATA)
            if (count > 0) {
                val iteratorImport = BulkImportIterator(dataImport, count, order)
                val iterator = DistinctIterator(iteratorImport)
                var newFirstLeaf = importHelper(iterator)
                if (firstLeaf != NodeManager.nodeNullPointer) {
                    pendingImport.add(firstLeaf)
                    firstLeaf = NodeManager.nodeNullPointer
                    NodeManager.freeAllInnerNodes(root)
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
        }
        SanityCheck.println({ "writeunlock 9" })
    }

    suspend fun rebuildData(_iterator: TripleIterator) {
//assuming to have write-lock
        val iterator = Count1PassThroughIterator(_iterator)
        if (iterator.hasNext()) {
            var currentLayer = mutableListOf<Int>()
            var newFirstLeaf = NodeManager.nodeNullPointer
            var node2: ByteArray? = null
            NodeManager.allocateNodeLeaf { n, i ->
                newFirstLeaf = i
                node2 = n
                currentLayer.add(i)
            }
            var node = node2!!
            NodeLeaf.initializeWith(node, iterator)
            while (iterator.hasNext()) {
                NodeManager.allocateNodeLeaf { n, i ->
                    NodeShared.setNextNode(node, i)
                    node = n
                    currentLayer.add(i)
                }
                NodeLeaf.initializeWith(node, iterator)
            }
            firstLeaf = newFirstLeaf
            SanityCheck.check { currentLayer.size > 0 }
            while (currentLayer.size > 1) {
                var tmp = mutableListOf<Int>()
                var prev2: ByteArray? = null
                NodeManager.allocateNodeInner { n, i ->
                    tmp.add(i)
                    NodeInner.initializeWith(n, currentLayer)
                    prev2 = n
                }
                var prev = prev2!!
                while (currentLayer.size > 0) {
                    NodeManager.allocateNodeInner { n, i ->
                        tmp.add(i)
                        NodeInner.initializeWith(n, currentLayer)
                        NodeShared.setNextNode(prev, i)
                        prev = n
                    }
                }
                currentLayer = tmp
            }
            var rootNodeIsLeaf = false
            NodeManager.getNodeAny(currentLayer[0], {
                rootNodeIsLeaf = true
            }, {
                rootNode = it
                root = currentLayer[0]
            })
            if (rootNodeIsLeaf) {
                NodeManager.allocateNodeInner { n, i ->
                    NodeInner.initializeWith(n, mutableListOf(currentLayer[0]))
                    rootNode = n
                    root = i
                }
            }
        } else {
//this index is cleared completely
            rootNode = null
            root = NodeManager.nodeNullPointer
            firstLeaf = NodeManager.nodeNullPointer
        }
        countPrimary = iterator.count
        distinctPrimary = iterator.distinct
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
            NodeManager.getNodeLeaf(firstLeaf, {
                iteratorStore2 = NodeLeaf.iterator(it)
            })
        }
        val iteratorStore = iteratorStore2!!
        val iterator = MergeIterator(iteratorStore, DistinctIterator(iteratorImport))
        var oldroot = root
        rebuildData(iterator)
        NodeManager.freeNodeAndAllRelated(oldroot)
        lock.writeUnlock()
        SanityCheck.println({ "writeunlock 10" })
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
            NodeManager.getNodeLeaf(firstLeaf, {
                iteratorStore2 = NodeLeaf.iterator(it)
            })
        }
        val iteratorStore = iteratorStore2!!
        val iterator = MinusIterator(iteratorStore, DistinctIterator(iteratorImport))
        var oldroot = root
        rebuildData(iterator)
        NodeManager.freeNodeAndAllRelated(oldroot)
        lock.writeUnlock()
        SanityCheck.println({ "writeunlock 11" })
    }

    override fun insert(a: Value, b: Value, c: Value) {
        SanityCheck.checkUnreachable()
    }

    override fun remove(a: Value, b: Value, c: Value) {
        SanityCheck.checkUnreachable()
    }

    suspend override fun clear() {
        flushContinueWithWriteLock()
        NodeManager.freeNodeAndAllRelated(root)
        firstLeaf = NodeManager.nodeNullPointer
        root = NodeManager.nodeNullPointer
        rootNode = null
        lock.writeUnlock()
        SanityCheck.println({ "writeunlock 12" })
    }

    suspend override fun printContents() {
        SanityCheck.println({ "readlock 13" })
        lock.withReadLock {
            if (firstLeaf != NodeManager.nodeNullPointer) {
                NodeManager.getNodeLeaf(firstLeaf, { node ->
                    var it = NodeLeaf.iterator(node)
                    while (it.hasNext()) {
                        var d = it.next()
                        SanityCheck.println({ "debug ${d.map { it }}" })
                    }
                })
            }
        }
        SanityCheck.println({ "readunlock 13" })
    }
}
