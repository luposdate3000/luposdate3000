package lupos.modulename
internal actual object ParallelThread {
    internal actual inline fun <T> runBlocking(crossinline action: () -> T): T {
        return action()
    }
    internal actual inline fun launch(crossinline action: () -> Unit): ParallelThreadJob {
        val res = ParallelThreadJob {
            action()
        }
        res.start()
        return res
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun delay(milliseconds: Long) {
        Thread.sleep(milliseconds)
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun createCondition(): ParallelThreadCondition {
        return ParallelThreadCondition()
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun <T> createQueue(terminationValue: T): ParallelThreadQueue<T> {
        return ParallelThreadQueue(terminationValue)
    }
}
