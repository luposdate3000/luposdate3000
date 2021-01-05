package lupos.s00misc
import kotlin.jvm.JvmName
internal actual class MyThreadLock {
    internal companion object {
        var uuidCounter = 0L
    }
    val uuid = uuidCounter++
     internal actual inline fun getUUID() = uuid
     internal actual inline fun lock() {
        throw NotImplementedException("MyThreadLock", "lock not implemented")
    }
     internal actual inline fun unlock() {
        throw NotImplementedException("MyThreadLock", "unlock not implemented")
    }
     internal actual inline fun tryLock(): Boolean {
        throw NotImplementedException("MyThreadLock", "trylock not implemented")
    }
     internal actual inline fun <T> withLock(crossinline action: () -> T): T {
        lock()
        try {
            return action()
        } finally {
            unlock()
        }
    }
}
