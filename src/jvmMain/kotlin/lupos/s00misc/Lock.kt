package lupos.s00misc
import lupos.s00misc.EOperatorID

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex


class Lock() {
    val mutex = Mutex()

    inline suspend fun <T> withLockSuspend(crossinline action: suspend () -> T): T {
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
