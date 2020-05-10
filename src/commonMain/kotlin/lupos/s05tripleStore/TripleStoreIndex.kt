package lupos.s05tripleStore

import lupos.s00misc.File
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.Query

abstract class TripleStoreIndex {
    abstract fun safeToFile(filename: String)
    abstract fun loadFromFile(filename: String)
    abstract fun getIterator(query: Query, filter: IntArray, projection: List<String>): ColumnIteratorRow
    abstract fun import(dataImport: IntArray, count: Int, order: IntArray)
    abstract fun insert(a: Value, b: Value, c: Value)
    abstract fun remove(a: Value, b: Value, c: Value)
    abstract fun clear()
    abstract fun printContents()
    open fun insertAsBulk(data: IntArray) {
        var i = 0
        while (i < data.size) {
            insert(data[i], data[i + 1], data[i + 2])
            i += 3
        }
    }
    open fun removeAsBulk(data: IntArray) {
        var i = 0
        while (i < data.size) {
            remove(data[i], data[i + 1], data[i + 2])
            i += 3
        }
    }
}
