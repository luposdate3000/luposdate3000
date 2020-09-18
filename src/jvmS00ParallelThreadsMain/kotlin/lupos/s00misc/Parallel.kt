package lupos.s00misc

import kotlin.jvm.JvmField
typealias ParallelMutex = java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock
object Parallel {
    inline fun <T> runBlocking(crossinline action:  () -> T): T {
return            action()
    }

    inline fun launch(crossinline action:  () -> Unit): ParallelJob {
var res=Thread{
action()
}
res.start()
return res
    }

    inline  fun delay(milliseconds: Int) {
Thread.sleep(milliseconds)
    }

    inline fun createCondition(lock: Lock) = ParallelCondition(lock)

    class ParallelCondition(@JvmField val lock: Lock) {

        @JvmField
        var cont: Continuation<Unit>? = null

         inline fun waitCondition(crossinline condition: () -> Boolean) {
            if (condition()) {
                synchronized(cont) {
                    cont.wait()
                }
            }
        }

         inline fun notify() {
            synchronized(cont) {
                cont.notify()
            }
        }
    }
}
