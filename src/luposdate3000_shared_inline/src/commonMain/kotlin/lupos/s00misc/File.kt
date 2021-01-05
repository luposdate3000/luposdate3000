package lupos.s00misc
import kotlin.jvm.JvmName
internal expect class File(filename: String) {
    @JvmName("createTempFile") internal inline fun createTempFile(prefix: String, suffix: String, directory: String): String
    @JvmName("exists") internal inline fun exists(): Boolean
    @JvmName("mkdirs") internal inline fun mkdirs(): Boolean
    @JvmName("deleteRecursively") internal inline fun deleteRecursively(): Boolean
    @JvmName("length") internal inline fun length(): Long
    @JvmName("readAsString") internal inline fun readAsString(): String
    @JvmName("readAsCharIterator") internal inline fun readAsCharIterator(): CharIterator
    @JvmName("readAsInputStream") internal inline fun readAsInputStream(): IMyInputStream
    @JvmName("walk") internal inline fun walk(crossinline action: (String) -> Unit)
    @JvmName("myPrintWriter") internal inline fun myPrintWriter(): MyPrintWriter
    @JvmName("printWriter") internal inline fun printWriter(crossinline action: (MyPrintWriter) -> Unit)
    /*suspend*/ @JvmName("printWriterSuspended") internal inline fun printWriterSuspended(crossinline action: /*suspend*/ (MyPrintWriter) -> Unit)
    @JvmName("forEachLine") internal inline fun forEachLine(crossinline action: (String) -> Unit)
    /*suspend*/ @JvmName("forEachLineSuspended") internal inline fun forEachLineSuspended(crossinline action: /*suspend*/ (String) -> Unit)
    @JvmName("dataOutputStream") internal inline fun dataOutputStream(crossinline action: (MyDataOutputStream) -> Unit)
    @JvmName("openDataOutputStream") internal inline fun openDataOutputStream(append: Boolean): MyDataOutputStream
    @JvmName("dataOutputStreamSuspend") internal inline fun dataOutputStreamSuspend(crossinline action: (MyDataOutputStream) -> Unit)
    @JvmName("dataInputStream") internal inline fun dataInputStream(crossinline action: (MyDataInputStream) -> Unit)
    /*suspend*/ @JvmName("dataInputStreamSuspended") internal inline fun dataInputStreamSuspended(crossinline action: /*suspend*/ (MyDataInputStream) -> Unit)
    override fun equals(other: Any?): Boolean
}
