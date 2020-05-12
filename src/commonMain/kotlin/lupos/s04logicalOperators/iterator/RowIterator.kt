package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value

open class RowIterator() {
    var _columns = arrayOf<String>()
    var columns: Array<String>
        set(value) {
            require(value.size == value.distinct().size)
            _columns = value
        }
        get() = _columns
    var buf = IntArray(0)
    var next: suspend () -> Int = ::_next /*returns start index in buf, or -1 otherwise*/
    var close: () -> Unit = ::_close
    fun _close() {
        next = ::_next
        close = ::_close
    }

    suspend fun _next() = -1
}
