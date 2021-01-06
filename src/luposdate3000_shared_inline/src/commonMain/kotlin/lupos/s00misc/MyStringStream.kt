package lupos.modulename
import lupos.s00misc.IMyInputStream
import kotlin.jvm.JvmField
internal class _MyStringStream(str: String) : IMyInputStream {
    @JvmField public val data = str.encodeToByteArray()
    @JvmField public var off = 0
    override fun read(buf: ByteArray): Int {
        var len = off + buf.size
        var res = buf.size
        if (len> data.size) {
            len = data.size
            res = len - off
        }
        data.copyInto(buf, 0, off, len)
        off = len
        return res
    }
}
