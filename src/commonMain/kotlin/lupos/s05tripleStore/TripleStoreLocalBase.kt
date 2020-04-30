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

abstract class TripleStoreLocalBase(@JvmField val name: String) {
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
    var data = arrayOf<TripleStoreIndex>()
    @JvmField
    var order = arrayOf<IntArray>()
    @JvmField
    var dataDistinct = arrayOf<Pair<String, TripleStoreIndex>>()

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
        val filter = MyListValue()
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
        return data[idx.ordinal].getIterator(query, filter, projection.toTypedArray())
    }

    fun import(dataImport: TripleStoreBulkImport, idx: EIndexPattern) {
println("import $idx")
        when (idx) {
            EIndexPattern.SPO, EIndexPattern.SP, EIndexPattern.S_0 -> {
                data[idx.ordinal].import(dataImport.dataSPO, dataImport.idx, order[idx.ordinal])
            }
            EIndexPattern.SO, EIndexPattern.S_1 -> {
                data[idx.ordinal].import(dataImport.dataSOP, dataImport.idx, order[idx.ordinal])
            }
            EIndexPattern.P_1, EIndexPattern.PO -> {
                data[idx.ordinal].import(dataImport.dataPOS, dataImport.idx, order[idx.ordinal])
            }
            EIndexPattern.O_0 -> {
                data[idx.ordinal].import(dataImport.dataOSP, dataImport.idx, order[idx.ordinal])
            }
            EIndexPattern.P_0 -> {
                data[idx.ordinal].import(dataImport.dataPSO, dataImport.idx, order[idx.ordinal])
            }
            EIndexPattern.O_1 -> {
                data[idx.ordinal].import(dataImport.dataOPS, dataImport.idx, order[idx.ordinal])
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
