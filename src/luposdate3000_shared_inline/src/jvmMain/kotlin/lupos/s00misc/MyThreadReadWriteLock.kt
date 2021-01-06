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
    internal actual inline fun getUUID() = uuid
    internal actual inline fun downgradeToReadLock() {
        lock.readLock().lock()
        lock.writeLock().unlock()
    }
    internal actual inline fun readLock() {
        lock.readLock().lock()
    }
    internal actual inline fun readUnlock() {
        lock.readLock().unlock()
    }
    internal actual inline fun writeLock() {
        lock.writeLock().lock()
    }
    internal actual inline fun tryWriteLock(): Boolean {
        return lock.writeLock().tryLock()
    }
    internal actual inline fun writeUnlock() {
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
