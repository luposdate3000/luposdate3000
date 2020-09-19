package lupos.s00misc
abstract class ClassCacheManager<T> {
    @JvmField
    var cache = Array<Any?>(100) { null }
    @JvmField
    var cacheLimit = cache.size - 1
    @JvmField
    var cachePointer = -1
    @JvmField
    val cacheLock = Lock()
    abstract fun allocNew(): T
    inline fun alloc(): T {
        var res: T
        if (cachePointer < 0) {
            res = allocNew()
        } else {
            cacheLock.lock()
            if (cachePointer < 0) {
                res = allocNew()
            } else {
                res = cache[cachePointer--]as T
            }
            cacheLock.unlock()
        }
        return res
    }
    inline fun release(obj: T) {
        if (cachePointer < cacheLimit) {
            cacheLock.lock()
            if (cachePointer < cacheLimit) {
                cache[1 + cachePointer++] = obj
            }
            cacheLock.unlock()
        }
    }
}
