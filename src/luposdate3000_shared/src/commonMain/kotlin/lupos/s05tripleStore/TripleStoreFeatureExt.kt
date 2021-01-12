package lupos.s05tripleStore
import kotlin.jvm.JvmField
public object TripleStoreFeatureExt {
    public const val DEFAULT: TripleStoreFeature = 0
    public const val PARTITION: TripleStoreFeature = 1
    public const val values_size: Int = 2
    @JvmField public val names: Array<String> = arrayOf(
        "DEFAULT",
        "PARTITION",
    )
}
