package lupos.modulename
internal expect class MyThreadReadWriteLock() {
    internal inline fun getUUID(): Long
    internal inline fun downgradeToReadLock()
    internal inline fun readLock()
    internal inline fun readUnlock()
    internal inline fun writeLock()
    internal inline fun tryWriteLock(): Boolean
    internal inline fun writeUnlock()
    internal inline fun <T> withReadLock(crossinline action: () -> T): T
    internal inline fun <T> withWriteLock(crossinline action: () -> T): T
}
