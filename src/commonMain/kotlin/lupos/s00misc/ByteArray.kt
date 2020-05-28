package lupos.s00misc

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
