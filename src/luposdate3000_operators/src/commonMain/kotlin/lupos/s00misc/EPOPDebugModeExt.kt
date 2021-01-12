package lupos.s00misc
import kotlin.jvm.JvmField
public object EPOPDebugModeExt {
    public const val DEBUG1: EPOPDebugMode = 0
    public const val DEBUG2: EPOPDebugMode = 1
    public const val NONE: EPOPDebugMode = 2
    @JvmField public val values: IntArray = IntArray(3) { it }
    @JvmField public val names: Array<String> = arrayOf(
        "DEBUG1",
        "DEBUG2",
        "NONE",
    )
}
