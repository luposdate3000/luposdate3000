package lupos.s00misc
import kotlin.jvm.JvmName
internal expect class MyThreadLock() {
     internal inline fun getUUID(): Long
     internal inline fun lock()
     internal inline fun unlock()
     internal inline fun tryLock(): Boolean
     internal inline fun <T> withLock(crossinline action: () -> T): T
}
