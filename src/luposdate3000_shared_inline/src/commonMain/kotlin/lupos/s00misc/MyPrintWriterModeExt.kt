package lupos.modulename
import kotlin.jvm.JvmField
import lupos.s00misc.UnreachableException
internal object MyPrintWriterModeExt {
    internal const val BUFFER : MyPrintWriterMode = 0
    internal const val FILE : MyPrintWriterMode = 1
    internal const val NONE : MyPrintWriterMode = 2
    @JvmField internal val values = IntArray(3){it}
    @JvmField internal val names = arrayOf(
        "BUFFER",
        "FILE",
        "NONE",
    )
}
