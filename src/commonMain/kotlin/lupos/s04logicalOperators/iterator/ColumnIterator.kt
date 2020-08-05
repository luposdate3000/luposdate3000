package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value

open class ColumnIterator() {
@JvmField
    var next: suspend () -> Value? = ::_next
@JvmField
    var close: () -> Unit = ::_close
    fun _close() {
        next = ::_next
        close = ::_close
    }

    suspend fun _next(): Value? = null
}
