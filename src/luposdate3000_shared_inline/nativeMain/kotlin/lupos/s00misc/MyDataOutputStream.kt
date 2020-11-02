package lupos.s00misc

internal actual class MyDataOutputStream {
    inline actual fun writeInt(value: Int): Unit = throw  NotImplementedException("MyDataInputStream", "xyz not implemented")
    inline actual fun write(buf: ByteArray, off: Int, len: Int): Unit = throw  NotImplementedException("MyDataInputStream", "xyz not implemented")
}
