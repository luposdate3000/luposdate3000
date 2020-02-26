package lupos.s00misc
import lupos.s00misc.EOperatorID

import kotlinx.cinterop.allocArray
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import lupos.s00misc.readFileContents
import platform.posix.fclose
import platform.posix.fgets
import platform.posix.fopen
import platform.posix.perror


actual fun readFileContents(filename: String): String {
    var result: String = ""
    val file = fopen(filename, "r")
    if (file == null) {
        perror("cannot open input file $filename")
        return ""
    }
    try {
        memScoped {
            val bufferLength = 64 * 1024
            val buffer = allocArray<ByteVar>(bufferLength)

            while (true) {
                val nextLine = fgets(buffer, bufferLength, file)?.toKString()
                if (nextLine == null || nextLine.isEmpty()) break
                result += nextLine
            }
        }
    } finally {
        fclose(file)
    }
    return result
}
