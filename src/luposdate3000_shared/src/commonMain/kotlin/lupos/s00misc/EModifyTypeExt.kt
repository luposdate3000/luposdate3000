package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s00misc.UnreachableException
public object EModifyTypeExt {
    public const val DELETE : EModifyType = 0
    public const val INSERT : EModifyType = 1
    @JvmField public val values = IntArray(2){it}
    @JvmField public val names = arrayOf(
        "DELETE",
        "INSERT",
    )
}
