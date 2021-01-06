package lupos.modulename
import lupos.s00misc.IMyInputStream
import java.io.InputStream
import kotlin.jvm.JvmField
internal class MyInputStream(@JvmField public val stream: InputStream) : IMyInputStream {
    override fun read(buf: ByteArray): Int {
        return stream.read(buf, 0, buf.size)
    }
}
