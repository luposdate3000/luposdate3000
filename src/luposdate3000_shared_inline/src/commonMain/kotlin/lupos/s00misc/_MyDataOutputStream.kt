package lupos.modulename
internal expect class _MyDataOutputStream {
    internal constructor()
    internal inline fun writeInt(value: Int): Unit
    internal inline fun write(buf: ByteArray, off: Int = 0, len: Int = buf.size): Unit
    internal inline fun close(): Unit
    internal inline fun flush(): Unit
}
