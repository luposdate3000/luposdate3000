package lupos.s00misc

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s04logicalOperators.Query

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
        }
    }

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
        var res: DynamicByteArray? = null
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
            action(dos!!)
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
            action(dis!!)
        } finally {
            dis?.close()
        }
    }
}
