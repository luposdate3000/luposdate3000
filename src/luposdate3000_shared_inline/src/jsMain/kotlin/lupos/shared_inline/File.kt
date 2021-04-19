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

import lupos.s00misc.IMyInputStream
import lupos.s00misc.IMyOutputStream
import lupos.s00misc.MyInputStream
import lupos.s00misc.NotImplementedException
import kotlin.jvm.JvmField

internal actual class File {
    @JvmField
    internal val filename: String

    actual constructor(filename: String) {
        this.filename = filename
    }

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun createTempFile(prefix: String, suffix: String, directory: String): String = throw NotImplementedException("File", "createTempFile not implemented")

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun exists(): Boolean = throw NotImplementedException("File", "exists not implemented")

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun mkdirs(): Boolean = throw NotImplementedException("File", "mkdirs not implemented")

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun deleteRecursively(): Boolean = throw NotImplementedException("File", "deleteRecursively not implemented")

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun length(): Long = throw NotImplementedException("File", "length not implemented")

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun readAsString(): String {
        var res = StringBuilder()
        forEachLine { it ->
            res.appendLine(it)
        }
        return res.toString()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun readAsCharIterator(): CharIterator = throw NotImplementedException("File", "readAsCharIterator not implemented")

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun openInputStream(): IMyInputStream = throw NotImplementedException("File", "openInputStream not implemented")
    internal actual inline fun walk(crossinline action: (String) -> Unit): Unit = throw NotImplementedException("File", "walk not implemented")
    internal actual inline fun forEachLine(crossinline action: (String) -> Unit) {
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

    internal actual inline fun withOutputStream(crossinline action: (IMyOutputStream) -> Unit): Unit = throw NotImplementedException("File", "withOutputStream not implemented")
    internal actual inline fun withInputStream(crossinline action: (IMyInputStream) -> Unit) {
        val stream = MyInputStream(filename)
        action(stream)
        stream.close()
    }

    actual override fun equals(other: Any?): Boolean = throw NotImplementedException("File", "equals not implemented")

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun openOutputStream(append: Boolean): IMyOutputStream = throw NotImplementedException("File", "openOutputStream not implemented")
}
