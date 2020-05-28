package lupos.s00misc
import lupos.s00misc.Coverage
fun ByteArray.writeInt1(offset: Int, value: Int) {
Coverage.funStart(0)
    this[offset] = (value and 0xFF).toByte()
Coverage.statementStart(1)
    SanityCheck.check({ value == readInt1(offset) }, { "$value ${readInt1(offset)}" })
Coverage.statementStart(2)
}
fun ByteArray.writeInt2(offset: Int, value: Int) {
Coverage.funStart(3)
    this[offset] = ((value shr 8) and 0xFF).toByte()
Coverage.statementStart(4)
    this[offset + 1] = (value and 0xFF).toByte()
Coverage.statementStart(5)
    SanityCheck.check({ value == readInt2(offset) }, { "$value ${readInt2(offset)}" })
Coverage.statementStart(6)
}
fun ByteArray.writeInt3(offset: Int, value: Int) {
Coverage.funStart(7)
    this[offset] = ((value shr 16) and 0xFF).toByte()
Coverage.statementStart(8)
    this[offset + 1] = ((value shr 8) and 0xFF).toByte()
Coverage.statementStart(9)
    this[offset + 2] = (value and 0xFF).toByte()
Coverage.statementStart(10)
    SanityCheck.check({ value == readInt3(offset) }, { "$value ${readInt3(offset)}" })
Coverage.statementStart(11)
}
fun ByteArray.writeInt4(offset: Int, value: Int) {
Coverage.funStart(12)
    this[offset] = ((value shr 24) and 0xFF).toByte()
Coverage.statementStart(13)
    this[offset + 1] = ((value shr 16) and 0xFF).toByte()
Coverage.statementStart(14)
    this[offset + 2] = ((value shr 8) and 0xFF).toByte()
Coverage.statementStart(15)
    this[offset + 3] = (value and 0xFF).toByte()
Coverage.statementStart(16)
    SanityCheck.check({ value == readInt4(offset) }, { "$value ${readInt4(offset)} ${this[offset].toString(16)} ${this[offset + 1].toString(16)} ${this[offset + 2].toString(16)} ${this[offset + 3].toString(16)}" })
Coverage.statementStart(17)
}
fun ByteArray.writeLong8(offset: Int, value: Long) {
Coverage.funStart(18)
    this[offset] = ((value shr 56) and 0xFF).toByte()
Coverage.statementStart(19)
    this[offset + 1] = ((value shr 48) and 0xFF).toByte()
Coverage.statementStart(20)
    this[offset + 2] = ((value shr 40) and 0xFF).toByte()
Coverage.statementStart(21)
    this[offset + 3] = ((value shr 32) and 0xFF).toByte()
Coverage.statementStart(22)
    this[offset + 4] = ((value shr 24) and 0xFF).toByte()
Coverage.statementStart(23)
    this[offset + 5] = ((value shr 16) and 0xFF).toByte()
Coverage.statementStart(24)
    this[offset + 6] = ((value shr 8) and 0xFF).toByte()
Coverage.statementStart(25)
    this[offset + 7] = (value and 0xFF).toByte()
Coverage.statementStart(26)
    SanityCheck.check({ value == readLong8(offset) })
Coverage.statementStart(27)
}
fun ByteArray.readLong8(offset: Int): Long {
Coverage.funStart(28)
    return (((this[offset].toLong() and 0xFF) shl 56) or ((this[offset + 1].toLong() and 0xFF) shl 48) or ((this[offset + 2].toLong() and 0xFF) shl 40) or ((this[offset + 3].toLong() and 0xFF) shl 32) or ((this[offset + 4].toLong() and 0xFF) shl 24) or ((this[offset + 5].toLong() and 0xFF) shl 16) or ((this[offset + 6].toLong() and 0xFF) shl 8) or ((this[offset + 7].toLong() and 0xFF)))
}
fun ByteArray.readInt4(offset: Int): Int {
Coverage.funStart(29)
    return (((this[offset].toInt() and 0xFF) shl 24) or ((this[offset + 1].toInt() and 0xFF) shl 16) or ((this[offset + 2].toInt() and 0xFF) shl 8) or ((this[offset + 3].toInt() and 0xFF)))
}
fun ByteArray.readInt3(offset: Int): Int {
Coverage.funStart(30)
    return (((this[offset].toInt() and 0xFF) shl 16) or ((this[offset + 1].toInt() and 0xFF) shl 8) or ((this[offset + 2].toInt() and 0xFF)))
}
fun ByteArray.readInt2(offset: Int): Int {
Coverage.funStart(31)
    return (((this[offset].toInt() and 0xFF) shl 8) or ((this[offset + 1].toInt() and 0xFF)))
}
fun ByteArray.readInt1(offset: Int): Int {
Coverage.funStart(32)
    return (this[offset].toInt() and 0xFF)
}
@UseExperimental(ExperimentalStdlibApi::class)
class ByteArrayRead(val data: ByteArray, val size: Int) {
    var offset = 0
    fun remaining() = size - offset
    fun readInt(): Int {
Coverage.funStart(33)
        val res = data.readInt4(offset)
Coverage.statementStart(34)
        offset += 4
Coverage.statementStart(35)
        return res
    }
    fun readByte(): Byte {
Coverage.funStart(36)
        val res = data.readInt1(offset)
Coverage.statementStart(37)
        offset += 1
Coverage.statementStart(38)
        return res.toByte()
    }
    fun readLong(): Long {
Coverage.funStart(39)
        val res = data.readLong8(offset)
Coverage.statementStart(40)
        offset += 8
Coverage.statementStart(41)
        return res
    }
    fun readString(): String {
Coverage.funStart(42)
        val len = readInt()
Coverage.statementStart(43)
        return data.decodeToString(offset, offset + len, true)
    }
}
@UseExperimental(ExperimentalStdlibApi::class)
class ByteArrayBuilder() {
    var capacity = 128
    var data = ByteArray(capacity)
    var size = 0
    fun build(): ByteArrayRead {
Coverage.funStart(44)
        return ByteArrayRead(data, size)
    }
    fun reset() {
Coverage.funStart(45)
        capacity = 128
Coverage.statementStart(46)
        data = ByteArray(capacity)
Coverage.statementStart(47)
        size = 0
Coverage.statementStart(48)
    }
    fun writeByte(b: Byte) {
Coverage.funStart(49)
        if (size + 1 > capacity) {
Coverage.ifStart(50)
            data += ByteArray(capacity)
Coverage.statementStart(51)
            capacity = capacity * 2
Coverage.statementStart(52)
        }
Coverage.statementStart(53)
        data.writeInt1(size, b.toInt() and 0xFF)
Coverage.statementStart(54)
        size += 1
Coverage.statementStart(55)
    }
    fun writeInt(i: Int) {
Coverage.funStart(56)
        if (size + 4 > capacity) {
Coverage.ifStart(57)
            data += ByteArray(capacity)
Coverage.statementStart(58)
            capacity = capacity * 2
Coverage.statementStart(59)
        }
Coverage.statementStart(60)
        data.writeInt4(size, i)
Coverage.statementStart(61)
        size += 4
Coverage.statementStart(62)
    }
    fun writeLong(l: Long) {
Coverage.funStart(63)
        if (size + 8 > capacity) {
Coverage.ifStart(64)
            data += ByteArray(capacity)
Coverage.statementStart(65)
            capacity = capacity * 2
Coverage.statementStart(66)
        }
Coverage.statementStart(67)
        data.writeLong8(size, l)
Coverage.statementStart(68)
        size += 8
Coverage.statementStart(69)
    }
    fun writeString(s: String) {
Coverage.funStart(70)
        val tmp = s.encodeToByteArray()
Coverage.statementStart(71)
        writeInt(tmp.size)
Coverage.statementStart(72)
        if (size + tmp.size >= capacity) {
Coverage.ifStart(73)
            data += ByteArray(tmp.size)
Coverage.statementStart(74)
            capacity += tmp.size
Coverage.statementStart(75)
        }
Coverage.statementStart(76)
        tmp.copyInto(data, size, tmp.size)
Coverage.statementStart(77)
    }
}
