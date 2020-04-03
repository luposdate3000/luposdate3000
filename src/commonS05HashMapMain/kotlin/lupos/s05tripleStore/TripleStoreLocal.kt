package lupos.s05tripleStore

import kotlin.jvm.JvmField
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
    val dictionary = ResultSetDictionary()
    @JvmField
    val tripleStore = Array(EIndexPattern.values().size) { mutableMapOf<MapKey, MutableSet<MapKey>>() }
    @JvmField
    var tripleStoreSPO = mutableSetOf<MapKey>()
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
                        if (idx == EIndexPattern.SPO) {
                            tripleStoreSPO.add(row)
                        } else {
                            val k = MapKey(idx.keyIndices.map { row.data[it] }.toTypedArray())
                            val v = MapKey(idx.valueIndices.map { row.data[it] }.toTypedArray())
                            val tmp = tripleStore[idx.ordinal][k]
                            if (tmp == null) {
                                tripleStore[idx.ordinal][k] = mutableSetOf(v)
                            } else {
                                tmp.add(v)
                            }
                        }
                        pendingModificationsInsert[idx.ordinal].remove(query.transactionID)
                    }
                }
                val delete = pendingModificationsDelete[idx.ordinal][query.transactionID]
                if (delete != null) {
                    for (row in delete) {
                        if (idx == EIndexPattern.SPO) {
                            tripleStoreSPO.remove(row)
                        } else {
                            val k = MapKey(idx.keyIndices.map { row.data[it] }.toTypedArray())
                            val v = MapKey(idx.valueIndices.map { row.data[it] }.toTypedArray())
                            val tmp = tripleStore[idx.ordinal][k]
                            if (tmp != null) {
                                tmp.remove(v)
                                if (tmp.size == 0) {
                                    tripleStore[idx.ordinal].remove(k)
                                }
                            }
                        }
                        pendingModificationsDelete[idx.ordinal].remove(query.transactionID)
                    }
                }
            }
        }
    }

    fun clear() {
        for (idx in EIndexPattern.values()) {
            tripleStore[idx.ordinal].clear()
            pendingModificationsInsert[idx.ordinal].clear()
            pendingModificationsDelete[idx.ordinal].clear()
        }
        tripleStoreSPO.clear()
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
                }
                k[columnIndex] = dictionary.createValue(query.dictionary.getValue(v))
            }
            tmp.add(MapKey(k))
        }
    }

    fun getIterator(query: Query, params: Array<AOPBase>, idx: EIndexPattern): ColumnIteratorRow {
        val outMap = mutableMapOf<String, ColumnIterator>()
        val variables: List<String>
        var data: Set<MapKey>?
        if (idx == EIndexPattern.SPO) {
            idx.keyIndices.map { require(params[it] is AOPVariable) }
            variables = idx.keyIndices.map { (params[it] as AOPVariable).name }
            data = tripleStoreSPO
            println("store SPO -> ${data.size}")
        } else {
            variables = idx.valueIndices.map { (params[it] as AOPVariable).name }
            val key = MapKey(idx.keyIndices.map { dictionary.createValue((params[it] as AOPConstant).value) }.toTypedArray())
            idx.keyIndices.map { require(params[it] is AOPConstant) }
            idx.valueIndices.map { require(params[it] is AOPVariable) }
            data = tripleStore[idx.ordinal][key]
            println("store $idx ${idx.keyIndices.map { (params[it] as AOPConstant).value.valueToString() }} -> ${data?.size}")
        }
        if (data == null || data.size == 0) {
            for (variable in variables) {
                outMap[variable] = ColumnIteratorDebug(-1L, variable, ColumnIterator())
            }
        } else {
            val columns = Array(variables.size) { mutableListOf<Value>() }
            for (row in data) {
                for (variableIndex in 0 until variables.size) {
                    columns[variableIndex].add(query.dictionary.createValue(dictionary.getValue(row.data[variableIndex])))
                }
            }
            for (variableIndex in 0 until variables.size) {
                outMap[variables[variableIndex]] = ColumnIteratorDebug(-2, variables[variableIndex], ColumnIteratorMultiValue(columns[variableIndex]))
            }
        }
        return ColumnIteratorRow(outMap)
    }
}
