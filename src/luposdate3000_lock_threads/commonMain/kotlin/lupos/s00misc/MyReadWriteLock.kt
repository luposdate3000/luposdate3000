package lupos.s00misc

import kotlin.jvm.JvmField

expect class MyReadWriteLock {
    val uuid: Long
    inline fun downgradeToReadLock()
    inline fun readLock()
    inline fun readUnlock()
    inline fun writeLock()
    inline fun tryWriteLock(): Boolean
    inline fun writeUnlock()
}
