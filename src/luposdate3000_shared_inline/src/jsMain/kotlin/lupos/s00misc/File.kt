package lupos.s00misc
import kotlin.jvm.JvmName
internal actual class File {
    val filename: String
    actual constructor(filename: String) {
        this.filename = filename
    }
    @JvmName("createTempFile") internal actual inline fun createTempFile(prefix: String, suffix: String, directory: String): String = throw NotImplementedException("File", "createTempFile not implemented")
    @JvmName("exists") internal actual inline fun exists(): Boolean = throw NotImplementedException("File", "exists not implemented")
    @JvmName("mkdirs") internal actual inline fun mkdirs(): Boolean = throw NotImplementedException("File", "mkdirs not implemented")
    @JvmName("deleteRecursively") internal actual inline fun deleteRecursively(): Boolean = throw NotImplementedException("File", "deleteRecursively not implemented")
    @JvmName("length") internal actual inline fun length(): Long = throw NotImplementedException("File", "length not implemented")
    @JvmName("readAsString") internal actual inline fun readAsString(): String = throw NotImplementedException("File", "readAsString not implemented")
    @JvmName("readAsCharIterator") internal actual inline fun readAsCharIterator(): CharIterator = throw NotImplementedException("File", "readAsCharIterator not implemented")
    @JvmName("readAsInputStream") internal actual inline fun readAsInputStream(): IMyInputStream = throw NotImplementedException("File", "readAsInputStream not implemented")
    @JvmName("walk") internal actual inline fun walk(crossinline action: (String) -> Unit): Unit = throw NotImplementedException("File", "walk not implemented")
    @JvmName("myPrintWriter") internal actual inline fun myPrintWriter(): MyPrintWriter = throw NotImplementedException("File", "myPrintWriter not implemented")
    @JvmName("printWriter") internal actual inline fun printWriter(crossinline action: (MyPrintWriter) -> Unit): Unit = throw NotImplementedException("File", "printWriter not implemented")
    @JvmName("printWriterSuspended") internal /*suspend*/ actual inline fun printWriterSuspended(crossinline action: /*suspend*/ (MyPrintWriter) -> Unit): Unit = throw NotImplementedException("File", "printWriterSuspended not implemented")
    @JvmName("forEachLine") internal actual inline fun forEachLine(crossinline action: (String) -> Unit): Unit = throw NotImplementedException("File", "forEachLine not implemented")
    @JvmName("forEachLineSuspended") internal /*suspend*/ actual inline fun forEachLineSuspended(crossinline action: /*suspend*/ (String) -> Unit): Unit = throw NotImplementedException("File", "forEachLineSuspended not implemented")
    @JvmName("dataOutputStream") internal actual inline fun dataOutputStream(crossinline action: (MyDataOutputStream) -> Unit): Unit = throw NotImplementedException("File", "dataOutputStream not implemented")
    @JvmName("dataOutputStreamSuspend") internal actual inline fun dataOutputStreamSuspend(crossinline action: (MyDataOutputStream) -> Unit): Unit = throw NotImplementedException("File", "dataOutputStreamSuspend not implemented")
    @JvmName("dataInputStream") internal actual inline fun dataInputStream(crossinline action: (MyDataInputStream) -> Unit): Unit = throw NotImplementedException("File", "dataInputStream not implemented")
    /*suspend*/ @JvmName("dataInputStreamSuspended") internal actual inline fun dataInputStreamSuspended(crossinline action: /*suspend*/ (MyDataInputStream) -> Unit): Unit = throw NotImplementedException("File", "dataInputStreamSuspended not implemented")
    actual override fun equals(other: Any?): Boolean = throw NotImplementedException("File", "equals not implemented")
    @JvmName("openDataOutputStream") internal actual inline fun openDataOutputStream(append: Boolean): MyDataOutputStream = throw NotImplementedException("File", "openDataOutputStream not implemented")
}
