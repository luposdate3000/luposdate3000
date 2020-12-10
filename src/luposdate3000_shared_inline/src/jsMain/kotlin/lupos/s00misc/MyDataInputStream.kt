package lupos.s00misc

internal actual class MyDataInputStream {
    actual inline fun readInt(): Int = throw NotImplementedException("MyDataOutputStream", "xyz not implemented")
    actual inline fun readByte(): Byte = throw NotImplementedException("MyDataOutputStream", "xyz not implemented")
    actual inline fun read(buf: ByteArray, off: Int, len: Int): Int = throw NotImplementedException("MyDataOutputStream", "xyz not implemented")
}
