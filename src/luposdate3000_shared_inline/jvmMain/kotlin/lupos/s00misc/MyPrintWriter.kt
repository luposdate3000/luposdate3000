package lupos.s00misc

import java.io.File
import java.io.OutputStream
import java.io.PrintWriter
import java.io.StringWriter

open actual internal class MyPrintWriter : IMyPrintWriter {
    @JvmField
    val buffer = StringWriter()
    @JvmField
    val printer: PrintWriter
    @JvmField
    val bufferMode: MyPrintWriterMode

    actual constructor(hasBuffer: Boolean) {
        if (hasBuffer) {
            bufferMode = MyPrintWriterMode.BUFFER
            printer = PrintWriter(buffer)
        } else {
            bufferMode = MyPrintWriterMode.NONE
            printer = PrintWriter(buffer)
        }
    }

    constructor(f: File) {
        bufferMode = MyPrintWriterMode.FILE
        printer = f.printWriter()
    }

    constructor(o: OutputStream) {
        bufferMode = MyPrintWriterMode.FILE
        printer = PrintWriter(o, false)
    }

    actual override fun clearBuffer() {
        if (bufferMode == MyPrintWriterMode.BUFFER) {
            buffer.getBuffer().setLength(0)
        } else {
            throw Exception("not supported")
        }
    }

    actual override fun toString(): String {
        if (bufferMode == MyPrintWriterMode.BUFFER) {
            return buffer.toString()
        } else {
            throw Exception("not supported")
        }
    }

    actual override fun println(x: String) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.println(x)
        }
    }

    actual override fun print(x: String) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.print(x)
        }
    }

    actual override fun println(x: Boolean) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.println(x)
        }
    }

    actual override fun print(x: Boolean) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.print(x)
        }
    }

    actual override fun println(x: Int) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.println(x)
        }
    }

    actual override fun print(x: Int) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.print(x)
        }
    }

    actual override fun println(x: Double) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.println(x)
        }
    }

    actual override fun print(x: Double) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.print(x)
        }
    }

    actual override fun println() {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.println()
        }
    }

    actual override fun close() {
        if (bufferMode == MyPrintWriterMode.FILE) {
            printer.close()
        } else {
            throw Exception("not supported")
        }
    }

    actual override fun flush() {
        if (bufferMode == MyPrintWriterMode.FILE) {
            printer.flush()
        } else {
            throw Exception("not supported")
        }
    }
}
