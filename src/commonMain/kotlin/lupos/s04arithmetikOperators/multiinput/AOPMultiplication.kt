package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.EvaluationException
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPMultiplication(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPMultiplicationID, "AOPMultiplication", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " * " + children[1].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPMultiplication && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            val b = childB()
            try {
                if (a is ValueDouble || b is ValueDouble) {
                    res = ValueDouble(a.toDouble() * b.toDouble())
                } else if (a is ValueFloat || b is ValueFloat) {
                    res = ValueFloat(a.toDouble() * b.toDouble())
                } else if (a is ValueDecimal || b is ValueDecimal) {
                    res = ValueDecimal(a.toDouble() * b.toDouble())
                } else if (a is ValueInteger || b is ValueInteger) {
                    res = ValueInteger(a.toInt() * b.toInt())
                }
            } catch (e: EvaluationException) {
            } catch (e: Throwable) {
                println("TODO exception 18")
                e.printStackTrace()
            }
/*return*/res
        }
/*Coverage Unreachable*/
    }

    override fun cloneOP() = AOPMultiplication(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
