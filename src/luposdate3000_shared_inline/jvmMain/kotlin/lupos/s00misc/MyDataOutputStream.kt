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

internal actual class MyDataOutputStream(@JvmField val it: DataOutputStream) {
    actual inline fun writeInt(value: Int) = it.writeInt(value)
    actual inline fun write(buf: ByteArray, off: Int, len: Int) = it.write(buf, off, len)
}
