package lupos.s00misc

internal expect class File{
constructor(filename: String)
 inline fun createTempFile(prefix: String, suffix: String, directory: String): String
inline fun exists() :Boolean
    inline fun mkdirs() :Boolean
    inline fun deleteRecursively() :Boolean
    inline fun length() :Long
    inline fun readAsString() :String
    inline fun readAsCharIterator(): CharIterator 
    inline fun readAsInputStream(): IMyInputStream 
    inline fun walk(crossinline action: (String) -> Unit) 
   inline fun myPrintWriter():MyPrintWriter
  inline fun printWriter(crossinline action: (MyPrintWriter) -> Unit)
 inline suspend fun printWriterSuspended(crossinline action: suspend (MyPrintWriter) -> Unit) 
inline fun forEachLine(crossinline action: (String) -> Unit)
 inline suspend fun forEachLineSuspended(crossinline action: suspend (String) -> Unit) 
 inline fun dataOutputStream(crossinline action: (MyDataOutputStream) -> Unit)
  inline fun dataOutputStreamSuspend(crossinline action: (MyDataOutputStream) -> Unit)
inline fun dataInputStream(crossinline action: (MyDataInputStream) -> Unit)
 suspend inline fun dataInputStreamSuspended(crossinline action: suspend (MyDataInputStream) -> Unit) 
override fun equals(other: Any?): Boolean
}

internal expect class MyPrintWriter {
    fun clearBuffer() 
    override fun toString(): String 
    fun println(x: String) 
    fun print(x: String) 
    fun println(x: Boolean) 
    fun print(x: Boolean) 
    fun println(x: Int) 
    fun print(x: Int) 
    fun println(x: Double) 
    fun print(x: Double) 
    fun println() 
    fun close() 
    fun flush() 
}
internal expect class MyDataInputStream{
inline fun readInt():Int
inline fun readByte():Byte
inline fun read(buf:ByteArray,off:Int=0,len:Int=buf.size):Int
}
internal expect class MyDataOutputStream{
inline fun writeInt(value:Int)
inline fun write(buf:ByteArray,off:Int=0,len:Int=buf.size)
}
