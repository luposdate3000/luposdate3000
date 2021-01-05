package lupos.s00misc
import kotlin.jvm.JvmName
internal object PartitionExt {
    @JvmName("hashFunction") internal inline fun hashFunction(v: Int, k: Int): Int {
        return if (v < 0) {
            (-v) % k
        } else {
            v % k
        }
    }
}
