package lupos.s05tripleStore
import lupos.s00misc.File
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.Query




interface TripleStoreIndex {
    fun safeToFile(filename: String)
    fun loadFromFile(filename: String)
    fun getIterator(query: Query, filter: IntArray, projection: List<String>): ColumnIteratorRow
    fun import(dataImport: IntArray, count: Int, order: IntArray)
    fun insert(a: Value, b: Value, c: Value)
    fun remove(a: Value, b: Value, c: Value)
    fun clear()
    fun printContents()
}
