package lupos.s04logicalOperators.iterator
import lupos.s03resultRepresentation.Value


open class ColumnIterator() {
    var next: suspend () -> Value? = ::_next
    var close: () -> Unit = ::_close
    fun _close() {
        next = ::_next
        close = ::_close
    }

    suspend fun _next(): Value? = null
}
