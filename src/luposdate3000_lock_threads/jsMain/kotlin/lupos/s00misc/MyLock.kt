package lupos.s00misc
import lupos.s00misc.NotImplementedException
class MyLock {
companion object{
var uuidCounter=0
}
    
    val uuid = uuidCounter++

    inline fun lock() {
throw NotImplementedException("MyLock","lock not implemented")
    }

    inline fun unlock() {
throw NotImplementedException("MyLock","unlock not implemented")
    }

    inline fun tryLock(): Boolean {
throw NotImplementedException("MyLock","trylock not implemented")
    }
}
