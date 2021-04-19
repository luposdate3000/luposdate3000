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
package lupos.s00misc

public class ByteArrayWrapper : Comparable<ByteArrayWrapper> {
    public constructor()
    public constructor(b: ByteArray) {
        size = b.size
        buf = b
    }

    @JvmField
    internal var buf = ByteArray(0)
    @JvmField
    internal var size = 0
    public fun setSize(c: Int) {
        size = c
        if (c > buf.size) {
            buf = ByteArray(c)
        }
    }

    public fun setSizeCopy(c: Int) {
        size = c
        if (c > buf.size) {
            val oldBuf = buf
            buf = ByteArray(c)
            oldBuf.copyInto(buf)
        }
    }

    public fun getSize(): Int = size
    public fun getBuf(): ByteArray = buf
    public fun commonBytes(other: ByteArrayWrapper): Int {
        var i = 0
        while (i < size && i < other.size) {
            if (buf[i] == other.buf[i]) {
                i++
            } else {
                break
            }
        }
        return i
    }

    override fun compareTo(other: ByteArrayWrapper): Int {
        var res = 0
        var i = 0
        while (i < size && i < other.size && res == 0) {
            res = buf[i] - other.buf[i]
            i++
        }
        if (res == 0) {
            res = size - other.size
        }
        return res
    }

    override fun equals(other: Any?): Boolean {
        return other is ByteArrayWrapper && compareTo(other) == 0
    }

    override fun hashCode(): Int {
        var res = size
        for (i in 0 until size) {
            res = (res shl 1) + buf[i]
        }
        return res
    }

    public fun copyInto(other: ByteArrayWrapper) {
        other.setSize(size)
        buf.copyInto(other.buf, 0, 0, size)
    }

    override fun toString(): String {
        return buf.map { it }.subList(0, size).toString()
    }
}
