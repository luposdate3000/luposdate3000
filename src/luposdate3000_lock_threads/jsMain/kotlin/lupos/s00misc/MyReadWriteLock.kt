package lupos.s00misc

import lupos.s00misc.NotImplementedException

actual class MyReadWriteLock {
    companion object {
        var uuidCounter = 0L
    }

    val uuid = uuidCounter++
    fun downgradeToReadLock() {
        throw object : NotImplementedException("MyReadWriteLock", "downgradeToReadLock not implemented") {}
    }

    fun readLock() {
        throw object : NotImplementedException("MyReadWriteLock", "readLock not implemented") {}
    }

    fun readUnlock() {
        throw object : NotImplementedException("MyReadWriteLock", "readUnlock not implemented") {}
    }

    fun writeLock() {
        throw object : NotImplementedException("MyReadWriteLock", "writeLock not implemented") {}
    }

    fun tryWriteLock(): Boolean {
        throw object : NotImplementedException("MyReadWriteLock", "tryWriteLock not implemented") {}
    }

    fun writeUnlock() {
        throw object : NotImplementedException("MyReadWriteLock", "writeUnlock not implemented") {}
    }
}
