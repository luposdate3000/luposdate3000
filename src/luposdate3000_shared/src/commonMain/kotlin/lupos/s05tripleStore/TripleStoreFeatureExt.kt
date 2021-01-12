package lupos.s05tripleStore
import kotlin.jvm.JvmField
import lupos.s00misc.UnreachableException
public object TripleStoreFeatureExt {
    public const val DEFAULT : TripleStoreFeature = 0
    public const val PARTITION : TripleStoreFeature = 1
    @JvmField public val values = IntArray(2){it}
    @JvmField public val names = arrayOf(
        "DEFAULT",
        "PARTITION",
    )
}
