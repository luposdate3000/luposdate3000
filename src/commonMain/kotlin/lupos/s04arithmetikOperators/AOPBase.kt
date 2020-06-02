package lupos.s04arithmetikOperators

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

abstract class AOPBase(query: Query,
                       operatorID: EOperatorID,
                       classname: String,
                       children: Array<OPBase>) :
        OPBase(query, operatorID, classname, children, ESortPriority.PREVENT_ANY) {
    fun evaluateAsBoolean(row: IteratorBundle): () -> Boolean {
        if (enforcesBooleanOrError()) {
            val tmp = evaluateID(row)
            return {
                /*return*/tmp() == ResultSetDictionary.booleanTrueValue
            }
        } else {
            val tmp = evaluate(row)
            return {
                var res: Boolean
                try {
                    val value = tmp()
                    res = value.toBoolean()
                } catch (e: Throwable) {
                    res = false
                }
/*return*/res
            }
        }
    }

    open fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        return {
            /*return*/query.dictionary.getValue(evaluateID(row)())
        }
/*Coverage Unreachable*/
    }

    open fun evaluateID(row: IteratorBundle): () -> Value {
        return {
            /*return*/query.dictionary.createValue(evaluate(row)())
        }
/*Coverage Unreachable*/
    }

    open fun enforcesBooleanOrError() = false
    override fun calculateHistogram(): HistogramResult = SanityCheck.checkUnreachable()
}
