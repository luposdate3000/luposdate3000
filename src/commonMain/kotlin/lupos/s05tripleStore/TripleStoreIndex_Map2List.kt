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

class TripleStoreIndex_Map2List : TripleStoreIndex {
    @JvmField
    val data = MyMapLong<MySetInt>()

    override fun safeToFolder(filename: String) {
        File(filename).dataOutputStream { out ->
            out.writeInt(data.values.size)
            var iterator0 = data.iterator()
            while (iterator0.hasNext()) {
                out.writeLong(iterator0.next())
                var value1 = iterator0.value()
                out.writeInt(value1.data.size)
                for (i in value1.data) {
                    out.writeInt(i)
                }
            }
        }
    }

    override fun loadFromFolder(filename: String) {
        File(filename).dataInputStream { it ->
            val size0 = it.readInt()
            for (i1 in 0 until size0) {
                val key1 = it.readLong()
                val tmp1 = data.appendAssumeSorted(key1, MySetInt())
                val size2 = it.readInt()
                for (i2 in 0 until size2) {
                    val key2 = it.readInt()
                    tmp1.appendAssumeSorted(key2)
                }
            }
        }
    }

    override fun getIterator(query: Query, filter: MyListValue, projection: Array<String>): ColumnIteratorRow {
        require(filter.size >= 0 && filter.size <= 3)
	require(projection.size==1)
        require(filter.size == 2)
        val columns = mutableMapOf<String, ColumnIterator>()
            if (projection[0] != "_") {
                columns[projection[0]] = ColumnIterator()
            }
        var res = ColumnIteratorRow(columns)
        val tmp = data[(filter[0].toLong() shl 32)+filter[1]]
        if (tmp != null) {
                if (projection[0] == "_") {
                    res.count = tmp.size
                } else {
                    columns[projection[0]] = ColumnIteratorDebug(-1, projection[0], ColumnIteratorMultiValue(tmp.toList()))
                }
        }
        return res
    }

    override fun import(dataImport: MutableList<MyMapInt< MySetInt>>, map0: MyListValue, map1: MyListValue, map2: MyListValue) {
        for (key0 in 0 until dataImport.size) {
	    val value0=dataImport[key0]
            var key=map0[key0].toLong() shl 32
            for(key1 in value0.keys){
		val value1=value0[key1]!!
                val tmp=data.getOrCreate(key+map1[key1], { MySetInt() })
		for(key2 in value1){
			tmp.add(map2[key2])
		}
	    }
        }
    }

    override fun insert(a: Value, b: Value, c: Value) {
val tmp=data.getOrCreate((a.toLong() shl 32)+b, { MySetInt() })
tmp.add(c)
    }

    override fun remove(a: Value, b: Value, c: Value) {
        val tmp = data[(a.toLong() shl 32)+b]
        if (tmp != null) {
                tmp.remove(c)
        }
    }

    override fun clear() {
        data.clear()
    }
}
