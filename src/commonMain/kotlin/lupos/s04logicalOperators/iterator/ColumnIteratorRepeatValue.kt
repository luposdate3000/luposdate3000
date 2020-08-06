package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value

class ColumnIteratorRepeatValue(@JvmField val count: Int, @JvmField val value: Value) : ColumnIterator() {
    @JvmField
    var index = 0
    override fun close() {
        index = count
    }

    override fun next(): Value? {
        if (index == count) {
            return null
        } else {
            index++
            return value
        }
    }
}
