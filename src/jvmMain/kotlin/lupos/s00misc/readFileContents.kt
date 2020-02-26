package lupos.s00misc
import lupos.s00misc.EOperatorID

import java.io.File
import java.io.InputStream


fun readFileContents(filename: String): String {
    val inputStream: InputStream = File(filename).inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }
    return inputString
}
