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

import lupos.shared.DictionaryValueType
import lupos.shared.IMyOutputStream
import lupos.shared.MyPrintWriterMode
import lupos.shared.MyPrintWriterModeExt
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.jvm.JvmField

internal actual open class MyPrintWriter : IMyOutputStream {
    @JvmField
    val buffer = StringWriter()

    @JvmField
    val printer: PrintWriter

    @JvmField
    val bufferMode: MyPrintWriterMode

    actual constructor(hasBuffer: Boolean) {
        if (hasBuffer) {
            bufferMode = MyPrintWriterModeExt.BUFFER
            printer = PrintWriter(buffer)
        } else {
            bufferMode = MyPrintWriterModeExt.NONE
            printer = PrintWriter(buffer)
        }
    }

    actual fun clearBuffer() {
        if (bufferMode == MyPrintWriterModeExt.BUFFER) {
            buffer.buffer.setLength(0)
        } else {
            TODO("MyPrintWriter")
        }
    }

    actual override fun toString(): String {
        if (bufferMode == MyPrintWriterModeExt.BUFFER) {
            return buffer.toString()
        } else {
            TODO("MyPrintWriter")
        }
    }

    actual override fun println(x: String) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            printer.println(x)
        }
    }

    actual override fun print(x: String) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            printer.print(x)
        }
    }

    actual fun println(x: Boolean) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            printer.println(x)
        }
    }

    actual override fun print(x: Boolean) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            printer.print(x)
        }
    }

    actual fun println(x: Int) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            printer.println(x)
        }
    }

    actual override fun print(x: Int) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            printer.print(x)
        }
    }

    actual fun println(x: Double) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            printer.println(x)
        }
    }

    actual override fun print(x: Double) {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            printer.print(x)
        }
    }

    actual override fun println() {
        if (bufferMode != MyPrintWriterModeExt.NONE) {
            printer.println()
        }
    }

    actual override fun write(buf: ByteArray, len: Int): Unit = TODO("MyPrintWriter")
    actual override fun write(buf: ByteArray): Unit = TODO("MyPrintWriter")
    actual override fun writeInt(value: Int): Unit = TODO("MyPrintWriter")
    actual override fun writeDictionaryValueType(value: DictionaryValueType): Unit = TODO("MyPrintWriter")
    actual override fun writeLong(value: Long): Unit = TODO("MyPrintWriter")
    actual override fun close(): Unit = TODO("MyPrintWriter")
    actual override fun flush(): Unit = TODO("MyPrintWriter")
}
