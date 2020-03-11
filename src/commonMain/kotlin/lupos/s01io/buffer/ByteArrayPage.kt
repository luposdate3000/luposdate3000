package lupos.s01io.buffer

import kotlin.jvm.JvmField
import lupos.s01io.buffer.createString
import lupos.s01io.buffer.Page
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


inline fun Int.toBytes(bytes: ByteArray, offset: Int): Int {
    bytes[offset] = this.toByte()
    val remaining1 = this ushr 8
    val adr1 = offset + 1
    bytes[adr1] = remaining1.toByte()
    val remaining2 = remaining1 ushr 8
    val adr2 = adr1 + 1
    bytes[adr2] = remaining2.toByte()
    val remaining3 = remaining2 ushr 8
    val adr3 = adr2 + 1
    bytes[adr3] = remaining3.toByte()
    return adr3 + 1
}

inline fun String.toBytesUTF(bytes: ByteArray, offset: Int): Int {
    val size = this.length
    var pos = size.toBytes(bytes, offset)
    for (i in 0 until size) {
        val strChar = this[i]
        bytes[pos] = (strChar.toInt() and 0xFF00 shr 8).toByte()
        pos++
        bytes[pos] = (strChar.toInt() and 0x00FF).toByte()
        pos++
    }
    return pos
}

inline fun String.toBytesUTF(): ByteArray {
    val bytes = ByteArray(this.length * 2)
    var pos = 0
    for (i in 0 until this.length) {
        val c = this[i]
        bytes[pos] = (c.toInt() and 0xFF00 shr 8).toByte()
        pos++
        bytes[pos] = (c.toInt() and 0x00FF).toByte()
        pos++
    }
    return bytes
}

inline fun String.toPageUTF(page: Page, address: Long): Long {
    val size = this.length
    page.putInt(address, size)
    var pos = address + 4
    for (i in 0 until size) {
        val strChar = this[i]
        page.putByte(pos, (strChar.toInt() and 0xFF00 shr 8).toByte())
        pos++
        page.putByte(pos, (strChar.toInt() and 0x00FF).toByte())
        pos++
    }
    return pos
}

inline fun Page.getString(address: Long): String { // avoid using this method and do comparisons of strings etc. directly in the pages!
    val size = this.getInt(address)
    val buffer = CharArray(size)
    var pos = address + 4
    for (i in buffer.indices) {
        buffer[i] = ((this.getByte(pos).toInt() and 0xFF shl 8) + (this.getByte(pos + 1).toInt() and 0xFF)).toChar()
        pos += 2
    }
    return createString(buffer)
}

inline fun ByteArray.toInt(offset: Int) = (0xFF and this[offset].toInt()) or ((0xFF and this[offset + 1].toInt()) or ((0xFF and this[offset + 2].toInt()) or ((0xFF and this[offset + 3].toInt()) shl 8) shl 8) shl 8)

inline fun ByteArray.toStringUTF(offset: Int): String {
    val size = this.toInt(offset)
    val buffer = CharArray(size)
    var pos = offset + 4
    for (i in buffer.indices) {
        buffer[i] = ((this[pos].toInt() and 0xFF shl 8) + (this[pos + 1].toInt() and 0xFF)).toChar()
        pos += 2
    }
    return createString(buffer)
}

inline fun ByteArray.toStringUTF(): String {
    val buffer = CharArray(this.size / 2)
    var pos = 0
    for (i in buffer.indices) {
        buffer[i] = ((this[pos].toInt() and 0xFF shl 8) + (this[pos + 1].toInt() and 0xFF)).toChar()
        pos += 2
    }
    return createString(buffer)
}

class ByteArrayPage {
    @JvmField // in JVM-environment: this does not generate any getter avoiding a virtual method call!
    val PAGESIZE = 8 * 1024
    @JvmField // in JVM-environment: this does not generate any getter avoiding a virtual method call!
    val byteArray = ByteArray(PAGESIZE)
    // in JVM-environment: this does not generate any getter avoiding a virtual method call!
    @JvmField
    var locked = 0
    // in JVM-environment: this does not generate any getter avoiding a virtual method call!
    @JvmField
    var modified = false

    constructor()

    inline fun getInt(address: Long): Int {
        val adr = address.toInt()
        return (0xFF and byteArray[adr].toInt()) or ((0xFF and byteArray[adr + 1].toInt()) or ((0xFF and byteArray[adr + 2].toInt()) or ((0xFF and byteArray[adr + 3].toInt()) shl 8) shl 8) shl 8)
    }

    inline fun getByte(address: Long): Byte {
        return this.byteArray[address.toInt()]
    }

    inline fun putInt(address: Long, data: Int) {
        this.modified = true
        val adr0 = address.toInt()
        this.byteArray[adr0] = data.toByte()
        val remaining1 = data ushr 8
        val adr1 = adr0 + 1
        this.byteArray[adr1] = remaining1.toByte()
        val remaining2 = remaining1 ushr 8
        val adr2 = adr1 + 1
        this.byteArray[adr2] = remaining2.toByte()
        val remaining3 = remaining2 ushr 8
        val adr3 = adr2 + 1
        this.byteArray[adr3] = remaining3.toByte()
    }

    inline fun putByte(address: Long, data: Byte) {
        this.modified = true
        this.byteArray[address.toInt()] = data
    }

    inline fun putString(address: Long, data: String): Long {
        this.modified = true
        val size = data.length
        this.putInt(address, size)
        var pos = address + 4
        for (i in 0 until size) {
            val strChar = data[i]
            this.putByte(pos, (strChar.toInt() and 0xFF00 shr 8).toByte())
            pos++
            this.putByte(pos, (strChar.toInt() and 0x00FF).toByte())
            pos++
        }
        return pos
    }

    inline fun getPageIndex(): Long = 0L
    inline fun lock() {
        this.locked++
    }

    inline fun unlock() {
        this.locked--
    }

    inline fun isLocked(): Boolean {
        return this.locked > 0
    }

    inline fun release() {}
    inline fun isModified() = this.modified
}
