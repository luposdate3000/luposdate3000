package lupos.s00misc
internal actual class ParallelThreadJob(action: () -> Unit) {
    @JvmField
    val myThread = Thread { action() }
    actual fun join() = myThread.join()
    actual fun start() = myThread.start()
}
