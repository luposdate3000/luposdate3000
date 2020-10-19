package lupos.s00misc

import kotlin.jvm.JvmField

internal expect class MyThreadLock() {
    inline fun getUUID(): Long
    inline fun lock()
    inline fun unlock()
    inline fun tryLock(): Boolean
}
