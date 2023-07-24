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

import lupos.shared.BufferManagerPageWrapperDebug
import lupos.shared.SanityCheck

public object BufferManagerPageDebug {
    public const val BUFFER_MANAGER_PAGE_SIZE_IN_BYTES: Int = 8192

    @Suppress("NOTHING_TO_INLINE")
    public inline fun getBuf(buf: BufferManagerPageWrapperDebug): ByteArray = buf.data

    @Suppress("NOTHING_TO_INLINE")
    public inline fun create(): BufferManagerPageWrapperDebug {
        val data = BufferManagerPageWrapperDebug(ByteArray(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES + 4))
        setPageID(data, -1)
        return data
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun copyInto(data2: BufferManagerPageWrapperDebug, destination: ByteArray, destinationOffset: Int, startIndex: Int, endIndex: Int) {
        val data = data2.data
        data.copyInto(destination, destinationOffset, startIndex, endIndex)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun copyFrom(data2: BufferManagerPageWrapperDebug, source: ByteArray, destinationOffset: Int, startIndex: Int, endIndex: Int) {
        val data = data2.data
        source.copyInto(data, destinationOffset, startIndex, endIndex)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun getPageID(data2: BufferManagerPageWrapperDebug): Int {
        val data = data2.data
        return ByteArrayHelper.readInt4(data, BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun setPageID(data2: BufferManagerPageWrapperDebug, value: Int) {
        val data = data2.data
        ByteArrayHelper.writeInt4(data, BUFFER_MANAGER_PAGE_SIZE_IN_BYTES, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeInt1(data2: BufferManagerPageWrapperDebug, offset: Int, value: Int) {
        val data = data2.data
        ByteArrayHelper.writeInt1(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeInt2(data2: BufferManagerPageWrapperDebug, offset: Int, value: Int) {
        val data = data2.data
        ByteArrayHelper.writeInt2(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeInt3(data2: BufferManagerPageWrapperDebug, offset: Int, value: Int) {
        val data = data2.data
        ByteArrayHelper.writeInt3(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeInt4(data2: BufferManagerPageWrapperDebug, offset: Int, value: Int) {
        val data = data2.data
        ByteArrayHelper.writeInt4(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeIntX(data2: BufferManagerPageWrapperDebug, offset: Int, value: Int, count: Int) {
        val data = data2.data
        ByteArrayHelper.writeIntX(data, offset, value, count)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeLongX(data2: BufferManagerPageWrapperDebug, offset: Int, value: Long, count: Int) {
        val data = data2.data
        ByteArrayHelper.writeLongX(data, offset, value, count)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeLong8(data2: BufferManagerPageWrapperDebug, offset: Int, value: Long) {
        val data = data2.data
        ByteArrayHelper.writeLong8(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeChar(data2: BufferManagerPageWrapperDebug, offset: Int, value: Char) {
        val data = data2.data
        ByteArrayHelper.writeChar(data, offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readLong8(data2: BufferManagerPageWrapperDebug, offset: Int): Long {
        val data = data2.data
        return ByteArrayHelper.readLong8(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readInt4(data2: BufferManagerPageWrapperDebug, offset: Int): Int {
        val data = data2.data
        return ByteArrayHelper.readInt4(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readInt3(data2: BufferManagerPageWrapperDebug, offset: Int): Int {
        val data = data2.data
        return ByteArrayHelper.readInt3(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readInt2(data2: BufferManagerPageWrapperDebug, offset: Int): Int {
        val data = data2.data
        return ByteArrayHelper.readInt2(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readInt1(data2: BufferManagerPageWrapperDebug, offset: Int): Int {
        val data = data2.data
        return ByteArrayHelper.readInt1(data, offset)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readIntX(data2: BufferManagerPageWrapperDebug, offset: Int, count: Int): Int {
        val data = data2.data
        return ByteArrayHelper.readIntX(data, offset, count)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readLongX(data2: BufferManagerPageWrapperDebug, offset: Int, count: Int): Long {
        val data = data2.data
        return ByteArrayHelper.readLongX(data, offset, count)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readChar(data2: BufferManagerPageWrapperDebug, offset: Int): Char {
        val data = data2.data
        return ByteArrayHelper.readChar(data, offset)
    }
}
