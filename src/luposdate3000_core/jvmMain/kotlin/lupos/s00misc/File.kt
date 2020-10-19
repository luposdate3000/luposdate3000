package lupos.s00misc

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import kotlin.io.createTempFile
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.IMyInputStream
import lupos.s00misc.Parallel

class MyCharIterator(val file: File) : CharIterator() {
    val fis = FileInputStream(file.filename)
    val bis = BufferedInputStream(fis)
    val dis = DataInputStream(bis)
    override fun hasNext(): Boolean {
        val res = dis.available() > 0
        if (res == false) {
            dis.close()
        }
        return res
    }

    override fun nextChar(): Char {
        return dis.readChar()
    }
}

class MyInputStream(@JvmField val stream: InputStream) : IMyInputStream {
    override fun read(buf: ByteArray): Int {
        return stream.read(buf, 0, buf.size)
    }
}

internal class File(@JvmField val filename: String) {
    inline fun createTempFile(prefix: String, suffix: String, directory: String): String {
        var f = createTempFile(prefix, suffix, java.io.File(directory))
        return f.getAbsolutePath()
    }

    inline fun exists() = java.io.File(filename).exists()
    inline fun mkdirs() = java.io.File(filename).mkdirs()
    inline fun deleteRecursively() = java.io.File(filename).deleteRecursively()
    inline fun length() = java.io.File(filename).length()
    inline fun readAsString() = java.io.File(filename).readText()
    inline fun readAsCharIterator(): CharIterator = MyCharIterator(this)
    inline fun readAsInputStream(): MyInputStream = MyInputStream(FileInputStream(java.io.File(filename)))
    inline fun walk(crossinline action: (String) -> Unit) {
        java.nio.file.Files.walk(java.nio.file.Paths.get(filename), 1).forEach { it ->
            val tmp = it.toString()
            if (tmp.length > filename.length) {
                action(tmp)
            }
        }
    }

    inline fun myPrintWriter() = MyPrintWriter(java.io.File(filename))
    inline fun printWriter(crossinline action: (java.io.PrintWriter) -> Unit) = java.io.File(filename).printWriter().use {
        action(it)
    }

    inline suspend fun printWriterSuspended(crossinline action: suspend (java.io.PrintWriter) -> Unit) = java.io.File(filename).printWriter().use {
        action(it)
    }

    inline fun forEachLine(crossinline action: (String) -> Unit) = java.io.File(filename).forEachLine {
        action(it)
    }

    inline suspend fun forEachLineSuspended(crossinline action: suspend (String) -> Unit) = java.io.File(filename).forEachLine {
        Parallel.runBlocking {
            action(it)
        }
    }

    inline fun dataOutputStream(crossinline action: (java.io.DataOutputStream) -> Unit) {
        var dos: DataOutputStream? = null
        try {
            val fos = FileOutputStream(filename)
            val bos = BufferedOutputStream(fos)
            dos = DataOutputStream(bos)
            action(dos)
        } finally {
            dos?.close()
        }
    }

    inline fun dataOutputStreamSuspend(crossinline action: (java.io.DataOutputStream) -> Unit) {
        var dos: DataOutputStream? = null
        try {
            val fos = FileOutputStream(filename)
            val bos = BufferedOutputStream(fos)
            dos = DataOutputStream(bos)
            action(dos)
        } finally {
            dos?.close()
        }
    }

    inline fun dataInputStream(crossinline action: (java.io.DataInputStream) -> Unit) {
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

    suspend inline fun dataInputStreamSuspended(crossinline action: suspend (java.io.DataInputStream) -> Unit) {
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
            throw DirectoryCompareNotImplementedException()
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
