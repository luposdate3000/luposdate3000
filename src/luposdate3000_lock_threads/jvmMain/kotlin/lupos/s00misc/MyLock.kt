package lupos.s00misc

import java.util.concurrent.Semaphore

actual class MyLock {
    internal companion object {
        var uuidCounter = 0L
    }

    val uuid = uuidCounter++
    val semaphore = Semaphore(1)
    actual fun getUUID() = uuid
    actual fun lock() {
        semaphore.acquire()
    }

    actual fun unlock() {
        semaphore.release()
    }

    actual fun tryLock(): Boolean {
        return semaphore.tryAcquire()
    }
}
