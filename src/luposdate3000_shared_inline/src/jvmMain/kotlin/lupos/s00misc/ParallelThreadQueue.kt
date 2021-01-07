package lupos.modulename
import java.util.concurrent.ArrayBlockingQueue
import kotlin.jvm.JvmField
internal actual class ParallelThreadQueue<T> actual constructor(@JvmField public val terminationValue: T) {
    @JvmField
    val queue = ArrayBlockingQueue<T>(4096)
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun send(value: T) {
        queue.put(value)
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun close() {
        queue.put(terminationValue)
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun receive(): T {
        return queue.take()
    }
}
