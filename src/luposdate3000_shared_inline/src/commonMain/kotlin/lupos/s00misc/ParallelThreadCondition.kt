package lupos.s00misc
import kotlin.jvm.JvmName
internal expect class ParallelThreadCondition {
    @JvmName("waitCondition") internal inline fun waitCondition(crossinline condition: () -> Boolean)
    @JvmName("signal") internal inline fun signal()
}
