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
package lupos.operator.base.iterator

import lupos.shared.DictionaryValueTypeArray
import lupos.shared.SanityCheck
import lupos.shared.operator.iterator.RowIterator
import kotlin.jvm.JvmField

public class RowIteratorBuf(buf: DictionaryValueTypeArray, columns: Array<String>, @JvmField public val size: Int) : RowIterator() {
    @JvmField
    public var offset: Int = 0

    init {
        this.buf = buf
        this.columns = columns
        if (size == 0) {
            offset = -1
        }
        if (SanityCheck.enabled) { if (!(size >= 0)) { throw Exception("SanityCheck failed") } }
        if (SanityCheck.enabled) { if (!(size <= buf.size)) { throw Exception("SanityCheck failed") } }
        if (SanityCheck.enabled) { if (!((buf.size % columns.size) == 0)) { throw Exception("SanityCheck failed") } }
        next = {
            val res = offset
            val tmp = offset + columns.size
            offset = if (tmp >= size) {
                -1
            } else {
                tmp
            }
            res
        }
    }
}
