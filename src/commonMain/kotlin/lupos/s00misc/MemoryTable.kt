package lupos.s00misc

class MemoryTable(val columns: Array<String>) {
    val data = mutableListOf<IntArray>()
    var booleanResult: Boolean? = null

    companion object {
operator        inline fun invoke(a: MemoryTable, b: MemoryTable): MemoryTable {
            if (a.columns.size != b.columns.size) {
                throw Exception("incompatible input")
            }
            if (a.booleanResult != null) {
                throw Exception("incompatible input")
            }
            if (b.booleanResult != null) {
                throw Exception("incompatible input")
            }
            for (i in 0 until a.columns.size) {
                if (a.columns[i] != b.columns[i]) {
                    throw Exception("incompatible input")
                }
            }
            val res = MemoryTable(a.columns)
            res.data.addAll(a.data)
            res.data.addAll(b.data)
            return res
        }
    }
}
