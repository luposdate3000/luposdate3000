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
        return res
    }

    override fun import(dataImport: MutableList<MyMapInt<MySetInt>>, map0: MyListValue, map1: MyListValue, map2: MyListValue) {
        if (data.size == 0) {
            val tmp = MyListInt()
            val tmpindex = MyMapInt<MyMapIntInt>()
            tmp.add(dataImport.size)
            for (key0 in 0 until dataImport.size) {
                val value0 = dataImport[key0]
                tmp.add(map0[key0])
                var tmp0 = MyMapIntInt()
                tmpindex[map0[key0]] = tmp0
                tmp.add(value0.size)
                for (key1 in value0.keys) {
                    val value1 = value0[key1]!!
                    tmp.add(map1[key1])
                    tmp0[map1[key1]] = tmp.size
                    tmp.add(value1.size)
                    for (key2 in value1) {
                        tmp.add(map2[key2])
                    }
                }
            }
            val iterator0 = tmpindex.iterator()
            data.add(tmpindex.size)
            while (iterator0.hasNext()) {
                val key0 = iterator0.next()
                var value0 = iterator0.value()
                val iterator1 = value0.iterator()
                data.add(key0)
                index1[key0] = data.size
                data.add(value0.size)
                while (iterator1.hasNext()) {
                    val key1 = iterator1.next()
                    var value2 = iterator1.value()
                    data.add(key1)
                    index2[(key0.toLong() shl 32) + key1] = data.size
                    var count = tmp[value2]
                    data.add(count)
                    var tmpdata = MySetInt()
                    for (i in value2 + 1 until value2 + 1 + count) {
                        tmpdata.add(tmp[i])
                    }
                    var iterator2 = tmpdata.iterator()
                    while (iterator2.hasNext()) {
                        val key2 = iterator2.next()
                        data.add(key2)
                    }
                }
            }
        }
    }

    override fun insert(a: Value, b: Value, c: Value) {
        require(false)
    }

    override fun remove(a: Value, b: Value, c: Value) {
        require(false)
    }

    override fun clear() {
        data.clear()
        index1.clear()
        index2.clear()
    }
}
