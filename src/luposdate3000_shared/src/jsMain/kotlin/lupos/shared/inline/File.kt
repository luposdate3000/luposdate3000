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
package lupos.shared.inline

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.js.ExternalModule_fs

public actual class File {
    public val filename: String

    actual constructor(filename: String) {
        this.filename = filename
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun getAbsolutePath(): String = TODO("File.getAbsolutePath")

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun exists(): Boolean = ExternalModule_fs.exists(filename)

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun mkdirs(): Boolean = ExternalModule_fs.mkdirs(filename)

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun deleteRecursively(): Boolean = ExternalModule_fs.deleteRecursively(filename)

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun length(): Long = ExternalModule_fs.length(filename)

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readAsString(): String {
        var res = StringBuilder()
        forEachLine { it ->
            res.appendLine(it)
        }
        return res.toString()
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readAsCharIterator(): CharIterator = TODO("File.readAsCharIterator")

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun openInputStream(): IMyInputStream {
        return MyInputStream(filename)
    }

    public actual inline fun walk(crossinline action: (String) -> Unit): Unit = TODO("File.walk")
    public actual inline fun walk(maxdepth: Int, crossinline action: (String) -> Unit): Unit = TODO("File.walk")
    public actual inline fun forEachLine(crossinline action: (String) -> Unit) {
        val stream = MyInputStream(filename)
        val buffer = ByteArray(8192)
        var pos = 0
        val s = mutableListOf<Byte>()
        while (true) {
            val len = stream.read(buffer, buffer.size)
            if (len == 0) {
                break
            }
            for (i in 0 until len) {
                val b = buffer[i]
                if (b == '\r'.toInt().toByte() || b == '\n'.toInt().toByte()) {
                    action(s.toByteArray().decodeToString())
                    s.clear()
                } else {
                    s.add(b)
                }
            }
            pos += len
        }
        action(s.toByteArray().decodeToString())
        stream.close()
    }

    public actual inline fun withOutputStream(crossinline action: (IMyOutputStream) -> Unit) {
        val stream = openOutputStream(false)
        try {
            action(stream)
        } finally {
            stream.close()
        }
    }

    public actual inline fun withInputStream(crossinline action: (IMyInputStream) -> Unit) {
        val stream = MyInputStream(filename)
        action(stream)
        stream.close()
    }

    actual override fun equals(other: Any?): Boolean = TODO("File.equals")

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun openOutputStream(append: Boolean): IMyOutputStream {
        return object : IMyOutputStream {
            val tmp = ExternalModule_fs.openOutputStream(filename, append)
            override fun writeInt(value: Int): Unit = tmp.writeInt(value)
            override fun writeLong(value: Long) = tmp.writeLong(value)
            override fun writeDictionaryValueType(value: DictionaryValueType) = DictionaryValueHelper.sendToStream(this, value)
            override fun write(buf: ByteArray): Unit = tmp.write(buf)
            override fun write(buf: ByteArray, len: Int): Unit = tmp.write(buf, len)
            override fun close(): Unit = tmp.close()
            override fun flush(): Unit = tmp.flush()
            override fun println(x: String) = tmp.println(x)
            override fun print(x: String) = tmp.print(x)
            override fun print(x: Boolean) = tmp.print(x)
            override fun print(x: Int) = tmp.print(x)
            override fun print(x: Double) = tmp.print(x)
            override fun println() = tmp.println()
        }
    }
}
