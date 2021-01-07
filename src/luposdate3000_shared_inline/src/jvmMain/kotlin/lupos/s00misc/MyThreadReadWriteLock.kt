package lupos.modulename
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
@OptIn(kotlin.contracts.ExperimentalContracts::class)
internal actual class MyThreadReadWriteLock {
    internal companion object {
        var uuidCounter = 0L
    }
    val uuid = uuidCounter++
    val lock = ReentrantReadWriteLock()
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getUUID() = uuid
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun downgradeToReadLock() {
        lock.readLock().lock()
        lock.writeLock().unlock()
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun readLock() {
        lock.readLock().lock()
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun readUnlock() {
        lock.readLock().unlock()
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun writeLock() {
        lock.writeLock().lock()
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun tryWriteLock(): Boolean {
        return lock.writeLock().tryLock()
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun writeUnlock() {
        lock.writeLock().unlock()
    }
    internal actual inline fun <T> withReadLock(crossinline action: () -> T): T {
        contract { callsInPlace(action, EXACTLY_ONCE) }
        readLock()
        try {
            return action()
        } finally {
            readUnlock()
        }
    }
    internal actual inline fun <T> withWriteLock(crossinline action: () -> T): T {
        contract { callsInPlace(action, EXACTLY_ONCE) }
        writeLock()
        try {
            return action()
        } finally {
            writeUnlock()
        }
    }
}
