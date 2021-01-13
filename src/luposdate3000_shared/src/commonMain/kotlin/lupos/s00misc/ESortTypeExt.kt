package lupos.s00misc
import kotlin.jvm.JvmField
public object ESortTypeExt {
    public const val ASC: ESortType = 0
    public const val DESC: ESortType = 1
    public const val FAST: ESortType = 2
    public const val values_size: Int = 3
    @JvmField public val names: Array<String> = arrayOf(
        "ASC",
        "DESC",
        "FAST",
    )
}
