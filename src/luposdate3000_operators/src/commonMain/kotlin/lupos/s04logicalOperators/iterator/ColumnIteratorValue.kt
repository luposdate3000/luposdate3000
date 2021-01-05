package lupos.s04logicalOperators.iterator
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
class ColumnIteratorValue : ColumnIterator() {
    companion object {
        @JvmName("invoke") internal inline operator fun invoke(value: Int): ColumnIteratorValue {
            val res = ColumnIteratorValue()
            res.value = value
            res.done = false
            return res
        }
    }
    @JvmField
    var value: Int = ResultSetDictionaryExt.nullValue
    @JvmField
    var done: Boolean = false
    override /*suspend*/ fun close() {
        done = true
    }
    override /*suspend*/ fun next(): Int {
        return if (done) {
            ResultSetDictionaryExt.nullValue
        } else {
            done = true
            value
        }
    }
}
