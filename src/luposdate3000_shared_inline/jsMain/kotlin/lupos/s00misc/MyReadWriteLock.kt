package lupos.s00misc

import lupos.s00misc.NotImplementedException

internal actual class MyThreadReadWriteLock {
    internal companion object {
        var uuidCounter = 0L
    }

    val uuid = uuidCounter++
    actual inline fun getUUID() = uuid
    actual inline fun downgradeToReadLock() {
        throw  NotImplementedException("MyThreadReadWriteLock", "downgradeToReadLock not implemented")
    }

    actual inline fun readLock() {
        throw  NotImplementedException("MyThreadReadWriteLock", "readLock not implemented")
    }

    actual inline fun readUnlock() {
        throw  NotImplementedException("MyThreadReadWriteLock", "readUnlock not implemented")
    }

    actual inline fun writeLock() {
        throw  NotImplementedException("MyThreadReadWriteLock", "writeLock not implemented")
    }

    actual inline fun tryWriteLock(): Boolean {
        throw  NotImplementedException("MyThreadReadWriteLock", "tryWriteLock not implemented")
    }

    actual inline fun writeUnlock() {
        throw  NotImplementedException("MyThreadReadWriteLock", "writeUnlock not implemented")
    }
actual inline fun <T> withReadLock(crossinline action: () -> T): T {
    readLock()
    try {
        return action()
    } finally {
        readUnlock()
    }
}

actual inline fun <T> withWriteLock(crossinline action: () -> T): T {
    writeLock()
    try {
        return action()
    } finally {
        writeUnlock()
    }
}

}
