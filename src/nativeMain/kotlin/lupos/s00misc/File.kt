package lupos.s00misc


expect class File(filename: String) {
    val filename: String
    fun readAsString(): String
    fun walk(action: (String) -> Unit)
    fun readAsDynamicByteArray(): DynamicByteArray
    fun write(buffer: DynamicByteArray)
    fun printWriter(action: (Any) -> Unit)
}
