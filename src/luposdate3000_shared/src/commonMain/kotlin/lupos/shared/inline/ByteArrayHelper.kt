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

public expect object ByteArrayHelper {
    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeInt1(data: ByteArray, offset: Int, value: Int)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeInt2(data: ByteArray, offset: Int, value: Int)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeInt3(data: ByteArray, offset: Int, value: Int)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeInt4(data: ByteArray, offset: Int, value: Int)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeIntX(data: ByteArray, offset: Int, value: Int, count: Int)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeLongX(data: ByteArray, offset: Int, value: Long, count: Int)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeLong8(data: ByteArray, offset: Int, value: Long)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeLong7(data: ByteArray, offset: Int, value: Long)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeLong6(data: ByteArray, offset: Int, value: Long)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeLong5(data: ByteArray, offset: Int, value: Long)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeLong4(data: ByteArray, offset: Int, value: Long)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeLong3(data: ByteArray, offset: Int, value: Long)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeLong2(data: ByteArray, offset: Int, value: Long)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeLong1(data: ByteArray, offset: Int, value: Long)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeDouble8(data: ByteArray, offset: Int, value: Double)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeChar(data: ByteArray, offset: Int, value: Char)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readLong8(data: ByteArray, offset: Int): Long

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readLong7(data: ByteArray, offset: Int): Long

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readLong6(data: ByteArray, offset: Int): Long

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readLong5(data: ByteArray, offset: Int): Long

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readLong4(data: ByteArray, offset: Int): Long

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readLong3(data: ByteArray, offset: Int): Long

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readLong2(data: ByteArray, offset: Int): Long

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readLong1(data: ByteArray, offset: Int): Long

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readDouble8(data: ByteArray, offset: Int): Double

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readInt4(data: ByteArray, offset: Int): Int

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readInt3(data: ByteArray, offset: Int): Int

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readInt2(data: ByteArray, offset: Int): Int

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readInt1(data: ByteArray, offset: Int): Int

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readIntX(data: ByteArray, offset: Int, count: Int): Int

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readLongX(data: ByteArray, offset: Int, count: Int): Long

    @Suppress("NOTHING_TO_INLINE")
    public inline fun readChar(data: ByteArray, offset: Int): Char
}
