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
