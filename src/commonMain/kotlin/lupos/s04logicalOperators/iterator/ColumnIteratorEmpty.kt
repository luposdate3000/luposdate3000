package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value

open class ColumnIteratorEmpty() : ColumnIterator() {
    override fun next(): Value {
        return ResultSetDictionary.nullValue
    }

    override fun close() {
    }
}
