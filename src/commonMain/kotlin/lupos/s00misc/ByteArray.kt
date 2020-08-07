package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck

inline fun ByteArray.writeInt1(offset: Int, value: Int) {
    this[offset] = (value and 0xFF).toByte()
    SanityCheck.check({ value == readInt1(offset) }, { "$value ${readInt1(offset)}" })
}

inline fun ByteArray.writeInt2(offset: Int, value: Int) {
    this[offset] = ((value shr 8) and 0xFF).toByte()
    this[offset + 1] = (value and 0xFF).toByte()
    SanityCheck.check({ value == readInt2(offset) }, { "$value ${readInt2(offset)}" })
}

inline fun ByteArray.writeInt3(offset: Int, value: Int) {
    this[offset] = ((value shr 16) and 0xFF).toByte()
    this[offset + 1] = ((value shr 8) and 0xFF).toByte()
    this[offset + 2] = (value and 0xFF).toByte()
    SanityCheck.check({ value == readInt3(offset) }, { "$value ${readInt3(offset)}" })
}

inline fun ByteArray.writeInt4(offset: Int, value: Int) {
    this[offset] = ((value shr 24) and 0xFF).toByte()
    this[offset + 1] = ((value shr 16) and 0xFF).toByte()
    this[offset + 2] = ((value shr 8) and 0xFF).toByte()
    this[offset + 3] = (value and 0xFF).toByte()
    SanityCheck.check({ value == readInt4(offset) }, { "$value ${readInt4(offset)} ${this[offset].toString(16)} ${this[offset + 1].toString(16)} ${this[offset + 2].toString(16)} ${this[offset + 3].toString(16)}" })
}

inline fun ByteArray.writeLong8(offset: Int, value: Long) {
    this[offset] = ((value shr 56) and 0xFF).toByte()
    this[offset + 1] = ((value shr 48) and 0xFF).toByte()
    this[offset + 2] = ((value shr 40) and 0xFF).toByte()
    this[offset + 3] = ((value shr 32) and 0xFF).toByte()
    this[offset + 4] = ((value shr 24) and 0xFF).toByte()
    this[offset + 5] = ((value shr 16) and 0xFF).toByte()
    this[offset + 6] = ((value shr 8) and 0xFF).toByte()
    this[offset + 7] = (value and 0xFF).toByte()
    SanityCheck.check({ value == readLong8(offset) })
}

inline fun ByteArray.writeChar(offset: Int, value: Char) {
    val v = value.toInt()
    this[offset] = ((v shr 8) and 0xFF).toByte()
    this[offset + 1] = (v and 0xFF).toByte()
    SanityCheck.check({ v == readInt2(offset) }, { "$v ${readInt2(offset)}" })
}

inline fun ByteArray.readLong8(offset: Int): Long {
    return (((this[offset].toLong() and 0xFF) shl 56) or ((this[offset + 1].toLong() and 0xFF) shl 48) or ((this[offset + 2].toLong() and 0xFF) shl 40) or ((this[offset + 3].toLong() and 0xFF) shl 32) or ((this[offset + 4].toLong() and 0xFF) shl 24) or ((this[offset + 5].toLong() and 0xFF) shl 16) or ((this[offset + 6].toLong() and 0xFF) shl 8) or ((this[offset + 7].toLong() and 0xFF)))
}

inline fun ByteArray.readInt4(offset: Int): Int {
    return (((this[offset].toInt() and 0xFF) shl 24) or ((this[offset + 1].toInt() and 0xFF) shl 16) or ((this[offset + 2].toInt() and 0xFF) shl 8) or ((this[offset + 3].toInt() and 0xFF)))
}

inline fun ByteArray.readInt3(offset: Int): Int {
    return (((this[offset].toInt() and 0xFF) shl 16) or ((this[offset + 1].toInt() and 0xFF) shl 8) or ((this[offset + 2].toInt() and 0xFF)))
}

inline fun ByteArray.readInt2(offset: Int): Int {
    return (((this[offset].toInt() and 0xFF) shl 8) or ((this[offset + 1].toInt() and 0xFF)))
}

inline fun ByteArray.readInt1(offset: Int): Int {
    return (this[offset].toInt() and 0xFF)
}

inline fun ByteArray.readChar(offset: Int): Char {
    return (((this[offset].toInt() and 0xFF) shl 8) or ((this[offset + 1].toInt() and 0xFF))).toChar()
}

var debuguuidtmp = 0

@UseExperimental(ExperimentalStdlibApi::class)
class ByteArrayRead(@JvmField val data: ByteArray, @JvmField val size: Int) {
    @JvmField
    var uuid = debuguuidtmp++

    @JvmField
    var offset = 0
    inline fun remaining() = size - offset

    init {
        SanityCheck.println { "ByteArrayRead($uuid).init with $size Bytes" }
    }

    inline fun readInt(): Int {
        val res = data.readInt4(offset)
        SanityCheck.println { "ByteArrayRead($uuid).readInt at $offset with value $res" }
        offset += 4
        return res
    }

    inline fun readByte(): Byte {
        val res = data.readInt1(offset)
        SanityCheck.println { "ByteArrayRead($uuid).readByte at $offset with value $res" }
        offset += 1
        return res.toByte()
    }

    inline fun readChar(): Char {
        val res = data.readChar(offset)
        SanityCheck.println { "ByteArrayRead($uuid).readChar at $offset with value '$res' ${res.toInt()}" }
        offset += 2
        return res
    }

    inline fun readLong(): Long {
        val res = data.readLong8(offset)
        SanityCheck.println { "ByteArrayRead($uuid).readLong at $offset with value $res" }
        offset += 8
        return res
    }

    inline fun readString(): String {
        val len = readInt()
        var d = CharArray(len)
        for (i in 0 until len) {
            d[i] = readChar()
        }
        val res = d.concatToString()
        SanityCheck.println { "ByteArrayBuilder($uuid).readString content '$res'" }
        return res
    }
}

@UseExperimental(ExperimentalStdlibApi::class)
class ByteArrayBuilder() {
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
