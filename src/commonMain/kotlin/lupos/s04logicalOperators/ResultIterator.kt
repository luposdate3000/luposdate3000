package lupos.s04logicalOperators

import lupos.s03resultRepresentation.*

import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.ResultIterator

open class ResultIterator() {
    var next: suspend () -> ResultChunk = ::_next
    var close: () -> Unit = ::_close

    constructor(next: suspend () -> ResultChunk) : this() {
        this.next = next
    }

    constructor(next: suspend () -> ResultChunk, close: () -> Unit) : this(next) {
        this.close = close
    }

    fun _close() {
        next = ::_next
        close = ::_close
    }

    suspend fun _next(): ResultChunk = throw Exception("no more Elements")
    inline suspend fun forEach(crossinline action: suspend (ResultChunk) -> Unit) {
        try {
            while (true) {
                action(next.invoke())
            }
        } catch (e: Throwable) {
            if (e.message != "Channel was closed" && e.message != "no more Elements")
                e.printStackTrace()
            close.invoke()
        }
    }
}
