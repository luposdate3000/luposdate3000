package lupos.s04logicalOperators

import lupos.s03resultRepresentation.*
import lupos.s04logicalOperators.ResultIterator


open class ResultIterator() {
    var next: suspend() -> ResultRow = ::_next
    var close: () -> Unit = { this@ResultIterator.next = ::_next }


    constructor(next: suspend() -> ResultRow) : this() {
        this.next = next
    }

    constructor(next: suspend() -> ResultRow, close: () -> Unit) : this(next) {
        this.close = close
    }

suspend    fun _next(): ResultRow = throw Exception("no more Elements")
    inline suspend fun forEach(crossinline action: (ResultRow) -> Unit) {
        try {
            while (true) {
                action(next.invoke())
            }
        } catch (e: Throwable) {
        }
    }
}
