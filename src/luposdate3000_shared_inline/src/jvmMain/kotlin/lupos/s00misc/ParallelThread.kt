package lupos.s00misc
import kotlin.jvm.JvmName
internal actual object ParallelThread {
    @JvmName("runBlocking") internal actual inline fun <T> runBlocking(crossinline action: () -> T): T {
        return action()
    }
    @JvmName("launch") internal actual inline fun launch(crossinline action: () -> Unit): ParallelThreadJob {
        val res = ParallelThreadJob {
            action()
        }
        res.start()
        return res
    }
    @JvmName("delay") internal actual inline fun delay(milliseconds: Long) {
        Thread.sleep(milliseconds)
    }
    @JvmName("createCondition") internal actual inline fun createCondition(): ParallelThreadCondition {
        return ParallelThreadCondition()
    }
    @JvmName("createQueue") internal actual inline fun <T> createQueue(terminationValue: T): ParallelThreadQueue<T> {
        return ParallelThreadQueue(terminationValue)
    }
}
