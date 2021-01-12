package lupos.s00misc
import kotlin.jvm.JvmField
public object MyPrintWriterModeExt {
    public const val BUFFER: MyPrintWriterMode = 0
    public const val FILE: MyPrintWriterMode = 1
    public const val NONE: MyPrintWriterMode = 2
    @JvmField public val values: IntArray = IntArray(3) { it }
    @JvmField public val names: Array<String> = arrayOf(
        "BUFFER",
        "FILE",
        "NONE",
    )
}
