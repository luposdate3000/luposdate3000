package lupos.s00misc
import kotlin.jvm.JvmName
internal expect class MyThreadLock() {
    @JvmName("getUUID") internal inline fun getUUID(): Long
    @JvmName("lock") internal inline fun lock()
    @JvmName("unlock") internal inline fun unlock()
    @JvmName("tryLock") internal inline fun tryLock(): Boolean
    @JvmName("withLock") internal inline fun <T> withLock(crossinline action: () -> T): T
}
