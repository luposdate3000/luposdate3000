package lupos.s00misc
import kotlin.jvm.JvmName
internal actual class MyDataOutputStream {
    actual constructor() {}
     internal actual inline fun writeInt(value: Int): Unit = throw NotImplementedException("MyDataInputStream", "xyz not implemented")
     internal actual inline fun write(buf: ByteArray, off: Int, len: Int): Unit = throw NotImplementedException("MyDataInputStream", "xyz not implemented")
     internal actual inline fun close() = throw NotImplementedException("MyDataInputStream", "xyz not implemented")
     internal actual inline fun flush() = throw NotImplementedException("MyDataInputStream", "xyz not implemented")
}
