package lupos.s00misc
internal expect class File(filename: String) {
    inline fun createTempFile(prefix: String, suffix: String, directory: String): String
    inline fun exists(): Boolean
    inline fun mkdirs(): Boolean
    inline fun deleteRecursively(): Boolean
    inline fun length(): Long
    inline fun readAsString(): String
    inline fun readAsCharIterator(): CharIterator
    inline fun readAsInputStream(): IMyInputStream
    inline fun walk(crossinline action: (String) -> Unit)
    inline fun myPrintWriter(): MyPrintWriter
    inline fun printWriter(crossinline action: (MyPrintWriter) -> Unit)
    /*suspend*/ inline fun printWriterSuspended(crossinline action: /*suspend*/ (MyPrintWriter) -> Unit)
    inline fun forEachLine(crossinline action: (String) -> Unit)
    /*suspend*/ inline fun forEachLineSuspended(crossinline action: /*suspend*/ (String) -> Unit)
    inline fun dataOutputStream(crossinline action: (MyDataOutputStream) -> Unit)
    inline fun openDataOutputStream(append: Boolean): MyDataOutputStream
    inline fun dataOutputStreamSuspend(crossinline action: (MyDataOutputStream) -> Unit)
    inline fun dataInputStream(crossinline action: (MyDataInputStream) -> Unit)
    /*suspend*/ inline fun dataInputStreamSuspended(crossinline action: /*suspend*/ (MyDataInputStream) -> Unit)
    override fun equals(other: Any?): Boolean
}
