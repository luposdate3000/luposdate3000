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

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.operator.iterator.ColumnIterator
import kotlin.jvm.JvmField

public class ColumnIteratorRepeatIterator(@JvmField public val count: Int, @JvmField public val child: ColumnIterator) : ColumnIterator() {
    @JvmField
    public var index: Int = 0

    @JvmField
    public var index2: Int = 0

    @JvmField
    public val data: MutableList<DictionaryValueType> = mutableListOf()

    @JvmField
    public var label: Int = 1

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal fun _close() {
        if (label != 0) {
            label = 0
            child.close()
        }
    }

    override /*suspend*/ fun close() {
        _close()
    }

    override /*suspend*/ fun next(): DictionaryValueType {
        when (label) {
            1 -> {
                val tmp = child.next()
                return if (tmp == DictionaryValueHelper.nullValue) {
                    child.close()
                    if (data.size == 0 || count == 1) {
                        label = 0
                        DictionaryValueHelper.nullValue
                    } else {
                        index = 2
                        label = 2
                        data[index2++]
                    }
                } else {
                    data.add(tmp)
                    tmp
                }
            }
            2 -> {
                return when {
                    index2 < data.size -> {
                        data[index2++]
                    }
                    index < count -> {
                        index++
                        index2 = 0
                        data[index2++]
                    }
                    else -> {
                        label = 0
                        DictionaryValueHelper.nullValue
                    }
                }
            }
            else -> {
                return DictionaryValueHelper.nullValue
            }
        }
    }
}
