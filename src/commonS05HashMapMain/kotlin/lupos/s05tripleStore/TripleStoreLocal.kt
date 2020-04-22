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
    val dataSPO = TripleStoreIndex_MapMapList()//s_0,sp,spo
    @JvmField
    val dataSOP = TripleStoreIndex_MapMapList()//s_1,so
    @JvmField
    val dataPOS = TripleStoreIndex_MapMapList()//p_0,po
    @JvmField
    val dataPSO = TripleStoreIndex_MapMapList()//p_1
    @JvmField
    val dataOSP = TripleStoreIndex_MapMapList()//o_0
    @JvmField
    val dataOPS = TripleStoreIndex_MapMapList()//o_1

    fun safeToFolder(foldername: String) {
        dataSPO.safeToFolder(foldername + "SPO.bin")
        dataSOP.safeToFolder(foldername + "SOP.bin")
        dataPOS.safeToFolder(foldername + "POS.bin")
        dataPSO.safeToFolder(foldername + "PSO.bin")
        dataOSP.safeToFolder(foldername + "OSP.bin")
        dataOPS.safeToFolder(foldername + "OPS.bin")
    }

    fun loadFromFolder(foldername: String) {
        dataSPO.loadFromFolder(foldername + "SPO.bin")
        dataSOP.loadFromFolder(foldername + "SOP.bin")
        dataPOS.loadFromFolder(foldername + "POS.bin")
        dataPSO.loadFromFolder(foldername + "PSO.bin")
        dataOSP.loadFromFolder(foldername + "OSP.bin")
        dataOPS.loadFromFolder(foldername + "OPS.bin")
    }

    fun getIterator(query: Query, params: Array<AOPBase>, idx: EIndexPattern): ColumnIteratorRow {
        val data: TripleStoreIndex_MapMapList
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
        return data.getIterator(query, filter.toTypedArray(), projection.toTypedArray())
    }

    fun import(data: TripleStoreBulkImport, idx: EIndexPattern) {
        val mapS = data.dictionaryS.getDictionaryMapping(nodeGlobalDictionary)
        val mapP = data.dictionaryP.getDictionaryMapping(nodeGlobalDictionary)
        val mapO = data.dictionaryO.getDictionaryMapping(nodeGlobalDictionary)
        //BenchmarkUtils.start(EBenchmark.IMPORT_TRIPLE_STORE)
        when (idx) {
            EIndexPattern.SPO, EIndexPattern.SP, EIndexPattern.S_0 -> {
                dataSPO.import(data.dataSPO, mapS, mapP, mapO)
            }
            EIndexPattern.SO, EIndexPattern.S_1 -> {
                dataSOP.import(data.dataSOP, mapS, mapO, mapP)
            }
            EIndexPattern.P_0, EIndexPattern.PO -> {
                dataPOS.import(data.dataPOS, mapP, mapO, mapS)
            }
            EIndexPattern.O_0 -> {
                dataOSP.import(data.dataOSP, mapO, mapS, mapP)
            }
            EIndexPattern.P_1 -> {
                dataPSO.import(data.dataPSO, mapP, mapS, mapO)
            }
            EIndexPattern.O_1 -> {
                dataOPS.import(data.dataOPS, mapO, mapP, mapS)
            }
        }
        //BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_TRIPLE_STORE)
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
                                dataSPO.insert(row.data[0], row.data[1], row.data[2])
                            }
                            EIndexPattern.SO, EIndexPattern.S_1 -> {
                                dataSOP.insert(row.data[0], row.data[2], row.data[1])
                            }
                            EIndexPattern.P_0, EIndexPattern.PO -> {
                                dataPOS.insert(row.data[1], row.data[2], row.data[0])
                            }
                            EIndexPattern.O_0 -> {
                                dataOSP.insert(row.data[2], row.data[0], row.data[1])
                            }
                            EIndexPattern.P_1 -> {
                                dataPSO.insert(row.data[1], row.data[0], row.data[2])
                            }
                            EIndexPattern.O_1 -> {
                                dataOPS.insert(row.data[2], row.data[1], row.data[0])
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
                                dataSPO.remove(row.data[0], row.data[1], row.data[2])
                            }
                            EIndexPattern.SO, EIndexPattern.S_1 -> {
                                dataSOP.remove(row.data[0], row.data[2], row.data[1])
                            }
                            EIndexPattern.P_0, EIndexPattern.PO -> {
                                dataPOS.remove(row.data[1], row.data[2], row.data[0])
                            }
                            EIndexPattern.O_0 -> {
                                dataOSP.remove(row.data[2], row.data[0], row.data[1])
                            }
                            EIndexPattern.P_1 -> {
                                dataPSO.remove(row.data[1], row.data[0], row.data[2])
                            }
                            EIndexPattern.O_1 -> {
                                dataOPS.remove(row.data[2], row.data[1], row.data[0])
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
