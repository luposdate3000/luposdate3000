package lupos.s00misc

internal expect class ParallelThreadCondition {
    constructor(lock: MyLock)

    inline fun waitCondition(crossinline condition: () -> Boolean)
    inline fun signal()
}
