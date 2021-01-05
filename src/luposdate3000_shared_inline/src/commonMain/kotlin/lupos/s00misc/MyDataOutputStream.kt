package lupos.s00misc
internal expect class MyDataOutputStream {
    public constructor()
    internal inline fun writeInt(value: Int)
    internal inline fun write(buf: ByteArray, off: Int = 0, len: Int = buf.size)
    internal inline fun close()
    internal inline fun flush()
}
