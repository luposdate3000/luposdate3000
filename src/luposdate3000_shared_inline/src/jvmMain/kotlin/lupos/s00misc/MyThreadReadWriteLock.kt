package lupos.s00misc
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import kotlin.jvm.JvmName
@OptIn(kotlin.contracts.ExperimentalContracts::class)
internal actual class MyThreadReadWriteLock {
    internal companion object {
        var uuidCounter = 0L
    }
    val uuid = uuidCounter++
    val lock = ReentrantReadWriteLock()
    @JvmName("getUUID") internal actual inline fun getUUID() = uuid
    @JvmName("downgradeToReadLock") internal actual inline fun downgradeToReadLock() {
        lock.readLock().lock()
        lock.writeLock().unlock()
    }
    @JvmName("readLock") internal actual inline fun readLock() {
        lock.readLock().lock()
    }
    @JvmName("readUnlock") internal actual inline fun readUnlock() {
        lock.readLock().unlock()
    }
    @JvmName("writeLock") internal actual inline fun writeLock() {
        lock.writeLock().lock()
    }
    @JvmName("tryWriteLock") internal actual inline fun tryWriteLock(): Boolean {
        return lock.writeLock().tryLock()
    }
    @JvmName("writeUnlock") internal actual inline fun writeUnlock() {
        lock.writeLock().unlock()
    }
    @JvmName("withReadLock") internal actual inline fun <T> withReadLock(crossinline action: () -> T): T {
        contract { callsInPlace(action, EXACTLY_ONCE) }
        readLock()
        try {
            return action()
        } finally {
            readUnlock()
        }
    }
    @JvmName("withWriteLock") internal actual inline fun <T> withWriteLock(crossinline action: () -> T): T {
        contract { callsInPlace(action, EXACTLY_ONCE) }
        writeLock()
        try {
            return action()
        } finally {
            writeUnlock()
        }
    }
}
