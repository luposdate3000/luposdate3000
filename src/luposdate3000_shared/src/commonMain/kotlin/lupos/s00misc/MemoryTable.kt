package lupos.s00misc

import lupos.s04logicalOperators.IQuery

class MemoryTable(val columns: Array<String>) {
    val data: MutableList<IntArray> = mutableListOf()
    var booleanResult: Boolean? = null
    var query: IQuery? = null

    companion object {
        inline operator fun invoke(a: MemoryTable, b: MemoryTable): MemoryTable {
            if (a.columns.size != b.columns.size) {
                throw Exception("incompatible input")
            }
            if (a.booleanResult != null) {
                throw Exception("incompatible input")
            }
            if (b.booleanResult != null) {
                throw Exception("incompatible input")
            }
            for (i in a.columns.indices) {
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
