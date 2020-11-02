package lupos.s00misc

internal actual object ParallelThread {
    actual inline fun <T> runBlocking(crossinline action: () -> T): T {
        return action()
    }

    actual inline fun launch(crossinline action: () -> Unit): ParallelThreadJob {
        var res = ParallelThreadJob {
            action()
        }
        res.start()
        return res
    }

    actual inline fun delay(milliseconds: Long) {
        Thread.sleep(milliseconds)
    }

    actual inline fun createCondition(lock: MyLock): ParallelThreadCondition {
        return ParallelThreadCondition(lock)
    }

    actual inline fun <T> createQueue(terminationValue: T): ParallelThreadQueue<T> {
        return ParallelThreadQueue<T>(terminationValue)
    }
}
