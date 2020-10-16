package lupos.s00misc

import kotlin.jvm.JvmField

expect class MyLock() {
    fun getUUID(): Long
    fun lock()
    fun unlock()
    fun tryLock(): Boolean
}
