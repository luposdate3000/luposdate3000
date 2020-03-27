package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

object Trace {
    inline fun <T> trace(name: () -> String, action: () -> T): T {
        return action()
    }

    inline suspend fun <T> traceSuspend(name: () -> String, action: suspend () -> T): T {
        return action()
    }

    fun start(obj: Any) {
    }

    fun start(name: String) {
    }

    fun stop(obj: Any) {
    }

    fun stop(name: String) {
    }

    fun print() {
    }
}
