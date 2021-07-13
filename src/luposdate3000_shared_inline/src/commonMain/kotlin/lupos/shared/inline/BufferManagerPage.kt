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
package lupos.shared.inline

import lupos.shared.SanityCheck

internal object BufferManagerPage {
    internal const val BUFFER_MANAGER_PAGE_SIZE_IN_BYTES: Int = 8192

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun create(): ByteArray {
        val data = ByteArray(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES + 4)
        setPageID(data, -1)
        return data
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun copyInto(data: ByteArray, destination: ByteArray, destinationOffset: Int, startIndex: Int, endIndex: Int) {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:32"/*SOURCE_FILE_END*/ }, { getPageID(data) != -1 })
        data.copyInto(destination, destinationOffset, startIndex, endIndex)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun copyFrom(data: ByteArray, source: ByteArray, destinationOffset: Int, startIndex: Int, endIndex: Int) {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:38"/*SOURCE_FILE_END*/ }, { getPageID(data) != -1 })
        source.copyInto(data, destinationOffset, startIndex, endIndex)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getPageID(data: ByteArray): Int {
        return ByteArrayHelper.readInt4(data, BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun setPageID(data: ByteArray, value: Int) {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:49"/*SOURCE_FILE_END*/ }, { value == -1 || getPageID(data) == -1 })
        ByteArrayHelper.writeInt4(data, BUFFER_MANAGER_PAGE_SIZE_IN_BYTES, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeInt1(data: ByteArray, offset: Int, value: Int) {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:55"/*SOURCE_FILE_END*/ }, { getPageID(data) != -1 })
        ByteArrayHelper.writeInt1(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeInt2(data: ByteArray, offset: Int, value: Int) {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:61"/*SOURCE_FILE_END*/ }, { getPageID(data) != -1 })
        ByteArrayHelper.writeInt2(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeInt3(data: ByteArray, offset: Int, value: Int) {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:67"/*SOURCE_FILE_END*/ }, { getPageID(data) != -1 })
        ByteArrayHelper.writeInt3(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeInt4(data: ByteArray, offset: Int, value: Int) {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:73"/*SOURCE_FILE_END*/ }, { getPageID(data) != -1 })
        ByteArrayHelper.writeInt4(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeIntX(data: ByteArray, offset: Int, value: Int, count: Int) {
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
    internal inline fun writeLong8(data: ByteArray, offset: Int, value: Long) {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:99"/*SOURCE_FILE_END*/ }, { getPageID(data) != -1 })
        ByteArrayHelper.writeLong8(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeChar(data: ByteArray, offset: Int, value: Char) {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:105"/*SOURCE_FILE_END*/ }, { getPageID(data) != -1 })
        ByteArrayHelper.writeChar(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readLong8(data: ByteArray, offset: Int): Long {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:111"/*SOURCE_FILE_END*/ }, { getPageID(data) != -1 })
        return ByteArrayHelper.readLong8(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readInt4(data: ByteArray, offset: Int): Int {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:117"/*SOURCE_FILE_END*/ }, { getPageID(data) != -1 })
        return ByteArrayHelper.readInt4(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readInt3(data: ByteArray, offset: Int): Int {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:123"/*SOURCE_FILE_END*/ }, { getPageID(data) != -1 })
        return ByteArrayHelper.readInt3(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readInt2(data: ByteArray, offset: Int): Int {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:129"/*SOURCE_FILE_END*/ }, { getPageID(data) != -1 })
        return ByteArrayHelper.readInt2(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readInt1(data: ByteArray, offset: Int): Int {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:135"/*SOURCE_FILE_END*/ }, { getPageID(data) != -1 })
        return ByteArrayHelper.readInt1(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readIntX(data: ByteArray, offset: Int, count: Int): Int {
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
    internal inline fun readChar(data: ByteArray, offset: Int): Char {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:162"/*SOURCE_FILE_END*/ }, { getPageID(data) != -1 })
        return ByteArrayHelper.readChar(data, offset)
    }
}
