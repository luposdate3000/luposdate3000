package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
open class ColumnIterator() {
    var next: suspend () -> Value? = ::_next
    var close: () -> Unit = ::_close
    fun _close() {
Coverage.funStart(3496)
        next = ::_next
Coverage.statementStart(3497)
        close = ::_close
Coverage.statementStart(3498)
    }
    suspend fun _next(): Value? = null
}
