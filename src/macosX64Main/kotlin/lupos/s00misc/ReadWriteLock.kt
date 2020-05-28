package lupos.s00misc
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import kotlinx.cinterop.cValue
import kotlinx.coroutines.CoroutineScope
import lupos.s00misc.Coverage
import platform.posix.pthread_mutex_init
import platform.posix.pthread_mutex_lock
import platform.posix.pthread_mutex_t
import platform.posix.pthread_mutex_unlock
class ReadWriteLock {
    val allowNewReads = cValue<pthread_mutex_t>()
    val allowNewWrites = cValue<pthread_mutex_t>()
    var readers = AtomicReference(0L.freeze())
    constructor() {
        pthread_mutex_init(allowNewReads, null)
        pthread_mutex_init(allowNewWrites, null)
    }
    inline suspend fun <T> withReadLockSuspend(crossinline action: suspend () -> T): T {
Coverage.funStart(17441)
        try {
Coverage.statementStart(17442)
            pthread_mutex_lock(allowNewReads)
Coverage.statementStart(17443)
            readers.value = (readers.value + 1).freeze()
Coverage.statementStart(17444)
            if (readers.value == 1L) {
Coverage.ifStart(17445)
                pthread_mutex_lock(allowNewWrites)
Coverage.statementStart(17446)
            }
Coverage.statementStart(17447)
        } finally {
Coverage.statementStart(17448)
            pthread_mutex_unlock(allowNewReads)
Coverage.statementStart(17449)
        }
Coverage.statementStart(17450)
        try {
Coverage.statementStart(17451)
            return action()
        } finally {
Coverage.statementStart(17452)
            try {
Coverage.statementStart(17453)
                pthread_mutex_lock(allowNewReads)
Coverage.statementStart(17454)
                readers.value = (readers.value - 1).freeze()
Coverage.statementStart(17455)
                if (readers.value == 0L) {
Coverage.ifStart(17456)
                    pthread_mutex_unlock(allowNewWrites)
Coverage.statementStart(17457)
                }
Coverage.statementStart(17458)
            } finally {
Coverage.statementStart(17459)
                pthread_mutex_unlock(allowNewReads)
Coverage.statementStart(17460)
            }
Coverage.statementStart(17461)
        }
Coverage.statementStart(17462)
    }
    inline suspend fun <T> withWriteLockSuspend(crossinline action: suspend () -> T): T {
Coverage.funStart(17463)
        try {
Coverage.statementStart(17464)
            pthread_mutex_lock(allowNewWrites)
Coverage.statementStart(17465)
            return action()
        } finally {
Coverage.statementStart(17466)
            pthread_mutex_unlock(allowNewWrites)
Coverage.statementStart(17467)
        }
Coverage.statementStart(17468)
    }
    inline fun <T> withReadLock(crossinline action: suspend CoroutineScope.() -> T): T {
Coverage.funStart(17469)
        var res: T? = null
Coverage.statementStart(17470)
        CoroutinesHelper.runBlock {
Coverage.statementStart(17471)
            withReadLockSuspend {
Coverage.statementStart(17472)
                res = action()
Coverage.statementStart(17473)
            }
Coverage.statementStart(17474)
        }
Coverage.statementStart(17475)
        return res!!
    }
    inline fun <T> withWriteLock(crossinline action: suspend CoroutineScope.() -> T): T {
Coverage.funStart(17476)
        var res: T? = null
Coverage.statementStart(17477)
        CoroutinesHelper.runBlock {
Coverage.statementStart(17478)
            withWriteLockSuspend {
Coverage.statementStart(17479)
                res = action()
Coverage.statementStart(17480)
            }
Coverage.statementStart(17481)
        }
Coverage.statementStart(17482)
        return res!!
    }
}
//pthread_mutex_destroy
