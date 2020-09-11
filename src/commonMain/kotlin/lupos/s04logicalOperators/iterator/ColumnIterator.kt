package lupos.s04logicalOperators.iterator

import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ResultSetDictionary

abstract class ColumnIterator() {
    abstract suspend fun next(): Value
    abstract suspend fun close()
    open fun hasSIP() = false
suspend    open fun nextSIP(minValue: Value, skippedElements: (counter: Int) -> Unit): Value {
        var counter = 0
        var value = next()
        while (value < minValue && value != ResultSetDictionary.nullValue) {
            value = next()
            counter++
        }
        skippedElements(counter)
        return value
    }

suspend    open fun nextSIP(skipCount: Int): Value {
        for (i in 0 until skipCount) {
            next()
        }
        return next()
    }
}
