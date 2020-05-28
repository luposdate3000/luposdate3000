package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage

@UseExperimental(ExperimentalStdlibApi::class)
class DynamicByteArray {
    companion object {
        fun intToBool(i: Int) = i == 1
        fun boolToInt(b: Boolean): Int {
            if (b) {
                return 1
            }
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
        setInt(pos, 0)
        return data
    }

    fun setInt(i: Int, p: Int) {
        data.set(p, i.toByte())
        data.set(p + 1, (i ushr 8).toByte())
        data.set(p + 2, (i ushr 16).toByte())
        data.set(p + 3, (i ushr 24).toByte())
    }

    fun appendInt(i: Int) {
        if (pos + 4 >= data.size) {
            data += ByteArray(data.size)
        }
        setInt(i, pos)
        pos += 4
    }

    fun getInt(p: Int): Int {
        return ((0xFF and data[p].toInt())) or ((0xFF and data[p + 1].toInt()) shl 8) or ((0xFF and data[p + 2].toInt()) shl 16) or ((0xFF and data[p + 3].toInt()) shl 24)
    }

    fun getNextInt(): Int {
        val res = getInt(pos)
        pos += 4
        return res
    }

    fun setLong(i: Long, p: Int) {
        data.set(p, i.toByte())
        data.set(p + 1, (i ushr 8).toByte())
        data.set(p + 2, (i ushr 16).toByte())
        data.set(p + 3, (i ushr 24).toByte())
        data.set(p + 4, (i ushr 32).toByte())
        data.set(p + 5, (i ushr 40).toByte())
        data.set(p + 6, (i ushr 48).toByte())
        data.set(p + 7, (i ushr 56).toByte())
    }

    fun appendLong(i: Long) {
        if (pos + 8 >= data.size) {
            data += ByteArray(data.size)
        }
        setLong(i, pos)
        pos += 8
    }

    fun getLong(p: Int): Long {
        return ((0xFF and data[p].toInt()).toLong()) or ((0xFF and data[p + 1].toInt()).toLong() shl 8) or ((0xFF and data[p + 2].toInt()).toLong() shl 16) or ((0xFF and data[p + 3].toInt()).toLong() shl 24) or ((0xFF and data[p + 4].toInt()).toLong() shl 32) or ((0xFF and data[p + 5].toInt()).toLong() shl 40) or ((0xFF and data[p + 6].toInt()).toLong() shl 48) or ((0xFF and data[p + 7].toInt()).toLong() shl 56)
    }

    fun getNextLong(): Long {
        val res = getLong(pos)
        pos += 8
        return res
    }

    fun appendString(s: String) {
        val tmp = s.encodeToByteArray()
        appendInt(tmp.size)
        while (pos + tmp.size >= data.size) {
            data += ByteArray(data.size)
        }
        for (b in tmp) {
            data.set(pos, b)
            pos++
        }
    }

    fun getString(p: Int): String {
        val l = getInt(p)
        return data.decodeToString(p + 4, p + 4 + l, true)
    }

    fun getNextString(): String {
        val l = getNextInt()
        val res = data.decodeToString(pos, pos + l, true)
        pos += l
        return res
    }

    fun appendSpace(l: Int): Int {
        val res = pos
        pos += l
        return res
    }
}
