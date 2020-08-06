package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition

class ColumnIteratorAggregate() : ColumnIterator() {
    var value: ValueDefinition = ResultSetDictionary.undefValue2
    var count = 0
    var evaluate: () -> Unit = ::_evaluate
    fun _evaluate() {
    }

    override fun next(): Value? {
        return null
    }

    override fun close() {
    }
}
