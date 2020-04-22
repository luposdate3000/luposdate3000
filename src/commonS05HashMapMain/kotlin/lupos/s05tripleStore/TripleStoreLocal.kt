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
    class MapKey(@JvmField val key: Array<Value>) {
        val hashCodeValue: Int

        init {
            var res = 0
            for (columnIndex in 0 until key.size) {
                res += key[columnIndex].hashCode()
            }
            hashCodeValue = res
        }

        override fun hashCode(): Int {
            return hashCodeValue
        }

        override fun equals(other: Any?): Boolean {
            require(other is MapKey)
            require(key.size == other.key.size)
            for (columnIndex in 0 until key.size) {
                if (key[columnIndex] != other.key[columnIndex]) {
                    return false
                }
            }
            return true
        }
    }

    @JvmField
    val data: Array<TripleStoreIndex>
    val order: Array<Array<Int>>
    val dataDistinct: Array<Pair<String, TripleStoreIndex>>

    init {
        dataDistinct = arrayOf(
                Pair("SPO", TripleStoreIndex_MapMapList()),
                Pair("SOP", TripleStoreIndex_MapMapList()),
                Pair("POS", TripleStoreIndex_MapMapList()),
                Pair("PSO", TripleStoreIndex_MapMapList()),
                Pair("OSP", TripleStoreIndex_MapMapList()),
                Pair("OPS", TripleStoreIndex_MapMapList())
        )
        data = Array(EIndexPattern.values().size) {
            val res: TripleStoreIndex
            when (EIndexPattern.values()[it]) {
                EIndexPattern.SPO, EIndexPattern.SP, EIndexPattern.S_0 -> {
                    res = dataDistinct[0].second
                }
                EIndexPattern.SO, EIndexPattern.S_1 -> {
                    res = dataDistinct[1].second
                }
                EIndexPattern.P_0, EIndexPattern.PO -> {
                    res = dataDistinct[2].second
                }
                EIndexPattern.O_0 -> {
                    res = dataDistinct[3].second
                }
                EIndexPattern.P_1 -> {
                    res = dataDistinct[4].second
                }
                EIndexPattern.O_1 -> {
                    res = dataDistinct[5].second
                }
            }
/*return*/res
        }
        order = Array(EIndexPattern.values().size) {
            val res: Array<Int>
            when (EIndexPattern.values()[it]) {
                EIndexPattern.SPO, EIndexPattern.SP, EIndexPattern.S_0 -> {
                    res = arrayOf(0, 1, 2)
                }
                EIndexPattern.SO, EIndexPattern.S_1 -> {
                    res = arrayOf(0, 2, 1)
                }
                EIndexPattern.P_0, EIndexPattern.PO -> {
                    res = arrayOf(1, 2, 0)
                }
                EIndexPattern.O_0 -> {
                    res = arrayOf(2, 0, 1)
                }
                EIndexPattern.P_1 -> {
                    res = arrayOf(1, 0, 2)
                }
                EIndexPattern.O_1 -> {
                    res = arrayOf(2, 1, 0)
                }
            }
/*return*/res
        }
    }

    fun safeToFolder(foldername: String) {
        dataDistinct.forEach {
            it.second.safeToFolder(foldername + it.first + ".bin")
        }
    }

    fun loadFromFolder(foldername: String) {
        dataDistinct.forEach {
            it.second.loadFromFolder(foldername + it.first + ".bin")
        }
    }

    fun getIterator(query: Query, params: Array<AOPBase>, idx: EIndexPattern): ColumnIteratorRow {
        val filter = mutableListOf<Value>()
        val projection = mutableListOf<String>()
        for (ii in 0 until 3) {
            val i = order[idx.ordinal][ii]
            val param = params[i]
            if (param is AOPConstant) {
                require(filter.size == ii)
                filter.add(nodeGlobalDictionary.createValue(param.value))
            } else {
                require(param is AOPVariable)
                projection.add(param.name)
            }
        }
        return data[idx.ordinal].getIterator(query, filter.toTypedArray(), projection.toTypedArray())
    }

    fun import(dataImport: TripleStoreBulkImport, idx: EIndexPattern) {
        val map = arrayOf(
                dataImport.dictionaryS.getDictionaryMapping(nodeGlobalDictionary),
                dataImport.dictionaryP.getDictionaryMapping(nodeGlobalDictionary),
                dataImport.dictionaryO.getDictionaryMapping(nodeGlobalDictionary)
        )
        when (idx) {
            EIndexPattern.SPO, EIndexPattern.SP, EIndexPattern.S_0 -> {
                data[idx.ordinal].import(dataImport.dataSPO, map[order[idx.ordinal][0]], map[order[idx.ordinal][1]], map[order[idx.ordinal][2]])
            }
            EIndexPattern.SO, EIndexPattern.S_1 -> {
                data[idx.ordinal].import(dataImport.dataSOP, map[order[idx.ordinal][0]], map[order[idx.ordinal][1]], map[order[idx.ordinal][2]])
            }
            EIndexPattern.P_0, EIndexPattern.PO -> {
                data[idx.ordinal].import(dataImport.dataPOS, map[order[idx.ordinal][0]], map[order[idx.ordinal][1]], map[order[idx.ordinal][2]])
            }
            EIndexPattern.O_0 -> {
                data[idx.ordinal].import(dataImport.dataOSP, map[order[idx.ordinal][0]], map[order[idx.ordinal][1]], map[order[idx.ordinal][2]])
            }
            EIndexPattern.P_1 -> {
                data[idx.ordinal].import(dataImport.dataPSO, map[order[idx.ordinal][0]], map[order[idx.ordinal][1]], map[order[idx.ordinal][2]])
            }
            EIndexPattern.O_1 -> {
                data[idx.ordinal].import(dataImport.dataOPS, map[order[idx.ordinal][0]], map[order[idx.ordinal][1]], map[order[idx.ordinal][2]])
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
                        data[idx.ordinal].insert(row.key[order[idx.ordinal][0]], row.key[order[idx.ordinal][1]], row.key[order[idx.ordinal][2]])
                    }
                    pendingModificationsInsert[idx.ordinal].remove(query.transactionID)
                }
                val delete = pendingModificationsDelete[idx.ordinal][query.transactionID]
                if (delete != null) {
                    for (row in delete) {
                        data[idx.ordinal].remove(row.key[order[idx.ordinal][0]], row.key[order[idx.ordinal][1]], row.key[order[idx.ordinal][2]])
                    }
                    pendingModificationsDelete[idx.ordinal].remove(query.transactionID)
                }
            }
        }
    }

    fun clear() {
        dataDistinct.forEach {
            it.second.clear()
        }
        for (idx in EIndexPattern.values()) {
            pendingModificationsInsert[idx.ordinal].clear()
            pendingModificationsDelete[idx.ordinal].clear()
        }
    }

    suspend fun modify(query: Query, dataModify: Array<ColumnIterator>, idx: EIndexPattern, type: EModifyType) {
        require(dataModify.size == 3)
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
                val v = dataModify[columnIndex].next()
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
