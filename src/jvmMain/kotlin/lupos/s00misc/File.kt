package lupos.s00misc
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
class File(@JvmField val filename: String) {
    companion object {
        fun readStdInAsDynamicByteArray(): DynamicByteArray? {
Coverage.funStart(16126)
            System.`in`.use { instream ->
Coverage.statementStart(16127)
                val data = instream.readBytes()
Coverage.statementStart(16128)
                if (data.size < 4) {
Coverage.ifStart(16129)
                    return null
                }
Coverage.statementStart(16130)
                return DynamicByteArray(data)
            }
Coverage.statementStart(16131)
        }
    }
    fun exists() = java.io.File(filename).exists()
    fun mkdirs() = java.io.File(filename).mkdirs()
    fun deleteRecursively() = java.io.File(filename).deleteRecursively()
    fun length() = java.io.File(filename).length()
    fun readAsString() = java.io.File(filename).readText()
    suspend fun walk(action: suspend (String) -> Unit) {
Coverage.funStart(16132)
        java.io.File(filename).walk().forEach {
Coverage.forEachLoopStart(16133)
            action(filename + "/" + it.toRelativeString(java.io.File(filename)))
Coverage.statementStart(16134)
        }
Coverage.statementStart(16135)
    }
    fun readAsDynamicByteArray(): DynamicByteArray {
Coverage.funStart(16136)
        var res: DynamicByteArray?
Coverage.statementStart(16137)
        java.io.File(filename).inputStream().use { instream ->
Coverage.statementStart(16138)
            val data = instream.readBytes()
Coverage.statementStart(16139)
            res = DynamicByteArray(data)
Coverage.statementStart(16140)
        }
Coverage.statementStart(16141)
        return res!!
    }
    fun write(buffer: DynamicByteArray) {
Coverage.funStart(16142)
        java.io.File(filename).outputStream().use { out ->
Coverage.statementStart(16143)
            val data = buffer.finish()
Coverage.statementStart(16144)
            out.write(data, 0, buffer.pos)
Coverage.statementStart(16145)
        }
Coverage.statementStart(16146)
    }
    fun printWriter(action: (java.io.PrintWriter) -> Unit) = java.io.File(filename).printWriter().use {
Coverage.funStart(16147)
        action(it)
Coverage.statementStart(16148)
    }
    fun forEachLine(action: (String) -> Unit) = java.io.File(filename).forEachLine { action(it) }
    fun dataOutputStream(action: (java.io.DataOutputStream) -> Unit) {
Coverage.funStart(16149)
        var dos: DataOutputStream? = null
Coverage.statementStart(16150)
        try {
Coverage.statementStart(16151)
            val fos = FileOutputStream(filename);
Coverage.statementStart(16152)
            val bos = BufferedOutputStream(fos)
Coverage.statementStart(16153)
            dos = DataOutputStream(bos)
Coverage.statementStart(16154)
            action(dos)
Coverage.statementStart(16155)
        } finally {
Coverage.statementStart(16156)
            dos?.close()
Coverage.statementStart(16157)
        }
Coverage.statementStart(16158)
    }
    fun dataInputStream(action: (java.io.DataInputStream) -> Unit) {
Coverage.funStart(16159)
        var dis: DataInputStream? = null
Coverage.statementStart(16160)
        try {
Coverage.statementStart(16161)
            val fis = FileInputStream(filename)
Coverage.statementStart(16162)
            val bis = BufferedInputStream(fis)
Coverage.statementStart(16163)
            dis = DataInputStream(bis)
Coverage.statementStart(16164)
            action(dis)
Coverage.statementStart(16165)
        } finally {
Coverage.statementStart(16166)
            dis?.close()
Coverage.statementStart(16167)
        }
Coverage.statementStart(16168)
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(16169)
        if (other !is File) {
Coverage.ifStart(16170)
            return false
        }
Coverage.statementStart(16171)
        val file1 = java.io.File(filename)
Coverage.statementStart(16172)
        val file2 = java.io.File(other.filename)
Coverage.statementStart(16173)
        val EOF = -1
Coverage.statementStart(16174)
        if (file1 == file2) {
Coverage.ifStart(16175)
            return true
        }
Coverage.statementStart(16176)
        if (file1.getCanonicalFile().equals(file2.getCanonicalFile())) {
Coverage.ifStart(16177)
            return true
        }
Coverage.statementStart(16178)
        val file1Exists = file1.exists()
Coverage.statementStart(16179)
        if (file1Exists != file2.exists()) {
Coverage.ifStart(16180)
            return false
        }
Coverage.statementStart(16181)
        if (!file1Exists) {
Coverage.ifStart(16182)
            return true
        }
Coverage.statementStart(16183)
        if (file1.isDirectory() || file2.isDirectory()) {
Coverage.ifStart(16184)
            throw Exception("Can't compare directories, only files")
        }
Coverage.statementStart(16185)
        if (file1.length() != file2.length()) {
Coverage.ifStart(16186)
            return false
        }
Coverage.statementStart(16187)
        val input1 = BufferedInputStream(FileInputStream(file1))
Coverage.statementStart(16188)
        val input2 = BufferedInputStream(FileInputStream(file2))
Coverage.statementStart(16189)
        try {
Coverage.statementStart(16190)
            var ch = input1.read()
Coverage.statementStart(16191)
            while (EOF != ch) {
Coverage.whileLoopStart(16192)
                val ch2 = input2.read()
Coverage.statementStart(16193)
                if (ch != ch2) {
Coverage.ifStart(16194)
                    return false
                }
Coverage.statementStart(16195)
                ch = input1.read()
Coverage.statementStart(16196)
            }
Coverage.statementStart(16197)
            val ch2 = input2.read()
Coverage.statementStart(16198)
            return ch2 == EOF
        } finally {
Coverage.statementStart(16199)
            input1.close()
Coverage.statementStart(16200)
            input2.close()
Coverage.statementStart(16201)
        }
Coverage.statementStart(16202)
    }
}
