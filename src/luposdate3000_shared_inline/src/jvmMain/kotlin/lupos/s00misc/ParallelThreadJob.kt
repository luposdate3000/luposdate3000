package lupos.s00misc
import kotlin.jvm.JvmField
internal actual class ParallelThreadJob(action: () -> Unit) {
    @JvmField
    val myThread = Thread { action() }
    actual fun join() = myThread.join()
    actual fun start() = myThread.start()
}
