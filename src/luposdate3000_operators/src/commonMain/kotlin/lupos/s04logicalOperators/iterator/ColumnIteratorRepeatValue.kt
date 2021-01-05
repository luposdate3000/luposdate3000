package lupos.s04logicalOperators.iterator
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import kotlin.jvm.JvmField
public class ColumnIteratorRepeatValue(@JvmField public val count: Int, @JvmField public val value: Int) : ColumnIterator() {
    @JvmField
    public var index: Int = 0
    override /*suspend*/ fun close() {
        index = count
    }
    override /*suspend*/ fun next(): Int {
        return if (index == count) {
            ResultSetDictionaryExt.nullValue
        } else {
            index++
            value
        }
    }
}
