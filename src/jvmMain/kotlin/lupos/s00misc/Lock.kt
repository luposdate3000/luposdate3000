package lupos.s00misc

import kotlin.jvm.JvmField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import lupos.s00misc.Coverage

class Lock {
    @JvmField
    val mutex = Mutex()
    suspend fun lock() {
        mutex.lock()
    }

    suspend fun unlock() {
        mutex.unlock()
    }

    suspend /*inline*/  fun <T> withWriteLockSuspend(/*crossinline*/  action: suspend () -> T): T {
        try {
            lock()
            return action()
        } finally {
            unlock()
        }
/*Coverage Unreachable*/
    }

    /*inline*/  fun <T> withWriteLock(/*crossinline*/  action: suspend CoroutineScope.() -> T): T {
        var res: T? = null
        runBlocking {
            withWriteLockSuspend {
                res = action()
            }
        }
        return res!!
    }
}
