package lupos.s00misc
internal expect class MyDataInputStream {
    inline fun readInt(): Int
    inline fun readByte(): Byte
    inline fun read(buf: ByteArray, off: Int = 0, len: Int = buf.size): Int
}
