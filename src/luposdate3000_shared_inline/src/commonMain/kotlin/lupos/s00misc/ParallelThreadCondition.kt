package lupos.s00misc
internal expect class ParallelThreadCondition {
    inline fun waitCondition(crossinline condition: () -> Boolean)
    inline fun signal()
}
