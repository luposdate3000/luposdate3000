package lupos.modulename
internal actual class MyDataInputStream {
    internal actual inline fun readByte(): Byte = throw NotImplementedException("MyDataOutputStream", "xyz not implemented")
    internal actual inline fun readInt(): Int = throw NotImplementedException("MyDataOutputStream", "xyz not implemented")
    internal actual inline fun read(buf: ByteArray, off: Int, len: Int): Int = throw NotImplementedException("MyDataOutputStream", "xyz not implemented")
}
