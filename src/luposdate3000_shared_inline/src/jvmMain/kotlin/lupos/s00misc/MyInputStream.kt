package lupos.s00misc
import java.io.InputStream
internal class MyInputStream(@JvmField public val stream: InputStream) : IMyInputStream {
    override fun read(buf: ByteArray): Int {
        return stream.read(buf, 0, buf.size)
    }
}
