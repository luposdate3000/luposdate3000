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
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.operator.iterator.ColumnIterator
import kotlin.jvm.JvmField

public class ColumnIteratorMultiValue3(@JvmField public val values: DictionaryValueTypeArray, @JvmField public var size: Int) : ColumnIterator() {
    @JvmField
    public var index: Int = 0

    public fun reset(newsize: Int) {
        index = 0
        size = newsize
    }

    /*suspend*/ override fun close() {
        index = size
    }

    /*suspend*/ override fun next(): DictionaryValueType {
        return if (index == size) {
            DictionaryValueHelper.nullValue
        } else {
            values[index++]
        }
    }
}
