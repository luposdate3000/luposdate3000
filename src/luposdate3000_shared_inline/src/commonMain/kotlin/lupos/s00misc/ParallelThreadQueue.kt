package lupos.s00misc
import kotlin.jvm.JvmName
internal expect class ParallelThreadQueue<T>(terminationValue: T) {
     internal inline fun send(value: T)
     internal inline fun close()
     internal inline fun receive(): T
}
