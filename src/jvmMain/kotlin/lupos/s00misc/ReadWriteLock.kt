package lupos.s00misc
import lupos.s00misc.EOperatorID

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex


class ReadWriteLock() {
    val allowNewReads = Mutex()
    val allowNewWrites = Mutex()
    var readers = 0L

    inline suspend fun <T> withReadLockSuspend(crossinline action: suspend () -> T): T {
        try {
            allowNewReads.lock()
            if (++readers == 1L)
                allowNewWrites.lock()
        } finally {
            allowNewReads.unlock()
        }
        try {
            return action()
        } finally {
            try {
                allowNewReads.lock()
                if (--readers == 0L)
                    allowNewWrites.unlock()
            } finally {
                allowNewReads.unlock()
            }
        }
    }

    inline suspend fun <T> withWriteLockSuspend(crossinline action: suspend () -> T): T {
        try {
            allowNewWrites.lock()
            return action()
        } finally {
            allowNewWrites.unlock()
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
