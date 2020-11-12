package lupos.s04logicalOperators.iterator

import kotlin.jvm.JvmField
import lupos.s03resultRepresentation.ResultSetDictionaryExt

class ColumnIteratorValue : ColumnIterator() {
    companion object {
        inline operator fun invoke(value: Int): ColumnIteratorValue {
            val res = ColumnIteratorValue()
            res.value = value
            res.done = false
            return res
        }
    }

    @JvmField
    var value = ResultSetDictionaryExt.nullValue

    @JvmField
    var done = false
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
