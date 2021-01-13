package lupos.s00misc
import kotlin.jvm.JvmField
public object EModifyTypeExt {
    public const val DELETE: EModifyType = 0
    public const val INSERT: EModifyType = 1
    public const val values_size: Int = 2
    @JvmField public val names: Array<String> = arrayOf(
        "DELETE",
        "INSERT",
    )
}
