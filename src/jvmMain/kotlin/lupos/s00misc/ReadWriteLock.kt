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
    }

    inline suspend fun readLock() {
        lockA.lock()
        var tmp = counter.incrementAndGet()
        if (tmp == 1) {
            lockB.lock()
        }
        lockA.unlock()
    }

    inline fun readUnlock() {
        var tmp = counter.decrementAndGet()
        if (tmp == 0) {
            lockB.unlock()
        }
    }

    inline suspend fun writeLock() {
        lockA.lock()
        lockB.lock() //effectively wait for_ the signal of the last read-unlock
        lockB.unlock()
        //assume that counter is 0, because otherwise lockB can not be accuired
    }

    inline suspend fun tryWriteLock(): Boolean {
        if (lockA.tryLock()) {
            if (lockB.tryLock()) { //effectively wait for_ the signal of the last read-unlock
                lockB.unlock()
                //assume that counter is 0, because otherwise lockB can not be accuired
                return true
            }
            lockA.unlock()
        }
        return false
    }

    inline fun writeUnlock() {
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
