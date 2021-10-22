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
package lupos.shared.inline.dynamicArray

import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.ByteArrayHelper2
public object ByteArrayWrapperExt {
    @Suppress("NOTHING_TO_INLINE")
    public inline fun getSize(data: ByteArrayWrapper): Int {
        return data.size_
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun getBuf(data: ByteArrayWrapper): ByteArray {
        return data.buf_
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun setSize(data: ByteArrayWrapper, c: Int, copy: Boolean) {
        data.size_ = c
        if (c > data.buf_.size) {
            if (copy) {
                val oldBuf = data.buf_
                data.buf_ = ByteArray(c)
                oldBuf.copyInto(data.buf_)
            } else {
                data.buf_ = ByteArray(c)
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun commonBytes(a: ByteArrayWrapper, b: ByteArrayWrapper): Int {
        var i = 0
        while (i < a.size_ && i < b.size_) {
            if (a.buf_[i] == b.buf_[i]) {
                i++
            } else {
                break
            }
        }
        return i
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun copyInto(a: ByteArrayWrapper, b: ByteArrayWrapper, copy: Boolean) {
        setSize(b, a.size_, copy)
        a.buf_.copyInto(b.buf_, 0, 0, a.size_)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun appendTo(a: ByteArrayWrapper, b: ByteArrayWrapper) {
        val offset = b.size_
        setSize(b, b.size_ + a.size_, true)
        a.buf_.copyInto(b.buf_, offset, 0, a.size_)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun appendTo(a: ByteArray, b: ByteArrayWrapper) {
        val offset = b.size_
        setSize(b, b.size_ + a.size, true)
        a.copyInto(b.buf_, offset, 0, a.size)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun appendTo(a: ByteArray, a_size: Int, b: ByteArrayWrapper) {
        val offset = b.size_
        setSize(b, b.size_ + a_size, true)
        a.copyInto(b.buf_, offset, 0, a_size)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun compare_slow(a: ByteArrayWrapper, b: ByteArrayWrapper): Int {
        var res = 0
        var i = 0
        while (i < a.size_ && i < b.size_ && res == 0) {
            res = a.buf_[i] - b.buf_[i]
            i++
        }
        if (res == 0) {
            res = a.size_ - b.size_
        }
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun compare_fast(a: ByteArrayWrapper, b: ByteArrayWrapper): Int {
        var res = 0
        var i = 0
        if (res == 0) {
            res = a.size_ - b.size_
        }
        while (i < a.size_ && i < b.size_ && res == 0) {
            res = a.buf_[i] - b.buf_[i]
            i++
        }
        return res
    }

    @Suppress("NOTHING_TO_INLINE")internal inline
    fun readDouble8(data: ByteArrayWrapper, offset: Int): Double {
        return ByteArrayHelper2.readDouble8(ByteArrayWrapperExt.getBuf(data), offset)
    }

    @Suppress("NOTHING_TO_INLINE")internal inline
    fun readInt1(data: ByteArrayWrapper, offset: Int): Int {
        return ByteArrayHelper2.readInt1(ByteArrayWrapperExt.getBuf(data), offset)
    }

    @Suppress("NOTHING_TO_INLINE")internal inline
    fun readInt4(data: ByteArrayWrapper, offset: Int): Int {
        return ByteArrayHelper2.readInt4(ByteArrayWrapperExt.getBuf(data), offset)
    }

    @Suppress("NOTHING_TO_INLINE")internal inline
    fun readIntX(data: ByteArrayWrapper, offset: Int, count: Int): Int {
        return ByteArrayHelper2.readIntX(ByteArrayWrapperExt.getBuf(data), offset, count)
    }

    @Suppress("NOTHING_TO_INLINE")internal inline
    fun readLong6(data: ByteArrayWrapper, offset: Int): Long {
        return ByteArrayHelper2.readLong6(ByteArrayWrapperExt.getBuf(data), offset)
    }

    @Suppress("NOTHING_TO_INLINE")internal inline
    fun readLong8(data: ByteArrayWrapper, offset: Int): Long {
        return ByteArrayHelper2.readLong8(ByteArrayWrapperExt.getBuf(data), offset)
    }

    @Suppress("NOTHING_TO_INLINE")internal inline
    fun readLongX(data: ByteArrayWrapper, offset: Int, count: Int): Long {
        return ByteArrayHelper2.readLongX(ByteArrayWrapperExt.getBuf(data), offset, count)
    }

    @Suppress("NOTHING_TO_INLINE")internal inline
    fun writeDouble8(data: ByteArrayWrapper, offset: Int, value: Double) {
        ByteArrayHelper2.writeDouble8(ByteArrayWrapperExt.getBuf(data), offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")internal inline
    fun writeInt1(data: ByteArrayWrapper, offset: Int, value: Int) {
        ByteArrayHelper2.writeInt1(ByteArrayWrapperExt.getBuf(data), offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")internal inline
    fun writeInt4(data: ByteArrayWrapper, offset: Int, value: Int) {
        ByteArrayHelper2.writeInt4(ByteArrayWrapperExt.getBuf(data), offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")internal inline
    fun writeIntX(data: ByteArrayWrapper, offset: Int, value: Int, count: Int) {
        ByteArrayHelper2.writeIntX(ByteArrayWrapperExt.getBuf(data), offset, value, count)
    }

    @Suppress("NOTHING_TO_INLINE")internal inline
    fun writeLong6(data: ByteArrayWrapper, offset: Int, value: Long) {
        ByteArrayHelper2.writeLong6(ByteArrayWrapperExt.getBuf(data), offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")internal inline
    fun writeLong8(data: ByteArrayWrapper, offset: Int, value: Long) {
        ByteArrayHelper2.writeLong8(ByteArrayWrapperExt.getBuf(data), offset, value)
    }

    @Suppress("NOTHING_TO_INLINE")internal inline
    fun writeLongX(data: ByteArrayWrapper, offset: Int, value: Long, count: Int) {
        ByteArrayHelper2.writeLongX(ByteArrayWrapperExt.getBuf(data), offset, value, count)
    }

    @Suppress("NOTHING_TO_INLINE")internal inline
    fun writeBuf(data: ByteArrayWrapper, offset: Int, value: ByteArray) {
        value.copyInto(data.buf_, offset, 0, value.size)
    }
}
