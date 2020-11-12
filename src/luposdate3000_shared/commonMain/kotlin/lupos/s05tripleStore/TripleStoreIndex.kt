package lupos.s05tripleStore

import lupos.s00misc.HistogramNotImplementedException
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

abstract class TripleStoreIndex {
    abstract suspend fun safeToFile(filename: String)
    abstract suspend fun loadFromFile(filename: String)
    abstract suspend fun getIterator(query: IQuery, params: TripleStoreFeatureParams): IteratorBundle
    abstract suspend fun import(dataImport: IntArray, count: Int, order: IntArray)
    abstract fun insert(a: Int, b: Int, c: Int)
    abstract fun remove(a: Int, b: Int, c: Int)
    abstract suspend fun clear()
    abstract suspend fun printContents()
    abstract suspend fun flush()
    open suspend fun getHistogram(query: IQuery, params: TripleStoreFeatureParams): Pair<Int, Int> = throw HistogramNotImplementedException("TripleStoreIndex")
    open suspend fun insertAsBulk(data: IntArray, order: IntArray) {
        var i = 0
        while (i < data.size) {
            insert(data[i + order[0]], data[i + order[1]], data[i + order[2]])
            i += 3
        }
    }

    open suspend fun removeAsBulk(data: IntArray, order: IntArray) {
        var i = 0
        while (i < data.size) {
            remove(data[i + order[0]], data[i + order[1]], data[i + order[2]])
            i += 3
        }
    }
}
