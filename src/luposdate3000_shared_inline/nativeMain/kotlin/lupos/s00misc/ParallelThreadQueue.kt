package lupos.s00misc

internal actual class ParallelThreadQueue<T> {
    actual constructor(terminationValue: T) {
    }

    actual inline fun send(value: T): Unit {
        throw  NotImplementedException("ParallelThreadQueue", "send not implemented")
    }

    actual inline fun close(): Unit {
        throw  NotImplementedException("ParallelThreadQueue", "close not implemented")
    }

    actual inline fun receive(): T {
        throw  NotImplementedException("ParallelThreadQueue", "receive not implemented")
    }
}
