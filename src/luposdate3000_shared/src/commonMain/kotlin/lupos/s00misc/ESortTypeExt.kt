package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s00misc.UnreachableException
public object ESortTypeExt {
    public const val ASC : ESortType = 0
    public const val DESC : ESortType = 1
    public const val FAST : ESortType = 2
    @JvmField public val values : IntArray = IntArray(3){it}
    @JvmField public val names : Array<String> = arrayOf(
        "ASC",
        "DESC",
        "FAST",
    )
}
