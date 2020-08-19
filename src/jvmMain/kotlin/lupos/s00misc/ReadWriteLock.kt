package lupos.s00misc

import java.util.concurrent.atomic.AtomicInteger
import kotlin.jvm.JvmField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import lupos.s00misc.Coverage

var debuguuidtmp123 = AtomicInteger()

class ReadWriteLock {
    @JvmField
    val uuid = debuguuidtmp123.incrementAndGet()

    @JvmField
    val lockA = Mutex() //required to assign a new read or write lock

    @JvmField
    val lockB = Mutex() //accuired as long as there is a reader active - used to signal a possible writer, that all readers are gone

    @JvmField
    var counter = AtomicInteger() //number of active readers
    inline suspend fun downgradeToReadLock() {
        counter.set(1)
        lockB.lock()
        lockA.unlock()
        SanityCheck.println({ "LOCK $uuid writeToReadLock" })
    }

    inline suspend fun readLock() {
        SanityCheck.println({ "LOCK $uuid get read lock start" })
        lockA.lock()
        var tmp = counter.incrementAndGet()
        if (tmp == 1) {
            lockB.lock()
        }
        lockA.unlock()
        SanityCheck.println({ "LOCK $uuid get read lock" })
    }

    inline fun readUnlock() {
        SanityCheck.println({ "LOCK $uuid releasing read lock" })
        var tmp = counter.decrementAndGet()
        if (tmp == 0) {
            lockB.unlock()
        }
    }

    inline suspend fun writeLock() {
        SanityCheck.println({ "LOCK $uuid get write lock start" })
        lockA.lock()
        lockB.lock() //effectively wait for_ the signal of the last read-unlock
        lockB.unlock()
        //assume that counter is 0, because otherwise lockB can not be accuired
        SanityCheck.println({ "LOCK $uuid get write lock" })
    }

    inline fun writeUnlock() {
        SanityCheck.println({ "LOCK $uuid releasing write lock" })
        lockA.unlock()
        //assume that counter is 0, because that is the precondition for_ a writer to start
    }

    inline suspend fun <T> withReadLock(crossinline action: suspend () -> T): T {
        readLock()
        try {
            return action()
        } finally {
            readUnlock()
        }
        /*Coverage Unreachable*/
    }

    inline suspend fun <T> withWriteLock(crossinline action: suspend () -> T): T {
        writeLock()
        try {
            return action()
        } finally {
            writeUnlock()
        }
        /*Coverage Unreachable*/
    }

    inline fun <T> withWriteLockSuspend(crossinline action: suspend CoroutineScope.() -> T): T {
        var res: T? = null
        runBlocking {
            withWriteLock {
                res = action()
            }
        }
        return res!!
    }
}
