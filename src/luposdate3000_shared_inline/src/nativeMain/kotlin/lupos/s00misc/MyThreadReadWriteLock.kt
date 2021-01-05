package lupos.s00misc
import kotlin.jvm.JvmName
internal actual class MyThreadReadWriteLock {
    internal companion object {
        var uuidCounter = 0L
    }
    val uuid = uuidCounter++
     internal actual inline fun getUUID() = uuid
     internal actual inline fun downgradeToReadLock() {
        throw NotImplementedException("MyThreadReadWriteLock", "downgradeToReadLock not implemented")
    }
     internal actual inline fun readLock() {
        throw NotImplementedException("MyThreadReadWriteLock", "readLock not implemented")
    }
     internal actual inline fun readUnlock() {
        throw NotImplementedException("MyThreadReadWriteLock", "readUnlock not implemented")
    }
     internal actual inline fun writeLock() {
        throw NotImplementedException("MyThreadReadWriteLock", "writeLock not implemented")
    }
     internal actual inline fun tryWriteLock(): Boolean {
        throw NotImplementedException("MyThreadReadWriteLock", "tryWriteLock not implemented")
    }
     internal actual inline fun writeUnlock() {
        throw NotImplementedException("MyThreadReadWriteLock", "writeUnlock not implemented")
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
