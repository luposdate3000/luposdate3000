package lupos.s04logicalOperators.iterator
import kotlin.jvm.JvmField
internal object IteratorBundleModeExt {
    internal const val COLUMN: IteratorBundleMode = 0
    internal const val COUNT: IteratorBundleMode = 1
    internal const val ROW: IteratorBundleMode = 2
    @JvmField internal val values: IntArray = IntArray(3) { it }
    @JvmField internal val names: Array<String> = arrayOf(
        "COLUMN",
        "COUNT",
        "ROW",
    )
}
