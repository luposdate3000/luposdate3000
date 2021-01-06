package lupos.modulename
internal actual object ParallelThread {
    internal actual inline fun <T> runBlocking(crossinline action: () -> T): T {
        return action()
    }
    internal actual inline fun launch(crossinline action: () -> Unit): ParallelThreadJob = throw NotImplementedException("ParallelThread", "launch not implemented")
    internal actual inline fun delay(milliseconds: Long) {}
    internal actual inline fun createCondition(): ParallelThreadCondition {
        return ParallelThreadCondition()
    }
    internal actual inline fun <T> createQueue(terminationValue: T): ParallelThreadQueue<T> {
        return ParallelThreadQueue<T>(terminationValue)
    }
}
