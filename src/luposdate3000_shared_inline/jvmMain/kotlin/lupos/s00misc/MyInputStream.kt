package lupos.s00misc

import java.io.InputStream
import kotlin.jvm.JvmField
import lupos.s00misc.IMyInputStream

internal class MyInputStream(@JvmField val stream: InputStream) : IMyInputStream {
    override fun read(buf: ByteArray): Int {
        return stream.read(buf, 0, buf.size)
    }
}
