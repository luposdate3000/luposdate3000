package lupos.s05tripleStore

import lupos.s00misc.Coverage
import lupos.s00misc.File
import lupos.s00misc.HistogramNotImplementedException
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.Query

abstract class TripleStoreIndex {
    abstract fun safeToFile(filename: String)
    abstract fun loadFromFile(filename: String)
    abstract fun getIterator(query: Query, filter: IntArray, projection: List<String>): IteratorBundle
    abstract fun import(dataImport: IntArray, count: Int, order: IntArray)
    abstract fun insert(a: Value, b: Value, c: Value)
    abstract fun remove(a: Value, b: Value, c: Value)
    abstract fun clear()
    abstract fun printContents()
    abstract fun flush()
    open fun getHistogram(query: Query, filter: IntArray): Pair<Int, Int> = throw HistogramNotImplementedException()
    open fun insertAsBulk(data: IntArray, order: IntArray) {
        var i = 0
        while (i < data.size) {
            insert(data[i + order[0]], data[i + order[1]], data[i + order[2]])
            i += 3
        }
    }

    open fun removeAsBulk(data: IntArray, order: IntArray) {
        var i = 0
        while (i < data.size) {
            remove(data[i + order[0]], data[i + order[1]], data[i + order[2]])
            i += 3
        }
    }
}
