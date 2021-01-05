package lupos.s00misc
import kotlin.jvm.JvmName
internal expect class ParallelThreadCondition {
     internal inline fun waitCondition(crossinline condition: () -> Boolean)
     internal inline fun signal()
}
