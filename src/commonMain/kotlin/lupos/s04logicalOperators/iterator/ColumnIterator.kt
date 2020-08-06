package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value

abstract class ColumnIterator() {
    abstract fun next(): Value
    abstract fun close()
}
