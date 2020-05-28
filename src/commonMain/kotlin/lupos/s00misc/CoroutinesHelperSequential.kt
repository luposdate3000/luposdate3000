package lupos.s00misc
import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import lupos.s00misc.Coverage
object CoroutinesHelperSequential {
    @JvmField
    val channelType = UNLIMITED
    inline fun run(crossinline action: suspend CoroutineScope.() -> Unit) = runBlocking {
Coverage.funStart(86)
        action()
Coverage.statementStart(87)
    }
    inline fun runBlock(crossinline action: suspend CoroutineScope.() -> Unit) = runBlocking {
Coverage.funStart(88)
        action()
Coverage.statementStart(89)
    }
    inline fun createLock() = 0
    inline fun runBlockWithLock(lock: CoroutinesHelperMutex, crossinline action: () -> Unit) = runBlocking {
Coverage.funStart(90)
        action()
Coverage.statementStart(91)
    }
}
