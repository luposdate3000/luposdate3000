package lupos.s05tripleStore
import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.ETripleIndexType
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.Parallel
import lupos.s00misc.SanityCheck
import lupos.s01io.BufferManager
import lupos.s01io.BufferManagerExt
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorEmpty
import lupos.s04logicalOperators.iterator.IteratorBundle
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
import lupos.s05tripleStore.index_IDTriple.NodeLeafColumnIteratorPrefix11
import lupos.s05tripleStore.index_IDTriple.NodeLeafColumnIteratorPrefix12
import lupos.s05tripleStore.index_IDTriple.NodeLeafColumnIteratorPrefix22
import lupos.s05tripleStore.index_IDTriple.NodeManager
import lupos.s05tripleStore.index_IDTriple.NodeShared
import lupos.s05tripleStore.index_IDTriple.TripleIterator
import kotlin.jvm.JvmField
public class TripleStoreIndexIDTriple(store_root_page_id_: Int, store_root_page_init: Boolean) : TripleStoreIndex(store_root_page_id_) {
    @JvmField public val bufferManager: BufferManager = BufferManagerExt.getBuffermanager("stores")
    @JvmField public var firstLeaf_: Int = NodeManager.nodeNullPointer
    @JvmField public var firstLeaf: Int
        set(value) {
            val rootPage = bufferManager.getPage(store_root_page_id)
            ByteArrayHelper.writeInt4(rootPage, 16, value)
            firstLeaf_ = value
            bufferManager.flushPage(store_root_page_id)
            bufferManager.releasePage(store_root_page_id)
        }
        get() = firstLeaf_
    @JvmField public var root_: Int = NodeManager.nodeNullPointer
    @JvmField public var root: Int
        set(value) {
            val rootPage = bufferManager.getPage(store_root_page_id)
            ByteArrayHelper.writeInt4(rootPage, 4, value)
            root_ = value
            bufferManager.flushPage(store_root_page_id)
            bufferManager.releasePage(store_root_page_id)
        }
        get() = root_
    @JvmField public var countPrimary_: Int = 0
    @JvmField public var countPrimary: Int
        set(value) {
            val rootPage = bufferManager.getPage(store_root_page_id)
            ByteArrayHelper.writeInt4(rootPage, 8, value)
            countPrimary_ = value
            bufferManager.flushPage(store_root_page_id)
            bufferManager.releasePage(store_root_page_id)
        }
        get() = countPrimary_
    @JvmField public var distinctPrimary_: Int = 0
    @JvmField public var distinctPrimary: Int
        set(value) {
            val rootPage = bufferManager.getPage(store_root_page_id)
            ByteArrayHelper.writeInt4(rootPage, 12, value)
            distinctPrimary_ = value
            bufferManager.flushPage(store_root_page_id)
            bufferManager.releasePage(store_root_page_id)
        }
        get() = distinctPrimary_
    @JvmField
    public var rootNode: ByteArray? = null
    @JvmField
    public var pendingImport: MutableList<Int?> = mutableListOf()
    @JvmField
    internal var lock = MyReadWriteLock()
    @JvmField
    public var cachedHistograms1Size: Int = 0
    @JvmField
    public var cachedHistograms1Cursor: Int = 0
    @JvmField
    public val cachedHistograms1: IntArray = IntArray(300)
    @JvmField
    public var cachedHistograms2Size: Int = 0
    @JvmField
    public var cachedHistograms2Cursor: Int = 0
    @JvmField
    public val cachedHistograms2: IntArray = IntArray(400)
    init {
        val rootPage = bufferManager.getPage(store_root_page_id)
        if (store_root_page_init) {
            root = ByteArrayHelper.readInt4(rootPage, 4)
            countPrimary = ByteArrayHelper.readInt4(rootPage, 8)
            distinctPrimary = ByteArrayHelper.readInt4(rootPage, 12)
            firstLeaf = ByteArrayHelper.readInt4(rootPage, 16)
            if (root != NodeManager.nodeNullPointer) {
                NodeManager.getNodeAny(
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
            ByteArrayHelper.writeInt4(rootPage, 0, ETripleIndexType.ID_TRIPLE.ordinal)
            ByteArrayHelper.writeInt4(rootPage, 4, root)
            ByteArrayHelper.writeInt4(rootPage, 8, countPrimary)
            ByteArrayHelper.writeInt4(rootPage, 12, distinctPrimary)
            ByteArrayHelper.writeInt4(rootPage, 16, firstLeaf)
            bufferManager.flushPage(store_root_page_id)
        }
        bufferManager.releasePage(store_root_page_id)
    }
    internal companion object {
        @JvmField
        public var debugLock = MyReadWriteLock()
    }
    override fun dropIndex() {
        clear()
        bufferManager.getPage(store_root_page_id)
        bufferManager.deletePage(store_root_page_id)
    }
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
    override /*suspend*/ fun getHistogram(query: IQuery, params: TripleStoreFeatureParams): Pair<Int, Int> {
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
                        val iterator = NodeInner.iterator1(node, filter, lock, 1)
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
                        val iterator = NodeInner.iterator2(node, filter, lock)
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
    override /*suspend*/ fun getIterator(query: IQuery, params: TripleStoreFeatureParams): IteratorBundle {
        val fp = (params as TripleStoreFeatureParamsDefault).getFilterAndProjection(query)
        val filter = fp.first
        val projection = fp.second
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
                val it = NodeInner.iterator3(node, filter, lock)
                while (it.next() != ResultSetDictionaryExt.nullValue) {
                    count++
                }
                res = IteratorBundle(count)
            } else if (filter.size == 2) {
                if (projection[0] == "_") {
                    var count = 0
                    val it = NodeInner.iterator2(node, filter, lock)
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
                    val it = NodeInner.iterator1(node, filter, lock, 1)
                    while (it.next() != ResultSetDictionaryExt.nullValue) {
                        count++
                    }
                    res = IteratorBundle(count)
                }
            } else {
                SanityCheck.check { filter.isEmpty() }
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
    private /*suspend*/ fun importHelper(a: Int, b: Int): Int {
        var nodeA: ByteArray? = null
        var nodeB: ByteArray? = null
        SanityCheck.println { "Outside.refcount($a)  x132" }
        NodeManager.getNodeLeaf(a) {
            nodeA = it
        }
        SanityCheck.println { "Outside.refcount($b)  x125" }
        NodeManager.getNodeLeaf(b) {
            nodeB = it
        }
        val res = importHelper(MergeIterator(NodeLeaf.iterator(nodeA!!, a), NodeLeaf.iterator(nodeB!!, b)))
        SanityCheck.println { "Outside.refcount($a)  x133" }
        NodeManager.freeAllLeaves(a)
        SanityCheck.println { "Outside.refcount($b)  x134" }
        NodeManager.freeAllLeaves(b)
        return res
    }
    private /*suspend*/ fun importHelper(iterator: TripleIterator): Int {
        var res = NodeManager.nodeNullPointer
        var node2: ByteArray? = null
        SanityCheck.println { "Outside.refcount(??) - x135" }
        NodeManager.allocateNodeLeaf { n, i ->
            res = i
            node2 = n
        }
        var nodeid = res
        var node = node2!!
        NodeLeaf.initializeWith(node, nodeid, iterator)
        while (iterator.hasNext()) {
            SanityCheck.println { "Outside.refcount(??) - x51" }
            NodeManager.allocateNodeLeaf { n, i ->
                SanityCheck.println { "Outside.refcount($nodeid)  x136" }
                NodeShared.setNextNode(node, i)
                NodeManager.flushNode(nodeid)
                NodeManager.releaseNode(nodeid)
                nodeid = i
                node = n
            }
            NodeLeaf.initializeWith(node, nodeid, iterator)
        }
        SanityCheck.println { "Outside.refcount($nodeid)  x137" }
        NodeManager.flushNode(nodeid)
        NodeManager.releaseNode(nodeid)
        return res
    }
    override /*suspend*/ fun flush() {
        if (pendingImport.size > 0) {
            SanityCheck.println { "writeLock(${lock.getUUID()}) x138" }
            lock.writeLock()
            flushAssumeLocks()
            SanityCheck.println { "writeUnlock(${lock.getUUID()}) x139" }
            lock.writeUnlock()
        }
    }
    /*suspend*/ private inline fun flushContinueWithWriteLock() {
        SanityCheck.println { "writeLock(${lock.getUUID()}) x140" }
        lock.writeLock()
        flushAssumeLocks()
    }
    /*suspend*/ private inline fun flushContinueWithReadLock() {
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
    private /*suspend*/ fun flushAssumeLocks() {
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
            SanityCheck.println { "Outside.refcount($firstLeaf2)  x141" }
            NodeManager.getNodeAny(
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
                rebuildData(NodeLeaf.iterator(node!!, firstLeaf2))
            } else {
                rebuildData(NodeInner.iterator(node!!))
            }
            SanityCheck.println { "Outside.refcount($firstLeaf2)  x48" }
            NodeManager.freeAllLeaves(firstLeaf2)
            pendingImport.clear()
        }
    }
    override /*suspend*/ fun import(dataImport: IntArray, count: Int, order: IntArray) {
        SanityCheck.println { "writeLock(${lock.getUUID()}) x142" }
        lock.writeLock()
        if (count > 0) {
            val iteratorImport = BulkImportIterator(dataImport, count, order)
            val newFirstLeaf = importHelper(iteratorImport)
            if (firstLeaf != NodeManager.nodeNullPointer) {
                pendingImport.add(firstLeaf)
                SanityCheck.println { "Outside.refcount($root)  x49" }
                NodeManager.freeAllInnerNodes(root)
                firstLeaf = NodeManager.nodeNullPointer
                SanityCheck.check { root != NodeManager.nodeNullPointer }
                SanityCheck.println { "Outside.refcount($root)  x60" }
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
    private /*suspend*/ fun rebuildData(_iterator: TripleIterator) {
// assuming to have write-lock
        var iterator: TripleIterator = Count1PassThroughIterator(DistinctIterator(_iterator))
        SanityCheck {
            iterator = DebugPassThroughIterator(iterator)
        }
        if (iterator.hasNext()) {
            var currentLayer = mutableListOf<Int>()
            var node2: ByteArray? = null
            SanityCheck.println { "Outside.refcount(??) - x52" }
            NodeManager.allocateNodeLeaf { n, i ->
                firstLeaf = i
                node2 = n
                currentLayer.add(i)
            }
            var node = node2!!
            var nodeid = firstLeaf
            NodeLeaf.initializeWith(node, nodeid, iterator)
            while (iterator.hasNext()) {
                SanityCheck.println { "Outside.refcount(??) - x53" }
                NodeManager.allocateNodeLeaf { n, i ->
                    NodeShared.setNextNode(node, i)
                    SanityCheck.println { "Outside.refcount($nodeid)  x143" }
                    NodeManager.flushNode(nodeid)
                    NodeManager.releaseNode(nodeid)
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
                    var prev2: ByteArray? = null
                    SanityCheck.println { "Outside.refcount(??) - x54" }
                    NodeManager.allocateNodeInner { n, i ->
                        SanityCheck.println { "Outside.refcount($nodeid)  x144" }
                        NodeManager.flushNode(nodeid)
                        NodeManager.releaseNode(nodeid)
                        nodeid = i
                        tmp.add(i)
                        NodeInner.initializeWith(n, i, currentLayer)
                        prev2 = n
                    }
                    var prev = prev2!!
                    while (currentLayer.size > 0) {
                        SanityCheck.println { "Outside.refcount(??) - x55" }
                        NodeManager.allocateNodeInner { n, i ->
                            SanityCheck.println { "Outside.refcount($nodeid)  x145" }
                            NodeManager.flushNode(nodeid)
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
            }
            rebuildDataPart1()
            SanityCheck.println { "Outside.refcount($nodeid)  x146" }
            NodeManager.flushNode(nodeid)
            NodeManager.releaseNode(nodeid)
            var rootNodeIsLeaf = false
            SanityCheck.check { rootNode == null }
            SanityCheck.println { "Outside.refcount(${currentLayer[0]}) x10" }
            NodeManager.getNodeAny(
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
                SanityCheck.println { "Outside.refcount($nodeid)  x148" }
                NodeManager.flushNode(nodeid)
                NodeManager.releaseNode(nodeid)
                SanityCheck.println { "Outside.refcount(??) - x56" }
                NodeManager.allocateNodeInner { n, i ->
                    NodeInner.initializeWith(n, i, mutableListOf(currentLayer[0]))
                    rootNode = n
                    root = i
                    NodeManager.flushNode(root)
                }
            }
        } else {
// this index is cleared completely
            SanityCheck.check { rootNode == null }
            rootNode = null
            root = NodeManager.nodeNullPointer
            firstLeaf = NodeManager.nodeNullPointer
        }
        SanityCheck.suspended {
            rebuildDataSanity(iterator)
            iterator = (iterator as DebugPassThroughIterator).a
        }
        countPrimary = (iterator as Count1PassThroughIterator).count
        distinctPrimary = (iterator as Count1PassThroughIterator).distinct
        clearCachedHistogram()
    }
    private fun rebuildDataSanity(iterator: TripleIterator) {
// work around the crossinline here, because the method would be too large
        rebuildDataSanity2(iterator)
    }
    private fun rebuildDataSanity2(iterator: TripleIterator) {
        if (firstLeaf != NodeManager.nodeNullPointer) {
            debugLock.writeLock()
            debugLock.writeUnlock()
            val queueS = (iterator as DebugPassThroughIterator).queueS
            val queueP = iterator.queueP
            val queueO = iterator.queueO
            var myleaf = ByteArray(0)
//
            NodeManager.getNodeLeaf(firstLeaf) {
                myleaf = it
            }
            val iterator0 = NodeLeafColumnIterator0(myleaf, firstLeaf, debugLock)
            for (s in queueS) {
                val tmpa = iterator0.next()
                SanityCheck.check { tmpa == s }
            }
            val tmpa = iterator0.next()
            SanityCheck.check { tmpa == ResultSetDictionaryExt.nullValue }
            SanityCheck.check { iterator0.label == 0 }
//
            NodeManager.getNodeLeaf(firstLeaf) {
                myleaf = it
            }
            val iterator1 = NodeLeafColumnIterator1(myleaf, firstLeaf, debugLock)
            for (s in queueP) {
                val tmpb = iterator1.next()
                SanityCheck.check { tmpb == s }
            }
            val tmpb = iterator1.next()
            SanityCheck.check { tmpb == ResultSetDictionaryExt.nullValue }
            SanityCheck.check { iterator1.label == 0 }
//
            NodeManager.getNodeLeaf(firstLeaf) {
                myleaf = it
            }
            val iterator2 = NodeLeafColumnIterator2(myleaf, firstLeaf, debugLock)
            for (s in queueO) {
                val tmpc = iterator2.next()
                SanityCheck.check { tmpc == s }
            }
            val tmpc = iterator2.next()
            SanityCheck.check { tmpc == ResultSetDictionaryExt.nullValue }
            SanityCheck.check { iterator2.label == 0 }
//
            if (queueS.size > 0) {
                val iteratorS = queueS.iterator()
                val iteratorP = queueP.iterator()
                val iteratorO = queueO.iterator()
                NodeManager.getNodeLeaf(firstLeaf) {
                    myleaf = it
                }
                var iterator11 = NodeLeafColumnIteratorPrefix11(myleaf, firstLeaf, intArrayOf(queueS[0]), debugLock)
                NodeManager.getNodeLeaf(firstLeaf) {
                    myleaf = it
                }
                var iterator12 = NodeLeafColumnIteratorPrefix12(myleaf, firstLeaf, intArrayOf(queueS[0]), debugLock)
                var lastS = iteratorS.next()
                val tmpd = iterator11.next()
                SanityCheck.check { tmpd == iteratorP.next() }
                val tmpe = iterator12.next()
                SanityCheck.check({ tmpe == iteratorO.next() }, { "$queueS $queueP $queueO $tmpe" })
                var count = 1
                val counters = mutableListOf<Int>()
                while (iteratorS.hasNext()) {
                    val currentS = iteratorS.next()
                    val currentP = iteratorP.next()
                    val currentO = iteratorO.next()
                    val tmpf = iterator11.next()
                    val tmpg = iterator12.next()
                    count++
                    if (lastS == currentS) {
                        SanityCheck.check { tmpf == currentP }
                        SanityCheck.check { tmpg == currentO }
                    } else {
                        SanityCheck.check { tmpf == ResultSetDictionaryExt.nullValue }
                        SanityCheck.check { tmpg == ResultSetDictionaryExt.nullValue }
                        SanityCheck.check { iterator11.label == 0 }
                        SanityCheck.check { iterator12.label == 0 }
                        NodeManager.getNodeLeaf(firstLeaf) {
                            myleaf = it
                        }
                        iterator11 = NodeLeafColumnIteratorPrefix11(myleaf, firstLeaf, intArrayOf(currentS), debugLock)
                        NodeManager.getNodeLeaf(firstLeaf) {
                            myleaf = it
                        }
                        iterator12 = NodeLeafColumnIteratorPrefix12(myleaf, firstLeaf, intArrayOf(currentS), debugLock)
                        val tmph = iterator11.next()
                        val tmpi = iterator12.next()
                        SanityCheck.check { tmph == currentP }
                        SanityCheck.check { tmpi == currentO }
                        counters.add(count)
                        count = 1
                    }
                    lastS = currentS
                }
                counters.add(count)
                val tmpj = iterator11.next()
                val tmpk = iterator12.next()
                SanityCheck.check({ tmpj == ResultSetDictionaryExt.nullValue }, { "$queueS $queueP $queueO $tmpj $counters" })
                SanityCheck.check { tmpk == ResultSetDictionaryExt.nullValue }
                SanityCheck.check { iterator11.label == 0 }
                SanityCheck.check { iterator12.label == 0 }
            }
            if (queueS.size > 0) {
                val iteratorS = queueS.iterator()
                val iteratorP = queueP.iterator()
                val iteratorO = queueO.iterator()
                NodeManager.getNodeLeaf(firstLeaf) {
                    myleaf = it
                }
                var iterator11 = NodeLeafColumnIteratorPrefix11(myleaf, firstLeaf, intArrayOf(queueS[0]), debugLock)
                NodeManager.getNodeLeaf(firstLeaf) {
                    myleaf = it
                }
                var iterator12 = NodeLeafColumnIteratorPrefix12(myleaf, firstLeaf, intArrayOf(queueS[0]), debugLock)
                var lastS = iteratorS.next()
                val tmpd = iterator11.skipSIP(0)
                SanityCheck.check { tmpd == iteratorP.next() }
                val tmpe = iterator12.skipSIP(0)
                SanityCheck.check({ tmpe == iteratorO.next() }, { "$queueS $queueP $queueO $tmpe" })
                var count = 1
                val counters = mutableListOf<Int>()
                while (iteratorS.hasNext()) {
                    val currentS = iteratorS.next()
                    val currentP = iteratorP.next()
                    val currentO = iteratorO.next()
                    count++
                    if (lastS == currentS) {
                        val tmpf = iterator11.skipSIP(0)
                        val tmpg = iterator12.skipSIP(0)
                        SanityCheck.check { tmpf == currentP }
                        SanityCheck.check { tmpg == currentO }
                    } else {
                        iterator11.close()
                        NodeManager.getNodeLeaf(firstLeaf) {
                            myleaf = it
                        }
                        iterator11 = NodeLeafColumnIteratorPrefix11(myleaf, firstLeaf, intArrayOf(currentS), debugLock)
                        iterator12.close()
                        NodeManager.getNodeLeaf(firstLeaf) {
                            myleaf = it
                        }
                        iterator12 = NodeLeafColumnIteratorPrefix12(myleaf, firstLeaf, intArrayOf(currentS), debugLock)
                        val tmph = iterator11.skipSIP(0)
                        val tmpi = iterator12.skipSIP(0)
                        SanityCheck.check { tmph == currentP }
                        SanityCheck.check { tmpi == currentO }
                        counters.add(count)
                        count = 1
                    }
                    lastS = currentS
                }
                counters.add(count)
                iterator11.close()
                iterator12.close()
            }
            if (queueS.size > 0) {
                val iteratorS = queueS.iterator()
                val iteratorP = queueP.iterator()
                val iteratorO = queueO.iterator()
                NodeManager.getNodeLeaf(firstLeaf) {
                    myleaf = it
                }
                var iterator11 = NodeLeafColumnIteratorPrefix11(myleaf, firstLeaf, intArrayOf(queueS[0]), debugLock)
                NodeManager.getNodeLeaf(firstLeaf) {
                    myleaf = it
                }
                var iterator12 = NodeLeafColumnIteratorPrefix12(myleaf, firstLeaf, intArrayOf(queueS[0]), debugLock)
                var skipping = 1
                var lastS = iteratorS.next()
                iteratorP.next()
                iteratorO.next()
                var idx = 0
                var lastresetIdx = 0
                while (iteratorS.hasNext()) {
                    idx++
                    val currentS = iteratorS.next()
                    val currentP = iteratorP.next()
                    val currentO = iteratorO.next()
                    skipping = if (skipping == 0) {
                        1
                    } else {
                        if (lastS == currentS) {
                            val tmpf = iterator11.skipSIP(1)
                            val tmpg = iterator12.skipSIP(1)
                            SanityCheck.check({ tmpg == currentO }, { "1_2 $queueS $queueP $queueO $tmpg $idx $lastresetIdx $currentO" })
                            SanityCheck.check({ tmpf == currentP }, { "1_1 $queueS $queueP $queueO $tmpf $idx $lastresetIdx $currentP" }) // error is here xxxx
                        }
                        0
                    }
                    if (lastS != currentS) {
                        lastresetIdx = idx
                        iterator11.close()
                        NodeManager.getNodeLeaf(firstLeaf) {
                            myleaf = it
                        }
                        iterator11 = NodeLeafColumnIteratorPrefix11(myleaf, firstLeaf, intArrayOf(currentS), debugLock)
                        iterator12.close()
                        NodeManager.getNodeLeaf(firstLeaf) {
                            myleaf = it
                        }
                        iterator12 = NodeLeafColumnIteratorPrefix12(myleaf, firstLeaf, intArrayOf(currentS), debugLock)
                        skipping = 1
                    }
                    lastS = currentS
                }
                iterator11.close()
                iterator12.close()
            }
            if (queueS.size > 0) {
                val iteratorS = queueS.iterator()
                val iteratorP = queueP.iterator()
                val iteratorO = queueO.iterator()
                NodeManager.getNodeLeaf(firstLeaf) {
                    myleaf = it
                }
                var iterator22 = NodeLeafColumnIteratorPrefix22(myleaf, firstLeaf, intArrayOf(queueS[0], queueP[0]), debugLock)
                var lastS = iteratorS.next()
                var lastP = iteratorP.next()
                val tmpo = iterator22.next()
                SanityCheck.check({ tmpo == iteratorO.next() }, { "$queueS $queueP $queueO $tmpo" })
                while (iteratorS.hasNext()) {
                    val currentS = iteratorS.next()
                    val currentP = iteratorP.next()
                    val currentO = iteratorO.next()
                    val tmpl = iterator22.next()
                    if (lastS == currentS && lastP == currentP) {
                        SanityCheck.check { tmpl == currentO }
                    } else {
                        SanityCheck.check { tmpl == ResultSetDictionaryExt.nullValue }
                        SanityCheck.check { iterator22.label == 0 }
                        NodeManager.getNodeLeaf(firstLeaf) {
                            myleaf = it
                        }
                        iterator22 = NodeLeafColumnIteratorPrefix22(myleaf, firstLeaf, intArrayOf(currentS, currentP), debugLock)
                        val tmpm = iterator22.next()
                        SanityCheck.check { tmpm == currentO }
                    }
                    lastS = currentS
                    lastP = currentP
                }
                val tmpn = iterator22.next()
                SanityCheck.check { tmpn == ResultSetDictionaryExt.nullValue }
                SanityCheck.check { iterator22.label == 0 }
            }
            debugLock.writeLock()
            debugLock.writeUnlock()
        }
//
    }
    override /*suspend*/ fun insertAsBulk(data: IntArray, order: IntArray) {
        flushContinueWithWriteLock()
        val d = arrayOf(data, IntArray(data.size))
        TripleStoreBulkImportExt.sortUsingBuffers(0, 0, 1, d, data.size / 3, order)
        val iteratorImport = BulkImportIterator(d[0], data.size, order)
        var iteratorStore2: TripleIterator? = null
        if (firstLeaf == NodeManager.nodeNullPointer) {
            iteratorStore2 = EmptyIterator()
        } else {
            SanityCheck.println { "Outside.refcount($firstLeaf)  x12" }
            NodeManager.getNodeLeaf(firstLeaf) {
                iteratorStore2 = NodeLeaf.iterator(it, firstLeaf)
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
            SanityCheck.println { "Outside.refcount($oldroot)  x149" }
            NodeManager.freeNodeAndAllRelated(oldroot)
        }
        SanityCheck.println { "writeUnlock(${lock.getUUID()}) x62" }
        lock.writeUnlock()
    }
    override /*suspend*/ fun removeAsBulk(data: IntArray, order: IntArray) {
        flushContinueWithWriteLock()
        val d = arrayOf(data, IntArray(data.size))
        TripleStoreBulkImportExt.sortUsingBuffers(0, 0, 1, d, data.size / 3, order)
        val iteratorImport = BulkImportIterator(d[0], data.size, order)
        var iteratorStore2: TripleIterator? = null
        if (firstLeaf == NodeManager.nodeNullPointer) {
            iteratorStore2 = EmptyIterator()
        } else {
            SanityCheck.println { "Outside.refcount($firstLeaf)  x13" }
            NodeManager.getNodeLeaf(firstLeaf) {
                iteratorStore2 = NodeLeaf.iterator(it, firstLeaf)
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
            SanityCheck.println { "Outside.refcount($oldroot)  x150" }
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
    override /*suspend*/ fun clear() {
        flushContinueWithWriteLock()
        if (root != NodeManager.nodeNullPointer) {
            SanityCheck.println { "Outside.refcount($root)  x151" }
            NodeManager.freeNodeAndAllRelated(root)
            root = NodeManager.nodeNullPointer
        }
        firstLeaf = NodeManager.nodeNullPointer
        rootNode = null
        clearCachedHistogram()
        SanityCheck.println { "writeUnlock(${lock.getUUID()}) x64" }
        lock.writeUnlock()
    }
    override /*suspend*/ fun printContents() {
        SanityCheck.println { "readLock(${lock.getUUID()}) x65" }
        lock.readLock()
        if (firstLeaf != NodeManager.nodeNullPointer) {
            SanityCheck.println { "Outside.refcount($firstLeaf)  x122" }
            NodeManager.getNodeLeaf(firstLeaf) { node ->
                val it = NodeLeaf.iterator(node, firstLeaf)
                while (it.hasNext()) {
                    val d = it.next()
                    SanityCheck.println { "debug ${d.map { it }}" }
                }
            }
        }
        SanityCheck.println { "readUnlock(${lock.getUUID()}) x66" }
        lock.readUnlock()
    }
}
