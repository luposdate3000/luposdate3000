package lupos.s00misc
import lupos.s00misc.Coverage
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
Coverage.forLoopStart(165)
                tmp += bucket.size
            }
/*return*/ tmp
        }()
    constructor(data: Pair<Long, Int>) : this() {
        set(data.first, data.second)
    }
    inline operator fun get(key: Long) = buckets[hashFunction(key)][key]
    inline operator fun set(key: Long, value: Int) {
Coverage.funStart(166)
        buckets[hashFunction(key)][key] = value
Coverage.statementStart(167)
    }
    inline fun getOrCreate(key: Long, crossinline onCreate: () -> Int) = buckets[hashFunction(key)].getOrCreate(key, onCreate)
    fun appendAssumeSorted(key: Long, value: Int) = buckets[hashFunction(key)].appendAssumeSorted(key, value)
    fun clear() {
Coverage.funStart(168)
        for (bucket in buckets) {
Coverage.forLoopStart(169)
            bucket.clear()
Coverage.statementStart(170)
        }
Coverage.statementStart(171)
    }
fun withFastInitializer(action: (MyMapLongIntHash) -> Unit) = action(this)
}
