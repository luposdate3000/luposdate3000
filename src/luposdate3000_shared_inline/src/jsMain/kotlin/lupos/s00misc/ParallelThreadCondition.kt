package lupos.modulename
import lupos.s00misc.NotImplementedException
internal actual class ParallelThreadCondition {
    internal actual inline fun waitCondition(crossinline condition: () -> Boolean): Unit = throw NotImplementedException("ParallelThreadCondition", "waitCondition not implemented")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun signal(): Unit = throw NotImplementedException("ParallelThreadCondition", "signal not implemented")
}
