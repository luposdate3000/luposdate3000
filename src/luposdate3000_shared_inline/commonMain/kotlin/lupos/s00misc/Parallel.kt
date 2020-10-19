package lupos.s00misc

internal expect object ParallelThread {
    inline fun <T> runBlocking(crossinline action: () -> T): T
    inline fun launch(crossinline action: () -> Unit): ParallelThreadJob
    inline fun delay(milliseconds: Long)
    inline fun <T> createCondition(lock: MyLock): ParallelThreadCondition
    inline fun <T> createQueue(terminationValue: T): ParallelThreadQueue<T>
}

internal expect class ParallelThreadJob {
}

internal expect class ParallelThreadCondition {
    constructor(lock: MyLock)

    inline fun waitCondition(crossinline condition: () -> Boolean)
    inline fun signal()
}

internal expect class ParallelThreadQueue<T> {
    constructor(terminationValue: T)

    inline fun send(value: T)
    inline fun close()
    inline fun receive(): T
}
