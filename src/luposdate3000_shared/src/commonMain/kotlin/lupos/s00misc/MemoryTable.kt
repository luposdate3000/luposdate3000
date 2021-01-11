package lupos.s00misc
import lupos.s04logicalOperators.IQuery
import kotlin.jvm.JvmField
public class MemoryTable public constructor (@JvmField public val columns: Array<String>) {
    @JvmField
    public val data: MutableList<IntArray> = mutableListOf()
    @JvmField
    public var booleanResult: Boolean? = null
    @JvmField
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
