package lupos.s00misc

import kotlin.jvm.JvmField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex

class Lock {
    @JvmField
    val mutex = Mutex()

    suspend inline fun <T> withWriteLockSuspend(crossinline action: suspend () -> T): T {
        try {
            mutex.lock()
            return action()
        } finally {
            mutex.unlock()
        }
    }

    inline fun <T> withWriteLock(crossinline action: suspend CoroutineScope.() -> T): T {
        var res: T? = null
        CoroutinesHelper.runBlock {
            withWriteLockSuspend {
                res = action()
            }
        }
        return res!!
    }
}
