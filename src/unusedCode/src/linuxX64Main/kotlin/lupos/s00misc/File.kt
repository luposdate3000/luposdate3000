package lupos.s00misc

import dirent.closedir
import dirent.opendir
import dirent.readdir
import kotlinx.cinterop.*
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import platform.posix.FILE
import stdio.fclose
import stdio.fgets
import stdio.fopen
import stdio.luposfprintf
import kotlin.native.concurrent.*

class File(val filename: String) {
    fun readAsString(): String {
        var result: String = ""
        val file = fopen(filename, "r")
        if (file == null) {
            throw ResourceNotFoundException(filename)
        }
        try {
            memScoped {
                const val bufferLength = 64 * 1024
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
                action(filename + "/" + dir.pointed.d_name!!.toKString())
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
    var file: CValuesRef<FILE>? = null
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
