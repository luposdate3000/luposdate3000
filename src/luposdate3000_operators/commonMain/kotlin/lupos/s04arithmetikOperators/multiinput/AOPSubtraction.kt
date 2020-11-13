package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.EvaluationException
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

class AOPSubtraction(query: IQuery, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPSubtractionID, "AOPSubtraction", arrayOf(childA, childB)) {
    override fun toSparql(): String = "(" + children[0].toSparql() + " - " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPSubtraction && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            val b = childB()
            try {
                if (a is ValueDouble || b is ValueDouble) {
                    res = ValueDouble(a.toDouble() - b.toDouble())
                } else if (a is ValueFloat || b is ValueFloat) {
                    res = ValueFloat(a.toDouble() - b.toDouble())
                } else if (a is ValueDecimal || b is ValueDecimal) {
                    res = ValueDecimal(a.toDecimal() - b.toDecimal())
                } else if (a is ValueInteger || b is ValueInteger) {
                    res = ValueInteger(a.toInt() - b.toInt())
                }
            } catch (e: EvaluationException) {
            } catch (e: Throwable) {
                SanityCheck.println { "TODO exception 16" }
                e.printStackTrace()
            }
/*return*/res
        }
    }

    override fun cloneOP(): IOPBase = AOPSubtraction(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
