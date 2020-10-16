package lupos.s00misc
import lupos.s00misc.NotImplementedException
class MyLock {
companion object{
var uuidCounter=0
}
    
    val uuid = uuidCounter++

    inline fun lock() {
throw object:NotImplementedException("MyLock","lock not implemented"){}
    }

    inline fun unlock() {
throw object:NotImplementedException("MyLock","unlock not implemented"){}
    }

    inline fun tryLock(): Boolean {
throw object:NotImplementedException("MyLock","trylock not implemented"){}
    }
}
