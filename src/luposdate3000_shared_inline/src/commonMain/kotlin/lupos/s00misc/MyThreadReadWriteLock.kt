package lupos.s00misc
internal expect class MyThreadReadWriteLock() {
    inline fun getUUID(): Long
    inline fun downgradeToReadLock()
    inline fun readLock()
    inline fun readUnlock()
    inline fun writeLock()
    inline fun tryWriteLock(): Boolean
    inline fun writeUnlock()
    inline fun <T> withReadLock(crossinline action: () -> T): T
    inline fun <T> withWriteLock(crossinline action: () -> T): T
}
