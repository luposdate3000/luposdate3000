package lupos.s00misc

internal actual object ParallelThread {
    actual inline fun <T> runBlocking(crossinline action: () -> T): T {
        return action()
    }

    actual inline fun launch(crossinline action: () -> Unit): ParallelThreadJob = throw NotImplementedException("ParallelThread", "launch not implemented")
    actual inline fun delay(milliseconds: Long): Unit = throw NotImplementedException("ParallelThread", "delay not implemented")
    actual inline fun createCondition(): ParallelThreadCondition {
        return ParallelThreadCondition()
    }

    actual inline fun <T> createQueue(terminationValue: T): ParallelThreadQueue<T> {
        return ParallelThreadQueue<T>(terminationValue)
    }
}
