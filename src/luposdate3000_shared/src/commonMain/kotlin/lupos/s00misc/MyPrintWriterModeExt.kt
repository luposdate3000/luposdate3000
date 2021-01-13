package lupos.s00misc
import kotlin.jvm.JvmField
public object MyPrintWriterModeExt {
    public val BUFFER: MyPrintWriterMode = MyPrintWriterMode(0)
    public val FILE: MyPrintWriterMode = MyPrintWriterMode(1)
    public val NONE: MyPrintWriterMode = MyPrintWriterMode(2)
    public const val values_size: Int = 3
    @JvmField public val names: Array<String> = arrayOf(
        "BUFFER",
        "FILE",
        "NONE",
    )
}
