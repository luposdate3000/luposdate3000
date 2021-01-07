package lupos.modulename
import java.util.concurrent.Semaphore
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
@OptIn(kotlin.contracts.ExperimentalContracts::class)
internal actual class MyThreadLock {
    internal companion object {
        var uuidCounter = 0L
    }
    val uuid = uuidCounter++
    val semaphore = Semaphore(1)
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getUUID() = uuid
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun lock() {
        semaphore.acquire()
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun unlock() {
        semaphore.release()
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun tryLock(): Boolean {
        return semaphore.tryAcquire()
    }
    internal actual inline fun <T> withLock(crossinline action: () -> T): T {
        contract { callsInPlace(action, EXACTLY_ONCE) }
        lock()
        try {
            return action()
        } finally {
            unlock()
        }
    }
}
