package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s00misc.UnreachableException
public object ETripleIndexTypeExt {
    public const val ID_TRIPLE : ETripleIndexType = 0
    public const val PARTITION : ETripleIndexType = 1
    @JvmField public val values = IntArray(2){it}
    @JvmField public val names = arrayOf(
        "ID_TRIPLE",
        "PARTITION",
    )
}
