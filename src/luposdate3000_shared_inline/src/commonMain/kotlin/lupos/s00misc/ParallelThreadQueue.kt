package lupos.s00misc
import kotlin.jvm.JvmName
internal expect class ParallelThreadQueue<T>(terminationValue: T) {
    @JvmName("send") internal inline fun send(value: T)
    @JvmName("close") internal inline fun close()
    @JvmName("receive") internal inline fun receive(): T
}
