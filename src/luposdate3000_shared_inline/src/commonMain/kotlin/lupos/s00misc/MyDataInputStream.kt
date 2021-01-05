package lupos.s00misc
import kotlin.jvm.JvmName
internal expect class MyDataInputStream {
    @JvmName("readInt") internal inline fun readInt(): Int
    @JvmName("readByte") internal inline fun readByte(): Byte
    @JvmName("read") internal inline fun read(buf: ByteArray, off: Int = 0, len: Int = buf.size): Int
}
