package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
@UseExperimental(ExperimentalStdlibApi::class)
class DynamicByteArray {
    companion object {
        fun intToBool(i: Int) = i == 1
        fun boolToInt(b: Boolean): Int {
Coverage.funStart(92)
            if (b) {
Coverage.ifStart(93)
                return 1
            }
Coverage.statementStart(94)
            return 0
        }
    }
    @JvmField
    var pos = 4
    @JvmField
    var data: ByteArray
    @JvmField
    val maxlen: Int
    constructor() {
        data = ByteArray(100)
        maxlen = Int.MAX_VALUE
    }
    constructor(data: ByteArray) {
        this.data = data
        maxlen = getInt(0)
    }
    constructor(data: ByteArray, size: Int) {
        this.data = ByteArray(4) + data
        maxlen = size
        setInt(size, 0)
    }
    fun finish(): ByteArray {
Coverage.funStart(95)
        setInt(pos, 0)
Coverage.statementStart(96)
        return data
    }
    fun setInt(i: Int, p: Int) {
Coverage.funStart(97)
        data.set(p, i.toByte())
Coverage.statementStart(98)
        data.set(p + 1, (i ushr 8).toByte())
Coverage.statementStart(99)
        data.set(p + 2, (i ushr 16).toByte())
Coverage.statementStart(100)
        data.set(p + 3, (i ushr 24).toByte())
Coverage.statementStart(101)
    }
    fun appendInt(i: Int) {
Coverage.funStart(102)
        if (pos + 4 >= data.size) {
Coverage.ifStart(103)
            data += ByteArray(data.size)
Coverage.statementStart(104)
        }
Coverage.statementStart(105)
        setInt(i, pos)
Coverage.statementStart(106)
        pos += 4
Coverage.statementStart(107)
    }
    fun getInt(p: Int): Int {
Coverage.funStart(108)
        return ((0xFF and data[p].toInt())) or ((0xFF and data[p + 1].toInt()) shl 8) or ((0xFF and data[p + 2].toInt()) shl 16) or ((0xFF and data[p + 3].toInt()) shl 24)
    }
    fun getNextInt(): Int {
Coverage.funStart(109)
        val res = getInt(pos)
Coverage.statementStart(110)
        pos += 4
Coverage.statementStart(111)
        return res
    }
    fun setLong(i: Long, p: Int) {
Coverage.funStart(112)
        data.set(p, i.toByte())
Coverage.statementStart(113)
        data.set(p + 1, (i ushr 8).toByte())
Coverage.statementStart(114)
        data.set(p + 2, (i ushr 16).toByte())
Coverage.statementStart(115)
        data.set(p + 3, (i ushr 24).toByte())
Coverage.statementStart(116)
        data.set(p + 4, (i ushr 32).toByte())
Coverage.statementStart(117)
        data.set(p + 5, (i ushr 40).toByte())
Coverage.statementStart(118)
        data.set(p + 6, (i ushr 48).toByte())
Coverage.statementStart(119)
        data.set(p + 7, (i ushr 56).toByte())
Coverage.statementStart(120)
    }
    fun appendLong(i: Long) {
Coverage.funStart(121)
        if (pos + 8 >= data.size) {
Coverage.ifStart(122)
            data += ByteArray(data.size)
Coverage.statementStart(123)
        }
Coverage.statementStart(124)
        setLong(i, pos)
Coverage.statementStart(125)
        pos += 8
Coverage.statementStart(126)
    }
    fun getLong(p: Int): Long {
Coverage.funStart(127)
        return ((0xFF and data[p].toInt()).toLong()) or ((0xFF and data[p + 1].toInt()).toLong() shl 8) or ((0xFF and data[p + 2].toInt()).toLong() shl 16) or ((0xFF and data[p + 3].toInt()).toLong() shl 24) or ((0xFF and data[p + 4].toInt()).toLong() shl 32) or ((0xFF and data[p + 5].toInt()).toLong() shl 40) or ((0xFF and data[p + 6].toInt()).toLong() shl 48) or ((0xFF and data[p + 7].toInt()).toLong() shl 56)
    }
    fun getNextLong(): Long {
Coverage.funStart(128)
        val res = getLong(pos)
Coverage.statementStart(129)
        pos += 8
Coverage.statementStart(130)
        return res
    }
    fun appendString(s: String) {
Coverage.funStart(131)
        val tmp = s.encodeToByteArray()
Coverage.statementStart(132)
        appendInt(tmp.size)
Coverage.statementStart(133)
        while (pos + tmp.size >= data.size) {
Coverage.whileLoopStart(134)
            data += ByteArray(data.size)
Coverage.statementStart(135)
        }
Coverage.statementStart(136)
        for (b in tmp) {
Coverage.forLoopStart(137)
            data.set(pos, b)
Coverage.statementStart(138)
            pos++
Coverage.statementStart(139)
        }
Coverage.statementStart(140)
    }
    fun getString(p: Int): String {
Coverage.funStart(141)
        val l = getInt(p)
Coverage.statementStart(142)
        return data.decodeToString(p + 4, p + 4 + l, true)
    }
    fun getNextString(): String {
Coverage.funStart(143)
        val l = getNextInt()
Coverage.statementStart(144)
        val res = data.decodeToString(pos, pos + l, true)
Coverage.statementStart(145)
        pos += l
Coverage.statementStart(146)
        return res
    }
    fun appendSpace(l: Int): Int {
Coverage.funStart(147)
        val res = pos
Coverage.statementStart(148)
        pos += l
Coverage.statementStart(149)
        return res
    }
}
