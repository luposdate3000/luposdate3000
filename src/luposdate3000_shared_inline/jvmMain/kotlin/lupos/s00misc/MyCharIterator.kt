package lupos.s00misc

import java.io.BufferedInputStream
import java.io.DataInputStream
import java.io.FileInputStream
import java.io.InputStream

internal class MyCharIterator(file: File) : CharIterator() {
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
