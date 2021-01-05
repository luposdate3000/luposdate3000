package lupos.s00misc
import kotlin.jvm.JvmName
internal actual class File {
    val filename: String
    actual constructor(filename: String) {
        this.filename = filename
    }
     internal actual inline fun createTempFile(prefix: String, suffix: String, directory: String): String = throw NotImplementedException("File", "createTempFile not implemented")
     internal actual inline fun exists(): Boolean = throw NotImplementedException("File", "exists not implemented")
     internal actual inline fun mkdirs(): Boolean = throw NotImplementedException("File", "mkdirs not implemented")
     internal actual inline fun deleteRecursively(): Boolean = throw NotImplementedException("File", "deleteRecursively not implemented")
     internal actual inline fun length(): Long = throw NotImplementedException("File", "length not implemented")
     internal actual inline fun readAsString(): String = throw NotImplementedException("File", "readAsString not implemented")
     internal actual inline fun readAsCharIterator(): CharIterator = throw NotImplementedException("File", "readAsCharIterator not implemented")
     internal actual inline fun readAsInputStream(): IMyInputStream = throw NotImplementedException("File", "readAsInputStream not implemented")
     internal actual inline fun walk(crossinline action: (String) -> Unit): Unit = throw NotImplementedException("File", "walk not implemented")
     internal actual inline fun myPrintWriter(): MyPrintWriter = throw NotImplementedException("File", "myPrintWriter not implemented")
     internal actual inline fun printWriter(crossinline action: (MyPrintWriter) -> Unit): Unit = throw NotImplementedException("File", "printWriter not implemented")
     internal /*suspend*/ actual inline fun printWriterSuspended(crossinline action: /*suspend*/ (MyPrintWriter) -> Unit): Unit = throw NotImplementedException("File", "printWriterSuspended not implemented")
     internal actual inline fun forEachLine(crossinline action: (String) -> Unit): Unit = throw NotImplementedException("File", "forEachLine not implemented")
     internal /*suspend*/ actual inline fun forEachLineSuspended(crossinline action: /*suspend*/ (String) -> Unit): Unit = throw NotImplementedException("File", "forEachLineSuspended not implemented")
     internal actual inline fun dataOutputStream(crossinline action: (MyDataOutputStream) -> Unit): Unit = throw NotImplementedException("File", "dataOutputStream not implemented")
     internal actual inline fun dataOutputStreamSuspend(crossinline action: (MyDataOutputStream) -> Unit): Unit = throw NotImplementedException("File", "dataOutputStreamSuspend not implemented")
     internal actual inline fun dataInputStream(crossinline action: (MyDataInputStream) -> Unit): Unit = throw NotImplementedException("File", "dataInputStream not implemented")
    /*suspend*/  internal actual inline fun dataInputStreamSuspended(crossinline action: /*suspend*/ (MyDataInputStream) -> Unit): Unit = throw NotImplementedException("File", "dataInputStreamSuspended not implemented")
    actual override fun equals(other: Any?): Boolean = throw NotImplementedException("File", "equals not implemented")
     internal actual inline fun openDataOutputStream(append: Boolean): MyDataOutputStream = throw NotImplementedException("File", "openDataOutputStream not implemented")
}
