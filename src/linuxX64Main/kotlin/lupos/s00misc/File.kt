package lupos.s00misc
import dirent.closedir
import dirent.opendir
import dirent.readdir
import kotlin.native.concurrent.*
import kotlinx.cinterop.*
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.memScoped
import lupos.s00misc.Coverage
import lupos.s04logicalOperators.Query
import platform.posix.FILE
import stdio.fclose
import stdio.fgets
import stdio.fopen
import stdio.fread
import stdio.fwrite
import stdio.luposfprintf
import unistd.luposSTDINread
class File(val filename: String) {
    companion object {
        fun readStdInAsDynamicByteArray(): DynamicByteArray? {
Coverage.funStart(17077)
            var res = ByteArray(0)
Coverage.statementStart(17078)
            memScoped {
Coverage.statementStart(17079)
                val bufferLength = 64 * 1024
Coverage.statementStart(17080)
                val buffer = allocArray<ByteVar>(bufferLength)
Coverage.statementStart(17081)
                while (true) {
Coverage.whileLoopStart(17082)
                    val len = luposSTDINread(buffer, bufferLength.toULong())
Coverage.statementStart(17083)
                    if (len <= 0) {
Coverage.ifStart(17084)
                        break
                    }
Coverage.statementStart(17085)
                    res += buffer.readBytes(len.toInt())
Coverage.statementStart(17086)
                }
Coverage.statementStart(17087)
            }
Coverage.statementStart(17088)
            if (res.size < 4) {
Coverage.ifStart(17089)
                return null
            }
Coverage.statementStart(17090)
            val result = DynamicByteArray(res)
Coverage.statementStart(17091)
            if (res.size < result.getInt(0)) {
Coverage.ifStart(17092)
                result.setInt(res.size, 0)//ensure there are enough available Bytes
Coverage.statementStart(17093)
            }
Coverage.statementStart(17094)
            return result
        }
    }
    fun readAsString(): String {
Coverage.funStart(17095)
        var result: String = ""
Coverage.statementStart(17096)
        val file = fopen(filename, "r")
Coverage.statementStart(17097)
        if (file == null) {
Coverage.ifStart(17098)
            throw Exception("can not open file $filename")
        }
Coverage.statementStart(17099)
        try {
Coverage.statementStart(17100)
            memScoped {
Coverage.statementStart(17101)
                val bufferLength = 64 * 1024
Coverage.statementStart(17102)
                val buffer = allocArray<ByteVar>(bufferLength)
Coverage.statementStart(17103)
                while (true) {
Coverage.whileLoopStart(17104)
                    val nextLine = fgets(buffer, bufferLength, file)?.toKString()
Coverage.statementStart(17105)
                    if (nextLine == null || nextLine.isEmpty()) {
Coverage.ifStart(17106)
                        break
                    }
Coverage.statementStart(17107)
                    result += nextLine
Coverage.statementStart(17108)
                }
Coverage.statementStart(17109)
            }
Coverage.statementStart(17110)
        } finally {
Coverage.statementStart(17111)
            fclose(file)
Coverage.statementStart(17112)
        }
Coverage.statementStart(17113)
        return result
    }
    fun walk(action: (String) -> Unit) {
Coverage.funStart(17114)
        val d = opendir(filename);
Coverage.statementStart(17115)
        if (d != null) {
Coverage.ifStart(17116)
            while (true) {
Coverage.whileLoopStart(17117)
                val dir = readdir(d)
Coverage.statementStart(17118)
                if (dir == null) {
Coverage.ifStart(17119)
                    break
                }
Coverage.statementStart(17120)
                action(filename + "/" + dir.pointed.d_name!!.toKString())
Coverage.statementStart(17121)
            }
Coverage.statementStart(17122)
            closedir(d);
Coverage.statementStart(17123)
        }
Coverage.statementStart(17124)
    }
    fun readAsDynamicByteArray(): DynamicByteArray {
Coverage.funStart(17125)
        var res = ByteArray(0)
Coverage.statementStart(17126)
        val file = fopen(filename, "r")
Coverage.statementStart(17127)
        if (file == null) {
Coverage.ifStart(17128)
            throw Exception("can not open file $filename")
        }
Coverage.statementStart(17129)
        try {
Coverage.statementStart(17130)
            memScoped {
Coverage.statementStart(17131)
                val bufferLength = 64 * 1024
Coverage.statementStart(17132)
                val buffer = allocArray<ByteVar>(bufferLength)
Coverage.statementStart(17133)
                while (true) {
Coverage.whileLoopStart(17134)
                    val len = fread(buffer, 1L.toULong(), bufferLength.toULong(), file)
Coverage.statementStart(17135)
                    if (len == (0L).toULong()) {
Coverage.ifStart(17136)
                        break
                    }
Coverage.statementStart(17137)
                    res += buffer.readBytes(len.toInt())
Coverage.statementStart(17138)
                }
Coverage.statementStart(17139)
            }
Coverage.statementStart(17140)
        } finally {
Coverage.statementStart(17141)
            fclose(file)
Coverage.statementStart(17142)
        }
Coverage.statementStart(17143)
        return DynamicByteArray(res)
    }
    fun write(buffer: DynamicByteArray) {
Coverage.funStart(17144)
        val file = fopen(filename, "w")
Coverage.statementStart(17145)
        if (file == null) {
Coverage.ifStart(17146)
            throw Exception("can not open file $filename")
        }
Coverage.statementStart(17147)
        try {
Coverage.statementStart(17148)
            var offset = 0
Coverage.statementStart(17149)
            val buf = buffer.finish()
Coverage.statementStart(17150)
            while (offset < buffer.pos) {
Coverage.whileLoopStart(17151)
                val len = fwrite(buf.refTo(offset), 1L.toULong(), (buffer.pos - offset).toULong(), file)
Coverage.statementStart(17152)
                offset += len.toInt()
Coverage.statementStart(17153)
            }
Coverage.statementStart(17154)
        } finally {
Coverage.statementStart(17155)
            fclose(file)
Coverage.statementStart(17156)
        }
Coverage.statementStart(17157)
    }
    fun printWriter(action: (PrintWriter) -> Unit) {
Coverage.funStart(17158)
        val p = PrintWriter(this)
Coverage.statementStart(17159)
        try {
Coverage.statementStart(17160)
            p.open()
Coverage.statementStart(17161)
            action(p)
Coverage.statementStart(17162)
        } finally {
Coverage.statementStart(17163)
            p.close()
Coverage.statementStart(17164)
        }
Coverage.statementStart(17165)
    }
}
class PrintWriter(val f: File) {
    var file: CValuesRef<FILE>? = null
    fun open() {
Coverage.funStart(17166)
        file = fopen(f.filename, "w")
Coverage.statementStart(17167)
        if (f == null) {
Coverage.ifStart(17168)
            throw Exception("can not open file ${f.filename}")
        }
Coverage.statementStart(17169)
    }
    fun close() {
Coverage.funStart(17170)
        fclose(file)
Coverage.statementStart(17171)
    }
    fun println(s: String) {
Coverage.funStart(17172)
        luposfprintf(file, s + "\n")
Coverage.statementStart(17173)
    }
}
