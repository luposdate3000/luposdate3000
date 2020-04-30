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

class TripleStoreIndex_SingleList : TripleStoreIndex {
    @JvmField
    var data = MyListInt()
    @JvmField
    val index1 = MyMapIntInt()
    @JvmField
    val index2 = MyMapLongInt()

    override fun safeToFolder(filename: String) {
        File(filename).dataOutputStream { out ->
            for (i in 0 until data.size) {
                out.writeInt(data[i])
            }
        }
    }

    fun rebuildMap() {
        index1.clear()
        index2.clear()
        var idx = 0
        val size0 = data[idx++]
        for (i0 in 0 until size0) {
            val key0 = data[idx++]
            index1[key0] = idx
            val size1 = data[idx++]
            for (i1 in 0 until size1) {
                val key1 = data[idx++]
                index2[(key0.toLong() shl 32) + key1] = idx
                idx += data[idx] + 1
            }
        }
    }

    override fun loadFromFolder(filename: String) {
        require(data.size == 0)
        val capacity = (File(filename).length() / 4).toInt()
        File(filename).dataInputStream { fis ->
            data = MyListInt(capacity, { fis.readInt() })
        }
        rebuildMap()
    }

    companion object {
        var storeIteratorCounter = 1L
    }

    override fun getIterator(query: Query, filter: MyListValue, projection: Array<String>): ColumnIteratorRow {
        var s = StringBuilder()
        for (i in 0 until filter.size) {
            s.append(" f:" + filter[i])
        }
        for (i in 0 until projection.size) {
            s.append(" p:" + projection[i])
        }
        println("ask for iterator ${data.size} ${s.toString()}")
        require(filter.size >= 0 && filter.size <= 3)
        require(projection.size + filter.size == 3)
//BenchmarkUtils.start(EBenchmark.STORE_GET_ITERATOR)
        val columns = mutableMapOf<String, ColumnIterator>()
        for (s in projection) {
            if (s != "_") {
                columns[s] = ColumnIterator()
            }
        }
        var res = ColumnIteratorRow(columns)
        if (data.size > 0) {
            if (filter.size == 3) {
                val key = (filter[0].toLong() shl 32) + filter[1]
                val idx = index2[key]
                if (idx != null) {
                    val count = data[idx]
                    for (i in idx + 1 until idx + 1 + count) {
                        if (data[i] == filter[2]) {
                            res.count = 1
                            break
                        }
                    }
                }
            } else if (filter.size == 2) {
                val key = (filter[0].toLong() shl 32) + filter[1]
                val idx = index2[key]
                if (idx != null) {
                    if (projection[0] == "_") {
                        res.count = data[idx]
                    } else {
                        println("storeIterator -$storeIteratorCounter 1 ${filter[0]} ${filter[1]}")
                        columns[projection[0]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[0], ColumnIteratorStore1(data, idx))
                    }
                }
            } else if (filter.size == 1) {
                val idx = index1[filter[0]]
                if (idx != null) {
                    if (projection[0] != "_") {
                        println("storeIterator -$storeIteratorCounter 2a ${filter[0]}")
                        columns[projection[0]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[0], ColumnIteratorStore2a(data, idx))
                        if (projection[1] != "_") {
                            println("storeIterator -$storeIteratorCounter 2b ${filter[0]}")
                            columns[projection[1]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[1], ColumnIteratorStore2b(data, idx))
                        }
                    } else {
                        require(projection[1] == "_")
                    }
                }
            } else {
                require(filter.size == 0)
                if (projection[0] != "_") {
                    println("storeIterator -$storeIteratorCounter 3a")
                    columns[projection[0]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[0], ColumnIteratorStore3a(data))
                    if (projection[1] != "_") {
                        println("storeIterator -$storeIteratorCounter 3b")
                        columns[projection[1]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[1], ColumnIteratorStore3b(data))
                        if (projection[2] != "_") {
                            println("storeIterator -$storeIteratorCounter 3c")
                            columns[projection[2]] = ColumnIteratorDebug(-storeIteratorCounter++, projection[2], ColumnIteratorStore3c(data))
                        }
                    } else {
                        require(projection[2] == "_")
                    }
                } else {
                    require(projection[1] == "_")
                    require(projection[2] == "_")
                }
            }
        }
//BenchmarkUtils.elapsedSeconds(EBenchmark.STORE_GET_ITERATOR)
        return res
    }

    fun mergeInternal(iterators: Array<Array<ColumnIterator>>): MyListInt {
        var data = MyListInt()
        CoroutinesHelper.runBlock {
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
            var position = Array(3) { 0 }
            for (i in 0 until 3) {
                position[i] = data.size
                data.add(count[i])
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
                                    data[position[i]] = count[i]
                                    count[i] = 1
                                    position[i] = data.size
                                    data.add(count[i])
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
                data[position[i]] = count[i]
            }
        }
        return data
    }

    class ImportIterator(val data: IntArray, val count: Int, val offset: Int) : ColumnIterator() {
        var idx = offset

        init {
            next = {
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
println(dataImport)
        if (count > 0) {
            for (i in 1 until count / 3) {
                val xx1 = (i - 1) * 3 + order[0]
		val xx2 = i * 3 + order[0]
		val xx3 = (i - 1) * 3 + order[1]
		val xx4 = i * 3 + order[1]
		val xx5 = (i - 1) * 3 + order[2]
		val xx6 = i * 3 + order[2]
                require(
                        (dataImport[xx1] < dataImport[xx2]) ||
                                (dataImport[xx1] == dataImport[xx2] && dataImport[xx3] < dataImport[xx4]) ||
                                (dataImport[xx1] == dataImport[xx2] && dataImport[xx3] == dataImport[xx4] && dataImport[xx5] <= dataImport[xx6]),
                        { "input is not ordered $i ${dataImport[xx1]} ${dataImport[xx3]} ${dataImport[xx5]} .. ${dataImport[xx2]} ${dataImport[xx4]} ${dataImport[xx6]}" })
            }
            val count3 = count * 3
            val iteratorsA: Array<ColumnIterator>
            if (data.size == 0) {
                iteratorsA = arrayOf(ColumnIterator(), ColumnIterator(), ColumnIterator())
            } else {
                iteratorsA = arrayOf(ColumnIteratorStore3a(data), ColumnIteratorStore3b(data), ColumnIteratorStore3c(data))
            }
            data = mergeInternal(arrayOf(iteratorsA, arrayOf<ColumnIterator>(ImportIterator(dataImport, count, order[0]), ImportIterator(dataImport, count, order[1]), ImportIterator(dataImport, count, order[2]))))
            rebuildMap()
        }
        CoroutinesHelper.runBlock {
            println("complete data")
            var ia = ColumnIteratorStore3a(data)
            var ib = ColumnIteratorStore3b(data)
            var ic = ColumnIteratorStore3c(data)
            var a = ia.next()
            var b = ib.next()
            var c = ic.next()
            while (a != null) {
                println("$a $b $c")
                var a2 = ia.next()
                var b2 = ib.next()
                var c2 = ic.next()
                require(
                        (a2 == null) ||
                                (a!! < a2!!) ||
                                ((a!! == a2!!) && (b!! < b2!!)) ||
                                ((a!! == a2!!) && (b!! == b2!!) && (c!! < c2!!)), { "incorrect merge with store data $a $b $c .. $a2 $b2 $c2" }
                )
                a = a2
                b = b2
                c = c2
            }
            println("raw data: >>>>>>>>>>")
            var it = data.iterator()
            var i = 0
            while (it.hasNext()) {
                val value = it.next()
                println("$i .. ${value}")
                require(data[i] == value)
                i++
            }
            println("<<<<<<<<<<<<<<0")
            index1.forEach { k, v ->
                println("$v <- $k")
                require(index1[k] == v)
            }
            println("<<<<<<<<<<<<<<1")
            index2.forEach { k, v ->
                println("$v <- ${(k shr 32).toInt()} ${k.toInt()}")
                require(index2[k] == v)
            }
            println("<<<<<<<<<<<<<<2")
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
        require(false, { "insert is not implemented" })
    }

    override fun remove(a: Value, b: Value, c: Value) {
        require(false, { "remove is not implemented" })
    }

    override fun clear() {
        data.clear()
        index1.clear()
        index2.clear()
    }
}
