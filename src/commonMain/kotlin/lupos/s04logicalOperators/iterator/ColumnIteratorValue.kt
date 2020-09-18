package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value

class ColumnIteratorValue(@JvmField val value: Value) : ColumnIterator() {
    @JvmField
    var done = false
    override suspend fun close() {
        done = true
    }

    override suspend fun next(): Value {
        if (done) {
            return ResultSetDictionary.nullValue
        } else {
            done = true
            return value
        }
    }
}
