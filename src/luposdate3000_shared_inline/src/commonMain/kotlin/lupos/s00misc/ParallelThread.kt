package lupos.s00misc

internal expect object ParallelThread {
    inline fun <T> runBlocking(crossinline action: () -> T): T
    inline fun launch(crossinline action: () -> Unit): ParallelThreadJob
    inline fun delay(milliseconds: Long)
    inline fun createCondition(): ParallelThreadCondition
    inline fun <T> createQueue(terminationValue: T): ParallelThreadQueue<T>
}
