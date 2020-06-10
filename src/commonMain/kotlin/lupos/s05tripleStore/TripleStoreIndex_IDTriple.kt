package lupos.s05tripleStore

import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
import lupos.s00misc.File
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
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
import lupos.s05tripleStore.index_IDTriple.TripleIterator

class TripleStoreIndex_IDTriple : TripleStoreIndex() {
    var firstLeaf = NodeManager.nodeNullPointer
    var root = NodeManager.nodeNullPointer
    var rootNode: NodeInner? = null
    var pendingImport = mutableListOf<Int?>()
    var countPrimary = 0
    var distinctPrimary = 0

    companion object {
        var storeIteratorCounter = 0L
    }

    override fun safeToFile(filename: String) {
        flush()
        SanityCheck {
            if (root != NodeManager.nodeNullPointer) {
                var found = false
                NodeManager.getNode(root, {
                    println("root is inner node")
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
        SanityCheck {
            println(firstLeaf)
            println(root)
            println(countPrimary)
            println(distinctPrimary)
            if (rootNode != null) {
                val iterator = rootNode!!.iterator()
                while (iterator.hasNext()) {
                    println(iterator.next().map { it })
                }
            }
        }
    }

    override fun loadFromFile(filename: String) {
        clear()
        File(filename).dataInputStream { fis ->
            firstLeaf = fis.readInt()
            root = fis.readInt()
            countPrimary = fis.readInt()
            distinctPrimary = fis.readInt()
            if (root == NodeManager.nodeNullPointer) {
                rootNode = null
            } else {
                NodeManager.getNode(root, {
                    SanityCheck.checkUnreachable()
                }, {
                    rootNode = it
                })
                SanityCheck.check { rootNode != null }
            }
        }
        SanityCheck {
            println(firstLeaf)
            println(root)
            println(countPrimary)
            println(distinctPrimary)
            if (rootNode != null) {
                val iterator = rootNode!!.iterator()
                while (iterator.hasNext()) {
                    println(iterator.next().map { it })
                }
            }
        }
    }

    class IteratorS(it: TripleIterator) : ColumnIterator() {
        init {
            next = {
                var tmp: Value? = null
                if (it.hasNext()) {
                    tmp = it.nextS()
                }
                /*return*/tmp
            }
        }
    }

    class IteratorP(it: TripleIterator) : ColumnIterator() {
        init {
            next = {
                var tmp: Value? = null
                if (it.hasNext()) {
                    tmp = it.nextP()
                }
                /*return*/tmp
            }
        }
    }

    class IteratorO(it: TripleIterator) : ColumnIterator() {
        init {
            next = {
                var tmp: Value? = null
                if (it.hasNext()) {
                    tmp = it.nextO()
                }
                /*return*/tmp
            }
        }
    }

    override fun getHistogram(query: Query, filter: IntArray): Pair<Int, Int> {
        val node = rootNode
        if (node != null) {
            when (filter.size) {
                0 -> {
                    return Pair(countPrimary, distinctPrimary)
                }
                1 -> {
                    var iterator = node.iterator1(filter)
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
                    return Pair(count, distinct)
                }
                2 -> {
                    var iterator = node.iterator2(filter)
                    var count = 0
                    while (iterator.hasNext()) {
                        iterator.next()
                        count++
                    }
                    return Pair(count, count)
                }
                3 -> {
                    return Pair(1, 1)
                }
                else -> {
                    SanityCheck.checkUnreachable()
                }
            }
/*Coverage Unreachable*/
        } else {
            return Pair(0, 0)
        }
/*Coverage Unreachable*/
    }

    override fun getIterator(query: Query, filter: IntArray, projection: List<String>): IteratorBundle {
        flush()
        SanityCheck.check { filter.size >= 0 && filter.size <= 3 }
        SanityCheck.check { projection.size + filter.size == 3 }
        val columns = mutableMapOf<String, ColumnIterator>()
        for (s in projection) {
            if (s != "_") {
                columns[s] = ColumnIterator()
            }
        }
        var res: IteratorBundle
        if (columns.size > 0) {
            res = IteratorBundle(columns)
        } else {
            res = IteratorBundle(0)
        }
        val node = rootNode
        if (node != null) {
            if (filter.size == 3) {
                if (node.iterator3(filter).hasNext()) {
                    res = IteratorBundle(1)
                }
            } else if (filter.size == 2) {
                if (projection[0] == "_") {
                    var count = 0
                    var it = node.iterator2(filter)
                    while (it.hasNext()) {
                        it.next()
                        count++
                    }
                    res = IteratorBundle(count)
                } else {
                    columns[projection[0]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[0], IteratorO(node.iterator2(filter)))
                }
            } else if (filter.size == 1) {
                if (projection[0] != "_") {
                    columns[projection[0]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[0], IteratorP(node.iterator1(filter)))
                    if (projection[1] != "_") {
                        columns[projection[1]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[1], IteratorO(node.iterator1(filter)))
                    }
                } else {
                    SanityCheck.check { projection[1] == "_" }
                    var count = 0
                    var it = node.iterator1(filter)
                    while (it.hasNext()) {
                        it.next()
                        count++
                    }
                    res = IteratorBundle(count)
                }
            } else {
                SanityCheck.check { filter.size == 0 }
                if (projection[0] != "_") {
                    columns[projection[0]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[0], IteratorS(node.iterator()))
                    if (projection[1] != "_") {
                        columns[projection[1]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[1], IteratorP(node.iterator()))
                        if (projection[2] != "_") {
                            columns[projection[2]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[2], IteratorO(node.iterator()))
                        }
                    } else {
                        SanityCheck.check { projection[2] == "_" }
                    }
                } else {
                    SanityCheck.check { projection[1] == "_" }
                    SanityCheck.check { projection[2] == "_" }
                    var count = 0
                    var it = node.iterator()
                    while (it.hasNext()) {
                        it.next()
                        count++
                    }
                    res = IteratorBundle(count)
                }
            }
        }
        return res
    }

    fun importHelper(a: TripleIterator, b: TripleIterator): Int {
        return importHelper(MergeIterator(a, b))
    }

    fun importHelper(a: Int, b: Int): Int {
        var nodeA: NodeLeaf? = null
        var nodeB: NodeLeaf? = null
        NodeManager.getNode(a, {
            nodeA = it
        }, {
            SanityCheck.checkUnreachable()
        })
        NodeManager.getNode(b, {
            nodeB = it
        }, {
            SanityCheck.checkUnreachable()
        })
        val res = importHelper(MergeIterator(nodeA!!.iterator(), nodeB!!.iterator()))
        NodeManager.freeAllLeaves(a)
        NodeManager.freeAllLeaves(b)
        return res
    }

    fun importHelper(iterator: TripleIterator): Int {
        var res = NodeManager.nodeNullPointer
        var node2: NodeLeaf? = null
        NodeManager.allocateNodeLeaf { n, i ->
            res = i
            node2 = n
        }
        var node = node2!!
        node.initializeWith(iterator)
        while (iterator.hasNext()) {
            NodeManager.allocateNodeLeaf { n, i ->
                node.setNextNode(i)
                node = n
            }
            node.initializeWith(iterator)
        }
        return res
    }

    override fun flush() {
        BenchmarkUtils.start(EBenchmark.IMPORT_REBUILD_MAP)
        if (pendingImport.size > 0) {
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
            NodeManager.getNode(newFirstLeaf, {
                rebuildData(DistinctIterator(it.iterator()))
            }, {
                rebuildData(DistinctIterator(it.iterator()))
            })
            NodeManager.freeAllLeaves(newFirstLeaf)
            pendingImport.clear()
        }
        BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_REBUILD_MAP)
    }

    override fun import(dataImport: IntArray, count: Int, order: IntArray) {
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

    fun rebuildData(_iterator: TripleIterator) {
        val iterator = Count1PassThroughIterator(_iterator)
        if (iterator.hasNext()) {
            var currentLayer = mutableListOf<Int>()
            var newFirstLeaf = NodeManager.nodeNullPointer
            var node2: NodeLeaf? = null
            NodeManager.allocateNodeLeaf { n, i ->
                newFirstLeaf = i
                node2 = n
                currentLayer.add(i)
            }
            var node = node2!!
            node.initializeWith(iterator)
            while (iterator.hasNext()) {
                NodeManager.allocateNodeLeaf { n, i ->
                    node.setNextNode(i)
                    node = n
                    currentLayer.add(i)
                }
                node.initializeWith(iterator)
            }
            firstLeaf = newFirstLeaf
            SanityCheck.check { currentLayer.size > 0 }
            while (currentLayer.size > 1) {
                var tmp = mutableListOf<Int>()
                var prev2: NodeInner? = null
                NodeManager.allocateNodeInner { n, i ->
                    tmp.add(i)
                    n.initializeWith(currentLayer)
                    prev2 = n
                }
                var prev = prev2!!
                while (currentLayer.size > 0) {
                    NodeManager.allocateNodeInner { n, i ->
                        tmp.add(i)
                        n.initializeWith(currentLayer)
                        prev.setNextNode(i)
                        prev = n
                    }
                }
                currentLayer = tmp
            }
            NodeManager.getNode(currentLayer[0], {
                NodeManager.allocateNodeInner { n, i ->
                    n.initializeWith(mutableListOf(currentLayer[0]))
                    rootNode = n
                    root = i
                }
            }, {
                rootNode = it
                root = currentLayer[0]
            })
        }
        countPrimary = iterator.count
        distinctPrimary = iterator.distinct
    }

    override fun insertAsBulk(data: IntArray, order: IntArray) {
        flush()
        var d = arrayOf(data, IntArray(data.size))
        TripleStoreBulkImport.sortUsingBuffers(0, 0, 1, d, data.size / 3, order)
        val iteratorImport = BulkImportIterator(d[0], data.size, order)
        var iteratorStore2: TripleIterator? = null
        if (firstLeaf == NodeManager.nodeNullPointer) {
            iteratorStore2 = EmptyIterator()
        } else {
            NodeManager.getNode(firstLeaf, {
                iteratorStore2 = it.iterator()
            }, {
                SanityCheck.checkUnreachable()
            })
        }
        val iteratorStore = iteratorStore2!!
        val iterator = MergeIterator(iteratorStore, DistinctIterator(iteratorImport))
        var oldroot = root
        rebuildData(iterator)
        NodeManager.freeNodeAndAllRelated(oldroot)
    }

    override fun removeAsBulk(data: IntArray, order: IntArray) {
        flush()
        var d = arrayOf(data, IntArray(data.size))
        TripleStoreBulkImport.sortUsingBuffers(0, 0, 1, d, data.size / 3, order)
        val iteratorImport = BulkImportIterator(d[0], data.size, order)
        var iteratorStore2: TripleIterator? = null
        if (firstLeaf == NodeManager.nodeNullPointer) {
            iteratorStore2 = EmptyIterator()
        } else {
            NodeManager.getNode(firstLeaf, {
                iteratorStore2 = it.iterator()
            }, {
                SanityCheck.checkUnreachable()
            })
        }
        val iteratorStore = iteratorStore2!!
        val iterator = MinusIterator(iteratorStore, DistinctIterator(iteratorImport))
        var oldroot = root
        rebuildData(iterator)
        NodeManager.freeNodeAndAllRelated(oldroot)
    }

    override fun insert(a: Value, b: Value, c: Value) {
        SanityCheck.checkUnreachable()
    }

    override fun remove(a: Value, b: Value, c: Value) {
        SanityCheck.checkUnreachable()
    }

    override fun clear() {
        flush()
        NodeManager.freeNodeAndAllRelated(root)
        firstLeaf = NodeManager.nodeNullPointer
        root = NodeManager.nodeNullPointer
        rootNode = null
    }

    override fun printContents() {
        if (firstLeaf != NodeManager.nodeNullPointer) {
            NodeManager.getNode(firstLeaf, { node ->
                var it = node.iterator()
                while (it.hasNext()) {
                    var d = it.next()
                    println("debug ${d.map { it }}")
                }
            }, {
                SanityCheck.checkUnreachable()
            })
        }
    }
}
