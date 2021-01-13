package lupos.s00misc
import kotlin.jvm.JvmField
public object ESortTypeExt {
    public val ASC: ESortType = ESortType(0)
    public val DESC: ESortType = ESortType(1)
    public val FAST: ESortType = ESortType(2)
    public const val values_size: Int = 3
    @JvmField public val names: Array<String> = arrayOf(
        "ASC",
        "DESC",
        "FAST",
    )
}
