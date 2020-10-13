package lupos.s00misc

class MemoryTable(val columns: Array<String>) {
    val data = mutableListOf<IntArray>()
    var booleanResult: Boolean? = null
}
