package lupos.s00misc

internal expect class MyThreadLock() {
    inline fun getUUID(): Long
    inline fun lock()
    inline fun unlock()
    inline fun tryLock(): Boolean
    inline fun <T> withLock(crossinline action: () -> T): T
}
