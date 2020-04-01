package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*

class ColumnIteratorAggregate() : ColumnIterator() {
    var value: ValueDefinition = ResultSetDictionary.undefValue2
    var count = 0
    var evaluate: suspend () -> Unit = ::_evaluate
    suspend fun _evaluate() {
    }
}
