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

import lupos.shared.dynamicArray.IntArrayWrapper

internal object IntArrayWrapperExt {
    @Suppress("NOTHING_TO_INLINE")
    internal inline fun setSize(data: IntArrayWrapper, c: Int) {
        data.size = c
        if (c > data.buf.size) {
            data.buf = IntArray(c)
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun setSizeCopy(data: IntArrayWrapper, c: Int) {
        data.size = c
        if (c > data.buf.size) {
            val oldBuf = data.buf
            data.buf = IntArray(c)
            oldBuf.copyInto(data.buf)
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun copyInto(a: IntArrayWrapper, b: IntArrayWrapper) {
        setSize(b, a.size)
        a.buf.copyInto(b.buf, 0, 0, a.size)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun append(data: IntArrayWrapper, v: Int) {
        if (data.buf.size == data.size) {
            val oldBuf = data.buf
            data.buf = IntArray(data.size * 2)
            oldBuf.copyInto(data.buf)
        }
        data.buf[data.size++] = v
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun removeLast(data: IntArrayWrapper): Int {
        data.size--
        return data.buf[data.size]
    }
}
