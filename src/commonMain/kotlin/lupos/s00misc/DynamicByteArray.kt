package lupos.s00misc

import kotlinx.coroutines.channels.*
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s09physicalOperators.POPBase


class DynamicByteArrayAsyncWrite {
    val channel = Channel<Pair<ByteArray, Int>>(Channel.UNLIMITED)
    var buffer = DynamicByteArray()

    constructor() {
        buffer.appendSpace(4)
    }

    suspend fun flush() {
        println("DynamicByteArrayAsyncWrite.flush start")
        buffer.setInt(buffer.pos, 0)
        println("flush :: ${buffer.pos} ${buffer.data.size}")
        channel.send(buffer.finish())
        buffer = DynamicByteArray()
        buffer.appendSpace(4)
        println("DynamicByteArrayAsyncWrite.flush end")
    }

    suspend fun finish() {
        println("DynamicByteArrayAsyncWrite.finish start")
        buffer.setInt(buffer.pos, 0)
        channel.send(buffer.finish())
        channel.close()
        println("DynamicByteArrayAsyncWrite.finish end")
    }
}

class DynamicByteArrayAsyncRead {
    val channel = Channel<ByteArray>(Channel.UNLIMITED)
    var buffer: DynamicByteArray? = null
    var maxlen = 0

    constructor() {
    }

    suspend fun fetch() {
        println("DynamicByteArrayAsyncRead.fetch start")
        if (buffer == null || buffer!!.pos >= buffer!!.data.size) {
            buffer = DynamicByteArray(channel.receive())
println("fetch a ${buffer!!.data.size}")
            maxlen = buffer!!.getNextInt()
println("fetch b ${maxlen}")
        } else {
		val newlen=maxlen +  buffer!!.getNextInt()
println("fetch update :: $maxlen $newlen ${buffer!!.data.size}")
if(newlen>buffer!!.data.size){
val remaining=buffer!!.data.size-maxlen
println("fetch c $remaining")
	val nextB=channel.receive()
println("fetch d ${remaining+nextB.size}")
	val buf=ByteArray(remaining+nextB.size)
println("fetch e $maxlen ${buffer!!.data.size}")
	buffer!!.data.copyInto(buf, 0, maxlen, buffer!!.data.size)
println("fetch f $remaining ${nextB.size}")
	nextB.copyInto(buf, remaining, 0, nextB.size)
	buffer = DynamicByteArray(buf)
	maxlen = buffer!!.getNextInt()
println("fetch h $maxlen ${buf.size}")
}else
            maxlen =newlen
        }
        require(maxlen <= buffer!!.data.size)
        println("DynamicByteArrayAsyncRead.fetch end")
    }

    suspend fun finish() {
        println("DynamicByteArrayAsyncRead.finish")
    }
}

@UseExperimental(kotlin.ExperimentalStdlibApi::class)
class DynamicByteArray {
    var data: ByteArray
    var pos = 0

    constructor() {
        data = ByteArray(100)
    }

    constructor(data: ByteArray) {
        this.data = data
    }

    fun finish(): Pair<ByteArray, Int> {
        return Pair(data, pos)
    }

    fun setInt(i: Int, p: Int) {
        data.set(p, i.toByte())
        data.set(p + 1, (i ushr 8).toByte())
        data.set(p + 2, (i ushr 16).toByte())
        data.set(p + 3, (i ushr 24).toByte())
    }

    fun appendInt(i: Int) {
        if (pos + 4 >= data.size)
            data += ByteArray(data.size)
        setInt(i, pos)
        pos += 4
    }

    fun getInt(p: Int): Int {
        val res = ((0xFF and data[p].toInt())) or
                ((0xFF and data[p + 1].toInt()) shl 8) or
                ((0xFF and data[p + 2].toInt()) shl 16) or
                ((0xFF and data[p + 3].toInt()) shl 24)
        return res
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
        if (pos + 8 >= data.size)
            data += ByteArray(data.size)
        setLong(i, pos)
        pos += 8
    }

    fun getLong(p: Int): Long {
        val res = ((0xFF and data[p].toInt()).toLong()) or
                ((0xFF and data[p + 1].toInt()).toLong() shl 8) or
                ((0xFF and data[p + 2].toInt()).toLong() shl 16) or
                ((0xFF and data[p + 3].toInt()).toLong() shl 24) or
                ((0xFF and data[p + 4].toInt()).toLong() shl 32) or
                ((0xFF and data[p + 5].toInt()).toLong() shl 40) or
                ((0xFF and data[p + 6].toInt()).toLong() shl 48) or
                ((0xFF and data[p + 7].toInt()).toLong() shl 56)
        return res
    }

    fun getNextLong(): Long {
        val res = getLong(pos)
        pos += 8
        return res
    }

    fun setByte(b: Byte, p: Int) {
        data.set(p, b)
    }

    fun appendByte(b: Byte) {
        if (pos + 1 >= data.size)
            data += ByteArray(data.size)
        setByte(b, pos)
        pos++
    }

    fun getByte(p: Int): Byte {
        return data[p]
    }

    fun getNextByte(): Byte {
        val res = getByte(pos)
        pos += 1
        return res
    }

    fun appendString(s: String) {
        val tmp = s.encodeToByteArray()
        appendInt(tmp.size)
        while (pos + tmp.size >= data.size)
            data += ByteArray(data.size)
        for (b in tmp) {
            data.set(pos, b)
            pos++
        }
    }

    fun getString(p: Int): String {
        val p = pos
        val l = getInt(p)
        val res = data.decodeToString(p + 4, pos + 5, true)
        return res
    }

    fun getNextString(): String {
        val l = getNextInt()
        val res = data.decodeToString(pos, pos + l, true)
        pos += l
        return res
    }

    fun rewind() {
        pos = 4
    }

    fun appendSpace(l: Int): Int {
        val res = pos
        pos += l
        return res
    }
    fun skip(l: Int) {
        pos += l
    }

    fun getPosition(): Int {
        return pos
    }
}

