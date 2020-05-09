package lupos.s05tripleStore
import kotlin.jvm.JvmField
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EBenchmark
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.File
import lupos.s00misc.GlobalLogger
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MemoryStatistics
import lupos.s00misc.MyListDouble
import lupos.s00misc.MyListGeneric
import lupos.s00misc.MyListInt
import lupos.s00misc.MyMapDoubleInt
import lupos.s00misc.MyMapIntGeneric
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.MyMapLongGeneric
import lupos.s00misc.MyMapLongInt
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
import lupos.s00misc.MySetInt
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromXml
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeMutableList
import lupos.s00misc.ThreadSafeMutableSet
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s05tripleStore.index_IDTriple.EmptyIterator
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorDistinct
import lupos.s04logicalOperators.iterator.ColumnIteratorMergeSort
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.iterator.ColumnIteratorStore1
import lupos.s04logicalOperators.iterator.ColumnIteratorStore2a
import lupos.s04logicalOperators.iterator.ColumnIteratorStore2b
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3a
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3b
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3c
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.index_IDTriple.BulkImportIterator
import lupos.s05tripleStore.index_IDTriple.MergeIterator
import lupos.s05tripleStore.index_IDTriple.Node
import lupos.s05tripleStore.index_IDTriple.NodeLeaf
import lupos.s05tripleStore.index_IDTriple.NodeManager
import lupos.s05tripleStore.index_IDTriple.TripleIterator




class TripleStoreIndex_IDTriple : TripleStoreIndex {
    var firstLeaf = NodeManager.nodeNullPointer
    var root = NodeManager.nodeNullPointer
    var rootNode: Node? = null

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
        val node = rootNode
        if (node != null) {
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
        if (firstLeaf == NodeManager.nodeNullPointer) {
            iteratorStore = EmptyIterator()
        } else {
            iteratorStore = (NodeManager.getNode(firstLeaf) as NodeLeaf).iterator()
        }
        val iterator = MergeIterator(iteratorImport, iteratorStore)
        if (iterator.hasNext()) {
            var currentLayer = mutableListOf<Pair<Int, Node>>()
            var newFirstLeaf = NodeManager.nodeNullPointer
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
            NodeManager.freeNodeAndAllRelated(root)
            firstLeaf = newFirstLeaf
            require(currentLayer.size > 0)
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
        NodeManager.freeNodeAndAllRelated(root)
        firstLeaf = NodeManager.nodeNullPointer
        root = NodeManager.nodeNullPointer
        rootNode = null
    }

    override fun printContents() {
        if (firstLeaf != NodeManager.nodeNullPointer) {
            val node = NodeManager.getNode(firstLeaf) as NodeLeaf
            var it = node.iterator()
            while (it.hasNext()) {
                var d = it.next()
                println(d.map { it })
            }
        }
    }
}
