package lupos.s00misc
import kotlin.jvm.JvmField
abstract class ClassCacheManager<T> {
    @JvmField
    var cache = Array<Any?>(100) { null }
    @JvmField
    var cacheLimit = cache.size - 1
    @JvmField
    var cachePointer = -1
    abstract fun allocNew(): T
    internal inline fun alloc(): T {
        var res: T
        if (cachePointer < 0) {
            res = allocNew()
        } else {
            res = cache[cachePointer--] as T
        }
        return res
    }
    internal inline fun release(obj: T) {
        if (cachePointer < cacheLimit) {
            cache[1 + cachePointer++] = obj
        }
    }
}
