package lupos.sparql1_1.test

import java.io.File
import java.io.InputStream

actual fun readFileContents(filename: String): String {
    println(File(filename).absolutePath)
    val inputStream: InputStream = File(filename).inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }
    return inputString
}