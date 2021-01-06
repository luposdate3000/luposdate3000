package lupos.modulename
internal object _PartitionExt {
    internal inline fun hashFunction(v: Int, k: Int): Int {
        return if (v < 0) {
            (-v) % k
        } else {
            v % k
        }
    }
}
