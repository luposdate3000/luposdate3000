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

internal class MyInputStream(@JvmField val stream: InputStream) : IMyInputStream {
    override fun read(buf: ByteArray): Int {
        return stream.read(buf, 0, buf.size)
    }
}
