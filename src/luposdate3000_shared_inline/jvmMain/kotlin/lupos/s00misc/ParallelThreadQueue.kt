package lupos.s00misc

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock
import java.util.concurrent.locks.ReentrantReadWriteLock
import java.util.concurrent.Semaphore
import kotlin.jvm.JvmField

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
