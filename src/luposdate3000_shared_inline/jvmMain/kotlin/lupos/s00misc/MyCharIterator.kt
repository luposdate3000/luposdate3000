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
import lupos.s00misc.IMyInputStream
import lupos.s00misc.Parallel

internal class MyCharIterator(val file: File) : CharIterator() {
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
