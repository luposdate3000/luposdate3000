package lupos.s05tripleStore
import kotlin.jvm.JvmField
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.File
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.index_IDTriple.Node
import lupos.s05tripleStore.index_IDTriple.NodeManager




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
            SanityCheck.check { other is MapKey }
            SanityCheck.check { key.size == (other as MapKey).key.size }
            for (columnIndex in 0 until key.size) {
                if (key[columnIndex] != (other as MapKey).key[columnIndex]) {
                    return false
                }
            }
            return true
        }
    }

    @JvmField
    var data = arrayOf<TripleStoreIndex>()
    @JvmField
    var dataDistinct = arrayOf<Pair<String, TripleStoreIndex>>()

    companion object {
        @JvmField
        var distinctIndices = arrayOf<EIndexPattern>()
    }

    fun safeToFolder(foldername: String) {
        println("TripleStoreLocalBase safeToFolder $foldername")
        dataDistinct.forEach {
            it.second.safeToFile(foldername + "/" + it.first + ".bin")
        }
        NodeManager.safeToFile(foldername + "/nodemanager")
    }

    fun loadFromFolder(foldername: String) {
        dataDistinct.forEach {
            it.second.loadFromFile(foldername + "/" + it.first + ".bin")
        }
        NodeManager.loadFromFile(foldername + "/nodemanager")
    }

    fun getIterator(query: Query, params: Array<AOPBase>, idx: EIndexPattern): ColumnIteratorRow {
        val filter = mutableListOf<Int>()
        val projection = mutableListOf<String>()
        for (ii in 0 until 3) {
            val i = idx.tripleIndicees[ii]
            val param = params[i]
            if (param is AOPConstant) {
                SanityCheck.check { filter.size == ii }
                filter.add(nodeGlobalDictionary.valueToGlobal(param.value))
            } else {
                SanityCheck.check { param is AOPVariable }
                projection.add((param as AOPVariable).name)
            }
        }
        return data[idx.ordinal].getIterator(query, IntArray(filter.size) { filter[it] }, projection)
    }

    fun import(dataImport: TripleStoreBulkImport, idx: EIndexPattern) {
        when (idx) {
            EIndexPattern.SPO, EIndexPattern.SP_O, EIndexPattern.S_PO -> {
                data[idx.ordinal].import(dataImport.dataSPO, dataImport.idx, idx.tripleIndicees)
            }
            EIndexPattern.SOP, EIndexPattern.SO_P, EIndexPattern.S_OP -> {
                data[idx.ordinal].import(dataImport.dataSOP, dataImport.idx, idx.tripleIndicees)
            }
            EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S -> {
                data[idx.ordinal].import(dataImport.dataPOS, dataImport.idx, idx.tripleIndicees)
            }
            EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O -> {
                data[idx.ordinal].import(dataImport.dataPSO, dataImport.idx, idx.tripleIndicees)
            }
            EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P -> {
                data[idx.ordinal].import(dataImport.dataOSP, dataImport.idx, idx.tripleIndicees)
            }
            EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S -> {
                data[idx.ordinal].import(dataImport.dataOPS, dataImport.idx, idx.tripleIndicees)
            }
        }
        println("imported $idx")
        data[idx.ordinal].printContents()
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
                        data[idx.ordinal].insert(row.key[idx.tripleIndicees[0]], row.key[idx.tripleIndicees[1]], row.key[idx.tripleIndicees[2]])
                    }
                    pendingModificationsInsert[idx.ordinal].remove(query.transactionID)
                }
                val delete = pendingModificationsDelete[idx.ordinal][query.transactionID]
                if (delete != null) {
                    for (row in delete) {
                        data[idx.ordinal].remove(row.key[idx.tripleIndicees[0]], row.key[idx.tripleIndicees[1]], row.key[idx.tripleIndicees[2]])
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
        SanityCheck.check { dataModify.size == 3 }
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
                    SanityCheck.check { columnIndex == 0 }
                    break@loop
                } else {
                    k[columnIndex] = query.dictionary.valueToGlobal(v)
                }
            }
            tmp.add(MapKey(k))
        }
    }
}
