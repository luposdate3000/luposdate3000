package lupos.s00misc

import java.io.File
import java.io.PrintWriter
import java.io.StringWriter


internal class MyPrintWriter :IPrintWriter{
internal enum class MyPrintWriterMode {
    BUFFER,
    FILE,
    NONE
}
    val buffer = StringWriter()
    val printer: PrintWriter
    val bufferMode: MyPrintWriterMode

    constructor(hasBuffer: Boolean = true) {
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

    override fun clearBuffer() {
        if (bufferMode == MyPrintWriterMode.BUFFER) {
            buffer.getBuffer().setLength(0)
        } else {
            throw Exception("not supported")
        }
    }

    override fun toString(): String {
        if (bufferMode == MyPrintWriterMode.BUFFER) {
            return buffer.toString()
        } else {
            throw Exception("not supported")
        }
    }

    override fun println(x: String) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.println(x)
        }
    }

    override fun print(x: String) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.print(x)
        }
    }

    override fun println(x: Boolean) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.println(x)
        }
    }

    override fun print(x: Boolean) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.print(x)
        }
    }

    override fun println(x: Int) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.println(x)
        }
    }

    override fun print(x: Int) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.print(x)
        }
    }

    override fun println(x: Double) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.println(x)
        }
    }

    override fun print(x: Double) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.print(x)
        }
    }

    override fun println() {
        if (bufferMode != MyPrintWriterMode.NONE) {
            printer.println()
        }
    }

    override fun close() {
        if (bufferMode == MyPrintWriterMode.FILE) {
            printer.close()
        } else {
            throw Exception("not supported")
        }
    }
}
