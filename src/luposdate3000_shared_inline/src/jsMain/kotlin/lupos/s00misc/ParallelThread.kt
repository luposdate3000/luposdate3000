package lupos.modulename
import lupos.s00misc.NotImplementedException
internal actual object ParallelThread {
    internal actual inline fun <T> runBlocking(crossinline action: () -> T): T {
        return action()
    }
    internal actual inline fun launch(crossinline action: () -> Unit): ParallelThreadJob = throw NotImplementedException("ParallelThread", "launch not implemented")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun delay(milliseconds: Long) {}
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun createCondition(): ParallelThreadCondition {
        return ParallelThreadCondition()
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun <T> createQueue(terminationValue: T): ParallelThreadQueue<T> {
        return ParallelThreadQueue<T>(terminationValue)
    }
}
