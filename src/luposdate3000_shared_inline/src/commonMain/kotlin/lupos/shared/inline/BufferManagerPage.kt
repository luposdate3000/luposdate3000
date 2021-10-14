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

import lupos.shared.BufferManagerPageWrapper
import lupos.shared.SanityCheck

internal object BufferManagerPage {
    internal const val BUFFER_MANAGER_PAGE_SIZE_IN_BYTES: Int = 8192

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getBuf(buf: BufferManagerPageWrapper): ByteArray = buf.data

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun create(): BufferManagerPageWrapper {
        val data = BufferManagerPageWrapper(ByteArray(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES + 4))
        setPageID(data, -1)
        return data
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun copyInto(data2: BufferManagerPageWrapper, destination: ByteArray, destinationOffset: Int, startIndex: Int, endIndex: Int) {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:38"/*SOURCE_FILE_END*/ },
            { getPageID(data2) != -1 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:42"/*SOURCE_FILE_END*/ },
            { startIndex >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:46"/*SOURCE_FILE_END*/ },
            { endIndex <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        data.copyInto(destination, destinationOffset, startIndex, endIndex)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun copyFrom(data2: BufferManagerPageWrapper, source: ByteArray, destinationOffset: Int, startIndex: Int, endIndex: Int) {
        val data = data2.data
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:55"/*SOURCE_FILE_END*/ }, { getPageID(data2) != -1 })
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:57"/*SOURCE_FILE_END*/ },
            { destinationOffset >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:61"/*SOURCE_FILE_END*/ },
            { destinationOffset + endIndex - startIndex <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        source.copyInto(data, destinationOffset, startIndex, endIndex)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getPageID(data2: BufferManagerPageWrapper): Int {
        val data = data2.data
        return ByteArrayHelper.readInt4(data, BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun setPageID(data2: BufferManagerPageWrapper, value: Int) {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:77"/*SOURCE_FILE_END*/ },
            { value == -1 || getPageID(data2) == -1 }
        )
        ByteArrayHelper.writeInt4(data, BUFFER_MANAGER_PAGE_SIZE_IN_BYTES, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeInt1(data2: BufferManagerPageWrapper, offset: Int, value: Int) {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:87"/*SOURCE_FILE_END*/ },
            { getPageID(data2) != -1 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:91"/*SOURCE_FILE_END*/ },
            { offset >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:95"/*SOURCE_FILE_END*/ },
            { offset < BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        ByteArrayHelper.writeInt1(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeInt2(data2: BufferManagerPageWrapper, offset: Int, value: Int) {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:105"/*SOURCE_FILE_END*/ },
            { getPageID(data2) != -1 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:109"/*SOURCE_FILE_END*/ },
            { offset >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:113"/*SOURCE_FILE_END*/ },
            { offset + 2 <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        ByteArrayHelper.writeInt2(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeInt3(data2: BufferManagerPageWrapper, offset: Int, value: Int) {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:123"/*SOURCE_FILE_END*/ },
            { getPageID(data2) != -1 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:127"/*SOURCE_FILE_END*/ },
            { offset >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:131"/*SOURCE_FILE_END*/ },
            { offset + 3 <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        ByteArrayHelper.writeInt3(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeInt4(data2: BufferManagerPageWrapper, offset: Int, value: Int) {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:141"/*SOURCE_FILE_END*/ },
            { getPageID(data2) != -1 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:145"/*SOURCE_FILE_END*/ },
            { offset >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:149"/*SOURCE_FILE_END*/ },
            { offset + 4 <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        ByteArrayHelper.writeInt4(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeIntX(data2: BufferManagerPageWrapper, offset: Int, value: Int, count: Int) {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:159"/*SOURCE_FILE_END*/ },
            { getPageID(data2) != -1 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:163"/*SOURCE_FILE_END*/ },
            { offset >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:167"/*SOURCE_FILE_END*/ },
            { offset + count <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        ByteArrayHelper.writeIntX(data, offset, value, count)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeLongX(data2: BufferManagerPageWrapper, offset: Int, value: Long, count: Int) {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:177"/*SOURCE_FILE_END*/ },
            { getPageID(data2) != -1 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:181"/*SOURCE_FILE_END*/ },
            { offset >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:185"/*SOURCE_FILE_END*/ },
            { offset + count <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        ByteArrayHelper.writeLongX(data, offset, value, count)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeLong8(data2: BufferManagerPageWrapper, offset: Int, value: Long) {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:195"/*SOURCE_FILE_END*/ },
            { getPageID(data2) != -1 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:199"/*SOURCE_FILE_END*/ },
            { offset >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:203"/*SOURCE_FILE_END*/ },
            { offset + 8 <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        ByteArrayHelper.writeLong8(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeChar(data2: BufferManagerPageWrapper, offset: Int, value: Char) {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:213"/*SOURCE_FILE_END*/ },
            { getPageID(data2) != -1 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:217"/*SOURCE_FILE_END*/ },
            { offset >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:221"/*SOURCE_FILE_END*/ },
            { offset + 2 <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        ByteArrayHelper.writeChar(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readLong8(data2: BufferManagerPageWrapper, offset: Int): Long {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:231"/*SOURCE_FILE_END*/ },
            { getPageID(data2) != -1 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:235"/*SOURCE_FILE_END*/ },
            { offset >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:239"/*SOURCE_FILE_END*/ },
            { offset + 8 <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        return ByteArrayHelper.readLong8(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readInt4(data2: BufferManagerPageWrapper, offset: Int): Int {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:249"/*SOURCE_FILE_END*/ },
            { getPageID(data2) != -1 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:253"/*SOURCE_FILE_END*/ },
            { offset >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:257"/*SOURCE_FILE_END*/ },
            { offset + 4 <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        return ByteArrayHelper.readInt4(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readInt3(data2: BufferManagerPageWrapper, offset: Int): Int {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:267"/*SOURCE_FILE_END*/ },
            { getPageID(data2) != -1 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:271"/*SOURCE_FILE_END*/ },
            { offset >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:275"/*SOURCE_FILE_END*/ },
            { offset + 3 <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        return ByteArrayHelper.readInt3(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readInt2(data2: BufferManagerPageWrapper, offset: Int): Int {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:285"/*SOURCE_FILE_END*/ },
            { getPageID(data2) != -1 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:289"/*SOURCE_FILE_END*/ },
            { offset >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:293"/*SOURCE_FILE_END*/ },
            { offset + 2 <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        return ByteArrayHelper.readInt2(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readInt1(data2: BufferManagerPageWrapper, offset: Int): Int {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:303"/*SOURCE_FILE_END*/ },
            { getPageID(data2) != -1 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:307"/*SOURCE_FILE_END*/ },
            { offset >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:311"/*SOURCE_FILE_END*/ },
            { offset < BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        return ByteArrayHelper.readInt1(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readIntX(data2: BufferManagerPageWrapper, offset: Int, count: Int): Int {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:321"/*SOURCE_FILE_END*/ },
            { getPageID(data2) != -1 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:325"/*SOURCE_FILE_END*/ },
            { offset >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:329"/*SOURCE_FILE_END*/ },
            { offset + count <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        return ByteArrayHelper.readIntX(data, offset, count)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readLongX(data2: BufferManagerPageWrapper, offset: Int, count: Int): Long {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:339"/*SOURCE_FILE_END*/ },
            { getPageID(data2) != -1 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:343"/*SOURCE_FILE_END*/ },
            { offset >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:347"/*SOURCE_FILE_END*/ },
            { offset + count <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        return ByteArrayHelper.readLongX(data, offset, count)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readChar(data2: BufferManagerPageWrapper, offset: Int): Char {
        val data = data2.data
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:357"/*SOURCE_FILE_END*/ },
            { getPageID(data2) != -1 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:361"/*SOURCE_FILE_END*/ },
            { offset >= 0 }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/BufferManagerPage.kt:365"/*SOURCE_FILE_END*/ },
            { offset + 2 <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        )
        return ByteArrayHelper.readChar(data, offset)
    }
}
