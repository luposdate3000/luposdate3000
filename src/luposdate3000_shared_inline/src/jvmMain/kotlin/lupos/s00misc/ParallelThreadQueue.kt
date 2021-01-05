package lupos.s00misc
import java.util.concurrent.ArrayBlockingQueue
import kotlin.jvm.JvmName
internal actual class ParallelThreadQueue<T> actual constructor(@JvmField val terminationValue: T) {
    @JvmField
    val queue = ArrayBlockingQueue<T>(4096)
    @JvmName("send") internal actual inline fun send(value: T) {
        queue.put(value)
    }
    @JvmName("close") internal actual inline fun close() {
        queue.put(terminationValue)
    }
    @JvmName("receive") internal actual inline fun receive(): T {
        return queue.take()
    }
}
