package lupos.s6tripleStore

import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.ResultSetIterator
import lupos.s4resultRepresentation.Variable

class TripleStoreIterator : ResultSetIterator {
    private val resultSetNew = ResultSet()
    private val resultSetOld: ResultSet
    private var mapIterator:
            MutableIterator<MutableMap.MutableEntry<ResultRow, MutableList<ResultRow>>>
    private var listIterator: Iterator<ResultRow>?
    private val sNew = resultSetNew.createVariable("s")
    private val pNew = resultSetNew.createVariable("p")
    private val oNew = resultSetNew.createVariable("o")
    private val sOld: Variable
    private val pOld: Variable
    private val oOld: Variable
    private val store: TripleStore
    private var currentKey: ResultRow?

    constructor(store: TripleStore) {
        this.store = store
        mapIterator = store.tripleStoreSPO.iterator()
        resultSetOld = store.resultSet
        sOld = resultSetOld.createVariable("s")
        pOld = resultSetOld.createVariable("p")
        oOld = resultSetOld.createVariable("o")
        listIterator = null
        currentKey = null
    }

    override fun next(): ResultRow {
        val value = listIterator!!.next()
        val result = resultSetNew.createResultRow()
        result[sNew] = resultSetNew.createValue(resultSetOld.getValue(currentKey!![sOld]))
        result[pNew] = resultSetNew.createValue(resultSetOld.getValue(currentKey!![pOld]))
        result[oNew] = resultSetNew.createValue(resultSetOld.getValue(currentKey!![oOld]))
        return result
    }

    override fun hasNext(): Boolean {
        while (listIterator == null || !listIterator!!.hasNext()) {
            if (mapIterator.hasNext()) {
                val tmp = mapIterator.next()
                currentKey = tmp.key
                listIterator = tmp.value.iterator()
            } else {
                break
            }
        }
        return listIterator != null && listIterator!!.hasNext()
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }
}

actual class TripleStore {
    val resultSet = ResultSet()
    val s = resultSet.createVariable("s")
    val p = resultSet.createVariable("p")
    val o = resultSet.createVariable("o")
    val tripleStoreS = mutableMapOf<ResultRow, MutableList<ResultRow>>()
    val tripleStoreP = mutableMapOf<ResultRow, MutableList<ResultRow>>()
    val tripleStoreO = mutableMapOf<ResultRow, MutableList<ResultRow>>()
    val tripleStoreSP = mutableMapOf<ResultRow, MutableList<ResultRow>>()
    val tripleStoreSO = mutableMapOf<ResultRow, MutableList<ResultRow>>()
    val tripleStoreOP = mutableMapOf<ResultRow, MutableList<ResultRow>>()
    val tripleStoreSPO = mutableMapOf<ResultRow, MutableList<ResultRow>>()

    actual constructor()

    fun addData(
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

    actual fun addData(iterator: ResultSetIterator) {
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
                addData(rrk, rrv, tripleStoreOP)
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

    actual fun getIterator(): ResultSetIterator {
        return TripleStoreIterator(this)
    }
}
