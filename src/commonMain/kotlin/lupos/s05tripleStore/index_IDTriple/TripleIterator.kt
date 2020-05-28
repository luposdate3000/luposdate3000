package lupos.s05tripleStore.index_IDTriple
import lupos.s00misc.Coverage
abstract class TripleIterator() {
    var value = IntArray(3)
    abstract fun hasNext(): Boolean
    abstract fun next(component: Int): Int//write the current triple-data into "value" and update offset
    fun nextS(): Int {
Coverage.funStart(6514)
        return next(0)
    }
    fun nextP(): Int {
Coverage.funStart(6515)
        return next(1)
    }
    fun nextO(): Int {
Coverage.funStart(6516)
        return next(2)
    }
    fun next(): IntArray {
Coverage.funStart(6517)
        next(0)
Coverage.statementStart(6518)
        return value
    }
}
