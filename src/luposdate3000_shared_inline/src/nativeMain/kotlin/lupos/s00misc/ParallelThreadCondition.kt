package lupos.s00misc

internal actual class ParallelThreadCondition {
    actual constructor() {
    }

    actual inline fun waitCondition(crossinline condition: () -> Boolean): Unit = throw  NotImplementedException("ParallelThreadCondition", "waitCondition not implemented")
    actual inline fun signal(): Unit = throw  NotImplementedException("ParallelThreadCondition", "signal not implemented")
}
