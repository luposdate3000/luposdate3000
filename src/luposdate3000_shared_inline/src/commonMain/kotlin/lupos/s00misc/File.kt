package lupos.modulename
import lupos.s00misc.IMyInputStream
import lupos.s00misc.MyDataOutputStream
import lupos.s00misc.MyPrintWriter
internal expect class _File(filename: String) {
    internal inline fun createTempFile(prefix: String, suffix: String, directory: String): String
    internal inline fun exists(): Boolean
    internal inline fun mkdirs(): Boolean
    internal inline fun deleteRecursively(): Boolean
    internal inline fun length(): Long
    internal inline fun readAsString(): String
    internal inline fun readAsCharIterator(): CharIterator
    internal inline fun readAsInputStream(): IMyInputStream
    internal inline fun walk(crossinline action: (String) -> Unit)
    internal inline fun myPrintWriter(): MyPrintWriter
    internal inline fun printWriter(crossinline action: (MyPrintWriter) -> Unit)
    /*suspend*/ internal inline fun printWriterSuspended(crossinline action: /*suspend*/ (MyPrintWriter) -> Unit)
    internal inline fun forEachLine(crossinline action: (String) -> Unit)
    /*suspend*/ internal inline fun forEachLineSuspended(crossinline action: /*suspend*/ (String) -> Unit)
    internal inline fun dataOutputStream(crossinline action: (MyDataOutputStream) -> Unit)
    internal inline fun openDataOutputStream(append: Boolean): MyDataOutputStream
    internal inline fun dataOutputStreamSuspend(crossinline action: (MyDataOutputStream) -> Unit)
    internal inline fun dataInputStream(crossinline action: (MyDataInputStream) -> Unit)
    /*suspend*/ internal inline fun dataInputStreamSuspended(crossinline action: /*suspend*/ (MyDataInputStream) -> Unit)
    override fun equals(other: Any?): Boolean
}
