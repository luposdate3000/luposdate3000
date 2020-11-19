package lupos.s00misc

import java.io.DataInputStream

internal actual class MyDataInputStream(@JvmField val it: DataInputStream) {
    actual inline fun readInt(): Int = it.readInt()
    actual inline fun readByte(): Byte = it.readByte()
    actual inline fun read(buf: ByteArray, off: Int, len: Int): Int = it.read(buf, off, len)
}
