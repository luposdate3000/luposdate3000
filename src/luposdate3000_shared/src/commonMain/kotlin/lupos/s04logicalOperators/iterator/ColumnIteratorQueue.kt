package lupos.s04logicalOperators.iterator
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import kotlin.jvm.JvmField
public abstract class ColumnIteratorQueue : ColumnIterator() {
    @JvmField
    public var tmp: Int = ResultSetDictionaryExt.nullValue
    @JvmField
    public val queue: MutableList<Int> = mutableListOf()
    @JvmField
    public var label: Int = 1
}
