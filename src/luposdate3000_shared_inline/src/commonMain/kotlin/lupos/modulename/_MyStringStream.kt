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
package lupos.modulename

import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.IMyInputStream
import kotlin.jvm.JvmField

internal class _MyStringStream(str: String) : IMyInputStream {
    @JvmField
    val buf4 = ByteArray(4)

    @JvmField
    public val data = str.encodeToByteArray()

    @JvmField
    public var pos = 0
    public override fun close() {
    }

    override fun read(buf: ByteArray): Int {
        var s = pos + buf.size
        var res = buf.size
        if (s > data.size) {
            s = data.size
            res = s - pos
        }
        data.copyInto(buf, 0, pos, s)
        pos = s
        return res
    }

    override fun read(buf: ByteArray, len: Int): Int {
        var s = pos + len
        var res = buf.size
        if (s > data.size) {
            s = data.size
            res = s - pos
        }
        data.copyInto(buf, 0, pos, s)
        pos = s
        return res
    }

    override fun read(buf: ByteArray, off: Int, len: Int): Int {
        var s = pos + len
        var res = buf.size
        if (s > data.size) {
            s = data.size
            res = s - pos
        }
        data.copyInto(buf, off, pos, s)
        pos = s
        return res
    }

    override fun readInt(): Int {
        read(buf4, 4)
        return ByteArrayHelper.readInt4(buf4, 0)
    }

    override fun readByte(): Byte {
        read(buf4, 1)
        return buf4[0]
    }

    public override fun readLine(): String? {
// TODO this may break on utf-8
        var buf = mutableListOf<Byte>()
        try {
            var b = readByte()
            while (b != '\n'.toByte()) {
                if (b != '\r'.toByte()) {
                    buf.add(b)
                }
                b = readByte()
            }
        } catch (e: Throwable) {
            if (buf.size == 0) {
                return null
            }
        }
        return buf.toByteArray().decodeToString()
    }
}
