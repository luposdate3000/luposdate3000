package lupos.s00misc
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import kotlinx.cinterop.cValue
import kotlinx.coroutines.CoroutineScope
import lupos.s00misc.Coverage
import platform.posix.pthread_mutex_init
import platform.posix.pthread_mutex_lock
import platform.posix.pthread_mutex_t
import platform.posix.pthread_mutex_unlock
class ReadWriteLock {
    val allowNewReads = cValue<pthread_mutex_t>()
    val allowNewWrites = cValue<pthread_mutex_t>()
    var readers = AtomicReference(0L.freeze())
    constructor() {
        pthread_mutex_init(allowNewReads, null)
        pthread_mutex_init(allowNewWrites, null)
    }
    suspend fun readLock() {
Coverage.funStart(17187)
        try {
Coverage.statementStart(17188)
            pthread_mutex_lock(allowNewReads)
Coverage.statementStart(17189)
            readers.value = (readers.value + 1).freeze()
Coverage.statementStart(17190)
            if (readers.value == 1L) {
Coverage.ifStart(17191)
                pthread_mutex_lock(allowNewWrites)
Coverage.statementStart(17192)
            }
Coverage.statementStart(17193)
        } finally {
Coverage.statementStart(17194)
            pthread_mutex_unlock(allowNewReads)
Coverage.statementStart(17195)
        }
Coverage.statementStart(17196)
    }
    suspend fun readUnlock() {
Coverage.funStart(17197)
        try {
Coverage.statementStart(17198)
            pthread_mutex_lock(allowNewReads)
Coverage.statementStart(17199)
            readers.value = (readers.value - 1).freeze()
Coverage.statementStart(17200)
            if (readers.value == 0L) {
Coverage.ifStart(17201)
                pthread_mutex_unlock(allowNewWrites)
Coverage.statementStart(17202)
            }
Coverage.statementStart(17203)
        } finally {
Coverage.statementStart(17204)
            pthread_mutex_unlock(allowNewReads)
Coverage.statementStart(17205)
        }
Coverage.statementStart(17206)
    }
    suspend fun writeLock() {
Coverage.funStart(17207)
        pthread_mutex_lock(allowNewWrites)
Coverage.statementStart(17208)
    }
    suspend fun writeUnlock() {
Coverage.funStart(17209)
        pthread_mutex_unlock(allowNewWrites)
Coverage.statementStart(17210)
    }
    inline suspend fun <T> withReadLockSuspend(crossinline action: suspend () -> T): T {
Coverage.funStart(17211)
        readLock()
Coverage.statementStart(17212)
        try {
Coverage.statementStart(17213)
            return action()
        } finally {
Coverage.statementStart(17214)
            readUnlock()
Coverage.statementStart(17215)
        }
Coverage.statementStart(17216)
    }
    inline suspend fun <T> withWriteLockSuspend(crossinline action: suspend () -> T): T {
Coverage.funStart(17217)
        writeLock()
Coverage.statementStart(17218)
        try {
Coverage.statementStart(17219)
            return action()
        } finally {
Coverage.statementStart(17220)
            writeUnlock()
Coverage.statementStart(17221)
        }
Coverage.statementStart(17222)
    }
    inline fun <T> withReadLock(crossinline action: suspend CoroutineScope.() -> T): T {
Coverage.funStart(17223)
        var res: T? = null
Coverage.statementStart(17224)
        CoroutinesHelper.runBlock {
Coverage.statementStart(17225)
            withReadLockSuspend {
Coverage.statementStart(17226)
                res = action()
Coverage.statementStart(17227)
            }
Coverage.statementStart(17228)
        }
Coverage.statementStart(17229)
        return res!!
    }
    inline fun <T> withWriteLock(crossinline action: suspend CoroutineScope.() -> T): T {
Coverage.funStart(17230)
        var res: T? = null
Coverage.statementStart(17231)
        CoroutinesHelper.runBlock {
Coverage.statementStart(17232)
            withWriteLockSuspend {
Coverage.statementStart(17233)
                res = action()
Coverage.statementStart(17234)
            }
Coverage.statementStart(17235)
        }
Coverage.statementStart(17236)
        return res!!
    }
}
//pthread_mutex_destroy
