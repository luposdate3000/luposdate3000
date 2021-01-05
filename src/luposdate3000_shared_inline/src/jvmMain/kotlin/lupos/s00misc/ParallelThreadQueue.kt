package lupos.s00misc
import java.util.concurrent.ArrayBlockingQueue
import kotlin.jvm.JvmName
internal actual class ParallelThreadQueue<T> actual constructor(@JvmField val terminationValue: T) {
    @JvmField
    val queue = ArrayBlockingQueue<T>(4096)
     internal actual inline fun send(value: T) {
        queue.put(value)
    }
     internal actual inline fun close() {
        queue.put(terminationValue)
    }
     internal actual inline fun receive(): T {
        return queue.take()
    }
}
