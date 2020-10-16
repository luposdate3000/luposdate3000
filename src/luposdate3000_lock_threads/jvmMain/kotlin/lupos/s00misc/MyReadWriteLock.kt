package lupos.s00misc

import java.util.concurrent.locks.ReentrantReadWriteLock

actual class MyReadWriteLock {
    companion object {
        var uuidCounter = 0L
    }

    @JvmField
    val uuid = uuidCounter++

    @JvmField
    val lock = ReentrantReadWriteLock()
    inline fun downgradeToReadLock() {
        lock.readLock().lock()
        lock.writeLock().unlock()
    }

    inline fun readLock() {
        lock.readLock().lock()
    }

    inline fun readUnlock() {
        lock.readLock().unlock()
    }

    inline fun writeLock() {
        lock.writeLock().lock()
    }

    inline fun tryWriteLock(): Boolean {
        return lock.writeLock().tryLock()
    }

    inline fun writeUnlock() {
        lock.writeLock().unlock()
    }
}
