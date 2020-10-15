package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.Value

abstract class ColumnIterator() {
    abstract suspend fun next(): Value
    abstract suspend fun close()
    suspend open fun nextSIP(minValue: Value, result: IntArray) {
        result[0] = 0
        result[1] = next()
    }

    suspend open fun skipSIP(skipCount: Int): Value {
        for (i in 0 until skipCount) {
            next()
        }
        return next()
    }
}
