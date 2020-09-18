package lupos.s00misc
import lupos.s03resultRepresentation.ResultSetDictionary
import kotlin.jvm.JvmField
import java.util.concurrent.locks.ReentrantLock
import java.util.concurrent.ArrayBlockingQueue

typealias ParallelJob = Thread

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
inline fun createMutex()=ReentrantLock()
    inline fun createCondition(lock: Lock) = ParallelCondition(lock)
    inline fun <T>createQueue(terminationValue:T) = ParallelQueue<T>(terminationValue)

    class ParallelCondition(@JvmField val lock: Lock) {
        @JvmField
        val lock2 = ReentrantLock()

        @JvmField
        val cond = lock2.newCondition()

        inline fun waitCondition(crossinline condition: () -> Boolean) {
            lock.lock()//this lock is required to execute the "condition()"
            if (condition()) {
                lock.unlock()
                lock2.lock()
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

    class ParallelQueue<T>    (@JvmField val terminationValue:T){
        @JvmField val queue = ArrayBlockingQueue<T>(4096)

        inline fun send(value: T) {
            queue.put(value)
        }
inline fun close(){
queue.put(terminationValue)
}

        inline fun receive(): T {
            return queue.take()
        }
    }
}
