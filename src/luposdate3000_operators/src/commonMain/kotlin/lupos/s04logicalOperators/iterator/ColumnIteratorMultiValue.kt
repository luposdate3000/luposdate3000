package lupos.s04logicalOperators.iterator
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import kotlin.jvm.JvmField
public object ColumnIteratorMultiValue {
    public operator fun invoke(values: IntArray): ColumnIteratorMultiValue3 = ColumnIteratorMultiValue3(values, values.size)
    public operator fun invoke(values: IntArray, size: Int): ColumnIteratorMultiValue3 = ColumnIteratorMultiValue3(values, size)
    public operator fun invoke(values: MutableList<Int>): ColumnIteratorMultiValue1 = ColumnIteratorMultiValue1(values)
    public operator fun invoke(iterator: Iterator<Int>): ColumnIteratorMultiValue2 = ColumnIteratorMultiValue2(iterator)
}
public class ColumnIteratorMultiValue1(@JvmField public val values: MutableList<Int>) : ColumnIterator() {
    @JvmField
    public var index: Int = 0
    public /*suspend*/ override fun close() {
        index = values.size
    }
    public /*suspend*/ override fun next(): Int {
        return if (index == values.size) {
            ResultSetDictionaryExt.nullValue
        } else {
            values[index++]
        }
    }
}
public class ColumnIteratorMultiValue3(@JvmField public val values: IntArray, @JvmField public val size: Int) : ColumnIterator() {
    @JvmField
    public var index: Int = 0
    public /*suspend*/ override fun close() {
        index = size
    }
    public /*suspend*/ override fun next(): Int {
        return if (index == size) {
            ResultSetDictionaryExt.nullValue
        } else {
            values[index++]
        }
    }
}
public class ColumnIteratorMultiValue2(@JvmField public val iterator: Iterator<Int>) : ColumnIterator() {
    @JvmField
    public var label: Int = 1
    public /*suspend*/ override fun close() {
        label = 0
    }
    public /*suspend*/ override fun next(): Int {
        return if (label != 0 && iterator.hasNext()) {
            iterator.next()
        } else {
            ResultSetDictionaryExt.nullValue
        }
    }
}
