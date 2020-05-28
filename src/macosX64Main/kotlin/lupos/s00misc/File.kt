package lupos.s00misc
import dirent.*
import kotlin.jvm.JvmField
import kotlin.native.concurrent.*
import kotlinx.cinterop.*
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.memScoped
import lupos.s00misc.Coverage
import lupos.s04logicalOperators.Query
import stdio.*
class File {
    val filename: String
    constructor(filename: String) {
        this.filename = filename
    }
    fun readAsString(): String {
Coverage.funStart(17349)
        var result: String = ""
Coverage.statementStart(17350)
        val file = fopen(filename, "r")
Coverage.statementStart(17351)
        if (file == null) {
Coverage.ifStart(17352)
            throw Exception("can not open file $filename")
        }
Coverage.statementStart(17353)
        try {
Coverage.statementStart(17354)
            memScoped {
Coverage.statementStart(17355)
                val bufferLength = 64 * 1024
Coverage.statementStart(17356)
                val buffer = allocArray<ByteVar>(bufferLength)
Coverage.statementStart(17357)
                while (true) {
Coverage.whileLoopStart(17358)
                    val nextLine = fgets(buffer, bufferLength, file)?.toKString()
Coverage.statementStart(17359)
                    if (nextLine == null || nextLine.isEmpty()) {
Coverage.ifStart(17360)
                        break
                    }
Coverage.statementStart(17361)
                    result += nextLine
Coverage.statementStart(17362)
                }
Coverage.statementStart(17363)
            }
Coverage.statementStart(17364)
        } finally {
Coverage.statementStart(17365)
            fclose(file)
Coverage.statementStart(17366)
        }
Coverage.statementStart(17367)
        return result
    }
    fun walk(action: (String) -> Unit) {
Coverage.funStart(17368)
        val d = opendir(filename);
Coverage.statementStart(17369)
        if (d != null) {
Coverage.ifStart(17370)
            while (true) {
Coverage.whileLoopStart(17371)
                val dir = readdir(d)
Coverage.statementStart(17372)
                if (dir == null) {
Coverage.ifStart(17373)
                    break
                }
Coverage.statementStart(17374)
                action(dir.pointed.d_name!!.toKString())
Coverage.statementStart(17375)
            }
Coverage.statementStart(17376)
            closedir(d);
Coverage.statementStart(17377)
        }
Coverage.statementStart(17378)
    }
    fun readAsDynamicByteArray(): DynamicByteArray {
Coverage.funStart(17379)
        var res = ByteArray(0)
Coverage.statementStart(17380)
        val file = fopen(filename, "r")
Coverage.statementStart(17381)
        if (file == null) {
Coverage.ifStart(17382)
            throw Exception("can not open file $filename")
        }
Coverage.statementStart(17383)
        try {
Coverage.statementStart(17384)
            memScoped {
Coverage.statementStart(17385)
                val bufferLength = 64 * 1024
Coverage.statementStart(17386)
                val buffer = allocArray<ByteVar>(bufferLength)
Coverage.statementStart(17387)
                while (true) {
Coverage.whileLoopStart(17388)
                    val len = fread(buffer, 1L.toULong(), bufferLength.toULong(), file)
Coverage.statementStart(17389)
                    if (len == (0L).toULong()) {
Coverage.ifStart(17390)
                        break
                    }
Coverage.statementStart(17391)
                    res += buffer.readBytes(len.toInt())
Coverage.statementStart(17392)
                }
Coverage.statementStart(17393)
            }
Coverage.statementStart(17394)
        } finally {
Coverage.statementStart(17395)
            fclose(file)
Coverage.statementStart(17396)
        }
Coverage.statementStart(17397)
        return DynamicByteArray(res)
    }
    fun write(buffer: DynamicByteArray) {
Coverage.funStart(17398)
        val file = fopen(filename, "w")
Coverage.statementStart(17399)
        if (file == null) {
Coverage.ifStart(17400)
            throw Exception("can not open file $filename")
        }
Coverage.statementStart(17401)
        try {
Coverage.statementStart(17402)
            var offset = 0
Coverage.statementStart(17403)
            val buf = buffer.finish()
Coverage.statementStart(17404)
            while (offset < buffer.pos) {
Coverage.whileLoopStart(17405)
                val len = fwrite(buf.refTo(offset), 1L.toULong(), (buffer.pos - offset).toULong(), file)
Coverage.statementStart(17406)
                offset += len.toInt()
Coverage.statementStart(17407)
            }
Coverage.statementStart(17408)
        } finally {
Coverage.statementStart(17409)
            fclose(file)
Coverage.statementStart(17410)
        }
Coverage.statementStart(17411)
    }
    fun printWriter(action: (PrintWriter) -> Unit) {
Coverage.funStart(17412)
        val p = PrintWriter(this)
Coverage.statementStart(17413)
        try {
Coverage.statementStart(17414)
            p.open()
Coverage.statementStart(17415)
            action(p)
Coverage.statementStart(17416)
        } finally {
Coverage.statementStart(17417)
            p.close()
Coverage.statementStart(17418)
        }
Coverage.statementStart(17419)
    }
}
class PrintWriter(val f: File) {
    var file: CPointer<FILE>? = null
    fun open() {
Coverage.funStart(17420)
        file = fopen(f.filename, "w")
Coverage.statementStart(17421)
        if (f == null) {
Coverage.ifStart(17422)
            throw Exception("can not open file ${f.filename}")
        }
Coverage.statementStart(17423)
    }
    fun close() {
Coverage.funStart(17424)
        fclose(file)
Coverage.statementStart(17425)
    }
    fun println(s: String) {
Coverage.funStart(17426)
        luposfprintf(file, s + "\n")
Coverage.statementStart(17427)
    }
}
