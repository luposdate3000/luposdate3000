package lupos.modulename
import lupos.s00misc.IMyPrintWriter
internal actual open class _MyPrintWriter : IMyPrintWriter {
    val buffer = StringBuilder()
    val bufferMode: MyPrintWriterMode
    actual constructor(hasBuffer: Boolean) {
        if (hasBuffer) {
            bufferMode = MyPrintWriterMode.BUFFER
        } else {
            bufferMode = MyPrintWriterMode.NONE
        }
    }
    actual override fun clearBuffer() {
        if (bufferMode == MyPrintWriterMode.BUFFER) {
            buffer.clear()
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
            buffer.appendLine(x)
        }
    }
    actual override fun print(x: String) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            buffer.append(x)
        }
    }
    actual override fun println(x: Boolean) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            buffer.appendLine(x)
        }
    }
    actual override fun print(x: Boolean) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            buffer.append(x)
        }
    }
    actual override fun println(x: Int) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            buffer.appendLine(x)
        }
    }
    actual override fun print(x: Int) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            buffer.append(x)
        }
    }
    actual override fun println(x: Double) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            buffer.appendLine(x)
        }
    }
    actual override fun print(x: Double) {
        if (bufferMode != MyPrintWriterMode.NONE) {
            buffer.append(x)
        }
    }
    actual override fun println() {
        if (bufferMode != MyPrintWriterMode.NONE) {
            buffer.appendLine()
        }
    }
    actual override fun close() {
        throw Exception("not supported")
    }
    actual override fun flush() {
        throw Exception("not supported")
    }
}
