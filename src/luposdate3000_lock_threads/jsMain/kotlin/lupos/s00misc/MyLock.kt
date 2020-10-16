package lupos.s00misc

import lupos.s00misc.NotImplementedException

actual class MyLock {
internal    companion object {
        var uuidCounter = 0L
    }

    val uuid = uuidCounter++
actual fun getUUID()=uuid
actual    fun lock() {
        throw object : NotImplementedException("MyLock", "lock not implemented") {}
    }

actual    fun unlock() {
        throw object : NotImplementedException("MyLock", "unlock not implemented") {}
    }

actual    fun tryLock(): Boolean {
        throw object : NotImplementedException("MyLock", "trylock not implemented") {}
    }
}
