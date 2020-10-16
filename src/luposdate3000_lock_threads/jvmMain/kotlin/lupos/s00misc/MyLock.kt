package lupos.s00misc
import java.util.concurrent.Semaphore

class MyLock {
companion object{
var uuidCounter=0
}
    @JvmField
    val uuid = uuidCounter++

@JvmField
    val semaphore = Semaphore(1)
    inline fun lock() {
        semaphore.acquire()
    }

    inline fun unlock() {
        semaphore.release()
    }

    inline fun tryLock(): Boolean {
        return semaphore.tryAcquire()
    }
}
