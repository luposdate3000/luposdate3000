package lupos.s05tripleStore

import lupos.s00misc.*
import lupos.s00misc.classNameToString
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetIterator
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s05tripleStore.IndexPattern
import lupos.s05tripleStore.POPTripleStoreIteratorBase
import lupos.s09physicalOperators.POPBase


class TripleStoreIterator : POPTripleStoreIteratorBase {
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf()
    private val resultSetNew: ResultSet
    private val resultSetOld: ResultSet
    private var mapIterator: MutableIterator<MutableMap.MutableEntry<ResultRow, MutableSet<ResultRow>>>
    private var listIterator: Iterator<ResultRow>?
    private var sNew: Variable
    private var pNew: Variable
    private var oNew: Variable
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

    constructor(dictionary: ResultSetDictionary, store: TripleStore, index: IndexPattern) {
        this.dictionary = dictionary
        resultSetNew = ResultSet(dictionary)
        sNew = resultSetNew.createVariable(nameS)
        pNew = resultSetNew.createVariable(nameP)
        oNew = resultSetNew.createVariable(nameO)
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

    constructor(dictionary: ResultSetDictionary, store: TripleStore) : this(dictionary, store, IndexPattern.S)

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
    val resultSet = ResultSet(ResultSetDictionary())
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

    inline fun modifyData(transactionID: Long, vals: Value, valp: Value, valo: Value, action: ModifyType) {
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

    inline fun truncate() {
        tripleStoreS.clear()
        tripleStoreP.clear()
        tripleStoreO.clear()
        tripleStoreSP.clear()
        tripleStoreSO.clear()
        tripleStorePO.clear()
        tripleStoreSPO.clear()
    }


    inline fun addData(
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

    inline fun deleteData(
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

    inline fun abort(transactionID: Long) {
        pendingModifications.remove(transactionID)
    }

    inline fun commit2(transactionID: Long) {
        val tmp = pendingModifications[transactionID]
        if (tmp == null)
            return
        for (m in tmp) {
            if (m.first == ModifyType.INSERT)
                commitModifyData(m.second[0], m.second[1], m.second[2], ::addData)
            else if (m.first == ModifyType.DELETE)
                commitModifyData(m.second[0], m.second[1], m.second[2], ::deleteData)
        }
        pendingModifications.remove(transactionID)
    }

    inline fun commitModifyData(vals: Value, valp: Value, valo: Value, action: (ResultRow, ResultRow, MutableMap<ResultRow, MutableSet<ResultRow>>) -> Unit) {
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

    inline fun addData(transactionID: Long, t: List<String?>) {
        val vals = resultSet.createValue(t[0])
        val valp = resultSet.createValue(t[1])
        val valo = resultSet.createValue(t[2])
        modifyData(transactionID, vals, valp, valo, ModifyType.INSERT)
    }

    inline fun deleteData(transactionID: Long, t: List<String?>) {
        val vals = resultSet.createValue(t[0])
        val valp = resultSet.createValue(t[1])
        val valo = resultSet.createValue(t[2])
        modifyData(transactionID, vals, valp, valo, ModifyType.DELETE)
    }

    inline fun addDataVar(transactionID: Long, t: List<Pair<String, Boolean>>) {
        require(t[0].second == true)
        require(t[1].second == true)
        require(t[2].second == true)
        val vals = resultSet.createValue(t[0].first)
        val valp = resultSet.createValue(t[1].first)
        val valo = resultSet.createValue(t[2].first)
        modifyData(transactionID, vals, valp, valo, ModifyType.INSERT)
    }

    inline fun deleteDataVar(transactionID: Long, t: List<Pair<String, Boolean>>) {
        val vals = resultSet.createValue(t[0].first)
        val valp = resultSet.createValue(t[1].first)
        val valo = resultSet.createValue(t[2].first)
        var tmp = 0
        val row = resultSet.createResultRow()
        if (t[0].second) {
            tmp++
            row[s] = vals
        }
        if (t[1].second) {
            tmp++
            row[p] = valp
        }
        if (t[2].second) {
            tmp++
            row[o] = valo
        }
        when (tmp) {
            3 -> modifyData(transactionID, vals, valp, valo, ModifyType.DELETE)
            2 -> {
                if (!t[2].second) {
                    val iterator = tripleStoreSP[row]?.iterator()
                    if (iterator != null) {
                        while (iterator.hasNext()) {
                            val r = iterator.next()
                            modifyData(transactionID, row[s], row[p], r[o], ModifyType.DELETE)
                        }
                    }
                } else if (!t[1].second) {
                    val iterator = tripleStoreSO[row]?.iterator()
                    if (iterator != null) {
                        while (iterator.hasNext()) {
                            val r = iterator.next()
                            modifyData(transactionID, row[s], r[p], row[o], ModifyType.DELETE)
                        }
                    }
                } else {
                    val iterator = tripleStorePO[row]?.iterator()
                    if (iterator != null) {
                        while (iterator.hasNext()) {
                            val r = iterator.next()
                            modifyData(transactionID, r[s], row[p], row[o], ModifyType.DELETE)
                        }
                    }
                }
            }
            else -> {
                throw Exception("deleteDataVar $tmp")
            }
        }
    }

    inline fun addData(transactionID: Long, iterator: ResultSetIterator) {
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

    inline fun getIterator(dictionary: ResultSetDictionary): POPTripleStoreIteratorBase {
        return TripleStoreIterator(dictionary, this)
    }

    inline fun getIterator(dictionary: ResultSetDictionary, s: String, p: String, o: String): POPTripleStoreIteratorBase {
        val res = TripleStoreIterator(dictionary, this)
        res.setMNameS(s)
        res.setMNameP(p)
        res.setMNameO(o)
        return res
    }

    inline fun getIterator(dictionary: ResultSetDictionary, index: IndexPattern): POPTripleStoreIteratorBase {
        return TripleStoreIterator(dictionary, this, index)
    }
}
