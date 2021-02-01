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
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import kotlin.jvm.JvmField
public object ColumnIteratorMultiValue {
    public operator fun invoke(values: IntArray): ColumnIteratorMultiValue3 = ColumnIteratorMultiValue3(values, values.size)
    public operator fun invoke(values: IntArray, size: Int): ColumnIteratorMultiValue3 = ColumnIteratorMultiValue3(values, size)
    public operator fun invoke(values: MutableList<Int>): ColumnIteratorMultiValue1 = ColumnIteratorMultiValue1(values)
    public operator fun invoke(iterator: Iterator<Int>): ColumnIteratorMultiValue2 = ColumnIteratorMultiValue2(iterator)
}
public class ColumnIteratorMultiValue1(@JvmField public val values: MutableList<Int>) : ColumnIterator() {
    @JvmField
    public var index: Int = 0
    public /*suspend*/ override fun close() {
        index = values.size
    }
    public /*suspend*/ override fun next(): Int {
        return if (index == values.size) {
            ResultSetDictionaryExt.nullValue
        } else {
            values[index++]
        }
    }
}
public class ColumnIteratorMultiValue3(@JvmField public val values: IntArray, @JvmField public val size: Int) : ColumnIterator() {
    @JvmField
    public var index: Int = 0
    public /*suspend*/ override fun close() {
        index = size
    }
    public /*suspend*/ override fun next(): Int {
        return if (index == size) {
            ResultSetDictionaryExt.nullValue
        } else {
            values[index++]
        }
    }
}
public class ColumnIteratorMultiValue2(@JvmField public val iterator: Iterator<Int>) : ColumnIterator() {
    @JvmField
    public var label: Int = 1
    public /*suspend*/ override fun close() {
        label = 0
    }
    public /*suspend*/ override fun next(): Int {
        return if (label != 0 && iterator.hasNext()) {
            iterator.next()
        } else {
            ResultSetDictionaryExt.nullValue
        }
    }
}
