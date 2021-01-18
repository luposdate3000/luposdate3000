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
import kotlin.jvm.JvmField
@OptIn(ExperimentalStdlibApi::class)
internal class ByteArrayBuilder {
    internal companion object {
        @JvmField
        var debuguuidtmp = 0L
    }
    @JvmField
    var uuid = debuguuidtmp++
    @JvmField
    var capacity = 128
    @JvmField
    var data = ByteArray(capacity)
    @JvmField
    var size = 0
    @Suppress("NOTHING_TO_INLINE") internal inline fun build(): ByteArrayRead {
        SanityCheck.println { "ByteArrayBuilder($uuid).build with size $size and capacity $capacity" }
        return ByteArrayRead(data, size)
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun reset() {
        SanityCheck.println { "ByteArrayBuilder($uuid).reset" }
        capacity = 128
        data = ByteArray(capacity)
        size = 0
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun writeByte(b: Byte) {
        if (size + 1 > capacity) {
            data += ByteArray(capacity)
            capacity *= 2
        }
        SanityCheck.println { "ByteArrayBuilder($uuid).writeByte at $size with value ${b.toInt()}" }
        ByteArrayHelper.writeInt1(data, size, b.toInt() and 0xFF)
        size += 1
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun writeChar(c: Char) {
        if (size + 2 > capacity) {
            data += ByteArray(capacity)
            capacity *= 2
        }
        SanityCheck.println { "ByteArrayBuilder($uuid).writeChar at $size with value '$c' ${c.toInt()}" }
        ByteArrayHelper.writeChar(data, size, c)
        size += 2
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun writeInt(i: Int) {
        if (size + 4 > capacity) {
            data += ByteArray(capacity)
            capacity *= 2
        }
        SanityCheck.println { "ByteArrayBuilder($uuid).writeInt at $size with value $i" }
        ByteArrayHelper.writeInt4(data, size, i)
        size += 4
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun writeLong(l: Long) {
        if (size + 8 > capacity) {
            data += ByteArray(capacity)
            capacity *= 2
        }
        SanityCheck.println { "ByteArrayBuilder($uuid).writeLong at $size with value $l" }
        ByteArrayHelper.writeLong8(data, size, l)
        size += 8
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun writeString(s: String) {
        val tmp = s.toCharArray()
        writeInt(tmp.size)
        tmp.forEach {
            writeChar(it)
        }
        SanityCheck.println { "ByteArrayBuilder($uuid).writeString content '$s'" }
    }
}
