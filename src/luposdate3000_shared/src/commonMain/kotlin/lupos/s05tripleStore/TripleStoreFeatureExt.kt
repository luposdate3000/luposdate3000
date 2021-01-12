package lupos.s05tripleStore
import kotlin.jvm.JvmField
public object TripleStoreFeatureExt {
    public  val DEFAULT: TripleStoreFeature = TripleStoreFeature(0)
    public  val PARTITION: TripleStoreFeature = TripleStoreFeature(1)
    public const val values_size: Int = 2
    @JvmField public val names: Array<String> = arrayOf(
        "DEFAULT",
        "PARTITION",
    )
}
