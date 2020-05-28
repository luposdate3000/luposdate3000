package lupos.s04arithmetikOperators
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
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
    open fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(1998)
        return {
Coverage.statementStart(1999)
            /*return*/query.dictionary.getValue(evaluateID(row)())
        }
Coverage.statementStart(2000)
    }
    open fun evaluateID(row: IteratorBundle): () -> Value {
Coverage.funStart(2001)
        return {
Coverage.statementStart(2002)
            /*return*/query.dictionary.createValue(evaluate(row)())
        }
Coverage.statementStart(2003)
    }
    open fun enforcesBooleanOrError() = false
    override fun calculateHistogram(): HistogramResult = throw Exception("not implemented")
}
