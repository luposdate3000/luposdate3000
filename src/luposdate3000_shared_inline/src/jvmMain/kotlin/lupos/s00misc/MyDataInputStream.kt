package lupos.modulename
import java.io.DataInputStream
import kotlin.jvm.JvmField
internal actual class MyDataInputStream(@JvmField public val it: DataInputStream) {
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun readInt(): Int = it.readInt()
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun readByte(): Byte = it.readByte()
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun read(buf: ByteArray, off: Int, len: Int): Int = it.read(buf, off, len)
}
