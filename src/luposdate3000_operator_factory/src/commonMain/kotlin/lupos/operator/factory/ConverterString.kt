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
package lupos.operator.factory

import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt

public object ConverterString {
    public fun encodeString(s: String?, data: ByteArrayWrapper, mapping: MutableMap<String, Int>): Int {
        if (s == null) {
            return -1
        } else {
            val r = mapping[s]
            if (r != null) {
                return r
            } else {
                val off = ByteArrayWrapperExt.getSize(data)
                mapping[s] = off
                val b = s.encodeToByteArray()
                ByteArrayWrapperExt.setSize(data, off + 4 + b.size, true)
                ByteArrayWrapperExt.writeInt4(data, off, b.size, { "encodeString.len" })
                if (b.size > 0) {
                    ByteArrayWrapperExt.writeBuf(data, off + 4, b, { "encodeString.data" })
                }
                return off
            }
        }
    }

    public fun decodeString(data: ByteArrayWrapper, off: Int): String {
        val len = ByteArrayWrapperExt.readInt4(data, off, { "encodeString.len" })
        if (len == 0) {
            return ""
        } else {
            return ByteArrayWrapperExt.getBuf(data).decodeToString(off + 4, off + 4 + len)
        }
    }

    public fun decodeStringNull(data: ByteArrayWrapper, off: Int): String? {
        if (off < 0) {
            return null
        } else {
            return decodeString(data, off)
        }
    }
}
