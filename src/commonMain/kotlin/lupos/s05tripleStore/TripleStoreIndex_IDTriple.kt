package lupos.s05tripleStore

import lupos.s00misc.Coverage
import lupos.s00misc.File
import lupos.s00misc.SanityCheck
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

    override fun import(dataImport: IntArray, count: Int, order: IntArray) {
        val iteratorImport = BulkImportIterator(dataImport, count, order)
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
        val iterator = MergeIterator(DistinctIterator(iteratorImport), iteratorStore)
        rebuildData(iterator)
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
        var d = arrayOf(data, IntArray(data.size))
        TripleStoreBulkImport.sortUsingBuffers(0, 0, 1, d, data.size / 3, order)
        import(d[0], data.size, order)
    }

    override fun removeAsBulk(data: IntArray, order: IntArray) {
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
                    println(d.map { it })
                }
            }, {
                throw Exception("unreachable")
            })
        }
    }
}
