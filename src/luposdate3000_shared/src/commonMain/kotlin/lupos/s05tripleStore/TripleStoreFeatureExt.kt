package lupos.s05tripleStore
import kotlin.jvm.JvmField
public object TripleStoreFeatureExt {
    public const val DEFAULT: TripleStoreFeature = 0
    public const val PARTITION: TripleStoreFeature = 1
    @JvmField public val values: IntArray = IntArray(2) { it }
    @JvmField public val names: Array<String> = arrayOf(
        "DEFAULT",
        "PARTITION",
    )
}
