package lupos.s00misc
import kotlin.jvm.JvmField
@OptIn(ExperimentalStdlibApi::class)
internal class ByteArrayRead(@JvmField public val data: ByteArray, @JvmField public val size: Int) {
    @JvmField
    var uuid = debuguuidtmp++
    internal companion object {
        @JvmField
        var debuguuidtmp = 0L
    }
    @JvmField
    var offset = 0
    internal inline fun remaining() = size - offset
    init {
        SanityCheck.println { "ByteArrayRead($uuid).init with $size Bytes" }
    }
    internal inline fun readInt(): Int {
        val res = ByteArrayHelper.readInt4(data, offset)
        SanityCheck.println { "ByteArrayRead($uuid).readInt at $offset with value $res" }
        offset += 4
        return res
    }
    internal inline fun readByte(): Byte {
        val res = ByteArrayHelper.readInt1(data, offset)
        SanityCheck.println { "ByteArrayRead($uuid).readByte at $offset with value $res" }
        offset += 1
        return res.toByte()
    }
    internal inline fun readChar(): Char {
        val res = ByteArrayHelper.readChar(data, offset)
        SanityCheck.println { "ByteArrayRead($uuid).readChar at $offset with value '$res' ${res.toInt()}" }
        offset += 2
        return res
    }
    internal inline fun readLong(): Long {
        val res = ByteArrayHelper.readLong8(data, offset)
        SanityCheck.println { "ByteArrayRead($uuid).readLong at $offset with value $res" }
        offset += 8
        return res
    }
    internal inline fun readString(): String {
        val len = readInt()
        val d = CharArray(len)
        for (i in 0 until len) {
            d[i] = readChar()
        }
        val res = d.concatToString()
        SanityCheck.println { "ByteArrayBuilder($uuid).readString content '$res'" }
        return res
    }
}
