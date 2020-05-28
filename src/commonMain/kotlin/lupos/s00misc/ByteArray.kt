package lupos.s00misc

import lupos.s00misc.Coverage

fun ByteArray.writeInt1(offset: Int, value: Int) {
    this[offset] = (value and 0xFF).toByte()
    SanityCheck.check({ value == readInt1(offset) }, { "$value ${readInt1(offset)}" })
}

fun ByteArray.writeInt2(offset: Int, value: Int) {
    this[offset] = ((value shr 8) and 0xFF).toByte()
    this[offset + 1] = (value and 0xFF).toByte()
    SanityCheck.check({ value == readInt2(offset) }, { "$value ${readInt2(offset)}" })
}

fun ByteArray.writeInt3(offset: Int, value: Int) {
    this[offset] = ((value shr 16) and 0xFF).toByte()
    this[offset + 1] = ((value shr 8) and 0xFF).toByte()
    this[offset + 2] = (value and 0xFF).toByte()
    SanityCheck.check({ value == readInt3(offset) }, { "$value ${readInt3(offset)}" })
}

fun ByteArray.writeInt4(offset: Int, value: Int) {
    this[offset] = ((value shr 24) and 0xFF).toByte()
    this[offset + 1] = ((value shr 16) and 0xFF).toByte()
    this[offset + 2] = ((value shr 8) and 0xFF).toByte()
    this[offset + 3] = (value and 0xFF).toByte()
    SanityCheck.check({ value == readInt4(offset) }, { "$value ${readInt4(offset)} ${this[offset].toString(16)} ${this[offset + 1].toString(16)} ${this[offset + 2].toString(16)} ${this[offset + 3].toString(16)}" })
}

fun ByteArray.writeLong8(offset: Int, value: Long) {
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

fun ByteArray.readLong8(offset: Int): Long {
    return (((this[offset].toLong() and 0xFF) shl 56) or ((this[offset + 1].toLong() and 0xFF) shl 48) or ((this[offset + 2].toLong() and 0xFF) shl 40) or ((this[offset + 3].toLong() and 0xFF) shl 32) or ((this[offset + 4].toLong() and 0xFF) shl 24) or ((this[offset + 5].toLong() and 0xFF) shl 16) or ((this[offset + 6].toLong() and 0xFF) shl 8) or ((this[offset + 7].toLong() and 0xFF)))
}

fun ByteArray.readInt4(offset: Int): Int {
    return (((this[offset].toInt() and 0xFF) shl 24) or ((this[offset + 1].toInt() and 0xFF) shl 16) or ((this[offset + 2].toInt() and 0xFF) shl 8) or ((this[offset + 3].toInt() and 0xFF)))
}

fun ByteArray.readInt3(offset: Int): Int {
    return (((this[offset].toInt() and 0xFF) shl 16) or ((this[offset + 1].toInt() and 0xFF) shl 8) or ((this[offset + 2].toInt() and 0xFF)))
}

fun ByteArray.readInt2(offset: Int): Int {
    return (((this[offset].toInt() and 0xFF) shl 8) or ((this[offset + 1].toInt() and 0xFF)))
}

fun ByteArray.readInt1(offset: Int): Int {
    return (this[offset].toInt() and 0xFF)
}

@UseExperimental(ExperimentalStdlibApi::class)
class ByteArrayRead(val data: ByteArray, val size: Int) {
    var offset = 0
    fun remaining() = size - offset
    fun readInt(): Int {
        val res = data.readInt4(offset)
        offset += 4
        return res
    }

    fun readByte(): Byte {
        val res = data.readInt1(offset)
        offset += 1
        return res.toByte()
    }

    fun readLong(): Long {
        val res = data.readLong8(offset)
        offset += 8
        return res
    }

    fun readString(): String {
        val len = readInt()
        return data.decodeToString(offset, offset + len, true)
    }
}

@UseExperimental(ExperimentalStdlibApi::class)
class ByteArrayBuilder() {
    var capacity = 128
    var data = ByteArray(capacity)
    var size = 0
    fun build(): ByteArrayRead {
        return ByteArrayRead(data, size)
    }

    fun reset() {
        capacity = 128
        data = ByteArray(capacity)
        size = 0
    }

    fun writeByte(b: Byte) {
        if (size + 1 > capacity) {
            data += ByteArray(capacity)
            capacity = capacity * 2
        }
        data.writeInt1(size, b.toInt() and 0xFF)
        size += 1
    }

    fun writeInt(i: Int) {
        if (size + 4 > capacity) {
            data += ByteArray(capacity)
            capacity = capacity * 2
        }
        data.writeInt4(size, i)
        size += 4
    }

    fun writeLong(l: Long) {
        if (size + 8 > capacity) {
            data += ByteArray(capacity)
            capacity = capacity * 2
        }
        data.writeLong8(size, l)
        size += 8
    }

    fun writeString(s: String) {
        val tmp = s.encodeToByteArray()
        writeInt(tmp.size)
        if (size + tmp.size >= capacity) {
            data += ByteArray(tmp.size)
            capacity += tmp.size
        }
        tmp.copyInto(data, size, tmp.size)
    }
}
