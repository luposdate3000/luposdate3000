package lupos.s00misc
import kotlin.jvm.JvmName
internal actual class ParallelThreadQueue<T> {
    actual constructor(terminationValue: T) {
    }
     internal actual inline fun send(value: T) {
        throw NotImplementedException("ParallelThreadQueue", "send not implemented")
    }
     internal actual inline fun close() {
        throw NotImplementedException("ParallelThreadQueue", "close not implemented")
    }
     internal actual inline fun receive(): T {
        throw NotImplementedException("ParallelThreadQueue", "receive not implemented")
    }
}
