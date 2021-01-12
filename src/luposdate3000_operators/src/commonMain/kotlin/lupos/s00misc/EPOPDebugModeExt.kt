package lupos.s00misc
import kotlin.jvm.JvmField
public object EPOPDebugModeExt {
    public const val DEBUG1: EPOPDebugMode = 0
    public const val DEBUG2: EPOPDebugMode = 1
    public const val NONE: EPOPDebugMode = 2
    public const val values_size: Int = 3
    @JvmField public val names: Array<String> = arrayOf(
        "DEBUG1",
        "DEBUG2",
        "NONE",
    )
}
