package lupos.s00misc

import lupos.s00misc.NotImplementedException

actual class MyLock {
    companion object {
        var uuidCounter = 0L
    }

    val uuid = uuidCounter++
    fun lock() {
        throw object : NotImplementedException("MyLock", "lock not implemented") {}
    }

    fun unlock() {
        throw object : NotImplementedException("MyLock", "unlock not implemented") {}
    }

    fun tryLock(): Boolean {
        throw object : NotImplementedException("MyLock", "trylock not implemented") {}
    }
}
