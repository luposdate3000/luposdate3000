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

import lupos.shared.BufferManagerPageWrapperRelease

internal object BufferManagerPageRelease {
    internal const val BUFFER_MANAGER_PAGE_SIZE_IN_BYTES: Int = 8192

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getBuf(buf: BufferManagerPageWrapperRelease): ByteArray = buf

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun create(): BufferManagerPageWrapperRelease {
        val data = BufferManagerPageWrapperRelease(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES + 4)
        setPageID(data, -1)
        return data
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun copyInto(data: BufferManagerPageWrapperRelease, destination: ByteArray, destinationOffset: Int, startIndex: Int, endIndex: Int) {
        data.copyInto(destination, destinationOffset, startIndex, endIndex)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun copyFrom(data: BufferManagerPageWrapperRelease, source: ByteArray, destinationOffset: Int, startIndex: Int, endIndex: Int) {
        source.copyInto(data, destinationOffset, startIndex, endIndex)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getPageID(data: BufferManagerPageWrapperRelease): Int {
        return ByteArrayHelper.readInt4(data, BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun setPageID(data: BufferManagerPageWrapperRelease, value: Int) {
        ByteArrayHelper.writeInt4(data, BUFFER_MANAGER_PAGE_SIZE_IN_BYTES, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeInt1(data: BufferManagerPageWrapperRelease, offset: Int, value: Int) {
        ByteArrayHelper.writeInt1(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeInt2(data: BufferManagerPageWrapperRelease, offset: Int, value: Int) {
        ByteArrayHelper.writeInt2(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeInt3(data: BufferManagerPageWrapperRelease, offset: Int, value: Int) {
        ByteArrayHelper.writeInt3(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeInt4(data: BufferManagerPageWrapperRelease, offset: Int, value: Int) {
        ByteArrayHelper.writeInt4(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeIntX(data: BufferManagerPageWrapperRelease, offset: Int, value: Int, count: Int) {
        ByteArrayHelper.writeIntX(data, offset, value, count)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeLongX(data: BufferManagerPageWrapperRelease, offset: Int, value: Long, count: Int) {
        ByteArrayHelper.writeLongX(data, offset, value, count)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeLong8(data: BufferManagerPageWrapperRelease, offset: Int, value: Long) {
        ByteArrayHelper.writeLong8(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeChar(data: BufferManagerPageWrapperRelease, offset: Int, value: Char) {
        ByteArrayHelper.writeChar(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readLong8(data: BufferManagerPageWrapperRelease, offset: Int): Long {
        return ByteArrayHelper.readLong8(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readInt4(data: BufferManagerPageWrapperRelease, offset: Int): Int {
        return ByteArrayHelper.readInt4(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readInt3(data: BufferManagerPageWrapperRelease, offset: Int): Int {
        return ByteArrayHelper.readInt3(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readInt2(data: BufferManagerPageWrapperRelease, offset: Int): Int {
        return ByteArrayHelper.readInt2(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readInt1(data: BufferManagerPageWrapperRelease, offset: Int): Int {
        return ByteArrayHelper.readInt1(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readIntX(data: BufferManagerPageWrapperRelease, offset: Int, count: Int): Int {
        return ByteArrayHelper.readIntX(data, offset, count)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readLongX(data: BufferManagerPageWrapperRelease, offset: Int, count: Int): Long {
        return ByteArrayHelper.readLongX(data, offset, count)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readChar(data: BufferManagerPageWrapperRelease, offset: Int): Char {
        return ByteArrayHelper.readChar(data, offset)
    }
}
