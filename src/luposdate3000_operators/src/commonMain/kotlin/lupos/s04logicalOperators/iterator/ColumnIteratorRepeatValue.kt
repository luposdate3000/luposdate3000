package lupos.s04logicalOperators.iterator
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import kotlin.jvm.JvmField
class ColumnIteratorRepeatValue(@JvmField val count: Int, @JvmField val value: Int) : ColumnIterator() {
    @JvmField
    var index: Int = 0
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
