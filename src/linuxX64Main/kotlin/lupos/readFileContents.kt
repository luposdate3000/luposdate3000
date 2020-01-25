package lupos

import kotlinx.cinterop.*
import platform.posix.*

actual fun readFileContents(filename: String): String {
var result:String=""
    println("filename $filename")
val file = fopen(filename, "r")
    if (file == null) {
        perror("cannot open input file $filename")
        return ""
    }
    try {
        memScoped {
            val bufferLength = 64 * 1024
            val buffer = allocArray<ByteVar>(bufferLength)

            while(true) {
                val nextLine = fgets(buffer, bufferLength, file)?.toKString()
                if (nextLine == null || nextLine.isEmpty()) break
		result+=nextLine
            }
        }
    } finally {
        fclose(file)
    }
return result
}
