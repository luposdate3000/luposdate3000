package lupos.s05tripleStore

import lupos.s00misc.HistogramNotImplementedException
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

abstract class TripleStoreIndex {
    suspend abstract fun safeToFile(filename: String)
    suspend abstract fun loadFromFile(filename: String)
    suspend abstract fun getIterator(query: IQuery, params: TripleStoreFeatureParams): IteratorBundle
    suspend abstract fun import(dataImport: IntArray, count: Int, order: IntArray)
    abstract fun insert(a: Int, b: Int, c: Int)
    abstract fun remove(a: Int, b: Int, c: Int)
    suspend abstract fun clear()
    suspend abstract fun printContents()
    suspend abstract fun flush()
    suspend open fun getHistogram(query: IQuery, params: TripleStoreFeatureParams): Pair<Int, Int> = throw HistogramNotImplementedException("TripleStoreIndex")
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
