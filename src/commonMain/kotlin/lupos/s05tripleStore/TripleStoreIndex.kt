package lupos.s05tripleStore
import lupos.s00misc.Coverage
import lupos.s00misc.File
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
    open fun getHistogram(query: Query, filter: IntArray): Pair<Int, Int> = throw Exception("not implemented")
    open fun insertAsBulk(data: IntArray, order: IntArray) {
Coverage.funStart(7178)
        var i = 0
Coverage.statementStart(7179)
        while (i < data.size) {
Coverage.whileLoopStart(7180)
            insert(data[i + order[0]], data[i + order[1]], data[i + order[2]])
Coverage.statementStart(7181)
            i += 3
Coverage.statementStart(7182)
        }
Coverage.statementStart(7183)
    }
    open fun removeAsBulk(data: IntArray, order: IntArray) {
Coverage.funStart(7184)
        var i = 0
Coverage.statementStart(7185)
        while (i < data.size) {
Coverage.whileLoopStart(7186)
            remove(data[i + order[0]], data[i + order[1]], data[i + order[2]])
Coverage.statementStart(7187)
            i += 3
Coverage.statementStart(7188)
        }
Coverage.statementStart(7189)
    }
}
