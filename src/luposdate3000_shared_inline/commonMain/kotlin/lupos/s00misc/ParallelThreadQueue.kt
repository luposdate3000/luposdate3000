package lupos.s00misc

internal expect class ParallelThreadQueue<T>(terminationValue: T) {

    inline fun send(value: T)
    inline fun close()
    inline fun receive(): T
}
