package lupos.modulename
import java.io.BufferedInputStream
import java.io.DataInputStream
import java.io.FileInputStream
import lupos.s00misc.File
internal class MyCharIterator(file: File) : CharIterator() {
    private val fis = FileInputStream(file.filename)
    private val bis = BufferedInputStream(fis)
    private val dis = DataInputStream(bis)
    override fun hasNext(): Boolean {
        val res = dis.available() > 0
        if (!res) {
            dis.close()
        }
        return res
    }
    override fun nextChar(): Char {
        return dis.readChar()
    }
}
