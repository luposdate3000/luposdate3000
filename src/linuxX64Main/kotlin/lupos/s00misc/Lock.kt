package lupos.s00misc
import kotlinx.cinterop.cValue
import kotlinx.coroutines.CoroutineScope
import lupos.s00misc.Coverage
import platform.posix.pthread_mutex_init
import platform.posix.pthread_mutex_lock
import platform.posix.pthread_mutex_t
import platform.posix.pthread_mutex_unlock
class Lock {
    val mutex = cValue<pthread_mutex_t>()
    constructor() {
        pthread_mutex_init(mutex, null)
    }
    inline suspend fun <T> withWriteLockSuspend(crossinline action: suspend () -> T): T {
Coverage.funStart(17174)
        try {
Coverage.statementStart(17175)
            pthread_mutex_lock(mutex)
Coverage.statementStart(17176)
            return action()
        } finally {
Coverage.statementStart(17177)
            pthread_mutex_unlock(mutex)
Coverage.statementStart(17178)
        }
Coverage.statementStart(17179)
    }
    inline fun <T> withWriteLock(crossinline action: suspend CoroutineScope.() -> T): T {
Coverage.funStart(17180)
        var res: T? = null
Coverage.statementStart(17181)
        CoroutinesHelper.runBlock {
Coverage.statementStart(17182)
            withWriteLockSuspend {
Coverage.statementStart(17183)
                res = action()
Coverage.statementStart(17184)
            }
Coverage.statementStart(17185)
        }
Coverage.statementStart(17186)
        return res!!
    }
}
//pthread_mutex_destroy
