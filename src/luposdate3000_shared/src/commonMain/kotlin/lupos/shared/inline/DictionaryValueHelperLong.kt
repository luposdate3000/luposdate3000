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

import lupos.shared.BufferManagerPage
import lupos.shared.BufferManagerPageWrapper
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import kotlin.jvm.JvmField

internal object DictionaryValueHelperLong {
    @JvmField
    internal val booleanTrueValue: Long = (0x0000000000000000) /*lowest 5 values*/ /*required to be 0 for_ truth table loopups*/

    @JvmField
    internal val booleanFalseValue: Long = (0x0000000000000001) /*lowest 5 values*/ /*required to be 1 for_ truth table loopups*/

    @JvmField
    internal val errorValue: Long = (0x0000000000000002) /*lowest 5 values*/ /*required to be 2 for_ truth table loopups*/

    @JvmField
    internal val undefValue: Long = (0x0000000000000003) /*lowest 5 values*/

    @JvmField
    internal val nullValue: Long = (0x0000000000000004) /*lowest 5 values*/ /*symbol for no more results, previously 'null'*/

    @JvmField
    internal val flagLocal: Long = 0x4000000000000000

    @JvmField
    internal val flagNoBNode: Long = 0x2000000000000000

    @JvmField
    internal val flagInlineValue1: Long = 0x0400000000000000

    @JvmField
    internal val flagInlineValue2: Long = 0x0800000000000000

    @JvmField
    internal val flagInlineValue3: Long = 0x0c00000000000000

    @JvmField
    internal val flagInlineValue4: Long = 0x1000000000000000

    @JvmField
    internal val flagInlineValue5: Long = 0x1400000000000000

    @JvmField
    internal val flagInlineValue6: Long = 0x1800000000000000

    @JvmField
    internal val flagInlineValue7: Long = 0x1c00000000000000

    @JvmField
    internal val flagInlineValue: Long = 0x1c00000000000000

    @JvmField
    internal val maskValue: Long = 0x03FFFFFFFFFFFFFF

    @JvmField
    internal val NULL: Long = 0L

    @JvmField
    internal val FIRST_BNODE: Long = 5L

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun DictionaryValueTypeArrayOf() = longArrayOf()

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun DictionaryValueTypeArrayOf(a: Long) = longArrayOf(a)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun DictionaryValueTypeArrayOf(a: Long, b: Long) = longArrayOf(a, b)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun DictionaryValueTypeArrayOf(a: Long, b: Long, c: Long) = longArrayOf(a, b, c)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun toByteArray(buffer: BufferManagerPageWrapper, off: Int, value: Long) = BufferManagerPage.writeLong8(buffer, off, value)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun toByteArray(buffer: ByteArrayWrapper, off: Int, value: Long, crossinline comment: () -> String = { "TODO" }) = ByteArrayWrapperExt.writeLong8(buffer, off, value, comment)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun toByteArrayX(buffer: ByteArrayWrapper, off: Int, value: Long, count: Int, crossinline comment: () -> String = { "TODO" }) = ByteArrayWrapperExt.writeLongX(buffer, off, value, count, comment)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun toByteArrayX(buffer: BufferManagerPageWrapper, off: Int, value: Long, count: Int) = BufferManagerPage.writeLongX(buffer, off, value, count)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun fromByteArray(buffer: BufferManagerPageWrapper, off: Int): Long = BufferManagerPage.readLong8(buffer, off)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun fromByteArray(buffer: ByteArrayWrapper, off: Int, crossinline comment: () -> String = { "TODO" }): Long = ByteArrayWrapperExt.readLong8(buffer, off, comment)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun fromByteArrayX(buffer: ByteArrayWrapper, off: Int, bytes: Int, crossinline comment: () -> String = { "TODO" }): Long = ByteArrayWrapperExt.readLongX(buffer, off, bytes, comment)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun fromByteArrayX(buffer: BufferManagerPageWrapper, off: Int, bytes: Int): Long = BufferManagerPage.readLongX(buffer, off, bytes)

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getSize(): Int = 8

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun toInt(value: Long): Int = value.toInt() // adapter for places, where always Int are used

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun fromInt(value: Int): Long = value.toLong() // adapter for places, where always Int are used

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun fromByte(value: Byte): Long = value.toLong() and 0xFF // adapter for places, where always Byte are used

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun fromString(value: String): Long = value.toLong() // adapter for places, where always String are used

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun numberOfBytesUsed(value: Long): Int {
        if (value > 0xFFFFFFFF) {
            if (value > 0xFFFFFFFFFFFF) {
                if (value > 0xFFFFFFFFFFFFFF) {
                    return 8
                } else {
                    return 7
                }
            } else {
                if (value > 0xFFFFFFFFFF) {
                    return 6
                } else {
                    return 5
                }
            }
        } else {
            if (value > 0xFFFF) {
                if (value > 0xFFFFFF) {
                    return 4
                } else {
                    return 3
                }
            } else {
                if (value > 0xFF) {
                    return 2
                } else {
                    if (value > 0) {
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
