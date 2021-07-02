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

/*


DictionaryValueHelper.DictionaryValueTypeArray

DictionaryValueType

import lupos.shared.dictionary.DictionaryExt
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray

*/

internal object DictionaryValueHelperInt {
    public const val booleanTrueValue: Int = (0x00000000) /*lowest 5 values*/ /*required to be 0 for_ truth table loopups*/
    public const val booleanFalseValue: Int = (0x00000001) /*lowest 5 values*/ /*required to be 1 for_ truth table loopups*/
    public const val errorValue: Int = (0x00000002) /*lowest 5 values*/ /*required to be 2 for_ truth table loopups*/
    public const val undefValue: Int = (0x00000003) /*lowest 5 values*/
    public const val nullValue: Int = (0x00000004) /*lowest 5 values*/ /*symbol for no more results, previously 'null'*/
    public const val flagLocal: Int = 0x40000000.toInt()
    public const val flagNoBNode: Int = 0x20000000.toInt()
    public const val maskValue: Int = 0x1FFFFFFF.toInt()
    public const val NULL: Int = 0
    public const val FIRST_BNODE: Int = 5
    internal inline fun DictionaryValueTypeArray(size: Int, init: (Int) -> Int): IntArray {
        return IntArray(size) { init(it) }
    }
    internal inline fun DictionaryValueTypeArray(size: Int): IntArray {
        return IntArray(size)
    }
    internal inline fun isLocalValue(value: Int): Boolean {
        return (value and flagLocal) == flagLocal
    }
    internal inline fun isBnode(value: Int): Boolean {
        return (value and flagNoBNode) != flagNoBNode
    }
    internal inline fun toByteArray(buffer: ByteArray, off: Int, value: Int) {
        ByteArrayHelper.writeInt4(buffer, off, value)
    }
internal inline fun toByteArrayX(buffer: ByteArrayWrapper, off: Int, value: Int,count:Int) {
        ByteArrayHelper.writeIntX(ByteArrayWrapperExt.getBuf(buffer), off, value,count)
    }
internal inline fun toByteArrayX(buffer: ByteArray, off: Int, value: Int,count:Int) {
        ByteArrayHelper.writeIntX(buffer, off, value,count)
    }
    internal inline fun fromByteArray(buffer: ByteArray, off: Int): Int {
        return ByteArrayHelper.readInt4(buffer, off)
    }
    internal inline fun fromByteArrayX(buffer: ByteArray, off: Int, bytes: Int): Int {
        return ByteArrayHelper.readIntX(buffer, off, bytes)
    }
    internal inline fun toByteArray(buffer: ByteArrayWrapper, off: Int, value: Int) {
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), off, value)
    }
    internal inline fun fromByteArray(buffer: ByteArrayWrapper, off: Int): Int {
        return ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
    }
    internal inline fun fromByteArrayX(buffer: ByteArrayWrapper, off: Int, bytes: Int): Int {
        return ByteArrayHelper.readIntX(ByteArrayWrapperExt.getBuf(buffer), off, bytes)
    }
    internal inline fun getSize(): Int {
        return 8
    }
    internal inline fun toInt(value: Int): Int {
        // adapter for places, where always Int are used
        return value
    }
    internal inline fun fromInt(value: Int): Int {
        // adapter for places, where always Int are used
        return value
    }
    internal inline fun numberOfBytesUsed(value: Int): Int {
        return (((32 + 7 - IntegerExt.numberOfLeadingZeros(value))) shr 3)
    }
}
internal object DictionaryValueHelperLong {
    public const val booleanTrueValue: Long = (0x00000000) /*lowest 5 values*/ /*required to be 0 for_ truth table loopups*/
    public const val booleanFalseValue: Long = (0x00000001) /*lowest 5 values*/ /*required to be 1 for_ truth table loopups*/
    public const val errorValue: Long = (0x00000002) /*lowest 5 values*/ /*required to be 2 for_ truth table loopups*/
    public const val undefValue: Long = (0x00000003) /*lowest 5 values*/
    public const val nullValue: Long = (0x00000004) /*lowest 5 values*/ /*symbol for no more results, previously 'null'*/
    public const val flagLocal: Long = 0x40000000
    public const val flagNoBNode: Long = 0x20000000
    public const val maskValue: Long = 0x1FFFFFFF
    public const val NULL: Long = 0L
    public const val FIRST_BNODE: Long = 5L
    internal inline fun DictionaryValueTypeArray(size: Int, init: (Int) -> Long): LongArray {
        return LongArray(size) { init(it) }
    }
    internal inline fun DictionaryValueTypeArray(size: Int): LongArray {
        return LongArray(size)
    }
    internal inline fun isLocalValue(value: Long): Boolean {
        return (value and flagLocal) == flagLocal
    }
    internal inline fun isBnode(value: Long): Boolean {
        return (value and flagNoBNode) != flagNoBNode
    }
    internal inline fun toByteArray(buffer: ByteArray, off: Int, value: Long) {
        ByteArrayHelper.writeLong8(buffer, off, value)
    }
    internal inline fun fromByteArray(buffer: ByteArray, off: Int): Long {
        return ByteArrayHelper.readLong8(buffer, off)
    }
    internal inline fun toByteArray(buffer: ByteArrayWrapper, off: Int, value: Long) {
        ByteArrayHelper.writeLong8(ByteArrayWrapperExt.getBuf(buffer), off, value)
    }
    internal inline fun toByteArrayX(buffer: ByteArrayWrapper, off: Int, value: Long,count:Int) {
        ByteArrayHelper.writeLongX(ByteArrayWrapperExt.getBuf(buffer), off, value,count)
    }
    internal inline fun toByteArrayX(buffer: ByteArray, off: Int, value: Long,count:Int) {
        ByteArrayHelper.writeLongX(buffer, off, value,count)
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
    internal inline fun numberOfBytesUsed(value: Long): Int {
        return (((64 + 7 - LongExt.numberOfLeadingZeros(value))) shr 3)
    }
}
