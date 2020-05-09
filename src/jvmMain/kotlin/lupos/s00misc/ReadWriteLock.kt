package lupos.s00misc
import kotlin.jvm.JvmField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex


class ReadWriteLock {
    @JvmField
    val allowNewReads = Mutex()
    @JvmField
    val allowNewWrites = Mutex()
    @JvmField
    var readers = 0L

    suspend fun readLock() {
        try {
            allowNewReads.lock()
            if (++readers == 1L) {
                allowNewWrites.lock()
            }
        } finally {
            allowNewReads.unlock()
        }
    }

    suspend fun readUnlock() {
        try {
            allowNewReads.lock()
            if (--readers == 0L) {
                allowNewWrites.unlock()
            }
        } finally {
            allowNewReads.unlock()
        }
    }

    suspend fun writeLock() {
        allowNewWrites.lock()
    }

    suspend fun writeUnlock() {
        allowNewWrites.unlock()
    }

    suspend inline fun <T> withReadLockSuspend(crossinline action: suspend () -> T): T {
        readLock()
        try {
            return action()
        } finally {
            readUnlock()
        }
    }

    suspend inline fun <T> withWriteLockSuspend(crossinline action: suspend () -> T): T {
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
