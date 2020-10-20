package lupos.s04arithmetikOperators.multiinput


import lupos.s00misc.EOperatorID
import lupos.s00misc.EvaluationException
import lupos.s00misc.MyBigDecimal
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.decimalZero
import lupos.s03resultRepresentation.integerZero

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

class AOPDivision(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPDivisionID, "AOPDivision", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " / " + children[1].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPDivision && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            val b = childB()
            try {
                if (a is ValueDouble || b is ValueDouble) {
                    if (b.toDouble() != 0.0) {
                        res = ValueDouble(a.toDouble() / b.toDouble())
                    }
                }
                if (a is ValueFloat || b is ValueFloat) {
                    if (b.toDouble() != 0.0) {
                        res = ValueFloat(a.toDouble() / b.toDouble())
                    }
                }
                if (a is ValueDecimal || b is ValueDecimal) {
                    if (b.toDecimal() != decimalZero) {
                        res = ValueDecimal(a.toDecimal() / b.toDecimal())
                    }
                }
                if (a is ValueInteger || b is ValueInteger) {
                    if (b.toInt() != integerZero) {
                        res = ValueDecimal(a.toDecimal() / b.toDecimal())
                    }
                }
            } catch (e: EvaluationException) {
            } catch (e: Throwable) {
                SanityCheck.println({ "TODO exception 23" })
                e.printStackTrace()
            }
/*return*/res
        }

    }

    override fun cloneOP() = AOPDivision(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
