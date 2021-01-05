package lupos.s00misc
import kotlin.jvm.JvmName
internal actual class MyThreadReadWriteLock {
    internal companion object {
        var uuidCounter = 0L
    }
    val uuid = uuidCounter++
    @JvmName("getUUID") internal actual inline fun getUUID() = uuid
    @JvmName("downgradeToReadLock") internal actual inline fun downgradeToReadLock() {
        throw NotImplementedException("MyThreadReadWriteLock", "downgradeToReadLock not implemented")
    }
    @JvmName("readLock") internal actual inline fun readLock() {
        throw NotImplementedException("MyThreadReadWriteLock", "readLock not implemented")
    }
    @JvmName("readUnlock") internal actual inline fun readUnlock() {
        throw NotImplementedException("MyThreadReadWriteLock", "readUnlock not implemented")
    }
    @JvmName("writeLock") internal actual inline fun writeLock() {
        throw NotImplementedException("MyThreadReadWriteLock", "writeLock not implemented")
    }
    @JvmName("tryWriteLock") internal actual inline fun tryWriteLock(): Boolean {
        throw NotImplementedException("MyThreadReadWriteLock", "tryWriteLock not implemented")
    }
    @JvmName("writeUnlock") internal actual inline fun writeUnlock() {
        throw NotImplementedException("MyThreadReadWriteLock", "writeUnlock not implemented")
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
