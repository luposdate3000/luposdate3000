package lupos.s00misc

import java.util.concurrent.Semaphore

internal actual class MyThreadLock {
    internal companion object {
        var uuidCounter = 0L
    }

    val uuid = uuidCounter++
    val semaphore = Semaphore(1)
    actual inline fun getUUID() = uuid
    actual inline fun lock() {
        semaphore.acquire()
    }

    actual inline fun unlock() {
        semaphore.release()
    }

    actual inline fun tryLock(): Boolean {
        return semaphore.tryAcquire()
    }
actual inline fun <T> withLock(crossinline action: () -> T): T {
    lock()
    try {
        return action()
    } finally {
        unlock()
    }
} 
}
