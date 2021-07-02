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
package lupos.shared.inline
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
internal typealias DictionaryValueType = Long
internal typealias DictionaryValueTypeArray = LongArray

internal object DictionaryValueHelper {
    internal const val booleanTrueValue: Long = (0x00000000) /*lowest 5 values*/ /*required to be 0 for_ truth table loopups*/
    internal const val booleanFalseValue: Long = (0x00000001) /*lowest 5 values*/ /*required to be 1 for_ truth table loopups*/
    internal const val errorValue: Long = (0x00000002) /*lowest 5 values*/ /*required to be 2 for_ truth table loopups*/
    internal const val undefValue: Long = (0x00000003) /*lowest 5 values*/
    internal const val nullValue: Long = (0x00000004) /*lowest 5 values*/ /*symbol for no more results, previously 'null'*/
    internal const val flagLocal: Long = 0x40000000
    internal const val flagNoBNode: Long = 0x20000000
    internal const val maskValue: Long = 0x1FFFFFFF
    internal const val NULL: Long = 0L
    internal const val FIRST_BNODE: Long = 5L
    internal inline fun DictionaryValueTypeArray(size: Int, init: (Int) -> DictionaryValueType): DictionaryValueTypeArray {
        return LongArray(size) { init(it) }
    }
    internal inline fun DictionaryValueTypeArray(size: Int): DictionaryValueTypeArray {
        return LongArray(size)
    }
    internal inline fun isLocalValue(value: DictionaryValueType): Boolean {
        return (value and flagLocal) == flagLocal
    }
    internal inline fun isBnode(value: DictionaryValueType): Boolean {
        return (value and flagNoBNode) != flagNoBNode
    }
    internal inline fun toByteArray(buffer: ByteArrayWrapper, off: Int, value: Long) {
        ByteArrayHelper.writeLong8(ByteArrayWrapperExt.getBuf(buffer), off, value)
    }
    internal inline fun fromByteArray(buffer: ByteArrayWrapper, off: Int): Long {
        return ByteArrayHelper.readLong8(ByteArrayWrapperExt.getBuf(buffer), off)
    }
    internal inline fun fromByteArrayX(buffer: ByteArrayWrapper, off: Int, bytes: Int): Long {
        return ByteArrayHelper.readLongX(ByteArrayWrapperExt.getBuf(buffer), off, bytes)
    }
    internal inline fun getSize(): Int {
        return 8
    }
    internal inline fun toInt(value: Long): Int {
// adapter for places, where always Int are used
        return value.toInt()
    }
    internal inline fun fromInt(value: Int): Long {
// adapter for places, where always Int are used
        return value.toLong()
    }
}

/*

import lupos.shared.inline.DictionaryValueHelper
import lupos.shared.inline.DictionaryValueType
import lupos.shared.inline.DictionaryValueTypeArray

*/
