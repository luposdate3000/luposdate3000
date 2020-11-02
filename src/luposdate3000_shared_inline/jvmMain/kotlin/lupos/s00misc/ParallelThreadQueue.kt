package lupos.s00misc

import java.util.concurrent.ArrayBlockingQueue
import kotlin.jvm.JvmField

internal actual class ParallelThreadQueue<T> {
    @JvmField
    val queue = ArrayBlockingQueue<T>(4096)

    @JvmField
    val terminationValue: T

    actual constructor(terminationValue: T) {
        this.terminationValue = terminationValue
    }

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
