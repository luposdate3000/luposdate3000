package lupos.s00misc
import java.io.File
import java.io.InputStream



fun readFileContents(filename: String): String {
    println("filename $filename")
    val inputStream: InputStream = File(filename).inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }
    return inputString
}
