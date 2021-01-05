package lupos.s00misc
import kotlin.jvm.JvmName
internal expect class MyDataOutputStream {
    constructor()
    @JvmName("writeInt") internal inline fun writeInt(value: Int)
    @JvmName("write") internal inline fun write(buf: ByteArray, off: Int = 0, len: Int = buf.size)
    @JvmName("close") internal inline fun close()
    @JvmName("flush") internal inline fun flush()
}
