package lupos.s00misc
internal actual class File {
    val filename: String
    actual constructor(filename: String) {
        this.filename = filename
    }
    actual inline fun createTempFile(prefix: String, suffix: String, directory: String): String = throw NotImplementedException("File", "createTempFile not implemented")
    actual inline fun exists(): Boolean = throw NotImplementedException("File", "exists not implemented")
    actual inline fun mkdirs(): Boolean = throw NotImplementedException("File", "mkdirs not implemented")
    actual inline fun deleteRecursively(): Boolean = throw NotImplementedException("File", "deleteRecursively not implemented")
    actual inline fun length(): Long = throw NotImplementedException("File", "length not implemented")
    actual inline fun readAsString(): String = throw NotImplementedException("File", "readAsString not implemented")
    actual inline fun readAsCharIterator(): CharIterator = throw NotImplementedException("File", "readAsCharIterator not implemented")
    actual inline fun readAsInputStream(): IMyInputStream = throw NotImplementedException("File", "readAsInputStream not implemented")
    actual inline fun walk(crossinline action: (String) -> Unit): Unit = throw NotImplementedException("File", "walk not implemented")
    actual inline fun myPrintWriter(): MyPrintWriter = throw NotImplementedException("File", "myPrintWriter not implemented")
    actual inline fun printWriter(crossinline action: (MyPrintWriter) -> Unit): Unit = throw NotImplementedException("File", "printWriter not implemented")
    actual /*suspend*/ inline fun printWriterSuspended(crossinline action: /*suspend*/ (MyPrintWriter) -> Unit): Unit = throw NotImplementedException("File", "printWriterSuspended not implemented")
    actual inline fun forEachLine(crossinline action: (String) -> Unit): Unit = throw NotImplementedException("File", "forEachLine not implemented")
    actual /*suspend*/ inline fun forEachLineSuspended(crossinline action: /*suspend*/ (String) -> Unit): Unit = throw NotImplementedException("File", "forEachLineSuspended not implemented")
    actual inline fun dataOutputStream(crossinline action: (MyDataOutputStream) -> Unit): Unit = throw NotImplementedException("File", "dataOutputStream not implemented")
    actual inline fun dataOutputStreamSuspend(crossinline action: (MyDataOutputStream) -> Unit): Unit = throw NotImplementedException("File", "dataOutputStreamSuspend not implemented")
    actual inline fun dataInputStream(crossinline action: (MyDataInputStream) -> Unit): Unit = throw NotImplementedException("File", "dataInputStream not implemented")
    /*suspend*/ actual inline fun dataInputStreamSuspended(crossinline action: /*suspend*/ (MyDataInputStream) -> Unit): Unit = throw NotImplementedException("File", "dataInputStreamSuspended not implemented")
    actual override fun equals(other: Any?): Boolean = throw NotImplementedException("File", "equals not implemented")
}
