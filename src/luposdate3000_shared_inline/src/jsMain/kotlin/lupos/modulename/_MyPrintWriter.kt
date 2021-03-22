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

import lupos.s00misc.IMyOutputStream
import lupos.s00misc.MyPrintWriterMode
import lupos.s00misc.MyPrintWriterModeExt

internal actual open class _MyPrintWriter : IMyOutputStream {
    val buffer = StringBuilder()
    val bufferMode: MyPrintWriterMode
    val fileName: String
    var file: Int
    var filePos: Int = 0

    actual constructor(hasBuffer: Boolean) {
        if (hasBuffer) {
            bufferMode = MyPrintWriterModeExt.BUFFER
        } else {
            bufferMode = MyPrintWriterModeExt.NONE
        }
        fileName = ""
        file = -1
    }

    actual fun clearBuffer() {
        if (bufferMode == MyPrintWriterModeExt.BUFFER) {
            buffer.clear()
        } else {
            throw Exception("not supported")
        }
    }

    actual override fun toString(): String {
        if (bufferMode == MyPrintWriterModeExt.BUFFER) {
            return buffer.toString()
        } else {
            throw Exception("not supported")
        }
    }

    actual override fun println(x: String) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.appendLine(x)
        }
    }

    actual override fun print(x: String) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.append(x)
        }
    }

    actual fun println(x: Boolean) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.appendLine(x)
        }
    }

    actual override fun print(x: Boolean) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.append(x)
        }
    }

    actual fun println(x: Int) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.appendLine(x)
        }
    }

    actual override fun print(x: Int) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.append(x)
        }
    }

    actual fun println(x: Double) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.appendLine(x)
        }
    }

    actual override fun print(x: Double) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.append(x)
        }
    }

    actual override fun println() {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            buffer.appendLine()
        }
    }

    actual override fun write(buf: ByteArray, len: Int): Unit = throw Exception("not supported")
    actual override fun write(buf: ByteArray): Unit = throw Exception("not supported")
    public actual override fun writeInt(value: Int): Unit = throw Exception("not supported")
    actual override fun close(): Unit = throw Exception("not supported")
    actual override fun flush(): Unit = throw Exception("not supported")
}
