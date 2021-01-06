package lupos.modulename
internal actual class MyThreadReadWriteLock {
    internal companion object {
        var uuidCounter = 0L
    }
    val uuid = uuidCounter++
    internal actual inline fun getUUID() = uuid
    var lockedRead = 0
    var lockedWrite = false
    internal actual inline fun downgradeToReadLock() {
        SanityCheck {
            if (!lockedWrite) {
                throw Exception("something went wrong 1")
            }
            lockedRead = 1
            lockedWrite = false
        }
    }
    internal actual inline fun readLock() {
        SanityCheck {
            if (lockedWrite) {
                throw Exception("something went wrong 2")
            }
            lockedRead++
        }
    }
    internal actual inline fun readUnlock() {
        SanityCheck {
            if (lockedRead <= 0) {
                throw Exception("something went wrong 3")
            }
            lockedRead--
        }
    }
    internal actual inline fun writeLock() {
        SanityCheck {
            if (lockedRead > 0 || lockedWrite) {
                throw Exception("something went wrong 4 $lockedRead $lockedWrite")
            }
            lockedWrite = true
        }
    }
    internal actual inline fun tryWriteLock(): Boolean {
        SanityCheck {
            if (lockedRead > 0 || lockedWrite) {
                throw Exception("something went wrong 5 $lockedRead $lockedWrite")
            }
            lockedWrite = true
        }
        return true
    }
    internal actual inline fun writeUnlock() {
        SanityCheck {
            if (!lockedWrite) {
                throw Exception("something went wrong 6")
            }
            lockedWrite = false
        }
    }
    internal actual inline fun <T> withReadLock(crossinline action: () -> T): T {
        readLock()
        try {
            return action()
        } finally {
            readUnlock()
        }
    }
    internal actual inline fun <T> withWriteLock(crossinline action: () -> T): T {
        writeLock()
        try {
            return action()
        } finally {
            writeUnlock()
        }
    }
}
