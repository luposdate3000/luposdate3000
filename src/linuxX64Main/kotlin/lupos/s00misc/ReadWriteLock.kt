package lupos.s00misc

import kotlin.native.concurrent.AtomicLong
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex


class ReadWriteLock {
    val allowNewReads = Mutex()
    val allowNewWrites = Mutex()
    var readers = AtomicLong(0L)
    inline suspend fun <T> withReadLock(crossinline action: suspend () -> T): T {
        try {
            allowNewReads.lock()
            if (readers.addAndGet(1) == 1L)
                allowNewWrites.lock()
        } finally {
            allowNewReads.unlock()
        }
        try {
            return action()
        } finally {
            if (readers.addAndGet(-1) == 0L)
                allowNewWrites.unlock()
        }
    }

    inline suspend fun <T> withWriteLock(crossinline action: suspend () -> T): T {
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

}
