package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeMutableList
import lupos.s00misc.ThreadSafeMutableSet
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.index_IDTriple.*

class TripleStoreIndex_IDTriple : TripleStoreIndex {
    var firstLeaf = NodeManager.NodeNullPointer
    var root = NodeManager.NodeNullPointer
    var rootNode: Node? = null

    companion object {
        var storeIteratorCounter = 0L
    }

    override fun safeToFolder(filename: String) {
        throw Exception("not implemented")
    }

    override fun loadFromFolder(filename: String) {
        throw Exception("not implemented")
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

    override fun getIterator(query: Query, filter: IntArray, projection: List<String>): ColumnIteratorRow {
        SanityCheck.check { filter.size >= 0 && filter.size <= 3 }
        SanityCheck.check { projection.size + filter.size == 3 }
        val columns = mutableMapOf<String, ColumnIterator>()
        for (s in projection) {
            if (s != "_") {
                columns[s] = ColumnIterator()
            }
        }
        var res = ColumnIteratorRow(columns)
        if (firstLeaf != NodeManager.NodeNullPointer) {
            val node = NodeManager.getNode(firstLeaf) as NodeLeaf
            if (filter.size == 3) {
                if (node.iterator3(filter).hasNext()) {
                    res.count = 1
                }
            } else if (filter.size == 2) {
                if (projection[0] == "_") {
                    var count = 0
                    var it = node.iterator2(filter)
                    while (it.hasNext()) {
                        it.next()
                        count++
                    }
                    res.count = count
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
                    res.count = count
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
                    res.count = count
                }
            }
        }
        return res
    }

    override fun import(dataImport: IntArray, count: Int, order: IntArray) {
        val iteratorImport = BulkImportIterator(dataImport, count, order)
        var iteratorStore: TripleIterator
        if (firstLeaf == NodeManager.NodeNullPointer) {
            iteratorStore = EmptyIterator()
        } else {
            iteratorStore = (NodeManager.getNode(firstLeaf) as NodeLeaf).iterator()
        }
        val iterator = MergeIterator(iteratorImport, iteratorStore)
        if (iterator.hasNext()) {
            var currentLayer = mutableListOf<Pair<Int, Node>>()
            var newFirstLeaf = NodeManager.NodeNullPointer
            var node2: NodeLeaf? = null
            NodeManager.allocateNodeLeaf { n, i ->
                newFirstLeaf = i
                node2 = n
                currentLayer.add(Pair(i, n))
            }
            var node = node2!!
            node.initializeWith(iterator)
            while (iterator.hasNext()) {
                NodeManager.allocateNodeLeaf { n, i ->
                    node.setNextNode(i)
                    node = n
                    currentLayer.add(Pair(i, n))
                }
                node.initializeWith(iterator)
            }
//            NodeManager.freeNodeAndAllRelated(root)
            NodeManager.freeNodeAndAllRelated(firstLeaf)
            firstLeaf = newFirstLeaf
require(currentLayer.size>0)
            while (currentLayer.size > 1) {
                var tmp = mutableListOf<Pair<Int, Node>>()
                var prev2: Node? = null
                NodeManager.allocateNodeInner { n, i ->
                    tmp.add(Pair(i, n))
                    n.initializeWith(currentLayer)
                    prev2 = n
                }
                var prev = prev2!!
                while (currentLayer.size > 0) {
                    NodeManager.allocateNodeInner { n, i ->
                        tmp.add(Pair(i, n))
                        n.initializeWith(currentLayer)
                        prev.setNextNode(i)
                        prev = n
                    }
                }
                currentLayer = tmp
            }
            root = currentLayer[0].first
            rootNode = currentLayer[0].second
        }
    }

    override fun insert(a: Value, b: Value, c: Value) {
        throw Exception("not implemented")
    }

    override fun remove(a: Value, b: Value, c: Value) {
        throw Exception("not implemented")
    }

    override fun clear() {
//        NodeManager.freeNodeAndAllRelated(root)
        NodeManager.freeNodeAndAllRelated(firstLeaf)
        firstLeaf = NodeManager.NodeNullPointer
        root = NodeManager.NodeNullPointer
        rootNode = null
    }

    override fun printContents() {
        if (firstLeaf != NodeManager.NodeNullPointer) {
            val node = NodeManager.getNode(firstLeaf) as NodeLeaf
            var it = node.iterator()
            while (it.hasNext()) {
                var d = it.next()
                println(d.map { it })
            }
        }
    }
}
