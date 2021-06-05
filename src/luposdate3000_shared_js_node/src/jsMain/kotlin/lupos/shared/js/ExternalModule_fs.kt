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
package lupos.shared.js

public object ExternalModule_fs {
    public fun openSync(filename: String, flags: String): Int = openSync_(filename, flags)
    public fun readSync(fd: Int, buffer: ByteArray, offset: Int, length: Int, position: Int): Int = readSync_(fd, buffer, offset, length, position)
    public fun writeSync(fd: Int, buffer: ByteArray, offset: Int, length: Int, position: Int): Int = writeSync_(fd, buffer, offset, length, position)
    public fun closeSync(fd: Int): Unit = closeSync_(fd)
    public fun readFileSync(filename: String): ByteArray = readFileSync_(filename)
    public fun exists(filename: String): Boolean = throw Exception("not implemented")
    public fun mkdirs(filename: String): Boolean = throw Exception("not implemented")
    public fun deleteRecursively(filename: String): Boolean = throw Exception("not implemented")
    public fun length(filename: String): Long = throw Exception("not implemented")
    public fun createTempDirectory(): String = throw Exception("not implemented")
    public fun openOutputStream(filename: String, append: Boolean): JSOutputStream {
        return JSOutputStream(filename, append)
    }
}

public class JSOutputStream(private val filename: String, append: Boolean) {
    public fun writeInt(value: Int): Unit = throw Exception("not implemented")
    public fun write(buf: ByteArray): Unit = throw Exception("not implemented")
    public fun write(buf: ByteArray, len: Int): Unit = throw Exception("not implemented")
    public fun close(): Unit = throw Exception("not implemented")
    public fun flush(): Unit = throw Exception("not implemented")
    public fun println(x: String): Unit = throw Exception("not implemented")
    public fun print(x: String): Unit = throw Exception("not implemented")
    public fun print(x: Boolean): Unit = throw Exception("not implemented")
    public fun print(x: Int): Unit = throw Exception("not implemented")
    public fun print(x: Double): Unit = throw Exception("not implemented")
    public fun println(): Unit = throw Exception("not implemented")
}

public class JSInputStream {
    internal val fd: Int
    internal var pos = 0

    public constructor(filename: String) {
        this.fd = ExternalModule_fs.openSync(filename, "r")
    }

    public constructor(fd: Int) {
        this.fd = fd
    }

    public fun readInt(): Int {
        val buffer = ByteArray(4)
        val l = ExternalModule_fs.readSync(fd, buffer, 0, buffer.size, pos)
        if (l != 4) {
            throw Exception("invalid len $l")
        }
        pos += l
        return (((buffer[0].toInt() and 0xFF) shl 24) or ((buffer[1].toInt() and 0xFF) shl 16) or ((buffer[2].toInt() and 0xFF) shl 8) or ((buffer[3].toInt() and 0xFF)))
    }

    public fun readByte(): Byte {
        val buffer = ByteArray(1)
        val l = ExternalModule_fs.readSync(fd, buffer, 0, buffer.size, pos)
        if (l != 1) {
            throw Exception("invalid len $l")
        }
        pos += l
        return buffer[0]
    }

    public fun read(buf: ByteArray, off: Int, len: Int): Int {
        val l = ExternalModule_fs.readSync(fd, buf, off, len, pos)
        pos += l
        return l
    }

    public fun read(buf: ByteArray, len: Int): Int {
        var off = 0
        var l = len
        while (l > 0) {
            val tmp = ExternalModule_fs.readSync(fd, buf, off, len, pos)
            if (tmp <= 0) {
                return len - l
            }
            l -= len
            off += len
            pos += tmp
        }
        return len
    }

    public fun read(buf: ByteArray): Int {
        return read(buf, buf.size)
    }

    public fun close() {
        ExternalModule_fs.closeSync(fd)
    }

    public fun readLine(): String? {
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
