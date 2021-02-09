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

import lupos.s00misc.IMyInputStream
import lupos.s00misc.NotImplementedException

internal actual class _MyInputStream : IMyInputStream {
    public actual override fun readByte(): Byte = throw NotImplementedException("MyOutputStream", "xyz not implemented")
    public actual override fun readInt(): Int = throw NotImplementedException("MyOutputStream", "xyz not implemented")
    public actual override fun read(buf: ByteArray, off: Int, len: Int): Int = throw NotImplementedException("MyOutputStream", "xyz not implemented")
    public actual override fun read(buf: ByteArray, len: Int): Int = throw NotImplementedException("MyOutputStream", "xyz not implemented")
    public actual override fun read(buf: ByteArray): Int = throw NotImplementedException("MyOutputStream", "xyz not implemented")
    public actual override fun close() {
    }

    public actual override fun readLine(): String? {
// TODO this may break on utf-8
        var buf = mutableListOf<Byte>()
        try {
            var b = readByte()
            while (b != '\n'.toByte()) {
                buf.add(b)
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
