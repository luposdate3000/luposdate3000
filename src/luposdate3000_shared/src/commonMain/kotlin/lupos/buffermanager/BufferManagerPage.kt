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
package lupos.buffermanager

import lupos.s00misc.SanityCheck

public const val BUFFER_MANAGER_PAGE_SIZE_IN_BYTES: Int = 8192
public fun createBufferManagerPage(): BufferManagerPage {
    return BufferManagerPage(ByteArray(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES + 4))
}

public inline class BufferManagerPage internal constructor(public val data: ByteArray) {
    public inline fun getPageID(): Int {
        return readInt4(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
    }

    public inline fun setPageID(value: Int) {
        SanityCheck.check { value == -1 || getPageID() == -1 }
        writeInt4(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun get(idx: Int): Byte {
        SanityCheck.check { getPageID() != -1 }
        return data[idx]
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun set(idx: Int, value: Byte) {
        SanityCheck.check { getPageID() != -1 }
        data[idx] = value
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeInt1(offset: Int, value: Int) {
        SanityCheck.check { getPageID() != -1 }
        data[offset] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeInt2(offset: Int, value: Int) {
        SanityCheck.check { getPageID() != -1 }
        data[offset] = ((value shr 8) and 0xFF).toByte()
        data[offset + 1] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeInt3(offset: Int, value: Int) {
        SanityCheck.check { getPageID() != -1 }
        data[offset] = ((value shr 16) and 0xFF).toByte()
        data[offset + 1] = ((value shr 8) and 0xFF).toByte()
        data[offset + 2] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeInt4(offset: Int, value: Int) {
        SanityCheck.check { getPageID() != -1 }
        data[offset] = ((value shr 24) and 0xFF).toByte()
        data[offset + 1] = ((value shr 16) and 0xFF).toByte()
        data[offset + 2] = ((value shr 8) and 0xFF).toByte()
        data[offset + 3] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeIntX(offset: Int, value: Int, count: Int) {
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

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeLong8(offset: Int, value: Long) {
        SanityCheck.check { getPageID() != -1 }
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
    public inline fun writeChar(offset: Int, value: Char) {
        SanityCheck.check { getPageID() != -1 }
        val v = value.toInt()
        data[offset] = ((v shr 8) and 0xFF).toByte()
        data[offset + 1] = (v and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readLong8(offset: Int): Long {
        SanityCheck.check { getPageID() != -1 }
        return (((data[offset].toLong() and 0xFF) shl 56) or ((data[offset + 1].toLong() and 0xFF) shl 48) or ((data[offset + 2].toLong() and 0xFF) shl 40) or ((data[offset + 3].toLong() and 0xFF) shl 32) or ((data[offset + 4].toLong() and 0xFF) shl 24) or ((data[offset + 5].toLong() and 0xFF) shl 16) or ((data[offset + 6].toLong() and 0xFF) shl 8) or ((data[offset + 7].toLong() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readInt4(offset: Int): Int {
        SanityCheck.check { getPageID() != -1 }
        return (((data[offset].toInt() and 0xFF) shl 24) or ((data[offset + 1].toInt() and 0xFF) shl 16) or ((data[offset + 2].toInt() and 0xFF) shl 8) or ((data[offset + 3].toInt() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readInt3(offset: Int): Int {
        SanityCheck.check { getPageID() != -1 }
        return (((data[offset].toInt() and 0xFF) shl 16) or ((data[offset + 1].toInt() and 0xFF) shl 8) or ((data[offset + 2].toInt() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readInt2(offset: Int): Int {
        SanityCheck.check { getPageID() != -1 }
        return (((data[offset].toInt() and 0xFF) shl 8) or ((data[offset + 1].toInt() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readInt1(offset: Int): Int {
        SanityCheck.check { getPageID() != -1 }
        return (data[offset].toInt() and 0xFF)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readIntX(offset: Int, count: Int): Int {
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

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readChar(offset: Int): Char {
        SanityCheck.check { getPageID() != -1 }
        return (((data[offset].toInt() and 0xFF) shl 8) or ((data[offset + 1].toInt() and 0xFF))).toChar()
    }
}
