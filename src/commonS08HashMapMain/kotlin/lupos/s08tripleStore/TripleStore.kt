package lupos.s08tripleStore

import lupos.s00misc.classNameToString
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.ResultSetIterator
import lupos.s06resultRepresentation.Variable
import lupos.s07physicalOperators.POPBase
import lupos.s08tripleStore.IndexPattern
import lupos.s08tripleStore.POPTripleStoreIteratorBase


class TripleStoreIterator : POPTripleStoreIteratorBase {
    private val resultSetNew = ResultSet()
    private val resultSetOld: ResultSet
    private var mapIterator: MutableIterator<MutableMap.MutableEntry<ResultRow, MutableList<ResultRow>>>
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

class TripleStore {
    val resultSet = ResultSet()
    val s = resultSet.createVariable("s")
    val p = resultSet.createVariable("p")
    val o = resultSet.createVariable("o")
    val tripleStoreS = mutableMapOf<ResultRow, MutableList<ResultRow>>()
    val tripleStoreP = mutableMapOf<ResultRow, MutableList<ResultRow>>()
    val tripleStoreO = mutableMapOf<ResultRow, MutableList<ResultRow>>()
    val tripleStoreSP = mutableMapOf<ResultRow, MutableList<ResultRow>>()
    val tripleStoreSO = mutableMapOf<ResultRow, MutableList<ResultRow>>()
    val tripleStorePO = mutableMapOf<ResultRow, MutableList<ResultRow>>()
    val tripleStoreSPO = mutableMapOf<ResultRow, MutableList<ResultRow>>()
    val name: String

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
            store: MutableMap<ResultRow, MutableList<ResultRow>>
    ) {
        var list = store[key]
        if (list == null) {
            list = mutableListOf<ResultRow>()
            store[key] = list
        }
        list.add(value)
    }

    fun addData(iterator: ResultSetIterator) {
        val rsOld = iterator.getResultSet()
        val sOld = rsOld.createVariable("s")
        val pOld = rsOld.createVariable("p")
        val oOld = rsOld.createVariable("o")
        while (iterator.hasNext()) {
            var data = iterator.next()
            val vals = resultSet.createValue(rsOld.getValue(data[sOld]))
            val valp = resultSet.createValue(rsOld.getValue(data[pOld]))
            val valo = resultSet.createValue(rsOld.getValue(data[oOld]))

            run {
                val rrk = resultSet.createResultRow()
                val rrv = resultSet.createResultRow()
                rrk[s] = vals
                rrv[p] = valp
                rrv[o] = valo
                addData(rrk, rrv, tripleStoreS)
            }

            run {
                val rrk = resultSet.createResultRow()
                val rrv = resultSet.createResultRow()
                rrv[s] = vals
                rrk[p] = valp
                rrv[o] = valo
                addData(rrk, rrv, tripleStoreP)
            }

            run {
                val rrk = resultSet.createResultRow()
                val rrv = resultSet.createResultRow()
                rrv[s] = vals
                rrv[p] = valp
                rrk[o] = valo
                addData(rrk, rrv, tripleStoreO)
            }

            run {
                val rrk = resultSet.createResultRow()
                val rrv = resultSet.createResultRow()
                rrk[s] = vals
                rrk[p] = valp
                rrv[o] = valo
                addData(rrk, rrv, tripleStoreSP)
            }

            run {
                val rrk = resultSet.createResultRow()
                val rrv = resultSet.createResultRow()
                rrk[s] = vals
                rrv[p] = valp
                rrk[o] = valo
                addData(rrk, rrv, tripleStoreSO)
            }

            run {
                val rrk = resultSet.createResultRow()
                val rrv = resultSet.createResultRow()
                rrv[s] = vals
                rrk[p] = valp
                rrk[o] = valo
                addData(rrk, rrv, tripleStorePO)
            }

            run {
                val rrk = resultSet.createResultRow()
                val rrv = resultSet.createResultRow()
                rrk[s] = vals
                rrk[p] = valp
                rrk[o] = valo
                addData(rrk, rrv, tripleStoreSPO)
            }
        }
    }

    fun getIterator(): POPTripleStoreIteratorBase {
        return TripleStoreIterator(this)
    }

    fun getIterator(index: IndexPattern): POPTripleStoreIteratorBase {
        return TripleStoreIterator(this, index)
    }
}
