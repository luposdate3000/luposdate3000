package lupos.s04logicalOperators

import lupos.s03resultRepresentation.*
import lupos.s04logicalOperators.ResultIterator


open class ResultIterator() {
    var next: suspend () -> ResultRow = ::_next
    var close: () -> Unit = ::_close

    constructor(next: suspend () -> ResultRow) : this() {
        this.next = next
    }

    constructor(next: suspend () -> ResultRow, close: () -> Unit) : this(next) {
        this.close = close
    }

    fun _close() {
        next = ::_next
        close = ::_close
    }

    suspend fun _next(): ResultRow = throw Exception("no more Elements")
    inline suspend fun forEach(crossinline action: suspend (ResultRow) -> Unit) {
        try {
            while (true) {
                action(next.invoke())
            }
        } catch (e: Throwable) {
            close.invoke()
        }
    }
}
