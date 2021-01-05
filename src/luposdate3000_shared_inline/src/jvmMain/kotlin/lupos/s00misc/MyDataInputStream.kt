package lupos.s00misc
import java.io.DataInputStream
import kotlin.jvm.JvmName
internal actual class MyDataInputStream(@JvmField val it: DataInputStream) {
     internal actual inline fun readInt(): Int = it.readInt()
     internal actual inline fun readByte(): Byte = it.readByte()
     internal actual inline fun read(buf: ByteArray, off: Int, len: Int): Int = it.read(buf, off, len)
}
