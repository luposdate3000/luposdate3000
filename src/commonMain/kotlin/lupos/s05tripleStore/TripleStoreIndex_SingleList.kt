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

    override fun getIterator(query: Query, filter: MyListValue, projection: Array<String>): ColumnIteratorRow {
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
                    columns[projection[0]] = ColumnIteratorDebug(-1, projection[0], ColumnIteratorStore1(data, idx))
                }
            }
        } else if (filter.size == 1) {
            val idx = index1[filter[0]]
            if (idx != null) {
                if (projection[0] != "_") {
                    columns[projection[0]] = ColumnIteratorDebug(-2, projection[0], ColumnIteratorStore2a(data, idx))
                    if (projection[1] != "_") {
                        columns[projection[1]] = ColumnIteratorDebug(-3, projection[1], ColumnIteratorStore2b(data, idx))
                    }
                } else {
                    require(projection[1] == "_")
                }
            }
        } else {
            require(filter.size == 0)
            if (projection[0] != "_") {
                columns[projection[0]] = ColumnIteratorDebug(-4, projection[0], ColumnIteratorStore3a(data))
                if (projection[1] != "_") {
                    columns[projection[1]] = ColumnIteratorDebug(-5, projection[1], ColumnIteratorStore3b(data))
                    if (projection[2] != "_") {
                        columns[projection[2]] = ColumnIteratorDebug(-6, projection[2], ColumnIteratorStore3c(data))
                    }
                } else {
                    require(projection[2] == "_")
                }
            } else {
                require(projection[1] == "_")
                require(projection[2] == "_")
            }
        }
//BenchmarkUtils.elapsedSeconds(EBenchmark.STORE_GET_ITERATOR)
        return res
    }

    fun mergeInternal(data1: MyListInt, data2: MyListInt): MyListInt {
        var data = MyListInt()
        CoroutinesHelper.runBlock {
            val iterators = arrayOf(arrayOf(ColumnIteratorStore3a(data1), ColumnIteratorStore3b(data1), ColumnIteratorStore3c(data1)), arrayOf(ColumnIteratorStore3a(data2), ColumnIteratorStore3b(data2), ColumnIteratorStore3c(data2)))
            val head = Array(2) { Array<Int?>(3) { null } }
            for (cmp in 0 until 2) {
                for (i in 0 until 3) {
                    head[cmp][i] = iterators[cmp][i].next()
                }
            }
            var cmp1 = 0
            for (i in 0 until 3) {
                if (head[0][i]!! < head[1][i]!!) {
                    cmp1 = 0
                    break
                } else if (head[0][i]!! > head[1][i]!!) {
                    cmp1 = 1
                    break
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

    override fun import(dataImport: MyMapLongGeneric<MySetInt>) {
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
                data = mergeInternal(data1, data)
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
