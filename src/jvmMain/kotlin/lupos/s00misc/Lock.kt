package lupos.s00misc
import kotlin.jvm.JvmField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex
import lupos.s00misc.Coverage
class Lock {
    @JvmField
    val mutex = Mutex()
    suspend inline fun <T> withWriteLockSuspend(crossinline action: suspend () -> T): T {
Coverage.funStart(16203)
        try {
Coverage.statementStart(16204)
            mutex.lock()
Coverage.statementStart(16205)
            return action()
        } finally {
Coverage.statementStart(16206)
            mutex.unlock()
Coverage.statementStart(16207)
        }
Coverage.statementStart(16208)
    }
    inline fun <T> withWriteLock(crossinline action: suspend CoroutineScope.() -> T): T {
Coverage.funStart(16209)
        var res: T? = null
Coverage.statementStart(16210)
        CoroutinesHelper.runBlock {
Coverage.statementStart(16211)
            withWriteLockSuspend {
Coverage.statementStart(16212)
                res = action()
Coverage.statementStart(16213)
            }
Coverage.statementStart(16214)
        }
Coverage.statementStart(16215)
        return res!!
    }
}
