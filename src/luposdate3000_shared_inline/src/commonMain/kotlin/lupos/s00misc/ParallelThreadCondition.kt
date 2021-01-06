package lupos.modulename
internal expect class ParallelThreadCondition {
    internal inline fun waitCondition(crossinline condition: () -> Boolean)
    internal inline fun signal()
}
