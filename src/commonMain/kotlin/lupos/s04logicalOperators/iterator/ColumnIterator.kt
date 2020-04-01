package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*

open class ColumnIterator() {
    var next: suspend () -> Value? = ::_next
    var close: () -> Unit = ::_close
    var onNoMoreElements: suspend () -> Unit = ::_onNoMoreElements
    fun _close() {
        next = ::_next
        close = ::_close
        onNoMoreElements = ::_onNoMoreElements
    }

    suspend fun _onNoMoreElements() {
        close()
    }

    suspend fun _next(): Value? = null
}
