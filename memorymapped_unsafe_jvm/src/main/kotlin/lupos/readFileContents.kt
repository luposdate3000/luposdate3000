package lupos

import java.io.File
import java.io.InputStream

actual fun readFileContents(filename: String): String {
    val inputStream: InputStream = File(filename).inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }
    return inputString
}
