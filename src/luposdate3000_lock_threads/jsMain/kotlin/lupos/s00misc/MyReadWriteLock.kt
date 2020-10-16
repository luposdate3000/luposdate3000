package lupos.s00misc
import lupos.s00misc.NotImplementedException

class MyReadWriteLock {
companion object{
var uuidCounter=0
}

    
    val uuid = uuidCounter++

    inline  fun downgradeToReadLock() {
throw NotImplementedException("MyReadWriteLock","downgradeToReadLock not implemented")
    }

    inline  fun readLock() {
throw NotImplementedException("MyReadWriteLock","readLock not implemented")
    }

    inline fun readUnlock() {
throw NotImplementedException("MyReadWriteLock","readUnlock not implemented")
    }

    inline  fun writeLock() {
throw NotImplementedException("MyReadWriteLock","writeLock not implemented")
    }

    inline  fun tryWriteLock(): Boolean {
throw NotImplementedException("MyReadWriteLock","tryWriteLock not implemented")
    }

    inline fun writeUnlock() {
throw NotImplementedException("MyReadWriteLock","writeUnlock not implemented")
    }
}
