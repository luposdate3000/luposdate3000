package lupos.s00misc 

import lupos.s00misc.IMyInputStream
import lupos.s00misc.Parallel


internal actual class File{
 val filename: String
actual constructor(filename: String){
this.filename=filename
}
    inline actual fun createTempFile(prefix: String, suffix: String, directory: String): String = throw  NotImplementedException("File", "createTempFile not implemented")
    inline actual fun exists() :Boolean = throw  NotImplementedException("File", "exists not implemented")
    inline actual fun mkdirs() :Boolean = throw  NotImplementedException("File", "mkdirs not implemented")
    inline actual fun deleteRecursively():Boolean = throw  NotImplementedException("File", "deleteRecursively not implemented")
    inline actual fun length() :Long = throw  NotImplementedException("File", "length not implemented")
    inline actual fun readAsString():String = throw  NotImplementedException("File", "readAsString not implemented")
    inline actual fun readAsCharIterator(): CharIterator  = throw  NotImplementedException("File", "readAsCharIterator not implemented")
    inline actual fun readAsInputStream(): IMyInputStream = throw  NotImplementedException("File", "readAsInputStream not implemented")
    inline actual fun walk(crossinline action: (String) -> Unit) :Unit = throw  NotImplementedException("File", "walk not implemented")
    inline actual fun myPrintWriter() :MyPrintWriter = throw  NotImplementedException("File", "myPrintWriter not implemented")
    inline actual fun printWriter(crossinline action: (MyPrintWriter) -> Unit) :Unit = throw  NotImplementedException("File", "printWriter not implemented")
    inline suspend actual fun printWriterSuspended(crossinline action: suspend (MyPrintWriter) -> Unit) :Unit = throw  NotImplementedException("File", "printWriterSuspended not implemented")
    inline actual fun forEachLine(crossinline action: (String) -> Unit) :Unit = throw  NotImplementedException("File", "forEachLine not implemented")
    inline suspend actual fun forEachLineSuspended(crossinline action: suspend (String) -> Unit) :Unit = throw  NotImplementedException("File", "forEachLineSuspended not implemented")
    inline actual fun dataOutputStream(crossinline action: (MyDataOutputStream) -> Unit) :Unit = throw  NotImplementedException("File", "dataOutputStream not implemented")
    inline actual fun dataOutputStreamSuspend(crossinline action: (MyDataOutputStream) -> Unit):Unit = throw  NotImplementedException("File", "dataOutputStreamSuspend not implemented")
    inline actual fun dataInputStream(crossinline action: (MyDataInputStream) -> Unit) :Unit = throw  NotImplementedException("File", "dataInputStream not implemented")
    suspend inline actual fun dataInputStreamSuspended(crossinline action: suspend (MyDataInputStream) -> Unit) :Unit = throw  NotImplementedException("File", "dataInputStreamSuspended not implemented")
    override actual fun equals(other: Any?): Boolean  = throw  NotImplementedException("File", "equals not implemented")
}
internal actual class MyPrintWriter {
    actual fun clearBuffer():Unit= throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    override actual fun toString(): String= throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun println(x: String):Unit= throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun print(x: String):Unit= throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun println(x: Boolean):Unit= throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun print(x: Boolean):Unit= throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun println(x: Int):Unit= throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun print(x: Int):Unit= throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun println(x: Double):Unit= throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun print(x: Double):Unit= throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun println():Unit= throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun close():Unit= throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun flush():Unit= throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
}
internal actual class MyDataInputStream{
inline actual fun readInt():Int= throw  NotImplementedException("MyDataOutputStream", "xyz not implemented")
inline actual fun read(buf:ByteArray,off:Int,len:Int):Int= throw  NotImplementedException("MyDataOutputStream", "xyz not implemented")
}
internal actual class MyDataOutputStream{
inline actual fun writeInt(value:Int):Unit= throw  NotImplementedException("MyDataInputStream", "xyz not implemented")
inline actual fun write(buf:ByteArray,off:Int,len:Int):Unit= throw  NotImplementedException("MyDataInputStream", "xyz not implemented")
}
