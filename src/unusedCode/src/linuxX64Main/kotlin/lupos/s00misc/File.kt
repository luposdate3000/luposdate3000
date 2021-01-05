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
public class File(val filename: String) {
    public fun readAsString(): String {
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
    public fun walk(action: (String) -> Unit) {
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
    public fun printWriter(action: (PrintWriter) -> Unit) {
        val p = PrintWriter(this)
        try {
            p.open()
            action(p)
        } finally {
            p.close()
        }
    }
}
public class PrintWriter(val f: File) {
    var file: CValuesRef<FILE>? = null
    public fun open() {
        file = fopen(f.filename, "w")
        if (f == null) {
            throw ResourceNotFoundException(f.filename)
        }
    }
    public fun close() {
        fclose(file)
    }
    public fun println(s: String) {
        luposfprintf(file, s + "\n")
    }
}
