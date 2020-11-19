package lupos.s00misc

internal expect class ParallelThreadCondition(lock: MyLock) {
    inline fun waitCondition(crossinline condition: () -> Boolean)
    inline fun signal()
}
