package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.ResultSetDictionaryExt

import lupos.s03resultRepresentation.ValueDefinition

class ColumnIteratorAggregate() : ColumnIteratorEmpty() {
    var value: ValueDefinition = ResultSetDictionaryExt.undefValue2
    var count = 0
    var evaluate: () -> Unit = ::_evaluate
    fun _evaluate() {
    }
}
