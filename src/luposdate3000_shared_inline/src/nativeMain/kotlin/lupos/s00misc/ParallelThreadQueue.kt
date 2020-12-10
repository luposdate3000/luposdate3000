package lupos.s00misc

internal actual class ParallelThreadQueue<T> {
    actual constructor(terminationValue: T) {
    }

    actual inline fun send(value: T) {
        throw NotImplementedException("ParallelThreadQueue", "send not implemented")
    }

    actual inline fun close() {
        throw NotImplementedException("ParallelThreadQueue", "close not implemented")
    }

    actual inline fun receive(): T {
        throw NotImplementedException("ParallelThreadQueue", "receive not implemented")
    }
}
