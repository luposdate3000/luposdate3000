package lupos.modulename
import lupos.s00misc.NotImplementedException
internal actual class MyDataInputStream {
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun readInt(): Int = throw NotImplementedException("MyDataOutputStream", "xyz not implemented")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun readByte(): Byte = throw NotImplementedException("MyDataOutputStream", "xyz not implemented")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun read(buf: ByteArray, off: Int, len: Int): Int = throw NotImplementedException("MyDataOutputStream", "xyz not implemented")
}
