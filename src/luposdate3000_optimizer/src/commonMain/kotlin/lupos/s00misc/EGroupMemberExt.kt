package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s00misc.UnreachableException
public object EGroupMemberExt {
    public const val GMLOPDataSource : EGroupMember = 0
    public const val GMLOPFilter : EGroupMember = 1
    public const val GMLOPMinus : EGroupMember = 2
    public const val GMLOPOptional : EGroupMember = 3
    @JvmField public val values : IntArray = IntArray(4){it}
    @JvmField public val names : Array<String> = arrayOf(
        "GMLOPDataSource",
        "GMLOPFilter",
        "GMLOPMinus",
        "GMLOPOptional",
    )
}
