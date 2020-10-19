package lupos.s00misc

internal actual object ParallelThread {
    actual inline fun <T> runBlocking(crossinline action: () -> T): T {
        return action()
    }

    actual inline fun launch(crossinline action: () -> Unit): ParallelThreadJob = throw  NotImplementedException("ParallelThread", "launch not implemented")
    actual inline fun delay(milliseconds: Long): Unit = throw  NotImplementedException("ParallelThread", "delay not implemented")
    actual inline fun <T> createCondition(lock: MyLock): ParallelThreadCondition {
        return ParallelThreadCondition(lock)
    }

    actual inline fun <T> createQueue(terminationValue: T): ParallelThreadQueue<T> {
        return ParallelThreadQueue<T>(terminationValue)
    }
}

internal actual class ParallelThreadJob {
}

internal actual class ParallelThreadCondition {
    actual constructor(lock: MyLock) {
    }

    actual inline fun waitCondition(crossinline condition: () -> Boolean): Unit = throw  NotImplementedException("ParallelThreadCondition", "waitCondition not implemented")
    actual inline fun signal(): Unit = throw  NotImplementedException("ParallelThreadCondition", "signal not implemented")
}

internal actual class ParallelThreadQueue<T> {
    actual constructor(terminationValue: T) {
    }

    actual inline fun send(value: T): Unit {
        throw  NotImplementedException("ParallelThreadQueue", "send not implemented")
    }

    actual inline fun close(): Unit {
        throw  NotImplementedException("ParallelThreadQueue", "close not implemented")
    }

    actual inline fun receive(): T {
        throw  NotImplementedException("ParallelThreadQueue", "receive not implemented")
    }
}
