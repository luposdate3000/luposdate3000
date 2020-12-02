package lupos.s00misc

import java.util.concurrent.locks.ReentrantLock
import kotlin.jvm.JvmField

internal actual class ParallelThreadCondition {
    @JvmField
    val myMonitorObject = this as Object

    @JvmField
    var wasSignalled = false
    actual inline fun waitCondition(crossinline condition: () -> Boolean) {
        synchronized(myMonitorObject) {
            if (!wasSignalled && condition()) {
                try {
                    myMonitorObject.wait()
                } catch (e: Exception) {
                }
            }
            wasSignalled = false
        }
    }

    actual inline fun signal() {
        synchronized(myMonitorObject) {
            wasSignalled = true
            myMonitorObject.notify()
        }
    }
}
