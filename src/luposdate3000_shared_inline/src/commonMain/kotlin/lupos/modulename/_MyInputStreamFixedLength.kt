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
import kotlin.jvm.JvmField

internal class _MyInputStreamFixedLength(@JvmField val stream: IMyInputStream, @JvmField var remainingBytes: Int) : IMyInputStream {
    public override fun readInt(): Int {
        // println("remainingBytes '$remainingBytes'")
        if (remainingBytes >= 4) {
            remainingBytes -= 4
            return stream.readInt()
        } else {
            throw Exception("not enough bytes available $remainingBytes")
        }
    }

    public override fun readByte(): Byte {
        // println("remainingBytes '$remainingBytes'")
        if (remainingBytes >= 1) {
            remainingBytes -= 1
            return stream.readByte()
        } else {
            throw Exception("not enough bytes available $remainingBytes")
        }
    }

    public override fun read(buf: ByteArray): Int {
        // println("remainingBytes '$remainingBytes'")
        if (remainingBytes >= buf.size) {
            remainingBytes -= buf.size
            return stream.read(buf)
        } else {
            throw Exception("not enough bytes available $remainingBytes")
        }
    }

    public override fun read(buf: ByteArray, len: Int): Int {
        // println("remainingBytes '$remainingBytes'")
        if (remainingBytes >= len) {
            remainingBytes -= len
            return stream.read(buf, len)
        } else {
            throw Exception("not enough bytes available $remainingBytes")
        }
    }

    public override fun read(buf: ByteArray, off: Int, len: Int): Int {
        // println("remainingBytes '$remainingBytes'")
        if (remainingBytes >= len) {
            remainingBytes -= len
            return stream.read(buf, off, len)
        } else {
            throw Exception("not enough bytes available $remainingBytes")
        }
    }

    public override fun close() {
        // println("remainingBytes '$remainingBytes'")
        stream.close()
    }

    public override fun readLine(): String? {
// TODO this may break on utf-8
        // println("remainingBytes '$remainingBytes'")
        var buf = mutableListOf<Byte>()
        // println("readLine start")
        try {
            var b = readByte()
            // println("b 0x${b.toString(16)} ''${b.toChar()}''")
            while (b != '\n'.toByte()) {
                if (b != '\r'.toByte()) {
                    buf.add(b)
                }
                b = readByte()
                // println("b 0x${b.toString(16)} ''${b.toChar()}''")
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            if (buf.size == 0) {
                return null
            }
        }
        // println("readLine end ''${buf.toByteArray().decodeToString()}''")
        return buf.toByteArray().decodeToString()
    }
}
