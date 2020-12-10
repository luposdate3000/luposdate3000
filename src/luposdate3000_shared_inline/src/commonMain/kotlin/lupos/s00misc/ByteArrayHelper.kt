package lupos.s00misc
internal object ByteArrayHelper {
    inline fun writeInt1(data: ByteArray, offset: Int, value: Int) {
        data[offset] = (value and 0xFF).toByte()
        SanityCheck.check({ value == readInt1(data, offset) }, { "$value ${readInt1(data, offset)}" })
    }
    inline fun writeInt2(data: ByteArray, offset: Int, value: Int) {
        data[offset] = ((value shr 8) and 0xFF).toByte()
        data[offset + 1] = (value and 0xFF).toByte()
        SanityCheck.check({ value == readInt2(data, offset) }, { "$value ${readInt2(data, offset)}" })
    }
    inline fun writeInt3(data: ByteArray, offset: Int, value: Int) {
        data[offset] = ((value shr 16) and 0xFF).toByte()
        data[offset + 1] = ((value shr 8) and 0xFF).toByte()
        data[offset + 2] = (value and 0xFF).toByte()
        SanityCheck.check({ value == readInt3(data, offset) }, { "$value ${readInt3(data, offset)}" })
    }
    inline fun writeInt4(data: ByteArray, offset: Int, value: Int) {
        data[offset] = ((value shr 24) and 0xFF).toByte()
        data[offset + 1] = ((value shr 16) and 0xFF).toByte()
        data[offset + 2] = ((value shr 8) and 0xFF).toByte()
        data[offset + 3] = (value and 0xFF).toByte()
        SanityCheck.check({ value == readInt4(data, offset) }, { "$value ${readInt4(data, offset)} ${data[offset].toString(16)} ${data[offset + 1].toString(16)} ${data[offset + 2].toString(16)} ${data[offset + 3].toString(16)}" })
    }
    inline fun writeIntX(data: ByteArray, offset: Int, value: Int, count: Int) {
        when (count) {
            0 -> {
            }
            1 -> {
                writeInt1(data, offset, value)
            }
            2 -> {
                writeInt2(data, offset, value)
            }
            3 -> {
                writeInt3(data, offset, value)
            }
            else -> {
                writeInt4(data, offset, value)
            }
        }
    }
    inline fun writeLong8(data: ByteArray, offset: Int, value: Long) {
        data[offset] = ((value shr 56) and 0xFF).toByte()
        data[offset + 1] = ((value shr 48) and 0xFF).toByte()
        data[offset + 2] = ((value shr 40) and 0xFF).toByte()
        data[offset + 3] = ((value shr 32) and 0xFF).toByte()
        data[offset + 4] = ((value shr 24) and 0xFF).toByte()
        data[offset + 5] = ((value shr 16) and 0xFF).toByte()
        data[offset + 6] = ((value shr 8) and 0xFF).toByte()
        data[offset + 7] = (value and 0xFF).toByte()
        SanityCheck.check { value == readLong8(data, offset) }
    }
    inline fun writeChar(data: ByteArray, offset: Int, value: Char) {
        val v = value.toInt()
        data[offset] = ((v shr 8) and 0xFF).toByte()
        data[offset + 1] = (v and 0xFF).toByte()
        SanityCheck.check({ v == readInt2(data, offset) }, { "$v ${readInt2(data, offset)}" })
    }
    inline fun readLong8(data: ByteArray, offset: Int): Long {
        return (((data[offset].toLong() and 0xFF) shl 56) or ((data[offset + 1].toLong() and 0xFF) shl 48) or ((data[offset + 2].toLong() and 0xFF) shl 40) or ((data[offset + 3].toLong() and 0xFF) shl 32) or ((data[offset + 4].toLong() and 0xFF) shl 24) or ((data[offset + 5].toLong() and 0xFF) shl 16) or ((data[offset + 6].toLong() and 0xFF) shl 8) or ((data[offset + 7].toLong() and 0xFF)))
    }
    inline fun readInt4(data: ByteArray, offset: Int): Int {
        return (((data[offset].toInt() and 0xFF) shl 24) or ((data[offset + 1].toInt() and 0xFF) shl 16) or ((data[offset + 2].toInt() and 0xFF) shl 8) or ((data[offset + 3].toInt() and 0xFF)))
    }
    inline fun readInt3(data: ByteArray, offset: Int): Int {
        return (((data[offset].toInt() and 0xFF) shl 16) or ((data[offset + 1].toInt() and 0xFF) shl 8) or ((data[offset + 2].toInt() and 0xFF)))
    }
    inline fun readInt2(data: ByteArray, offset: Int): Int {
        return (((data[offset].toInt() and 0xFF) shl 8) or ((data[offset + 1].toInt() and 0xFF)))
    }
    inline fun readInt1(data: ByteArray, offset: Int): Int {
        return (data[offset].toInt() and 0xFF)
    }
    inline fun readIntX(data: ByteArray, offset: Int, count: Int): Int {
        when (count) {
            0 -> {
                return 0
            }
            1 -> {
                return readInt1(data, offset)
            }
            2 -> {
                return readInt2(data, offset)
            }
            3 -> {
                return readInt3(data, offset)
            }
            else -> {
                return readInt4(data, offset)
            }
        }
    }
    inline fun readChar(data: ByteArray, offset: Int): Char {
        return (((data[offset].toInt() and 0xFF) shl 8) or ((data[offset + 1].toInt() and 0xFF))).toChar()
    }
}
