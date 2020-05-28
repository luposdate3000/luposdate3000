package lupos.s05tripleStore
import kotlin.jvm.JvmField
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
import lupos.s00misc.File
import lupos.s00misc.MyListInt
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.MyMapLongGeneric
import lupos.s00misc.MyMapLongInt
import lupos.s00misc.MySetInt
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.index_SingleList.ColumnIteratorStore1
import lupos.s05tripleStore.index_SingleList.ColumnIteratorStore2a
import lupos.s05tripleStore.index_SingleList.ColumnIteratorStore2b
import lupos.s05tripleStore.index_SingleList.ColumnIteratorStore3a
import lupos.s05tripleStore.index_SingleList.ColumnIteratorStore3b
import lupos.s05tripleStore.index_SingleList.ColumnIteratorStore3c
class TripleStoreIndex_SingleList : TripleStoreIndex() {
    @JvmField
    var data = MyListInt()
    @JvmField
    val index1 = MyMapIntInt()
    @JvmField
    val index2 = MyMapLongInt()
    override fun safeToFile(filename: String) {
Coverage.funStart(7393)
        File(filename).dataOutputStream { out ->
Coverage.statementStart(7394)
            data.forEach {
Coverage.forEachLoopStart(7395)
                out.writeInt(it)
Coverage.statementStart(7396)
            }
Coverage.statementStart(7397)
        }
Coverage.statementStart(7398)
    }
    fun rebuildMap() {
Coverage.funStart(7399)
        BenchmarkUtils.start(EBenchmark.IMPORT_REBUILD_MAP)
Coverage.statementStart(7400)
        index1.clear()
Coverage.statementStart(7401)
        index2.clear()
Coverage.statementStart(7402)
        index1.withFastInitializer { index1Init ->
Coverage.statementStart(7403)
            index2.withFastInitializer { index2Init ->
Coverage.statementStart(7404)
                var idx = 0
Coverage.statementStart(7405)
                var iterator = data.iterator()
Coverage.statementStart(7406)
                val size0 = iterator.next()
Coverage.statementStart(7407)
                idx++
Coverage.statementStart(7408)
                for (i0 in 0 until size0) {
Coverage.forLoopStart(7409)
                    val key0 = iterator.next()
Coverage.statementStart(7410)
                    idx++
Coverage.statementStart(7411)
                    index1Init.appendAssumeSorted(key0, idx)
Coverage.statementStart(7412)
                    val size1 = iterator.next()
Coverage.statementStart(7413)
                    idx++
Coverage.statementStart(7414)
                    for (i1 in 0 until size1) {
Coverage.forLoopStart(7415)
                        val key1 = iterator.next()
Coverage.statementStart(7416)
                        idx++
Coverage.statementStart(7417)
                        index2Init.appendAssumeSorted((key0.toLong() shl 32) + key1, idx)
Coverage.statementStart(7418)
                        var c = iterator.next()
Coverage.statementStart(7419)
                        idx++
Coverage.statementStart(7420)
                        for (i in 0 until c) {
Coverage.forLoopStart(7421)
                            iterator.next()
Coverage.statementStart(7422)
                        }
Coverage.statementStart(7423)
                        idx += c
Coverage.statementStart(7424)
                    }
Coverage.statementStart(7425)
                }
Coverage.statementStart(7426)
            }
Coverage.statementStart(7427)
        }
Coverage.statementStart(7428)
        BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_REBUILD_MAP)
Coverage.statementStart(7429)
    }
    override fun loadFromFile(filename: String) {
Coverage.funStart(7430)
        SanityCheck.check { data.size == 0 }
Coverage.statementStart(7431)
        val capacity = (File(filename).length() / 4).toInt()
Coverage.statementStart(7432)
        File(filename).dataInputStream { fis ->
Coverage.statementStart(7433)
            data = MyListInt(capacity, { fis.readInt() })
Coverage.statementStart(7434)
        }
Coverage.statementStart(7435)
        rebuildMap()
Coverage.statementStart(7436)
    }
    companion object {
        var storeIteratorCounter = 1L
    }
    override fun printContents() {
Coverage.funStart(7437)
        SanityCheck {
Coverage.statementStart(7438)
            CoroutinesHelper.runBlock {
Coverage.statementStart(7439)
                val ai = ColumnIteratorDebug(-storeIteratorCounter++, "_", ColumnIteratorStore3a(data))
Coverage.statementStart(7440)
                val bi = ColumnIteratorDebug(-storeIteratorCounter++, "_", ColumnIteratorStore3b(data))
Coverage.statementStart(7441)
                val ci = ColumnIteratorDebug(-storeIteratorCounter++, "_", ColumnIteratorStore3c(data))
Coverage.statementStart(7442)
                var a = ai.next()
Coverage.statementStart(7443)
                var b = bi.next()
Coverage.statementStart(7444)
                var c = ci.next()
Coverage.statementStart(7445)
                while (a != null) {
Coverage.whileLoopStart(7446)
                    println("debug store content ::: $a $b $c")
Coverage.statementStart(7447)
                    a = ai.next()
Coverage.statementStart(7448)
                    b = bi.next()
Coverage.statementStart(7449)
                    c = ci.next()
Coverage.statementStart(7450)
                }
Coverage.statementStart(7451)
            }
Coverage.statementStart(7452)
        }
Coverage.statementStart(7453)
    }
    override fun getIterator(query: Query, filter: IntArray, projection: List<String>): IteratorBundle {
Coverage.funStart(7454)
        SanityCheck.check { filter.size >= 0 && filter.size <= 3 }
Coverage.statementStart(7455)
        SanityCheck.check { projection.size + filter.size == 3 }
Coverage.statementStart(7456)
//BenchmarkUtils.start(EBenchmark.STORE_GET_ITERATOR)
Coverage.statementStart(7457)
        val columns = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(7458)
        for (s in projection) {
Coverage.forLoopStart(7459)
            if (s != "_") {
Coverage.ifStart(7460)
                columns[s] = ColumnIterator()
Coverage.statementStart(7461)
            }
Coverage.statementStart(7462)
        }
Coverage.statementStart(7463)
        var res = IteratorBundle(columns)
Coverage.statementStart(7464)
        if (data.size > 0) {
Coverage.ifStart(7465)
            if (filter.size == 3) {
Coverage.ifStart(7466)
                val key = (filter[0].toLong() shl 32) + filter[1]
Coverage.statementStart(7467)
                val idx = index2[key]
Coverage.statementStart(7468)
                if (idx != null) {
Coverage.ifStart(7469)
                    val count = data[idx]
Coverage.statementStart(7470)
                    for (i in idx + 1 until idx + 1 + count) {
Coverage.forLoopStart(7471)
                        if (data[i] == filter[2]) {
Coverage.ifStart(7472)
                            res = IteratorBundle(1)
Coverage.statementStart(7473)
                            break
                        }
Coverage.statementStart(7474)
                    }
Coverage.statementStart(7475)
                }
Coverage.statementStart(7476)
            } else if (filter.size == 2) {
Coverage.ifStart(7477)
                val key = (filter[0].toLong() shl 32) + filter[1]
Coverage.statementStart(7478)
                val idx = index2[key]
Coverage.statementStart(7479)
                if (idx != null) {
Coverage.ifStart(7480)
                    if (projection[0] == "_") {
Coverage.ifStart(7481)
                        res = IteratorBundle(data[idx])
Coverage.statementStart(7482)
                    } else {
Coverage.ifStart(7483)
                        columns[projection[0]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[0], ColumnIteratorStore1(data, idx))
Coverage.statementStart(7484)
                    }
Coverage.statementStart(7485)
                }
Coverage.statementStart(7486)
            } else if (filter.size == 1) {
Coverage.ifStart(7487)
                val idx = index1[filter[0]]
Coverage.statementStart(7488)
                if (idx != null) {
Coverage.ifStart(7489)
                    if (projection[0] != "_") {
Coverage.ifStart(7490)
                        columns[projection[0]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[0], ColumnIteratorStore2a(data, idx))
Coverage.statementStart(7491)
                        if (projection[1] != "_") {
Coverage.ifStart(7492)
                            columns[projection[1]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[1], ColumnIteratorStore2b(data, idx))
Coverage.statementStart(7493)
                        }
Coverage.statementStart(7494)
                    } else {
Coverage.ifStart(7495)
                        SanityCheck.check { projection[1] == "_" }
Coverage.statementStart(7496)
                    }
Coverage.statementStart(7497)
                }
Coverage.statementStart(7498)
            } else {
Coverage.ifStart(7499)
                SanityCheck.check { filter.size == 0 }
Coverage.statementStart(7500)
                if (projection[0] != "_") {
Coverage.ifStart(7501)
                    columns[projection[0]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[0], ColumnIteratorStore3a(data))
Coverage.statementStart(7502)
                    if (projection[1] != "_") {
Coverage.ifStart(7503)
                        columns[projection[1]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[1], ColumnIteratorStore3b(data))
Coverage.statementStart(7504)
                        if (projection[2] != "_") {
Coverage.ifStart(7505)
                            columns[projection[2]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[2], ColumnIteratorStore3c(data))
Coverage.statementStart(7506)
                        }
Coverage.statementStart(7507)
                    } else {
Coverage.ifStart(7508)
                        SanityCheck.check { projection[2] == "_" }
Coverage.statementStart(7509)
                    }
Coverage.statementStart(7510)
                } else {
Coverage.ifStart(7511)
                    SanityCheck.check { projection[1] == "_" }
Coverage.statementStart(7512)
                    SanityCheck.check { projection[2] == "_" }
Coverage.statementStart(7513)
                }
Coverage.statementStart(7514)
            }
Coverage.statementStart(7515)
        }
Coverage.statementStart(7516)
//BenchmarkUtils.elapsedSeconds(EBenchmark.STORE_GET_ITERATOR)
Coverage.statementStart(7517)
        return res
    }
    fun mergeInternal(iterators: Array<Array<ColumnIterator>>): MyListInt {
Coverage.funStart(7518)
        BenchmarkUtils.start(EBenchmark.IMPORT_MERGE_DATA)
Coverage.statementStart(7519)
        var data = MyListInt()
Coverage.statementStart(7520)
        CoroutinesHelper.runBlock {
Coverage.statementStart(7521)
            val head = Array(2) { Array<Int?>(3) { null } }
Coverage.statementStart(7522)
            for (cmp in 0 until 2) {
Coverage.forLoopStart(7523)
                for (i in 0 until 3) {
Coverage.forLoopStart(7524)
                    head[cmp][i] = iterators[cmp][i].next()
Coverage.statementStart(7525)
                }
Coverage.statementStart(7526)
            }
Coverage.statementStart(7527)
            var cmp1 = 0
Coverage.statementStart(7528)
            if (head[0][0] == null) {
Coverage.ifStart(7529)
                cmp1 = 1
Coverage.statementStart(7530)
            } else if (head[1][0] == null) {
Coverage.ifStart(7531)
                cmp1 = 0
Coverage.statementStart(7532)
            } else {
Coverage.ifStart(7533)
                for (i in 0 until 3) {
Coverage.forLoopStart(7534)
                    if (head[0][i]!! < head[1][i]!!) {
Coverage.ifStart(7535)
                        cmp1 = 0
Coverage.statementStart(7536)
                        break
                    } else if (head[0][i]!! > head[1][i]!!) {
Coverage.statementStart(7537)
                        cmp1 = 1
Coverage.statementStart(7538)
                        break
                    }
Coverage.statementStart(7539)
                }
Coverage.statementStart(7540)
            }
Coverage.statementStart(7541)
            var last = Array(3) { head[cmp1][it]!! }
Coverage.statementStart(7542)
            var count = Array(3) { 1 }
Coverage.statementStart(7543)
            var position = Array(3) { data.getNullPointer() }
Coverage.statementStart(7544)
            for (i in 0 until 3) {
Coverage.forLoopStart(7545)
                position[i] = data.addAndGetPointer(count[i])
Coverage.statementStart(7546)
                data.add(head[cmp1][i]!!)
Coverage.statementStart(7547)
                head[cmp1][i] = iterators[cmp1][i].next()
Coverage.statementStart(7548)
            }
Coverage.statementStart(7549)
            loop@ while (head[0][0] != null || head[1][0] != null) {
Coverage.whileLoopStart(7550)
                for (pos in 0 until 3) {
Coverage.forLoopStart(7551)
                    for (cmp in 0 until 2) {
Coverage.forLoopStart(7552)
                        if (head[cmp][pos] != null && (head[1 - cmp][pos] == null || head[cmp][pos]!! < head[1 - cmp][pos]!!)) {
Coverage.ifStart(7553)
                            var hadChange = false
Coverage.statementStart(7554)
                            for (i in 0 until 3) {
Coverage.forLoopStart(7555)
                                if (hadChange) {
Coverage.ifStart(7556)
                                    last[i] = head[cmp][i]!!
Coverage.statementStart(7557)
                                    data.set(position[i], count[i])
Coverage.statementStart(7558)
                                    count[i] = 1
Coverage.statementStart(7559)
                                    position[i] = data.addAndGetPointer(count[i])
Coverage.statementStart(7560)
                                    data.add(head[cmp][i]!!)
Coverage.statementStart(7561)
                                } else if (last[i] != head[cmp][i]) {
Coverage.ifStart(7562)
                                    last[i] = head[cmp][i]!!
Coverage.statementStart(7563)
                                    count[i]++
Coverage.statementStart(7564)
                                    hadChange = true
Coverage.statementStart(7565)
                                    data.add(head[cmp][i]!!)
Coverage.statementStart(7566)
                                }
Coverage.statementStart(7567)
                                head[cmp][i] = iterators[cmp][i].next()
Coverage.statementStart(7568)
                            }
Coverage.statementStart(7569)
                            continue@loop
                        }
Coverage.statementStart(7570)
                    }
Coverage.statementStart(7571)
                }
Coverage.statementStart(7572)
                for (i in 0 until 3) {
Coverage.forLoopStart(7573)
                    head[0][i] = iterators[0][i].next()
Coverage.statementStart(7574)
                }
Coverage.statementStart(7575)
            }
Coverage.statementStart(7576)
            for (i in 0 until 3) {
Coverage.forLoopStart(7577)
                data.set(position[i], count[i])
Coverage.statementStart(7578)
            }
Coverage.statementStart(7579)
        }
Coverage.statementStart(7580)
        BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_MERGE_DATA)
Coverage.statementStart(7581)
        return data
    }
    class ImportIterator(val data: IntArray, val count: Int, val offset: Int) : ColumnIterator() {
        var idx = offset
        init {
Coverage.funStart(7582)
            next = {
Coverage.statementStart(7583)
                var res: Value?
Coverage.statementStart(7584)
                if (idx >= count) {
Coverage.ifStart(7585)
                    res = null
Coverage.statementStart(7586)
                } else {
Coverage.ifStart(7587)
                    res = data[idx]
Coverage.statementStart(7588)
                    idx += 3
Coverage.statementStart(7589)
                }
Coverage.statementStart(7590)
/*return*/res
            }
Coverage.statementStart(7591)
        }
    }
    override fun import(dataImport: IntArray, count: Int, order: IntArray) {
Coverage.funStart(7592)
        if (count > 0) {
Coverage.ifStart(7593)
            SanityCheck {
Coverage.statementStart(7594)
                for (i in 1 until count / 3) {
Coverage.forLoopStart(7595)
                    val xx1 = (i - 1) * 3 + order[0]
Coverage.statementStart(7596)
                    val xx2 = i * 3 + order[0]
Coverage.statementStart(7597)
                    val xx3 = (i - 1) * 3 + order[1]
Coverage.statementStart(7598)
                    val xx4 = i * 3 + order[1]
Coverage.statementStart(7599)
                    val xx5 = (i - 1) * 3 + order[2]
Coverage.statementStart(7600)
                    val xx6 = i * 3 + order[2]
Coverage.statementStart(7601)
                    SanityCheck.check { (dataImport[xx1] < dataImport[xx2]) || (dataImport[xx1] == dataImport[xx2] && dataImport[xx3] < dataImport[xx4]) || (dataImport[xx1] == dataImport[xx2] && dataImport[xx3] == dataImport[xx4] && dataImport[xx5] <= dataImport[xx6]) }
Coverage.statementStart(7602)
                }
Coverage.statementStart(7603)
            }
Coverage.statementStart(7604)
            val iteratorsA: Array<ColumnIterator>
Coverage.statementStart(7605)
            if (data.size == 0) {
Coverage.ifStart(7606)
                iteratorsA = arrayOf(ColumnIterator(), ColumnIterator(), ColumnIterator())
Coverage.statementStart(7607)
            } else {
Coverage.ifStart(7608)
                iteratorsA = arrayOf(ColumnIteratorStore3a(data), ColumnIteratorStore3b(data), ColumnIteratorStore3c(data))
Coverage.statementStart(7609)
            }
Coverage.statementStart(7610)
            data = mergeInternal(arrayOf(iteratorsA, arrayOf<ColumnIterator>(ImportIterator(dataImport, count, order[0]), ImportIterator(dataImport, count, order[1]), ImportIterator(dataImport, count, order[2]))))
Coverage.statementStart(7611)
            rebuildMap()
Coverage.statementStart(7612)
        }
Coverage.statementStart(7613)
        SanityCheck {
Coverage.statementStart(7614)
            CoroutinesHelper.runBlock {
Coverage.statementStart(7615)
                var ia = ColumnIteratorStore3a(data)
Coverage.statementStart(7616)
                var ib = ColumnIteratorStore3b(data)
Coverage.statementStart(7617)
                var ic = ColumnIteratorStore3c(data)
Coverage.statementStart(7618)
                var a = ia.next()
Coverage.statementStart(7619)
                var b = ib.next()
Coverage.statementStart(7620)
                var c = ic.next()
Coverage.statementStart(7621)
                while (a != null) {
Coverage.whileLoopStart(7622)
                    var a2 = ia.next()
Coverage.statementStart(7623)
                    var b2 = ib.next()
Coverage.statementStart(7624)
                    var c2 = ic.next()
Coverage.statementStart(7625)
                    SanityCheck.check { (a2 == null) || (a!! < a2) || ((a == a2) && (b!! < b2!!)) || ((a == a2) && (b == b2) && (c!! < c2!!)) }
Coverage.statementStart(7626)
                    a = a2
Coverage.statementStart(7627)
                    b = b2
Coverage.statementStart(7628)
                    c = c2
Coverage.statementStart(7629)
                }
Coverage.statementStart(7630)
            }
Coverage.statementStart(7631)
        }
Coverage.statementStart(7632)
    }
    fun import(dataImport: MyMapLongGeneric<MySetInt>) {
Coverage.funStart(7633)
        val data1 = MyListInt()
Coverage.statementStart(7634)
        val iterator = dataImport.iterator()
Coverage.statementStart(7635)
        if (iterator.hasNext()) {
Coverage.ifStart(7636)
            var sCount = 1
Coverage.statementStart(7637)
            data1.add(sCount)
Coverage.statementStart(7638)
            var key = iterator.next()
Coverage.statementStart(7639)
            var value = iterator.value()
Coverage.statementStart(7640)
            var sLast = (key shr 32).toInt()
Coverage.statementStart(7641)
            var p = key.toInt()
Coverage.statementStart(7642)
            data1.add(sLast)
Coverage.statementStart(7643)
            var pCount = 1
Coverage.statementStart(7644)
            var pPosition = data1.size
Coverage.statementStart(7645)
            data1.add(pCount)
Coverage.statementStart(7646)
            data1.add(p)
Coverage.statementStart(7647)
            data1.add(value.size)
Coverage.statementStart(7648)
            var oiterator = value.iterator()
Coverage.statementStart(7649)
            while (oiterator.hasNext()) {
Coverage.whileLoopStart(7650)
                data1.add(oiterator.next())
Coverage.statementStart(7651)
            }
Coverage.statementStart(7652)
            while (iterator.hasNext()) {
Coverage.whileLoopStart(7653)
                key = iterator.next()
Coverage.statementStart(7654)
                value = iterator.value()
Coverage.statementStart(7655)
                val s = (key shr 32).toInt()
Coverage.statementStart(7656)
                p = key.toInt()
Coverage.statementStart(7657)
                if (s != sLast) {
Coverage.ifStart(7658)
                    sCount++
Coverage.statementStart(7659)
                    data1[pPosition] = pCount
Coverage.statementStart(7660)
                    data1.add(s)
Coverage.statementStart(7661)
                    sLast = s
Coverage.statementStart(7662)
                    pPosition = data1.size
Coverage.statementStart(7663)
                    pCount = 0
Coverage.statementStart(7664)
                    data1.add(pCount)
Coverage.statementStart(7665)
                }
Coverage.statementStart(7666)
                pCount++
Coverage.statementStart(7667)
                data1.add(p)
Coverage.statementStart(7668)
                data1.add(value.size)
Coverage.statementStart(7669)
                oiterator = value.iterator()
Coverage.statementStart(7670)
                while (oiterator.hasNext()) {
Coverage.whileLoopStart(7671)
                    data1.add(oiterator.next())
Coverage.statementStart(7672)
                }
Coverage.statementStart(7673)
            }
Coverage.statementStart(7674)
            data1[pPosition] = pCount
Coverage.statementStart(7675)
            data1[0] = sCount
Coverage.statementStart(7676)
            if (data.size == 0) {
Coverage.ifStart(7677)
                data = data1
Coverage.statementStart(7678)
            } else {
Coverage.ifStart(7679)
                data = mergeInternal(arrayOf(arrayOf(ColumnIteratorStore3a(data1), ColumnIteratorStore3b(data1), ColumnIteratorStore3c(data1)), arrayOf(ColumnIteratorStore3a(data), ColumnIteratorStore3b(data), ColumnIteratorStore3c(data))))
Coverage.statementStart(7680)
            }
Coverage.statementStart(7681)
            rebuildMap()
Coverage.statementStart(7682)
        }
Coverage.statementStart(7683)
    }
    override fun insert(a: Value, b: Value, c: Value) {
Coverage.funStart(7684)
        throw Exception("not implemented")
    }
    override fun remove(a: Value, b: Value, c: Value) {
Coverage.funStart(7685)
        throw Exception("not implemented")
    }
    override fun clear() {
Coverage.funStart(7686)
        data.clear()
Coverage.statementStart(7687)
        index1.clear()
Coverage.statementStart(7688)
        index2.clear()
Coverage.statementStart(7689)
    }
}
