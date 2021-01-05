package lupos.s00misc
import kotlin.jvm.JvmName
internal actual class ParallelThreadCondition {
     internal actual inline fun waitCondition(crossinline condition: () -> Boolean): Unit = throw NotImplementedException("ParallelThreadCondition", "waitCondition not implemented")
     internal actual inline fun signal(): Unit = throw NotImplementedException("ParallelThreadCondition", "signal not implemented")
}
