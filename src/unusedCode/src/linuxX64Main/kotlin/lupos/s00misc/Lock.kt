package lupos.s00misc
import kotlinx.cinterop.cValue
import platform.posix.pthread_mutex_init
import platform.posix.pthread_mutex_lock
import platform.posix.pthread_mutex_t
import platform.posix.pthread_mutex_unlock
import kotlin.jvm.JvmName
class Lock {
    val mutex = cValue<pthread_mutex_t>()
    constructor() {
        pthread_mutex_init(mutex, null)
    }
     internal suspend inline fun <T> withWriteLock(crossinline action: suspend () -> T): T {
        try {
            pthread_mutex_lock(mutex)
            return action()
        } finally {
            pthread_mutex_unlock(mutex)
        }
    }
     internal inline fun <T> withWriteLockSuspend(crossinline action: suspend () -> T): T {
        var res: T? = null
        Parallel.runBlocking {
            withWriteLock {
                res = action()
            }
        }
        return res!!
    }
}
// pthread_mutex_destroy
