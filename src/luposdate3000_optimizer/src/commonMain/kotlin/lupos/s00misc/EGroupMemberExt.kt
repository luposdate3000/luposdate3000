package lupos.s00misc
import kotlin.jvm.JvmField
public object EGroupMemberExt {
    public const val GMLOPDataSource: EGroupMember = 0
    public const val GMLOPFilter: EGroupMember = 1
    public const val GMLOPMinus: EGroupMember = 2
    public const val GMLOPOptional: EGroupMember = 3
    public const val values_size: Int = 4
    @JvmField public val names: Array<String> = arrayOf(
        "GMLOPDataSource",
        "GMLOPFilter",
        "GMLOPMinus",
        "GMLOPOptional",
    )
}
