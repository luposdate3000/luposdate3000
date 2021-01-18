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
internal class ByteArrayRead(@JvmField val data: ByteArray, @JvmField val size: Int) {
    @JvmField
    var uuid = debuguuidtmp++
    internal companion object {
        @JvmField
        var debuguuidtmp = 0L
    }
    @JvmField
    var offset = 0
    @Suppress("NOTHING_TO_INLINE") internal inline fun remaining() = size - offset
    init {
        SanityCheck.println { "ByteArrayRead($uuid).init with $size Bytes" }
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun readInt(): Int {
        val res = ByteArrayHelper.readInt4(data, offset)
        SanityCheck.println { "ByteArrayRead($uuid).readInt at $offset with value $res" }
        offset += 4
        return res
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun readByte(): Byte {
        val res = ByteArrayHelper.readInt1(data, offset)
        SanityCheck.println { "ByteArrayRead($uuid).readByte at $offset with value $res" }
        offset += 1
        return res.toByte()
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun readChar(): Char {
        val res = ByteArrayHelper.readChar(data, offset)
        SanityCheck.println { "ByteArrayRead($uuid).readChar at $offset with value '$res' ${res.toInt()}" }
        offset += 2
        return res
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun readLong(): Long {
        val res = ByteArrayHelper.readLong8(data, offset)
        SanityCheck.println { "ByteArrayRead($uuid).readLong at $offset with value $res" }
        offset += 8
        return res
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun readString(): String {
        val len = readInt()
        val d = CharArray(len)
        for (i in 0 until len) {
            d[i] = readChar()
        }
        val res = d.concatToString()
        SanityCheck.println { "ByteArrayBuilder($uuid).readString content '$res'" }
        return res
    }
}
