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

import lupos.s00misc.IMyPrintWriter
import lupos.s00misc.MyPrintWriterMode
import lupos.s00misc.MyPrintWriterModeExt
import java.io.OutputStream
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.jvm.JvmField

internal actual open class _MyPrintWriter : IMyPrintWriter {
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

    public constructor(f: java.io.File) {
        bufferMode = MyPrintWriterModeExt.FILE
        printer = f.printWriter()
    }

    public constructor(o: OutputStream) {
        bufferMode = MyPrintWriterModeExt.FILE
        printer = PrintWriter(o, false)
    }

    actual fun clearBuffer() {
        if (bufferMode == MyPrintWriterModeExt.BUFFER) {
            buffer.buffer.setLength(0)
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

    actual fun close() {
        if (bufferMode == MyPrintWriterModeExt.FILE) {
            printer.close()
        } else {
            throw Exception("not supported")
        }
    }

    actual fun flush() {
        if (bufferMode == MyPrintWriterModeExt.FILE) {
            printer.flush()
        } else {
            throw Exception("not supported")
        }
    }
}
