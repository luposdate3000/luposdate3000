package lupos.s00misc
import kotlin.jvm.JvmName
internal expect class MyThreadReadWriteLock() {
    @JvmName("getUUID") internal inline fun getUUID(): Long
    @JvmName("downgradeToReadLock") internal inline fun downgradeToReadLock()
    @JvmName("readLock") internal inline fun readLock()
    @JvmName("readUnlock") internal inline fun readUnlock()
    @JvmName("writeLock") internal inline fun writeLock()
    @JvmName("tryWriteLock") internal inline fun tryWriteLock(): Boolean
    @JvmName("writeUnlock") internal inline fun writeUnlock()
    @JvmName("withReadLock") internal inline fun <T> withReadLock(crossinline action: () -> T): T
    @JvmName("withWriteLock") internal inline fun <T> withWriteLock(crossinline action: () -> T): T
}
