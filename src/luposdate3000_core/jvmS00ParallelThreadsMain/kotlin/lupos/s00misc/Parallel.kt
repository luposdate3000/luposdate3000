package lupos.s00misc

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock
import java.util.concurrent.locks.ReentrantReadWriteLock
import java.util.concurrent.Semaphore
import kotlin.jvm.JvmField
import lupos.s03resultRepresentation.ResultSetDictionary

typealias ParallelJob = Thread

class MyMutex {
    @JvmField
    val semaphore = Semaphore(1)
    inline fun lock() {
        semaphore.acquire()
    }

    inline fun unlock() {
        semaphore.release()
    }

    inline fun tryLock(): Boolean {
        return semaphore.tryAcquire()
    }
}

class ReadWriteLock {
    @JvmField
    val uuid = debuguuidtmp123.incrementAndGet()
    @JvmField
    val lock = ReentrantReadWriteLock()
    inline suspend fun downgradeToReadLock() {
        println("ReadWriteLock.downgradeToReadLock($uuid)")
        lock.readLock().lock()
        lock.writeLock().unlock()
    }

    inline suspend fun readLock() {
        println("ReadWriteLock.readLock($uuid)")
        lock.readLock().lock()
    }

    inline fun readUnlock() {
        println("ReadWriteLock.readUnlock($uuid)")
        lock.readLock().unlock()
    }

    inline suspend fun writeLock() {
        println("ReadWriteLock.writeLock($uuid)")
        lock.writeLock().lock()
    }

    inline suspend fun tryWriteLock(): Boolean {
        println("ReadWriteLock.tryWriteLock($uuid)")
        return lock.writeLock().tryLock()
    }

    inline fun writeUnlock() {
        println("ReadWriteLock.writeUnlock($uuid)")
        lock.writeLock().unlock()
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

object Parallel {
    inline fun <T> runBlocking(crossinline action: () -> T): T {
        return action()
    }

    inline fun launch(crossinline action: () -> Unit): ParallelJob {
        var res = Thread {
            action()
        }
        res.start()
        return res
    }

    inline fun delay(milliseconds: Long) {
        Thread.sleep(milliseconds)
    }

    inline fun createMutex() = MyMutex()
    inline fun createCondition(lock: Lock) = ParallelCondition(lock)
    inline fun <T> createQueue(terminationValue: T) = ParallelQueue<T>(terminationValue)
    class ParallelCondition(@JvmField val lock: Lock) {
        @JvmField
        val lock2 = ReentrantLock()

        @JvmField
        val cond = lock2.newCondition()
        inline fun waitCondition(crossinline condition: () -> Boolean) {
            lock.lock()//this lock is required to execute the "condition()"
            if (condition()) {
                lock2.lock()
                lock.unlock()
                cond.await()
                lock2.unlock()
            } else {
                lock.unlock()
            }
        }

        inline fun signal() {
            lock2.lock()
            cond.signal()
            lock2.unlock()
        }
    }

    class ParallelQueue<T>(@JvmField val terminationValue: T) {
        @JvmField
        val queue = ArrayBlockingQueue<T>(4096)
        inline fun send(value: T) {
            queue.put(value)
        }

        inline fun close() {
            queue.put(terminationValue)
        }

        inline fun receive(): T {
            return queue.take()
        }
    }
}
