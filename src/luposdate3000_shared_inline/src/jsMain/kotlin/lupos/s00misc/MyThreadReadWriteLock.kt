package lupos.s00misc
import kotlin.jvm.JvmName
internal actual class MyThreadReadWriteLock {
    internal companion object {
        var uuidCounter = 0L
    }
    val uuid = uuidCounter++
    @JvmName("getUUID") internal actual inline fun getUUID() = uuid
    var lockedRead = 0
    var lockedWrite = false
    @JvmName("downgradeToReadLock") internal actual inline fun downgradeToReadLock() {
        SanityCheck {
            if (!lockedWrite) {
                throw Exception("something went wrong 1")
            }
            lockedRead = 1
            lockedWrite = false
        }
    }
    @JvmName("readLock") internal actual inline fun readLock() {
        SanityCheck {
            if (lockedWrite) {
                throw Exception("something went wrong 2")
            }
            lockedRead++
        }
    }
    @JvmName("readUnlock") internal actual inline fun readUnlock() {
        SanityCheck {
            if (lockedRead <= 0) {
                throw Exception("something went wrong 3")
            }
            lockedRead--
        }
    }
    @JvmName("writeLock") internal actual inline fun writeLock() {
        SanityCheck {
            if (lockedRead > 0 || lockedWrite) {
                throw Exception("something went wrong 4 $lockedRead $lockedWrite")
            }
            lockedWrite = true
        }
    }
    @JvmName("tryWriteLock") internal actual inline fun tryWriteLock(): Boolean {
        SanityCheck {
            if (lockedRead > 0 || lockedWrite) {
                throw Exception("something went wrong 5 $lockedRead $lockedWrite")
            }
            lockedWrite = true
        }
        return true
    }
    @JvmName("writeUnlock") internal actual inline fun writeUnlock() {
        SanityCheck {
            if (!lockedWrite) {
                throw Exception("something went wrong 6")
            }
            lockedWrite = false
        }
    }
    @JvmName("withReadLock") internal actual inline fun <T> withReadLock(crossinline action: () -> T): T {
        readLock()
        try {
            return action()
        } finally {
            readUnlock()
        }
    }
    @JvmName("withWriteLock") internal actual inline fun <T> withWriteLock(crossinline action: () -> T): T {
        writeLock()
        try {
            return action()
        } finally {
            writeUnlock()
        }
    }
}
