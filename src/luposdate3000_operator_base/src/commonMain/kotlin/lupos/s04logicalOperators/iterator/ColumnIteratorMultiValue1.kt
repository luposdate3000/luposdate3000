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
package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.DictionaryExt
import kotlin.jvm.JvmField

public class ColumnIteratorMultiValue1(@JvmField public val values: MutableList<Int>) : ColumnIterator() {
    @JvmField
    public var index: Int = 0
    public /*suspend*/ override fun close() {
        index = values.size
    }

    public /*suspend*/ override fun next(): Int {
        return if (index == values.size) {
            DictionaryExt.nullValue
        } else {
            values[index++]
        }
    }
}
