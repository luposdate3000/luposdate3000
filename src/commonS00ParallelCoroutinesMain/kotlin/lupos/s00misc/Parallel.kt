package lupos.s00misc
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlin.coroutines.resume
import kotlinx.coroutines.channels.Channel
import kotlin.jvm.JvmField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.Job
import kotlinx.coroutines.sync.Mutex
typealias ParallelJob = Job

object Parallel {
    inline fun <T> runBlocking(crossinline action: suspend () -> T): T {
        return kotlinx.coroutines.runBlocking {
            action()
        }
    }

    inline fun launch(crossinline action: suspend () -> Unit): ParallelJob {
        return GlobalScope.launch(Dispatchers.Default) {
            action()
        }
    }

    inline suspend fun delay(milliseconds: Long) {
        kotlinx.coroutines.delay(milliseconds)
    }
inline fun createMutex()=Mutex()
    inline fun createCondition(lock: Lock) = ParallelCondition(lock)
    inline fun <T> createQueue(terminationValue: T) = ParallelQueue<T>()

    class ParallelCondition(@JvmField val lock: Lock) {

        @JvmField
        var cont: Continuation<Unit>? = null

        suspend inline fun waitCondition(crossinline condition: () -> Boolean) {
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

        suspend inline fun signal() {
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

    class ParallelQueue<T>()    {
        @JvmField val queue = Channel<T>(2)

        inline fun close() {
            queue.close()
        }

        suspend inline fun send(value: T) {
            queue.send(value)
        }

        suspend inline fun receive(): T {
            return queue.receive()
        }
    }
}
