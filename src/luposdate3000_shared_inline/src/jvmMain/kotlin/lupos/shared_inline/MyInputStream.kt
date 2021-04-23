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
package lupos.shared_inline

import lupos.shared.IMyInputStream
import lupos.shared.SanityCheck
import lupos.shared.UUID_Counter
import java.io.InputStream
import kotlin.jvm.JvmField

internal actual class MyInputStream(@JvmField internal val stream: InputStream) : IMyInputStream {
    @JvmField
    internal val buf4: ByteArray = ByteArray(4)

    @JvmField
    internal val uuid = UUID_Counter.getNextUUID()

    init {
        // println("MyInputStream $uuid open")
    }

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
        // println("MyInputStream $uuid readByte start")
        read(buf4, 1)
        // println("MyInputStream $uuid readByte done ${(0xFF and buf4[0].toInt()).toString(16).padStart(2, '0')}")
        return buf4[0]
    }

    public actual override fun close() {
        stream.close()
        // println("MyInputStream $uuid close")
    }

    @JvmField
    internal var buffer = ByteArray(1)
    public actual override fun readLine(): String? {
// TODO this may break on utf-8 if '\r' or '\0' is part of another char
        var len = 0
        try {
            var b = readByte()
            while (true) {
                when (b) {
                    '\n'.toByte() -> break
                    '\r'.toByte() -> {
                    }
                    0.toByte() -> throw Exception("zero Bytes not allowed within utf8-string")
                    else -> {
                        if (len >= buffer.size) {
                            SanityCheck {
                                try {
                                    throw Exception("resize input buffer from $len '${buffer.decodeToString()}'")
                                } catch (e: Throwable) {
                                    e.printStackTrace()
                                }
                            }
                            val bb = ByteArray(len * 2)
                            buffer.copyInto(bb)
                            buffer = bb
                        }
                        buffer[len++] = b
                    }
                }
                b = readByte()
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            if (len == 0) {
                return null
            }
        }
        val res = buffer.decodeToString(0, len)
        // println("MyInputStream $uuid readLine '$res'")
        return res
    }
}
