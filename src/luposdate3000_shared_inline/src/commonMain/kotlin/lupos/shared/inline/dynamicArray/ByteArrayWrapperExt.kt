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
        if (data.uuid == 20197) {
            kotlin.io.println("ByteArrayWrapperExt.setSize ${data.uuid} ${data.size_ } $c")
        }
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
}
