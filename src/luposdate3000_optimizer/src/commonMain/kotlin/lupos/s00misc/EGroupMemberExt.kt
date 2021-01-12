package lupos.s00misc
import kotlin.jvm.JvmField
public object EGroupMemberExt {
    public  val GMLOPDataSource: EGroupMember = EGroupMember(0)
    public  val GMLOPFilter: EGroupMember = EGroupMember(1)
    public  val GMLOPMinus: EGroupMember = EGroupMember(2)
    public  val GMLOPOptional: EGroupMember = EGroupMember(3)
    public const val values_size: Int = 4
    @JvmField public val names: Array<String> = arrayOf(
        "GMLOPDataSource",
        "GMLOPFilter",
        "GMLOPMinus",
        "GMLOPOptional",
    )
}
