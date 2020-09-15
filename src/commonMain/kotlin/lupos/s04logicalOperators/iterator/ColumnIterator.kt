package lupos.s04logicalOperators.iterator

import lupos.s00misc.classNameToString
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value

abstract class ColumnIterator() {
    abstract suspend fun next(): Value
    abstract suspend fun close()
    suspend open fun nextSIP(minValue: Value, skippedElements: (counter: Int) -> Unit): Value {
        return next()
    }

    suspend open fun skipSIP(skipCount: Int): Value {
        for (i in 0 until skipCount) {
            next()
        }
        return next()
    }
}
