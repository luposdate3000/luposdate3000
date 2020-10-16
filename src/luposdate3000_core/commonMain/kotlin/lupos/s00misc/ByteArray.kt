package lupos.s00misc

import kotlin.jvm.JvmField
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

inline fun ByteArray.writeIntX(offset: Int, value: Int, count: Int) {
    when (count) {
        0 -> {
        }
        1 -> {
            writeInt1(offset, value)
        }
        2 -> {
            writeInt2(offset, value)
        }
        3 -> {
            writeInt3(offset, value)
        }
        else -> {
            writeInt4(offset, value)
        }
    }
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

inline fun ByteArray.readIntX(offset: Int, count: Int): Int {
    when (count) {
        0 -> {
            return 0
        }
        1 -> {
            return readInt1(offset)
        }
        2 -> {
            return readInt2(offset)
        }
        3 -> {
            return readInt3(offset)
        }
        else -> {
            return readInt4(offset)
        }
    }
}

inline fun ByteArray.readChar(offset: Int): Char {
    return (((this[offset].toInt() and 0xFF) shl 8) or ((this[offset + 1].toInt() and 0xFF))).toChar()
}
