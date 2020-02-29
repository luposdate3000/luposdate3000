package lupos.s00misc

import dirent.*
import kotlinx.cinterop.*
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.memScoped
import stdio.*


actual class File {
    actual val filename: String

    actual constructor(filename: String) {
        this.filename = filename
    }

    actual fun readAsString(): String {
        var result: String = ""
        val file = fopen(filename, "r")
        if (file == null)
            throw Exception("can not open file $filename")
        try {
            memScoped {
                val bufferLength = 64 * 1024
                val buffer = allocArray<ByteVar>(bufferLength)
                while (true) {
                    val nextLine = fgets(buffer, bufferLength, file)?.toKString()
                    if (nextLine == null || nextLine.isEmpty())
                        break
                    result += nextLine
                }
            }
        } finally {
            fclose(file)
        }
        return result
    }

    actual fun walk(action: (String) -> Unit) {
        val d = opendir(filename);
        if (d != null) {
            while (true) {
                val dir = readdir(d)
                if (dir == null)
                    break
                action(dir.pointed.d_name!!.toKString())
            }
            closedir(d);
        }
    }

    actual fun readAsDynamicByteArray(): DynamicByteArray {
        var res = ByteArray(0)
        val file = fopen(filename, "r")
        if (file == null)
            throw Exception("can not open file $filename")
        try {
            memScoped {
                val bufferLength = 64 * 1024
                val buffer = allocArray<ByteVar>(bufferLength)
                while (true) {
                    val len = fread(buffer, bufferLength.toULong(), 1L.toULong(), file)
                    if (len == (0L).toULong())
                        break
                    res += buffer.readBytes(len.toInt())
                }
            }
        } finally {
            fclose(file)
        }
        return DynamicByteArray(res)
    }

    actual fun write(buffer: DynamicByteArray) {
        val file = fopen(filename, "w")
        if (file == null)
            throw Exception("can not open file $filename")
        try {
            var offset = 0
            val buf = buffer.finish()
            while (offset < buffer.pos) {
                val len = fwrite(buf.refTo(offset), (buffer.pos - offset).toULong(), 1L.toULong(), file)
                offset += len.toInt()
            }
        } finally {
            fclose(file)
        }
    }

    actual fun printWriter(action: (PrintWriter) -> Unit) {
        val p = PrintWriterImpl(this)
        try {
            p.open()
            action(p)
        } finally {
            p.close()
        }
    }
}


class PrintWriterImpl(val f: File) : PrintWriter {
    var file: CPointer<FILE>? = null
    fun open() {
        file = fopen(f.filename, "w")
        if (f == null)
            throw Exception("can not open file ${f.filename}")
    }

    fun close() {
        fclose(file)
    }

    override fun println(s: String) {
        luposfprintf(file, s + "\n")
    }
}
