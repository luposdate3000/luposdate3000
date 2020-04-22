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
    val data = MyListInt()
    @JvmField
    val index1 = MyMapInt<Int>()
    @JvmField
    val index2 = MyMapLong<Int>()

    override fun safeToFolder(filename: String) {
        require(false)
    }

    override fun loadFromFolder(filename: String) {
        require(data.size == 0)
        File(filename).dataInputStream { it ->
            val size0 = it.readInt()
            data.add(size0)
            for (i0 in 0 until size0) {
                val key0 = it.readInt()
                val size1 = it.readInt()
                data.add(key0)
                index1[key0] = data.size
                data.add(size1)
                for (i1 in 0 until size1) {
                    val key1 = it.readInt()
                    val size2 = it.readInt()
                    data.add(key1)
                    index2[(key0.toLong() shl 32) + key1] = data.size
                    data.add(size2)
                    for (i2 in 0 until size2) {
                        val key2 = it.readInt()
                        data.add(key2)
                    }
                }
            }
        }
    }

    override fun getIterator(query: Query, filter: MyListValue, projection: Array<String>): ColumnIteratorRow {
        require(filter.size >= 0 && filter.size <= 3)
        require(projection.size + filter.size == 3)
        val columns = mutableMapOf<String, ColumnIterator>()
        var res = ColumnIteratorRow(columns)
        if (filter.size == 3) {
            val key = (filter[0].toLong() shl 32) + filter[1]
            val idx = index2[key]
            if (idx != null) {
                val count = data[idx]
                var found = false
                for (i in idx + 1 until idx + 1 + count) {
                    if (data[i] == filter[2]) {
                        res.count = 1
                        found = true
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
                }
                if (projection[1] != "_") {
                    columns[projection[1]] = ColumnIteratorDebug(-3, projection[1], ColumnIteratorStore2b(data, idx))
                }
            }
        } else if (filter.size == 0) {
            if (projection[0] != "_") {
                columns[projection[0]] = ColumnIteratorDebug(-2, projection[0], ColumnIteratorStore3a(data))
            }
            if (projection[1] != "_") {
                columns[projection[1]] = ColumnIteratorDebug(-3, projection[1], ColumnIteratorStore3b(data))
            }
            if (projection[2] != "_") {
                columns[projection[2]] = ColumnIteratorDebug(-3, projection[2], ColumnIteratorStore3c(data))
            }
        }
        return res
    }

    override fun import(dataImport: MutableList<MyMapInt<MySetInt>>, map0: MyListValue, map1: MyListValue, map2: MyListValue) {
        require(false)
    }

    override fun insert(a: Value, b: Value, c: Value) {
        require(false)
    }

    override fun remove(a: Value, b: Value, c: Value) {
        require(false)
    }

    override fun clear() {
        require(false)
    }
}
