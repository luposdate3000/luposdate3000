package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s04logicalOperators.Query

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
