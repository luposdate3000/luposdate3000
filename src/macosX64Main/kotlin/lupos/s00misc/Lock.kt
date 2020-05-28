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
Coverage.funStart(17428)
        try {
Coverage.statementStart(17429)
            pthread_mutex_lock(mutex)
Coverage.statementStart(17430)
            return action()
        } finally {
Coverage.statementStart(17431)
            pthread_mutex_unlock(mutex)
Coverage.statementStart(17432)
        }
Coverage.statementStart(17433)
    }
    inline fun <T> withWriteLock(crossinline action: suspend CoroutineScope.() -> T): T {
Coverage.funStart(17434)
        var res: T? = null
Coverage.statementStart(17435)
        CoroutinesHelper.runBlock {
Coverage.statementStart(17436)
            withWriteLockSuspend {
Coverage.statementStart(17437)
                res = action()
Coverage.statementStart(17438)
            }
Coverage.statementStart(17439)
        }
Coverage.statementStart(17440)
        return res!!
    }
}
//pthread_mutex_destroy
