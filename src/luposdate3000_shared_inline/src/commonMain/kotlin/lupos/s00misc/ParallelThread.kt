package lupos.modulename
internal expect object ParallelThread {
    internal inline fun <T> runBlocking(crossinline action: () -> T): T
    internal inline fun launch(crossinline action: () -> Unit): ParallelThreadJob
    internal inline fun delay(milliseconds: Long)
    internal inline fun createCondition(): ParallelThreadCondition
    internal inline fun <T> createQueue(terminationValue: T): ParallelThreadQueue<T>
}
