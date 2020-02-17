package lupos.s00misc

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex


class ReadWriteLock() {
    val allowNewReads = Mutex()
    val allowNewWrites = Mutex()
    var readers = 0L

    inline fun addAndGet(value: Long): Long {
        var res = 0L
        synchronized(readers) {
            readers += value
            res = readers
        }
        return res
    }

    inline suspend fun <T> withReadLockSuspend(crossinline action: suspend () -> T): T {
        try {
            allowNewReads.lock()
            if (addAndGet(1) == 1L)
                allowNewWrites.lock()
        } finally {
            allowNewReads.unlock()
        }
        try {
            return action()
        } finally {
            if (addAndGet(-1) == 0L)
                allowNewWrites.unlock()
        }
    }

    inline suspend fun <T> withWriteLockSuspend(crossinline action: suspend () -> T): T {
        try {
            allowNewReads.lock()
            try {
                allowNewWrites.lock()
                return action()
            } finally {
                allowNewWrites.unlock()
            }
        } finally {
            allowNewReads.unlock()
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
