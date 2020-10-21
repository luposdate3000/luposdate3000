package lupos.s00misc

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock
import java.util.concurrent.locks.ReentrantReadWriteLock
import java.util.concurrent.Semaphore
import kotlin.jvm.JvmField

internal actual object ParallelThread {
    actual inline fun <T> runBlocking(crossinline action: () -> T): T {
        return action()
    }

    actual inline fun launch(crossinline action: () -> Unit): ParallelThreadJob {
        var res = ParallelThreadJob {
            action()
        }
        res.start()
        return res
    }

    actual inline fun delay(milliseconds: Long) {
        Thread.sleep(milliseconds)
    }

    actual inline fun createCondition(lock: MyLock): ParallelThreadCondition {
        return ParallelThreadCondition(lock)
    }

    actual inline fun <T> createQueue(terminationValue: T): ParallelThreadQueue<T> {
        return ParallelThreadQueue<T>(terminationValue)
    }
}

internal actual class ParallelThreadJob(action: () -> Unit) : Thread({ action() }) {
}

internal actual class ParallelThreadCondition {
    @JvmField
    val lock: MyLock

    @JvmField
    val lock2 = ReentrantLock()

    @JvmField
    val cond = lock2.newCondition()

    actual constructor(lock: MyLock) {
        this.lock = lock
    }

    actual inline fun waitCondition(crossinline condition: () -> Boolean) {
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

    actual inline fun signal() {
        lock2.lock()
        cond.signal()
        lock2.unlock()
    }
}

internal actual class ParallelThreadQueue<T> {
    @JvmField
    val queue = ArrayBlockingQueue<T>(4096)

    @JvmField
    val terminationValue: T

    actual constructor(terminationValue: T) {
        this.terminationValue = terminationValue
    }

    actual inline fun send(value: T) {
        queue.put(value)
    }

    actual inline fun close() {
        queue.put(terminationValue)
    }

    actual inline fun receive(): T {
        return queue.take()
    }
}
