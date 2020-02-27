package lupos.s00misc

import java.io.File
import java.io.InputStream


fun readFileContents(filename: String): String {
    val inputStream: InputStream = File(filename).inputStream()
    return inputStream.bufferedReader().use { it.readText() }
}
