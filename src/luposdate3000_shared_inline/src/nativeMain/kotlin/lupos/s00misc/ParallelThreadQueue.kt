package lupos.s00misc
import kotlin.jvm.JvmName
internal actual class ParallelThreadQueue<T> {
    actual constructor(terminationValue: T) {
    }
    @JvmName("send") internal actual inline fun send(value: T) {
        throw NotImplementedException("ParallelThreadQueue", "send not implemented")
    }
    @JvmName("close") internal actual inline fun close() {
        throw NotImplementedException("ParallelThreadQueue", "close not implemented")
    }
    @JvmName("receive") internal actual inline fun receive(): T {
        throw NotImplementedException("ParallelThreadQueue", "receive not implemented")
    }
}
