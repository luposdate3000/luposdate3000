package lupos.s00misc
import kotlin.jvm.JvmName
internal expect class MyDataInputStream {
     internal inline fun readInt(): Int
     internal inline fun readByte(): Byte
     internal inline fun read(buf: ByteArray, off: Int = 0, len: Int = buf.size): Int
}
