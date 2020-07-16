package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.Luposdate3000Exception
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPEQ(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPEQID, "AOPEQ", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " = " + children[1].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPEQ && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ResultSetDictionary.booleanTrueValue2
            val a = childA()
            val b = childB()
            try {
                if (a != b) {
                    res = ResultSetDictionary.booleanFalseValue2
                }
            } catch (e: Luposdate3000Exception) {
                res = ResultSetDictionary.errorValue2
            } catch (e: Throwable) {
                res = ResultSetDictionary.errorValue2
                e.printStackTrace()
            }
            println("Calculating NEQ ${a.toSparql()} ${b.toSparql()} -> ${res.toSparql()}")
/*return*/res
        }
/*Coverage Unreachable*/
    }

    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPEQ(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
