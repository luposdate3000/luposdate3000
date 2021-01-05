package lupos.s00misc
import kotlin.jvm.JvmName
internal actual class MyThreadLock {
    internal companion object {
        var uuidCounter = 0L
    }
    val uuid = uuidCounter++
    @JvmName("getUUID") internal actual inline fun getUUID() = uuid
    @JvmName("lock") internal actual inline fun lock() {
        throw NotImplementedException("MyThreadLock", "lock not implemented")
    }
    @JvmName("unlock") internal actual inline fun unlock() {
        throw NotImplementedException("MyThreadLock", "unlock not implemented")
    }
    @JvmName("tryLock") internal actual inline fun tryLock(): Boolean {
        throw NotImplementedException("MyThreadLock", "trylock not implemented")
    }
    @JvmName("withLock") internal actual inline fun <T> withLock(crossinline action: () -> T): T {
        lock()
        try {
            return action()
        } finally {
            unlock()
        }
    }
}
