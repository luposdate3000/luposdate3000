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

public object DictionaryValueHelperInt {
    @JvmField
    public val booleanTrueValue: Int = (0x00000000) /*lowest 5 values*/ /*required to be 0 for_ truth table loopups*/

    @JvmField
    public val booleanFalseValue: Int = (0x00000001) /*lowest 5 values*/ /*required to be 1 for_ truth table loopups*/

    @JvmField
    public val errorValue: Int = (0x00000002) /*lowest 5 values*/ /*required to be 2 for_ truth table loopups*/

    @JvmField
    public val undefValue: Int = (0x00000003) /*lowest 5 values*/

    @JvmField
    public val nullValue: Int = (0x00000004) /*lowest 5 values*/ /*symbol for no more results, previously 'null'*/

    @JvmField
    public val flagLocal: Int = 0x40000000.toInt()

    @JvmField
    public val flagNoBNode: Int = 0x20000000.toInt()

    @JvmField
    public val flagInlineValue1: Int = 0x04000000.toInt()

    @JvmField
    public val flagInlineValue2: Int = 0x08000000.toInt()

    @JvmField
    public val flagInlineValue3: Int = 0x0c000000.toInt()

    @JvmField
    public val flagInlineValue4: Int = 0x10000000.toInt()

    @JvmField
    public val flagInlineValue5: Int = 0x14000000.toInt()

    @JvmField
    public val flagInlineValue6: Int = 0x18000000.toInt()

    @JvmField
    public val flagInlineValue7: Int = 0x1c000000.toInt()

    @JvmField
    public val flagInlineValue: Int = 0x1c000000.toInt()

    @JvmField
    public val maskValue: Int = 0x03FFFFFF.toInt()

    @JvmField
    public val NULL: Int = 0

    @JvmField
    public val FIRST_BNODE: Int = 5

    @Suppress("NOTHING_TO_INLINE")
    public inline fun DictionaryValueTypeArrayOf() :IntArray= intArrayOf()

    @Suppress("NOTHING_TO_INLINE")
    public inline fun DictionaryValueTypeArrayOf(a: Int) :IntArray= intArrayOf(a)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun DictionaryValueTypeArrayOf(a: Int, b: Int) :IntArray= intArrayOf(a, b)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun DictionaryValueTypeArrayOf(a: Int, b: Int, c: Int) :IntArray= intArrayOf(a, b, c)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun toByteArray(buffer: BufferManagerPageWrapper, off: Int, value: Int) :Unit= BufferManagerPage.writeInt4(buffer, off, value)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun toByteArrayX(buffer: ByteArrayWrapper, off: Int, value: Int, count: Int, crossinline comment: () -> String = { "TODO" }) :Unit= ByteArrayWrapperExt.writeIntX(buffer, off, value, count, comment)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun toByteArrayX(buffer: BufferManagerPageWrapper, off: Int, value: Int, count: Int) :Unit= BufferManagerPage.writeIntX(buffer, off, value, count)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun toByteArray(buffer: ByteArrayWrapper, off: Int, value: Int, crossinline comment: () -> String = { "TODO" }) :Unit= ByteArrayWrapperExt.writeInt4(buffer, off, value, comment)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun fromByteArray(buffer: BufferManagerPageWrapper, off: Int): Int = BufferManagerPage.readInt4(buffer, off)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun fromByteArrayX(buffer: BufferManagerPageWrapper, off: Int, bytes: Int): Int = BufferManagerPage.readIntX(buffer, off, bytes)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun fromByteArray(buffer: ByteArrayWrapper, off: Int, crossinline comment: () -> String = { "TODO" }): Int = ByteArrayWrapperExt.readInt4(buffer, off, comment)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun fromByteArrayX(buffer: ByteArrayWrapper, off: Int, bytes: Int, crossinline comment: () -> String = { "TODO" }): Int = ByteArrayWrapperExt.readIntX(buffer, off, bytes, comment)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun getSize(): Int = 4

    @Suppress("NOTHING_TO_INLINE")
    public inline fun toInt(value: Int): Int = value // adapter for places, where always Int are used

    @Suppress("NOTHING_TO_INLINE")
    public inline fun fromInt(value: Int): Int = value // adapter for places, where always Int are used

    @Suppress("NOTHING_TO_INLINE")
    public inline fun fromByte(value: Byte): Int = value.toInt() and 0xFF // adapter for places, where always Byte are used

    @Suppress("NOTHING_TO_INLINE")
    public inline fun fromString(value: String): Int = value.toInt() // adapter for places, where always String are used

    @Suppress("NOTHING_TO_INLINE")
    public inline fun numberOfBytesUsed(value: Int): Int {
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

    @Suppress("NOTHING_TO_INLINE")
    public inline fun sendToStream(stream: IMyOutputStream, value: Int) :Unit= stream.writeInt(value)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readFromStream(stream: IMyInputStream) :Int= stream.readInt()
}
