package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPMultiplication(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPMultiplicationID, "AOPMultiplication", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " * " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2471)
        if (other !is AOPMultiplication) {
Coverage.ifStart(2472)
            return false
        }
Coverage.statementStart(2473)
        for (i in children.indices) {
Coverage.forLoopStart(2474)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2475)
                return false
            }
Coverage.statementStart(2476)
        }
Coverage.statementStart(2477)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2478)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2479)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2480)
        return {
Coverage.statementStart(2481)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(2482)
            val a = childA()
Coverage.statementStart(2483)
            val b = childB()
Coverage.statementStart(2484)
            try {
Coverage.statementStart(2485)
                if (a is ValueDouble || b is ValueDouble) {
Coverage.ifStart(2486)
                    res = ValueDouble(a.toDouble() * b.toDouble())
Coverage.statementStart(2487)
                } else if (a is ValueDecimal || b is ValueDecimal) {
Coverage.ifStart(2488)
                    res = ValueDecimal(a.toDouble() * b.toDouble())
Coverage.statementStart(2489)
                } else if (a is ValueInteger || b is ValueInteger) {
Coverage.ifStart(2490)
                    res = ValueInteger(a.toInt() * b.toInt())
Coverage.statementStart(2491)
                }
Coverage.statementStart(2492)
            } catch (e: Throwable) {
Coverage.statementStart(2493)
            }
Coverage.statementStart(2494)
/*return*/res
        }
Coverage.statementStart(2495)
    }
    override fun cloneOP() = AOPMultiplication(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
