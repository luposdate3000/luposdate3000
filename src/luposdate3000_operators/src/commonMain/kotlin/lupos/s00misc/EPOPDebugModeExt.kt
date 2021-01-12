package lupos.s00misc
import kotlin.jvm.JvmField
public object EPOPDebugModeExt {
    public  val DEBUG1: EPOPDebugMode = EPOPDebugMode(0)
    public  val DEBUG2: EPOPDebugMode = EPOPDebugMode(1)
    public  val NONE: EPOPDebugMode = EPOPDebugMode(2)
    public const val values_size: Int = 3
    @JvmField public val names: Array<String> = arrayOf(
        "DEBUG1",
        "DEBUG2",
        "NONE",
    )
}
