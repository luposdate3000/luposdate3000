package lupos.s00misc
import java.io.File
import java.io.InputStream
import lupos.s00misc.EOperatorID



fun readFileContents(filename: String): String {
    val inputStream: InputStream = File(filename).inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }
    return inputString
}
