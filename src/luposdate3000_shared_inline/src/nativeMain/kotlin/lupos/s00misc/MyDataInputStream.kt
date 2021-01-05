package lupos.s00misc
import kotlin.jvm.JvmName
internal actual class MyDataInputStream {
    @JvmName("readByte") internal actual inline fun readByte(): Byte = throw NotImplementedException("MyDataOutputStream", "xyz not implemented")
    @JvmName("readInt") internal actual inline fun readInt(): Int = throw NotImplementedException("MyDataOutputStream", "xyz not implemented")
    @JvmName("read") internal actual inline fun read(buf: ByteArray, off: Int, len: Int): Int = throw NotImplementedException("MyDataOutputStream", "xyz not implemented")
}
