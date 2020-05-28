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
Coverage.funStart(6822)
        flush()
Coverage.statementStart(6823)
        File(filename).dataOutputStream { out ->
Coverage.statementStart(6824)
            out.writeInt(firstLeaf)
Coverage.statementStart(6825)
            out.writeInt(root)
Coverage.statementStart(6826)
            out.writeInt(countPrimary)
Coverage.statementStart(6827)
            out.writeInt(distinctPrimary)
Coverage.statementStart(6828)
        }
Coverage.statementStart(6829)
    }
    override fun loadFromFile(filename: String) {
Coverage.funStart(6830)
        pendingImport.clear()
Coverage.statementStart(6831)
        File(filename).dataInputStream { fis ->
Coverage.statementStart(6832)
            firstLeaf = fis.readInt()
Coverage.statementStart(6833)
            root = fis.readInt()
Coverage.statementStart(6834)
            countPrimary = fis.readInt()
Coverage.statementStart(6835)
            distinctPrimary = fis.readInt()
Coverage.statementStart(6836)
            NodeManager.getNode(root, {
Coverage.statementStart(6837)
                throw Exception("unreachable")
            }, {
Coverage.statementStart(6838)
                rootNode = it
Coverage.statementStart(6839)
            })
Coverage.statementStart(6840)
        }
Coverage.statementStart(6841)
    }
    class IteratorS(it: TripleIterator) : ColumnIterator() {
        init {
Coverage.funStart(6842)
            next = {
Coverage.statementStart(6843)
                var tmp: Value? = null
Coverage.statementStart(6844)
                if (it.hasNext()) {
Coverage.ifStart(6845)
                    tmp = it.nextS()
Coverage.statementStart(6846)
                }
Coverage.statementStart(6847)
                /*return*/tmp
            }
Coverage.statementStart(6848)
        }
    }
    class IteratorP(it: TripleIterator) : ColumnIterator() {
        init {
Coverage.funStart(6849)
            next = {
Coverage.statementStart(6850)
                var tmp: Value? = null
Coverage.statementStart(6851)
                if (it.hasNext()) {
Coverage.ifStart(6852)
                    tmp = it.nextP()
Coverage.statementStart(6853)
                }
Coverage.statementStart(6854)
                /*return*/tmp
            }
Coverage.statementStart(6855)
        }
    }
    class IteratorO(it: TripleIterator) : ColumnIterator() {
        init {
Coverage.funStart(6856)
            next = {
Coverage.statementStart(6857)
                var tmp: Value? = null
Coverage.statementStart(6858)
                if (it.hasNext()) {
Coverage.ifStart(6859)
                    tmp = it.nextO()
Coverage.statementStart(6860)
                }
Coverage.statementStart(6861)
                /*return*/tmp
            }
Coverage.statementStart(6862)
        }
    }
    override fun getHistogram(query: Query, filter: IntArray): Pair<Int, Int> {
Coverage.funStart(6863)
        val node = rootNode
Coverage.statementStart(6864)
        if (node != null) {
Coverage.ifStart(6865)
            when (filter.size) {
                0 -> {
Coverage.whenCaseStart(6867)
                    return Pair(countPrimary, distinctPrimary)
                }
                1 -> {
Coverage.whenCaseStart(6868)
                    var iterator = node.iterator1(filter)
Coverage.statementStart(6869)
                    var count = 0
Coverage.statementStart(6870)
                    var distinct = 0
Coverage.statementStart(6871)
                    if (iterator.hasNext()) {
Coverage.ifStart(6872)
                        var lastValue = iterator.next(1)
Coverage.statementStart(6873)
                        distinct++
Coverage.statementStart(6874)
                        count++
Coverage.statementStart(6875)
                        while (iterator.hasNext()) {
Coverage.whileLoopStart(6876)
                            var value = iterator.next(1)
Coverage.statementStart(6877)
                            count++
Coverage.statementStart(6878)
                            if (value != lastValue) {
Coverage.ifStart(6879)
                                distinct++
Coverage.statementStart(6880)
                            }
Coverage.statementStart(6881)
                            lastValue = value
Coverage.statementStart(6882)
                        }
Coverage.statementStart(6883)
                    }
Coverage.statementStart(6884)
                    return Pair(count, distinct)
                }
                2 -> {
Coverage.whenCaseStart(6885)
                    var iterator = node.iterator2(filter)
Coverage.statementStart(6886)
                    var count = 0
Coverage.statementStart(6887)
                    while (iterator.hasNext()) {
Coverage.whileLoopStart(6888)
                        iterator.next()
Coverage.statementStart(6889)
                        count++
Coverage.statementStart(6890)
                    }
Coverage.statementStart(6891)
                    return Pair(count, count)
                }
                3 -> {
Coverage.whenCaseStart(6892)
                    return Pair(1, 1)
                }
                else -> {
Coverage.whenCaseStart(6893)
                    throw Exception("unreachable")
                }
            }
Coverage.statementStart(6894)
        } else {
Coverage.ifStart(6895)
            return Pair(0, 0)
        }
Coverage.statementStart(6896)
    }
    override fun getIterator(query: Query, filter: IntArray, projection: List<String>): IteratorBundle {
Coverage.funStart(6897)
        flush()
Coverage.statementStart(6898)
        SanityCheck.check { filter.size >= 0 && filter.size <= 3 }
Coverage.statementStart(6899)
        SanityCheck.check { projection.size + filter.size == 3 }
Coverage.statementStart(6900)
        val columns = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(6901)
        for (s in projection) {
Coverage.forLoopStart(6902)
            if (s != "_") {
Coverage.ifStart(6903)
                columns[s] = ColumnIterator()
Coverage.statementStart(6904)
            }
Coverage.statementStart(6905)
        }
Coverage.statementStart(6906)
        var res: IteratorBundle
Coverage.statementStart(6907)
        if (columns.size > 0) {
Coverage.ifStart(6908)
            res = IteratorBundle(columns)
Coverage.statementStart(6909)
        } else {
Coverage.ifStart(6910)
            res = IteratorBundle(0)
Coverage.statementStart(6911)
        }
Coverage.statementStart(6912)
        val node = rootNode
Coverage.statementStart(6913)
        if (node != null) {
Coverage.ifStart(6914)
            if (filter.size == 3) {
Coverage.ifStart(6915)
                if (node.iterator3(filter).hasNext()) {
Coverage.ifStart(6916)
                    res = IteratorBundle(1)
Coverage.statementStart(6917)
                }
Coverage.statementStart(6918)
            } else if (filter.size == 2) {
Coverage.ifStart(6919)
                if (projection[0] == "_") {
Coverage.ifStart(6920)
                    var count = 0
Coverage.statementStart(6921)
                    var it = node.iterator2(filter)
Coverage.statementStart(6922)
                    while (it.hasNext()) {
Coverage.whileLoopStart(6923)
                        it.next()
Coverage.statementStart(6924)
                        count++
Coverage.statementStart(6925)
                    }
Coverage.statementStart(6926)
                    res = IteratorBundle(count)
Coverage.statementStart(6927)
                } else {
Coverage.ifStart(6928)
                    columns[projection[0]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[0], IteratorO(node.iterator2(filter)))
Coverage.statementStart(6929)
                }
Coverage.statementStart(6930)
            } else if (filter.size == 1) {
Coverage.ifStart(6931)
                if (projection[0] != "_") {
Coverage.ifStart(6932)
                    columns[projection[0]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[0], IteratorP(node.iterator1(filter)))
Coverage.statementStart(6933)
                    if (projection[1] != "_") {
Coverage.ifStart(6934)
                        columns[projection[1]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[1], IteratorO(node.iterator1(filter)))
Coverage.statementStart(6935)
                    }
Coverage.statementStart(6936)
                } else {
Coverage.ifStart(6937)
                    SanityCheck.check { projection[1] == "_" }
Coverage.statementStart(6938)
                    var count = 0
Coverage.statementStart(6939)
                    var it = node.iterator1(filter)
Coverage.statementStart(6940)
                    while (it.hasNext()) {
Coverage.whileLoopStart(6941)
                        it.next()
Coverage.statementStart(6942)
                        count++
Coverage.statementStart(6943)
                    }
Coverage.statementStart(6944)
                    res = IteratorBundle(count)
Coverage.statementStart(6945)
                }
Coverage.statementStart(6946)
            } else {
Coverage.ifStart(6947)
                SanityCheck.check { filter.size == 0 }
Coverage.statementStart(6948)
                if (projection[0] != "_") {
Coverage.ifStart(6949)
                    columns[projection[0]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[0], IteratorS(node.iterator()))
Coverage.statementStart(6950)
                    if (projection[1] != "_") {
Coverage.ifStart(6951)
                        columns[projection[1]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[1], IteratorP(node.iterator()))
Coverage.statementStart(6952)
                        if (projection[2] != "_") {
Coverage.ifStart(6953)
                            columns[projection[2]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[2], IteratorO(node.iterator()))
Coverage.statementStart(6954)
                        }
Coverage.statementStart(6955)
                    } else {
Coverage.ifStart(6956)
                        SanityCheck.check { projection[2] == "_" }
Coverage.statementStart(6957)
                    }
Coverage.statementStart(6958)
                } else {
Coverage.ifStart(6959)
                    SanityCheck.check { projection[1] == "_" }
Coverage.statementStart(6960)
                    SanityCheck.check { projection[2] == "_" }
Coverage.statementStart(6961)
                    var count = 0
Coverage.statementStart(6962)
                    var it = node.iterator()
Coverage.statementStart(6963)
                    while (it.hasNext()) {
Coverage.whileLoopStart(6964)
                        it.next()
Coverage.statementStart(6965)
                        count++
Coverage.statementStart(6966)
                    }
Coverage.statementStart(6967)
                    res = IteratorBundle(count)
Coverage.statementStart(6968)
                }
Coverage.statementStart(6969)
            }
Coverage.statementStart(6970)
        }
Coverage.statementStart(6971)
        return res
    }
    fun importHelper(a: TripleIterator, b: TripleIterator): Int {
Coverage.funStart(6972)
        return importHelper(MergeIterator(a, b))
    }
    fun importHelper(a: Int, b: Int): Int {
Coverage.funStart(6973)
        var nodeA: NodeLeaf? = null
Coverage.statementStart(6974)
        var nodeB: NodeLeaf? = null
Coverage.statementStart(6975)
        NodeManager.getNode(a, {
Coverage.statementStart(6976)
            nodeA = it
Coverage.statementStart(6977)
        }, {
Coverage.statementStart(6978)
            throw Exception("unreachable")
        })
Coverage.statementStart(6979)
        NodeManager.getNode(b, {
Coverage.statementStart(6980)
            nodeB = it
Coverage.statementStart(6981)
        }, {
Coverage.statementStart(6982)
            throw Exception("unreachable")
        })
Coverage.statementStart(6983)
        return importHelper(MergeIterator(nodeA!!.iterator(), nodeB!!.iterator()))
    }
    fun importHelper(iterator: TripleIterator): Int {
Coverage.funStart(6984)
        var res = NodeManager.nodeNullPointer
Coverage.statementStart(6985)
        var node2: NodeLeaf? = null
Coverage.statementStart(6986)
        NodeManager.allocateNodeLeaf { n, i ->
Coverage.statementStart(6987)
            res = i
Coverage.statementStart(6988)
            node2 = n
Coverage.statementStart(6989)
        }
Coverage.statementStart(6990)
        var node = node2!!
Coverage.statementStart(6991)
        node.initializeWith(iterator)
Coverage.statementStart(6992)
        while (iterator.hasNext()) {
Coverage.whileLoopStart(6993)
            NodeManager.allocateNodeLeaf { n, i ->
Coverage.statementStart(6994)
                node.setNextNode(i)
Coverage.statementStart(6995)
                node = n
Coverage.statementStart(6996)
            }
Coverage.statementStart(6997)
            node.initializeWith(iterator)
Coverage.statementStart(6998)
        }
Coverage.statementStart(6999)
        return res
    }
    fun flush() {
Coverage.funStart(7000)
        BenchmarkUtils.start(EBenchmark.IMPORT_REBUILD_MAP)
Coverage.statementStart(7001)
        if (pendingImport.size > 0) {
Coverage.ifStart(7002)
            var j = 1
Coverage.statementStart(7003)
            while (j < pendingImport.size) {
Coverage.whileLoopStart(7004)
                if (pendingImport[j] == null) {
Coverage.ifStart(7005)
                    pendingImport[j] = pendingImport[j - 1]
Coverage.statementStart(7006)
                } else if (pendingImport[j - 1] != null) {
Coverage.ifStart(7007)
                    val a = pendingImport[j]!!
Coverage.statementStart(7008)
                    val b = pendingImport[j - 1]!!
Coverage.statementStart(7009)
                    pendingImport[j] = importHelper(a, b)
Coverage.statementStart(7010)
                    NodeManager.freeAllLeaves(a)
Coverage.statementStart(7011)
                    NodeManager.freeAllLeaves(b)
Coverage.statementStart(7012)
                }
Coverage.statementStart(7013)
                j++
Coverage.statementStart(7014)
            }
Coverage.statementStart(7015)
            SanityCheck.check { pendingImport.size > 0 }
Coverage.statementStart(7016)
            val newFirstLeaf = pendingImport[pendingImport.size - 1]!!
Coverage.statementStart(7017)
            NodeManager.getNode(newFirstLeaf, {
Coverage.statementStart(7018)
                rebuildData(DistinctIterator(it.iterator()))
Coverage.statementStart(7019)
            }, {
Coverage.statementStart(7020)
                rebuildData(DistinctIterator(it.iterator()))
Coverage.statementStart(7021)
            })
Coverage.statementStart(7022)
            NodeManager.freeAllLeaves(newFirstLeaf)
Coverage.statementStart(7023)
            pendingImport.clear()
Coverage.statementStart(7024)
        }
Coverage.statementStart(7025)
        BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_REBUILD_MAP)
Coverage.statementStart(7026)
    }
    override fun import(dataImport: IntArray, count: Int, order: IntArray) {
Coverage.funStart(7027)
        BenchmarkUtils.start(EBenchmark.IMPORT_MERGE_DATA)
Coverage.statementStart(7028)
        if (count > 0) {
Coverage.ifStart(7029)
            val iteratorImport = BulkImportIterator(dataImport, count, order)
Coverage.statementStart(7030)
            val iterator = DistinctIterator(iteratorImport)
Coverage.statementStart(7031)
            var newFirstLeaf = importHelper(iterator)
Coverage.statementStart(7032)
            if (firstLeaf != NodeManager.nodeNullPointer) {
Coverage.ifStart(7033)
                pendingImport.add(firstLeaf)
Coverage.statementStart(7034)
                firstLeaf = NodeManager.nodeNullPointer
Coverage.statementStart(7035)
                NodeManager.freeAllInnerNodes(root)
Coverage.statementStart(7036)
                root = NodeManager.nodeNullPointer
Coverage.statementStart(7037)
                rootNode = null
Coverage.statementStart(7038)
            }
Coverage.statementStart(7039)
            if (pendingImport.size == 0) {
Coverage.ifStart(7040)
                pendingImport.add(newFirstLeaf)
Coverage.statementStart(7041)
            } else if (pendingImport[0] == null) {
Coverage.ifStart(7042)
                pendingImport[0] = newFirstLeaf
Coverage.statementStart(7043)
            } else {
Coverage.ifStart(7044)
                pendingImport[0] = importHelper(pendingImport[0]!!, newFirstLeaf)
Coverage.statementStart(7045)
                if (pendingImport[pendingImport.size - 1] != null) {
Coverage.ifStart(7046)
                    pendingImport.add(null)
Coverage.statementStart(7047)
                }
Coverage.statementStart(7048)
                var j = 1
Coverage.statementStart(7049)
                while (j < pendingImport.size) {
Coverage.whileLoopStart(7050)
                    if (pendingImport[j] == null) {
Coverage.ifStart(7051)
                        pendingImport[j] = pendingImport[j - 1]
Coverage.statementStart(7052)
                        pendingImport[j - 1] = null
Coverage.statementStart(7053)
                        break
                    } else {
Coverage.statementStart(7054)
                        val a = pendingImport[j]!!
Coverage.statementStart(7055)
                        val b = pendingImport[j - 1]!!
Coverage.statementStart(7056)
                        pendingImport[j] = importHelper(a, b)
Coverage.statementStart(7057)
                        NodeManager.freeAllLeaves(a)
Coverage.statementStart(7058)
                        NodeManager.freeAllLeaves(b)
Coverage.statementStart(7059)
                        pendingImport[j - 1] = null
Coverage.statementStart(7060)
                    }
Coverage.statementStart(7061)
                    j++
Coverage.statementStart(7062)
                }
Coverage.statementStart(7063)
            }
Coverage.statementStart(7064)
        }
Coverage.statementStart(7065)
        BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_MERGE_DATA)
Coverage.statementStart(7066)
    }
    fun rebuildData(_iterator: TripleIterator) {
Coverage.funStart(7067)
        val iterator = Count1PassThroughIterator(_iterator)
Coverage.statementStart(7068)
        if (iterator.hasNext()) {
Coverage.ifStart(7069)
            var currentLayer = mutableListOf<Int>()
Coverage.statementStart(7070)
            var newFirstLeaf = NodeManager.nodeNullPointer
Coverage.statementStart(7071)
            var node2: NodeLeaf? = null
Coverage.statementStart(7072)
            NodeManager.allocateNodeLeaf { n, i ->
Coverage.statementStart(7073)
                newFirstLeaf = i
Coverage.statementStart(7074)
                node2 = n
Coverage.statementStart(7075)
                currentLayer.add(i)
Coverage.statementStart(7076)
            }
Coverage.statementStart(7077)
            var node = node2!!
Coverage.statementStart(7078)
            node.initializeWith(iterator)
Coverage.statementStart(7079)
            while (iterator.hasNext()) {
Coverage.whileLoopStart(7080)
                NodeManager.allocateNodeLeaf { n, i ->
Coverage.statementStart(7081)
                    node.setNextNode(i)
Coverage.statementStart(7082)
                    node = n
Coverage.statementStart(7083)
                    currentLayer.add(i)
Coverage.statementStart(7084)
                }
Coverage.statementStart(7085)
                node.initializeWith(iterator)
Coverage.statementStart(7086)
            }
Coverage.statementStart(7087)
            firstLeaf = newFirstLeaf
Coverage.statementStart(7088)
            SanityCheck.check { currentLayer.size > 0 }
Coverage.statementStart(7089)
            while (currentLayer.size > 1) {
Coverage.whileLoopStart(7090)
                var tmp = mutableListOf<Int>()
Coverage.statementStart(7091)
                var prev2: NodeInner? = null
Coverage.statementStart(7092)
                NodeManager.allocateNodeInner { n, i ->
Coverage.statementStart(7093)
                    tmp.add(i)
Coverage.statementStart(7094)
                    n.initializeWith(currentLayer)
Coverage.statementStart(7095)
                    prev2 = n
Coverage.statementStart(7096)
                }
Coverage.statementStart(7097)
                var prev = prev2!!
Coverage.statementStart(7098)
                while (currentLayer.size > 0) {
Coverage.whileLoopStart(7099)
                    NodeManager.allocateNodeInner { n, i ->
Coverage.statementStart(7100)
                        tmp.add(i)
Coverage.statementStart(7101)
                        n.initializeWith(currentLayer)
Coverage.statementStart(7102)
                        prev.setNextNode(i)
Coverage.statementStart(7103)
                        prev = n
Coverage.statementStart(7104)
                    }
Coverage.statementStart(7105)
                }
Coverage.statementStart(7106)
                currentLayer = tmp
Coverage.statementStart(7107)
            }
Coverage.statementStart(7108)
            NodeManager.getNode(currentLayer[0], {
Coverage.statementStart(7109)
                NodeManager.allocateNodeInner { n, i ->
Coverage.statementStart(7110)
                    n.initializeWith(mutableListOf(currentLayer[0]))
Coverage.statementStart(7111)
                    rootNode = n
Coverage.statementStart(7112)
                    root = i
Coverage.statementStart(7113)
                }
Coverage.statementStart(7114)
            }, {
Coverage.statementStart(7115)
                rootNode = it
Coverage.statementStart(7116)
            })
Coverage.statementStart(7117)
        }
Coverage.statementStart(7118)
        countPrimary = iterator.count
Coverage.statementStart(7119)
        distinctPrimary = iterator.distinct
Coverage.statementStart(7120)
    }
    override fun insertAsBulk(data: IntArray, order: IntArray) {
Coverage.funStart(7121)
        flush()
Coverage.statementStart(7122)
        var d = arrayOf(data, IntArray(data.size))
Coverage.statementStart(7123)
        TripleStoreBulkImport.sortUsingBuffers(0, 0, 1, d, data.size / 3, order)
Coverage.statementStart(7124)
        val iteratorImport = BulkImportIterator(d[0], data.size, order)
Coverage.statementStart(7125)
        var iteratorStore2: TripleIterator? = null
Coverage.statementStart(7126)
        if (firstLeaf == NodeManager.nodeNullPointer) {
Coverage.ifStart(7127)
            iteratorStore2 = EmptyIterator()
Coverage.statementStart(7128)
        } else {
Coverage.ifStart(7129)
            NodeManager.getNode(firstLeaf, {
Coverage.statementStart(7130)
                iteratorStore2 = it.iterator()
Coverage.statementStart(7131)
            }, {
Coverage.statementStart(7132)
                throw Exception("unreachable")
            })
Coverage.statementStart(7133)
        }
Coverage.statementStart(7134)
        val iteratorStore = iteratorStore2!!
Coverage.statementStart(7135)
        val iterator = MergeIterator(iteratorStore, DistinctIterator(iteratorImport))
Coverage.statementStart(7136)
        var oldroot = root
Coverage.statementStart(7137)
        rebuildData(iterator)
Coverage.statementStart(7138)
        NodeManager.freeNodeAndAllRelated(oldroot)
Coverage.statementStart(7139)
    }
    override fun removeAsBulk(data: IntArray, order: IntArray) {
Coverage.funStart(7140)
        flush()
Coverage.statementStart(7141)
        var d = arrayOf(data, IntArray(data.size))
Coverage.statementStart(7142)
        TripleStoreBulkImport.sortUsingBuffers(0, 0, 1, d, data.size / 3, order)
Coverage.statementStart(7143)
        val iteratorImport = BulkImportIterator(d[0], data.size, order)
Coverage.statementStart(7144)
        var iteratorStore2: TripleIterator? = null
Coverage.statementStart(7145)
        if (firstLeaf == NodeManager.nodeNullPointer) {
Coverage.ifStart(7146)
            iteratorStore2 = EmptyIterator()
Coverage.statementStart(7147)
        } else {
Coverage.ifStart(7148)
            NodeManager.getNode(firstLeaf, {
Coverage.statementStart(7149)
                iteratorStore2 = it.iterator()
Coverage.statementStart(7150)
            }, {
Coverage.statementStart(7151)
                throw Exception("unreachable")
            })
Coverage.statementStart(7152)
        }
Coverage.statementStart(7153)
        val iteratorStore = iteratorStore2!!
Coverage.statementStart(7154)
        val iterator = MinusIterator(iteratorStore, DistinctIterator(iteratorImport))
Coverage.statementStart(7155)
        var oldroot = root
Coverage.statementStart(7156)
        rebuildData(iterator)
Coverage.statementStart(7157)
        NodeManager.freeNodeAndAllRelated(oldroot)
Coverage.statementStart(7158)
    }
    override fun insert(a: Value, b: Value, c: Value) {
Coverage.funStart(7159)
        throw Exception("unreachable")
    }
    override fun remove(a: Value, b: Value, c: Value) {
Coverage.funStart(7160)
        throw Exception("not implemented")
    }
    override fun clear() {
Coverage.funStart(7161)
        flush()
Coverage.statementStart(7162)
        NodeManager.freeNodeAndAllRelated(root)
Coverage.statementStart(7163)
        firstLeaf = NodeManager.nodeNullPointer
Coverage.statementStart(7164)
        root = NodeManager.nodeNullPointer
Coverage.statementStart(7165)
        rootNode = null
Coverage.statementStart(7166)
    }
    override fun printContents() {
Coverage.funStart(7167)
        if (firstLeaf != NodeManager.nodeNullPointer) {
Coverage.ifStart(7168)
            NodeManager.getNode(firstLeaf, { node ->
Coverage.statementStart(7169)
                var it = node.iterator()
Coverage.statementStart(7170)
                while (it.hasNext()) {
Coverage.whileLoopStart(7171)
                    var d = it.next()
Coverage.statementStart(7172)
                    println("debug ${d.map { it }}")
Coverage.statementStart(7173)
                }
Coverage.statementStart(7174)
            }, {
Coverage.statementStart(7175)
                throw Exception("unreachable")
            })
Coverage.statementStart(7176)
        }
Coverage.statementStart(7177)
    }
}
