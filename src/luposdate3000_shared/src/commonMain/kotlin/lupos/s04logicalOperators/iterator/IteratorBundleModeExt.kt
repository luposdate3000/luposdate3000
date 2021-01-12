package lupos.s04logicalOperators.iterator
import kotlin.jvm.JvmField
internal object IteratorBundleModeExt {
    internal  val COLUMN: IteratorBundleMode = IteratorBundleMode(0)
    internal  val COUNT: IteratorBundleMode = IteratorBundleMode(1)
    internal  val ROW: IteratorBundleMode = IteratorBundleMode(2)
    internal const val values_size: Int = 3
    @JvmField internal val names: Array<String> = arrayOf(
        "COLUMN",
        "COUNT",
        "ROW",
    )
}
