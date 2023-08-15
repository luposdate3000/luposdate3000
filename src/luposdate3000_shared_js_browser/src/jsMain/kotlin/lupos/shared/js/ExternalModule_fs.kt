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
        return if (f == null) {
            0
        } else {
            f.size.toLong()
        }
    }

    public fun openOutputStream(filename: String, append: Boolean): JSOutputStream {
        return JSOutputStream(filename, append)
    }
}

public class JSOutputStream(private val filename: String, append: Boolean) {
    private var buffer: ByteArray
    private var bufferSize: Int

    init {
        if (append) {
            val v = ExternalModule_fs.inmemoryFs[filename]
            if (v != null) {
                buffer = v
                bufferSize = v.size
            } else {
                buffer = ByteArray(1024)
                bufferSize = 0
            }
        } else {
            buffer = ByteArray(1024)
            bufferSize = 0
        }
    }

    private fun reserveSpace(size: Int) {
        if (bufferSize + size > buffer.size) {
            var destSize = 1024
            while (destSize < size + bufferSize) {
                destSize *= 2
            }
            val b = ByteArray(destSize)
            buffer.copyInto(b)
            buffer = b
        }
    }

    public fun writeInt(value: Int) {
        reserveSpace(4)
        buffer[bufferSize] = ((value shr 24) and 0xFF).toByte()
        buffer[bufferSize + 1] = ((value shr 16) and 0xFF).toByte()
        buffer[bufferSize + 2] = ((value shr 8) and 0xFF).toByte()
        buffer[bufferSize + 3] = (value and 0xFF).toByte()
        bufferSize += 4
    }

    public fun writeLong(value: Long) {
        reserveSpace(8)
        buffer[bufferSize] = ((value shr 56) and 0xFF).toByte()
        buffer[bufferSize + 1] = ((value shr 48) and 0xFF).toByte()
        buffer[bufferSize + 2] = ((value shr 40) and 0xFF).toByte()
        buffer[bufferSize + 3] = ((value shr 32) and 0xFF).toByte()
        buffer[bufferSize + 4] = ((value shr 24) and 0xFF).toByte()
        buffer[bufferSize + 5] = ((value shr 16) and 0xFF).toByte()
        buffer[bufferSize + 6] = ((value shr 8) and 0xFF).toByte()
        buffer[bufferSize + 7] = (value and 0xFF).toByte()
        bufferSize += 8
    }

    public fun write(buf: ByteArray) {
        write(buf, buf.size)
    }

    public fun write(buf: ByteArray, len: Int) {
        reserveSpace(len)
        buf.copyInto(buffer, bufferSize, 0, len)
        bufferSize += len
    }

    public fun close() {
        val b = ByteArray(bufferSize)
        buffer.copyInto(b, 0, 0, bufferSize)
        ExternalModule_fs.inmemoryFs[filename] = b
    }

    public fun flush() {
    }

    public fun println(x: String) {
        print("$x\n")
    }

    public fun print(x: String) {
        var p = bufferSize
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
        TODO()
    }

    public fun readInt(): Int {
        val res = (((buffer[pos].toInt() and 0xFF) shl 24) or ((buffer[pos + 1].toInt() and 0xFF) shl 16) or ((buffer[pos + 2].toInt() and 0xFF) shl 8) or ((buffer[pos + 3].toInt() and 0xFF)))
        pos += 4
        return res
    }

    public fun readLong(): Long {
        val res = (((buffer[pos].toLong() and 0xFF) shl 56) or ((buffer[pos + 1].toLong() and 0xFF) shl 48) or ((buffer[pos + 2].toLong() and 0xFF) shl 40) or ((buffer[pos + 3].toLong() and 0xFF) shl 32) or ((buffer[pos + 4].toLong() and 0xFF) shl 24) or ((buffer[pos + 5].toLong() and 0xFF) shl 16) or ((buffer[pos + 6].toLong() and 0xFF) shl 8) or ((buffer[pos + 7].toLong() and 0xFF)))
        pos += 8
        return res
    }

    public fun readByte(): Byte {
        return buffer[pos++]
    }

    public fun read(buf: ByteArray, off: Int, len: Int): Int {
        var ll = len
        if (ll > buf.size - off) {
            ll = buf.size - off
        }
        var end = ll + pos
        if (end > buffer.size) {
            end = buffer.size
        }
        val res = end - pos
        if (end >= pos) {
            buffer.copyInto(buf, off, pos, end)
        }
        pos = end
        return res
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
        val buf = mutableListOf<Byte>()
        try {
            var b = readByte()
            while (b != '\n'.code.toByte()) {
                if (b != '\r'.code.toByte()) {
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
