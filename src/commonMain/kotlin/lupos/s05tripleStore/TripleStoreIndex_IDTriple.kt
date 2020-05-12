package lupos.s05tripleStore

import lupos.s00misc.Coverage
import lupos.s00misc.File
import lupos.s00misc.SanityCheck
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.EBenchmark
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.index_IDTriple.BulkImportIterator
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

    companion object {
        var storeIteratorCounter = 0L
    }

    override fun safeToFile(filename: String) {
        flush()
        File(filename).dataOutputStream { out ->
            out.writeInt(firstLeaf)
            out.writeInt(root)
        }
    }

    override fun loadFromFile(filename: String) {
        File(filename).dataInputStream { fis ->
            firstLeaf = fis.readInt()
            root = fis.readInt()
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

    var pendingImport = mutableListOf<Int?>()
    fun importHelper(a: TripleIterator, b: TripleIterator): Int {
        return importHelper(MergeIterator(a, b))
    }

    fun importHelper(a: Int, b: Int): Int {
        var nodeA: NodeLeaf? = null
        var nodeB: NodeLeaf? = null
        NodeManager.getNode(a, {
            nodeA = it
        }, {
            throw Exception("unreachable")
        })
        NodeManager.getNode(b, {
            nodeB = it
        }, {
            throw Exception("unreachable")
        })
        return importHelper(MergeIterator(nodeA!!.iterator(), nodeB!!.iterator()))
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

    fun flush() {
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
                    NodeManager.freeAllLeaves(a)
                    NodeManager.freeAllLeaves(b)
                }
                j++
            }
            require(pendingImport.size > 0)
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
                    NodeManager.freeAllLeaves(a)
                    NodeManager.freeAllLeaves(b)
                    pendingImport[j - 1] = null
                }
                j++
            }
        }
BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_MERGE_DATA)
    }

    fun rebuildData(iterator: TripleIterator) {
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
            NodeManager.freeNodeAndAllRelated(root)
            firstLeaf = newFirstLeaf
            require(currentLayer.size > 0)
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
            root = currentLayer[0]
            NodeManager.getNode(root, {
                NodeManager.allocateNodeInner { n, i ->
                    n.initializeWith(mutableListOf(root))
                    rootNode = n
                }
            }, {
                rootNode = it
            })
        }
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
                throw Exception("unreachable")
            })
        }
        val iteratorStore = iteratorStore2!!
        val iterator = MergeIterator(iteratorStore, DistinctIterator(iteratorImport))
        rebuildData(iterator)
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
                throw Exception("unreachable")
            })
        }
        val iteratorStore = iteratorStore2!!
        val iterator = MinusIterator(iteratorStore, DistinctIterator(iteratorImport))
        rebuildData(iterator)
    }

    override fun insert(a: Value, b: Value, c: Value) {
        throw Exception("unreachable")
    }

    override fun remove(a: Value, b: Value, c: Value) {
        throw Exception("not implemented")
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
                throw Exception("unreachable")
            })
        }
    }
}
