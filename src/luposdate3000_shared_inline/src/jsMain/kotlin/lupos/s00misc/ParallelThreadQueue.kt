package lupos.s00misc
import kotlin.jvm.JvmName
internal actual class ParallelThreadQueue<T> {
    var queue = mutableListOf<T>()
    var terminalValue: T
    actual constructor(terminationValue: T) {
        terminalValue = terminationValue
    }
     internal actual inline fun send(value: T) {
        queue.add(value)
    }
     internal actual inline fun close() {
        queue.clear()
    }
     internal actual inline fun receive(): T {
        if (queue.size > 0) {
            return queue.removeAt(0)
        }
        return terminalValue
    }
}
