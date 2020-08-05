package lupos.s05tripleStore
import lupos.s04logicalOperators.iterator.ColumnIteratorNext
import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.File
import lupos.s00misc.MyListInt
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.MyMapLongGeneric
import lupos.s00misc.MyMapLongInt
import lupos.s00misc.MySetInt
import lupos.s00misc.SanityCheck
import lupos.s00misc.TripleStoreModifyOperationsNotImplementedException
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator
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
        File(filename).dataOutputStream { out ->
            data.forEach {
                out.writeInt(it)
            }
        }
    }

    override fun flush() {
    }

    fun rebuildMap() {
        index1.clear()
        index2.clear()
        index1.withFastInitializer { index1Init ->
            index2.withFastInitializer { index2Init ->
                var idx = 0
                var iterator = data.iterator()
                val size0 = iterator.next()
                idx++
                for (i0 in 0 until size0) {
                    val key0 = iterator.next()
                    idx++
                    index1Init.appendAssumeSorted(key0, idx)
                    val size1 = iterator.next()
                    idx++
                    for (i1 in 0 until size1) {
                        val key1 = iterator.next()
                        idx++
                        index2Init.appendAssumeSorted((key0.toLong() shl 32) + key1, idx)
                        var c = iterator.next()
                        idx++
                        for (i in 0 until c) {
                            iterator.next()
                        }
                        idx += c
                    }
                }
            }
        }
    }

    override fun loadFromFile(filename: String) {
        SanityCheck.check { data.size == 0 }
        val capacity = (File(filename).length() / 4).toInt()
        File(filename).dataInputStream { fis ->
            data = MyListInt(capacity, { fis.readInt() })
        }
        rebuildMap()
    }

    companion object {
        var storeIteratorCounter = 1L
    }

    override fun printContents() {
        SanityCheck {
            runBlocking {
                val ai = ColumnIteratorStore3a(data)
                val bi = ColumnIteratorStore3b(data)
                val ci = ColumnIteratorStore3c(data)
                var a = ai.next()
                var b = bi.next()
                var c = ci.next()
                while (a != null) {
                    SanityCheck.println({ "debug store content ::: $a $b $c" })
                    a = ai.next()
                    b = bi.next()
                    c = ci.next()
                }
            }
        }
    }

    override fun getIterator(query: Query, params: TripleStoreFeatureParams): IteratorBundle {
        var fp = (params as TripleStoreFeatureParamsDefault).getFilterAndProjection(query)
        val filter = fp.first
        val projection = fp.second
        SanityCheck.check { filter.size >= 0 && filter.size <= 3 }
        SanityCheck.check { projection.size + filter.size == 3 }
        val columns = mutableMapOf<String, ColumnIterator>()
        for (s in projection) {
            if (s != "_") {
                columns[s] = ColumnIterator()
            }
        }
        var res: IteratorBundle? = null
        if (columns.size > 0) {
            res = IteratorBundle(columns)
        }
        if (data.size > 0) {
            if (filter.size == 3) {
                val key = (filter[0].toLong() shl 32) + filter[1]
                val idx = index2[key]
                if (idx != null) {
                    val count = data[idx]
                    for (i in idx + 1 until idx + 1 + count) {
                        if (data[i] == filter[2]) {
                            res = IteratorBundle(1)
                            break
                        }
                    }
                }
            } else if (filter.size == 2) {
                val key = (filter[0].toLong() shl 32) + filter[1]
                val idx = index2[key]
                if (idx != null) {
                    if (projection[0] == "_") {
                        res = IteratorBundle(data[idx])
                    } else {
                        columns[projection[0]] = ColumnIteratorStore1(data, idx)
                    }
                }
            } else if (filter.size == 1) {
                val idx = index1[filter[0]]
                if (idx != null) {
                    if (columns.size == 0) {
                        var count = 0
                        var iterator = ColumnIteratorStore2a(data, idx)
                        runBlocking {
                            while (iterator.next() != null) {
                                count++
                            }
                        }
                        res = IteratorBundle(count)
                    } else {
                        if (projection[0] != "_") {
                            columns[projection[0]] = ColumnIteratorStore2a(data, idx)
                            if (projection[1] != "_") {
                                columns[projection[1]] = ColumnIteratorStore2b(data, idx)
                            }
                        } else {
                            SanityCheck.check { projection[1] == "_" }
                        }
                    }
                }
            } else {
                SanityCheck.check { filter.size == 0 }
                if (columns.size == 0) {
                    var count = 0
                    var iterator = ColumnIteratorStore3a(data)
                    runBlocking {
                        while (iterator.next() != null) {
                            count++
                        }
                    }
                    res = IteratorBundle(count)
                } else {
                    if (projection[0] != "_") {
                        columns[projection[0]] = ColumnIteratorStore3a(data)
                        if (projection[1] != "_") {
                            columns[projection[1]] = ColumnIteratorStore3b(data)
                            if (projection[2] != "_") {
                                columns[projection[2]] = ColumnIteratorStore3c(data)
                            }
                        } else {
                            SanityCheck.check { projection[2] == "_" }
                        }
                    } else {
                        SanityCheck.check { projection[1] == "_" }
                        SanityCheck.check { projection[2] == "_" }
                    }
                }
            }
        }
        if (res == null) {
            res = IteratorBundle(0)
        }
        return res
    }

    fun mergeInternal(iterators: Array<Array<ColumnIterator>>): MyListInt {
        var data = MyListInt()
        runBlocking {
            val head = Array(2) { Array<Int?>(3) { null } }
            for (cmp in 0 until 2) {
                for (i in 0 until 3) {
                    head[cmp][i] = iterators[cmp][i].next()
                }
            }
            var cmp1 = 0
            if (head[0][0] == null) {
                cmp1 = 1
            } else if (head[1][0] == null) {
                cmp1 = 0
            } else {
                for (i in 0 until 3) {
                    if (head[0][i]!! < head[1][i]!!) {
                        cmp1 = 0
                        break
                    } else if (head[0][i]!! > head[1][i]!!) {
                        cmp1 = 1
                        break
                    }
                }
            }
            var last = Array(3) { head[cmp1][it]!! }
            var count = Array(3) { 1 }
            var position = Array(3) { data.getNullPointer() }
            for (i in 0 until 3) {
                position[i] = data.addAndGetPointer(count[i])
                data.add(head[cmp1][i]!!)
                head[cmp1][i] = iterators[cmp1][i].next()
            }
            loop@ while (head[0][0] != null || head[1][0] != null) {
                for (pos in 0 until 3) {
                    for (cmp in 0 until 2) {
                        if (head[cmp][pos] != null && (head[1 - cmp][pos] == null || head[cmp][pos]!! < head[1 - cmp][pos]!!)) {
                            var hadChange = false
                            for (i in 0 until 3) {
                                if (hadChange) {
                                    last[i] = head[cmp][i]!!
                                    data.set(position[i], count[i])
                                    count[i] = 1
                                    position[i] = data.addAndGetPointer(count[i])
                                    data.add(head[cmp][i]!!)
                                } else if (last[i] != head[cmp][i]) {
                                    last[i] = head[cmp][i]!!
                                    count[i]++
                                    hadChange = true
                                    data.add(head[cmp][i]!!)
                                }
                                head[cmp][i] = iterators[cmp][i].next()
                            }
                            continue@loop
                        }
                    }
                }
                for (i in 0 until 3) {
                    head[0][i] = iterators[0][i].next()
                }
            }
            for (i in 0 until 3) {
                data.set(position[i], count[i])
            }
        }
        return data
    }

    class ImportIterator(val data: IntArray, val count: Int, val offset: Int) : ColumnIterator() {
        var idx = offset

        init {
            next =ColumnIteratorNext("ImportIterator.next") {
                var res: Value?
                if (idx >= count) {
                    res = null
                } else {
                    res = data[idx]
                    idx += 3
                }
/*return*/res
            }
        }
    }

    override fun import(dataImport: IntArray, count: Int, order: IntArray) {
        if (count > 0) {
            SanityCheck {
                for (i in 1 until count / 3) {
                    val xx1 = (i - 1) * 3 + order[0]
                    val xx2 = i * 3 + order[0]
                    val xx3 = (i - 1) * 3 + order[1]
                    val xx4 = i * 3 + order[1]
                    val xx5 = (i - 1) * 3 + order[2]
                    val xx6 = i * 3 + order[2]
                    SanityCheck.check { (dataImport[xx1] < dataImport[xx2]) || (dataImport[xx1] == dataImport[xx2] && dataImport[xx3] < dataImport[xx4]) || (dataImport[xx1] == dataImport[xx2] && dataImport[xx3] == dataImport[xx4] && dataImport[xx5] <= dataImport[xx6]) }
                }
            }
            val iteratorsA: Array<ColumnIterator>
            if (data.size == 0) {
                iteratorsA = arrayOf(ColumnIterator(), ColumnIterator(), ColumnIterator())
            } else {
                iteratorsA = arrayOf(ColumnIteratorStore3a(data), ColumnIteratorStore3b(data), ColumnIteratorStore3c(data))
            }
            data = mergeInternal(arrayOf(iteratorsA, arrayOf<ColumnIterator>(ImportIterator(dataImport, count, order[0]), ImportIterator(dataImport, count, order[1]), ImportIterator(dataImport, count, order[2]))))
            rebuildMap()
        }
        SanityCheck {
            runBlocking {
                var ia = ColumnIteratorStore3a(data)
                var ib = ColumnIteratorStore3b(data)
                var ic = ColumnIteratorStore3c(data)
                var a = ia.next()
                var b = ib.next()
                var c = ic.next()
                while (a != null) {
                    var a2 = ia.next()
                    var b2 = ib.next()
                    var c2 = ic.next()
                    SanityCheck.check { (a2 == null) || (a!! < a2) || ((a == a2) && (b!! < b2!!)) || ((a == a2) && (b == b2) && (c!! < c2!!)) }
                    a = a2
                    b = b2
                    c = c2
                }
            }
        }
    }

    fun import(dataImport: MyMapLongGeneric<MySetInt>) {
        val data1 = MyListInt()
        val iterator = dataImport.iterator()
        if (iterator.hasNext()) {
            var sCount = 1
            data1.add(sCount)
            var key = iterator.next()
            var value = iterator.value()
            var sLast = (key shr 32).toInt()
            var p = key.toInt()
            data1.add(sLast)
            var pCount = 1
            var pPosition = data1.size
            data1.add(pCount)
            data1.add(p)
            data1.add(value.size)
            var oiterator = value.iterator()
            while (oiterator.hasNext()) {
                data1.add(oiterator.next())
            }
            while (iterator.hasNext()) {
                key = iterator.next()
                value = iterator.value()
                val s = (key shr 32).toInt()
                p = key.toInt()
                if (s != sLast) {
                    sCount++
                    data1[pPosition] = pCount
                    data1.add(s)
                    sLast = s
                    pPosition = data1.size
                    pCount = 0
                    data1.add(pCount)
                }
                pCount++
                data1.add(p)
                data1.add(value.size)
                oiterator = value.iterator()
                while (oiterator.hasNext()) {
                    data1.add(oiterator.next())
                }
            }
            data1[pPosition] = pCount
            data1[0] = sCount
            if (data.size == 0) {
                data = data1
            } else {
                data = mergeInternal(arrayOf(arrayOf(ColumnIteratorStore3a(data1), ColumnIteratorStore3b(data1), ColumnIteratorStore3c(data1)), arrayOf(ColumnIteratorStore3a(data), ColumnIteratorStore3b(data), ColumnIteratorStore3c(data))))
            }
            rebuildMap()
        }
    }

    override fun insert(a: Value, b: Value, c: Value) {
        throw TripleStoreModifyOperationsNotImplementedException()
    }

    override fun remove(a: Value, b: Value, c: Value) {
        throw TripleStoreModifyOperationsNotImplementedException()
    }

    override fun clear() {
        data.clear()
        index1.clear()
        index2.clear()
    }
}
