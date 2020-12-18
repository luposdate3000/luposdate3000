package lupos.s00misc
internal actual class MyDataOutputStream {
    actual constructor() {}
    actual inline fun writeInt(value: Int): Unit = throw NotImplementedException("MyDataInputStream", "xyz not implemented")
    actual inline fun write(buf: ByteArray, off: Int, len: Int): Unit = throw NotImplementedException("MyDataInputStream", "xyz not implemented")
    actual inline fun close() = throw NotImplementedException("MyDataInputStream", "xyz not implemented")
    actual inline fun flush() = throw NotImplementedException("MyDataInputStream", "xyz not implemented")
}
