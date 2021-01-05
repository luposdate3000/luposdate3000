package lupos.s00misc
import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
@OptIn(ExperimentalStdlibApi::class)
internal class ByteArrayBuilder {
    internal companion object {
        @JvmField
        var debuguuidtmp = 0L
    }
    @JvmField
    var uuid = debuguuidtmp++
    @JvmField
    var capacity = 128
    @JvmField
    var data = ByteArray(capacity)
    @JvmField
    var size = 0
     internal inline fun build(): ByteArrayRead {
        SanityCheck.println { "ByteArrayBuilder($uuid).build with size $size and capacity $capacity" }
        return ByteArrayRead(data, size)
    }
     internal inline fun reset() {
        SanityCheck.println { "ByteArrayBuilder($uuid).reset" }
        capacity = 128
        data = ByteArray(capacity)
        size = 0
    }
     internal inline fun writeByte(b: Byte) {
        if (size + 1 > capacity) {
            data += ByteArray(capacity)
            capacity *= 2
        }
        SanityCheck.println { "ByteArrayBuilder($uuid).writeByte at $size with value ${b.toInt()}" }
        ByteArrayHelper.writeInt1(data, size, b.toInt() and 0xFF)
        size += 1
    }
     internal inline fun writeChar(c: Char) {
        if (size + 2 > capacity) {
            data += ByteArray(capacity)
            capacity *= 2
        }
        SanityCheck.println { "ByteArrayBuilder($uuid).writeChar at $size with value '$c' ${c.toInt()}" }
        ByteArrayHelper.writeChar(data, size, c)
        size += 2
    }
     internal inline fun writeInt(i: Int) {
        if (size + 4 > capacity) {
            data += ByteArray(capacity)
            capacity *= 2
        }
        SanityCheck.println { "ByteArrayBuilder($uuid).writeInt at $size with value $i" }
        ByteArrayHelper.writeInt4(data, size, i)
        size += 4
    }
     internal inline fun writeLong(l: Long) {
        if (size + 8 > capacity) {
            data += ByteArray(capacity)
            capacity *= 2
        }
        SanityCheck.println { "ByteArrayBuilder($uuid).writeLong at $size with value $l" }
        ByteArrayHelper.writeLong8(data, size, l)
        size += 8
    }
     internal inline fun writeString(s: String) {
        val tmp = s.toCharArray()
        writeInt(tmp.size)
        tmp.forEach {
            writeChar(it)
        }
        SanityCheck.println { "ByteArrayBuilder($uuid).writeString content '$s'" }
    }
}
