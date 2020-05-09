package lupos.s00misc
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import kotlinx.cinterop.cValue
import kotlinx.coroutines.CoroutineScope
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

    suspend fun readLock() {
        try {
            pthread_mutex_lock(allowNewReads)
            readers.value = (readers.value + 1).freeze()
            if (readers.value == 1L) {
                pthread_mutex_lock(allowNewWrites)
            }
        } finally {
            pthread_mutex_unlock(allowNewReads)
        }
    }

    suspend fun readUnlock() {
        try {
            pthread_mutex_lock(allowNewReads)
            readers.value = (readers.value - 1).freeze()
            if (readers.value == 0L) {
                pthread_mutex_unlock(allowNewWrites)
            }
        } finally {
            pthread_mutex_unlock(allowNewReads)
        }
    }

    suspend fun writeLock() {
        pthread_mutex_lock(allowNewWrites)
    }

    suspend fun writeUnlock() {
        pthread_mutex_unlock(allowNewWrites)
    }

    inline suspend fun <T> withReadLockSuspend(crossinline action: suspend () -> T): T {
        readLock()
        try {
            return action()
        } finally {
            readUnlock()
        }
    }

    inline suspend fun <T> withWriteLockSuspend(crossinline action: suspend () -> T): T {
        writeLock()
        try {
            return action()
        } finally {
            writeUnlock()
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
