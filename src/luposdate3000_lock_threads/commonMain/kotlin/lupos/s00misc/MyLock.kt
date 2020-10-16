package lupos.s00misc

import kotlin.jvm.JvmField

expect class MyLock {
    val uuid: Long
    inline fun lock()
    inline fun unlock()
    inline fun tryLock(): Boolean
}
