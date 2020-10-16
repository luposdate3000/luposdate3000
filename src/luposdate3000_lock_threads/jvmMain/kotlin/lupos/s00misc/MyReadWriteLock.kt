package lupos.s00misc

import java.util.concurrent.locks.ReentrantReadWriteLock

actual class MyReadWriteLock {
    companion object {
        var uuidCounter = 0L
    }

    val uuid = uuidCounter++

    val lock = ReentrantReadWriteLock()
actual fun getUUID()=uuid
     actual fun downgradeToReadLock() {
        lock.readLock().lock()
        lock.writeLock().unlock()
    }

     actual fun readLock() {
        lock.readLock().lock()
    }

     actual fun readUnlock() {
        lock.readLock().unlock()
    }

     actual fun writeLock() {
        lock.writeLock().lock()
    }

     actual fun tryWriteLock(): Boolean {
        return lock.writeLock().tryLock()
    }

     actual fun writeUnlock() {
        lock.writeLock().unlock()
    }
}
