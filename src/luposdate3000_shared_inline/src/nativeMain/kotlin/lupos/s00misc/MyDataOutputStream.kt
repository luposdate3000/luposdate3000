package lupos.s00misc
import kotlin.jvm.JvmName
internal actual class MyDataOutputStream {
    actual constructor() {}
    @JvmName("writeInt") internal actual inline fun writeInt(value: Int): Unit = throw NotImplementedException("MyDataInputStream", "xyz not implemented")
    @JvmName("write") internal actual inline fun write(buf: ByteArray, off: Int, len: Int): Unit = throw NotImplementedException("MyDataInputStream", "xyz not implemented")
    @JvmName("close") internal actual inline fun close() = throw NotImplementedException("MyDataInputStream", "xyz not implemented")
    @JvmName("flush") internal actual inline fun flush() = throw NotImplementedException("MyDataInputStream", "xyz not implemented")
}
