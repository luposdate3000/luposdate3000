package lupos.s00misc

internal actual object ParallelThread {
    actual inline fun <T> runBlocking(crossinline action: () -> T): T {
        return action()
    }

    actual inline fun launch(crossinline action: () -> Unit): ParallelThreadJob {
        val res = ParallelThreadJob {
            action()
        }
        res.start()
        return res
    }

    actual inline fun delay(milliseconds: Long) {
        Thread.sleep(milliseconds)
    }

    actual inline fun createCondition(): ParallelThreadCondition {
        return ParallelThreadCondition()
    }

    actual inline fun <T> createQueue(terminationValue: T): ParallelThreadQueue<T> {
        return ParallelThreadQueue(terminationValue)
    }
}
