package lupos.s00misc
import java.util.concurrent.Semaphore
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import kotlin.jvm.JvmName
@OptIn(kotlin.contracts.ExperimentalContracts::class)
internal actual class MyThreadLock {
    internal companion object {
        var uuidCounter = 0L
    }
    val uuid = uuidCounter++
    val semaphore = Semaphore(1)
    @JvmName("getUUID") internal actual inline fun getUUID() = uuid
    @JvmName("lock") internal actual inline fun lock() {
        semaphore.acquire()
    }
    @JvmName("unlock") internal actual inline fun unlock() {
        semaphore.release()
    }
    @JvmName("tryLock") internal actual inline fun tryLock(): Boolean {
        return semaphore.tryAcquire()
    }
    @JvmName("withLock") internal actual inline fun <T> withLock(crossinline action: () -> T): T {
        contract { callsInPlace(action, EXACTLY_ONCE) }
        lock()
        try {
            return action()
        } finally {
            unlock()
        }
    }
}
