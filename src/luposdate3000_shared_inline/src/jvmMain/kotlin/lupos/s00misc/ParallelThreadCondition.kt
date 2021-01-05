package lupos.s00misc
import kotlin.jvm.JvmName
internal actual class ParallelThreadCondition {
    @JvmField
    val myMonitorObject = this as Object
    @JvmField
    var wasSignalled = false
     internal actual inline fun waitCondition(crossinline condition: () -> Boolean) {
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
     internal actual inline fun signal() {
        synchronized(myMonitorObject) {
            wasSignalled = true
            myMonitorObject.notify()
        }
    }
}
