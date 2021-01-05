package lupos.s00misc
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream
internal actual class File actual constructor(@JvmField val filename: String) {
    internal actual inline fun createTempFile(prefix: String, suffix: String, directory: String): String {
        val f = createTempFile(prefix, suffix, java.io.File(directory))
        return f.absolutePath
    }
    internal actual inline fun exists() = java.io.File(filename).exists()
    internal actual inline fun mkdirs() = java.io.File(filename).mkdirs()
    internal actual inline fun deleteRecursively() = java.io.File(filename).deleteRecursively()
    internal actual inline fun length() = java.io.File(filename).length()
    internal actual inline fun readAsString() = java.io.File(filename).readText()
    internal actual inline fun readAsCharIterator(): CharIterator = MyCharIterator(this)
    internal actual inline fun readAsInputStream(): IMyInputStream = MyInputStream(FileInputStream(java.io.File(filename)))
    internal actual inline fun walk(crossinline action: (String) -> Unit) {
        java.nio.file.Files.walk(java.nio.file.Paths.get(filename), 1).forEach {
            val tmp = it.toString()
            if (tmp.length > filename.length) {
                action(tmp)
            }
        }
    }
    internal actual inline fun myPrintWriter(): MyPrintWriter = MyPrintWriter(java.io.File(filename))
    internal actual inline fun printWriter(crossinline action: (MyPrintWriter) -> Unit) {
        val printer = MyPrintWriter(java.io.File(filename))
        try {
            action(printer)
        } finally {
            printer.close()
        }
    }
    internal /*suspend*/ actual inline fun printWriterSuspended(crossinline action: /*suspend*/ (MyPrintWriter) -> Unit) {
        val printer = MyPrintWriter(java.io.File(filename))
        try {
            action(printer)
        } finally {
            printer.close()
        }
    }
    internal actual inline fun forEachLine(crossinline action: (String) -> Unit) = java.io.File(filename).forEachLine {
        action(it)
    }
    internal /*suspend*/ actual inline fun forEachLineSuspended(crossinline action: /*suspend*/ (String) -> Unit) = java.io.File(filename).forEachLine {
        Parallel.runBlocking {
            action(it)
        }
    }
    internal actual inline fun dataOutputStream(crossinline action: (MyDataOutputStream) -> Unit) {
        var dos: DataOutputStream? = null
        try {
            val fos = FileOutputStream(filename)
            val bos = BufferedOutputStream(fos)
            dos = DataOutputStream(bos)
            action(MyDataOutputStream(dos))
        } finally {
            dos?.close()
        }
    }
    internal actual inline fun dataOutputStreamSuspend(crossinline action: (MyDataOutputStream) -> Unit) {
        var dos: DataOutputStream? = null
        try {
            val fos = FileOutputStream(filename)
            val bos = BufferedOutputStream(fos)
            dos = DataOutputStream(bos)
            action(MyDataOutputStream(dos))
        } finally {
            dos?.close()
        }
    }
    internal actual inline fun dataInputStream(crossinline action: (MyDataInputStream) -> Unit) {
        var dis: DataInputStream? = null
        try {
            val fis = FileInputStream(filename)
            val bis = BufferedInputStream(fis)
            dis = DataInputStream(bis)
            action(MyDataInputStream(dis))
        } finally {
            dis?.close()
        }
    }
    internal /*suspend*/ actual inline fun dataInputStreamSuspended(crossinline action: /*suspend*/ (MyDataInputStream) -> Unit) {
        var dis: DataInputStream? = null
        try {
            val fis = FileInputStream(filename)
            val bis = BufferedInputStream(fis)
            dis = DataInputStream(bis)
            action(MyDataInputStream(dis))
        } finally {
            dis?.close()
        }
    }
    actual override fun equals(other: Any?): Boolean {
        if (other !is File) {
            return false
        }
        val file1 = java.io.File(filename)
        val file2 = java.io.File(other.filename)
        val EOF = -1
        if (file1 == file2) {
            return true
        }
        if (file1.canonicalFile.equals(file2.canonicalFile)) {
            return true
        }
        val file1Exists = file1.exists()
        if (file1Exists != file2.exists()) {
            return false
        }
        if (!file1Exists) {
            return true
        }
        if (file1.isDirectory || file2.isDirectory) {
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
    }
    internal actual inline fun openDataOutputStream(append: Boolean): MyDataOutputStream {
        var dos: DataOutputStream? = null
        val fos = FileOutputStream(filename, append)
        val bos = BufferedOutputStream(fos)
        dos = DataOutputStream(bos)
        return MyDataOutputStream(dos)
    }
}
