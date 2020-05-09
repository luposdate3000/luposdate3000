package lupos.s00misc

class MyMapLongIntHash() {
    val bucket_shift = 16
    val bucket_count = 2 shl bucket_shift
    val bucket_mask = bucket_count - 1
    var buckets = Array(bucket_count) { MyMapLongIntBinaryTree() }
    fun hashFunction(key: Long): Int = (key.toInt() + (key shr 32).toInt()) and bucket_mask
    var size: Int = 0
        get() = {
            var tmp = 0
            for (bucket in buckets) {
                tmp += bucket.size
            }
/*return*/ tmp
        }()

    constructor(data: Pair<Long, Int>) : this() {
        set(data.first, data.second)
    }

    inline operator fun get(key: Long) = buckets[hashFunction(key)][key]
    inline operator fun set(key: Long, value: Int) {
        buckets[hashFunction(key)][key] = value
    }

    inline fun getOrCreate(key: Long, crossinline onCreate: () -> Int) = buckets[hashFunction(key)].getOrCreate(key, onCreate)
    fun appendAssumeSorted(key: Long, value: Int) = buckets[hashFunction(key)].appendAssumeSorted(key, value)
    fun clear() {
        for (bucket in buckets) {
            bucket.clear()
        }
    }
//    inline fun iterator() = throw Exception("hash map does not have ordered iterator")
}
