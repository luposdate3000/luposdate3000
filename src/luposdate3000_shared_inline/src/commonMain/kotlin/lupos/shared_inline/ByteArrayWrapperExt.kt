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
package lupos.shared_inline

import lupos.shared.ByteArrayWrapper

public object ByteArrayWrapperExt {
    public inline fun setSize(data: ByteArrayWrapper, c: Int) {
        data.size = c
        if (c > data.buf.size) {
            data.buf = ByteArray(c)
        }
    }

    public inline fun setSizeCopy(data: ByteArrayWrapper, c: Int) {
        data.size = c
        if (c > data.buf.size) {
            val oldBuf = data.buf
            data.buf = ByteArray(c)
            oldBuf.copyInto(data.buf)
        }
    }

    public inline fun commonBytes(a: ByteArrayWrapper, b: ByteArrayWrapper): Int {
        var i = 0
        while (i < a.size && i < b.size) {
            if (a.buf[i] == b.buf[i]) {
                i++
            } else {
                break
            }
        }
        return i
    }

    public inline fun copyInto(a: ByteArrayWrapper, b: ByteArrayWrapper) {
        setSize(b, a.size)
        a.buf.copyInto(b.buf, 0, 0, a.size)
    }
}
