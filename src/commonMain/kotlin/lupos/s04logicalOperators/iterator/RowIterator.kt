package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage

open class RowIterator() {
@JvmField
    var columns = arrayOf<String>()
@JvmField
    var buf = IntArray(0)
@JvmField
    var next: () -> Int = ::_next

    /*next returns start index in buf, or -1 otherwise*/
@JvmField
    var close: () -> Unit = ::_close

    fun _close() {
        next = ::_next
        close = ::_close
    }

    fun _next() = -1
}
