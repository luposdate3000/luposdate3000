package lupos.s04arithmetikOperators
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.EvaluationException
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
abstract class AOPBase(
    query: IQuery,
    operatorID: EOperatorID,
    classname: String,
    children: Array<IOPBase>
) :
    OPBase(query, operatorID, classname, children, ESortPriority.PREVENT_ANY), IAOPBase {
    fun evaluateAsBoolean(row: IteratorBundle): () -> Boolean {
        if (enforcesBooleanOrError()) {
            val tmp = evaluateID(row)
            return {
                tmp() == ResultSetDictionaryExt.booleanTrueValue
            }
        } else {
            val tmp = evaluate(row)
            return {
                var res: Boolean
                try {
                    val value = tmp()
                    res = value.toBoolean()
                } catch (e: EvaluationException) {
                    res = false
                } catch (e: Throwable) {
                    res = false
                    SanityCheck.println { "TODO exception 48" }
                    e.printStackTrace()
                }
                res
            }
        }
    }
    open fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        return {
            query.getDictionary().getValue(evaluateID(row)())
        }
    }
    open fun evaluateID(row: IteratorBundle): () -> Int {
        return {
            query.getDictionary().createValue(evaluate(row)())
        }
    }
    open fun enforcesBooleanOrError(): Boolean = false
    override fun getPartitionCount(variable: String): Int = SanityCheck.checkUnreachable()
    override /*suspend*/ fun calculateHistogram(): HistogramResult = SanityCheck.checkUnreachable()
}
