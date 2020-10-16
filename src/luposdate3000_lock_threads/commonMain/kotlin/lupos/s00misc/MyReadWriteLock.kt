package lupos.s00misc

import kotlin.jvm.JvmField

expect class MyReadWriteLock {
constructor()
fun getUUID():Long
     fun downgradeToReadLock()
     fun readLock()
     fun readUnlock()
     fun writeLock()
     fun tryWriteLock(): Boolean
     fun writeUnlock()
}
