package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value

open class ColumnIteratorEmpty() : ColumnIterator() {
    override suspend fun next(): Value {
        return ResultSetDictionary.nullValue
    }

    override suspend fun close() {
    }
}
