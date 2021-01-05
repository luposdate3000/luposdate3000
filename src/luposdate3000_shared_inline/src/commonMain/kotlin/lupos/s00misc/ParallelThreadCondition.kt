package lupos.s00misc
internal expect class ParallelThreadCondition {
    internal inline fun waitCondition(crossinline condition: () -> Boolean)
    internal inline fun signal()
}
