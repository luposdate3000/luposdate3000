package lupos.s05tripleStore

import lupos.s00misc.*
import lupos.s00misc.classNameToString
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.ResultSetIterator
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.POPTripleStoreIteratorBase


class TripleStoreIteratorLocal : POPTripleStoreIteratorBase {
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf()
    private val resultSetNew: ResultSet
    private val resultSetOld: ResultSet
    private val iterator: Iterator<ResultRow>
    private var sNew: Variable
    private var pNew: Variable
    private var oNew: Variable
    private val sOld: Variable
    private val pOld: Variable
    private val oOld: Variable
    private val store: TripleStoreLocal
    private var index: EIndexPattern = EIndexPattern.SPO
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

    constructor(dictionary: ResultSetDictionary, store: TripleStoreLocal, index: EIndexPattern) {
        this.dictionary = dictionary
        resultSetNew = ResultSet(dictionary)
        sNew = resultSetNew.createVariable(nameS)
        pNew = resultSetNew.createVariable(nameP)
        oNew = resultSetNew.createVariable(nameO)
        this.store = store
        this.index = index
        iterator = store.tripleStore[index.ordinal].iterator()
        resultSetOld = store.resultSet
        sOld = resultSetOld.createVariable("s")
        pOld = resultSetOld.createVariable("p")
        oOld = resultSetOld.createVariable("o")
    }

    constructor(dictionary: ResultSetDictionary, store: TripleStoreLocal) : this(dictionary, store, EIndexPattern.SPO)

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>(nameS, nameP, nameO)
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun next(): ResultRow = Trace.trace({
        "TripleStore.next"
    }, {
        val value = iterator!!.next()
        val result = resultSetNew.createResultRow()
        result[sNew] = resultSetNew.createValue(resultSetOld.getValue(value!![sOld]))
        result[pNew] = resultSetNew.createValue(resultSetOld.getValue(value!![pOld]))
        result[oNew] = resultSetNew.createValue(resultSetOld.getValue(value!![oOld]))
        return result
    }) as ResultRow

    override fun hasNext(): Boolean = Trace.trace({ "TripleStore.hasNext" }, {
        return iterator.hasNext()
    }) as Boolean

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }
}

class TripleStoreLocal {
    val resultSet = ResultSet(ResultSetDictionary())
    val s = resultSet.createVariable("s")
    val p = resultSet.createVariable("p")
    val o = resultSet.createVariable("o")
    val tripleStore = Array(EIndexPattern.values().size) { it -> mutableSetOf<ResultRow>() }
    val name: String

    val pendingModifications = Array(EIndexPattern.values().size) { it -> mutableMapOf<Long, MutableSet<Pair<EModifyType, ResultRow>>>() }

    inline fun modifyData(transactionID: Long, vals: Value, valp: Value, valo: Value, action: EModifyType, idx: EIndexPattern) {
        var tmp = pendingModifications[idx.ordinal][transactionID]
        if (tmp == null) {
            tmp = mutableSetOf<Pair<EModifyType, ResultRow>>()
            pendingModifications[idx.ordinal][transactionID] = tmp
        }
        val r = resultSet.createResultRow()
        r[s] = vals
        r[p] = valp
        r[o] = valo
        tmp.add(Pair(action, r))
    }

    constructor(name: String) {
        this.name = name
    }

    inline fun clear() {
        tripleStore.forEach() {
            it.clear()
        }
    }

    inline fun abort(transactionID: Long) {
        pendingModifications.forEach {
            it.remove(transactionID)
        }
    }

    inline fun commit2(transactionID: Long) {
        EIndexPattern.values().forEach {
            val tmp = pendingModifications[it.ordinal][transactionID]
            if (tmp != null) {
                for (m in tmp) {
                    when (m.first) {
                        EModifyType.INSERT -> tripleStore[it.ordinal].add(m.second)
                        EModifyType.DELETE -> tripleStore[it.ordinal].remove(m.second)
                    }
                }
                pendingModifications[it.ordinal].remove(transactionID)
            }
        }
    }

    inline fun addData(transactionID: Long, ss: String, ps: String, os: String, idx: EIndexPattern) {
        val vals = resultSet.createValue(ss)
        val valp = resultSet.createValue(ps)
        val valo = resultSet.createValue(os)
        modifyData(transactionID, vals, valp, valo, EModifyType.INSERT, idx)
    }

    inline fun deleteData(transactionID: Long, ss: String, ps: String, os: String, idx: EIndexPattern) {
        val vals = resultSet.createValue(ss)
        val valp = resultSet.createValue(ps)
        val valo = resultSet.createValue(os)
        modifyData(transactionID, vals, valp, valo, EModifyType.DELETE, idx)
    }

    inline fun addDataVar(transactionID: Long, ss: String, ps: String, os: String, sv: Boolean, pv: Boolean, ov: Boolean, idx: EIndexPattern) {
        require(sv == true)
        require(pv == true)
        require(ov == true)
        val vals = resultSet.createValue(ss)
        val valp = resultSet.createValue(ps)
        val valo = resultSet.createValue(os)
        modifyData(transactionID, vals, valp, valo, EModifyType.INSERT, idx)
    }

    inline fun deleteDataVar(transactionID: Long, ss: String, ps: String, os: String, sv: Boolean, pv: Boolean, ov: Boolean, idx: EIndexPattern) {
        val vals: Value = resultSet.createValue(ss)
        val valp: Value = resultSet.createValue(ps)
        val valo: Value = resultSet.createValue(os)
        var tmp = 0
        val row = resultSet.createResultRow()
        if (sv) {
            tmp++
            row[s] = vals
        }
        if (pv) {
            tmp++
            row[p] = valp
        }
        if (ov) {
            tmp++
            row[o] = valo
        }
        when (tmp) {
            3 -> modifyData(transactionID, vals, valp, valo, EModifyType.DELETE, idx)
            2 -> {
                if (!ov) {
                    val iterator = tripleStore[idx.ordinal].iterator()
                        while (iterator.hasNext()) {
                            val r = iterator.next()
                            if (r[s] == vals && r[p] == valp)
                                modifyData(transactionID, r[s], r[p], r[o], EModifyType.DELETE, idx)
                    }
                } else if (!pv) {
                    val iterator = tripleStore[idx.ordinal].iterator()
                        while (iterator.hasNext()) {
                            val r = iterator.next()
                            if (r[s] == vals && r[o] == valo)
                                modifyData(transactionID, r[s], r[p], r[o], EModifyType.DELETE, idx)
                    }
                } else {
                    val iterator = tripleStore[idx.ordinal].iterator()
                        while (iterator.hasNext()) {
                            val r = iterator.next()
                            if (r[p] == valp && r[o] == valo)
                                modifyData(transactionID, r[s], r[p], r[o], EModifyType.DELETE, idx)
                    }
                }
            }
            1 -> {
                if (ov) {
                    val iterator = tripleStore[idx.ordinal].iterator()
                        while (iterator.hasNext()) {
                            val r = iterator.next()
                            if (r[o] == valo)
                                modifyData(transactionID, r[s], r[p], r[o], EModifyType.DELETE, idx)
                    }
                } else if (pv) {
                    val iterator = tripleStore[idx.ordinal].iterator()
                        while (iterator.hasNext()) {
                            val r = iterator.next()
                            if (r[p] == valp)
                                modifyData(transactionID, r[s], r[p], r[o], EModifyType.DELETE, idx)
                    }
                } else {
                    val iterator = tripleStore[idx.ordinal].iterator()
                        while (iterator.hasNext()) {
                            val r = iterator.next()
                            if (r[s] == vals)
                                modifyData(transactionID, r[s], r[p], r[o], EModifyType.DELETE, idx)
                        }
                }
            }
            else -> {
                throw Exception("deleteDataVar $tmp")
            }
        }
    }

    inline fun getIterator(transactionID: Long,dictionary: ResultSetDictionary, index: EIndexPattern): POPTripleStoreIteratorBase {
        return TripleStoreIteratorLocal(dictionary, this, index)
    }

    inline fun getIterator(transactionID: Long,dictionary: ResultSetDictionary, s: String, p: String, o: String, index: EIndexPattern): POPTripleStoreIteratorBase {
        val res = TripleStoreIteratorLocal(dictionary, this, index)
        res.setMNameS(s)
        res.setMNameP(p)
        res.setMNameO(o)
        return res
    }
}
