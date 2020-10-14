package lupos.s00misc

import java.util.concurrent.atomic.AtomicInteger
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.Parallel

var debuguuidtmp123 = AtomicInteger()

/*class ReadWriteLock {
    @JvmField
    val uuid = debuguuidtmp123.incrementAndGet()

    @JvmField
    val lockA = Parallel.createMutex() //required to assign a new read or write lock

    @JvmField
    val lockB = Parallel.createMutex() //accuired as long as there is a reader active - used to signal a possible writer, that all readers are gone

    @JvmField
    var counter = 0
    inline suspend fun downgradeToReadLock() {
        println("ReadWriteLock.downgradeToReadLock($uuid)")
        counter=1
        lockB.lock()
        lockA.unlock()
    }

    inline suspend fun readLock() {
        println("ReadWriteLock.readLock($uuid)")
        lockA.lock()
        var tmp = counter++
        if (tmp == 0) {
            lockB.lock()
        }
        lockA.unlock()
    }

    inline fun readUnlock() {
        println("ReadWriteLock.readUnlock($uuid)")
lockA.lock()
        var tmp = counter--
        if (tmp == 1) {
            lockB.unlock()
        }
lockA.unlock()
    }

    inline suspend fun writeLock() {
        println("ReadWriteLock.writeLock($uuid)")
        lockA.lock()
        lockB.lock() //effectively wait for_ the signal of the last read-unlock
        lockB.unlock()
        //assume that counter is 0, because otherwise lockB can not be accuired
    }

    inline suspend fun tryWriteLock(): Boolean {
        println("ReadWriteLock.tryWriteLock($uuid)")
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
        println("ReadWriteLock.writeUnlock($uuid)")
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

    inline fun <T> withWriteLockSuspend(crossinline action: suspend () -> T): T {
        var res: T? = null
        Parallel.runBlocking {
            withWriteLock {
                res = action()
            }
        }
        return res!!
    }
}
*/
