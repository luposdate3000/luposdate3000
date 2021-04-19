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

internal actual class MyInputStream : IMyInputStream {
    internal val fd: Int
    internal var pos = 0

    internal constructor(filename: String) {
        this.fd = ext.fs.ExternalModule_fs.openSync(filename, "r")
    }

    internal constructor(fd: Int) {
        this.fd = fd
    }

    public actual override fun readInt(): Int {
        val buffer = ByteArray(4)
        val l = ext.fs.ExternalModule_fs.readSync(fd, buffer, 0, buffer.size, pos)
        if (l != 4) {
            throw Exception("invalid len $l")
        }
        pos += l
        return ByteArrayHelper.readInt4(buffer, 0)
    }

    public actual override fun readByte(): Byte {
        val buffer = ByteArray(1)
        val l = ext.fs.ExternalModule_fs.readSync(fd, buffer, 0, buffer.size, pos)
        if (l != 1) {
            throw Exception("invalid len $l")
        }
        pos += l
        return buffer[0]
    }

    public actual override fun read(buf: ByteArray, off: Int, len: Int): Int {
        val l = ext.fs.ExternalModule_fs.readSync(fd, buf, off, len, pos)
        pos += l
        return l
    }

    public actual override fun read(buf: ByteArray, len: Int): Int {
        var off = 0
        var l = len
        while (l > 0) {
            val tmp = ext.fs.ExternalModule_fs.readSync(fd, buf, off, len, pos)
            if (tmp <= 0) {
                return len - l
            }
            l -= len
            off += len
            pos += tmp
        }
        return len
    }

    public actual override fun read(buf: ByteArray): Int {
        return read(buf, buf.size)
    }

    public actual override fun close() {
        ext.fs.ExternalModule_fs.closeSync(fd)
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
            e.printStackTrace()
            if (buf.size == 0) {
                return null
            }
        }
        return buf.toByteArray().decodeToString()
    }
}
