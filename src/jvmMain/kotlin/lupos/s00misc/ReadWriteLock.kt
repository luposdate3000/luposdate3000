package lupos.s00misc
import kotlin.jvm.JvmField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex
import lupos.s00misc.Coverage
class ReadWriteLock {
    @JvmField
    val allowNewReads = Mutex()
    @JvmField
    val allowNewWrites = Mutex()
    @JvmField
    var readers = 0L
    suspend fun readLock() {
Coverage.funStart(16222)
        try {
Coverage.statementStart(16223)
            allowNewReads.lock()
Coverage.statementStart(16224)
            if (++readers == 1L) {
Coverage.ifStart(16225)
                allowNewWrites.lock()
Coverage.statementStart(16226)
            }
Coverage.statementStart(16227)
        } finally {
Coverage.statementStart(16228)
            allowNewReads.unlock()
Coverage.statementStart(16229)
        }
Coverage.statementStart(16230)
    }
    suspend fun readUnlock() {
Coverage.funStart(16231)
        try {
Coverage.statementStart(16232)
            allowNewReads.lock()
Coverage.statementStart(16233)
            if (--readers == 0L) {
Coverage.ifStart(16234)
                allowNewWrites.unlock()
Coverage.statementStart(16235)
            }
Coverage.statementStart(16236)
        } finally {
Coverage.statementStart(16237)
            allowNewReads.unlock()
Coverage.statementStart(16238)
        }
Coverage.statementStart(16239)
    }
    suspend fun writeLock() {
Coverage.funStart(16240)
        allowNewWrites.lock()
Coverage.statementStart(16241)
    }
    suspend fun writeUnlock() {
Coverage.funStart(16242)
        allowNewWrites.unlock()
Coverage.statementStart(16243)
    }
    suspend inline fun <T> withReadLockSuspend(crossinline action: suspend () -> T): T {
Coverage.funStart(16244)
        readLock()
Coverage.statementStart(16245)
        try {
Coverage.statementStart(16246)
            return action()
        } finally {
Coverage.statementStart(16247)
            readUnlock()
Coverage.statementStart(16248)
        }
Coverage.statementStart(16249)
    }
    suspend inline fun <T> withWriteLockSuspend(crossinline action: suspend () -> T): T {
Coverage.funStart(16250)
        writeLock()
Coverage.statementStart(16251)
        try {
Coverage.statementStart(16252)
            return action()
        } finally {
Coverage.statementStart(16253)
            writeUnlock()
Coverage.statementStart(16254)
        }
Coverage.statementStart(16255)
    }
    inline fun <T> withReadLock(crossinline action: suspend CoroutineScope.() -> T): T {
Coverage.funStart(16256)
        var res: T? = null
Coverage.statementStart(16257)
        CoroutinesHelper.runBlock {
Coverage.statementStart(16258)
            withReadLockSuspend {
Coverage.statementStart(16259)
                res = action()
Coverage.statementStart(16260)
            }
Coverage.statementStart(16261)
        }
Coverage.statementStart(16262)
        return res!!
    }
    inline fun <T> withWriteLock(crossinline action: suspend CoroutineScope.() -> T): T {
Coverage.funStart(16263)
        var res: T? = null
Coverage.statementStart(16264)
        CoroutinesHelper.runBlock {
Coverage.statementStart(16265)
            withWriteLockSuspend {
Coverage.statementStart(16266)
                res = action()
Coverage.statementStart(16267)
            }
Coverage.statementStart(16268)
        }
Coverage.statementStart(16269)
        return res!!
    }
}
