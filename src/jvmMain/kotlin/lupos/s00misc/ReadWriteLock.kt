package lupos.s00misc

import java.util.concurrent.atomic.AtomicInteger
import kotlin.jvm.JvmField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex
import lupos.s00misc.Coverage

var debuguuidtmp123 = 0

class ReadWriteLock {
    val uuid = debuguuidtmp123++
    val lockA = Mutex() //required to assign a new read or write lock
    val lockB = Mutex() //accuired as long as there is a reader active - used to signal a possible writer, that all readers are gone
    var counter = AtomicInteger() //number of active readers
    suspend fun downgradeToReadLock() {
        counter.set(1)
        lockB.lock()
        lockA.unlock()
        println("LOCK $uuid writeToReadLock")
    }

    suspend fun readLock() {
        lockA.lock()
        var tmp = counter.incrementAndGet()
        if (tmp == 1) {
            lockB.lock()
        }
        lockA.unlock()
        println("LOCK $uuid get read lock")
    }

    suspend fun readUnlock() {
        println("LOCK $uuid releasing read lock")
        var tmp = counter.decrementAndGet()
        if (tmp == 0) {
            lockB.unlock()
        }
    }

    suspend fun writeLock() {
        lockA.lock()
        lockB.lock() //effectively wait for the signal of the last read-unlock
        lockB.unlock()
        //assume that counter is 0, because otherwise lockB can not be accuired
        println("LOCK $uuid get write lock")
    }

    suspend fun writeUnlock() {
        println("LOCK $uuid releasing write lock")
        lockA.unlock()
        //assume that counter is 0, because that is the precondition for a writer to start
    }

    suspend /*inline*/  fun <T> withReadLockSuspend(/*crossinline*/  action: suspend () -> T): T {
        readLock()
        try {
            return action()
        } finally {
            readUnlock()
        }
        /*Coverage Unreachable*/
    }

    suspend /*inline*/  fun <T> withWriteLockSuspend(/*crossinline*/  action: suspend () -> T): T {
        writeLock()
        try {
            return action()
        } finally {
            writeUnlock()
        }
        /*Coverage Unreachable*/
    }

    /*inline*/  fun <T> withReadLock(/*crossinline*/  action: suspend CoroutineScope.() -> T): T {
        var res: T? = null
        CoroutinesHelper.runBlock {
            withReadLockSuspend {
                res = action()
            }
        }
        return res!!
    }

    /*inline*/  fun <T> withWriteLock(/*crossinline*/  action: suspend CoroutineScope.() -> T): T {
        var res: T? = null
        CoroutinesHelper.runBlock {
            withWriteLockSuspend {
                res = action()
            }
        }
        return res!!
    }
}
