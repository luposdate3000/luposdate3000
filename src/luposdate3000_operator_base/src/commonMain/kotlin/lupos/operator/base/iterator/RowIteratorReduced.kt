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
import lupos.shared.operator.iterator.RowIterator
import kotlin.jvm.JvmField

public open class RowIteratorReduced(@JvmField public val child: RowIterator) : RowIterator() {
    @JvmField
    public var first: Boolean = true

    init {
        columns = child.columns
        buf = DictionaryValueTypeArray(columns.size)
        close = {
            child.close()
            _close()
        }
        next = {
            var off = child.next()
            var res = -1
            if (first) {
                if (off == -1) {
                    close()
                } else {
                    for (i in columns.indices) {
                        buf[i] = child.buf[off + i]
                    }
                    res = 0
                }
                first = false
            } else {
                loop@ while (true) {
                    if (off == -1) {
                        close()
                        break@loop
                    }
                    for (i in columns.indices) {
                        if (buf[i] != child.buf[off + i]) {
                            for (j in columns.indices) {
                                buf[j] = child.buf[off + j]
                            }
                            res = 0
                            break@loop
                        }
                    }
                    off = child.next()
                }
            }
            res
        }
    }
}
