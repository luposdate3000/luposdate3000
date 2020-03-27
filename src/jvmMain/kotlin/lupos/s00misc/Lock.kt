package lupos.s00misc

import kotlin.jvm.JvmField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query


class Lock {
    @JvmField
    val mutex = Mutex()

    suspend inline fun <T> withLockSuspend(crossinline action: suspend () -> T): T {
        try {
            mutex.lock()
            return action()
        } finally {
            mutex.unlock()
        }
    }

    inline fun <T> withLock(crossinline action: suspend CoroutineScope.() -> T): T {
        var res: T? = null
        CoroutinesHelper.runBlock {
            withLockSuspend {
                res = action()
            }
        }
        return res!!
    }
}
