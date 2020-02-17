package lupos.s00misc

import kotlin.native.concurrent.AtomicLong
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import kotlinx.cinterop.cValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex
import platform.posix.pthread_mutex_init
import platform.posix.pthread_mutex_lock
import platform.posix.pthread_mutex_t
import platform.posix.pthread_mutex_unlock


class ReadWriteLock {
    val allowNewReads = cValue<pthread_mutex_t>()
    val allowNewWrites = cValue<pthread_mutex_t>()
    var readers = AtomicLong(0L)

    constructor() {
        pthread_mutex_init(allowNewReads, null)
        pthread_mutex_init(allowNewWrites, null)
    }

    inline suspend fun <T> withReadLockSuspend(crossinline action: suspend () -> T): T {
        try {
            pthread_mutex_lock(allowNewReads)
            if (readers.addAndGet(1) == 1L)
                pthread_mutex_lock(allowNewWrites)
        } finally {
            pthread_mutex_unlock(allowNewReads)
        }
        try {
            return action()
        } finally {
            if (readers.addAndGet(-1) == 0L)
                pthread_mutex_unlock(allowNewWrites)
        }
    }

    inline suspend fun <T> withWriteLockSuspend(crossinline action: suspend () -> T): T {
        try {
            pthread_mutex_lock(allowNewReads)
            try {
                pthread_mutex_lock(allowNewWrites)
                return action()
            } finally {
                pthread_mutex_unlock(allowNewWrites)
            }
        } finally {
            pthread_mutex_unlock(allowNewReads)
        }
    }

    inline fun <T> withReadLock(crossinline action: suspend CoroutineScope.() -> T): T {
        var res: T? = null
        CoroutinesHelper.runBlock {
            withReadLockSuspend {
                res = action()
            }
        }
        return res!!
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
//pthread_mutex_destroy
