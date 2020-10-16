package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s00misc.SanityCheck

@UseExperimental(ExperimentalStdlibApi::class)
class ByteArrayBuilder() {
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
    inline fun build(): ByteArrayRead {
        SanityCheck.println { "ByteArrayBuilder($uuid).build with size $size and capacity $capacity" }
        return ByteArrayRead(data, size)
    }

    inline fun reset() {
        SanityCheck.println { "ByteArrayBuilder($uuid).reset" }
        capacity = 128
        data = ByteArray(capacity)
        size = 0
    }

    inline fun writeByte(b: Byte) {
        if (size + 1 > capacity) {
            data += ByteArray(capacity)
            capacity = capacity * 2
        }
        SanityCheck.println { "ByteArrayBuilder($uuid).writeByte at $size with value ${b.toInt()}" }
        data.writeInt1(size, b.toInt() and 0xFF)
        size += 1
    }

    inline fun writeChar(c: Char) {
        if (size + 2 > capacity) {
            data += ByteArray(capacity)
            capacity = capacity * 2
        }
        SanityCheck.println { "ByteArrayBuilder($uuid).writeChar at $size with value '${c}' ${c.toInt()}" }
        data.writeChar(size, c)
        size += 2
    }

    inline fun writeInt(i: Int) {
        if (size + 4 > capacity) {
            data += ByteArray(capacity)
            capacity = capacity * 2
        }
        SanityCheck.println { "ByteArrayBuilder($uuid).writeInt at $size with value ${i}" }
        data.writeInt4(size, i)
        size += 4
    }

    inline fun writeLong(l: Long) {
        if (size + 8 > capacity) {
            data += ByteArray(capacity)
            capacity = capacity * 2
        }
        SanityCheck.println { "ByteArrayBuilder($uuid).writeLong at $size with value ${l}" }
        data.writeLong8(size, l)
        size += 8
    }

    inline fun writeString(s: String) {
        val tmp = s.toCharArray()
        writeInt(tmp.size)
        tmp.forEach {
            writeChar(it)
        }
        SanityCheck.println { "ByteArrayBuilder($uuid).writeString content '$s'" }
    }
}
