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
import java.io.InputStream
import kotlin.jvm.JvmField

internal actual class _MyInputStream(@JvmField internal val stream: InputStream) : IMyInputStream {
    @JvmField
    internal val buf4: ByteArray = ByteArray(4)
    public actual override fun read(buf: ByteArray): Int {
        return read(buf, buf.size)
    }

    public actual override fun read(buf: ByteArray, len: Int): Int {
        var o = 0
        var s = len
        while (s > 0) {
            val tmp = stream.read(buf, o, s)
            if (tmp <= 0) {
                return len - s
            }
            s -= tmp
            o += tmp
        }
        return len
    }

    public actual override fun read(buf: ByteArray, off: Int, len: Int): Int {
        var o = off
        var s = len
        while (s > 0) {
            val tmp = stream.read(buf, o, s)
            if (tmp <= 0) {
                return len - s
            }
            s -= tmp
            o += tmp
        }
        return len
    }

    public actual override fun readInt(): Int {
        read(buf4, 4)
        return ByteArrayHelper.readInt4(buf4, 0)
    }

    public actual override fun readByte(): Byte {
        read(buf4, 1)
        return buf4[0]
    }

    public actual override fun close() {
        stream.close()
    }

    public actual override fun readLine(): String? {
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
