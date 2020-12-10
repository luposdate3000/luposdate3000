package lupos.s00misc
internal expect class MyDataOutputStream {
    inline fun writeInt(value: Int)
    inline fun write(buf: ByteArray, off: Int = 0, len: Int = buf.size)
}
