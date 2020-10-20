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
   inline fun myPrintWriter():IPrintWriter
  inline fun printWriter(crossinline action: (IPrintWriter) -> Unit)
 inline suspend fun printWriterSuspended(crossinline action: suspend (IPrintWriter) -> Unit) 
inline fun forEachLine(crossinline action: (String) -> Unit)
 inline suspend fun forEachLineSuspended(crossinline action: suspend (String) -> Unit) 
 inline fun dataOutputStream(crossinline action: (IDataOutputStream) -> Unit)
  inline fun dataOutputStreamSuspend(crossinline action: (IDataOutputStream) -> Unit)
inline fun dataInputStream(crossinline action: (IDataInputStream) -> Unit)
 suspend inline fun dataInputStreamSuspended(crossinline action: suspend (IDataInputStream) -> Unit) 
override fun equals(other: Any?): Boolean
}

internal interface IPrintWriter {
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
}
internal interface IDataOutputStream{

}
internal interface IDataInputStream{

}
