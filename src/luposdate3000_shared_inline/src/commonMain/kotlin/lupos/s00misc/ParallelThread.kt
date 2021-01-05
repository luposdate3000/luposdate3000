package lupos.s00misc
import kotlin.jvm.JvmName
internal expect object ParallelThread {
    @JvmName("runBlocking") internal inline fun <T> runBlocking(crossinline action: () -> T): T
    @JvmName("launch") internal inline fun launch(crossinline action: () -> Unit): ParallelThreadJob
    @JvmName("delay") internal inline fun delay(milliseconds: Long)
    @JvmName("createCondition") internal inline fun createCondition(): ParallelThreadCondition
    @JvmName("createQueue") internal inline fun <T> createQueue(terminationValue: T): ParallelThreadQueue<T>
}
