package lupos.s00misc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlin.coroutines.resume
import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
typealias ParallelJob = Job
object Parallel {
     internal inline fun <T> runBlocking(crossinline action: suspend () -> T): T {
        return kotlinx.coroutines.runBlocking {
            action()
        }
    }
     internal inline fun launch(crossinline action: suspend () -> Unit): ParallelJob {
        return GlobalScope.launch(Dispatchers.Default) {
            action()
        }
    }
     internal suspend inline fun delay(milliseconds: Long) {
        kotlinx.coroutines.delay(milliseconds)
    }
     internal inline fun createMutex() = Mutex()
     internal inline fun createCondition(lock: Lock) = ParallelCondition(lock)
     internal inline fun <T> createQueue(terminationValue: T) = ParallelQueue<T>()
    class ParallelCondition(@JvmField val lock: Lock) {
        @JvmField
        var cont: Continuation<Unit>? = null
         internal suspend inline fun waitCondition(crossinline condition: () -> Boolean) {
            lock.lock()
            if (condition()) {
                suspendCoroutineUninterceptedOrReturn { continuation: Continuation<Unit> ->
                    cont = continuation
                    lock.unlock()
                    COROUTINE_SUSPENDED
                }
            } else {
                lock.unlock()
            }
        }
         internal suspend inline fun signal() {
            val tmp = cont
            if (tmp != null) {
                lock.lock()
                val tmp2 = cont
                cont = null
                lock.unlock()
                if (tmp2 != null) {
                    tmp2.resume(Unit)
                }
            }
        }
    }
    class ParallelQueue<T>() {
        @JvmField
        val queue = Channel<T>(2)
         internal inline fun close() {
            queue.close()
        }
         internal suspend inline fun send(value: T) {
            queue.send(value)
        }
         internal suspend inline fun receive(): T {
            return queue.receive()
        }
    }
}
