package lupos.s04logicalOperators.iterator


open class RowIterator() {
    var columns = arrayOf<String>()
    var buf = IntArray(0)
    var next: suspend () -> Int = ::_next

    /*next returns start index in buf, or -1 otherwise*/
    var close: () -> Unit = ::_close
    fun _close() {
        next = ::_next
        close = ::_close
    }

    suspend fun _next() = -1
}
