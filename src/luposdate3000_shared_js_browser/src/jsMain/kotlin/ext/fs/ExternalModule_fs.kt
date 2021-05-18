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
    internal val inmemoryFs = mutableMapOf<String, ByteArray>()
    private var tmpCounter = 0
    public fun createTempDirectory(): String {
        return "tmp${tmpCounter++}"
    }

    public fun exists(filename: String): Boolean {
        return inmemoryFs[filename] != null
    }

    public fun mkdirs(filename: String): Boolean {
        return true
    }

    public fun deleteRecursively(filename: String): Boolean {
        var changed = true
        loop@ while (changed) {
            changed = false
            for (k in inmemoryFs.keys) {
                if (k.startsWith(filename)) {
                    inmemoryFs.remove(k)
                    changed = true
                    continue@loop
                }
            }
        }
        return true
    }

    public fun length(filename: String): Long {
        val f = inmemoryFs[filename]
        if (f == null) {
            return 0
        } else {
            return f.size.toLong()
        }
    }

    public fun openOutputStream(filename: String, append: Boolean): JSOutputStream {
        return JSOutputStream(filename, append)
    }
}

public class JSOutputStream(private val filename: String, append: Boolean) {
    private var buffer: ByteArray

    init {
        if (append) {
            val v = ExternalModule_fs.inmemoryFs[filename]
            if (v != null) {
                buffer = v
            } else {
                buffer = ByteArray(0)
            }
        } else {
            buffer = ByteArray(0)
        }
    }

    public fun writeInt(value: Int) {
        val b = ByteArray(buffer.size + 4)
        buffer.copyInto(b)
        b[buffer.size] = ((value shr 24) and 0xFF).toByte()
        b[buffer.size + 1] = ((value shr 16) and 0xFF).toByte()
        b[buffer.size + 2] = ((value shr 8) and 0xFF).toByte()
        b[buffer.size + 3] = (value and 0xFF).toByte()
        buffer = b
    }

    public fun write(buf: ByteArray) {
        val b = ByteArray(buffer.size + buf.size)
        buffer.copyInto(b)
        buf.copyInto(b, buffer.size)
        buffer = b
    }

    public fun write(buf: ByteArray, len: Int) {
        val b = ByteArray(buffer.size + len)
        buffer.copyInto(b)
        buf.copyInto(b, buffer.size, 0, len)
        buffer = b
    }

    public fun close() {
        ExternalModule_fs.inmemoryFs[filename] = buffer
    }

    public fun flush() {
    }

    public fun println(x: String) {
        print("$x\n")
    }

    public fun print(x: String) {
        write(x.encodeToByteArray())
    }

    public fun print(x: Boolean) {
        print("$x")
    }

    public fun print(x: Int) {
        print("$x")
    }

    public fun print(x: Double) {
        print("$x")
    }

    public fun println() {
        print("\n")
    }
}

public class JSInputStream {
    internal var pos = 0
    internal lateinit var buffer: ByteArray

    public constructor(filename: String) {
        buffer = ExternalModule_fs.inmemoryFs[filename]!!
    }

    public constructor(fd: Int) {
        buffer = ByteArray(0)
        throw Exception("not implemented")
    }

    public fun readInt(): Int {
        val res = (((buffer[pos].toInt() and 0xFF) shl 24) or ((buffer[pos + 1].toInt() and 0xFF) shl 16) or ((buffer[pos + 2].toInt() and 0xFF) shl 8) or ((buffer[pos + 3].toInt() and 0xFF)))
        pos += 4
        return res
    }

    public fun readByte(): Byte {
        return buffer[pos++]
    }

    public fun read(buf: ByteArray, off: Int, len: Int): Int {
        var l = len
        if (len + off > buffer.size - pos) {
            l = buffer.size - pos
        }
        if (l > 0) {
            buffer.copyInto(buf, 0, off, len)
        }
        return l
    }

    public fun read(buf: ByteArray, len: Int): Int {
        return read(buf, 0, len)
    }

    public fun read(buf: ByteArray): Int {
        return read(buf, 0, buf.size)
    }

    public fun close() {
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
