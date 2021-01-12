package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s00misc.UnreachableException
public object EPOPDebugModeExt {
    public const val DEBUG1 : EPOPDebugMode = 0
    public const val DEBUG2 : EPOPDebugMode = 1
    public const val NONE : EPOPDebugMode = 2
    @JvmField public val values = IntArray(3){it}
    @JvmField public val names = arrayOf(
        "DEBUG1",
        "DEBUG2",
        "NONE",
    )
}
