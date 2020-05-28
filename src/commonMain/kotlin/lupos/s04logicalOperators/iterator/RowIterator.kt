package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
open class RowIterator() {
    var columns = arrayOf<String>()
    var buf = IntArray(0)
    var next: suspend () -> Int = ::_next /*returns start index in buf, or -1 otherwise*/
    var close: () -> Unit = ::_close
    fun _close() {
Coverage.funStart(4018)
        next = ::_next
Coverage.statementStart(4019)
        close = ::_close
Coverage.statementStart(4020)
    }
    suspend fun _next() = -1
}
