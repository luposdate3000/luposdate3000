package lupos.s00misc

import lupos.s00misc.NotImplementedException

internal actual class MyThreadLock {
    internal companion object {
        var uuidCounter = 0L
    }

    val uuid = uuidCounter++
    actual inline fun getUUID() = uuid
    actual inline fun lock() {
        throw  NotImplementedException("MyThreadLock", "lock not implemented")
    }

    actual inline fun unlock() {
        throw  NotImplementedException("MyThreadLock", "unlock not implemented")
    }

    actual inline fun tryLock(): Boolean {
        throw  NotImplementedException("MyThreadLock", "trylock not implemented")
    }
}
