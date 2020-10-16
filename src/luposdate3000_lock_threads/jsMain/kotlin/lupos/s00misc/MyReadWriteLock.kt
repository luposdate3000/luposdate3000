package lupos.s00misc

import lupos.s00misc.NotImplementedException

actual class MyReadWriteLock {
internal    companion object {
        var uuidCounter = 0L
    }

    val uuid = uuidCounter++
actual fun getUUID()=uuid
    actual fun downgradeToReadLock() {
        throw object : NotImplementedException("MyReadWriteLock", "downgradeToReadLock not implemented") {}
    }

    actual fun readLock() {
        throw object : NotImplementedException("MyReadWriteLock", "readLock not implemented") {}
    }

    actual fun readUnlock() {
        throw object : NotImplementedException("MyReadWriteLock", "readUnlock not implemented") {}
    }

    actual fun writeLock() {
        throw object : NotImplementedException("MyReadWriteLock", "writeLock not implemented") {}
    }

    actual fun tryWriteLock(): Boolean {
        throw object : NotImplementedException("MyReadWriteLock", "tryWriteLock not implemented") {}
    }

    actual fun writeUnlock() {
        throw object : NotImplementedException("MyReadWriteLock", "writeUnlock not implemented") {}
    }
}
