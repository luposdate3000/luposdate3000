package lupos.s00misc
import kotlin.jvm.JvmField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import lupos.s00misc.Coverage
import lupos.s00misc.Lock
object CoroutinesHelperParallel {
    @JvmField
    val channelType = 2
    inline fun run(crossinline action: suspend CoroutineScope.() -> Unit) = GlobalScope.launch {
Coverage.funStart(78)
        action()
Coverage.statementStart(79)
    }
    inline fun runBlock(crossinline action: suspend CoroutineScope.() -> Unit) = runBlocking {
Coverage.funStart(80)
        action()
Coverage.statementStart(81)
    }
    inline fun createLock() = Lock()
    inline fun runBlockWithLock(lock: CoroutinesHelperMutex, crossinline action: () -> Unit) = runBlocking {
Coverage.funStart(82)
        lock.withWriteLock {
Coverage.statementStart(83)
            action()
Coverage.statementStart(84)
        }
Coverage.statementStart(85)
    }
}
