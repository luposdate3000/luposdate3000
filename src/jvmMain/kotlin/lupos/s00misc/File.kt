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
            System.`in`.use { instream ->
                val data = instream.readBytes()
                if (data.size < 4) {
                    return null
                }
                return DynamicByteArray(data)
            }
/*Coverage Unreachable*/
        }
    }

    fun exists() = java.io.File(filename).exists()
    fun mkdirs() = java.io.File(filename).mkdirs()
    fun deleteRecursively() = java.io.File(filename).deleteRecursively()
    fun length() = java.io.File(filename).length()
    fun readAsString() = java.io.File(filename).readText()
    suspend fun walk(action: suspend (String) -> Unit) {
        java.io.File(filename).walk().forEach {
            action(filename + "/" + it.toRelativeString(java.io.File(filename)))
        }
    }

    fun readAsDynamicByteArray(): DynamicByteArray {
        var res: DynamicByteArray?
        java.io.File(filename).inputStream().use { instream ->
            val data = instream.readBytes()
            res = DynamicByteArray(data)
        }
        return res!!
    }

    fun write(buffer: DynamicByteArray) {
        java.io.File(filename).outputStream().use { out ->
            val data = buffer.finish()
            out.write(data, 0, buffer.pos)
        }
    }

    fun printWriter(action: (java.io.PrintWriter) -> Unit) = java.io.File(filename).printWriter().use {
        action(it)
    }

    fun forEachLine(action: (String) -> Unit) = java.io.File(filename).forEachLine { action(it) }
    fun dataOutputStream(action: (java.io.DataOutputStream) -> Unit) {
        var dos: DataOutputStream? = null
        try {
            val fos = FileOutputStream(filename);
            val bos = BufferedOutputStream(fos)
            dos = DataOutputStream(bos)
            action(dos)
        } finally {
            dos?.close()
        }
    }

    fun dataInputStream(action: (java.io.DataInputStream) -> Unit) {
        var dis: DataInputStream? = null
        try {
            val fis = FileInputStream(filename)
            val bis = BufferedInputStream(fis)
            dis = DataInputStream(bis)
            action(dis)
        } finally {
            dis?.close()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is File) {
            return false
        }
        val file1 = java.io.File(filename)
        val file2 = java.io.File(other.filename)
        val EOF = -1
        if (file1 == file2) {
            return true
        }
        if (file1.getCanonicalFile().equals(file2.getCanonicalFile())) {
            return true
        }
        val file1Exists = file1.exists()
        if (file1Exists != file2.exists()) {
            return false
        }
        if (!file1Exists) {
            return true
        }
        if (file1.isDirectory() || file2.isDirectory()) {
            throw Exception("Can't compare directories, only files")
        }
        if (file1.length() != file2.length()) {
            return false
        }
        val input1 = BufferedInputStream(FileInputStream(file1))
        val input2 = BufferedInputStream(FileInputStream(file2))
        try {
            var ch = input1.read()
            while (EOF != ch) {
                val ch2 = input2.read()
                if (ch != ch2) {
                    return false
                }
                ch = input1.read()
            }
            val ch2 = input2.read()
            return ch2 == EOF
        } finally {
            input1.close()
            input2.close()
        }
/*Coverage Unreachable*/
    }
}
