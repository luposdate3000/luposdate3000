package lupos.s05tripleStore
import lupos.modulename.File
import lupos.s00misc.MyMapIntGeneric
import lupos.s00misc.MyMapLongGeneric
import lupos.s00misc.MySetInt
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIteratorEmpty
import lupos.s04logicalOperators.iterator.ColumnIteratorEmpty
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.jvm.JvmField
public class TripleStoreIndex_MapMapList : TripleStoreIndex() {
    @JvmField
    val data = MyMapIntGeneric<MyMapIntGeneric<MySetInt>>()
    override suspend fun printContents() {
    }
    override suspend fun flush() {
    }
    override suspend fun safeToFile(filename: String) {
        File(filename).dataOutputStream { out ->
            out.writeInt(data.size)
            val iterator0 = data.iterator()
            while (iterator0.hasNext()) {
                out.writeInt(iterator0.next())
                val value0 = iterator0.value()
                out.writeInt(value0.size)
                val iterator1 = value0.iterator()
                while (iterator1.hasNext()) {
                    out.writeInt(iterator1.next())
                    val value1 = iterator1.value()
                    out.writeInt(value1.size)
                    value1.forEach {
                        out.writeInt(it)
                    }
                }
            }
        }
    }
    override suspend fun loadFromFile(filename: String) {
        File(filename).dataInputStream { it ->
            val size0 = it.readInt()
            data.withFastInitializer { initData ->
                for (i0 in 0 until size0) {
                    val key0 = it.readInt()
                    initData.appendAssumeSorted(key0, MyMapIntGeneric<MySetInt>()).withFastInitializer { tmp0 ->
                        val size1 = it.readInt()
                        for (i1 in 0 until size1) {
                            val key1 = it.readInt()
                            val tmp1 = tmp0.appendAssumeSorted(key1, MySetInt())
                            val size2 = it.readInt()
                            for (i2 in 0 until size2) {
                                val key2 = it.readInt()
                                tmp1.appendAssumeSorted(key2)
                            }
                        }
                    }
                }
            }
        }
    }
    override suspend fun getIterator(query: Query, params: TripleStoreFeatureParams): IteratorBundle {
        var fp = (params as TripleStoreFeatureParamsDefault).getFilterAndProjection(query)
        val filter = fp.first
        val projection = fp.second
        SanityCheck.check { filter.size >= 0 && filter.size <= 3 }
        SanityCheck.check { projection.size + filter.size == 3 }
        val columns = mutableMapOf<String, ColumnIterator>()
        for (sIndex in 0 until projection.size) {
            val s = projection[sIndex]
            if (s != "_") {
                columns[s] = ColumnIteratorEmpty()
            }
        }
        var res: IteratorBundle? = null
        if (columns.size > 0) {
            res = IteratorBundle(columns)
        }
        val columnsArr = Array<ColumnIteratorChildIterator>(3) { ColumnIteratorChildIteratorEmpty() }
        if (filter.size > 0) {
            val tmp = data[filter[0]]
            if (tmp != null) {
                if (filter.size > 1) {
                    val tmp1 = tmp[filter[1]]
                    if (tmp1 != null) {
                        if (filter.size > 2) {
                            if (tmp1.contains(filter[2])) {
                                res = IteratorBundle(1)
                            } else {
                                res = IteratorBundle(0)
                            }
                        } else {
                            if (projection[0] == "_") {
                                res = IteratorBundle(tmp1.size)
                            } else {
                                columns[projection[0]] = ColumnIteratorMultiValue(tmp1.iterator())
                            }
                        }
                    }
                } else {
                    var iter = tmp.iterator()
                    if (columns.size == 0) {
                        var count = 0
                        while (iter.hasNext()) {
                            val key = iter.next()
                            val value = iter.value()
                            count += value.size
                        }
                        res = IteratorBundle(count)
                    } else {
                        for (i in 0 until 2) {
                            columnsArr[i] = object : ColumnIteratorChildIterator() {
                                override suspend fun close() {
                                    _close()
                                }
                                override suspend fun next(): Int {
                                    return next_helper(
                                        {
                                            if (iter.hasNext()) {
                                                val key = iter.next()
                                                val value = iter.value()
                                                if (projection[0] != "_") {
                                                    columnsArr[0].addChild(ColumnIteratorRepeatValue(value.size, key))
                                                }
                                                if (projection[1] != "_") {
                                                    columnsArr[1].addChild(ColumnIteratorMultiValue(value.iterator()))
                                                }
                                            }
                                        },
                                        {
                                            _close()
                                        }
                                    )
                                }
                            }
                        }
                        if (projection[0] != "_") {
                            columns[projection[0]] = columnsArr[0]
                        }
                        if (projection[1] != "_") {
                            columns[projection[1]] = columnsArr[1]
                        }
                    }
                }
            }
        } else {
            var iter = data.iterator()
            if (columns.size == 0) {
                var count = 0
                while (iter.hasNext()) {
                    var key1 = iter.next()
                    var value1 = iter.value()
                    var iter2 = value1.iterator()
                    while (iter2.hasNext()) {
                        val key2 = iter2.next()
                        val value2 = iter2.value()
                        count += value2.size
                    }
                }
                res = IteratorBundle(count)
            } else {
                if (iter.hasNext()) {
                    var key1 = iter.next()
                    var value1 = iter.value()
                    var iter2 = value1.iterator()
                    for (i in 0 until 3) {
                        columnsArr[i] = object : ColumnIteratorChildIterator() {
                            override suspend fun close() {
                                _close()
                            }
                            override suspend fun next(): Int {
                                return next_helper(
                                    {
                                        while (true) {
                                            if (iter2.hasNext()) {
                                                val key2 = iter2.next()
                                                val value2 = iter2.value()
                                                if (projection[0] != "_") {
                                                    columnsArr[0].addChild(ColumnIteratorRepeatValue(value2.size, key1))
                                                }
                                                if (projection[1] != "_") {
                                                    columnsArr[1].addChild(ColumnIteratorRepeatValue(value2.size, key2))
                                                }
                                                if (projection[2] != "_") {
                                                    columnsArr[2].addChild(ColumnIteratorMultiValue(value2.iterator()))
                                                }
                                                break
                                            } else {
                                                if (iter.hasNext()) {
                                                    key1 = iter.next()
                                                    value1 = iter.value()
                                                    iter2 = value1.iterator()
                                                } else {
                                                    break
                                                }
                                            }
                                        }
                                    },
                                    { _close() }
                                )
                            }
                        }
                    }
                }
                if (projection[0] != "_") {
                    columns[projection[0]] = columnsArr[0]
                }
                if (projection[1] != "_") {
                    columns[projection[1]] = columnsArr[1]
                }
                if (projection[2] != "_") {
                    columns[projection[2]] = columnsArr[2]
                }
            }
        }
        if (res == null) {
            res = IteratorBundle(0)
        }
        return res
    }
    override suspend fun import(dataImport: IntArray, count: Int, order: IntArray) {
        if (count > 0) {
            var lastS = dataImport[order[0]]
            var tmpS = data.getOrCreate(lastS, { MyMapIntGeneric<MySetInt>() })
            var lastP = dataImport[order[1]]
            var tmpP = tmpS.getOrCreate(lastP, { MySetInt() })
            tmpP.add(dataImport[order[2]])
            var i = 3
            while (i < count) {
                var s = dataImport[i + order[0]]
                var p = dataImport[i + order[1]]
                if (s != lastS) {
                    lastS = s
                    lastP = p
                    tmpS = data.getOrCreate(lastS, { MyMapIntGeneric<MySetInt>() })
                    tmpP = tmpS.getOrCreate(lastP, { MySetInt() })
                } else if (p != lastP) {
                    lastP = p
                    tmpP = tmpS.getOrCreate(lastP, { MySetInt() })
                }
                tmpP.add(dataImport[i + order[2]])
                i += 3
            }
        }
    }
    public fun import(dataImport: MyMapLongGeneric<MySetInt>) {
        val iterator = dataImport.iterator()
        while (iterator.hasNext()) {
            val key = iterator.next()
            val value = iterator.value()
            val s = (key shr 32).toInt()
            val p = key.toInt()
            val tmpS = data.getOrCreate(s, { MyMapIntGeneric<MySetInt>() })
            val tmpP = tmpS.getOrCreate(p, { MySetInt() })
            val oiterator = value.iterator()
            while (oiterator.hasNext()) {
                tmpP.add(oiterator.next())
            }
        }
    }
    override fun insert(a: Int, b: Int, c: Int) {
        val tmp = data[a]
        if (tmp == null) {
            data[a] = MyMapIntGeneric(b to MySetInt(c))
        } else {
            val tmp2 = tmp[b]
            if (tmp2 == null) {
                tmp[b] = MySetInt(c)
            } else {
                tmp2.add(c)
            }
        }
    }
    override fun remove(a: Int, b: Int, c: Int) {
        val tmp = data[a]
        if (tmp != null) {
            val tmp2 = tmp[b]
            if (tmp2 != null) {
                tmp2.remove(c)
            }
        }
    }
    override suspend fun clear() {
        data.clear()
    }
}
