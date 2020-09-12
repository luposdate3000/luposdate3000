package lupos.s00misc

import kotlin.jvm.JvmField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import lupos.s00misc.Coverage

class Lock {
    @JvmField
    val uuid = debuguuidtmp123.incrementAndGet()

    @JvmField
    val mutex = Mutex()
    inline suspend fun lock() {
println("Lock.lock($uuid)")
        mutex.lock()
    }

    inline fun unlock() {
println("Lock.unlock($uuid)")
        mutex.unlock()
    }

    suspend /*inline*/  fun <T> withWriteLock(/*crossinline*/  action: suspend () -> T): T {
        try {
            lock()
            return action()
        } finally {
            unlock()
        }
/*Coverage Unreachable*/
    }

    /*inline*/  fun <T> withWriteLockSuspend(/*crossinline*/  action: suspend CoroutineScope.() -> T): T {
        var res: T? = null
        runBlocking {
            withWriteLock {
                res = action()
            }
        }
        return res!!
    }
}
