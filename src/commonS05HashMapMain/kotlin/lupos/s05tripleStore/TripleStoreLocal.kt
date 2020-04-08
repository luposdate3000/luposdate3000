package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
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

class TripleStoreLocal(@JvmField val name: String) {
    class MapKey(@JvmField val data: Array<Value>) {
        val hashCodeValue: Int

        init {
            var res = 0
            for (columnIndex in 0 until data.size) {
                res += data[columnIndex].hashCode()
            }
            hashCodeValue = res
        }

        override fun hashCode(): Int {
            return hashCodeValue
        }

        override fun equals(other: Any?): Boolean {
            require(other is MapKey)
            require(data.size == other.data.size)
            for (columnIndex in 0 until data.size) {
                if (data[columnIndex] != other.data[columnIndex]) {
                    return false
                }
            }
            return true
        }
    }

    @JvmField
    val dictionary = ResultSetDictionary()
    @JvmField
    val dataSPO = mutableMapOf<Value, MutableMap<Value, MutableSet<Value>>>()//s,sp,spo
    @JvmField
    val dataSOP = mutableMapOf<Value, MutableMap<Value, MutableSet<Value>>>()//so
    @JvmField
    val dataPOS = mutableMapOf<Value, MutableMap<Value, MutableSet<Value>>>()//p,po
    @JvmField
    val dataOSP = mutableMapOf<Value, MutableMap<Value, MutableSet<Value>>>()//o

    fun getIteratorInternal(query: Query, data: MutableMap<Value, MutableMap<Value, MutableSet<Value>>>, filter: Array<Value>, projection: Array<String>): ColumnIteratorRow {
        require(filter.size >= 0 && filter.size <= 3)
        require(projection.size + filter.size == 3)
        var mapping = mutableMapOf<Value, Value>()
        val columns = mutableMapOf<String, ColumnIterator>()
        for (sIndex in 0 until projection.size) {
            val s = projection[sIndex]
            if (s != "_") {
                columns[s] = ColumnIterator()
            }
        }
        var res = ColumnIteratorRow(columns)
        if (filter.size > 0) {
            val tmp = data[filter[0]]
            if (tmp != null) {
                if (filter.size > 1) {
                    val tmp1 = tmp[filter[1]]
                    if (tmp1 != null) {
                        if (filter.size > 2) {
                            if (tmp1.contains(filter[2])) {
                                res.count = 1
                            } else {
                                res.count = 0
                            }
                        } else {
                            if (projection[0] == "_") {
                                res.count = tmp1.size
                            } else {
                                columns[projection[0]] = ColumnIteratorMultiValue(tmp1.map { dictionaryMapInternal(it, dictionary, query.dictionary, mapping) })
                            }
                        }
                    }
                } else {
                    val columnsArr = arrayOf(ColumnIteratorChildIterator(), ColumnIteratorChildIterator())
                    if (projection[0] != "_") {
                        columns[projection[0]] = columnsArr[0]
                    }
                    if (projection[1] != "_") {
                        columns[projection[1]] = columnsArr[1]
                    }
                    var iter = tmp.keys.iterator()
                    for (iterator in columnsArr) {
                        iterator.onNoMoreElements = {
                            if (iter.hasNext()) {
                                val key = iter.next()
                                val value = tmp[key]!!
                                if (projection[0] != "_") {
                                    columnsArr[0].childs.add(ColumnIteratorRepeatValue(value.size, dictionaryMapInternal(key, dictionary, query.dictionary, mapping)))
                                }
                                if (projection[1] != "_") {
                                    columnsArr[1].childs.add(ColumnIteratorMultiValue(value.map { dictionaryMapInternal(it, dictionary, query.dictionary, mapping) }))
                                }
                            }
                        }
                    }
                }
            }
        } else {
            val columnsArr = arrayOf(ColumnIteratorChildIterator(), ColumnIteratorChildIterator(), ColumnIteratorChildIterator())
            if (projection[0] != "_") {
                columns[projection[0]] = columnsArr[0]
            }
            if (projection[1] != "_") {
                columns[projection[1]] = columnsArr[1]
            }
            if (projection[2] != "_") {
                columns[projection[2]] = columnsArr[2]
            }
            var iter = data.keys.iterator()
            if (iter.hasNext()) {
                var key1 = iter.next()
                var value1 = data[key1]!!
                var iter2 = value1.keys.iterator()
                for (iterator in columnsArr) {
                    iterator.onNoMoreElements = {
                        while (true) {
                            if (iter2.hasNext()) {
                                val key2 = iter2.next()
                                val value2 = value1[key2]!!
                                if (projection[0] != "_") {
                                    columnsArr[0].childs.add(ColumnIteratorRepeatValue(value2.size, dictionaryMapInternal(key1, dictionary, query.dictionary, mapping)))
                                }
                                if (projection[1] != "_") {
                                    columnsArr[1].childs.add(ColumnIteratorRepeatValue(value2.size, dictionaryMapInternal(key2, dictionary, query.dictionary, mapping)))
                                }
                                if (projection[2] != "_") {
                                    columnsArr[2].childs.add(ColumnIteratorMultiValue(value2.map { dictionaryMapInternal(it, dictionary, query.dictionary, mapping) }))
                                }
                                break
                            } else {
                                if (iter.hasNext()) {
                                    key1 = iter.next()
                                    value1 = data[key1]!!
                                    iter2 = value1.keys.iterator()
                                } else {
                                    break
                                }
                            }
                        }
                    }
                }
            }
        }
        return res
    }

    fun getIterator(query: Query, params: Array<AOPBase>, idx: EIndexPattern): ColumnIteratorRow {
        val data: MutableMap<Value, MutableMap<Value, MutableSet<Value>>>
        val filter = mutableListOf<Value>()
        val projection = mutableListOf<String>()
        val order: Array<Int>
        when (idx) {
            EIndexPattern.SPO, EIndexPattern.SP, EIndexPattern.S -> {
                data = dataSPO
                order = arrayOf(0, 1, 2)
            }
            EIndexPattern.SO -> {
                data = dataSOP
                order = arrayOf(0, 2, 1)
            }
            EIndexPattern.P, EIndexPattern.PO -> {
                data = dataPOS
                order = arrayOf(1, 2, 0)
            }
            EIndexPattern.O -> {
                data = dataOSP
                order = arrayOf(2, 0, 1)
            }
        }
        for (ii in 0 until 3) {
            val i = order[ii]
            val param = params[i]
            if (param is AOPConstant) {
                require(filter.size == ii)
                filter.add(dictionary.createValue(param.value))
            } else {
                require(param is AOPVariable)
                projection.add(param.name)
            }
        }
        return getIteratorInternal(query, data, filter.toTypedArray(), projection.toTypedArray())
    }

    fun importInternal(data: MutableSet<Int>, store: MutableSet<Value>, map2: Array<Value>) {
        for (rawKey in data) {
            val key = map2[rawKey]
            store.add(key)
        }
    }

    fun importInternal(data: MutableMap<Int, MutableSet<Int>>, store: MutableMap<Value, MutableSet<Value>>, map1: Array<Value>, map2: Array<Value>) {
        for (rawKey in data.keys) {
            val key = map1[rawKey]
            val value = data[rawKey]!!
            var tmp = store[key]
            if (tmp == null) {
                tmp = mutableSetOf<Value>()
                store[key] = tmp
            }
            importInternal(value, tmp, map2)
        }
    }

    fun importInternal(data: MutableList<MutableMap<Int, MutableSet<Int>>>, store: MutableMap<Value, MutableMap<Value, MutableSet<Value>>>, map0: Array<Value>, map1: Array<Value>, map2: Array<Value>) {
        for (rawKey in 0 until data.size) {
            val key = map0[rawKey]
            val value = data[rawKey]
            var tmp = store[key]
            if (tmp == null) {
                tmp = mutableMapOf<Value, MutableSet<Value>>()
                store[key] = tmp
            }
            importInternal(value, tmp, map1, map2)
        }
    }

    fun import(data: TripleStoreBulkImport, idx: EIndexPattern) {
        val mapS = data.dictionaryS.getDictionaryMapping(dictionary)
        val mapP = data.dictionaryP.getDictionaryMapping(dictionary)
        val mapO = data.dictionaryO.getDictionaryMapping(dictionary)
        //BenchmarkUtils.start(EBenchmark.IMPORT_TRIPLE_STORE)
        when (idx) {
            EIndexPattern.SPO, EIndexPattern.SP, EIndexPattern.S -> {
                importInternal(data.dataSPO, dataSPO, mapS, mapP, mapO)
            }
            EIndexPattern.SO -> {
                importInternal(data.dataSOP, dataSOP, mapS, mapO, mapP)
            }
            EIndexPattern.P, EIndexPattern.PO -> {
                importInternal(data.dataPOS, dataPOS, mapP, mapO, mapS)
            }
            EIndexPattern.O -> {
                importInternal(data.dataOSP, dataOSP, mapO, mapS, mapP)
            }
        }
        //BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_TRIPLE_STORE)
    }

    fun insertInternal(a: Value, b: Value, c: Value, data: MutableMap<Value, MutableMap<Value, MutableSet<Value>>>) {
        val tmp = data[a]
        if (tmp == null) {
            data[a] = mutableMapOf(b to mutableSetOf(c))
        } else {
            val tmp2 = tmp[b]
            if (tmp2 == null) {
                tmp[b] = mutableSetOf(c)
            } else {
                tmp2.add(c)
            }
        }
    }

    fun removeInternal(a: Value, b: Value, c: Value, data: MutableMap<Value, MutableMap<Value, MutableSet<Value>>>) {
        val tmp = data[a]
        if (tmp != null) {
            val tmp2 = tmp[b]
            if (tmp2 != null) {
                tmp2.remove(c)
            }
        }
    }

    @JvmField
    val pendingModificationsInsert = Array(EIndexPattern.values().size) { mutableMapOf<Long, MutableSet<MapKey>>() }
    @JvmField
    val pendingModificationsDelete = Array(EIndexPattern.values().size) { mutableMapOf<Long, MutableSet<MapKey>>() }

    fun commit(query: Query) {
        CoroutinesHelper.runBlock {
            for (idx in EIndexPattern.values()) {
                val insert = pendingModificationsInsert[idx.ordinal][query.transactionID]
                if (insert != null) {
                    for (row in insert) {
                        when (idx) {
                            EIndexPattern.SPO, EIndexPattern.SP, EIndexPattern.S -> {
                                insertInternal(row.data[0], row.data[1], row.data[2], dataSPO)
                            }
                            EIndexPattern.SO -> {
                                insertInternal(row.data[0], row.data[2], row.data[1], dataSOP)
                            }
                            EIndexPattern.P, EIndexPattern.PO -> {
                                insertInternal(row.data[1], row.data[2], row.data[0], dataPOS)
                            }
                            EIndexPattern.O -> {
                                insertInternal(row.data[2], row.data[0], row.data[1], dataOSP)
                            }
                        }
                    }
                    pendingModificationsInsert[idx.ordinal].remove(query.transactionID)
                }
                val delete = pendingModificationsDelete[idx.ordinal][query.transactionID]
                if (delete != null) {
                    for (row in delete) {
                        when (idx) {
                            EIndexPattern.SPO, EIndexPattern.SP, EIndexPattern.S -> {
                                removeInternal(row.data[0], row.data[1], row.data[2], dataSPO)
                            }
                            EIndexPattern.SO -> {
                                removeInternal(row.data[0], row.data[2], row.data[1], dataSOP)
                            }
                            EIndexPattern.P, EIndexPattern.PO -> {
                                removeInternal(row.data[1], row.data[2], row.data[0], dataPOS)
                            }
                            EIndexPattern.O -> {
                                removeInternal(row.data[2], row.data[0], row.data[1], dataOSP)
                            }
                        }
                    }
                    pendingModificationsDelete[idx.ordinal].remove(query.transactionID)
                }
            }
        }
    }

    fun clear() {
        dataSPO.clear()
        dataSOP.clear()
        dataPOS.clear()
        dataOSP.clear()
        for (idx in EIndexPattern.values()) {
            pendingModificationsInsert[idx.ordinal].clear()
            pendingModificationsDelete[idx.ordinal].clear()
        }
    }

    fun dictionaryMapInternal(value: Value, dictionaryFrom: ResultSetDictionary, dictionaryTo: ResultSetDictionary, mapping: MutableMap<Value, Value>): Value {
        var tmp = mapping[value]
        if (tmp == null) {
            tmp = dictionaryTo.createValue(dictionaryFrom.getValue(value))
            mapping[value] = tmp
        }
        return tmp
    }

    suspend fun modify(query: Query, data: Array<ColumnIterator>, idx: EIndexPattern, type: EModifyType) {
        require(data.size == 3)
        var tmp: MutableSet<MapKey>?
        if (type == EModifyType.INSERT) {
            tmp = pendingModificationsInsert[idx.ordinal][query.transactionID]
        } else {
            tmp = pendingModificationsDelete[idx.ordinal][query.transactionID]
        }
        if (tmp == null) {
            tmp = mutableSetOf<MapKey>()
            if (type == EModifyType.INSERT) {
                pendingModificationsInsert[idx.ordinal][query.transactionID] = tmp
            } else {
                pendingModificationsDelete[idx.ordinal][query.transactionID] = tmp
            }
        }
        var mapping = mutableMapOf<Value, Value>()
        loop@ while (true) {
            val k = Array(3) { ResultSetDictionary.undefValue }
            for (columnIndex in 0 until 3) {
                val v = data[columnIndex].next()
                if (v == null) {
                    require(columnIndex == 0)
                    break@loop
                } else {
                    k[columnIndex] = dictionaryMapInternal(v, query.dictionary, dictionary, mapping)
                }
            }
            tmp.add(MapKey(k))
        }
    }
}
