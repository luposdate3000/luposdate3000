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
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
/*



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

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun DictionaryValueTypeArrayOf() = intArrayOf()

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun DictionaryValueTypeArrayOf(a: Int) = intArrayOf(a)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun DictionaryValueTypeArrayOf(a: Int, b: Int) = intArrayOf(a, b)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun DictionaryValueTypeArrayOf(a: Int, b: Int, c: Int) = intArrayOf(a, b, c)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun isLocalValue(value: Int): Boolean = (value and flagLocal) == flagLocal

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun isBnode(value: Int): Boolean = (value and flagNoBNode) != flagNoBNode

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun toByteArray(buffer: ByteArray, off: Int, value: Int) = ByteArrayHelper.writeInt4(buffer, off, value)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun toByteArrayX(buffer: ByteArrayWrapper, off: Int, value: Int, count: Int) = ByteArrayHelper.writeIntX(ByteArrayWrapperExt.getBuf(buffer), off, value, count)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun toByteArrayX(buffer: ByteArray, off: Int, value: Int, count: Int) = ByteArrayHelper.writeIntX(buffer, off, value, count)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun toByteArray(buffer: ByteArrayWrapper, off: Int, value: Int) = ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), off, value)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun fromByteArray(buffer: ByteArray, off: Int): Int = ByteArrayHelper.readInt4(buffer, off)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun fromByteArrayX(buffer: ByteArray, off: Int, bytes: Int): Int = ByteArrayHelper.readIntX(buffer, off, bytes)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun fromByteArray(buffer: ByteArrayWrapper, off: Int): Int = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun fromByteArrayX(buffer: ByteArrayWrapper, off: Int, bytes: Int): Int = ByteArrayHelper.readIntX(ByteArrayWrapperExt.getBuf(buffer), off, bytes)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getSize(): Int = 8

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun toInt(value: Int): Int = value // adapter for places, where always Int are used

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun fromInt(value: Int): Int = value // adapter for places, where always Int are used

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun numberOfBytesUsed(value: Int): Int {
        SanityCheck.check { value >= 0 }
        if (value> 0xFFFF) {
            if (value> 0xFFFFFF) {
                return 4
            } else {
                return 3
            }
        } else {
            if (value> 0xFF) {
                return 2
            } else {
                if (value> 0) {
                    return 1
                } else {
                    return 0
                }
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun sendToStream(stream: IMyOutputStream, value: Int) = stream.writeInt(value)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readFromStream(stream: IMyInputStream) = stream.readInt()
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

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun DictionaryValueTypeArrayOf() = longArrayOf()

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun DictionaryValueTypeArrayOf(a: Long) = longArrayOf(a)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun DictionaryValueTypeArrayOf(a: Long, b: Long) = longArrayOf(a, b)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun DictionaryValueTypeArrayOf(a: Long, b: Long, c: Long) = longArrayOf(a, b, c)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun isLocalValue(value: Long): Boolean = (value and flagLocal) == flagLocal

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun isBnode(value: Long): Boolean = (value and flagNoBNode) != flagNoBNode

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun toByteArray(buffer: ByteArray, off: Int, value: Long) = ByteArrayHelper.writeLong8(buffer, off, value)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun toByteArray(buffer: ByteArrayWrapper, off: Int, value: Long) = ByteArrayHelper.writeLong8(ByteArrayWrapperExt.getBuf(buffer), off, value)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun toByteArrayX(buffer: ByteArrayWrapper, off: Int, value: Long, count: Int) = ByteArrayHelper.writeLongX(ByteArrayWrapperExt.getBuf(buffer), off, value, count)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun toByteArrayX(buffer: ByteArray, off: Int, value: Long, count: Int) = ByteArrayHelper.writeLongX(buffer, off, value, count)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun fromByteArray(buffer: ByteArray, off: Int): Long = ByteArrayHelper.readLong8(buffer, off)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun fromByteArray(buffer: ByteArrayWrapper, off: Int): Long = ByteArrayHelper.readLong8(ByteArrayWrapperExt.getBuf(buffer), off)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun fromByteArrayX(buffer: ByteArrayWrapper, off: Int, bytes: Int): Long = ByteArrayHelper.readLongX(ByteArrayWrapperExt.getBuf(buffer), off, bytes)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun fromByteArrayX(buffer: ByteArray, off: Int, bytes: Int): Long = ByteArrayHelper.readLongX(buffer, off, bytes)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getSize(): Int = 8

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun toInt(value: Long): Int = value.toInt() // adapter for places, where always Int are used

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun fromInt(value: Int): Long = value.toLong() // adapter for places, where always Int are used

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun numberOfBytesUsed(value: Long): Int {
        SanityCheck.check { value >= 0 }
        if (value> 0xFFFFFFFF) {
            if (value> 0xFFFFFFFFFFFF) {
                if (value> 0xFFFFFFFFFFFFFF) {
                    return 8
                } else {
                    return 7
                }
            } else {
                if (value> 0xFFFFFFFFFF) {
                    return 6
                } else {
                    return 5
                }
            }
        } else {
            if (value> 0xFFFF) {
                if (value> 0xFFFFFF) {
                    return 4
                } else {
                    return 3
                }
            } else {
                if (value> 0xFF) {
                    return 2
                } else {
                    if (value> 0) {
                        return 1
                    } else {
                        return 0
                    }
                }
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun sendToStream(stream: IMyOutputStream, value: Long) = stream.writeLong(value)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readFromStream(stream: IMyInputStream) = stream.readLong()
}
