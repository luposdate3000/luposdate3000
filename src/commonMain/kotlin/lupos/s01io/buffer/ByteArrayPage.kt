package lupos.s01io.buffer
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s01io.buffer.createString
import lupos.s01io.buffer.Page
inline fun Int.toBytes(bytes: ByteArray, offset: Int): Int {
Coverage.funStart(1720)
    bytes[offset] = this.toByte()
Coverage.statementStart(1721)
    val remaining1 = this ushr 8
Coverage.statementStart(1722)
    val adr1 = offset + 1
Coverage.statementStart(1723)
    bytes[adr1] = remaining1.toByte()
Coverage.statementStart(1724)
    val remaining2 = remaining1 ushr 8
Coverage.statementStart(1725)
    val adr2 = adr1 + 1
Coverage.statementStart(1726)
    bytes[adr2] = remaining2.toByte()
Coverage.statementStart(1727)
    val remaining3 = remaining2 ushr 8
Coverage.statementStart(1728)
    val adr3 = adr2 + 1
Coverage.statementStart(1729)
    bytes[adr3] = remaining3.toByte()
Coverage.statementStart(1730)
    return adr3 + 1
}
inline fun String.toBytesUTF(bytes: ByteArray, offset: Int): Int {
Coverage.funStart(1731)
    val size = this.length
Coverage.statementStart(1732)
    var pos = size.toBytes(bytes, offset)
Coverage.statementStart(1733)
    for (i in 0 until size) {
Coverage.forLoopStart(1734)
        val strChar = this[i]
Coverage.statementStart(1735)
        bytes[pos] = (strChar.toInt() and 0xFF00 shr 8).toByte()
Coverage.statementStart(1736)
        pos++
Coverage.statementStart(1737)
        bytes[pos] = (strChar.toInt() and 0x00FF).toByte()
Coverage.statementStart(1738)
        pos++
Coverage.statementStart(1739)
    }
Coverage.statementStart(1740)
    return pos
}
inline fun String.toBytesUTF(): ByteArray {
Coverage.funStart(1741)
    val bytes = ByteArray(this.length * 2)
Coverage.statementStart(1742)
    var pos = 0
Coverage.statementStart(1743)
    for (i in 0 until this.length) {
Coverage.forLoopStart(1744)
        val c = this[i]
Coverage.statementStart(1745)
        bytes[pos] = (c.toInt() and 0xFF00 shr 8).toByte()
Coverage.statementStart(1746)
        pos++
Coverage.statementStart(1747)
        bytes[pos] = (c.toInt() and 0x00FF).toByte()
Coverage.statementStart(1748)
        pos++
Coverage.statementStart(1749)
    }
Coverage.statementStart(1750)
    return bytes
}
inline fun String.toPageUTF(page: Page, address: Long): Long {
Coverage.funStart(1751)
    val size = this.length
Coverage.statementStart(1752)
    page.putInt(address, size)
Coverage.statementStart(1753)
    var pos = address + 4
Coverage.statementStart(1754)
    for (i in 0 until size) {
Coverage.forLoopStart(1755)
        val strChar = this[i]
Coverage.statementStart(1756)
        page.putByte(pos, (strChar.toInt() and 0xFF00 shr 8).toByte())
Coverage.statementStart(1757)
        pos++
Coverage.statementStart(1758)
        page.putByte(pos, (strChar.toInt() and 0x00FF).toByte())
Coverage.statementStart(1759)
        pos++
Coverage.statementStart(1760)
    }
Coverage.statementStart(1761)
    return pos
}
inline fun Page.getString(address: Long): String { // avoid using this method and do comparisons of strings etc. directly in the pages!
    val size = this.getInt(address)
    val buffer = CharArray(size)
    var pos = address + 4
    for (i in buffer.indices) {
Coverage.forLoopStart(1762)
        buffer[i] = ((this.getByte(pos).toInt() and 0xFF shl 8) + (this.getByte(pos + 1).toInt() and 0xFF)).toChar()
        pos += 2
    }
    return createString(buffer)
}
inline fun ByteArray.toInt(offset: Int): Int = (0xFF and this[offset].toInt()) or ((0xFF and this[offset + 1].toInt()) or ((0xFF and this[offset + 2].toInt()) or ((0xFF and this[offset + 3].toInt()) shl 8) shl 8) shl 8)
inline fun ByteArray.toStringUTF(offset: Int): String {
Coverage.funStart(1763)
    val size = this.toInt(offset)
Coverage.statementStart(1764)
    val buffer = CharArray(size)
Coverage.statementStart(1765)
    var pos = offset + 4
Coverage.statementStart(1766)
    for (i in buffer.indices) {
Coverage.forLoopStart(1767)
        buffer[i] = ((this[pos].toInt() and 0xFF shl 8) + (this[pos + 1].toInt() and 0xFF)).toChar()
Coverage.statementStart(1768)
        pos += 2
Coverage.statementStart(1769)
    }
Coverage.statementStart(1770)
    return createString(buffer)
}
inline fun ByteArray.toStringUTF(): String {
Coverage.funStart(1771)
    val buffer = CharArray(this.size / 2)
Coverage.statementStart(1772)
    var pos = 0
Coverage.statementStart(1773)
    for (i in buffer.indices) {
Coverage.forLoopStart(1774)
        buffer[i] = ((this[pos].toInt() and 0xFF shl 8) + (this[pos + 1].toInt() and 0xFF)).toChar()
Coverage.statementStart(1775)
        pos += 2
Coverage.statementStart(1776)
    }
Coverage.statementStart(1777)
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
Coverage.funStart(1778)
        val adr = address.toInt()
Coverage.statementStart(1779)
        return (0xFF and byteArray[adr].toInt()) or ((0xFF and byteArray[adr + 1].toInt()) or ((0xFF and byteArray[adr + 2].toInt()) or ((0xFF and byteArray[adr + 3].toInt()) shl 8) shl 8) shl 8)
    }
    inline fun getByte(address: Long): Byte {
Coverage.funStart(1780)
        return this.byteArray[address.toInt()]
    }
    inline fun putInt(address: Long, data: Int) {
Coverage.funStart(1781)
        this.modified = true
Coverage.statementStart(1782)
        val adr0 = address.toInt()
Coverage.statementStart(1783)
        this.byteArray[adr0] = data.toByte()
Coverage.statementStart(1784)
        val remaining1 = data ushr 8
Coverage.statementStart(1785)
        val adr1 = adr0 + 1
Coverage.statementStart(1786)
        this.byteArray[adr1] = remaining1.toByte()
Coverage.statementStart(1787)
        val remaining2 = remaining1 ushr 8
Coverage.statementStart(1788)
        val adr2 = adr1 + 1
Coverage.statementStart(1789)
        this.byteArray[adr2] = remaining2.toByte()
Coverage.statementStart(1790)
        val remaining3 = remaining2 ushr 8
Coverage.statementStart(1791)
        val adr3 = adr2 + 1
Coverage.statementStart(1792)
        this.byteArray[adr3] = remaining3.toByte()
Coverage.statementStart(1793)
    }
    inline fun putByte(address: Long, data: Byte) {
Coverage.funStart(1794)
        this.modified = true
Coverage.statementStart(1795)
        this.byteArray[address.toInt()] = data
Coverage.statementStart(1796)
    }
    inline fun putString(address: Long, data: String): Long {
Coverage.funStart(1797)
        this.modified = true
Coverage.statementStart(1798)
        val size = data.length
Coverage.statementStart(1799)
        this.putInt(address, size)
Coverage.statementStart(1800)
        var pos = address + 4
Coverage.statementStart(1801)
        for (i in 0 until size) {
Coverage.forLoopStart(1802)
            val strChar = data[i]
Coverage.statementStart(1803)
            this.putByte(pos, (strChar.toInt() and 0xFF00 shr 8).toByte())
Coverage.statementStart(1804)
            pos++
Coverage.statementStart(1805)
            this.putByte(pos, (strChar.toInt() and 0x00FF).toByte())
Coverage.statementStart(1806)
            pos++
Coverage.statementStart(1807)
        }
Coverage.statementStart(1808)
        return pos
    }
    inline fun getPageIndex(): Long = 0L
    inline fun lock() {
Coverage.funStart(1809)
        this.locked++
Coverage.statementStart(1810)
    }
    inline fun unlock() {
Coverage.funStart(1811)
        this.locked--
Coverage.statementStart(1812)
    }
    inline fun isLocked(): Boolean {
Coverage.funStart(1813)
        return this.locked > 0
    }
    inline fun release() {}
    inline fun isModified() = this.modified
}
