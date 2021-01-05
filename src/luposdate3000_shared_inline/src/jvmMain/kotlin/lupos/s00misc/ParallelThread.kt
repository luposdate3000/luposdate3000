package lupos.s00misc
import kotlin.jvm.JvmName
internal actual object ParallelThread {
     internal actual inline fun <T> runBlocking(crossinline action: () -> T): T {
        return action()
    }
     internal actual inline fun launch(crossinline action: () -> Unit): ParallelThreadJob {
        val res = ParallelThreadJob {
            action()
        }
        res.start()
        return res
    }
     internal actual inline fun delay(milliseconds: Long) {
        Thread.sleep(milliseconds)
    }
     internal actual inline fun createCondition(): ParallelThreadCondition {
        return ParallelThreadCondition()
    }
     internal actual inline fun <T> createQueue(terminationValue: T): ParallelThreadQueue<T> {
        return ParallelThreadQueue(terminationValue)
    }
}
