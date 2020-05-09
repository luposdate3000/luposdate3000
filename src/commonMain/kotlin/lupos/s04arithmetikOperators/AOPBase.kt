package lupos.s04arithmetikOperators

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

abstract class AOPBase(query: Query,
                       operatorID: EOperatorID,
                       classname: String,
                       children: Array<OPBase>) :
        OPBase(query, operatorID, classname, children, ESortPriority.PREVENT_ANY) {
    open fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        return {
            /*return*/query.dictionary.getValue(evaluateID(row)())
        }
    }

    open fun evaluateID(row: ColumnIteratorRow): () -> Value {
        return {
            /*return*/query.dictionary.createValue(evaluate(row)())
        }
    }

    open fun enforcesBooleanOrError() = false
}
