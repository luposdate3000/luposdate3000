package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.EvaluationException
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
class AOPGT(query: IQuery, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPGTID, "AOPGT", arrayOf(childA, childB)) {
    override fun toSparql(): String = "(" + children[0].toSparql() + " > " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPGT && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ResultSetDictionaryExt.errorValue2
            val a = childA()
            val b = childB()
            try {
                res = if (a > b) {
                    ResultSetDictionaryExt.booleanTrueValue2
                } else {
                    ResultSetDictionaryExt.booleanFalseValue2
                }
            } catch (e: Throwable) {
                SanityCheck.println { "TODO exception 20" }
                e.printStackTrace()
            }
            res
        }
    }
    override fun evaluateID(row: IteratorBundle): () -> Int {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: Int = ResultSetDictionaryExt.errorValue
            val a = childA()
            val b = childB()
            try {
                res = if (a > b) {
                    ResultSetDictionaryExt.booleanTrueValue
                } else {
                    ResultSetDictionaryExt.booleanFalseValue
                }
            } catch (e: EvaluationException) {
            } catch (e: Throwable) {
                SanityCheck.println { "TODO exception 21" }
                e.printStackTrace()
            }
            res
        }
    }
    override fun enforcesBooleanOrError(): Boolean = true
    override fun cloneOP(): IOPBase = AOPGT(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
