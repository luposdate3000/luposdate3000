package lupos.s08tripleStore

import lupos.s00misc.classNameToString
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03buildOperatorGraph.data.*
import lupos.s06resultRepresentation.*
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.ResultSetIterator
import lupos.s07physicalOperators.POPBase
import lupos.s08tripleStore.IndexPattern
import lupos.s08tripleStore.POPTripleStoreIteratorBase


class TripleStoreIterator : POPTripleStoreIteratorBase {
    private val resultSetNew = ResultSet()
    private val resultSetOld: ResultSet
    private var mapIterator: MutableIterator<MutableMap.MutableEntry<ResultRow, MutableSet<ResultRow>>>
    private var listIterator: Iterator<ResultRow>?
    private var sNew = resultSetNew.createVariable(nameS)
    private var pNew = resultSetNew.createVariable(nameP)
    private var oNew = resultSetNew.createVariable(nameO)
    private val sOld: Variable
    private val pOld: Variable
    private val oOld: Variable
    private val store: TripleStore
    private var currentKey: ResultRow?
    private var index: IndexPattern = IndexPattern.S
    override fun getGraphName(): String {
        return store.name
    }

    override fun toXMLElement(): XMLElement {
        return XMLElement("TripleStoreIterator").addAttribute("uuid", "" + uuid).addAttribute("nameS", nameS).addAttribute("nameP", nameP).addAttribute("nameO", nameO).addAttribute("name", getGraphName())
    }

    override fun setMNameS(n: String) {
        sNew = resultSetNew.renameVariable(nameS, n)
        nameS = n
    }

    override fun setMNameP(n: String) {
        pNew = resultSetNew.renameVariable(nameP, n)
        nameP = n
    }

    override fun setMNameO(n: String) {
        oNew = resultSetNew.renameVariable(nameO, n)
        nameO = n
    }

    constructor(store: TripleStore, index: IndexPattern) {
        this.store = store
        when (index) {
            IndexPattern.S -> this.index = index
            IndexPattern.P -> this.index = index
            IndexPattern.O -> this.index = index
            IndexPattern.SP -> this.index = index
            IndexPattern.SO -> this.index = index
            IndexPattern.PO -> this.index = index
            IndexPattern.SPO -> this.index = index
            IndexPattern.SOP -> this.index = IndexPattern.SO
            IndexPattern.PSO -> this.index = IndexPattern.SP
            IndexPattern.POS -> this.index = IndexPattern.PO
            IndexPattern.OSP -> this.index = IndexPattern.SO
            IndexPattern.OPS -> this.index = IndexPattern.PO
        }
        when (index) {
            IndexPattern.S -> mapIterator = store.tripleStoreS.iterator()
            IndexPattern.P -> mapIterator = store.tripleStoreP.iterator()
            IndexPattern.O -> mapIterator = store.tripleStoreO.iterator()
            IndexPattern.SP -> mapIterator = store.tripleStoreSP.iterator()
            IndexPattern.SO -> mapIterator = store.tripleStoreSO.iterator()
            IndexPattern.PO -> mapIterator = store.tripleStorePO.iterator()
            IndexPattern.SPO -> mapIterator = store.tripleStoreSPO.iterator()
            else -> throw UnsupportedOperationException("UnsupportedOperationException ${classNameToString(this)} ${index}")
        }
        resultSetOld = store.resultSet
        sOld = resultSetOld.createVariable("s")
        pOld = resultSetOld.createVariable("p")
        oOld = resultSetOld.createVariable("o")
        listIterator = null
        currentKey = null
    }

    constructor(store: TripleStore) : this(store, IndexPattern.S)

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>(nameS, nameP, nameO)
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun next(): ResultRow {
        try {
            Trace.start("TripleStore.next")
            val value = listIterator!!.next()
            val result = resultSetNew.createResultRow()
            if (index == IndexPattern.SPO || index == IndexPattern.SP || index == IndexPattern.SO || index == IndexPattern.S)
                result[sNew] = resultSetNew.createValue(resultSetOld.getValue(currentKey!![sOld]))
            else
                result[sNew] = resultSetNew.createValue(resultSetOld.getValue(value[sOld]))
            if (index == IndexPattern.SPO || index == IndexPattern.SP || index == IndexPattern.PO || index == IndexPattern.P)
                result[pNew] = resultSetNew.createValue(resultSetOld.getValue(currentKey!![pOld]))
            else
                result[pNew] = resultSetNew.createValue(resultSetOld.getValue(value[pOld]))
            if (index == IndexPattern.SPO || index == IndexPattern.SO || index == IndexPattern.PO || index == IndexPattern.O)
                result[oNew] = resultSetNew.createValue(resultSetOld.getValue(currentKey!![oOld]))
            else
                result[oNew] = resultSetNew.createValue(resultSetOld.getValue(value[oOld]))
            return result
        } finally {
            Trace.stop("TripleStore.next")
        }
    }

    override fun hasNext(): Boolean {
        try {
            Trace.start("TripleStore.hasNext")
            while (listIterator == null || !listIterator!!.hasNext()) {
                if (mapIterator.hasNext()) {
                    val tmp = mapIterator.next()
                    currentKey = tmp.key
                    listIterator = tmp.value.iterator()
                } else {
                    break
                }
            }
            val res = listIterator != null && listIterator!!.hasNext()
            return res
        } finally {
            Trace.stop("TripleStore.hasNext")
        }
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }
}

enum class ModifyType {
    INSERT, DELETE
}

class TripleStore {
    val resultSet = ResultSet()
    val s = resultSet.createVariable("s")
    val p = resultSet.createVariable("p")
    val o = resultSet.createVariable("o")
    val tripleStoreS = mutableMapOf<ResultRow, MutableSet<ResultRow>>()
    val tripleStoreP = mutableMapOf<ResultRow, MutableSet<ResultRow>>()
    val tripleStoreO = mutableMapOf<ResultRow, MutableSet<ResultRow>>()
    val tripleStoreSP = mutableMapOf<ResultRow, MutableSet<ResultRow>>()
    val tripleStoreSO = mutableMapOf<ResultRow, MutableSet<ResultRow>>()
    val tripleStorePO = mutableMapOf<ResultRow, MutableSet<ResultRow>>()
    val tripleStoreSPO = mutableMapOf<ResultRow, MutableSet<ResultRow>>()
    val name: String

    val pendingModifications = mutableMapOf<Long, MutableSet<Pair<ModifyType, List<Value>>>>()

    private fun modifyData(transactionID: Long, vals: Value, valp: Value, valo: Value, action: ModifyType) {
        println("modifyData $transactionID")
        var tmp = pendingModifications[transactionID]
        if (tmp == null) {
            tmp = mutableSetOf<Pair<ModifyType, List<Value>>>()
            pendingModifications[transactionID] = tmp
        }
        tmp.add(Pair(action, listOf(vals, valp, valo)))
    }

    constructor(name: String) {
        this.name = name
    }

    fun truncate() {
        tripleStoreS.clear()
        tripleStoreP.clear()
        tripleStoreO.clear()
        tripleStoreSP.clear()
        tripleStoreSO.clear()
        tripleStorePO.clear()
        tripleStoreSPO.clear()
    }


    private fun addData(
            key: ResultRow,
            value: ResultRow,
            store: MutableMap<ResultRow, MutableSet<ResultRow>>
    ) {
        var list = store[key]
        if (list == null) {
            list = mutableSetOf<ResultRow>()
            store[key] = list
        }
        list.add(value)
    }

    private fun deleteData(
            key: ResultRow,
            value: ResultRow,
            store: MutableMap<ResultRow, MutableSet<ResultRow>>
    ) {
        var list = store[key]
        if (list == null) {
            list = mutableSetOf<ResultRow>()
            store[key] = list
        }
        list.remove(value)
    }

    fun abort(transactionID: Long) {
        println("abort $transactionID")
        pendingModifications.remove(transactionID)
    }

    fun commit2(transactionID: Long) {
        println("commit $transactionID")
        println(pendingModifications)
        val tmp = pendingModifications[transactionID]
        println(tmp)
        if (tmp == null)
            return
        for (m in tmp) {
            println(m)
            if (m.first == ModifyType.INSERT)
                commitModifyData(m.second[0], m.second[1], m.second[2], ::addData)
            else if (m.first == ModifyType.DELETE)
                commitModifyData(m.second[0], m.second[1], m.second[2], ::deleteData)
        }
        pendingModifications.remove(transactionID)
    }

    private fun commitModifyData(vals: Value, valp: Value, valo: Value, action: (ResultRow, ResultRow, MutableMap<ResultRow, MutableSet<ResultRow>>) -> Unit) {
        run {
            val rrk = resultSet.createResultRow()
            val rrv = resultSet.createResultRow()
            rrk[s] = vals
            rrv[p] = valp
            rrv[o] = valo
            action(rrk, rrv, tripleStoreS)
        }

        run {
            val rrk = resultSet.createResultRow()
            val rrv = resultSet.createResultRow()
            rrv[s] = vals
            rrk[p] = valp
            rrv[o] = valo
            action(rrk, rrv, tripleStoreP)
        }

        run {
            val rrk = resultSet.createResultRow()
            val rrv = resultSet.createResultRow()
            rrv[s] = vals
            rrv[p] = valp
            rrk[o] = valo
            action(rrk, rrv, tripleStoreO)
        }

        run {
            val rrk = resultSet.createResultRow()
            val rrv = resultSet.createResultRow()
            rrk[s] = vals
            rrk[p] = valp
            rrv[o] = valo
            action(rrk, rrv, tripleStoreSP)
        }

        run {
            val rrk = resultSet.createResultRow()
            val rrv = resultSet.createResultRow()
            rrk[s] = vals
            rrv[p] = valp
            rrk[o] = valo
            action(rrk, rrv, tripleStoreSO)
        }

        run {
            val rrk = resultSet.createResultRow()
            val rrv = resultSet.createResultRow()
            rrv[s] = vals
            rrk[p] = valp
            rrk[o] = valo
            action(rrk, rrv, tripleStorePO)
        }

        run {
            val rrk = resultSet.createResultRow()
            val rrv = resultSet.createResultRow()
            rrk[s] = vals
            rrk[p] = valp
            rrk[o] = valo
            action(rrk, rrv, tripleStoreSPO)
        }
    }

    fun addData(transactionID: Long, t: List<String>) {
        println("addData1 $transactionID")
        val vals = resultSet.createValue(t[0])
        val valp = resultSet.createValue(t[1])
        val valo = resultSet.createValue(t[2])
        modifyData(transactionID, vals, valp, valo, ModifyType.INSERT)
    }

    fun deleteData(transactionID: Long, t: List<String>) {
        println("deleteData $transactionID")
        val vals = resultSet.createValue(t[0])
        val valp = resultSet.createValue(t[1])
        val valo = resultSet.createValue(t[2])
        modifyData(transactionID, vals, valp, valo, ModifyType.DELETE)
    }

    fun addData(transactionID: Long, iterator: ResultSetIterator) {
        println("addData2 $transactionID")
        val rsOld = iterator.getResultSet()
        val sOld = rsOld.createVariable("s")
        val pOld = rsOld.createVariable("p")
        val oOld = rsOld.createVariable("o")
        while (iterator.hasNext()) {
            var data = iterator.next()
            val vals = resultSet.createValue(rsOld.getValue(data[sOld]))
            val valp = resultSet.createValue(rsOld.getValue(data[pOld]))
            val valo = resultSet.createValue(rsOld.getValue(data[oOld]))
            modifyData(transactionID, vals, valp, valo, ModifyType.INSERT)
        }
    }

    fun getIterator(): POPTripleStoreIteratorBase {
        return TripleStoreIterator(this)
    }

    fun getIterator(index: IndexPattern): POPTripleStoreIteratorBase {
        return TripleStoreIterator(this, index)
    }
}
