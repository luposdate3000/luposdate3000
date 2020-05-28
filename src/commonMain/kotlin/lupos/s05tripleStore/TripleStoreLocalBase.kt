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
Coverage.funStart(7690)
        dataDistinct.forEach {
Coverage.forEachLoopStart(7691)
            it.second.safeToFile(foldername + "/" + it.first + ".bin")
Coverage.statementStart(7692)
        }
Coverage.statementStart(7693)
        NodeManager.safeToFile(foldername + "/nodemanager")//required to be called AFTER the individual indicees, to be able to perform some cleanups there
Coverage.statementStart(7694)
    }
    fun loadFromFolder(foldername: String) {
Coverage.funStart(7695)
        NodeManager.loadFromFile(foldername + "/nodemanager")
Coverage.statementStart(7696)
        dataDistinct.forEach {
Coverage.forEachLoopStart(7697)
            it.second.loadFromFile(foldername + "/" + it.first + ".bin")
Coverage.statementStart(7698)
        }
Coverage.statementStart(7699)
    }
    fun getHistogram(query: Query, params: Array<AOPBase>, idx: EIndexPattern): Pair<Int, Int> {
Coverage.funStart(7700)
        var variableCount = 0
Coverage.statementStart(7701)
        val filter = mutableListOf<Int>()
Coverage.statementStart(7702)
        for (ii in 0 until 3) {
Coverage.forLoopStart(7703)
            val i = idx.tripleIndicees[ii]
Coverage.statementStart(7704)
            val param = params[i]
Coverage.statementStart(7705)
            if (param is AOPConstant) {
Coverage.ifStart(7706)
                SanityCheck.check { filter.size == ii }
Coverage.statementStart(7707)
                filter.add(nodeGlobalDictionary.valueToGlobal(param.value))
Coverage.statementStart(7708)
            } else if (param is AOPVariable) {
Coverage.ifStart(7709)
                SanityCheck {
Coverage.statementStart(7710)
                    if (param.name != "_") {
Coverage.ifStart(7711)
                        variableCount++
Coverage.statementStart(7712)
                    }
Coverage.statementStart(7713)
                }
Coverage.statementStart(7714)
            } else {
Coverage.ifStart(7715)
                throw Exception("unreachable")
            }
Coverage.statementStart(7716)
        }
Coverage.statementStart(7717)
        SanityCheck.check { variableCount == 1 }
Coverage.statementStart(7718)
        return data[idx.ordinal].getHistogram(query, IntArray(filter.size) { filter[it] })
    }
    fun getIterator(query: Query, params: Array<AOPBase>, idx: EIndexPattern): IteratorBundle {
Coverage.funStart(7719)
        val filter = mutableListOf<Int>()
Coverage.statementStart(7720)
        val projection = mutableListOf<String>()
Coverage.statementStart(7721)
        for (ii in 0 until 3) {
Coverage.forLoopStart(7722)
            val i = idx.tripleIndicees[ii]
Coverage.statementStart(7723)
            val param = params[i]
Coverage.statementStart(7724)
            if (param is AOPConstant) {
Coverage.ifStart(7725)
                SanityCheck.check { filter.size == ii }
Coverage.statementStart(7726)
                filter.add(nodeGlobalDictionary.valueToGlobal(param.value))
Coverage.statementStart(7727)
            } else {
Coverage.ifStart(7728)
                SanityCheck.check { param is AOPVariable }
Coverage.statementStart(7729)
                projection.add((param as AOPVariable).name)
Coverage.statementStart(7730)
            }
Coverage.statementStart(7731)
        }
Coverage.statementStart(7732)
        return data[idx.ordinal].getIterator(query, IntArray(filter.size) { filter[it] }, projection)
    }
    fun import(dataImport: TripleStoreBulkImport, idx: EIndexPattern) {
Coverage.funStart(7733)
        when (idx) {
            EIndexPattern.SPO, EIndexPattern.SP_O, EIndexPattern.S_PO -> {
Coverage.whenCaseStart(7735)
                data[idx.ordinal].import(dataImport.dataSPO, dataImport.idx, idx.tripleIndicees)
Coverage.statementStart(7736)
            }
            EIndexPattern.SOP, EIndexPattern.SO_P, EIndexPattern.S_OP -> {
Coverage.whenCaseStart(7737)
                data[idx.ordinal].import(dataImport.dataSOP, dataImport.idx, idx.tripleIndicees)
Coverage.statementStart(7738)
            }
            EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S -> {
Coverage.whenCaseStart(7739)
                data[idx.ordinal].import(dataImport.dataPOS, dataImport.idx, idx.tripleIndicees)
Coverage.statementStart(7740)
            }
            EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O -> {
Coverage.whenCaseStart(7741)
                data[idx.ordinal].import(dataImport.dataPSO, dataImport.idx, idx.tripleIndicees)
Coverage.statementStart(7742)
            }
            EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P -> {
Coverage.whenCaseStart(7743)
                data[idx.ordinal].import(dataImport.dataOSP, dataImport.idx, idx.tripleIndicees)
Coverage.statementStart(7744)
            }
            EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S -> {
Coverage.whenCaseStart(7745)
                data[idx.ordinal].import(dataImport.dataOPS, dataImport.idx, idx.tripleIndicees)
Coverage.statementStart(7746)
            }
        }
Coverage.statementStart(7747)
    }
    @JvmField
    val pendingModificationsInsert = Array(EIndexPattern.values().size) { mutableMapOf<Long, MutableList<Int>>() }
    @JvmField
    val pendingModificationsRemove = Array(EIndexPattern.values().size) { mutableMapOf<Long, MutableList<Int>>() }
    fun commit(query: Query) {
Coverage.funStart(7748)
/*
Coverage.statementStart(7749)
 * the input is ALWAYS in SPO order. The remapping of the triple layout is within the index, using the parameter order.
Coverage.statementStart(7750)
 */
Coverage.statementStart(7751)
        CoroutinesHelper.runBlock {
Coverage.statementStart(7752)
            for (idx in EIndexPattern.values()) {
Coverage.forLoopStart(7753)
                var list = pendingModificationsInsert[idx.ordinal][query.transactionID]
Coverage.statementStart(7754)
                if (list != null) {
Coverage.ifStart(7755)
                    var tmp = IntArray(list.size)
Coverage.statementStart(7756)
                    var i = 0
Coverage.statementStart(7757)
                    var it = list.iterator()
Coverage.statementStart(7758)
                    while (it.hasNext()) {
Coverage.whileLoopStart(7759)
                        tmp[i] = it.next()
Coverage.statementStart(7760)
                        i++
Coverage.statementStart(7761)
                    }
Coverage.statementStart(7762)
                    data[idx.ordinal].insertAsBulk(tmp, idx.tripleIndicees)
Coverage.statementStart(7763)
                    pendingModificationsInsert[idx.ordinal].remove(query.transactionID)
Coverage.statementStart(7764)
                }
Coverage.statementStart(7765)
                list = pendingModificationsRemove[idx.ordinal][query.transactionID]
Coverage.statementStart(7766)
                if (list != null) {
Coverage.ifStart(7767)
                    var tmp = IntArray(list.size)
Coverage.statementStart(7768)
                    var i = 0
Coverage.statementStart(7769)
                    var it = list.iterator()
Coverage.statementStart(7770)
                    while (it.hasNext()) {
Coverage.whileLoopStart(7771)
                        tmp[i] = it.next()
Coverage.statementStart(7772)
                        i++
Coverage.statementStart(7773)
                    }
Coverage.statementStart(7774)
                    data[idx.ordinal].removeAsBulk(tmp, idx.tripleIndicees)
Coverage.statementStart(7775)
                    pendingModificationsInsert[idx.ordinal].remove(query.transactionID)
Coverage.statementStart(7776)
                }
Coverage.statementStart(7777)
            }
Coverage.statementStart(7778)
        }
Coverage.statementStart(7779)
    }
    fun clear() {
Coverage.funStart(7780)
        dataDistinct.forEach {
Coverage.forEachLoopStart(7781)
            it.second.clear()
Coverage.statementStart(7782)
        }
Coverage.statementStart(7783)
        for (idx in EIndexPattern.values()) {
Coverage.forLoopStart(7784)
            pendingModificationsInsert[idx.ordinal].clear()
Coverage.statementStart(7785)
            pendingModificationsRemove[idx.ordinal].clear()
Coverage.statementStart(7786)
        }
Coverage.statementStart(7787)
    }
    suspend fun modify(query: Query, dataModify: Array<ColumnIterator>, idx: EIndexPattern, type: EModifyType) {
Coverage.funStart(7788)
/*
Coverage.statementStart(7789)
 * the input iterators are always in the SPO order. The real remapping to the ordering of the store happens within the commit-phase
Coverage.statementStart(7790)
 */
Coverage.statementStart(7791)
        SanityCheck.check { dataModify.size == 3 }
Coverage.statementStart(7792)
        var tmp: MutableList<Int>?
Coverage.statementStart(7793)
        if (type == EModifyType.INSERT) {
Coverage.ifStart(7794)
            tmp = pendingModificationsInsert[idx.ordinal][query.transactionID]
Coverage.statementStart(7795)
        } else {
Coverage.ifStart(7796)
            tmp = pendingModificationsRemove[idx.ordinal][query.transactionID]
Coverage.statementStart(7797)
        }
Coverage.statementStart(7798)
        if (tmp == null) {
Coverage.ifStart(7799)
            tmp = mutableListOf<Int>()
Coverage.statementStart(7800)
            if (type == EModifyType.INSERT) {
Coverage.ifStart(7801)
                pendingModificationsInsert[idx.ordinal][query.transactionID] = tmp
Coverage.statementStart(7802)
            } else {
Coverage.ifStart(7803)
                pendingModificationsRemove[idx.ordinal][query.transactionID] = tmp
Coverage.statementStart(7804)
            }
Coverage.statementStart(7805)
        }
Coverage.statementStart(7806)
        loop@ while (true) {
Coverage.whileLoopStart(7807)
            for (columnIndex in 0 until 3) {
Coverage.forLoopStart(7808)
                val v = dataModify[columnIndex].next()
Coverage.statementStart(7809)
                if (v == null) {
Coverage.ifStart(7810)
                    SanityCheck.check { columnIndex == 0 }
Coverage.statementStart(7811)
                    break@loop
                } else {
Coverage.statementStart(7812)
                    tmp.add(query.dictionary.valueToGlobal(v))
Coverage.statementStart(7813)
                }
Coverage.statementStart(7814)
            }
Coverage.statementStart(7815)
        }
Coverage.statementStart(7816)
    }
    suspend fun modify(query: Query, dataModify: MutableList<Value>, idx: EIndexPattern, type: EModifyType) {
Coverage.funStart(7817)
/*
Coverage.statementStart(7818)
 * the input iterators are always in the SPO order. The real remapping to the ordering of the store happens within the commit-phase 
Coverage.statementStart(7819)
 */
Coverage.statementStart(7820)
        SanityCheck.check { dataModify.size == 3 }
Coverage.statementStart(7821)
        var tmp: MutableList<Int>?
Coverage.statementStart(7822)
        if (type == EModifyType.INSERT) {
Coverage.ifStart(7823)
            tmp = pendingModificationsInsert[idx.ordinal][query.transactionID]
Coverage.statementStart(7824)
        } else {
Coverage.ifStart(7825)
            tmp = pendingModificationsRemove[idx.ordinal][query.transactionID]
Coverage.statementStart(7826)
        }
Coverage.statementStart(7827)
        if (tmp == null) {
Coverage.ifStart(7828)
            tmp = mutableListOf<Int>()
Coverage.statementStart(7829)
            if (type == EModifyType.INSERT) {
Coverage.ifStart(7830)
                pendingModificationsInsert[idx.ordinal][query.transactionID] = tmp
Coverage.statementStart(7831)
            } else {
Coverage.ifStart(7832)
                pendingModificationsRemove[idx.ordinal][query.transactionID] = tmp
Coverage.statementStart(7833)
            }
Coverage.statementStart(7834)
        }
Coverage.statementStart(7835)
        for (v in dataModify) {
Coverage.forLoopStart(7836)
            tmp.add(query.dictionary.valueToGlobal(v))
Coverage.statementStart(7837)
        }
Coverage.statementStart(7838)
    }
}
