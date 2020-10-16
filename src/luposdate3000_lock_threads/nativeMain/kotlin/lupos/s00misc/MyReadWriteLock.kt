package lupos.s00misc
import lupos.s00misc.NotImplementedException

class MyReadWriteLock {
companion object{
var uuidCounter=0
}

    
    val uuid = uuidCounter++

    inline  fun downgradeToReadLock() {
throw object:NotImplementedException("MyReadWriteLock","downgradeToReadLock not implemented"){}
    }

    inline  fun readLock() {
throw object:NotImplementedException("MyReadWriteLock","readLock not implemented"){}
    }

    inline fun readUnlock() {
throw object:NotImplementedException("MyReadWriteLock","readUnlock not implemented"){}
    }

    inline  fun writeLock() {
throw object:NotImplementedException("MyReadWriteLock","writeLock not implemented"){}
    }

    inline  fun tryWriteLock(): Boolean {
throw object:NotImplementedException("MyReadWriteLock","tryWriteLock not implemented"){}
    }

    inline fun writeUnlock() {
throw object:NotImplementedException("MyReadWriteLock","writeUnlock not implemented"){}
    }
}
