package lupos.s05tripleStore

import lupos.s00misc.File
import lupos.s00misc.HistogramNotImplementedException
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.Query

abstract class TripleStoreIndex {
    suspend abstract fun safeToFile(filename: String)
    suspend abstract fun loadFromFile(filename: String)
    suspend abstract fun getIterator(query: Query, params: TripleStoreFeatureParams): IteratorBundle
    suspend abstract fun import(dataImport: IntArray, count: Int, order: IntArray)
    abstract fun insert(a: Value, b: Value, c: Value)
    abstract fun remove(a: Value, b: Value, c: Value)
    suspend abstract fun clear()
    suspend abstract fun printContents()
    suspend abstract fun flush()
    suspend open fun getHistogram(query: Query, params: TripleStoreFeatureParams): Pair<Int, Int> = throw HistogramNotImplementedException("TripleStoreIndex")
    suspend open fun insertAsBulk(data: IntArray, order: IntArray) {
        var i = 0
        while (i < data.size) {
            insert(data[i + order[0]], data[i + order[1]], data[i + order[2]])
            i += 3
        }
    }

    suspend open fun removeAsBulk(data: IntArray, order: IntArray) {
        var i = 0
        while (i < data.size) {
            remove(data[i + order[0]], data[i + order[1]], data[i + order[2]])
            i += 3
        }
    }
}
