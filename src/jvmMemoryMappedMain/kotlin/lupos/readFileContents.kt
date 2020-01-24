package lupos

import java.io.File
import java.io.InputStream

fun readFileContents(filename: String): String {
    println(File(filename).absolutePath)
    val inputStream: InputStream = File(filename).inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }
    return inputString
}
