package lupos.s00misc

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import kotlin.io.createTempFile
import kotlin.jvm.JvmField
import lupos.s00misc.IMyInputStream
import lupos.s00misc.Parallel

internal actual class MyDataInputStream(@JvmField val it: DataInputStream) {
    actual inline fun readInt(): Int = it.readInt()
    actual inline fun readByte(): Byte = it.readByte()
    actual inline fun read(buf: ByteArray, off: Int, len: Int): Int = it.read(buf, off, len)
}
