package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.Value

abstract class ColumnIterator() {
    abstract suspend fun next(): Value
    abstract suspend fun close()
}
