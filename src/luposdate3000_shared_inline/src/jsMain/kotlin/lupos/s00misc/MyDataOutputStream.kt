package lupos.s00misc
internal actual class MyDataOutputStream {
 internal   actual constructor() {}
    internal actual inline fun writeInt(value: Int): Unit = throw NotImplementedException("MyDataInputStream", "xyz not implemented")
    internal actual inline fun write(buf: ByteArray, off: Int, len: Int): Unit = throw NotImplementedException("MyDataInputStream", "xyz not implemented")
    internal actual inline fun close() :Unit= throw NotImplementedException("MyDataInputStream", "xyz not implemented")
    internal actual inline fun flush():Unit = throw NotImplementedException("MyDataInputStream", "xyz not implemented")
}
