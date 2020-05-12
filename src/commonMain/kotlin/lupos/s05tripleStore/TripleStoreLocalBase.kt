package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
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
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.index_IDTriple.NodeManager

abstract class TripleStoreLocalBase(@JvmField val name: String) {
    @JvmField
    var data = arrayOf<TripleStoreIndex>()
    @JvmField
    var dataDistinct = arrayOf<Pair<String, TripleStoreIndex>>()

    companion object {
        @JvmField
        var distinctIndices = arrayOf<EIndexPattern>()
    }

    fun safeToFolder(foldername: String) {
        dataDistinct.forEach {
            it.second.safeToFile(foldername + "/" + it.first + ".bin")
        }
        NodeManager.safeToFile(foldername + "/nodemanager")//required to be called AFTER the individual indicees, to be able to perform some cleanups there
    }

    fun loadFromFolder(foldername: String) {
        NodeManager.loadFromFile(foldername + "/nodemanager")
        dataDistinct.forEach {
            it.second.loadFromFile(foldername + "/" + it.first + ".bin")
        }
    }

    fun getIterator(query: Query, params: Array<AOPBase>, idx: EIndexPattern): IteratorBundle {
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
    }

    @JvmField
    val pendingModificationsInsert = Array(EIndexPattern.values().size) { mutableMapOf<Long, MutableList<Int>>() }
    @JvmField
    val pendingModificationsRemove = Array(EIndexPattern.values().size) { mutableMapOf<Long, MutableList<Int>>() }

    fun commit(query: Query) {
/*
 * the input is ALWAYS in SPO order. The remapping of the triple layout is within the index, using the parameter order.
 */
        CoroutinesHelper.runBlock {
            for (idx in EIndexPattern.values()) {
                var list = pendingModificationsInsert[idx.ordinal][query.transactionID]
                if (list != null) {
                    var tmp = IntArray(list.size)
                    var i = 0
                    var it = list.iterator()
                    while (it.hasNext()) {
                        tmp[i] = it.next()
                        i++
                    }
                    data[idx.ordinal].insertAsBulk(tmp, idx.tripleIndicees)
                    pendingModificationsInsert[idx.ordinal].remove(query.transactionID)
                }
                list = pendingModificationsRemove[idx.ordinal][query.transactionID]
                if (list != null) {
                    var tmp = IntArray(list.size)
                    var i = 0
                    var it = list.iterator()
                    while (it.hasNext()) {
                        tmp[i] = it.next()
                        i++
                    }
                    data[idx.ordinal].removeAsBulk(tmp, idx.tripleIndicees)
                    pendingModificationsInsert[idx.ordinal].remove(query.transactionID)
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
            pendingModificationsRemove[idx.ordinal].clear()
        }
    }

    suspend fun modify(query: Query, dataModify: Array<ColumnIterator>, idx: EIndexPattern, type: EModifyType) {
/*
 * the input iterators are always in the SPO order. The real remapping to the ordering of the store happens within the commit-phase 
 */
        SanityCheck.check { dataModify.size == 3 }
        var tmp: MutableList<Int>?
        if (type == EModifyType.INSERT) {
            tmp = pendingModificationsInsert[idx.ordinal][query.transactionID]
        } else {
            tmp = pendingModificationsRemove[idx.ordinal][query.transactionID]
        }
        if (tmp == null) {
            tmp = mutableListOf<Int>()
            if (type == EModifyType.INSERT) {
                pendingModificationsInsert[idx.ordinal][query.transactionID] = tmp
            } else {
                pendingModificationsRemove[idx.ordinal][query.transactionID] = tmp
            }
        }
        loop@ while (true) {
            for (columnIndex in 0 until 3) {
                val v = dataModify[columnIndex].next()
                if (v == null) {
                    SanityCheck.check { columnIndex == 0 }
                    break@loop
                } else {
                    tmp.add(query.dictionary.valueToGlobal(v))
                }
            }
        }
    }
}
