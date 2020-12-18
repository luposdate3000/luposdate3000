package lupos.s00misc
internal expect class MyDataOutputStream {
    constructor()
    inline fun writeInt(value: Int)
    inline fun write(buf: ByteArray, off: Int = 0, len: Int = buf.size)
    inline fun close()
    inline fun flush()
}
