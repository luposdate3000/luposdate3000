package lupos.s00misc

import java.util.concurrent.locks.ReentrantReadWriteLock

internal actual class MyThreadReadWriteLock {
    internal companion object {
        var uuidCounter = 0L
    }

    val uuid = uuidCounter++
    val lock = ReentrantReadWriteLock()
    actual inline fun getUUID() = uuid
    actual inline fun downgradeToReadLock() {
        lock.readLock().lock()
        lock.writeLock().unlock()
    }

    actual inline fun readLock() {
        lock.readLock().lock()
    }

    actual inline fun readUnlock() {
        lock.readLock().unlock()
    }

    actual inline fun writeLock() {
        lock.writeLock().lock()
    }

    actual inline fun tryWriteLock(): Boolean {
        return lock.writeLock().tryLock()
    }

    actual inline fun writeUnlock() {
        lock.writeLock().unlock()
    }
}
