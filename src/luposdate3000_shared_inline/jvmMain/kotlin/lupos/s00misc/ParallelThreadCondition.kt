package lupos.s00misc

import java.util.concurrent.locks.ReentrantLock
import kotlin.jvm.JvmField

internal actual class ParallelThreadCondition actual constructor(@JvmField val lock: MyLock) {
    @JvmField
    val lock2 = ReentrantLock()

    @JvmField
    val cond = lock2.newCondition()
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
