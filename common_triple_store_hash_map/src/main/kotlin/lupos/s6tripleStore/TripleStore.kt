package lupos.s6tripleStore

import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.ResultSetIterator


class TripleStoreIterator : ResultSetIterator {
    private val resultSet = ResultSet()
    private var mapIterator: MutableIterator<MutableMap.MutableEntry<Int, MutableList<Array<String>>>>
    private var listIterator: Iterator<Array<String>>?
    private var s = resultSet.createVariable("s")
    private var p = resultSet.createVariable("p")
    private var o = resultSet.createVariable("o")
    private val store: TripleStore

    constructor(store: TripleStore) {
        this.store = store
        mapIterator = store.tripleStoreS.iterator()
        listIterator = null
    }

    override fun next(): ResultRow {
        val value = listIterator!!.next()
        val result = resultSet.createResultRow()
        result[s] = resultSet.createValue(value[0])
        result[p] = resultSet.createValue(value[1])
        result[o] = resultSet.createValue(value[2])
        return result
    }

    override fun hasNext(): Boolean {
        while (listIterator == null || !listIterator!!.hasNext()) {
            if (mapIterator.hasNext()) {
                listIterator = mapIterator.next().value.iterator()
            } else {
                break
            }
        }
        return listIterator != null && listIterator!!.hasNext()
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }
}

actual class TripleStore {

    val tripleStoreS = mutableMapOf<Int, MutableList<Array<String>>>()
    val tripleStoreP = mutableMapOf<Int, MutableList<Array<String>>>()
    val tripleStoreO = mutableMapOf<Int, MutableList<Array<String>>>()
    val tripleStoreSP = mutableMapOf<Int, MutableList<Array<String>>>()
    val tripleStoreSO = mutableMapOf<Int, MutableList<Array<String>>>()
    val tripleStoreOP = mutableMapOf<Int, MutableList<Array<String>>>()
    val tripleStoreSOP = mutableMapOf<Int, MutableList<Array<String>>>()

    actual constructor()

    fun hashValue(value: String): Int {
        return value.hashCode()
    }

    fun addData(hash: Int, values: Array<String>, store: MutableMap<Int, MutableList<Array<String>>>) {
        var list = store[hash]
        if (list == null) {
            list = mutableListOf<Array<String>>()
            store[hash] = list
        }
        list.add(values)
    }

    actual fun addData(iterator: ResultSetIterator) {
        val resultSet = iterator.getResultSet()
        val s = resultSet.createVariable("s")
        val p = resultSet.createVariable("p")
        val o = resultSet.createVariable("o")
        while (iterator.hasNext()) {
            var data = iterator.next()
            var values = arrayOf(resultSet.getValue(data[s]), resultSet.getValue(data[p]), resultSet.getValue(data[o]))
            addData(hashValue(values[0]), values, tripleStoreS)
            addData(hashValue(values[1]), values, tripleStoreP)
            addData(hashValue(values[2]), values, tripleStoreO)
            addData(hashValue(values[0]) + hashValue(values[1]), values, tripleStoreSO)
            addData(hashValue(values[0]) + hashValue(values[2]), values, tripleStoreSP)
            addData(hashValue(values[1]) + hashValue(values[2]), values, tripleStoreOP)
            addData(hashValue(values[0]) + hashValue(values[1]) + hashValue(values[2]), values, tripleStoreSOP)
        }
    }

    actual fun getIterator(): ResultSetIterator {
        return TripleStoreIterator(this)
    }
}