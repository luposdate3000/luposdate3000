package lupos.s00misc

import dirent.*
import kotlinx.cinterop.*
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import stdio.*
import kotlin.native.concurrent.*

class File {
    val filename: String

    constructor(filename: String) {
        this.filename = filename
    }

    const val bufferLength = 64 * 1024
    fun readAsString(): String {
        var result: String = ""
        val file = fopen(filename, "r")
        if (file == null) {
            throw ResourceNotFoundException(filename)
        }
        try {
            memScoped {
                val buffer = allocArray<ByteVar>(bufferLength)
                while (true) {
                    val nextLine = fgets(buffer, bufferLength, file)?.toKString()
                    if (nextLine == null || nextLine.isEmpty()) {
                        break
                    }
                    result += nextLine
                }
            }
        } finally {
            fclose(file)
        }
        return result
    }

    fun walk(action: (String) -> Unit) {
        val d = opendir(filename)
        if (d != null) {
            while (true) {
                val dir = readdir(d)
                if (dir == null) {
                    break
                }
                action(dir.pointed.d_name!!.toKString())
            }
            closedir(d)
        }
    }

    fun printWriter(action: (PrintWriter) -> Unit) {
        val p = PrintWriter(this)
        try {
            p.open()
            action(p)
        } finally {
            p.close()
        }
    }
}

class PrintWriter(val f: File) {
    var file: CPointer<FILE>? = null
    fun open() {
        file = fopen(f.filename, "w")
        if (f == null) {
            throw ResourceNotFoundException(f.filename)
        }
    }

    fun close() {
        fclose(file)
    }

    fun println(s: String) {
        luposfprintf(file, s + "\n")
    }
}
