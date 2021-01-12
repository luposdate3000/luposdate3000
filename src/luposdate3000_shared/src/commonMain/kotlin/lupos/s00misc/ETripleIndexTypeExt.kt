package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s00misc.UnreachableException
public object ETripleIndexTypeExt {
    public const val ID_TRIPLE : ETripleIndexType = 0
    public const val PARTITION : ETripleIndexType = 1
    @JvmField public val values : IntArray = IntArray(2){it}
    @JvmField public val names : Array<String> = arrayOf(
        "ID_TRIPLE",
        "PARTITION",
    )
}
