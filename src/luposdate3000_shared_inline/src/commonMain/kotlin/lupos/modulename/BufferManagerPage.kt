/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General public License for more details.
 *
 * You should have received a copy of the GNU General public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.modulename

import lupos.s00misc.SanityCheck

typealias BufferManagerPage = ByteArray

internal object BufferManagerPage {
    internal const val BUFFER_MANAGER_PAGE_SIZE_IN_BYTES: Int = 8192

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun createBufferManagerPage(): BufferManagerPage {
        val res = ByteArray(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES + 4)
        setPageID(-1)
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getData(data: BufferManagerPage): ByteArray {
        return data
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun copyInto(data: BufferManagerPage, destination: ByteArray, destinationOffset: Int, startIndex: Int, endIndex: Int) {
        SanityCheck.check { getPageID() != -1 }
        data.copyInto(destination, destinationOffset, startIndex, endIndex)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun copyFrom(data: BufferManagerPage, source: ByteArray, destinationOffset: Int, startIndex: Int, endIndex: Int) {
        SanityCheck.check { getPageID() != -1 }
        source.copyInto(data, destinationOffset, startIndex, endIndex)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getPageID(data: BufferManagerPage): Int {
        return (((data[BUFFER_MANAGER_PAGE_SIZE_IN_BYTES].toInt() and 0xFF) shl 24) or ((data[BUFFER_MANAGER_PAGE_SIZE_IN_BYTES + 1].toInt() and 0xFF) shl 16) or ((data[BUFFER_MANAGER_PAGE_SIZE_IN_BYTES + 2].toInt() and 0xFF) shl 8) or ((data[BUFFER_MANAGER_PAGE_SIZE_IN_BYTES + 3].toInt() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun setPageID(data: BufferManagerPage, value: Int) {
        SanityCheck.check { value == -1 || getPageID() == -1 }
        data[BUFFER_MANAGER_PAGE_SIZE_IN_BYTES] = ((value shr 24) and 0xFF).toByte()
        data[BUFFER_MANAGER_PAGE_SIZE_IN_BYTES + 1] = ((value shr 16) and 0xFF).toByte()
        data[BUFFER_MANAGER_PAGE_SIZE_IN_BYTES + 2] = ((value shr 8) and 0xFF).toByte()
        data[BUFFER_MANAGER_PAGE_SIZE_IN_BYTES + 3] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline operator fun get(data: BufferManagerPage, idx: Int): Byte {
        SanityCheck.check { getPageID() != -1 }
        return data[idx]
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline operator fun set(data: BufferManagerPage, idx: Int, value: Byte) {
        SanityCheck.check { getPageID() != -1 }
        data[idx] = value
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeInt1(data: BufferManagerPage, offset: Int, value: Int) {
        SanityCheck.check { getPageID() != -1 }
        data[offset] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeInt2(data: BufferManagerPage, offset: Int, value: Int) {
        SanityCheck.check { getPageID() != -1 }
        data[offset] = ((value shr 8) and 0xFF).toByte()
        data[offset + 1] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeInt3(data: BufferManagerPage, offset: Int, value: Int) {
        SanityCheck.check { getPageID() != -1 }
        data[offset] = ((value shr 16) and 0xFF).toByte()
        data[offset + 1] = ((value shr 8) and 0xFF).toByte()
        data[offset + 2] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeInt4(data: BufferManagerPage, offset: Int, value: Int) {
        SanityCheck.check { getPageID() != -1 }
        data[offset] = ((value shr 24) and 0xFF).toByte()
        data[offset + 1] = ((value shr 16) and 0xFF).toByte()
        data[offset + 2] = ((value shr 8) and 0xFF).toByte()
        data[offset + 3] = (value and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeIntX(data: BufferManagerPage, offset: Int, value: Int, count: Int) {
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
    internal inline fun writeLong8(data: BufferManagerPage, offset: Int, value: Long) {
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
    internal inline fun writeChar(data: BufferManagerPage, offset: Int, value: Char) {
        SanityCheck.check { getPageID() != -1 }
        val v = value.toInt()
        data[offset] = ((v shr 8) and 0xFF).toByte()
        data[offset + 1] = (v and 0xFF).toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readLong8(data: BufferManagerPage, offset: Int): Long {
        SanityCheck.check { getPageID() != -1 }
        return (((data[offset].toLong() and 0xFF) shl 56) or ((data[offset + 1].toLong() and 0xFF) shl 48) or ((data[offset + 2].toLong() and 0xFF) shl 40) or ((data[offset + 3].toLong() and 0xFF) shl 32) or ((data[offset + 4].toLong() and 0xFF) shl 24) or ((data[offset + 5].toLong() and 0xFF) shl 16) or ((data[offset + 6].toLong() and 0xFF) shl 8) or ((data[offset + 7].toLong() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readInt4(data: BufferManagerPage, offset: Int): Int {
        SanityCheck.check { getPageID() != -1 }
        return (((data[offset].toInt() and 0xFF) shl 24) or ((data[offset + 1].toInt() and 0xFF) shl 16) or ((data[offset + 2].toInt() and 0xFF) shl 8) or ((data[offset + 3].toInt() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readInt3(data: BufferManagerPage, offset: Int): Int {
        SanityCheck.check { getPageID() != -1 }
        return (((data[offset].toInt() and 0xFF) shl 16) or ((data[offset + 1].toInt() and 0xFF) shl 8) or ((data[offset + 2].toInt() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readInt2(data: BufferManagerPage, offset: Int): Int {
        SanityCheck.check { getPageID() != -1 }
        return (((data[offset].toInt() and 0xFF) shl 8) or ((data[offset + 1].toInt() and 0xFF)))
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readInt1(data: BufferManagerPage, offset: Int): Int {
        SanityCheck.check { getPageID() != -1 }
        return (data[offset].toInt() and 0xFF)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readIntX(data: BufferManagerPage, offset: Int, count: Int): Int {
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
    internal inline fun readChar(data: BufferManagerPage, offset: Int): Char {
        SanityCheck.check { getPageID() != -1 }
        return (((data[offset].toInt() and 0xFF) shl 8) or ((data[offset + 1].toInt() and 0xFF))).toChar()
    }
}
