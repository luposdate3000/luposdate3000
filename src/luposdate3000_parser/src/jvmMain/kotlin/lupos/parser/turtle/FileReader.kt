package lupos.parser.turtle
import java.io.FileReader
public actual class MyFileReader public actual constructor(name: String) {
    private val reader = FileReader(name)
    public actual fun read(buf: CharArray): Int = reader.read(buf)
    public actual fun read(buf: CharArray, offset: Int, len: Int): Int = reader.read(buf, offset, len)
}
