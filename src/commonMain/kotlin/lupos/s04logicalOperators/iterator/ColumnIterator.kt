package lupos.s04logicalOperators.iterator

import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value

abstract class ColumnIterator() {
    abstract suspend fun next(): Value
    abstract suspend fun close()
    open fun hasSIPSupport(): Boolean = false
    open fun nextSIP(columnIdx: Int, minValue: Value, skippedElements: () -> Int): Value = SanityCheck.checkUnreachable()
    open fun nextSIP(skipCount: Int): Value = SanityCheck.checkUnreachable()
}
