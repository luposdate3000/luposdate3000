/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.shared.inline

public external class ArrayBuffer(size: Int)
public external class Int64Array(buffer: ArrayBuffer) {
    public operator fun get(i: Int): Long
    public operator fun set(i: Int, v: Long)
}

public external class Float64Array(buffer: ArrayBuffer) {
    public operator fun get(i: Int): Double
    public operator fun set(i: Int, v: Double)
}

public actual object ByteArrayHelper {
    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readDouble8(data: ByteArray, offset: Int): Double {
        var buffer = ArrayBuffer(8)
        var intView = Int64Array(buffer)
        var floatView = Float64Array(buffer)
        intView[0] = readLong8(data, offset)
        return floatView[0]
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeDouble8(data: ByteArray, offset: Int, value: Double) {
        var buffer = ArrayBuffer(8)
        var intView = Int64Array(buffer)
        var floatView = Float64Array(buffer)
        floatView[0] = value
        writeLong8(data, offset, intView[0])
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeInt1(data: ByteArray, offset: Int, value: Int) {
        data[offset] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeInt2(data: ByteArray, offset: Int, value: Int) {
        data[offset] = ((value shr 8) and 0xFF).toByte()
        data[offset + 1] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeInt3(data: ByteArray, offset: Int, value: Int) {
        data[offset] = ((value shr 16) and 0xFF).toByte()
        data[offset + 1] = ((value shr 8) and 0xFF).toByte()
        data[offset + 2] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeInt4(data: ByteArray, offset: Int, value: Int) {
        data[offset] = ((value shr 24) and 0xFF).toByte()
        data[offset + 1] = ((value shr 16) and 0xFF).toByte()
        data[offset + 2] = ((value shr 8) and 0xFF).toByte()
        data[offset + 3] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeIntX(data: ByteArray, offset: Int, value: Int, count: Int) {
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

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeLong8(data: ByteArray, offset: Int, value: Long) {
        data[offset] = ((value shr 56) and 0xFF).toByte()
        data[offset + 1] = ((value shr 48) and 0xFF).toByte()
        data[offset + 2] = ((value shr 40) and 0xFF).toByte()
        data[offset + 3] = ((value shr 32) and 0xFF).toByte()
        data[offset + 4] = ((value shr 24) and 0xFF).toByte()
        data[offset + 5] = ((value shr 16) and 0xFF).toByte()
        data[offset + 6] = ((value shr 8) and 0xFF).toByte()
        data[offset + 7] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeChar(data: ByteArray, offset: Int, value: Char) {
        val v = value.toInt()
        data[offset] = ((v shr 8) and 0xFF).toByte()
        data[offset + 1] = (v and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readLong8(data: ByteArray, offset: Int): Long {
        return (((data[offset].toLong() and 0xFF) shl 56) or ((data[offset + 1].toLong() and 0xFF) shl 48) or ((data[offset + 2].toLong() and 0xFF) shl 40) or ((data[offset + 3].toLong() and 0xFF) shl 32) or ((data[offset + 4].toLong() and 0xFF) shl 24) or ((data[offset + 5].toLong() and 0xFF) shl 16) or ((data[offset + 6].toLong() and 0xFF) shl 8) or ((data[offset + 7].toLong() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readInt4(data: ByteArray, offset: Int): Int {
        return (((data[offset].toInt() and 0xFF) shl 24) or ((data[offset + 1].toInt() and 0xFF) shl 16) or ((data[offset + 2].toInt() and 0xFF) shl 8) or ((data[offset + 3].toInt() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readInt3(data: ByteArray, offset: Int): Int {
        return (((data[offset].toInt() and 0xFF) shl 16) or ((data[offset + 1].toInt() and 0xFF) shl 8) or ((data[offset + 2].toInt() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readInt2(data: ByteArray, offset: Int): Int {
        return (((data[offset].toInt() and 0xFF) shl 8) or ((data[offset + 1].toInt() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readInt1(data: ByteArray, offset: Int): Int {
        return (data[offset].toInt() and 0xFF)
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readIntX(data: ByteArray, offset: Int, count: Int): Int {
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

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeLong1(data: ByteArray, offset: Int, value: Long) {
        data[offset + 0] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeLong2(data: ByteArray, offset: Int, value: Long) {
        data[offset + 0] = ((value shr 8) and 0xFF).toByte()
        data[offset + 1] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeLong3(data: ByteArray, offset: Int, value: Long) {
        data[offset + 0] = ((value shr 16) and 0xFF).toByte()
        data[offset + 1] = ((value shr 8) and 0xFF).toByte()
        data[offset + 2] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeLong4(data: ByteArray, offset: Int, value: Long) {
        data[offset + 0] = ((value shr 24) and 0xFF).toByte()
        data[offset + 1] = ((value shr 16) and 0xFF).toByte()
        data[offset + 2] = ((value shr 8) and 0xFF).toByte()
        data[offset + 3] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeLong5(data: ByteArray, offset: Int, value: Long) {
        data[offset + 0] = ((value shr 32) and 0xFF).toByte()
        data[offset + 1] = ((value shr 24) and 0xFF).toByte()
        data[offset + 2] = ((value shr 16) and 0xFF).toByte()
        data[offset + 3] = ((value shr 8) and 0xFF).toByte()
        data[offset + 4] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeLong6(data: ByteArray, offset: Int, value: Long) {
        data[offset + 0] = ((value shr 40) and 0xFF).toByte()
        data[offset + 1] = ((value shr 32) and 0xFF).toByte()
        data[offset + 2] = ((value shr 24) and 0xFF).toByte()
        data[offset + 3] = ((value shr 16) and 0xFF).toByte()
        data[offset + 4] = ((value shr 8) and 0xFF).toByte()
        data[offset + 5] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeLong7(data: ByteArray, offset: Int, value: Long) {
        data[offset + 0] = ((value shr 48) and 0xFF).toByte()
        data[offset + 1] = ((value shr 40) and 0xFF).toByte()
        data[offset + 2] = ((value shr 32) and 0xFF).toByte()
        data[offset + 3] = ((value shr 24) and 0xFF).toByte()
        data[offset + 4] = ((value shr 16) and 0xFF).toByte()
        data[offset + 5] = ((value shr 8) and 0xFF).toByte()
        data[offset + 6] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeLongX(data: ByteArray, offset: Int, value: Long, count: Int) {
        when (count) {
            0 -> {
            }
            1 -> {
                writeLong1(data, offset, value)
            }
            2 -> {
                writeLong2(data, offset, value)
            }
            3 -> {
                writeLong3(data, offset, value)
            }
            4 -> {
                writeLong4(data, offset, value)
            }
            5 -> {
                writeLong5(data, offset, value)
            }
            6 -> {
                writeLong6(data, offset, value)
            }
            7 -> {
                writeLong7(data, offset, value)
            }
            else -> {
                writeLong8(data, offset, value)
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readLong1(data: ByteArray, offset: Int): Long {
        return (((data[offset + 0].toLong() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readLong2(data: ByteArray, offset: Int): Long {
        return (((data[offset + 0].toLong() and 0xFF) shl 8) or ((data[offset + 1].toLong() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readLong3(data: ByteArray, offset: Int): Long {
        return (((data[offset + 0].toLong() and 0xFF) shl 16) or ((data[offset + 1].toLong() and 0xFF) shl 8) or ((data[offset + 2].toLong() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readLong4(data: ByteArray, offset: Int): Long {
        return (((data[offset + 0].toLong() and 0xFF) shl 24) or ((data[offset + 1].toLong() and 0xFF) shl 16) or ((data[offset + 2].toLong() and 0xFF) shl 8) or ((data[offset + 3].toLong() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readLong5(data: ByteArray, offset: Int): Long {
        return (((data[offset + 0].toLong() and 0xFF) shl 32) or ((data[offset + 1].toLong() and 0xFF) shl 24) or ((data[offset + 2].toLong() and 0xFF) shl 16) or ((data[offset + 3].toLong() and 0xFF) shl 8) or ((data[offset + 4].toLong() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readLong6(data: ByteArray, offset: Int): Long {
        return (((data[offset + 0].toLong() and 0xFF) shl 40) or ((data[offset + 1].toLong() and 0xFF) shl 32) or ((data[offset + 2].toLong() and 0xFF) shl 24) or ((data[offset + 3].toLong() and 0xFF) shl 16) or ((data[offset + 4].toLong() and 0xFF) shl 8) or ((data[offset + 5].toLong() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readLong7(data: ByteArray, offset: Int): Long {
        return (((data[offset + 0].toLong() and 0xFF) shl 48) or ((data[offset + 1].toLong() and 0xFF) shl 40) or ((data[offset + 2].toLong() and 0xFF) shl 32) or ((data[offset + 3].toLong() and 0xFF) shl 24) or ((data[offset + 4].toLong() and 0xFF) shl 16) or ((data[offset + 5].toLong() and 0xFF) shl 8) or ((data[offset + 6].toLong() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readLongX(data: ByteArray, offset: Int, count: Int): Long {
        when (count) {
            0 -> {
                return 0
            }
            1 -> {
                return readLong1(data, offset)
            }
            2 -> {
                return readLong2(data, offset)
            }
            3 -> {
                return readLong3(data, offset)
            }
            4 -> {
                return readLong4(data, offset)
            }
            5 -> {
                return readLong5(data, offset)
            }
            6 -> {
                return readLong6(data, offset)
            }
            7 -> {
                return readLong7(data, offset)
            }
            else -> {
                return readLong8(data, offset)
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readChar(data: ByteArray, offset: Int): Char {
        return (((data[offset].toInt() and 0xFF) shl 8) or ((data[offset + 1].toInt() and 0xFF))).toChar()
    }
}
