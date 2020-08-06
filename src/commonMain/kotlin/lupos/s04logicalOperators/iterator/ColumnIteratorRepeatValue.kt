package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value

class ColumnIteratorRepeatValue(@JvmField val count: Int, @JvmField val value: Value) : ColumnIterator() {
    @JvmField
    var index = 0
    override fun close() {
        index = count
    }

    override fun next(): Value {
        if (index == count) {
            return ResultSetDictionary.nullValue
        } else {
            index++
            return value
        }
    }
}
