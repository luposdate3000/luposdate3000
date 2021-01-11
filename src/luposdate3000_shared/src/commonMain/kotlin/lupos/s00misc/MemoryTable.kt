package lupos.s00misc
import lupos.s04logicalOperators.IQuery
public class MemoryTable public constructor (public val columns: Array<String>) {
    public val data: MutableList<IntArray> = mutableListOf()
    public var booleanResult: Boolean? = null
    public var query: IQuery? = null
    public companion object {
        @Suppress("NOTHING_TO_INLINE") internal inline operator fun invoke(a: MemoryTable, b: MemoryTable): MemoryTable {
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
