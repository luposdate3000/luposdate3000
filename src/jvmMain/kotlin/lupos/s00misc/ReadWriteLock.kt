package lupos.s00misc

import kotlin.jvm.JvmField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex
import lupos.s00misc.Coverage

var debuguuidtmp123 = 0

class ReadWriteLock {
    val uuid = debuguuidtmp123++

    @JvmField
    val allowNewReads = Mutex()

    @JvmField
    val allowNewWrites = Mutex()

    @JvmField
    var readers = 0L
    suspend fun readLock() {
//println("ReadWriteLock($uuid) readLock A")
        try {
            allowNewReads.lock()
            if (++readers == 1L) {
                allowNewWrites.lock()
            }
        } finally {
            allowNewReads.unlock()
        }
//println("ReadWriteLock($uuid) readLock B")
    }

    suspend fun readUnlock() {
//println("ReadWriteLock($uuid) readUnlock A")
        try {
            allowNewReads.lock()
            if (--readers == 0L) {
                allowNewWrites.unlock()
            }
        } finally {
            allowNewReads.unlock()
        }
//println("ReadWriteLock($uuid) readUnlock B")
    }

    suspend fun writeLock() {
//println("ReadWriteLock($uuid) writeLock A")
        allowNewWrites.lock()
//println("ReadWriteLock($uuid) writeLock B")
    }

    suspend fun writeUnlock() {
//println("ReadWriteLock($uuid) writeUnlock A")
        allowNewWrites.unlock()
//println("ReadWriteLock($uuid) writeUnlock B")
    }

    suspend /*inline*/  fun <T> withReadLockSuspend(/*crossinline*/  action: suspend () -> T): T {
//println("ReadWriteLock($uuid) withReadLock A")
        readLock()
        try {
            return action()
        } finally {
            readUnlock()
//println("ReadWriteLock($uuid) withReadLock B")
        }
/*Coverage Unreachable*/
    }

    suspend /*inline*/  fun <T> withWriteLockSuspend(/*crossinline*/  action: suspend () -> T): T {
//println("ReadWriteLock($uuid) withWriteLock A")
        writeLock()
        try {
            return action()
        } finally {
            writeUnlock()
//println("ReadWriteLock($uuid) withWriteLock B")
        }
/*Coverage Unreachable*/
    }

    /*inline*/  fun <T> withReadLock(/*crossinline*/  action: suspend CoroutineScope.() -> T): T {
//println("ReadWriteLock($uuid) withReadLock C")
        var res: T? = null
        CoroutinesHelper.runBlock {
            withReadLockSuspend {
                res = action()
            }
        }
//println("ReadWriteLock($uuid) withReadLock D")
        return res!!
    }

    /*inline*/  fun <T> withWriteLock(/*crossinline*/  action: suspend CoroutineScope.() -> T): T {
//println("ReadWriteLock($uuid) withWriteLock C")
        var res: T? = null
        CoroutinesHelper.runBlock {
            withWriteLockSuspend {
                res = action()
            }
        }
//println("ReadWriteLock($uuid) withWriteLock D")
        return res!!
    }
}
