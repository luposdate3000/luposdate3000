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
    val dataSPO = SortedIntMap<SortedIntMap<SortedIntSet>>()//s_0,sp,spo
    @JvmField
    val dataSOP = SortedIntMap<SortedIntMap<SortedIntSet>>()//s_1,so
    @JvmField
    val dataPOS = SortedIntMap<SortedIntMap<SortedIntSet>>()//p_0,po
    @JvmField
    val dataPSO = SortedIntMap<SortedIntMap<SortedIntSet>>()//p_1
    @JvmField
    val dataOSP = SortedIntMap<SortedIntMap<SortedIntSet>>()//o_0
    @JvmField
    val dataOPS = SortedIntMap<SortedIntMap<SortedIntSet>>()//o_1

    fun getIteratorInternal(query: Query, data: SortedIntMap<SortedIntMap<SortedIntSet>>, filter: Array<Value>, projection: Array<String>): ColumnIteratorRow {
        require(filter.size >= 0 && filter.size <= 3)
        require(projection.size + filter.size == 3)
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
                                columns[projection[0]] = ColumnIteratorDebug(-1, projection[0], ColumnIteratorMultiValue(tmp1.toList()))
                            }
                        }
                    }
                } else {
                    val columnsArr = arrayOf(ColumnIteratorChildIterator(), ColumnIteratorChildIterator())
                    if (projection[0] != "_") {
                        columns[projection[0]] = ColumnIteratorDebug(-2, projection[0], columnsArr[0])
                    }
                    if (projection[1] != "_") {
                        columns[projection[1]] = ColumnIteratorDebug(-3, projection[1], columnsArr[1])
                    }
                    var iter = tmp.iterator()
                    for (iterator in columnsArr) {
                        iterator.onNoMoreElements = {
                            if (iter.hasNext()) {
                                val key = iter.next()
                                val value = iter.value()
                                if (projection[0] != "_") {
                                    columnsArr[0].childs.add(ColumnIteratorRepeatValue(value.size, key))
                                }
                                if (projection[1] != "_") {
                                    columnsArr[1].childs.add(ColumnIteratorMultiValue(value.toList()))
                                }
                            }
                        }
                    }
                }
            }
        } else {
            val columnsArr = arrayOf(ColumnIteratorChildIterator(), ColumnIteratorChildIterator(), ColumnIteratorChildIterator())
            if (projection[0] != "_") {
                columns[projection[0]] = ColumnIteratorDebug(-4, projection[0], columnsArr[0])
            }
            if (projection[1] != "_") {
                columns[projection[1]] = ColumnIteratorDebug(-5, projection[1], columnsArr[1])
            }
            if (projection[2] != "_") {
                columns[projection[2]] = ColumnIteratorDebug(-6, projection[2], columnsArr[2])
            }
            var iter = data.iterator()
            if (iter.hasNext()) {
                var key1 = iter.next()
                var value1 = iter.value()
                var iter2 = value1.iterator()
                for (iterator in columnsArr) {
                    iterator.onNoMoreElements = {
                        while (true) {
                            if (iter2.hasNext()) {
                                val key2 = iter2.next()
                                val value2 = iter2.value()
                                if (projection[0] != "_") {
                                    columnsArr[0].childs.add(ColumnIteratorRepeatValue(value2.size, key1))
                                }
                                if (projection[1] != "_") {
                                    columnsArr[1].childs.add(ColumnIteratorRepeatValue(value2.size, key2))
                                }
                                if (projection[2] != "_") {
                                    columnsArr[2].childs.add(ColumnIteratorMultiValue(value2.toList()))
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
                    }
                }
            }
        }
        return res
    }

    fun getIterator(query: Query, params: Array<AOPBase>, idx: EIndexPattern): ColumnIteratorRow {
        val data: SortedIntMap<SortedIntMap<SortedIntSet>>
        val filter = mutableListOf<Value>()
        val projection = mutableListOf<String>()
        val order: Array<Int>
        when (idx) {
            EIndexPattern.SPO, EIndexPattern.SP, EIndexPattern.S_0 -> {
                data = dataSPO
                order = arrayOf(0, 1, 2)
            }
            EIndexPattern.SO, EIndexPattern.S_1 -> {
                data = dataSOP
                order = arrayOf(0, 2, 1)
            }
            EIndexPattern.P_0, EIndexPattern.PO -> {
                data = dataPOS
                order = arrayOf(1, 2, 0)
            }
            EIndexPattern.O_0 -> {
                data = dataOSP
                order = arrayOf(2, 0, 1)
            }
            EIndexPattern.P_1 -> {
                data = dataPSO
                order = arrayOf(1, 0, 2)
            }
            EIndexPattern.O_1 -> {
                data = dataOPS
                order = arrayOf(2, 1, 0)
            }
        }
        for (ii in 0 until 3) {
            val i = order[ii]
            val param = params[i]
            if (param is AOPConstant) {
                require(filter.size == ii)
                filter.add(nodeGlobalDictionary.createValue(param.value))
            } else {
                require(param is AOPVariable)
                projection.add(param.name)
            }
        }
        return getIteratorInternal(query, data, filter.toTypedArray(), projection.toTypedArray())
    }

    fun importInternal(data: MutableSet<Int>, store: SortedIntSet, map2: Array<Value>) {
        for (rawKey in data) {
            val key = map2[rawKey]
            store.add(key)
        }
    }

    fun importInternal(data: MutableMap<Int, MutableSet<Int>>, store: SortedIntMap<SortedIntSet>, map1: Array<Value>, map2: Array<Value>) {
        for (rawKey in data.keys) {
            val key = map1[rawKey]
            val value = data[rawKey]!!
            var tmp = store.getOrCreate(key, { SortedIntSet() })
            importInternal(value, tmp, map2)
        }
    }

    fun importInternal(data: MutableList<MutableMap<Int, MutableSet<Int>>>, store: SortedIntMap<SortedIntMap<SortedIntSet>>, map0: Array<Value>, map1: Array<Value>, map2: Array<Value>) {
        for (rawKey in 0 until data.size) {
            val key = map0[rawKey]
            val value = data[rawKey]
            var tmp = store.getOrCreate(key, { SortedIntMap<SortedIntSet>() })
            importInternal(value, tmp, map1, map2)
        }
    }

    fun import(data: TripleStoreBulkImport, idx: EIndexPattern) {
        val mapS = data.dictionaryS.getDictionaryMapping(nodeGlobalDictionary)
        val mapP = data.dictionaryP.getDictionaryMapping(nodeGlobalDictionary)
        val mapO = data.dictionaryO.getDictionaryMapping(nodeGlobalDictionary)
        //BenchmarkUtils.start(EBenchmark.IMPORT_TRIPLE_STORE)
        when (idx) {
            EIndexPattern.SPO, EIndexPattern.SP, EIndexPattern.S_0 -> {
                importInternal(data.dataSPO, dataSPO, mapS, mapP, mapO)
            }
            EIndexPattern.SO, EIndexPattern.S_1 -> {
                importInternal(data.dataSOP, dataSOP, mapS, mapO, mapP)
            }
            EIndexPattern.P_0, EIndexPattern.PO -> {
                importInternal(data.dataPOS, dataPOS, mapP, mapO, mapS)
            }
            EIndexPattern.O_0 -> {
                importInternal(data.dataOSP, dataOSP, mapO, mapS, mapP)
            }
            EIndexPattern.P_1 -> {
                importInternal(data.dataPSO, dataPSO, mapP, mapS, mapO)
            }
            EIndexPattern.O_1 -> {
                importInternal(data.dataOPS, dataOPS, mapO, mapP, mapS)
            }
        }
        //BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_TRIPLE_STORE)
    }

    fun insertInternal(a: Value, b: Value, c: Value, data: SortedIntMap<SortedIntMap<SortedIntSet>>) {
        val tmp = data[a]
        if (tmp == null) {
            data[a] = SortedIntMap(b to SortedIntSet(c))
        } else {
            val tmp2 = tmp[b]
            if (tmp2 == null) {
                tmp[b] = SortedIntSet(c)
            } else {
                tmp2.add(c)
            }
        }
    }

    fun removeInternal(a: Value, b: Value, c: Value, data: SortedIntMap<SortedIntMap<SortedIntSet>>) {
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
                            EIndexPattern.SPO, EIndexPattern.SP, EIndexPattern.S_0 -> {
                                insertInternal(row.data[0], row.data[1], row.data[2], dataSPO)
                            }
                            EIndexPattern.SO, EIndexPattern.S_1 -> {
                                insertInternal(row.data[0], row.data[2], row.data[1], dataSOP)
                            }
                            EIndexPattern.P_0, EIndexPattern.PO -> {
                                insertInternal(row.data[1], row.data[2], row.data[0], dataPOS)
                            }
                            EIndexPattern.O_0 -> {
                                insertInternal(row.data[2], row.data[0], row.data[1], dataOSP)
                            }
                            EIndexPattern.P_1 -> {
                                insertInternal(row.data[1], row.data[0], row.data[2], dataPSO)
                            }
                            EIndexPattern.O_1 -> {
                                insertInternal(row.data[2], row.data[1], row.data[0], dataOPS)
                            }
                        }
                    }
                    pendingModificationsInsert[idx.ordinal].remove(query.transactionID)
                }
                val delete = pendingModificationsDelete[idx.ordinal][query.transactionID]
                if (delete != null) {
                    for (row in delete) {
                        when (idx) {
                            EIndexPattern.SPO, EIndexPattern.SP, EIndexPattern.S_0 -> {
                                removeInternal(row.data[0], row.data[1], row.data[2], dataSPO)
                            }
                            EIndexPattern.SO, EIndexPattern.S_1 -> {
                                removeInternal(row.data[0], row.data[2], row.data[1], dataSOP)
                            }
                            EIndexPattern.P_0, EIndexPattern.PO -> {
                                removeInternal(row.data[1], row.data[2], row.data[0], dataPOS)
                            }
                            EIndexPattern.O_0 -> {
                                removeInternal(row.data[2], row.data[0], row.data[1], dataOSP)
                            }
                            EIndexPattern.P_1 -> {
                                removeInternal(row.data[1], row.data[0], row.data[2], dataPSO)
                            }
                            EIndexPattern.O_1 -> {
                                removeInternal(row.data[2], row.data[1], row.data[0], dataOPS)
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
        dataPSO.clear()
        dataOSP.clear()
        dataOPS.clear()
        for (idx in EIndexPattern.values()) {
            pendingModificationsInsert[idx.ordinal].clear()
            pendingModificationsDelete[idx.ordinal].clear()
        }
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
        loop@ while (true) {
            val k = Array(3) { ResultSetDictionary.undefValue }
            for (columnIndex in 0 until 3) {
                val v = data[columnIndex].next()
                if (v == null) {
                    require(columnIndex == 0)
                    break@loop
                } else {
                    k[columnIndex] = query.dictionary.valueToGlobal(v)
                }
            }
            tmp.add(MapKey(k))
        }
    }
}
