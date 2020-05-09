package lupos.s04logicalOperators.iterator
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition


class ColumnIteratorAggregate() : ColumnIterator() {
    var value: ValueDefinition = ResultSetDictionary.undefValue2
    var count = 0
    var evaluate: suspend () -> Unit = ::_evaluate
    suspend fun _evaluate() {
    }
}
