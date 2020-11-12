package lupos.s00misc

import java.util.concurrent.ArrayBlockingQueue
import kotlin.jvm.JvmField

internal actual class ParallelThreadQueue<T> actual constructor(@JvmField val terminationValue: T) {
    @JvmField
    val queue = ArrayBlockingQueue<T>(4096)
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
