package lupos.s00misc

import kotlin.jvm.JvmField

internal expect class MyThreadReadWriteLock() {
    inline fun getUUID(): Long
    inline fun downgradeToReadLock()
    inline fun readLock()
    inline fun readUnlock()
    inline fun writeLock()
    inline fun tryWriteLock(): Boolean
    inline fun writeUnlock()
}
