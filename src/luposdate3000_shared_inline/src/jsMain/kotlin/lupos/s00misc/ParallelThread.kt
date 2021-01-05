package lupos.s00misc
import kotlin.jvm.JvmName
internal actual object ParallelThread {
    @JvmName("runBlocking") internal actual inline fun <T> runBlocking(crossinline action: () -> T): T {
        return action()
    }
    @JvmName("launch") internal actual inline fun launch(crossinline action: () -> Unit): ParallelThreadJob = throw NotImplementedException("ParallelThread", "launch not implemented")
    @JvmName("delay") internal actual inline fun delay(milliseconds: Long) {}
    @JvmName("createCondition") internal actual inline fun createCondition(): ParallelThreadCondition {
        return ParallelThreadCondition()
    }
    @JvmName("createQueue") internal actual inline fun <T> createQueue(terminationValue: T): ParallelThreadQueue<T> {
        return ParallelThreadQueue<T>(terminationValue)
    }
}
