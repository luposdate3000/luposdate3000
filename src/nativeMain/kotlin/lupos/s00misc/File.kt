package lupos.s00misc

interface PrintWriter {
    fun println(s: String)
}

expect class File(filename: String) {
    val filename: String
    fun readAsString(): String
    fun walk(action: (String) -> Unit)
    fun readAsDynamicByteArray(): DynamicByteArray
    fun write(buffer: DynamicByteArray)
    fun printWriter(action: (PrintWriter) -> Unit)
}
